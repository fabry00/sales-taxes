package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.TestHelper;
import com.id.salestaxesapi.api.ICustomer;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrder;
import com.id.salestaxesapi.api.IOrderItem;
import java.util.Date;
import java.util.Set;
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
    private static IOrderItem baseOrderItem;
    private static IItem baseItem;
    private static TestHelper helper;
    
    public OrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
        baseOrderItem = helper.getBaseOrderItem();
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
        Set<IOrderItem> expResult = helper.getBaseOrder().getGoods();
        Set<IOrderItem> result = baseOrder.getGoods();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGoods method, of class Order.
     */
    @Test
    public void testGetGoods2() {
        System.out.println("getGoods2");
        assertTrue(baseOrder.getGoods().contains(baseOrderItem));
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
