package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${Mobpair} on 7/3/18.
 */

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ApplicationLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private static final String TAG = ApplicationLifecycleHandler.class.getName();
    private static boolean isInBackground = true;
    private String getDatePref;
    private Util util;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        util = new Util(activity);
        Toast.makeText(activity, "onActivityCreated", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onActivityCreated:" + getDatePref);

        if (InternetConnectionClass.getInstance(activity).isOnline()) {
            Log.d(TAG, ":IF");
            Boolean res = util.getErrorResponse();
            String apiKey = util.getApiKey();
            String serverKey = util.getServerKey();
            String userAgent = util.getUserAgent();
            String refferer_chk = util.getRefferer();

            Log.d(TAG, "Boolean" + res);
            if (res) {
                Log.d(TAG, "If : True");
                new Util.callapi(util.getFCMToken(), apiKey, serverKey, userAgent, refferer_chk, "INSTALL").execute();
            } else {
                Log.d(TAG, "If : False");
            }
        } else {
            Log.d(TAG, ":ELSE");
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Toast.makeText(activity, "onActivityResumed", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onActivityResumed");
        Log.d(TAG, "onActivityResumed :" + getDatePref);
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(date);

        Toast.makeText(activity, "" + util.getCurrentDate(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Date" + util.getCurrentDate());
        getDatePref = util.getCurrentDate();

        if (isInBackground) {
            if (getDatePref.equalsIgnoreCase(formattedDate)) {
                Toast.makeText(activity, "onActivityResumed : Equals  ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onActivityResumed : Equals");
            } else {
                boolean isFirstTime = util.getIsFirstTime();

                Log.d(TAG, "isFirst" + isFirstTime);
                if (isFirstTime) {
                    Log.d(TAG, "isFirst : If");
                } else {
                    Boolean isInstall = util.getBoolean();

                    Log.d(TAG, "isFirst : Else" + isInstall);
                    String fcmtoken, serverkey, apikey, useragent, clickId, eventId = "ACTIVE";

                    fcmtoken = util.getFCMToken();
                    serverkey = util.getServerKey();
                    apikey = util.getApiKey();
                    useragent = util.getUserAgent();
                    clickId = util.getClickID();
                    util.setCurrentDate(formattedDate);

                    if (isInstall) {
                        new Util.callapi(fcmtoken, apikey, serverkey, useragent, clickId, eventId).execute();
                    } else {

                    }
                    Toast.makeText(activity, "onActivityResumed : NotEquals", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onActivityResumed : NotEquals");
                }

            }
            Log.d(TAG, "app went to foreground");
            isInBackground = false;
        } else {
            Log.d(TAG, "onActivityResumed : Null");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onTrimMemory(int i) {
        Log.d(TAG, "onTrimMemory");
        if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Log.d(TAG, "app went to background");
            isInBackground = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onLowMemory() {

    }
}