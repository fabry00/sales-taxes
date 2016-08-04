package com.id.salestaxesapi.api;

import java.util.Date;
import java.util.Map;

/**
 * Order Interface
 * @author Fabrizio Faustinoni
 */
public interface IOrder {
    
    /**
     * @return the Map<Item,quantity> of items of the order
     */
    public Map<IItem, Integer> getGoods();
    
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
