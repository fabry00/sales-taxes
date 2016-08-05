package com.id.salestaxes.service.resources.params;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.obj.Item;
import com.id.salestaxesapi.obj.Order;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Deserialize a JSON order to Order
 * @author Fabrizio Faustinoni
 */
public class OrderDeserializer extends JsonDeserializer<IOrder> {

    @Override
    public IOrder deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        System.out.println("JSONE DESER");
        

        return null;
    }

}
