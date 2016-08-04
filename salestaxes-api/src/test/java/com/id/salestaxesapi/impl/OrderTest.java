package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.TestHelper;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for ORder
 *
 * @author Fabrizio Faustinoni
 */
public class OrderTest {

    private static IOrder baseOrder;
    private static IItem baseItem;
    private static TestHelper helper;

    public OrderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
        baseItem = helper.getBaseItem();
        baseOrder = helper.getBaseOrder();
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
     * Test of getGoods method, of class Order.
     */
    @Test
    public void testGetGoods() {
        System.out.println("getGoods");
        Map<IItem, Integer> expResult = helper.getBaseOrder().getGoods();
        Map<IItem, Integer> result = baseOrder.getGoods();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGoods method, of class Order.
     */
    @Test
    public void testGetGoods2() {
        System.out.println("getGoods2");
        assertTrue(baseOrder.getGoods().containsKey(baseItem));
    }

    /**
     * Test of equals method, of class Order.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertEquals(baseOrder, helper.getBaseOrder());
        assertEquals(baseOrder.hashCode(), helper.getBaseOrder().hashCode());
    }

}
