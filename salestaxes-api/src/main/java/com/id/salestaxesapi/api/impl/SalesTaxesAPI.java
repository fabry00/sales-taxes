package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.obj.Price;
import com.id.salestaxesapi.obj.Receipt;
import com.id.salestaxesapi.obj.ReceiptItem;
import java.math.BigDecimal;
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
