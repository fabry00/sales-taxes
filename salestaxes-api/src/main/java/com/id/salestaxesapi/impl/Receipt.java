package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The Receipt implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Receipt implements IReceipt {

    private final int id;
    private final Date date;
    private final ICustomer customer;
    private final Set<IReceiptItem> goods;
    private final double salesTaxes;
    private final IPrice total;

    private Receipt(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.customer = builder.customer;
        this.goods = Collections.unmodifiableSet(builder.goods);
        this.salesTaxes = builder.salesTaxes;
        this.total = builder.total;
    }

    @Override
    public double getSalesTaxes() {
        return salesTaxes;
    }

    @Override
    public IPrice getTotal() {
        return total;
    }

    @Override
    public Set<IReceiptItem> getGoods() {
        return goods;
    }

    @Override
    public String getCustomerName() {
        return customer.getName();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
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
        final Receipt other = (Receipt) o;
        return Objects.equals(id, other.id);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String lineSep = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();

        goods.stream().forEach((item) -> {
            builder.append(item.toString())
                    .append(lineSep);
        });
        builder.append(lineSep)
                .append("------").append(lineSep)
                .append("Sales Taxes: ")
                .append(salesTaxes)
                .append(lineSep)
                .append("Total:")
                .append(total)
                .append(lineSep);
        return builder.toString();
    }

    public static class Builder {

        private final int id;
        private final Set<IReceiptItem> goods = new HashSet<>();
        private Date date;
        private ICustomer customer;
        private double salesTaxes = 0;
        private IPrice total;

        public Builder(int id) {

            this.id = id;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder customer(ICustomer customer) {
            this.customer = customer;
            return this;
        }

        public Builder item(IReceiptItem item) {
            this.goods.add(item);
            return this;
        }

        public Builder salesTaxes(double salesTaxes) {
            this.salesTaxes = salesTaxes;
            return this;
        }

        public Builder toatal(IPrice total) {
            this.total = total;
            return this;
        }

        public IReceipt build() {
            IReceipt receipt = new Receipt(this);

            if (receipt.getGoods().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Empty goods");
            }

            if (receipt.getID() < 0) {
                // thread-safe
                throw new IllegalStateException("index out of range");
            }

            if (receipt.getTotal().getValue().doubleValue() < 0) {
                // thread-safe
                throw new IllegalStateException("Total out of range");
            }

            if (receipt.getCustomerName().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Customer not set");
            }
            return receipt;
        }

    }
}
