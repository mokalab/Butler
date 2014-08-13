package com.mokalab.butler.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class functions as a helper to provide easy
 */
public class JsonUtils {

    private JsonUtils() {}

    /**
     * Get JSONObject from JSONObject by the given key, will return null if unsuccessful.
     */
    public static JSONObject getJsonObject(JSONObject object, String keyToParse, String debugTag, boolean shouldLogInfo) {

        return parseFromJsonObject(object, keyToParse, JSONObject.class, debugTag, shouldLogInfo);
    }

    /**
     * Get JSONObject from JSONObject by the given key, will return null if unsuccessful.
     */
    public static JSONObject getJsonObject(JSONObject object, String keyToParse) {

        return parseFromJsonObject(object, keyToParse, JSONObject.class, "", false);
    }

    /**
     * Get JSONArray from JSONObject by the given key, will return null if unsuccessful.
     */
    public static JSONArray getJsonArray(JSONObject object, String keyToParse, String debugTag, boolean shouldLogInfo) {

        return parseFromJsonObject(object, keyToParse, JSONArray.class, debugTag, shouldLogInfo);
    }

    /**
     * Get JSONArray from JSONObject by the given key, will return null if unsuccessful.
     */
    public static JSONArray getJsonArray(JSONObject object, String keyToParse) {

        return parseFromJsonObject(object, keyToParse, JSONArray.class, "", false);
    }

    /**
     * Get JSONObject from JSONArray by the given index, will return null if unsuccessful.
     */
    public static JSONObject getJsonObjectAt(JSONArray array, int index) {

        try {
            return array.getJSONObject(index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get Integer from JSONArray by the given index, will return -1 if unsuccessful.
     */
    public static int parseIntAt(JSONArray array, int index) {

        try {
            return array.getInt(index);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get String from JSONArray by the given index, will return defaultStr if unsuccessful.
     */
    public static String parseStringAt(JSONArray array, int index, String defaultStr) {

        try {
            String value = array.getString(index);
            if (value == null) {
                return defaultStr;
            } else {
                return value;
            }
        } catch (Exception e) {
            return defaultStr;
        }
    }

    /**
     * Function to parse a specified Return Type 'T' from a valid JSONObject by the specified key.
     * If debug tag is set and debug is allowed, it will log info messages related to the parsing status.
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseFromJsonObject(JSONObject object, String keyToParse, Class<T> returnType, String debugTag, boolean shouldLogInfo) {

        if (object != null && object.has(keyToParse)) {

            try {

                Object obj = object.get(keyToParse);
                if (obj != null && returnType.isAssignableFrom(obj.getClass())) {
                    return (T) obj;
                } else {
                    logInfo(debugTag, shouldLogInfo, "JSONObject '" + keyToParse + "' is not of type " + returnType.getClass()
                            .toString() + ".");
                }

            } catch (JSONException e) {
                logInfo(debugTag, shouldLogInfo, e.getLocalizedMessage());
            }

        } else if (object == null) {
            logInfo(debugTag, shouldLogInfo, "The passed JSONObject is null so can't be parsed.");
        } else if (!object.has(keyToParse)) {
            logInfo(debugTag, shouldLogInfo, "The passed JSONObject doesn't contain key '" + keyToParse + "'.");
        }

        return null;
    }

    /**
     * Function to parse a specified Return Type 'T' from a valid JSONObject by the specified key.
     */
    public static <T> T parseFromJsonObject(JSONObject object, String keyToParse, Class<T> returnType) {

        return parseFromJsonObject(object, keyToParse, returnType, "", false);
    }

    /**
     * Get String from JSONObject by the given key, will return defaultStr if unsuccessful.
     */
    public static String parseString(JSONObject object, String keyToParse, String defaultStr, String debugTag, boolean shouldLogInfo) {

        String string = parseFromJsonObject(object, keyToParse, String.class, debugTag, shouldLogInfo);
        if (string == null) {
            string = defaultStr;
        } else {
            string = string.trim();
        }
        return string;
    }

    /**
     * Get String from JSONObject by the given key, will return defaultStr if unsuccessful.
     */
    public static String parseString(JSONObject object, String keyToParse, String defaultStr) {

        return parseString(object, keyToParse, defaultStr, "", false);
    }

    /**
     * Get Integer from JSONObject by the given key, will return defaultInt if unsuccessful.
     */
    public static int parseInt(JSONObject object, String keyToParse, int defaultInt, String debugTag, boolean shouldLogInfo) {

        Integer intData = parseFromJsonObject(object, keyToParse, Integer.class, debugTag, shouldLogInfo);
        if (intData == null) {
            intData = defaultInt;
        }

        return intData;
    }

    /**
     * Get Integer from JSONObject by the given key, will return defaultInt if unsuccessful.
     */
    public static int parseInt(JSONObject object, String keyToParse, int defaultInt) {

        return parseInt(object, keyToParse, defaultInt, "", false);
    }

    /**
     * Get Float from JSONObject by the given key, will return defaultFloat if unsuccessful.
     */
    public static float parseFloat(JSONObject object, String keyToParse, float defaultFloat, String debugTag, boolean shouldLogInfo) {

        Float floatData = parseFromJsonObject(object, keyToParse, Float.class, debugTag, shouldLogInfo);
        if (floatData == null) {
            floatData = defaultFloat;
        }

        return floatData;
    }

    /**
     * Get Float from JSONObject by the given key, will return defaultFloat if unsuccessful.
     */
    public static float parseFloat(JSONObject object, String keyToParse, float defaultFloat) {

        Float floatData = parseFromJsonObject(object, keyToParse, Float.class, "", false);
        if (floatData == null) {
            floatData = defaultFloat;
        }

        return floatData;
    }

    /**
     * Get Double from JSONObject by the given key, will return defaultDouble if unsuccessful.
     */
    public static double parseDouble(JSONObject object, String keyToParse, double defaultDouble, String debugTag, boolean shouldLogInfo) {

        Double doubleData = parseFromJsonObject(object, keyToParse, Double.class, debugTag, shouldLogInfo);
        if (doubleData == null) {
            doubleData = defaultDouble;
        }

        return doubleData;
    }
    /**
     * Get Double from JSONObject by the given key, will return defaultDouble if unsuccessful.
     */
    public static double parseDouble(JSONObject object, String keyToParse, double defaultDouble) {

        Double doubleData = parseFromJsonObject(object, keyToParse, Double.class, "", false);
        if (doubleData == null) {
            doubleData = defaultDouble;
        }

        return doubleData;
    }

    /**
     * Get Boolean from JSONObject by the given key, will return defaultBoolean if unsuccessful.
     */
    public static boolean parseBoolean(JSONObject object, String keyToParse, boolean defaultBoolean, String debugTag, boolean shouldLogInfo) {

        Boolean boolData = parseFromJsonObject(object, keyToParse, Boolean.class, debugTag, shouldLogInfo);
        if (boolData == null) {
            boolData = defaultBoolean;
        }

        return boolData;
    }

    /**
     * Get Boolean from JSONObject by the given key, will return defaultBoolean if unsuccessful.
     */
    public static boolean parseBoolean(JSONObject object, String keyToParse, boolean defaultBoolean) {

        Boolean boolData = parseFromJsonObject(object, keyToParse, Boolean.class, "", false);
        if (boolData == null) {
            boolData = defaultBoolean;
        }

        return boolData;
    }

    /**
     * Finds a JSONArray based on the provided key and parses it to an ArrayList of String.
     */
    public static ArrayList<String> parseToStringArrayList(JSONObject object, String keyToParse) {

        return parseToStringArrayList(object, keyToParse, "", false);
    }

    /**
     * Finds a JSONArray based on the provided key and parses it to an ArrayList of String.
     */
    public static ArrayList<String> parseToStringArrayList(JSONObject object, String keyToParse, String debugTag,
                                                     boolean shouldLogInfo) {

        ArrayList<String> values = new ArrayList<String>();

        JSONArray jsonArray = getJsonArray(object, keyToParse);
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                String value = parseStringAt(jsonArray, i, null);
                if (value != null) {
                    values.add(value);
                } else {
                    logInfo(debugTag, shouldLogInfo, "String is null at (" + i + ") and wasn't added to the ArrayList of String" +
                            ".");
                }
            }
        }

        return values;
    }

    /**
     * Parse String to JSONObject.
     */
    public static JSONObject convertToJSONObject(String response, String debugTag, boolean shouldLogInfo) {

        if (response != null) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                logInfo(debugTag, shouldLogInfo, "response couldn't be converted to JSONObject. More Info: " + e.getLocalizedMessage());
                return null;
            }

            return jsonObject;

        } else {
            logInfo(debugTag, shouldLogInfo, "String response is null therefore the JSONObject is null.");
            return null;
        }
    }

    /**
     * Parse String to JSONObject.
     */
    public static JSONObject convertToJSONObject(String response) {

        return convertToJSONObject(response, "", false);
    }

    /**
     * Parse String to JSONArray.
     */
    public static JSONArray convertToJSONArray(String response, String debugTag, boolean shouldLogInfo) {

        if (response != null) {

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                logInfo(debugTag, shouldLogInfo, "response couldn't be converted to JSONArray. More Info: " + e.getLocalizedMessage());
                return null;
            }

            return jsonArray;

        } else {
            logInfo(debugTag, shouldLogInfo, "String response is null therefore the JSONArray is null.");
            return null;
        }
    }

    /**
     * Parse String to JSONArray.
     */
    public static JSONArray convertToJSONArray(String response) {

        return convertToJSONArray(response, "", false);
    }

    /**
     * Just logs an info.
     */
    private static void logInfo(String tag, boolean shouldLog, String message) {

        if (shouldLog) {
            MrLogger.info(tag, message);
        }
    }
}
