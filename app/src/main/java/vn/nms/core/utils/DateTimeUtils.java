package vn.nms.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {

    public static int compareTwoDate(long t1, long t2) {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy", Locale.US);
        Date date1 = new Date(t1);
        Date date2 = new Date(t2);
        String dateString1 = df.format(date1);
        String dateString2 = df.format(date2);
        try {
            Date formatDate1 = df.parse(dateString1);
            Date formatDate2 = df.parse(dateString2);
            return formatDate1.compareTo(formatDate2);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Date getDate(String srcDate) {
        SimpleDateFormat serverTimeFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        serverTimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return serverTimeFormat.parse(srcDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date getDate(long time) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(time*1000);
        return calendar.getTime();
    }
}