package com.mokalab.butler.util;

import android.util.Log;

/**
 * MrLogger can be used in two ways to Log out messages.<br><br>
 *     1) Statically:
 *          <br><code>
 *          MrLogger.debug("tag", "your cool");
 *          </code><br>
 *     2) Objectively:
 *          <br><code>
 *          MrLogger logger = new MrLogger("tag", true);<br>
 *          logger.debug("your cool");<br>
 *          </code><br>
 * @author pirdad
 */
public class MrLogger {

    private String mTag;
    private boolean mLoggingEnabled = false;

    /**
     * Construct new MrLogger by setting the tag and whether Logging should happen.
     * @param tag tag used for logging
     * @param isLoggingEnabled whether log should happen
     */
    public MrLogger(String tag, boolean isLoggingEnabled) {

        mTag = tag;
        mLoggingEnabled = isLoggingEnabled;
    }

    /**
     * Set to true if should log else false.
     */
    public void setLogEnabled(boolean isLogEnabled) {
        mLoggingEnabled = isLogEnabled;
    }

    /**
     * Show Debug Log only if Logging is enabled.
     */
    public void debug(String text) {

        if (!mLoggingEnabled) return;
        debug(mTag, text);
    }

    /**
     * Show Info in Logs only if Logging is enabled.
     */
    public void info(String text) {

        if (!mLoggingEnabled) return;
        info(mTag, text);
    }

    /**
     * Show Error in Logs only if Logging is enabled.
     */
    public void error(String text) {

        if (!mLoggingEnabled) return;
        error(mTag, text);
    }

    /**
     * Show Debug Log.
     */
    public static void debug(String tag, String text) {
        Log.d(tag, text);
    }

    /**
     * Show Info in Logs.
     */
    public static void info(String tag, String text) {
        Log.i(tag, text);
    }

    /**
     * Show Error in Logs.
     */
    public static void error(String tag, String text) {
        Log.e(tag, text);
    }

    /**
     * Show Debug Log based on shouldLog.
     */
    public static void debug(String tag, String text, boolean shouldLog) {

        if (shouldLog) {
            debug(tag, text);
        }
    }

    /**
     * Show Info in Logs based on shouldLog.
     */
    public static void info(String tag, String text, boolean shouldLog) {

        if (shouldLog) {
            info(tag, text);
        }
    }

    /**
     * Show Error in Logs based on shouldLog.
     */
    public static void error(String tag, String text, boolean shouldLog) {

        if (shouldLog) {
            error(tag, text);
        }
    }
}
