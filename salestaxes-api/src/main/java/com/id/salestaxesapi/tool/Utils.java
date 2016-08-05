package com.id.salestaxesapi.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils
 *
 * @author Fabrizio Faustinoni
 */
public class Utils {

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public String dateToString(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT);
        return dt.format(date);
    }
}
