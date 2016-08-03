package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.api.IItem;

/**
 * The IItem implementation Immutable object
 *
 * @author Fabrizio Faustinoni
 */
public class Item implements IItem {

    private final String name;

    /**
     * Constructor
     * @param name 
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return this.name;
    }

    public static class Builder {

        private final String name;

        public Builder(String name) {
            this.name = name;
        }
        
        
        public IItem build() {
            IItem item = new Item(this.name);
            return item;
        }
    }

}
