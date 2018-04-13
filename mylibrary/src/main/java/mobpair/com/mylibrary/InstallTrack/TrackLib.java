package mobpair.com.mylibrary.InstallTrack;

import android.app.Application;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
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
    private String refferer_chk, legacyKey, apiKey;
    private Context context;

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

    public void updateFCMToken(String fcmToken) {
        Log.d(TAG, "Token : " + fcmToken);
        new callapi(fcmToken, legacyKey, apiKey).execute();
    }

    public void legacyKey(String legacyKey) {
        legacyKey = legacyKey;
        Log.d(TAG, "Token : " + legacyKey);
    }

    public void apiKey(String apikey) {
        apikey = apikey;
        Log.d(TAG, "Token : " + apikey);
    }

    private class callapi extends AsyncTask<String, String, String> {
        String token;
        String apikey;
        String legacy;

        public callapi(String token, String apikey, String legacy) {
            this.token = token;
            this.apikey = apikey;
            this.legacy = legacy;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "" + s);
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("mtoken", token);
            hashMap.put("eventid", "INSTALL");
            hashMap.put("deviceid", Util.DeviceId(context));
            hashMap.put("apikey", apikey);
            hashMap.put("legacy", legacy);

            return Util.getResponseofPost("http://technology.makeaff.com:8081/frontend/web/site/track?", hashMap);
        }
    }
}