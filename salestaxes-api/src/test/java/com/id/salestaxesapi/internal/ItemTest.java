package com.id.salestaxesapi.internal;

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
        System.out.println("getName");
        String name = "My ITEM";
        IItem instance = new Item.Builder(name).build();
        String expResult = name;
        String result = instance.getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEquals() {
        System.out.println("Equals");
        IItem secondItem = helper.getBaseItem();
        assertEquals(baseItem, secondItem);
        assertEquals(baseItem.hashCode(), secondItem.hashCode());
        
    }
    
    @Test
    public void testNotEquals() {
        System.out.println("NotEquals");
        IItem secondItem =  new Item.Builder("item2").build();
        assertNotEquals(baseItem, secondItem);
        assertNotEquals(baseItem.hashCode(), secondItem.hashCode());
    }

}
