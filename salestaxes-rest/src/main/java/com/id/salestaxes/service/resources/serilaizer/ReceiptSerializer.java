package com.id.salestaxes.service.resources.serilaizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.id.salestaxes.service.resources.helper.ResourceHelper;
import com.id.salestaxesapi.api.IReceipt;
import java.io.IOException;
import java.text.ParseException;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class ReceiptSerializer extends JsonSerializer<IReceipt> {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(ReceiptSerializer.class);

    @Override
    public void serialize(IReceipt t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {

        ResourceHelper helper = new ResourceHelper();

        jg.writeStartObject();
        jg.writeObjectFieldStart("recepit");
        try {
            jg.writeObjectField("Date", helper.dateToString(t.getDate()));
        } catch (ParseException ex) {
            throw new IOException("Error converting date", ex);
        }

        jg.writeObjectField("Customer", t.getCustomerName());

        serializeItems(t, jg);

        jg.writeFieldName("salesTaxes");
        jg.writeNumber(t.getSalesTaxes());
        jg.writeFieldName("total");
        jg.writeNumber(t.getTotal().getValue());
        jg.writeEndObject();
    }

    private void serializeItems(IReceipt t, JsonGenerator jg) throws IOException {
        jg.writeArrayFieldStart("items");
       // jg.writeStartArray();
        ReceiptItemSerializer serializer = new ReceiptItemSerializer();
        t.getGoods().stream().forEach((item) -> {
            try {
                serializer.serializeItems(item, jg);
            } catch (IOException ex) {
                log.error("Error serialize IreciptItem", ex);
            }
        });
        jg.writeEndArray();
        jg.writeEndObject();
    }

}
