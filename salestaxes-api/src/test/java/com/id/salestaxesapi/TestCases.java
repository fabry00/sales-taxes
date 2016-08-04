package com.id.salestaxesapi;

import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.impl.Category;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class TestCases {

    public static final double PRICE_INPUT1_ELEM1 = 12.49;
    public static final double PRICE_INPUT1_ELEM2 = 16.49;
    public static final double PRICE_INPUT1_ELEM3 = 0.85;
    public static final double TOTAL_TAXES_INPUT1 = 1.50;
    public static final double TOTAL_PRICE_INPUT1 = 29.83;

    public static final double PRICE_INPUT2_ELEM1 = 10.00;
    public static final double PRICE_INPUT2_ELEM2 = 54.65;
    public static final double TOTAL_TAXES_INPUT2 = 7.65;
    public static final double TOTAL_PRICE_INPUT2 = 65.15;

    public static final double PRICE_INPUT3_ELEM1 = 32.19;
    public static final double PRICE_INPUT3_ELEM2 = 20.89;
    public static final double PRICE_INPUT3_ELEM3 = 9.75;
    public static final double PRICE_INPUT3_ELEM4 = 11.85;
    public static final double TOTAL_TAXES_INPUT3 = 6.70;
    public static final double TOTAL_PRICE_INPUT3 = 74.68;

    private final TestHelper helper;

    public TestCases() {
        helper = new TestHelper();
    }

    public IItem input1_Element1() {
        return helper.getItem("book", Category.BOOK, 12.49);
    }

    public IItem input1_Element2() {
        return helper.getItem("music CD", Category.DIGITAL, 14.99);
    }

    public IItem input1_Element3() {
        return helper.getItem("chocolate bar", Category.FOOD, 0.85);
    }

    public IItem input2_Element1() {
        return helper.getItemImported("imported box of chocolates", Category.FOOD,
                10.00);
    }

    public IItem input2_Element2() {
        return helper.getItemImported("imported bottle of perfume", Category.OTHER,
                47.50);
    }
    
    public IItem input3_Element1() {
        return helper.getItemImported("imported bottle of perfume", Category.OTHER,
                27.99);
    }
    
    public IItem input3_Element2() {
        return helper.getItem("bottle of perfume", Category.OTHER,
                18.99);
    }
    
    public IItem input3_Element3() {
        return helper.getItem("packet of headache pills", Category.MEDICAL,
                9.75);
    }
    
    public IItem input3_Element4() {
        return helper.getItemImported("box of imported chocolates", Category.FOOD,
                11.25);
    }

}
