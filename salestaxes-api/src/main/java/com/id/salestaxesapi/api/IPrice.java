package com.id.salestaxesapi.api;

/**
 * The interface for the price of the item
 *
 * @author Fabrizio Faustinoni
 */
public interface IPrice {

    /**
     * @return the currency of the price
     */
    public Currency getCurrency();

    public double getValue();

}
