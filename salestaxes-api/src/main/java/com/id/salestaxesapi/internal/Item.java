package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IPrice;
import java.util.Objects;

/**
 * The IItem implementation Immutable object
 *
 * @author Fabrizio Faustinoni
 */
public class Item implements IItem {

    /**
     * The name of the item It is unique.
     */
    private final String name;
    private final Category category;
    private final IPrice price;

    /**
     * Constructor
     *
     * @param name
     */
    private Item(String name, Category category, IPrice price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTaxesFree() {
        return this.category.isTaxesFree();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
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
        final Item other = (Item) o;
        return Objects.equals(name, other.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * With the current implementation it is not necessary to have a builder But
     * if in the feauture we will add some optional fields, we are ready
     */
    public static class Builder {

        private final String name;
        private final Category category;
        private final IPrice price;

        public Builder(String name, Category category, IPrice price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public IItem build() {
            IItem item = new Item(this.name, this.category, this.price);
            return item;
        }
    }

}
