package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IOrderItem;
import java.util.Collections;
import java.util.Set;

/**
 * The IOrder implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Order implements IOrder {

    private final Set<IOrderItem> goods;

    private Order(Set<IOrderItem> goods) {
        this.goods = Collections.unmodifiableSet(goods);
    }

    @Override
    public Set<IOrderItem> getGoods() {
        return this.goods;
    }

}
