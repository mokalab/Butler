package com.mokalab.butler.datetime;

/**
 * Provides Format String enums for Date and Time. Contains Formats that are standard
 * or commonly used. If you require a format that isn't available, you may be able to
 * create your custom format using the Date and Time elements such as 'H' or 'mm'. These
 * enums are best used with DateTime class located in com.bnotions.axcess.datetime package
 * otherwise they can also work with Android's SimpleDateFormat class.
 *
 * @author Pirdad S.
 * @since 2012-05-31
 */
public enum DTFormat {

    // -- Time Formats

    /**
     * HHmm - 0014 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute
     */
    T0("HHmm"),

    /**
     * HH:mm - 00:14 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute
     */
    T1("HH:mm"),

    /**
     * HH:mmZ - 00:14-0500 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute <br>
     * Z -> timezone
     */
    T2("HH:mmZ"),

    /**
     * HH:mm:ss - 00:14:25 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute <br>
     * s -> second
     */
    T3("HH:mm:ss"),

    /**
     * HH:mm:ssZ - 00:14:25-0500 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute <br>
     * s -> second <br>
     * Z -> timezone
     */
    T4("HH:mm:ssZ"),

    /**
     * HH:mm:ss.SSS - 00:14:25.978 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute <br>
     * s -> second <br>
     * S -> fractional seconds
     */
    T5("HH:mm:ss.SSS"),

    /**
     * HH:mm:ss.SSSZ - 00:14:25.978-0500 <br>
     * ISO 8601 - Standard Time Notation <br>
     * H -> hour (0-23) <br>
     * m -> minute <br>
     * s -> second <br>
     * S -> fractional seconds <br>
     * Z -> timezone
     */
    T6("HH:mm:ss.SSSZ"),

    /**
     * KK:mm - 00:14 <br>
     * K -> hour (0-11) <br>
     * m -> minute
     */
    T7("KK:mm"),

    /**
     * KK:mm:ss - 11:14:25 <br>
     * K -> hour (0-11) <br>
     * m -> minute <br>
     * s -> second
     */
    T8("KK:mm:ss"),

    /**
     * KK:mm a - 11:14 AM <br>
     * K -> hour (0-11) <br>
     * m -> minute <br>
     * a -> AM/PM
     */
    T9("KK:mm a"),

    /**
     * KK:mm:ss a - 11:14:25 AM <br>
     * K -> hour (0-11) <br>
     * m -> minute <br>
     * s -> second <br>
     * a -> AM/PM
     */
    T10("KK:mm:ss a"),

    /**
     * h:mm - 9:45 <br>
     * h -> hour(1-12) <br>
     * m -> minute <br>
     */
    T11("h:mm"),

    /**
     * h:mm:ss - 9:45:20 <br>
     * h -> hour(1-12) <br>
     * m -> minute <br>
     * s -> second
     */
    T12("h:mm:ss"),

    /**
     * h:mm a - 9:45 PM <br>
     * h -> hour(1-12) <br>
     * m -> minute <br>
     * a -> AM/PM
     */
    T13("h:mm a"),

    /**
     * h:mm:ss a - 9:45:20 PM <br>
     * h -> hour(1-12) <br>
     * m -> minute <br>
     * s -> second
     * a -> AM/PM
     */
    T14("h:mm:ss a"),

    /**
     * kk:mm - 23:10 <br>
     * k -> hour (1-24) <br>
     * m -> minute
     */
    T15("kk:mm"),

    /**
     * kk:mm:ss - 23:10:22 <br>
     * k -> hour (1-24) <br>
     * m -> minute
     * s -> second
     */
    T16("kk:mm:ss"),

    /**
     * H - 8 <br>
     * Hour (0-23)
     */
    T_SINGLE_HOUR_1("H"),

    /**
     * HH - 08 <br>
     * Hour (0-23)
     */
    T_DOUBLE_HOUR_1("HH"),

    /**
     * k - 8 <br>
     * Hour (1-24)
     */
    T_SINGLE_HOUR_2("k"),

    /**
     * kk - 08 <br>
     * Hour (1-24)
     */
    T_DOUBLE_HOUR_2("kk"),

    /**
     * K - 8 <br>
     * Hour (0-11)
     */
    T_SINGLE_HOUR_3("K"),

    /**
     * KK - 08 <br>
     * Hour (0-11)
     */
    T_DOUBLE_HOUR_3("KK"),

    /**
     * h - 8 <br>
     * Hour (1-12)
     */
    T_SINGLE_HOUR_4("h"),

    /**
     * hh - 08 <br>
     * Hour (1-12)
     */
    T_DOUBLE_HOUR_4("hh"),

    /**
     * m - 2 <br>
     * Minute (0-60)
     */
    T_SINGLE_MINUTE("m"),

    /**
     * mm - 02 <br>
     * Minute (00-60)
     */
    T_DOUBLE_MINUTE("mm"),

    /**
     * s - 5 <br>
     * Second (0-60)
     */
    T_SINGLE_SECOND("s"),

    /**
     * ss - 05 <br>
     * Second (00-60)
     */
    T_DOUBLE_SECOND("ss"),

    /**
     * a - AM <br>
     * AM/PM flag (AM or PM)
     */
    T_AM_PM("a"),

    /**
     * SSS - 978 <br>
     * Fractional Seconds
     */
    T_FRACTIONAL_SECOND("SSS"),

    /**
     * Z - -0500 <br>
     * Timezone
     */
    T_TIMEZONE("Z"),

    // -- Date Formats

    /**
     * yyyy-MM-dd - 2010-02-25 <br>
     * ISO 8601 - Standard Date Notation
     */
    D0("yyyy-MM-dd"),

    /**
     * M/d/yy - 2/9/95
     */
    D1("M/d/yy"),

    /**
     * M/d/yyyy - 2/9/1995
     */
    D2("M/d/yyyy"),

    /**
     * d/M/yy - 9/2/95
     */
    D3("d/M/yy"),

    /**
     * d/M/yyyy - 9/2/1995
     */
    D4("d/M/yyyy"),

    /**
     * yy/M/d - 95/2/9
     */
    D5("yy/M/d"),

    /**
     * yyyy/M/d - 1995/2/9
     */
    D6("yyyy/M/d"),

    /**
     * d.M.yyyy - 25.2.1995
     */
    D7("d.M.yyyy"),

    /**
     * yyyy-MMM-dd - 1995-Feb-09
     */
    D8("yyyy-MMM-dd"),

    /**
     * MMM-dd-yyyy - Feb-09-1995
     */
    D9("MMM-dd-yyyy"),

    /**
     * dd-MMM-yyyy - 09-Feb-1995
     */
    D10("dd-MMM-yyyy"),

    /**
     * yyyy-MMMM-d - 1995-February-9
     */
    D11("yyyy-MMMM-d"),

    /**
     * MMMM-d-yyyy - February-9-1995
     */
    D12("MMMM-d-yyyy"),

    /**
     * d-MMMM-yyyy - 9-February-1995
     */
    D13("d-MMMM-yyyy"),

    /**
     * yyyyMMdd - 19950225 - Compact
     */
    D14("yyyyMMdd"),

    /**
     * yyyy-MM - 1995-02
     */
    D15("yyyy-MM"),

    /**
     * MM-dd - 02-09
     */
    D16("MM-dd"),

    /**
     * M-d - 2-9
     */
    D17("MM-d"),

    /**
     * MMM-d - Feb-9
     */
    D18("MMM-d"),

    /**
     * MMM-dd - Feb-09
     */
    D19("MMM-dd"),

    /**
     * MMMM-d - February-9
     */
    D20("MMMM-d"),

    /**
     * MMMM-dd - February-09
     */
    D21("MMMM-dd"),

    /**
     * yyyy - 1995 <br>
     * Year in 4-Character
     */
    D_FULL_YEAR("yyyy"),

    /**
     * yy - 95 <br>
     * Double Digit Year
     */
    D_DOUBLE_YEAR("yy"),

    /**
     * M - 2 <br>
     * Single Digit Month
     */
    D_SINGLE_MONTH("M"),

    /**
     * MM - 02 <br>
     * Double Digit Month
     */
    D_DOUBLE_MONTH("MM"),

    /**
     * MMM - Feb <br>
     * Month in 3-Character
     */
    D_3CHAR_MONTH("MMM"),

    /**
     * MMMM - February <br>
     * Month in full word
     */
    D_FULL_MONTH("MMMM"),

    /**
     * dd - 09 <br>
     * Double Digit Day
     */
    D_DOUBLE_DAY("dd"),

    /**
     * d - 9 <br>
     * Single Digit Day
     */
    D_SINGLE_DAY("d"),

    // -- Date and Time Formats

    /**
     * yyyy-MM-dd'T'HH:mm:ss.SSSZ - 1995-02-25T00:00:00.387-0500 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT0("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),

    /**
     * yyyy-MM-dd'T'HH:mm:ssZ - 1995-02-25T00:00:00-0500 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT1("yyyy-MM-dd'T'HH:mm:ssZ"),

    /**
     * yyyy-MM-dd'T'HH:mmZ - 1995-02-25T00:00-0500 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT2("yyyy-MM-dd'T'HH:mmZ"),

    /**
     * yyyy-MM-dd HH:mm:ss.SSS - 1995-02-25 00:00:00.387 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT3("yyyy-MM-dd HH:mm:ss.SSS"),

    /**
     * yyyy-MM-dd HH:mm:ss - 1995-02-25 00:00:00 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT4("yyyy-MM-dd HH:mm:ss"),

    /**
     * yyyy-MM-dd HH:mm - 1995-02-25 00:00 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT5("yyyy-MM-dd HH:mm"),

    /**
     * yyyy-MM-dd kk:mm:ss - 1995-02-25 24:00:00
     */
    DT6("yyyy-MM-dd kk:mm:ss"),

    /**
     * yyyy-MM-dd kk:mm - 1995-02-25 24:00
     */
    DT7("yyyy-MM-dd kk:mm"),

    /**
     * yyyy-MM-dd hh:mm a - 1995-02-25 12:25 PM
     */
    DT8("yyyy-MM-dd hh:mm a"),

    /**
     * yyyy-MM-dd hh:mm:ss a - 1995-02-25 12:25:10 PM
     */
    DT9("yyyy-MM-dd hh:mm:ss a"),

    /**
     * MMM-dd-yyyy hh:mm a - Feb-09-1995 12:25 PM
     */
    DT10("MMM-dd-yyyy hh:mm a"),

    /**
     * MMM-dd-yyyy hh:mm:ss a - Feb-09-1995 12:25:10 PM
     */
    DT11("MMM-dd-yyyy hh:mm:ss a"),

    /**
     * MMM-dd-yyyy h:mm a - Feb-09-1995 9:02 PM
     */
    DT12("MMM-dd-yyyy h:mm a"),

    /**
     * EEE, dd MMM yyyy HH:mm:ss Z - Mon, 25 Jun 2012 23:05:00 -0000
     */
    DT13("EEE, dd MMM yyyy HH:mm:ss Z"),

    /**
     * MMM-dd-yyyy h:mm:ss a - Feb-09-1995 9:02:10 PM
     */
    DT14("MMM-dd-yyyy h:mm:ss a"),

    /**
     * yyyy-MM-dd'T'HH:mm:ss - 1995-02-25T00:00:00 <br>
     * ISO 8601 - Standard Date and Time Notation
     */
    DT15("yyyy-MM-dd'T'HH:mm:ss"),

    /**
     * dd MMM yyyy HH:mm - 23 Jan 1998 08:21 <br>
     */
    DT16("dd MMM yyyy HH:mm"),

    /**
     * yyyy/MM/dd - 2014/01/20 <br>
     */
    DT17("yyyy/MM/dd"),

    /**
     * MMMM dd, yyyy - January 20,2014 <br>
     */
    DT18("MMMM dd, yyyy"),

    /**
     * dd MMM yyyy - 23 Jan 1998 <br>
     */
    DT19("dd MMM yyyy"),

    /**
     * dd MMM yyyy HH:mm - 23 Jan 1998 8:21 <br>
     */
    DT20("dd MMM yyyy h:mm a");


    // -- Attribute
    private String format;

    // -- Constructor
    DTFormat(String format) {
        this.format = format;
    }

    // -- Getter

    /**
     * @return The String representation of the Format Enum.
     */
    public String getFormatString() {
        return format;
    }
}
