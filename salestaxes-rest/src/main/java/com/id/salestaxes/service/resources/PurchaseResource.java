package com.id.salestaxes.service.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.id.salestaxes.service.api.IServiceApi;
import com.id.salestaxes.service.resources.deserilaizer.OrderDeserializer;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import javax.ws.rs.Consumes;
import org.jose4j.json.internal.json_simple.JSONObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(IServiceApi.BASE + PurchaseResource.URL)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    public static final String URL = "salestaxes";
    public static final String GET_ORDERS_URL = "orders";
    public static final String PURCHASE_URL = "purchase";

    //SLF4J is provided with dropwizard
    private final Logger log = LoggerFactory.getLogger(PurchaseResource.class);

    private final ISalesTaxesAPI salesTaxesAPI;

    public PurchaseResource(ISalesTaxesAPI salesTaxesAPI) {

        this.salesTaxesAPI = salesTaxesAPI;
    }

    @GET
    @Timed
    @Path("/" + GET_ORDERS_URL)

    public JSONObject getOrders() {

        JSONObject jo = new JSONObject();
        jo.put("data", this.salesTaxesAPI.getOrders());
        return jo;

    }

    @POST
    @Timed
    @Path("/" + PURCHASE_URL)
    @JsonDeserialize(using = OrderDeserializer.class)
    public IReceipt purchase(IOrder order) {

        log.info("purchase: " + order.toString());
        IReceipt receipt = this.salesTaxesAPI.purchase(order);
        log.info("receipt: " + receipt.toString());
        return receipt;

    }
}
