package com.mokalab.butler.datetime;

import android.os.Build;
import android.os.Build.VERSION_CODES;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Allows parsing and conversion of dates and times to any specified format.
 * This class mainly works with Calendar and SimpleDateFormat. If you want to parse
 * a Date and/or Time String, you may use any of the constructors that takes an Input String.
 * Input Format and Output Format are optional because it will use the Standard ISO 8601 Date and Time
 * Notation. So if you don't set an Input/Output Format, the class will use "yyyy-MM-dd'T'HH:mm:ss.SSSZ".<br>
 * <br>
 * Note: The constructors that are use to parse Input String will throw Exception if the String doesn't match
 * the Input Format. So Make sure they match and don't forget to catch the exceptions.<br>
 * <br>
 * This class can also be used to set Date and Time element values to the Calendar object which can then
 * be used to run Date and Time operation. The toString() method will always return the String representation of the
 * Date and/or Time by the specified Output Format if required.
 *
 * @author Pirdad S.
 * @since 2012-05-31
 */
public class DateTime {

    // -- Attributes
    private Calendar calendar;
    private String input_string = "";
    private String output_format = "";
    private String input_format = "";
    private TimeZone output_timezone = TimeZone.getDefault();
    private TimeZone input_timezone = TimeZone.getDefault();


    // -- Constructors
    public DateTime() {

        calendar = Calendar.getInstance();
        setInputFormat(DTFormat.DT0);
        setOutputFormat(DTFormat.DT0);
    }

    public DateTime(TimeZone timezone) {

        calendar = Calendar.getInstance(timezone);
        setInputFormat(DTFormat.DT0);
        setOutputFormat(DTFormat.DT0);
    }

    // -- Constructors : For Parsing from String
    public DateTime(String date_time) throws ParseException {

        this();
        this.input_string = date_time;
        parse();
    }

    public DateTime(String date_time, DTFormat input_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(input_format);
        parse();
    }

    public DateTime(String date_time, String custom_input_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(custom_input_format);
        parse();
    }

    public DateTime(String date_time, DTFormat input_format, String custom_output_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(input_format);
        setOutputFormat(custom_output_format);
        parse();
    }

    public DateTime(String date_time, String custom_input_format, DTFormat output_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(custom_input_format);
        setOutputFormat(output_format);
        parse();
    }

    public DateTime(String date_time, DTFormat input_format, DTFormat output_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(input_format);
        setOutputFormat(output_format);
        parse();
    }

    public DateTime(String date_time, DTFormat input_format, DTFormat output_format, TimeZone input_timezone, TimeZone output_timezone) throws
                                                                                                                                                    ParseException {

        this(input_timezone);
        this.input_string = date_time;
        this.input_timezone = input_timezone;
        this.output_timezone = output_timezone;
        setInputFormat(input_format);
        setOutputFormat(output_format);
        parse();
    }

    public DateTime(String date_time, String custom_input_format, String custom_output_format) throws ParseException {

        this();
        this.input_string = date_time;
        setInputFormat(custom_input_format);
        setOutputFormat(custom_output_format);
        parse();
    }

    public DateTime(String date_time, String custom_input_format, String custom_output_format, TimeZone input_timezone, TimeZone output_timezone) throws
                                                                                                                                                  ParseException {

        this(input_timezone);
        this.input_string = date_time;
        this.input_timezone = input_timezone;
        this.output_timezone = output_timezone;
        setInputFormat(custom_input_format);
        setOutputFormat(custom_output_format);
        parse();
    }

    // -- Constructors : Setting Time
    public DateTime(int hour, int minute, int second, int am_pm) {

        this();
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.AM_PM, am_pm);
    }

    public DateTime(int hour, int minute, int second, int am_pm, DTFormat output_format) {

        this(hour, minute, second, am_pm);
        setOutputFormat(output_format);
    }

    public DateTime(int hour, int minute, int second, int am_pm, String custom_output_format) {

        this(hour, minute, second, am_pm);
        setOutputFormat(custom_output_format);
    }

    // -- Constructors : Setting Date
    public DateTime(int year, int month, int day) {

        this();
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public DateTime(int year, int month, int day, DTFormat output_format) {

        this(year, month, day);
        setOutputFormat(output_format);
    }

    public DateTime(int year, int month, int day, String custom_output_format) {

        this(year, month, day);
        setOutputFormat(custom_output_format);
    }

    // -- Constructors : Setting Date and Time
    public DateTime(int year, int month, int day, int hour, int minute, int second) {

        this();
        setYear(year);
        setMonth(month);
        setDay(day);
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public DateTime(int year, int month, int day, int hour, int minute, int second, DTFormat output_format) {

        this(year, month, day, hour, minute, second);
        setOutputFormat(output_format);
    }

    public DateTime(int year, int month, int day, int hour, int minute, int second, String custom_output_format) {

        this(year, month, day, hour, minute, second);
        setOutputFormat(custom_output_format);
    }

    // -- Constructors : From Date
    public DateTime(Date date) {

        this();
        setDate(date);
    }

    public DateTime(Date date, DTFormat out_put_format) {

        this(date);
        setOutputFormat(out_put_format);
    }

    public DateTime(Date date, String custom_output_format) {

        this(date);
        setOutputFormat(custom_output_format);
    }


    // -- Methods
    private void parse() throws ParseException {

        if (Build.VERSION.SDK_INT < VERSION_CODES.HONEYCOMB) {

            SafeSimpleDateFormat simple_format = new SafeSimpleDateFormat(input_format);
            simple_format.setTimeZone(input_timezone);
            Date date = simple_format.parse(input_string);
            calendar.setTimeZone(input_timezone);
            calendar.setTime(date);

        } else {

            SimpleDateFormat simple_format = new SimpleDateFormat(input_format, Locale.US);
            simple_format.setTimeZone(input_timezone);
            Date date = simple_format.parse(input_string);
            calendar.setTimeZone(input_timezone);
            calendar.setTime(date);
        }
    }

    /**
     * Use this method to get the current date and time.
     *
     * @return DateTime instance of the current date and time.
     */
    public static DateTime getCurrentDateTime() {

        DateTime now = new DateTime();
        now.setCalendar(Calendar.getInstance());
        return now;
    }

    /**
     * Use this method to get the current date and time.
     *
     * @return DateTime instance of the current date and time.
     */
    public static DateTime getCurrentDateTime(TimeZone timezone) {

        DateTime now = new DateTime(timezone);
        now.setCalendar(Calendar.getInstance(timezone));
        return now;
    }

    /**
     * Use this method to get the current date and time in milliseconds.
     *
     * @return current date and time in milliseconds (long).
     */
    public static long getCurrentDateTimeInMillis() {

        return System.currentTimeMillis();
    }

    /**
     * Use this method to get the current Unix epoch date and time in seconds.
     * Unix epoch date and time is the number of seconds that have elapsed since
     * January 1, 1970 (midnight UTC/GMT).
     *
     * @return
     */
    public static long getCurrentEpochTime() {

        return System.currentTimeMillis() / 1000;
    }

    @Override
    public String toString() {

        if (Build.VERSION.SDK_INT < VERSION_CODES.HONEYCOMB) {

            int milliDifference = output_timezone.getOffset(0) - input_timezone.getOffset(0);
            calendar.setTimeInMillis(calendar.getTimeInMillis() + milliDifference);
            SafeSimpleDateFormat simple_format = new SafeSimpleDateFormat(output_format);
            simple_format.setCalendar(calendar);
            simple_format.setTimeZone(output_timezone);
            calendar.getTimeInMillis();
            Date date = calendar.getTime();
            return simple_format.format(date);

        } else {

            SimpleDateFormat simple_format = new SimpleDateFormat(output_format, Locale.US);
            simple_format.setCalendar(calendar);
            simple_format.setTimeZone(output_timezone);
            Date date = calendar.getTime();
            return simple_format.format(date);
        }
    }

    public void add(int field, int time) {
        calendar.add(field, time);
    }


    // -- Getters
    public String getInputFormat() {
        return input_format;
    }

    public String getOutputFormat() {
        return output_format;
    }

    public int getHour() {
        return calendar.get(Calendar.HOUR);
    }

    public int getHour24HourFormat() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public int getAmPm() {
        return calendar.get(Calendar.AM_PM);
    }

    public int getDay() {
        return calendar.get(Calendar.DATE);
    }

    public int getWeek() {
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public long getTimeInMillis() {
        return calendar.getTimeInMillis();
    }

    public long getAsEpochTime() {
        return calendar.getTimeInMillis() / 1000;
    }

    public Date getAsDate() {
        return calendar.getTime();
    }

    public TimeZone getTimeZone() {
        return calendar.getTimeZone();
    }


    // -- Setters
    public void setInputFormat(DTFormat format) {
        this.input_format = format.getFormatString();
    }

    public void setInputFormat(String custom_format) {
        this.input_format = custom_format;
    }

    public void setOutputFormat(DTFormat format) {
        this.output_format = format.getFormatString();
    }

    public void setOutputFormat(String custom_format) {
        this.output_format = custom_format;
    }

    public void setHour(int hour) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
    }

    public void setMinute(int minute) {
        calendar.set(Calendar.MINUTE, minute);
    }

    public void setSecond(int second) {
        calendar.set(Calendar.SECOND, second);
    }

    public void setAmPm(int am_pm) {
        calendar.set(Calendar.AM_PM, am_pm);
    }

    public void setDay(int day) {
        calendar.set(Calendar.DATE, day);
    }

    public void setWeek(int week) {
        calendar.set(Calendar.WEEK_OF_MONTH, week);
    }

    public void setMonth(int month) {
        calendar.set(Calendar.MONTH, month);
    }

    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setTimeInMillis(long time_in_milliseconds) {
        calendar.setTimeInMillis(time_in_milliseconds);
    }

    public void setEpochTime(long time_in_seconds) {
        calendar.setTimeInMillis(time_in_seconds * 1000);
    }

    public void setDate(Date date) {
        calendar.setTime(date);
    }

    public void setTimeZone(TimeZone timezone) {
        calendar.setTimeZone(timezone);
    }

    public void setOutputTimeZone(TimeZone timezone) {
        output_timezone = timezone;
    }
}