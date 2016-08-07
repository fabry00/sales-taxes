package com.id.salestaxes.service.resources.serilaizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.id.salestaxesapi.api.IReceiptItem;
import java.io.IOException;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class ReceiptItemSerializer {

    public void serializeItems(IReceiptItem item, JsonGenerator jg) throws IOException {

        jg.writeStartObject();
        jg.writeFieldName("n");
        jg.writeNumber(item.getQuantity());
        jg.writeObjectField("name",item.getOrderItem().getName());
        jg.writeFieldName("price");
        jg.writeNumber(item.getTotalFinalPrice().getValue());
        jg.writeEndObject();

    }
}
