package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.Currency;
import com.id.salestaxesapi.api.IPrice;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class Price implements IPrice {

    private final Currency currency;
    private final double value;

    public Price(double value, Currency currency) {
        this.currency = currency;
        this.value = value;
    }

    @Override
    public Currency getCurrency() {
        return this.currency;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
