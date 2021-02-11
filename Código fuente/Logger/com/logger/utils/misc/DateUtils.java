package com.logger.utils.misc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hikingcarrot7
 */
public class DateUtils {

    public static String getCurrentDate() {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	return dateFormat.format(date);
    }

    public static String getCurrentHour() {
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }

}
