package com.id.salestaxesapi.api;

import com.id.salestaxesapi.impl.Currency;

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
     * The value
     * @return 
     */
    public double getValue();

}
