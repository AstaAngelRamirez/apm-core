package com.apm.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ing. Oscar G. Medina Cruz on 03/07/17.
 *
 * Handle network functions
 */

public class NetworkUtils {

    /**
     * Check if device has an internet connection
     *
     * @param context current application context
     * @return true if device has an internet connection
     */
    public static boolean HasInternetConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE
                    || activeNetwork.getType() == ConnectivityManager.TYPE_BLUETOOTH)
                return HasInternetAccess();
        }
        return false;
    }

    /**
     * Get current internet connection type
     *
     * @param context   application context
     * @return          connection type {@link ConnectivityManager} integer
     */
    public static int GetInternetConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return ConnectivityManager.TYPE_WIFI;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return ConnectivityManager.TYPE_MOBILE;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_BLUETOOTH)
                return ConnectivityManager.TYPE_BLUETOOTH;
        }
        return -1;
    }

    /**
     * Extra check of connection state
     *
     * @return true if device has internet access
     */
    private static boolean HasInternetAccess() {
        try {
            URL url = new URL("http://www.google.com");

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application:1");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 30);
            urlc.connect();

            // http://www.w3.org/Protocols/HTTP/HTRESP.html
            if (urlc.getResponseCode() == 200 || urlc.getResponseCode() > 400) {
                // Requested site is available
                return true;
            }
        } catch (Exception ex) {
            // Error while trying to connect
            return false;
        }
        return false;
    }
}
