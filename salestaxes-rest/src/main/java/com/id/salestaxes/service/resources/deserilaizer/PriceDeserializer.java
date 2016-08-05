package com.id.salestaxes.service.resources.deserilaizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.api.ICurrency.SupportedCurrency;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.obj.Currency;
import com.id.salestaxesapi.obj.Price;
import java.math.BigDecimal;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class PriceDeserializer {

    public IPrice deserialize(JsonNode node) {
        Long value = node.get("value").asLong();

        Price.Builder builder = new Price.Builder(BigDecimal.valueOf(value));
        getCurrency(builder, node);
        return builder.build();
    }

    private void getCurrency(Price.Builder builder, JsonNode node) {

        if (node.get("currency") != null) {
            String currency = node.get("currency")
                    .asText(SupportedCurrency.EUR.name());
            double rate = node.get("rate")
                    .asDouble(1);

            ICurrency curr = new Currency.Builder(SupportedCurrency.valueOf(currency), rate)
                    .build();

            builder.currency(curr);
        }
    }
}
