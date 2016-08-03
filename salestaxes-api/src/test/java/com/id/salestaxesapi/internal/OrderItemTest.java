package com.id.salestaxesapi.internal;

import com.id.salestaxesapi.TestHelper;
import com.id.salestaxesapi.api.IItem;
import com.id.salestaxesapi.api.IOrderItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Order Item Test
 *
 * @author Fabrizio Faustinoni
 */
public class OrderItemTest {

    private static IOrderItem baseOrderItem;
    private static IItem baseItem;
    private static TestHelper helper;

    public OrderItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
        baseOrderItem = helper.getBaseOrderItem();
        baseItem = helper.getBaseItem();
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
     * Test of getItem method, of class OrderItem.
     */
    @Test
    public void testGetItem() {
        System.out.println("getItem");

        IOrderItem instance = new OrderItem.Builder(baseItem).build();
        IItem expResult = baseItem;
        IItem result = instance.getItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantity method, of class OrderItem.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        IOrderItem instance = helper.getBaseOrderItem();
        int expResult = 1;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals() {
        System.out.println("Equals");
        IOrderItem secondItem = helper.getBaseOrderItem();
        assertEquals(baseOrderItem, secondItem);
        assertEquals(baseOrderItem.hashCode(), secondItem.hashCode());
    }

    @Test
    public void testNotEquals() {
        System.out.println("NotEquals");
        IItem secondItem = new Item.Builder("item2").build();
        IOrderItem secondOrderItem = new OrderItem.Builder(secondItem).build();
        assertNotEquals(baseOrderItem, secondOrderItem);
        assertNotEquals(baseOrderItem.hashCode(), secondOrderItem.hashCode());
    }

    @Test
    public void testNotEquals2() {
        System.out.println("NotEquals2");
        IItem item = new Item.Builder(TestHelper.ITEM_NAME).build();

        IOrderItem firstOrderItem = new OrderItem.Builder(item)
                .withQuantity(2)
                .build();

        IOrderItem secondOrderItem = new OrderItem.Builder(item)
                .withQuantity(3)
                .build();

        assertNotEquals(firstOrderItem, secondOrderItem);
        assertNotEquals(firstOrderItem.hashCode(), secondOrderItem.hashCode());
    }

}
