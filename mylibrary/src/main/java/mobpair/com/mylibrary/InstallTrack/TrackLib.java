package mobpair.com.mylibrary.InstallTrack;

import android.app.Application;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TabHost;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ${Mobpair} on 21/3/18.
 */

public class TrackLib {
    private String TAG = TrackLib.class.getName();
    private static TrackLib instance = new TrackLib();
    private Util util;
    private String refferer_chk, serverKey, apiKey, fcmToken;
    private Context context;
    private String userAgent;

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
    }

    public void init(Application application) {
        util = new Util(application);
        context = application;
        Log.d(TAG, "Init : ServerKey" + serverKey + "ApiKey :" + apiKey + "FcmToken" + fcmToken);
        userAgent = new WebView(application).getSettings().getUserAgentString();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(application));
        if (util.getRefferer() != null) {
            refferer_chk = util.getRefferer();
        }
        if (refferer_chk != null) {
            Log.d(TAG, "If : Refferer" + refferer_chk);
        } else {
            Log.d(TAG, "Else : Null Refferer");
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

        Util util = new Util(application);
        /*util.SendDeviceId(new Util.CallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("RESULT@@", "" + result);
            }

            @Override
            public void onError(String error) {
                Log.d("RESULT@@", "Error" + error);
            }
        });*/
    }

    public void updateFCMToken(String fcmtoken) {
        util.setIsFirstTime(false);
        util.setFCMToken(fcmToken);
        fcmToken = fcmtoken;
        Log.d(TAG, "Token1 : " + fcmToken);
        Log.d(TAG, "Token1 : " + serverKey);
        Log.d(TAG, "Token1 : " + apiKey);
        Log.d(TAG, "Token1 : " + refferer_chk);
        Log.d(TAG, "user : " + userAgent);

        Boolean res = util.getBoolean();
        Log.d(TAG, "Boolean" + res);
        if (res) {

        } else {
            new Util.callapi(fcmToken, apiKey, serverKey, userAgent, refferer_chk).execute();
        }
    }

    public void serverKey(String serverkey) {
        util.setServerKey(serverkey);
        serverKey = serverkey;
        Log.d(TAG, "Token : " + serverkey);
    }

    public void apiKey(String apikey) {
        util.setApiKey(apiKey);
        apiKey = apikey;
        Log.d(TAG, "Token : " + apikey);
    }
}