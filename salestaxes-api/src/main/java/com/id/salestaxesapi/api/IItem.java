package com.id.salestaxesapi.api;

import com.id.salestaxesapi.impl.Category;

/**
 * Item interface
 *
 * @author Fabrizio Faustinoni
 */
public interface IItem {

    /**
     * @return the Item name
     */
    public String getName();

    /**
     * @return the item category
     */
    public Category getCategory();
    
    /**
     * @return the base price of the Item
     */
    public IPrice getPrice();

    /**
     * @return true if the item is without taxes
     */
    public boolean isTaxesFree();
    
    /**
     * @return true if the item has been imported
     */
    public boolean isImported();
    
    
}
