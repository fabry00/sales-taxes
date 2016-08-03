package com.id.salestaxesapi.api;

import com.id.salestaxesapi.internal.Category;

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
     * @return true if the item is without taxes
     */
    public boolean isTaxesFree();
}
