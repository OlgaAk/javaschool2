package javaee.util;

import java.text.DateFormat;
import java.text.ParseException;
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

    public static Date getTimeFromHoursString(String time) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String today = dateFormat.format(now);
        Date date = new Date();
        try {
            DateFormat format = new SimpleDateFormat("HH:mm yyyy.MM.dd");
            date = format.parse(time + " " + today);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
