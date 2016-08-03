package com.id.salestaxesapi.internal;

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

    public ItemTest() {
    }

    @BeforeClass
    public static void setUpClass() {
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

}
