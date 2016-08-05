package com.id.salestaxesapi.obj.persistent;

import java.io.IOException;

/**
 * Very very Very simple and dummy Persistent engien
 *
 * @author Fabrizio Faustinoni
 */
public interface IPersistentEngine {

    public boolean execQuery(Object obj) throws IOException;
    public String execQuery(String query) throws IOException;

}
