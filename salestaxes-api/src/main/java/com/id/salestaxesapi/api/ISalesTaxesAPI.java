package com.id.salestaxesapi.api;

/**
 * Public API 
 * @author Fabrizio Faustinoni
 */
public interface ISalesTaxesAPI {
    
    /**
     * Purchase an order
     * @param order The order
     * @return the Receipt of the purchase
     */
    public IReceipt purchase(IOrder order);
}
