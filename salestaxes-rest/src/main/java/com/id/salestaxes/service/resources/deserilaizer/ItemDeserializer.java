package com.id.salestaxes.service.resources.deserilaizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.obj.Category;
import com.id.salestaxesapi.obj.Item;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class ItemDeserializer {

    private final Logger log = LoggerFactory.getLogger(ItemDeserializer.class);

    public Set<IItem> deserialize(JsonNode node) {
        Set<IItem> items = new HashSet<>();

        for (final JsonNode itemNode : node) {
            items.add(getItem(itemNode));
        }

        return items;
    }

    private IItem getItem(JsonNode itemNode) {
        String name = itemNode.get("name").asText();
        String categoryStr = itemNode.get("category").asText();
        boolean imported = itemNode.get("imported").asBoolean(false);

        Category category = Category.OTHER;
        try {
            category = Category.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException ex) {
            log.error("Category unknown", ex);
        }

        // Price
        PriceDeserializer priceDe = new PriceDeserializer();
        IPrice price = priceDe.deserialize(itemNode.get("price"));

        Item.Builder builder = new Item.Builder(name, category, price);
        if (imported) {
            builder.imported();
        }
        return builder.build();
    }
}
