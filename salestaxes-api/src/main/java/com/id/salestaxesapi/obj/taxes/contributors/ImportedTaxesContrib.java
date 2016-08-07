package com.id.salestaxesapi.obj.taxes.contributors;

import com.id.salestaxesapi.api.IItem;

/**
 * The Imported Item taxes contributor
 * @author Fabrizio Faustinoni
 */
public class ImportedTaxesContrib implements ITaxesContributor {

    // This should be retrieved from a configuration file
    public static final int TAXES = 5;

    @Override
     public int getItemTaxes(IItem item) {
        if (item.isImported()) {
            return TAXES;
        }

        return 0;
    }

}
