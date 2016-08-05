package com.id.salestaxesapi.obj;

import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.api.ICurrency.SupportedCurrency;

/**
 * @author Fabrizio Faustinoni
 */
public class Currency implements ICurrency {

    private final SupportedCurrency code;
    private final double rate;

    private Currency(Builder builder) {
        this.code = builder.code;
        this.rate = builder.rate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SupportedCurrency getCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRate() {
        return this.rate;
    }

    public static class Builder {

        private final SupportedCurrency code;
        private final double rate;

        public Builder(SupportedCurrency code, double rate) {
            this.code = code;
            this.rate = rate;
        }

        public ICurrency build() {
            ICurrency currency = new Currency(this);

            if (currency.getRate() < 0) {
                // thread-safe
                throw new IllegalStateException("Rate out of range");
            }
            return currency;
        }
    }
}
