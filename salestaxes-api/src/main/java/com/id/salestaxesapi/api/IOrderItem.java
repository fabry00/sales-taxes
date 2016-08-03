package com.id.salestaxesapi.api;

/**
 * The Order Item interface
 * @author Fabrizio Faustinoni
 */
public interface IOrderItem {
    
    /**
     * @return the item
     */
    public IItem getItem();
    
    /**
     * @return the number of Items
     */
    public int getQuantity();
}
