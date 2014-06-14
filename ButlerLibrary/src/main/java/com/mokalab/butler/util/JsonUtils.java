package com.mokalab.butler.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by work on 2014-06-13.
 */
public class JsonUtils {

    /**
     * Parse String to JSONObject.<br></br>
     *
     * @param response
     * @return JSONObject
     */
    public static JSONObject convertToJSONObject(String response) {

        if (response != null) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return jsonObject;

        } else {
            return null;
        }
    }

    /**
     * Parse String to JSONArray.<br></br>
     *
     * @param response
     * @return JSONArray
     */
    public static JSONArray convertToJSONArray(String response) {

        if (response != null) {

            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

            return jsonArray;

        } else {
            return null;
        }
    }

    private static <T> T parseFromJsonObject(JSONObject object, String keyToParse, Class<T> type, String debugTag, boolean shouldLogInfo) {

        if (object != null && object.has(keyToParse)) {

            try {

                Object obj = object.get(keyToParse);
                if (obj != null && type.isAssignableFrom(obj.getClass())) {
                    return (T) obj;
                } else {
                    MrLogger.info(debugTag, "JSONObject 'object': '" + keyToParse + "' is not of type " + type.getClass().toString());
                    return null;
                }


            } catch (JSONException e) {
                e.printStackTrace();
                MrLogger.info(debugTag, "JSONObject 'object': doesn't contain key '" + keyToParse + "'");
            }

        } else if (object == null) {
            MrLogger.error(debugTag, "JSONObject 'object' passed is null so can't be parsed");
        } else if (!object.has(keyToParse)) {
            MrLogger.info(debugTag, "JSONObject 'object': doesn't contain key '" + keyToParse + "'");
        }

        return null;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @param defaultStr
     * @return String
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
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @param defaultInt
     * @return int
     */
    public static int parseInt(JSONObject object, String keyToParse, int defaultInt, String debugTag, boolean shouldLogInfo) {

        Integer intData = parseFromJsonObject(object, keyToParse, Integer.class, debugTag, shouldLogInfo);
        if (intData == null) {
            intData = defaultInt;
        }

        return intData;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @param defaultFloat
     * @return float
     */
    public static float parseFloat(JSONObject object, String keyToParse, float defaultFloat, String debugTag, boolean shouldLogInfo) {

        Float floatData = parseFromJsonObject(object, keyToParse, Float.class, debugTag, shouldLogInfo);
        if (floatData == null) {
            floatData = defaultFloat;
        }

        return floatData;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @param defaultDouble
     * @return double
     */
    public static double parseDouble(JSONObject object, String keyToParse, double defaultDouble, String debugTag, boolean shouldLogInfo) {

        Double doubleData = parseFromJsonObject(object, keyToParse, Double.class, debugTag, shouldLogInfo);
        if (doubleData == null) {
            doubleData = defaultDouble;
        }

        return doubleData;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @param defaultBoolean
     * @return boolean
     */
    public static boolean parseBoolean(JSONObject object, String keyToParse, boolean defaultBoolean, String debugTag, boolean shouldLogInfo) {

        Boolean boolData = parseFromJsonObject(object, keyToParse, Boolean.class, debugTag, shouldLogInfo);
        if (boolData == null) {
            boolData = defaultBoolean;
        }

        return boolData;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @return JSONObject
     */
    public static JSONObject getJsonObject(JSONObject object, String keyToParse, String debugTag, boolean shouldLogInfo) {

        JSONObject jsonObj = parseFromJsonObject(object, keyToParse, JSONObject.class, debugTag, shouldLogInfo);
        return jsonObj;
    }

    /**
     * Get value from JSONObject given key, will return defaultStr if unsuccessful.<br></br>
     *
     * @param object
     * @param keyToParse
     * @return JSONArray
     */
    public static JSONArray getJsonArray(JSONObject object, String keyToParse, String debugTag, boolean shouldLogInfo) {

        JSONArray jsonArr = parseFromJsonObject(object, keyToParse, JSONArray.class, debugTag, shouldLogInfo);
        return jsonArr;
    }

    /**
     * Get value from JSONArray given index, will return defaultStr if unsuccessful.<br></br>
     *
     * @param array
     * @param index
     * @return JSONObject
     */
    public static JSONObject getJsonObjectForIndex(JSONArray array, int index) {

        try {
            return array.getJSONObject(index);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get value from JSONArray given index, will return defaultStr if unsuccessful.<br></br>
     *
     * @param array
     * @param index
     * @return int
     */
    public static int getIntForIndex(JSONArray array, int index) {

        try {
            return array.getInt(index);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get value from JSONArray given index, will return defaultStr if unsuccessful.<br></br>
     *
     * @param array
     * @param index
     * @return String
     */
    public static String getStringForIndex(JSONArray array, int index) {

        try {
            return array.getString(index);
        } catch (Exception e) {
            return "";
        }
    }
}
