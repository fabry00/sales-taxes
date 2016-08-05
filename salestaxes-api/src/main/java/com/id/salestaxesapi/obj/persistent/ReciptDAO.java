package com.id.salestaxesapi.obj.persistent;

import com.id.salestaxesapi.api.IReceipt;
import java.io.IOException;

/**
 * Very very Very simple and dummy DAO
 * FIXME We should create an interface
 *
 * @author Fabrizio Faustinoni
 */
public class ReciptDAO {

    private final IPersistentEngine persistent;

    public ReciptDAO(IPersistentEngine persistent) {
        this.persistent = persistent;
    }

    public boolean insertRecipt(IReceipt receipt) {
        try {
            // Fake query
            return persistent.execQuery(receipt);
        } catch (IOException ex) {
            return false;
        }
    }

    public String findAll() {
        try {
            // Fake query
            return persistent.execQuery("SELECT * FROM...");
        } catch (IOException ex) {
            return "";
        }
    }
}
