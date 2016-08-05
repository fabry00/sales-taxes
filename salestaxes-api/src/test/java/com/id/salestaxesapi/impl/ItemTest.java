package com.id.salestaxesapi.impl;

import com.id.salestaxesapi.TestHelper;
import com.id.salestaxesapi.api.IItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for IItem Interface
 *
 * @author Fabrizio Faustinoni
 */
public class ItemTest {

    private static IItem baseItem;
    private static TestHelper helper;

    public ItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
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
     * Test of getName method, of class Item.
     */
    @Test
    public void testGetName() {
        String name = "My ITEM";
        IItem instance = new Item.Builder(name, Category.OTHER,
                helper.getBasePrice()).build();
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test all Item that by definition are taxes free. In this way we are sure
     * that if someone change it we will know
     */
    @Test
    public void testTaxesFreeItems() {
        String name = "myItem";
        IItem item = helper.getItem(name, Category.BOOK);
        assertTrue(item.isTaxesFree());

        item = helper.getItem(name, Category.FOOD);
        assertTrue(item.getCategory() + " should be taxes free",
                item.isTaxesFree());

        item = helper.getItem(name, Category.MEDICAL);
        assertTrue(item.getCategory() + " should be taxes free",
                item.isTaxesFree());

        item = helper.getItem(name, Category.OTHER);
        assertFalse(item.getCategory() + " should be taxes free",
                item.isTaxesFree());
    }

    @Test
    public void testEquals() {
        IItem secondItem = helper.getBaseItem();
        assertEquals(baseItem, secondItem);
        assertEquals(baseItem.hashCode(), secondItem.hashCode());

    }

    @Test
    public void testNotEquals() {
        IItem secondItem = new Item.Builder("item2", Category.OTHER,
                helper.getBasePrice()).build();
        assertNotEquals(baseItem, secondItem);
        assertNotEquals(baseItem.hashCode(), secondItem.hashCode());
    }

}
