package javaee.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeConverter {
    /**
     * Static util method to convert date to a string with any pattern
     * @param date Date
     * @param pattern String
     * @return String
     */
    public static String parseDateToString(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        String dateString = "";
        dateString = format.format(date);
        return dateString;
    }
    public static String parseDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String dateString = "";
        dateString = format.format(date);
        return dateString;
    }

    public static Calendar createCalender() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
