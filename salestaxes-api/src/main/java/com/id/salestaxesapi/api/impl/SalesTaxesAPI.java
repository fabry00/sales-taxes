package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.api.PurchaseException;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.obj.Price;
import com.id.salestaxesapi.obj.Receipt;
import com.id.salestaxesapi.obj.ReceiptItem;
import com.id.salestaxesapi.obj.persistent.DAOFactory;
import com.id.salestaxesapi.obj.persistent.ElementExistsException;
import com.id.salestaxesapi.obj.persistent.ElementNotFoundException;
import com.id.salestaxesapi.obj.persistent.IReceiptDAO;
import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The implementation of
 *
 * @author Fabrizio Faustinoni
 */
public class SalesTaxesAPI implements ISalesTaxesAPI {

    private final ITaxesCalculator calculator;

    public SalesTaxesAPI(ITaxesCalculator calcultor) {
        this.calculator = calcultor;
    }

    @Override
    public IReceipt purchase(IOrder order) throws PurchaseException{
        
        if(checkOrderExists(order)) {
            throw new PurchaseException("The order exists");
        }
        
        Receipt.Builder builder = new Receipt.Builder(order.getId())
                .customer(order.getCustomer())
                .date(order.getOrderDate());

        final AtomicReference<Double> salesTaxes = new AtomicReference<>(0.0);
        final AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

        // Since the item is immutable (only add operation) 
        // we can perform a parallel taxes calculation
        order.getGoods().entrySet().stream().parallel().forEach((entry) -> {
            IReceiptItem item = getReceiptItem(entry.getKey(), entry.getValue());
            builder.item(item);

            // Atomically update salesTaxed and total
            salesTaxes.updateAndGet(n -> n + item.getTotalTaxesAmount());
            total.updateAndGet(n -> n.add(item.getTotalFinalPrice().getValue()));
        });

        builder.salesTaxes(Math.round(salesTaxes.get() * 100.0) / 100.0);

        // Build the total Price
        Price.Builder priceBuilder = new Price.Builder(total.get());
        if (!order.getGoods().entrySet().isEmpty()) {
            // Set the Currency as the currency of the first element
            // We suppose that all the element have the same Currency
            IItem firstItem = order.getGoods().entrySet().iterator().next().getKey();
            priceBuilder.currency(firstItem.getPrice().getCurrency());
        }

        builder.toatal(priceBuilder.build());
        IReceipt receipt = builder.build();

        persisteReicpit(receipt);

        return receipt;
    }

    /**
     * Return all the orders stored. WARNING: this is a very very simple and
     * dummy functionality added just to complete the design.
     *
     * @return all the orders in string format
     */
    @Override
    public Set<IReceipt> getOrders() {
        DAOFactory factory = new DAOFactory();
        IReceiptDAO dao = factory.getReceiptDAO();
        return dao.findAll();
    }

    private IReceiptItem getReceiptItem(IItem item, Integer nOfItems) {
        ReceiptItem.Builder builder = new ReceiptItem.Builder(item)
                .quantity(nOfItems);
        builder.withTaxes(calculateItemTaxes(item));
        return builder.build();
    }

    private double calculateItemTaxes(IItem item) {
        int taxesPercent = this.calculator.getItemTaxes(item);
        double taxes
                = calculator.calculateTaxesAmount(item.getPrice(), taxesPercent);

        return taxes;
    }

    private boolean checkOrderExists(IOrder order) {
        DAOFactory factory = new DAOFactory();
        IReceiptDAO dao = factory.getReceiptDAO();
        try {
            dao.findReceipt(order.getId());
            return true;
        } catch (ElementNotFoundException ex) {
            Logger.getLogger(SalesTaxesAPI.class.getName())
                    .log(Level.SEVERE, "Orer exists", ex);
        }
        return false;

    }

    /**
     * Persiste the Reeipt 
     * WARNING, this functionality has been added only to
     * complete the design, and, of course, could be improved a lot
     *
     * @param receipt
     */
    private void persisteReicpit(IReceipt receipt) {
        DAOFactory factory = new DAOFactory();
        IReceiptDAO dao = factory.getReceiptDAO();
        try {
            dao.insertReceipt(receipt);
        } catch (ElementExistsException ex) {
            Logger.getLogger(SalesTaxesAPI.class.getName())
                    .log(Level.SEVERE, "Error insert", ex);
        }
    }

}
