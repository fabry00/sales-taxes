package com.id.salestaxesapi.api;

import com.id.salestaxesapi.api.ICustomer;
import java.util.Objects;

/**
 * The ICustomer implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Customer implements ICustomer {

    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
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
}
