package com.id.salestaxesapi.api;

/**
 * Public API
 *
 * @author Fabrizio Faustinoni
 */
public interface ISalesTaxesAPI {

    /**
     * Purchase an order
     *
     * @param order The order
     * @return the Receipt of the purchase
     */
    public IReceipt purchase(IOrder order);

    /**
     * Return all the orders stored.
     * WARNING: this is a very very simple and dummy
     * functionality added just to complete the design.
     * @return all the orders in string format
     */
    public String getOrders();
}
