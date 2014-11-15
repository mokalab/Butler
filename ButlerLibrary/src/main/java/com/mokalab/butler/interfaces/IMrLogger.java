package com.mokalab.butler.interfaces;

/**
 * Implement this interface to provide functionality for logging.
 *
 * <br><br>
 * Created by Pirdad S on 2014-08-11.
 */
public interface IMrLogger {

    /**
     * Return a Tag related to the class that implements it.
     */
    public String getLogTag();

    /**
     * Return true to log a message or false otherwise.
     */
    public boolean shouldLog();

    /**
     * Log info message.
     */
    public void info(String message);

    /**
     * Log debug message.
     */
    public void debug(String message);

    /**
     * Log error message.
     */
    public void error(String message);
}
