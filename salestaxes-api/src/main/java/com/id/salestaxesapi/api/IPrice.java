package com.id.salestaxesapi.api;

import java.math.BigDecimal;

/**
 * The interface for the price of the item
 *
 * @author Fabrizio Faustinoni
 */
public interface IPrice {

    /**
     * @return the currency of the price
     */
    public ICurrency getCurrency();

    /**
     * @return The value
     */
    public BigDecimal getValue();

}
