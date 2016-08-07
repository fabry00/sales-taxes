package com.id.salestaxesapi.obj.persistent;

/**
 * The dao factory
 * WARNING, this functionality has been added only
 * to complete the design, and, of course, could
 * be improved a lot
 * @author Fabrizio Faustinoni
 */
public class DAOFactory {
    
    public DAOFactory() {
          
    }
    
    public IReceiptDAO getReceiptDAO() {
        return new InMemoryReceiptDAO();
    }

}
