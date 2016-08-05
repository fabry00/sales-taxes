package com.id.salestaxes.service.resources.deserilaizer;

import com.fasterxml.jackson.databind.JsonNode;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.obj.Customer;

/**
 *
 * @author Fabrizio Faustinoni
 */
class CustomerDeserializer {
    
    public ICustomer deserialize(JsonNode node) {
        String name = node.get("name").asText();
        Customer.Builder builder = new Customer.Builder(name);
        
        return builder.build();
    }
}
