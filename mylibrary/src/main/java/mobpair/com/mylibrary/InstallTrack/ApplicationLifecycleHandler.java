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
    private String formattedDate;
    private Util util;

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        util = new Util(activity);

      /*  Log.d(TAG, "onActivityCreated:" + getDatePref);
        if (getDatePref.equalsIgnoreCase(formattedDate)) {
            Log.d(TAG, "onActivityCreated : Equals");
        } else {
            util.setCurrentDate(formattedDate);
            Log.d(TAG, "onActivityCreated : NotEquals");
        }*/
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "onActivityResumed");
        Log.d(TAG, "onActivityResumed :" + getDatePref);
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(date);

        Log.d(TAG, "Date" + util.getCurrentDate());
        getDatePref = util.getCurrentDate();

        if (isInBackground) {
            if (getDatePref.equalsIgnoreCase(formattedDate)) {
                Log.d(TAG, "onActivityResumed : Equals");
            } else {
                util.setCurrentDate(formattedDate);
                Log.d(TAG, "onActivityResumed : NotEquals");
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