package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.ICustomer;
import java.util.Objects;

/**
 * The ICustomer implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Customer implements ICustomer {

    private final String name;

    private Customer(Builder build) {
        this.name = build.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
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
        final Customer other = (Customer) o;
        return Objects.equals(name, other.name);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.name;
    }

    public static class Builder {

        private final String name;

        public Builder(String name) {
            this.name = name;
        }

        public ICustomer build() {
            ICustomer customer = new Customer(this);

            if (customer.getName().isEmpty()) {
                // thread-safe
                throw new IllegalStateException("Name not valid");
            }

            return customer;
        }
    }
}
