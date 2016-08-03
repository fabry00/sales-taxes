package com.id.salestaxesapi.api;

import java.util.Date;
import java.util.Set;

/**
 * Order Interface
 * @author Fabrizio Faustinoni
 */
public interface IOrder {
    
    /**
     * @return the list of items of the order
     */
    public Set<IOrderItem> getGoods();
    
    /**
     * @return the id of the order
     */
    public int getId();
    
    /**
     * @return The customer that did the order
     */
    public ICustomer getCustomer();
    
    /**
     * @return the date of the order
     */
    public Date getOrderDate();
    
}
