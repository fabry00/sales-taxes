package com.id.salestaxesapi.api;

/**
 * Receipt Item Interface
 * @author Fabrizio Faustinoni
 */
public interface IReceiptItem {
    
    /**
     * @return The ordered item
     */
    public IOrderItem getOrderItem();
    
    /**
     * the TOTAL final price is calculated in this way:
     *  item price with taxes * quantity
     * 
     * @return the TOTAL final price of the order item
     */
    public IPrice getTotalFinalPrice();
    
    /**
     * @return the Item price with taxes
     */
    public IPrice getItemFinalPrice();
        
    /**
     * @return The amount of the taxes applied for the single item
     */
    public double getTaxesAmount();
    
    
    /**
     * @return The total amount of the taxes applied (item taxes * quantity)
     */
    public double getTotalTaxesAmount();
}
