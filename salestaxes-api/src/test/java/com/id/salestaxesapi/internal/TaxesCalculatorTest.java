package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.TestHelper;
import com.id.salestaxesapi.api.IOrderItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.impl.Category;
import com.id.salestaxesapi.impl.Price;
import com.id.salestaxesapi.internal.taxes.GeneralTaxesContrb;
import com.id.salestaxesapi.internal.taxes.ImportedTaxesContrib;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the Taxes calculator
 *
 * @author Fabrizio Faustinoni
 */
public class TaxesCalculatorTest {

    private static final double DELTA = 0;
    private static TestHelper helper;

    public TaxesCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetItemTaxes() {
        System.out.println("getItemTaxes");
        IOrderItem item = helper.getBaseOrderItem();

        int expResult = GeneralTaxesContrb.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item.getItem());
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes2() {
        System.out.println("getItemTaxes2");
        IOrderItem item = helper.getOrderItem(helper.getItemExported("Item", Category.OTHER));

        int expResult = GeneralTaxesContrb.TAXES + ImportedTaxesContrib.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item.getItem());
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes3() {
        System.out.println("getItemTaxes3");
        IOrderItem item = helper.getOrderItem(helper.getItem("Item", Category.BOOK));

        int expResult = 0;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item.getItem());
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes4() {
        System.out.println("getItemTaxes4");
        IOrderItem item = helper.getOrderItem(helper.getItemExported("Item", Category.BOOK));

        int expResult = ImportedTaxesContrib.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item.getItem());
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testCalculateTaxes() {
        System.out.println("calculateTaxes");

        double priceValue = 12.49;
        int taxes = 0;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator().calculateTaxesAmount(price, taxes);
        assertEquals(priceValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes2() {
        System.out.println("calculateTaxes2");

        double priceValue = 14.99;
        double expectedValue = 1.5;
        int taxes = 10;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes3() {
        System.out.println("calculateTaxes3");

        double priceValue = 27.99;
        double expectedValue = 4.2;
        int taxes = 15;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator().calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }
}
