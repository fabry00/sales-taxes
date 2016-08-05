package com.id.salestaxes.service.resources;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.id.salestaxes.service.resources.deserilaizer.OrderDeserializer;
import com.id.salestaxes.service.resources.serilaizer.ReceiptSerializer;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class PurchaseModule extends SimpleModule {
     public PurchaseModule() {
        addDeserializer(IOrder.class, new OrderDeserializer());     
        addSerializer(IReceipt.class, new ReceiptSerializer());
    }
}
