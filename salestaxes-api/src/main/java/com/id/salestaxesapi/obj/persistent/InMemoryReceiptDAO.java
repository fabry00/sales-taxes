package com.id.salestaxesapi.obj.persistent;

import com.id.salestaxesapi.api.IReceipt;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Very simple and dummy DAO.
 * WARNING, this functionality has been added only to
 * complete the design, and, of course, could be improved a lot
 *
 * @author Fabrizio Faustinoni
 */
public class InMemoryReceiptDAO implements IReceiptDAO {

    private static final int MAX_ELEMENTS = 100;
    private static Set<IReceipt> memoryDriver = ConcurrentHashMap.newKeySet();

    public InMemoryReceiptDAO() {

    }

    @Override
    public void insertReceipt(IReceipt receipt) throws InsertElementException {
        if (memoryDriver.size() > MAX_ELEMENTS) {
            throw new InsertElementException("Limit reached!! MAX: " + MAX_ELEMENTS);
        }
        if (memoryDriver.contains(receipt)) {
            throw new InsertElementException("The Order with id:" + receipt.getID()
                    + " already exists");
        }
        memoryDriver.add(receipt);
    }

    @Override
    public boolean deleteReceipt(IReceipt receipt) {
        if (memoryDriver.contains(receipt)) {
            memoryDriver.remove(receipt);
            return true;
        }

        return false;
    }

    @Override
    public IReceipt findReceipt(int id) throws ElementNotFoundException {
        for (IReceipt rec : memoryDriver) {
            if (rec.getID() == id) {
                return rec;
            }
        }
        throw new ElementNotFoundException("The Receipt with id   "
                + id + " not found");
    }

    @Override
    public boolean updateReceipt(IReceipt receipt) {
        throw new UnsupportedOperationException("The Receipt is immutable and "
                + "cannot be modified");
    }

    @Override
    public Set<IReceipt> findAll() {
        Set<IReceipt> all = new HashSet<>(memoryDriver);
        return all;
    }
}
