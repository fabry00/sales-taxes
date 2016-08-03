package com.id.salestaxesapi.internal;

/**
 * The Category Enum
 *
 * @author Fabrizio Faustinoni
 */
public enum Category {
    BOOK(Type.TAXES_FREE),
    FOOD(Type.TAXES_FREE),
    MEDICAL(Type.TAXES_FREE),
    HOME(Type.WITH_TAXES),
    GARDEN(Type.WITH_TAXES),
    SPORTS(Type.WITH_TAXES),
    TOOLS(Type.WITH_TAXES),
    VIDEO(Type.WITH_TAXES),
    DIGITAL(Type.WITH_TAXES),
    OTHER(Type.WITH_TAXES);

    private enum Type {
        TAXES_FREE, WITH_TAXES
    };

    private final Type type;

    Category(Type type) {
        this.type = type;
    }

    public boolean isTaxesFree() {
        return this.type.equals(Type.TAXES_FREE);
    }
}
