package com.id.salestaxesapi.obj.taxes;

import com.id.salestaxesapi.TestCases;
import com.id.salestaxesapi.obj.taxes.TaxesCalculator;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IPrice;
import com.id.salestaxesapi.obj.Price;
import com.id.salestaxesapi.obj.taxes.contributors.GeneralTaxesContrb;
import com.id.salestaxesapi.obj.taxes.contributors.ImportedTaxesContrib;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test the Taxes calculator
 *
 * @author Fabrizio Faustinoni
 */
public class TaxesCalculatorTest {

    private static final double DELTA = 0;
    private static TestCases testCases;

    public TaxesCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testCases = new TestCases();
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
        // Input1 - music CD
        IItem item = testCases.input1_Element2();
        int expResult = GeneralTaxesContrb.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item);
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes2() {
        // input2 -- imported bottle of perfume
        IItem item = testCases.input2_Element2();
        int expResult = GeneralTaxesContrb.TAXES + ImportedTaxesContrib.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item);
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes3() {
        // input1 - book
        IItem item = testCases.input1_Element1();

        int expResult = 0;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item);
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testGetItemTaxes4() {
        // input2 - imported box of chocolates
        IItem item = testCases.input2_Element1();

        int expResult = ImportedTaxesContrib.TAXES;
        int taxesPercent = new TaxesCalculator().getItemTaxes(item);
        assertEquals(expResult, taxesPercent);
    }

    @Test
    public void testCalculateTaxes() {
        // input1
        //book
        IItem item = testCases.input1_Element1();
        BigDecimal priceValue = item.getPrice().getValue();
        int taxesPrecent = 0;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxesPrecent);

        assertEquals(0, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes2() {
        // input1
        //music
        IItem item = testCases.input1_Element2();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 1.5;
        int taxes = 10;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes4() {
        // input2
        //imported box of chocolates
        IItem item = testCases.input2_Element1();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 0.5;
        int taxes = 5;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes5() {
        // input2
        //imported bottle of perfume
        IItem item = testCases.input2_Element2();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 7.15;
        int taxes = 15;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes6() {
        // input3
        //imported bottle of perfume
        IItem item = testCases.input3_Element1();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 4.2;
        int taxes = 15;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);

        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes7() {
        // input3
        //bottle of perfume
        IItem item = testCases.input3_Element2();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 1.9;
        int taxes = 10;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }

    @Test
    public void testCalculateTaxes8() {
        // input3
        //box of imported chocolates
         IItem item = testCases.input3_Element4();
        BigDecimal priceValue = item.getPrice().getValue();
        double expectedValue = 0.6;
        int taxes = 5;
        IPrice price = new Price.Builder(priceValue).build();
        double taxesAmount = new TaxesCalculator()
                .calculateTaxesAmount(price, taxes);
        assertEquals(expectedValue, taxesAmount, DELTA);
    }
}
