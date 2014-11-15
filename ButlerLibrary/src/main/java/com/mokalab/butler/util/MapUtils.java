package com.mokalab.butler.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Pirdad S on 2014-09-16.
 */
public class MapUtils {

    private MapUtils() {}

    /**
     * You can use LatLng to provide latitude and longitude.
     * <br>
     * Starts a External Map intent.
     */
    public static void startExternalMapByGeo(Activity activity, String latitude, String longitude, String searchCriteria) {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:" + latitude + "," + longitude + "?q=" + searchCriteria.replace(" ", "+")));
        activity.startActivity(intent);
    }

    /**
     * You can use LatLng to provide latitude and longitude.
     * <br>
     * Starts a External Map intent.
     */
    public static void startExternalMapUri(Activity activity, String latitude, String longitude, String searchCriteria) {

        // NOT GOING TO USE THIS ONE, INSTEAD THE geo: ONE...
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                latitude + "," + longitude));
        activity.startActivity(intent);
    }
}
