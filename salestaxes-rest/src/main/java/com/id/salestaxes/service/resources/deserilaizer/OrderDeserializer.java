package com.id.salestaxes.service.resources.deserilaizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.obj.Order;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deserialize a JSON order to Order
 *
 * @author Fabrizio Faustinoni
 */
public class OrderDeserializer extends JsonDeserializer<IOrder> {

    private final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    private final Logger log = LoggerFactory.getLogger(OrderDeserializer.class);

    @Override
    public IOrder deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        log.debug("Deserializing Order");
        int orderId = node.get("id").asInt();
        ICustomer customer = getCustomer(node);
        Order.Builder builder = new Order.Builder(orderId, customer);
        try {
            builder.date(getDate(node));
        } catch (ParseException ex) {
            throw new IOException("Error retrieving date", ex);
        }
        getGoods(builder, node);

        return builder.build();
    }

    private ICustomer getCustomer(JsonNode node) {
        CustomerDeserializer deserializer = new CustomerDeserializer();
        return deserializer.deserialize(node.get("customer"));
    }

    private Date getDate(JsonNode node) throws ParseException {
        String date = node.get("date").asText();
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.parse(date);
    }

    private void getGoods(Order.Builder builder, JsonNode node) {

        ItemDeserializer deserializer = new ItemDeserializer();
        Set<IItem> goods = deserializer.deserialize(node.get("goods"));

        goods.stream().forEach((item) -> {
            builder.addItem(item);
        });
    }

}
