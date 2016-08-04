package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.internal.taxes.GeneralTaxesContrb;
import com.id.salestaxesapi.internal.taxes.ITaxesContributor;
import com.id.salestaxesapi.internal.taxes.ImportedTaxesContrib;
import java.util.HashSet;
import java.util.Set;

/**
 * Calculator of the Item taxes
 *
 * @author Fabrizio Faustiononi
 */
public class TaxesCalculator {

    private static final Set<ITaxesContributor> CONTRIBUTORS
            = new HashSet<ITaxesContributor>() {
                {
                    add(new GeneralTaxesContrb());
                    add(new ImportedTaxesContrib());
                }
            };

    /**
     * Calculate the percentage of taxes of a specific Item
     * For example:
     *  - For non imported book return 0
     *  - For imported book return 5
     *  - For imported item return 15
     *  - For item return 10
     * @param item The Item
     * @return the % of taxes to apply
     */
    public int getItemTaxes(IItem item) {
        int taxes = 0;
        // Sum all the taxes detected by each taxes contributors
        taxes = CONTRIBUTORS.stream().map((contributor)
                -> contributor.getItemTaxes(item)).reduce(taxes, Integer::sum);

        return taxes;
    }

    /**
     * Calculate the value of the taxes and round it 
     * For example:
     *  - if price is 1 and the taxesPercentage is 10 return: 0.1
     * @param price The item price
     * @param taxesPercentage The taxes percentage
     * @return the value of the taxes
     */
    public double calculateTaxesAmount(IPrice price, int taxesPercentage) {

        double taxes = price.getValue() * taxesPercentage / 100;

        return taxes;
    }
}
