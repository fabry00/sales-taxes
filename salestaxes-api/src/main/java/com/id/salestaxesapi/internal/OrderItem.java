package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrderItem;
import java.util.Objects;

/**
 * The order Item implementation Immutable
 *
 * @author Fabrizio Faustinoni
 */
public class OrderItem implements IOrderItem {

    private final IItem item;
    private final int quantity;

    /**
     * OrderItem constructore
     *
     * @param item The Item
     * @param quantity The quantity
     */
    private OrderItem(IItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IItem getItem() {
        return this.item;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getQuantity() {
        return this.quantity;
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
        final OrderItem other = (OrderItem) o;
        return Objects.equals(item, other.item)
                && Objects.equals(quantity, other.quantity);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(item, quantity);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.item.toString() + " [" + this.quantity + "]";
    }

    public static class Builder {

        private final IItem item;
        private int quantity = 1;

        public Builder(IItem item) {
            this.item = item;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public IOrderItem build() {
            IOrderItem orederItem = new OrderItem(this.item, this.quantity);
            return orederItem;
        }
    }

}
