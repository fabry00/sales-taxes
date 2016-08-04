package com.id.salestaxesapi.api;

import java.util.Date;
import java.util.Set;

/**
 * Receipt Interface
 *
 * @author Fabrizio Faustinoni
 */
public interface IReceipt {

    /**
     * Return the total sales taxed
     *
     * @return the total sales taxed
     */
    public double getSalesTaxes();

    /**
     * @return The list of the itesm purchased
     */
    public Set<IReceiptItem> getGoods();

    /**
     * @return The customer name
     */
    public String getCustomerName();

    /**
     * @return the order id
     */
    public int getID();

    /**
     * @return The order date
     */
    public Date getDate();
}
