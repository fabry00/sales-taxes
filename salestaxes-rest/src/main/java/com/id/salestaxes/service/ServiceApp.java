package com.id.salestaxes.service;

import com.id.salestaxes.service.resources.PurchaseResource;
import com.id.salestaxes.service.resources.DefaultResource;
import com.id.salestaxes.service.health.HealthCheckTask;
import com.id.salestaxes.service.resources.deserilaizer.PurchaseModule;
import com.id.salestaxesapi.api.impl.SalesTaxesFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.net.URISyntaxException;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class ServiceApp extends Application<ServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ServiceApp().run(args);
    }

    @Override
    public String getName() {
        return ServiceConfiguration.SERVICE_DESC;
    }

    @Override
    public void initialize(final Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new PurchaseModule());
    }

    @Override
    public void run(final ServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException {

        enableCors(environment);

        environment.healthChecks().register(ServiceConfiguration.SERVICE_NAME,
                getHealthCheck(configuration, environment));
        environment.jersey().register(getDefault());
        environment.jersey().register(getPurchaseResource());

    }

    private DefaultResource getDefault() {
        DefaultResource defaultRes = new DefaultResource.Builder()
                .withName(ServiceConfiguration.SERVICE_NAME)
                .withDesc(ServiceConfiguration.SERVICE_DESC)
                .build();

        return defaultRes;
    }

    private Object getPurchaseResource() {
        SalesTaxesFactory factory = new SalesTaxesFactory();
        PurchaseResource resource = new PurchaseResource(factory.create());
        return resource;
    }

    private HealthCheckTask getHealthCheck(final ServiceConfiguration configuration,
            final Environment environment) throws URISyntaxException {

        HealthCheckTask checker = new HealthCheckTask(configuration);
        return checker;
    }

    private void enableCors(Environment environment) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors
                = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
