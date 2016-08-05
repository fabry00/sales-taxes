package com.id.salestaxes.service.resources.deserilaizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.id.salestaxes.service.helper.TestHelper;
import com.id.salestaxesapi.api.IOrder;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class OrderDeserializerTest {

    private final OrderDeserializer deserializer = new OrderDeserializer();

    private static String order1;
    private static String order2;
    private static String order3;
    private static TestHelper helper;
    private ObjectMapper mapper;

    public OrderDeserializerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        helper = new TestHelper();
        order1 = helper.getFile(TestHelper.FILE_ORDER_1);
        order2 = helper.getFile(TestHelper.FILE_ORDER_2);
        order3 = helper.getFile(TestHelper.FILE_ORDER_3);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of deserialize method, of class OrderDeserializer.
     *
     * @throws Exception
     */
    @Test
    public void testDeserialize() throws Exception {
        int expId = 515461;
        String expName = "Mario rossi";
        Date expDate = helper.getDate("2016-08-04 15:28:36");
        int expNOfGoods = 3;
        JsonParser parser = mapper.getFactory().createParser(order1);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        IOrder order = (IOrder) deserializer.deserialize(parser, ctxt);

        assertNotNull(order);
        assertEquals(expId, order.getId());
        assertEquals(expName, order.getCustomer().getName());
        assertEquals(expDate, order.getOrderDate());
        assertEquals(expNOfGoods, order.getGoods().size());

        // FIXME --> test all goods values
    }

    /**
     * Test of deserialize method, of class OrderDeserializer.
     *
     * @throws Exception
     */
    @Test
    public void testDeserialize2() throws Exception {
        int expId = 515461;
        String expName = "Mario rossi";
        Date expDate = helper.getDate("2016-08-04 15:28:36");
        int expNOfGoods = 2;
        JsonParser parser = mapper.getFactory().createParser(order2);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        IOrder order = (IOrder) deserializer.deserialize(parser, ctxt);

        assertNotNull(order);
        assertEquals(expId, order.getId());
        assertEquals(expName, order.getCustomer().getName());
        assertEquals(expDate, order.getOrderDate());
        assertEquals(expNOfGoods, order.getGoods().size());

        // FIXME --> test all goods values
    }

    /**
     * Test of deserialize method, of class OrderDeserializer.
     *
     * @throws Exception
     */
    @Test
    public void testDeserialize3() throws Exception {
        int expId = 515461;
        String expName = "Mario rossi";
        Date expDate = helper.getDate("2016-08-04 15:28:36");
        int expNOfGoods = 4;
        JsonParser parser = mapper.getFactory().createParser(order3);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        IOrder order = (IOrder) deserializer.deserialize(parser, ctxt);

        assertNotNull(order);
        assertEquals(expId, order.getId());
        assertEquals(expName, order.getCustomer().getName());
        assertEquals(expDate, order.getOrderDate());
        assertEquals(expNOfGoods, order.getGoods().size());

        // FIXME --> test all goods values
    }
}
