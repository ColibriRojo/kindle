package com.mydevworks.colibri.kindle;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class NetworkLocationHelper {

    private static boolean listening = false;

    private static LocationManager locationManager = null;

    private static LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.v("NETWORK_LOCATION", location.toString());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public static void startListening(Activity rootActivity) {
        if (locationManager == null) {
            locationManager = (LocationManager) rootActivity.getSystemService(Context.LOCATION_SERVICE);
        }

        if (!listening) {
            if (ContextCompat.checkSelfPermission(rootActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
                listening = true;
            } else {
                // TODO: User doen't have permissions
                Log.e("HELPER", "User hasn't granted use of the location service!");
            }
        }
    }

    public static void stopListening(Activity rootActivity) {
        if (locationManager != null && ContextCompat.checkSelfPermission(rootActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (listening) {
                listening = false;
                locationManager.removeUpdates(locationListener);
            }
        }
    }

    public static void toggleListening(Activity rootActivity) {
        if (listening)
            stopListening(rootActivity);
        else
            startListening(rootActivity);
    }

    public static boolean isListening() {
        return listening;
    }
}
