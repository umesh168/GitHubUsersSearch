package com.umesh.github.app.githubsearch.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;

public class DateHelper {

    public static final String DATE_FORMAT_DMY = "dd-MM-yyyy";
    public static final String DATE_FORMAT_MDY = "MM/dd/yyyy";
    public static final String DATE_FORMAT_DMY1 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YY = "yy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT_DMY = DATE_FORMAT_DMY + " " + TIME_FORMAT;
    public static final String DATE_TIME_FORMAT_MDY = DATE_FORMAT_MDY + " " + TIME_FORMAT;
    public static final String DATE_TIME_FORMAT_YMD = DATE_FORMAT_YMD + " " + TIME_FORMAT;
    public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String DATE_FORMAT_UTC_WITH_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String TIMESTAMP_FORMAT_NOTIFICATION = "dd-MMM-yy', at 'HH:mm:ss";

    public static final int MILLI_TO_HOUR = 1000 * 60 * 60;
    public static final int MILLI_TO_MINUTE = 1000 * 60;

    public static final String TWELVE_HOUR_TIME_FORMAT = "hh:mm a";

    public static final String DATE_FORMAT_DMMY = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DDMY = "dd MMM yyyy";
    public static final String DATE_FORMAT_DDM = "dd-MMM";
    public static final String DAY_OF_WEEK = "EEEE";
    public static final String VOUCHER_TIMESTAMP_FORMAT = DAY_OF_WEEK + "," + DATE_FORMAT_DDMY;

    public static final String CHAT_TIMESTAMP_FORMAT = DATE_FORMAT_DMMY + " " + TWELVE_HOUR_TIME_FORMAT;
    public static final String CHARIOT_DATE_FORMAT = DATE_FORMAT_DDMY + " | " + TWELVE_HOUR_TIME_FORMAT;
    public static final String COUPON_DATE_FORMAT =  DATE_FORMAT_DDM;
    public static final String DATE_TIME_12H_FORMAT_YMD = "dd-MM-yyyy" + " " + TWELVE_HOUR_TIME_FORMAT;
    public static final String DATE_TIME_FORMAT_DMY_TWELVE_HOURS = DATE_FORMAT_DMY + " " + TWELVE_HOUR_TIME_FORMAT;
    public static final String DATE_TIME_24H_FORMAT_YMD = "dd-MM-yyyy" + " " + "hh:mm:ss";

    public static String getChariotDateFormat(Date d) {
        return new SimpleDateFormat(CHARIOT_DATE_FORMAT).format(d);
    }

    public static String getYYFormattedDate(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_YY).format(d);
    }

    public static String getCouponDateFormat(Date d){
        return new SimpleDateFormat(COUPON_DATE_FORMAT).format(d);
    }

    public static String getDMYFormattedDate(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_DMY).format(d);
    }

    public static String getDMYFormattedDate1(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_DMY1).format(d);
    }
    public static String getMDYFormattedDate(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_MDY).format(d);
    }

    public static String getYMDFormattedDate(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_YMD).format(d);
    }

    public static String getFormattedTime(Date d) {
        return new SimpleDateFormat(TIME_FORMAT).format(d);
    }

    public static String getDMYFormattedTimestamp(Date d) {
        return new SimpleDateFormat(DATE_TIME_FORMAT_DMY).format(d);
    }

    public static String getMDYFormattedTimestamp(Date d) {
        return new SimpleDateFormat(DATE_TIME_FORMAT_MDY).format(d);
    }

    public static String getYMDFormattedTimestamp(Date d) {
        return new SimpleDateFormat(DATE_TIME_FORMAT_YMD).format(d);
    }

    public static String getFormattedNotificationTimestamp(Date d) {
        return new SimpleDateFormat(TIMESTAMP_FORMAT_NOTIFICATION).format(d);
    }

    public static String getVoucherTimestampFormat(Date d) {
        return new SimpleDateFormat(VOUCHER_TIMESTAMP_FORMAT).format(d);
    }

    public static String getFormattedChatTimestamp(Date d) {
        return new SimpleDateFormat(CHAT_TIMESTAMP_FORMAT).format(d);
    }

    public static String getTwelveHourFormattedTime(Date d) {
        return new SimpleDateFormat(TWELVE_HOUR_TIME_FORMAT).format(d);
    }

    public static String getDateFormatDmmy(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_DMMY).format(d);
    }

    public static String getDateFormatDdmy(Date d) {
        return new SimpleDateFormat(DATE_FORMAT_DDMY).format(d);
    }

    public static String getDayOfWeek(Date d) {
        return new SimpleDateFormat(DAY_OF_WEEK).format(d);
    }

    public static Date parseChariotDate(String time) {
        try {
            return new SimpleDateFormat(CHARIOT_DATE_FORMAT).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDMYDateTime(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_FORMAT_DMY).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDMYFormattedDate(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_DMY).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse12HDateTimeStamp(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_12H_FORMAT_YMD).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse24HDateTimeStamp(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_24H_FORMAT_YMD).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDMMYDateTime(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_DDMY).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseMDYDateTime(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_FORMAT_MDY).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseYMDDateTime(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_FORMAT_YMD).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_DMY).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseMDYDate(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_MDY).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getCurrDateWithTimeZone(String timeZone) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        calendar.setTime(date);
        return calendar.getTime();
    }

    public static Date convertUTCToDate(String date) {
        DateFormat utcFormat = new SimpleDateFormat(DATE_FORMAT_UTC);
        Date convertedDate = null;
        try {
            convertedDate = utcFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date convertUTCToDateWithUTCZone(String date) {
        DateFormat utcFormat = new SimpleDateFormat(DATE_FORMAT_UTC);
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date convertedDate = null;
        try {
            convertedDate = utcFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Date convertUTCToDateWithZoneFormat(String date, String timeZone) {
        SimpleDateFormat utcFormat = new SimpleDateFormat(DATE_FORMAT_UTC_WITH_ZONE);
        Date utcDate = null, localDate = null;
        try {
            utcDate = utcFormat.parse(date);
            localDate = new Date(utcDate.getTime() + TimeZone.getTimeZone(timeZone).getRawOffset() + TimeZone.getTimeZone(timeZone).getDSTSavings());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return localDate;
    }

    public static Date parseDMYTwelveHourDateTim(String dateTime) {
        try {
            return new SimpleDateFormat(DATE_TIME_FORMAT_DMY_TWELVE_HOURS).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setToStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        date.setTime(calendar.getTimeInMillis());
        return new SimpleDateFormat(DATE_TIME_24H_FORMAT_YMD).format(date);
    }

    public static String
    setToEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        date.setTime(calendar.getTimeInMillis());
        return new SimpleDateFormat(DATE_TIME_24H_FORMAT_YMD).format(date);
    }

    public static String getRemainingMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();

        return null;
    }

    public static Date getLeftTimeWindow(Date date, int window) {
        Calendar leftTimeWindow = Calendar.getInstance();
        leftTimeWindow.setTimeInMillis(date.getTime() - window);
        return leftTimeWindow.getTime();
    }

    public static Date getRightTimeWindow(Date date, int window) {
        Calendar rightTimeWindow = Calendar.getInstance();
        rightTimeWindow.setTimeInMillis(date.getTime() + window);
        return rightTimeWindow.getTime();
    }

    public static void setToFirstDayOfPreviousWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date.setTime(calendar.getTimeInMillis());
    }

    public static boolean validateTime(String str) throws NumberFormatException, PatternSyntaxException {
        try {
            String[] hrsAndMinsStrings = str.split(":");
            if (hrsAndMinsStrings.length != 2) {
                return false;
            }
            if (Integer.parseInt(hrsAndMinsStrings[0]) > 23 || Integer.parseInt(hrsAndMinsStrings[1]) > 59) {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new NumberFormatException("Invalid hrs./mins. values");
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            throw new PatternSyntaxException("Invalid time format", ":", -1);
        }
        return true;
    }

    public static boolean compareDateTimeStrings(String fromDateString, String toDateString) {
        Date fromDate = parseDMYDateTime(fromDateString);
        Date toDate = parseDMYDateTime(toDateString);
        if (toDate.compareTo(fromDate) >= 0) {
            return true;
        }
        return false;
    }


    public static boolean compareTimeStampEquals(String fromDateString, String toDateString) {
        Date fromDate = parseDMYDateTime(fromDateString);
        Date toDate = parseDMYDateTime(toDateString);
        if (toDate.compareTo(fromDate) == 0) {
            return true;
        }
        return false;
    }

    public static boolean compareDateStrings(String fromDateString, String toDateString) {
        Date fromDate = parseDate(fromDateString);
        Date toDate = parseDate(toDateString);
        if (toDate.compareTo(fromDate) >= 0) {
            return true;
        }
        return false;
    }

    public static Integer compareDateString(String fromDate, String toDate) {
        Date from = parseDate(fromDate);
        Date to = parseDate(toDate);
        return from.compareTo(to);
    }

    public static boolean compareDates(Date fromDate, Date toDate) {
        if (toDate.compareTo(fromDate) >= 0) {
            return true;
        }
        return false;
    }

    public static int getMinsFromHHMM(String hhMMString) {
        String[] tokenArray = hhMMString.split(":");
        int hours = Integer.valueOf(tokenArray[0]);
        int mins = Integer.valueOf(tokenArray[1]);
        return (hours * 60) + mins;
    }

    public static String getHHMMFromMins(int mins) {
        int hours = mins / 60;
        int remainMinute = mins % 60;
        String result = String.format("%02d", hours) + ":" + String.format("%02d", remainMinute);
        return result;
    }

    public static String getReminderFormattedString(long reminderTime) {
        long hours = reminderTime / 60;
        long remainMinute = reminderTime % 60;

        String result = "";
        if (hours > 0) {
            result = hours + " hr ";
        }
        if (remainMinute > 0) {
            result = result + remainMinute + " mins ";
        }
        return result;
    }

    public static int getDifferenceInHrs(Date date1, Date date2) {
        return Math.round((date1.getTime() - date2.getTime()) / MILLI_TO_HOUR);
    }

    public static double getDifferenceInSeconds(Date date1, Date date2) {
        return Math.round((date2.getTime() - date1.getTime()) / 1000);
    }

    public static long getMinsFromSeconds(long seconds) {
        return seconds / (MILLI_TO_MINUTE);
    }

    public static String getMMSS(long seconds) {
        long s = seconds % 60;
        long m = (seconds / 60) % 60;

        return String.format("%02d:%02d", m, s);
    }

    public static String getFormattedDateTime(String dateStr, String strReadFormat, String strWriteFormat) {

        String formattedDate = dateStr;

        DateFormat readFormat = new SimpleDateFormat(strReadFormat, Locale.getDefault());
        DateFormat writeFormat = new SimpleDateFormat(strWriteFormat, Locale.getDefault());

        Date date = null;

        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
        }

        if (date != null) {
            formattedDate = writeFormat.format(date);
        }

        return formattedDate;
    }

    public static boolean getIsFromSameWeek(Date firstDate, Date secondDate) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(firstDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(secondDate);
        if ((firstDate.getMonth() == secondDate.getMonth()) && (calendar1.WEEK_OF_MONTH == calendar2.WEEK_OF_MONTH))
            return true;
        else return false;
    }

    /**
     * Subtracts first date from second date
     *
     * @param firstDate
     * @param secondDate
     * @return
     * @author Umesh
     */
    public static long getDifferenceInDays(Date firstDate, Date secondDate) {

        long diffInMillies = secondDate.getTime() - firstDate.getTime();
        TimeUnit timeUnit = TimeUnit.DAYS;
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * -ve days will decreament
     *
     * @param date
     * @param days
     * @return
     * @author Umesh
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * -ve months will decreament
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the Months
        return cal.getTime();
    }

    public static int getDateField(Date date, int calendarField) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(calendarField);
    }

}