package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;

/**
 * The Receipt implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Receipt implements IReceipt {

    private final IOrder order;

    private Receipt(IOrder order) {
        this.order = order;
    }
}
