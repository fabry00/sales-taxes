package com.id.salestaxesapi.impl.taxes;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.impl.taxes.contributors.GeneralTaxesContrb;
import com.id.salestaxesapi.impl.taxes.contributors.ITaxesContributor;
import com.id.salestaxesapi.impl.taxes.contributors.ImportedTaxesContrib;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * Calculator of the Item taxes
 *
 * @author Fabrizio Faustiononi
 */
public class TaxesCalculator implements ITaxesCalculator {

    private static final Set<ITaxesContributor> CONTRIBUTORS
            = new HashSet<ITaxesContributor>() {
        {
            add(new GeneralTaxesContrb());
            add(new ImportedTaxesContrib());
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemTaxes(IItem item) {
        int taxes = 0;
        // Sum all the taxes detected by each taxes contributors
        taxes = CONTRIBUTORS.stream().map((contributor)
                -> contributor.getItemTaxes(item)).reduce(taxes, Integer::sum);

        return taxes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double calculateTaxesAmount(IPrice price, int taxesPercentage) {

        // The division by 100 is implemented using BigDecimal.scaleByPowerOfTen(-2)
        // It is faster then divide by 100
        BigDecimal percent = new BigDecimal(taxesPercentage);
        BigDecimal taxes = price.getValue()
                .multiply(percent)
                .scaleByPowerOfTen(-2);

        return roundUpToNearestV2(taxes, BigDecimal.valueOf(0.05),
                RoundingMode.UP);
    }

    /**
     * This improve the roundUpToNearestV1 because perform the round operation
     * without converting to double. The conversion to double is done only where
     * the number has already been rounded (then we do not have loss of
     * precision)
     *
     * @param value
     * @param increment
     * @param roundingMode
     * @return
     */
    private static double roundUpToNearestV2(BigDecimal value,
            BigDecimal increment, RoundingMode roundingMode) {

        if (increment.signum() == 0) {
            // 0 increment does not make much sense, but prevent division by 0
            return value.doubleValue();
        } else {
            BigDecimal divided = value.divide(increment, 0, roundingMode);
            BigDecimal result = divided.multiply(increment);
            return result.doubleValue();
        }
    }

    /**
     * Round up the value to the nearest decimal
     * 
     * Fromula: 
     * BigDecimal result = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20)
     * result.setScale(2, RoundingMode.HALF_UP) 
     * 
     * example: 
     * factor = 20 ( 1/ 0.05 )
     * result = 7.125 * (factor) == 142.5 result = Math.ceil( 142.5) == 142
     * result = 142 / factor = 7.1 
     * result = (7.1).setScale(2,RoundingMode.HALF_UP) == 7.15
     *
     * @param value
     * @param roundUpFactor
     * @return
     */
    private double roundUpToNearestV1(BigDecimal value, double roundUpFactor) {
        BigDecimal result = new BigDecimal(Math.ceil(value.doubleValue()
                * roundUpFactor) / roundUpFactor);

        result.setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();

    }
}
