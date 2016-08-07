package com.id.salestaxesapi.obj.taxes.contributors;

import com.id.salestaxesapi.api.IItem;

/**
 * Taxes contributor Interface
 * @author Fabrizio Faustinoni
 */
public interface ITaxesContributor {
    
    /**
     * @param item The item
     * @return the taxed to applay
     */
    public int getItemTaxes(IItem item);
}
