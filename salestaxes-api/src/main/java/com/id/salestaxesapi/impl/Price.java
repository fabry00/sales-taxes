package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.api.IPrice;
import java.math.BigDecimal;

/**
 * The price implementation
 *
 * Representing money as a double or float will probably look good at first as
 * the software rounds off the tiny errors, but as you perform more additions,
 * subtractions, multiplications and divisions on inexact numbers, you'll lose
 * more and more precision as the errors add up. This makes floats and doubles
 * inadequate for dealing with money, where perfect accuracy for multiples of
 * base 10 powers is required.
 *
 * we could use org.javamoney or Joda Money
 *
 * @author Fabrizio Faustinoni
 */
public class Price implements IPrice {

    private final ICurrency currency;

    private final BigDecimal value;

    public Price(Builder builder) {
        this.currency = builder.currency;
        this.value = builder.value;
    }

    @Override
    public ICurrency getCurrency() {
        return this.currency;
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.value.doubleValue())
                .append(" - ")
                .append(this.currency.getCode())
                .append("[")
                .append(this.currency.getRate())
                .append("]");

        return builder.toString();
    }

    public static class Builder {

        private final BigDecimal value;
        private ICurrency currency;

        public Builder(BigDecimal value) {
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

            if (price.getValue().compareTo(BigDecimal.ZERO) < 0) { // = prise allowed
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
