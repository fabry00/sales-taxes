package com.id.salestaxesapi.api;

/**
 * TaxesCalculator algorithm
 *
 * @author Fabrizio Faustinoni
 */
public interface ITaxesCalculator {

    /**
     * Calculate the percentage of taxes of a specific Item For example: - For
     * non imported book return 0 - For imported book return 5 - For imported
     * item return 15 - For item return 10
     *
     * @param item The Item
     * @return the % of taxes to apply
     */
    public int getItemTaxes(IItem item);

    /**
     * Calculate the value of the taxes and round it For example: - if price is
     * 1 and the taxesPercentage is 10 return: 0.1
     *
     * @param price The item price
     * @param taxesPercentage The taxes percentage
     * @return the value of the taxes
     */
    public double calculateTaxesAmount(IPrice price, int taxesPercentage);
}
