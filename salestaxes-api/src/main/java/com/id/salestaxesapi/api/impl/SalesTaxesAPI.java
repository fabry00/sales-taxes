package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.impl.Receipt;
import com.id.salestaxesapi.impl.ReceiptItem;
import java.util.concurrent.atomic.AtomicReference;

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
    public IReceipt purchase(IOrder order) {
        Receipt.Builder builder = new Receipt.Builder(order.getId())
                .customer(order.getCustomer())
                .date(order.getOrderDate());

        final AtomicReference<Double> salesTaxes = new AtomicReference<>(0.0);

        // Since the item is immutable (only add operation) 
        // we can perform a parallel taxes calculation
        order.getGoods().entrySet().stream().parallel().forEach((entry) -> {
            IReceiptItem item = getReceiptItem(entry.getKey(), entry.getValue());
            builder.item(item);

            // Atomically update salesTaxed
            salesTaxes.updateAndGet(n -> n + item.getTotalTaxesAmount());
        });

        builder.salesTaxes(Math.round(salesTaxes.get() * 100.0) / 100.0);
        return builder.build();
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

}
