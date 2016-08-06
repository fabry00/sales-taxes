package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.obj.purchase.PurchaseManager;
import com.id.salestaxesapi.api.PurchaseException;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.ISalesTaxesAPI;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.obj.persistent.DAOFactory;
import com.id.salestaxesapi.obj.persistent.ElementNotFoundException;
import com.id.salestaxesapi.obj.persistent.IReceiptDAO;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The implementation of
 *
 * @author Fabrizio Faustinoni
 */
public class SalesTaxesAPI implements ISalesTaxesAPI {

    private final ITaxesCalculator calculator;
    private final Logger logger = Logger.getLogger(SalesTaxesAPI.class.getName());

    public SalesTaxesAPI(ITaxesCalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IReceipt purchase(IOrder order) throws PurchaseException {

        PurchaseManager purchaser = new PurchaseManager();
        IReceipt receipt = purchaser.purchase(order, calculator);

        return receipt;
    }

    /**
     * Return all the orders stored. WARNING: this is a very very simple and
     * dummy functionality added just to complete the design.
     *
     * @return all the orders in string format
     */
    @Override
    public Set<IReceipt> getOrders() {
        DAOFactory factory = new DAOFactory();
        IReceiptDAO dao = factory.getReceiptDAO();
        return dao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Integer orderID) {
        DAOFactory factory = new DAOFactory();
        IReceiptDAO dao = factory.getReceiptDAO();
        IReceipt receipt;
        try {
            receipt = dao.findReceipt(orderID);
            return dao.deleteReceipt(receipt);
        } catch (ElementNotFoundException ex) {
            logger.log(Level.SEVERE, "Unable to delete", ex);
        }

        return false;
    }

}
