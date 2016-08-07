package com.id.salestaxes.service.helper;

import com.id.salestaxes.service.resources.deserilaizer.OrderDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class TestHelper {

    public final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String FILE_ORDER_1 = "json/order1.json";
    public static final String FILE_ORDER_2 = "json/order2.json";
    public static final String FILE_ORDER_3 = "json/order3.json";

    public String getFile(String fileName) {

        String result = "";

        ClassLoader classLoader = OrderDeserializer.class.getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public Date getDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.parse(date);
    }

}
