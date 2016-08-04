package com.id.salestaxesapi;

import com.id.salestaxesapi.api.ICurrency;
import com.id.salestaxesapi.impl.Currency;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.impl.Customer;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.api.IReceiptItem;
import com.id.salestaxesapi.impl.Category;
import com.id.salestaxesapi.impl.Item;
import com.id.salestaxesapi.impl.Order;
import com.id.salestaxesapi.impl.Price;
import com.id.salestaxesapi.impl.ReceiptItem;
import com.id.salestaxesapi.impl.taxes.TaxesCalculator;
import java.math.BigDecimal;

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

    public ICustomer getBaseCustomer() {
        return new Customer.Builder(CUSTOMER_NAME).build();
    }

    public IItem getItem(String itemName) {
        return new Item.Builder(itemName, Category.OTHER, getBasePrice()).build();
    }

    public IOrder getBaseOrder() {
        Order.Builder builder = new Order.Builder(0, getBaseCustomer());
        builder.addItem(getBaseItem());
        builder.addItem(getItem("ItemX"), 3);
        return builder.build();
    }

    public IItem getItem(String name, Category category) {
        return new Item.Builder(name, category, getBasePrice()).build();
    }

    public IItem getItem(String name, Category category, double price) {

        return new Item.Builder(name, category, getPrice(price)).build();
    }

    public IItem getItemImported(String name, Category category) {
        return new Item.Builder(name, category, getBasePrice()).imported().build();
    }

    public IItem getItemImported(String name, Category category, double price) {
        return new Item.Builder(name, category, getPrice(price)).imported().build();
    }

    public IPrice getPrice(double value) {
        ICurrency currency
                = new Currency.Builder(ICurrency.SupportedCurrency.EUR, 1).build();

        return new Price.Builder(BigDecimal.valueOf(value))
                .currency(currency).build();
    }

    public IPrice getBasePrice() {
        ICurrency currency
                = new Currency.Builder(ICurrency.SupportedCurrency.EUR, 1).build();

        return new Price.Builder(BigDecimal.valueOf(15.5))
                .currency(currency).build();
    }

    public IReceiptItem getReceiptItem(IItem item) {
        return new ReceiptItem.Builder(item).build();

    }
}
