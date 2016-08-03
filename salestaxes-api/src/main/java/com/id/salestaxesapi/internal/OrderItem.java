package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrderItem;

/**
 * The order Item implementation
 *
 * @author Fabrizio Faustinoni
 */
public class OrderItem implements IOrderItem {

    /**
     * @inheritDoc
     */
    @Override
    public IItem getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
