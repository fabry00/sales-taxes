package com.id.salestaxes.service.resources;

import com.id.salestaxes.service.api.IServiceInfo;
import com.codahale.metrics.annotation.Timed;
import com.id.salestaxes.service.api.ServiceInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DefaultResource {

    private final String serviceName;
    private final String serviceDesc;

    public DefaultResource(String serviceName,String servceDesc) {
        
        this.serviceName = serviceName;
        this.serviceDesc = servceDesc;
    }

    @GET
    @Timed
    public IServiceInfo serviceInfo() {
        return new ServiceInfo.Builder()
                .withName(serviceName)
                .withDesc(serviceDesc)
                .build();
    }

    public static class Builder {

        private String name;
        private String description;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDesc(String desc) {
            this.description = desc;
            return this;
        }

        public DefaultResource build() {
            DefaultResource defaultRes = new DefaultResource(name,description);
            return defaultRes;
        }
    }
}
