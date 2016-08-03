package com.id.salestaxesapi;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.internal.Item;
import com.id.salestaxesapi.internal.OrderItem;

/**
 * Helper test
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {

    public static final String ITEM_NAME = "My Item";

    public IItem getBaseItem() {
        return new Item.Builder(ITEM_NAME).build();
    }

    public IOrderItem getBaseOrderItem() {
        return new OrderItem.Builder(getBaseItem()).build();
    }
}
