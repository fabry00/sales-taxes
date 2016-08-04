package com.id.salestaxesapi;

import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.impl.Currency;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.impl.Customer;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.impl.Category;
import com.id.salestaxesapi.impl.Item;
import com.id.salestaxesapi.impl.Order;
import com.id.salestaxesapi.impl.OrderItem;
import com.id.salestaxesapi.impl.Price;

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
        return new Customer.Builder(CUSTOMER_NAME).build();
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

    public IItem getItemExported(String name, Category category) {
        return new Item.Builder(name, category, getBasePrice()).imported().build();
    }

    public IPrice getBasePrice() {
        ICurrency currency
                = new Currency.Builder(ICurrency.SupportedCurrency.EUR, 1).build();
        return new Price.Builder(15.5).currency(currency).build();
    }
}
