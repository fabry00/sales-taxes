package com.id.salestaxes.service.resources;

import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.id.salestaxes.service.IServiceConfiguration;
import com.id.salestaxes.service.api.IServiceApi;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import org.jose4j.json.internal.json_simple.JSONObject;
import javax.ws.rs.GET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path(IServiceApi.BASE + PurchaseResource.URL)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseResource {

    public static final String URL = "salestaxes";
    public static final String GET_ORDERS_URL = "orders";

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
    // /taks-list
    // /taks-list?contains=string
    @Path("/" + GET_ORDERS_URL)
    public JSONObject getNeighborhoods() {

        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();
     
        jo.put("data", data);
        log.info("Return " + jo);
        return jo;

    }
}
