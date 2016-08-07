package com.id.salestaxes.service.health;

import com.codahale.metrics.health.HealthCheck;
import com.id.salestaxes.service.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthCheckTask extends HealthCheck {

    private final Logger log = LoggerFactory.getLogger(HealthCheckTask.class);
    private final ServiceConfiguration configuration;

    public HealthCheckTask(ServiceConfiguration configuration) {
        this.configuration = configuration;

    }

    @Override
    protected Result check() {
        this.log.info("checking");
        //FIXME perform an health check
        return Result.healthy();

    }
}
