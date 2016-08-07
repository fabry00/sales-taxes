package com.id.salestaxesapi.api.impl;

import com.id.salestaxesapi.TestCases;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
import com.id.salestaxesapi.api.ITaxesCalculator;
import com.id.salestaxesapi.api.PurchaseException;
import com.id.salestaxesapi.obj.taxes.TaxesCalculator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Sales taxes API TEST
 *
 * @author Fabrizio Faustinoni
 */
public class SalesTaxesAPITest {

    private static TestCases testCases;
    private static ITaxesCalculator calculator;

    public SalesTaxesAPITest() {
    }

    @BeforeClass
    public static void setUpClass() {
        testCases = new TestCases();
        calculator = new TaxesCalculator();
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

    /**
     * Test of purchase method, of class SalesTaxesAPI.
     */
    @Test
    public void testPurchase() {
        try {
            IOrder order = testCases.input1();
            SalesTaxesAPI instance = new SalesTaxesAPI(calculator);
            double expResult = TestCases.TOTAL_TAXES_INPUT1;
            IReceipt result = instance.purchase(order);

            printTest(1, order, result);

            assertEquals(expResult, result.getSalesTaxes(), 0);
        } catch (PurchaseException ex) {
            fail();
        }
    }

    @Test
    public void testPurchase2() {
        try {
            IOrder order = testCases.input2();
            SalesTaxesAPI instance = new SalesTaxesAPI(calculator);
            double expResult = TestCases.TOTAL_TAXES_INPUT2;
            IReceipt result = instance.purchase(order);
            printTest(2, order, result);
            assertEquals(expResult, result.getSalesTaxes(), 0);
        } catch (PurchaseException ex) {
            fail();
        }
    }

    @Test
    public void testPurchase3() {
        try {
            IOrder order = testCases.input3();
            SalesTaxesAPI instance = new SalesTaxesAPI(calculator);
            double expResult = TestCases.TOTAL_TAXES_INPUT3;
            IReceipt result = instance.purchase(order);
            printTest(3, order, result);
            assertEquals(expResult, result.getSalesTaxes(), 0);
        } catch (PurchaseException ex) {
            fail();
        }
    }
    
    @Test(expected = PurchaseException.class)
    public void testPurchase4() throws PurchaseException {
            IOrder order = testCases.input3();
            SalesTaxesAPI instance = new SalesTaxesAPI(calculator);
            instance.purchase(order);
            instance.purchase(order);
            fail("Expected PurchaseExcepiton");
    }

    private void printTest(int id, IOrder order, IReceipt result) {
        String lineSep = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("######################################").append(lineSep);
        builder.append("Inuput: ").append(id).append(lineSep)
                .append(order.toString()).append(lineSep)
                .append("Receipt:").append(lineSep)
                .append(result.toString());
        builder.append("######################################")
                .append(lineSep)
                .append(lineSep);
        System.out.println(builder.toString());
    }

}
