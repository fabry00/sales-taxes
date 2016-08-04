package com.id.salestaxesapi.internal.taxes;

import com.id.salestaxesapi.api.IItem;

;

/**
 * Manage the general taxes
 *
 * @author Fabrizio Fausitnon
 */
public class GeneralTaxesContrb implements ITaxesContributor {

    // This should be retrieved from a configuration file
    public static final int TAXES = 10;

    @Override
    public int getItemTaxes(IItem item) {
        if (!item.isTaxesFree()) {
            return TAXES;
        }

        return 0;
    }

}
