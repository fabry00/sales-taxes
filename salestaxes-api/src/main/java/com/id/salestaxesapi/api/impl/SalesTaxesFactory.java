package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.obj.persistent.FilePersistentEngine;
import com.id.salestaxesapi.obj.persistent.IPersistentEngine;
import com.id.salestaxesapi.obj.taxes.TaxesCalculator;
import java.io.IOException;

/**
 * SalesTaxes Factory
 *
 * @author Fabrizio Faustinoni
 */
public class SalesTaxesFactory {

    public ISalesTaxesAPI create() throws IOException {
        // Create the calculator to inject
        ITaxesCalculator calculator = new TaxesCalculator();
        IPersistentEngine engine = new FilePersistentEngine();
        return new SalesTaxesAPI(calculator, engine);
    }

}
