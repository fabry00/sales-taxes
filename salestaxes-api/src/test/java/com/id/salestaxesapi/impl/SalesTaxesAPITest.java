package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.TestCases;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IReceipt;
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

    public SalesTaxesAPITest() {
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

    /**
     * Test of purchase method, of class SalesTaxesAPI.
     */
    @Test
    public void testPurchase() {
        System.out.println("purchase");
        IOrder order = testCases.input1();
        SalesTaxesAPI instance = new SalesTaxesAPI();
        double expResult = TestCases.TOTAL_TAXES_INPUT1;
        IReceipt result = instance.purchase(order);
        assertEquals(expResult, result.getSalesTaxes(), 0);
    }

}
