package com.id.salestaxesapi.impl;

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
    private final boolean imported;

    /**
     * Constructor
     *
     * @param name
     */
    private Item(Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
        this.price = builder.price;
        this.imported = builder.imported;
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
    public IPrice getPrice() {
        return this.price;
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
    public boolean isImported() {
        return this.imported;
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

    public static class Builder {

        private final String name;
        private final Category category;
        private final IPrice price;
        private boolean imported;

        public Builder(String name, Category category, IPrice price) {
            this.name = name;
            this.category = category;
            this.imported = false;
            this.price = price;
        }

        public Builder imported() {
            this.imported = true;
            return this;
        }

        public IItem build() {
            IItem item = new Item(this);
            
            if (item.getName().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Name not valid");
            }
            return item;
        }
    }

}
