package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IOrderItem;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The IOrder implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Order implements IOrder {

    private final int id;
    private final Set<IOrderItem> goods;
    private final ICustomer customer;
    private final Date date;

    /**
     * Order construcotr
     *
     * @param id The oorder id
     * @param goods The goods
     * @param date The date
     * @param customer The customer
     */
    private Order(Builder builder) {
        this.id = builder.id;
        this.goods = Collections.unmodifiableSet(builder.goods);
        this.customer = builder.customer;
        this.date = builder.date;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Set<IOrderItem> getGoods() {
        return this.goods;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ICustomer getCustomer() {
        return this.customer;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Date getOrderDate() {
        return this.date;
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
        final Order other = (Order) o;
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

        builder.append("OrderID: ").append(id).append(lineSep);

        goods.stream().forEach((item) -> {
            builder.append(item.toString()).append(lineSep);
        });

        return builder.toString();
    }

    public static class Builder {

        private final int id;
        private final ICustomer customer;
        private final Set<IOrderItem> goods = new HashSet<>();
        private Date date = new Date();

        public Builder(int id, ICustomer customer) {
            this.id = id;
            this.customer = customer;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder addItem(IOrderItem item) {
            this.goods.add(item);
            return this;
        }

        public IOrder build() {
            IOrder order = new Order(this);

            if (order.getId() < 0) {
                // thread-safe
                throw new IllegalStateException("ID out of range");
            }

            if (order.getGoods().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Goods length out of range");
            }
            return order;
        }
    }
}
