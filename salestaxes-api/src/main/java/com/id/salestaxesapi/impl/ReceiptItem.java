package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.api.IReceiptItem;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Receipt Item implementation
 *
 * @author Fabrizio Faustinoni
 */
public class ReceiptItem implements IReceiptItem {

    private final IItem item;
    private final double taxesAmount;
    private final int quantity;

    private ReceiptItem(Builder builder) {
        this.item = builder.item;
        this.taxesAmount = builder.taxesAmount;
        this.quantity = builder.quantity;
    }

    @Override
    public IItem getOrderItem() {
        return this.item;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public IPrice getTotalFinalPrice() {

        // Calculate the final price (ItemFinalPrice + quantity)
        IPrice finalPriceSingle = getItemFinalPrice();

        BigDecimal nOfItems = BigDecimal.valueOf(quantity);
        BigDecimal total
                = finalPriceSingle.getValue().multiply(nOfItems);

        return new Price.Builder(total)
                .currency(item.getPrice().getCurrency())
                .build();
    }

    @Override
    public IPrice getItemFinalPrice() {
        IPrice itemPrice = item.getPrice();

        // Calculate the final price (price + taxesAmount)
        BigDecimal finalPrice
                = itemPrice.getValue().add(BigDecimal.valueOf(taxesAmount));

        return new Price.Builder(finalPrice)
                .currency(itemPrice.getCurrency())
                .build();
    }

    @Override
    public double getTaxesAmount() {
        return taxesAmount;
    }

    @Override
    public double getTotalTaxesAmount() {
        return quantity * taxesAmount;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ReceiptItem other = (ReceiptItem) o;
        return Objects.equals(item, other.item);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.quantity).append(" ")
                .append(this.item.toString())
                .append(" at ")
                .append(getTotalFinalPrice());
        return builder.toString();
    }

    public static class Builder {

        private final IItem item;
        private double taxesAmount = 0;
        private int quantity = 1;

        public Builder(IItem item) {
            this.item = item;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withTaxes(double taxesAmount) {
            this.taxesAmount = taxesAmount;
            return this;
        }

        public IReceiptItem build() {

            IReceiptItem receiptItem = new ReceiptItem(this);
            if (receiptItem.getQuantity() < 1) {
                // thread-safe
                throw new IllegalStateException("Quantity out of range");
            }

            if (receiptItem.getTaxesAmount() < 0) {
                // thread-safe
                throw new IllegalStateException("TaxesAmount out of range");
            }

            return receiptItem;
        }
    }

}
