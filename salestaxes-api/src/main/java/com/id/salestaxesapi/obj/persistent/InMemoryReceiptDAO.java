package com.id.salestaxesapi.obj.persistent;

import com.id.salestaxesapi.api.IReceipt;
import java.util.HashSet;
import java.util.Set;

/**
 * Very simple and dummy DAO WARNING, this functionality has been added only to
 * complete the design, and, of course, could be improved a lot
 *
 * @author Fabrizio Faustinoni
 */
public class InMemoryReceiptDAO implements IReceiptDAO {

    private static Set<IReceipt> memoryDriver = memoryDriver = new HashSet<>();

    public InMemoryReceiptDAO() {

    }

    @Override
    public void insertReceipt(IReceipt receipt) throws ElementExistsException {
        System.out.println(memoryDriver.size());
        if (memoryDriver.contains(receipt)) {
            throw new ElementExistsException("The Order with id:" + receipt.getID()
                    + " lready exists");
        }
        memoryDriver.add(receipt);
        System.out.println(memoryDriver.size());
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<IReceipt> findAll() {
        Set<IReceipt> all = new HashSet<>(memoryDriver);
        return all;
    }
}
