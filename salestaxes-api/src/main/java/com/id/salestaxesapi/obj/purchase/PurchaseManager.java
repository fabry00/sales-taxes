package com.id.salestaxesapi.obj.purchase;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.api.PurchaseException;
import com.id.salestaxesapi.api.impl.SalesTaxesAPI;
import com.id.salestaxesapi.obj.Price;
import com.id.salestaxesapi.obj.Receipt;
import com.id.salestaxesapi.obj.ReceiptItem;
import com.id.salestaxesapi.obj.persistent.DAOFactory;
import com.id.salestaxesapi.obj.persistent.ElementNotFoundException;
import com.id.salestaxesapi.obj.persistent.IReceiptDAO;
import com.id.salestaxesapi.obj.persistent.InsertElementException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manage the purchase phase
 *
 * @author Fabrizio Faustinoni
 */
public class PurchaseManager {

    /**
     * Purchase an order
     *
     * @param order The ordere
     * @param calculator The injected taxes calculator
     * @return The receipt
     * @throws PurchaseException An exception
     */
    public IReceipt purchase(IOrder order, ITaxesCalculator calculator)
            throws PurchaseException {

        // Check if the order alredy exists
        if (checkOrderExists(order)) {
            throw new PurchaseException("The order exists");
        }

        // Create the builder
        Receipt.Builder builder = new Receipt.Builder(order.getId())
                .customer(order.getCustomer())
                .date(order.getOrderDate());

        // TAXES CALCULATION
        final AtomicReference<Double> salesTaxes = new AtomicReference<>(0.0);
        final AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

        // Since the item is immutable (only add operation) 
        // we can perform a parallel taxes calculation
        order.getGoods().entrySet().stream().parallel().forEach((entry) -> {
            IReceiptItem item = getReceiptItem(entry.getKey(), entry.getValue(),
                    calculator);
            
            builder.item(item);

            // Atomically update salesTaxed and total price
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

    private IReceiptItem getReceiptItem(IItem item, Integer nOfItems,
            ITaxesCalculator calculator) {

        ReceiptItem.Builder builder = new ReceiptItem.Builder(item)
                .quantity(nOfItems);
        builder.withTaxes(calculateItemTaxes(item, calculator));
        return builder.build();
    }

    private double calculateItemTaxes(IItem item, ITaxesCalculator calculator) {
        int taxesPercent = calculator.getItemTaxes(item);
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
        }
        return false;

    }

    /**
     * Persiste the Reeipt.
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
        } catch (InsertElementException ex) {
            Logger.getLogger(SalesTaxesAPI.class.getName())
                    .log(Level.SEVERE, "Error insert", ex);
        }
    }
}
