package com.id.salestaxesapi.api;

import java.util.Set;

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
     * @throws com.id.salestaxesapi.api.PurchaseException
     */
    public IReceipt purchase(IOrder order)  throws PurchaseException;

    /**
     * Return all the orders stored.
     * WARNING, this functionality has been added only
     * to complete the design, and, of course, could
     * be improved a lot
     * 
     * @return the receipts
     */
    public Set<IReceipt> getOrders();
}
