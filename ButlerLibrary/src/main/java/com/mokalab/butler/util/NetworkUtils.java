package com.mokalab.butler.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by work on 2014-06-13.
 */
public class NetworkUtils {

    /**
     * Checks for network connection. Good to use before execution of request.
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        if (context == null) return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean available = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        return available;
    }
}
