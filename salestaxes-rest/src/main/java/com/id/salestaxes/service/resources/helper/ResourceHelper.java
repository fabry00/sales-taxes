package com.id.salestaxes.service.resources.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fabrizio Faustinoni
 */
public class ResourceHelper {

    private final static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public Date stringToDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.parse(date);
    }

    public String dateToString(Date date) throws ParseException {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }
}
