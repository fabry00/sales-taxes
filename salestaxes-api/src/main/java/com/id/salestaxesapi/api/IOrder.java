package com.id.salestaxesapi.api;

import java.util.Set;

/**
 * Order Interface
 * @author Fabrizio Faustinoni
 */
public interface IOrder {
    
    /**
     * @return the list of items of the order
     */
    Set<IOrderItem> getGoods();
}
