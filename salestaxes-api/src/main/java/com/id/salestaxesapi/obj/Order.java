package com.id.salestaxesapi.obj;

import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.tool.Utils;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The IOrder implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Order implements IOrder {

    private final int id;
    private final Map<IItem, Integer> goods;
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
        this.goods = Collections.unmodifiableMap(builder.goods);
        this.customer = builder.customer;
        this.date = builder.date;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Map<IItem, Integer> getGoods() {
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
        
        Utils utils = new Utils();
        builder.append("OrderID: ").append(id).append(lineSep);
        builder.append("Date: ").append(utils.dateToString(date)).append(lineSep);
        builder.append("Customer: ").append(customer.getName()).append(lineSep);

        goods.entrySet().stream().forEach((entry) -> {
            builder.append(entry.getValue()).append(" ")
                    .append(entry.getKey().toString())
                    .append(" at ")
                    .append(entry.getKey().getPrice().getValue())                    
                    .append(lineSep);
        });

        return builder.toString();
    }

    public static class Builder {

        private final int id;
        private final ICustomer customer;
        private final Map<IItem, Integer> goods = new HashMap<>();
        private Date date = new Date();

        public Builder(int id, ICustomer customer) {
            this.id = id;
            this.customer = customer;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder addItem(IItem item, int quantity) {

            if (quantity < 1) {
                throw new IllegalStateException("quantity out of range");
            }

            this.goods.put(item, quantity);
            return this;
        }

        public Builder addItem(IItem item) {
            this.goods.put(item, 1);
            return this;
        }

        public IOrder build() {
            IOrder order = new Order(this);

            if (order.getId() < 0) {
                // thread-safe
                throw new IllegalStateException("ID out of range "+order.getId());
            }

            if (order.getGoods().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Goods length out of range");
            }
            return order;
        }
    }
}
