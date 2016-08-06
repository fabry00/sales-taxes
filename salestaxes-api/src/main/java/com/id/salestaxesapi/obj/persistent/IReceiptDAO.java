package com.id.salestaxesapi.obj.persistent;

import com.id.salestaxesapi.api.IReceipt;
import java.util.Set;

/**
 * The receipt DAO interface
 * WARNING, this functionality has been added only
 * to complete the design, and, of course, could
 * be improved a lot
 * @author Fabrizio Faustinoni
 */
public interface IReceiptDAO {

    /**
     * insert a Receipt
     *
     * @param receipt The receipt
     * @throws InsertElementException The exception
     */
    public void insertReceipt(IReceipt receipt) throws InsertElementException;

    /**
     * Delete a receipt
     *
     * @param receipt The receipt
     * @return ture if deleted
     */
    public boolean deleteReceipt(IReceipt receipt);

    /**
     * find a Receipt
     *
     * @param id The id
     * @return The receipt
     * @throws ElementNotFoundException The exception
     */
    public IReceipt findReceipt(int id)  throws ElementNotFoundException;

    /**
     * update a Receipt
     *
     * @param receipt The receipt
     * @return true if update
     
     */
    public boolean updateReceipt(IReceipt receipt);

    /**
     * @return all receipt
     */
    public Set<IReceipt> findAll();
}
