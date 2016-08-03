package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;
import java.util.Objects;

/**
 * The IItem implementation Immutable object
 *
 * @author Fabrizio Faustinoni
 */
public class Item implements IItem {

    /**
     * The name of the item
     * It is unique.
     */
    private final String name;

    /**
     * Constructor
     *
     * @param name
     */
    private Item(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class Builder {

        private final String name;

        public Builder(String name) {
            this.name = name;
        }

        public IItem build() {
            IItem item = new Item(this.name);
            return item;
        }
    }

}
