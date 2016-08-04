package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.IReceiptItem;
import java.util.Date;
import java.util.Set;

/**
 * The Receipt implementation
 *
 * @author Fabrizio Faustinoni
 */
public class Receipt implements IReceipt {

    private final int id;
    private final Date date;
    private final String customerName;
    private final Set<IReceiptItem> goods;
    private final double salesTaxes;

    private Receipt(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.customerName = builder.customerName;
        this.goods = builder.goods;
        this.salesTaxes = builder.salesTaxes;
    }

    @Override
    public double getSalesTaxes() {
        return salesTaxes;
    }

    @Override
    public Set<IReceiptItem> getGoods() {
        return goods;
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public static class Builder {

        private final int id;
        private final Date date;
        private final String customerName;
        private final Set<IReceiptItem> goods;
        private final double salesTaxes;

        public Builder(int id, Date date, String customerName,
                Set<IReceiptItem> goods, double salesTaxes) {

            this.id = id;
            this.date = date;
            this.customerName = customerName;
            this.goods = goods;
            this.salesTaxes = salesTaxes;
        }

        public IReceipt buld() {
            return new Receipt(this);
        }

    }
}
