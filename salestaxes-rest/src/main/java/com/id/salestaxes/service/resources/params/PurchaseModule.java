package com.id.salestaxes.service.resources.params;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.id.salestaxesapi.api.IOrder;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class PurchaseModule extends SimpleModule {
     public PurchaseModule() {
        addDeserializer(IOrder.class, new OrderDeserializer());     
    }
}
