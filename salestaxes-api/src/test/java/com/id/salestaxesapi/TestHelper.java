package com.id.salestaxesapi;

import com.id.salestaxesapi.api.Currency;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.api.Customer;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.internal.Category;
import com.id.salestaxesapi.internal.Item;
import com.id.salestaxesapi.internal.Order;
import com.id.salestaxesapi.internal.OrderItem;
import com.id.salestaxesapi.internal.Price;

/**
 * Helper test
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {

    public static final String ITEM_NAME = "My Item";
    public static final String CUSTOMER_NAME = "User1";

    public IItem getBaseItem() {
        return new Item.Builder(ITEM_NAME, Category.OTHER, getBasePrice()).build();
    }

    public IOrderItem getBaseOrderItem() {
        return new OrderItem.Builder(getBaseItem()).build();
    }

    public ICustomer getBaseCustomer() {
        return new Customer(CUSTOMER_NAME);
    }

    public IItem getItem(String itemName) {
        return new Item.Builder(itemName, Category.OTHER, getBasePrice()).build();
    }

    public IOrderItem getOrderItem(IItem item) {
        return new OrderItem.Builder(item).build();
    }

    public IOrder getBaseOrder() {
        Order.Builder builder = new Order.Builder(0, getBaseCustomer());
        builder.addItem(getBaseOrderItem());
        builder.addItem(getOrderItem(getItem("ItemX")));
        return builder.build();
    }

    public IItem getItem(String name, Category category) {
        return new Item.Builder(name, category, getBasePrice()).build();
    }

    public IPrice getBasePrice() {
        return new Price(15.5, Currency.EURO);
    }
}
