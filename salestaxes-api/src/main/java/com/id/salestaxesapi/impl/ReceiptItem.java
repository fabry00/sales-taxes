package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.api.IReceiptItem;

/**
 * Receipt Item implementation
 * @author Fabrizio Faustinoni
 */
public class ReceiptItem implements IReceiptItem{
    
    public ReceiptItem() {
    }

    @Override
    public IOrderItem getOrderItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPrice getTotalFinalPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPrice getItemFinalPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTaxesAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalTaxesAmount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
