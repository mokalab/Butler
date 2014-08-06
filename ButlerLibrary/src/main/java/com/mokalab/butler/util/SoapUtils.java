package com.mokalab.butler.util;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

/**
 * TODO: JAVA DOC
 * Contains helper/utility functions related to Soap Request.
 * Created by Pirdad S on 2014-08-06.
 */
public class SoapUtils {
    
    private SoapUtils() {}

    public static <T> T parsePropertyFromSoapObject(SoapObject object, String keyToParse, Class<T> type, String debugTag, 
                                                       boolean shouldLog) {

        if (object != null && object.hasProperty(keyToParse)) {

            Object obj = object.getProperty(keyToParse);
            if (obj != null && type.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            } else {
                MrLogger.debug(debugTag, "SoapObject 'object': '" + keyToParse + "' is not of type " + type.getClass().toString
                        (), shouldLog);
            }

        } else if (object == null) {
            MrLogger.debug(debugTag, "SoapObject 'object': is null", shouldLog);
        } else if (!object.hasProperty(keyToParse)) {
            MrLogger.debug(debugTag, "SoapObject 'object': doesn't contain key '" + keyToParse + "'", shouldLog);
        }

        return null;
    }

    public static <T> T parsePropertyFromSoapObject(SoapObject object, String keyToParse, Class<T> type) {

        return parsePropertyFromSoapObject(object, keyToParse, type, "", false);
    }

    public static <T> T parsePropertyFromSoapObject(SoapObject object, int index, Class<T> type, String debugTag, 
                                                       boolean shouldLog) {

        if (object != null && object.getPropertyCount() > index) {

            Object obj = object.getProperty(index);
            if (obj != null && type.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            } else {
                MrLogger.debug(debugTag, "SoapObject 'object': '" + index + "' is not of type " + type.getClass().toString(), 
                        shouldLog);
            }

        } else if (object == null) {
            MrLogger.debug(debugTag, "SoapObject 'object': is null", shouldLog);
        }

        return null;
    }

    public static <T> T parsePropertyFromSoapObject(SoapObject object, int index, Class<T> type) {

        return parsePropertyFromSoapObject(object, index, type, "", false);
    }

    public static <T> T parseAttributeFromSoapObject(SoapObject object, String keyToParse, Class<T> type, String debugTag, 
                                                        boolean shouldLog) {

        if (object != null && object.hasAttribute(keyToParse)) {

            Object obj = object.getAttribute(keyToParse);
            if (obj != null && type.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            } else {
                MrLogger.debug(debugTag, "SoapObject 'object': '" + keyToParse + "' is not of type " + type.getClass().toString(), shouldLog);
            }

        } else if (object == null) {
            MrLogger.debug(debugTag, "SoapObject 'object': is null", shouldLog);
        } else if (!object.hasAttribute(keyToParse)) {
            MrLogger.debug(debugTag, "SoapObject 'object': doesn't contain key '" + keyToParse + "'", shouldLog);
        }

        return null;
    }

    public static <T> T parseAttributeFromSoapObject(SoapObject object, String keyToParse, Class<T> type) {

        return parseAttributeFromSoapObject(object, keyToParse, type, "", false);
    }

    public static <T> T parseAttributeFromSoapObject(SoapPrimitive object, String keyToParse, Class<T> type, String debugTag,
                                                        boolean shouldLog) {

        if (object != null && object.hasAttribute(keyToParse)) {

            Object obj = object.getAttribute(keyToParse);
            if (obj != null && type.isAssignableFrom(obj.getClass())) {
                return (T) obj;
            } else {
                MrLogger.debug(debugTag, "SoapPrimitive 'object': '" + keyToParse + "' is not of type " + type.getClass()
                        .toString(), shouldLog);
            }

        } else if (object == null) {
            MrLogger.debug(debugTag, "SoapPrimitive 'object': is null", shouldLog);
        } else if (!object.hasAttribute(keyToParse)) {
            MrLogger.debug(debugTag, "SoapPrimitive 'object': doesn't contain key '" + keyToParse + "'", shouldLog);
        }

        return null;
    }

    public static <T> T parseAttributeFromSoapObject(SoapPrimitive object, String keyToParse, Class<T> type) {

        return parseAttributeFromSoapObject(object, keyToParse, type, "", false);
    }
}
