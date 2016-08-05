package com.id.salestaxesapi.api;

/**
 * The Currency interface
 * @author Fabrizio Faustinoni
 */
public interface ICurrency {
    public enum SupportedCurrency {USD,EUR};
    
    /**
     * @return the currency code
     */
    public SupportedCurrency getCode();

    /**
     * @return the currency Rate 
     */
    public double getRate();
}
