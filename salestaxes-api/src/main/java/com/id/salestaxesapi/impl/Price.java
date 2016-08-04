package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.api.IPrice;

/**
 * The price implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Price implements IPrice {

    private final ICurrency currency;
    private final double value;

    public Price(Builder builder) {
        this.currency = builder.currency;
        this.value = builder.value;
    }

    @Override
    public ICurrency getCurrency() {
        return this.currency;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public static class Builder {

        private final double value;
        private ICurrency currency;

        public Builder(double value) {
            this.value = value;
            this.currency = new Currency.Builder(ICurrency.SupportedCurrency.EUR, 1)
                    .build();
        }

        public Builder currency(ICurrency currency) {
            this.currency = currency;
            return this;
        }

        public IPrice build() {
            IPrice price = new Price(this);

            if (price.getValue() < 0) {
                // thread-safe
                throw new IllegalStateException("Value out of range");
            }

            if (price.getCurrency().getRate() < 0) {
                // thread-safe
                throw new IllegalStateException("Currency rate out of range");
            }

            return price;
        }
    }
}
