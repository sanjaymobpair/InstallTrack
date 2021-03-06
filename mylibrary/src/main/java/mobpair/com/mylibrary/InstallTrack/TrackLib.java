package mobpair.com.mylibrary.InstallTrack;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by ${Mobpair} on 21/3/18.
 */

public class TrackLib {
    private String TAG = TrackLib.class.getName();
    private static TrackLib instance = new TrackLib();
    private Util util;
    private String refferer_chk;

    public static TrackLib getInstance() {
        return instance;
    }

    void onReceive(Context context, Intent intent) {
        String REFFERER_VALUE = "referrer";
        String referrer = intent.getStringExtra(REFFERER_VALUE);

        if (referrer != null) {
            util = new Util(context);
            util.setRefferer(referrer);
            Log.d(TAG, "refferer : " + referrer);
        } else {
            Log.d(TAG, "refferer : Else");
        }
        Util util = new Util(context);
        util.SendDeviceId(new Util.CallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("RESULT@@", "" + result);
            }

            @Override
            public void onError(String error) {
                Log.d("RESULT@@", "Error" + error);
            }
        });
    }

    public void init(Application application) {
        util = new Util(application);
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(application));
        if (util.getRefferer() != null) {
            refferer_chk = util.getRefferer();
        }
        if (refferer_chk != null) {
        } else {
        }

        ApplicationLifecycleHandler handler = new ApplicationLifecycleHandler();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(handler);
            application.registerComponentCallbacks(handler);
        }

        if (InternetConnectionClass.getInstance(application).isOnline()) {
            Log.d(TAG, ":IF");
        } else {
            Log.d(TAG, ":ELSE");
        }
    }

    public void updateFCMToken(String fcmToken) {
        Log.d(TAG, "Token : " + fcmToken);
    }

    public void addLegacyKey(String legacykey) {
        Log.d(TAG, "Key : " + legacykey);
    }
}