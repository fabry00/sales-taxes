package com.id.salestaxes.service.resources.params;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.id.salestaxesapi.api.IOrder;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class OrderDeserializerTest {

    private static final String FILE_ORDER_1 = "json/order1.json";
    private static String order1;
    
    private final OrderDeserializer deserializer = new OrderDeserializer();

    protected ObjectMapper mapper;

    public OrderDeserializerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        order1 = getFile(FILE_ORDER_1);
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
     * @throws java.io.IOException
     */
    @Test
    public void testDeserialize() throws IOException {
        JsonParser parser = mapper.getFactory().createParser(order1);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        IOrder order = (IOrder) deserializer.deserialize(parser, ctxt);

        assertNotNull(order);
    }

    private static String getFile(String fileName) {

        String result = "";

        ClassLoader classLoader = OrderDeserializer.class.getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

}
