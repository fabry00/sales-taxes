package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.obj.taxes.TaxesCalculator;

/**
 * SalesTaxes Factory
 * @author Fabrizio Faustinoni
 */
public class SalesTaxesFactory {
    
    public ISalesTaxesAPI create(){
        // Create the calculator to inject
        ITaxesCalculator calculator = new TaxesCalculator();
        return new SalesTaxesAPI(calculator);
    }
    
}
