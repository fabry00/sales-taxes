package com.id.salestaxes.service.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.id.salestaxes.service.IServiceConfiguration;
import com.id.salestaxes.service.api.IServiceApi;
import com.id.salestaxes.service.resources.params.OrderDeserializer;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.obj.Order;
import org.jose4j.json.internal.json_simple.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(IServiceApi.BASE + PurchaseResource.URL)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    public static final String URL = "salestaxes";
    public static final String GET_ORDERS_URL = "orders";
    public static final String PURCHASE_URL = "purchase";

    //SLF4J is provided with dropwizard
    private final Logger log = LoggerFactory.getLogger(PurchaseResource.class);

    private final ISalesTaxesAPI salesTaxesAPI;

    IServiceConfiguration configuration;

    public PurchaseResource(IServiceConfiguration configuration,
            ISalesTaxesAPI salesTaxesAPI) {

        this.configuration = configuration;
        this.salesTaxesAPI = salesTaxesAPI;
    }

    @GET
    @Timed
    @Path("/" + GET_ORDERS_URL)
    
    public JSONObject getOrders() {

        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();
     
        jo.put("data", data);
        log.info("Return " + jo);
        return jo;

    }
    
    
    @POST
    @Timed
    @Path("/" + PURCHASE_URL)
    @JsonDeserialize(using=OrderDeserializer.class)
    public JSONObject purchase(IOrder order) {

        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();
     
        jo.put("data", data);
        log.info("Return " + jo);
        return jo;

    }
}
