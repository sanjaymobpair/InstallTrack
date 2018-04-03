package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mobpair.com.mylibrary.AuthFailureError;
import mobpair.com.mylibrary.DefaultRetryPolicy;
import mobpair.com.mylibrary.Request;
import mobpair.com.mylibrary.RequestQueue;
import mobpair.com.mylibrary.Response;
import mobpair.com.mylibrary.RetryPolicy;
import mobpair.com.mylibrary.VolleyError;
import mobpair.com.mylibrary.toolbox.StringRequest;
import mobpair.com.mylibrary.toolbox.Volley;

/**
 * Created by ${Mobpair} on 6/3/18.
 */

/**
 * {@link Util class is used to store sharedpreference }
 */
class Util {
    private Context mContext;
    private static String CURRENT_DATE = "currentdate";
    private static String REFFERER = "refferer";
    private final SharedPreferences mPrefs;
    private static final String PREFERENCES = "settings";
    private String TAG = Util.class.getName();

    /**
     * @param context context of used class
     */
    Util(final Context context) {
        mPrefs = context.getSharedPreferences(PREFERENCES, 0);
        mContext = context;
    }

    /**
     * putString method is used for store string value preference
     *
     * @param name
     * @param value
     */
    private void putString(String name, String value) {
        mPrefs.edit().putString(name, value).apply();
    }

    /**
     * set Current Date in preference
     *
     * @param date date you want to store
     */
    void setCurrentDate(String date) {
        putString(CURRENT_DATE, date);
    }

    /**
     * Get Date Stored in preference
     *
     * @return return current date stored in preference
     */

    String getCurrentDate() {
        return mPrefs.getString(CURRENT_DATE, "null");
    }

    /**
     * for store refferer in preference
     *
     * @param refferer value of refferer
     */
    void setRefferer(String refferer) {
        putString(REFFERER, refferer);
    }

    String getRefferer() {
        return mPrefs.getString(REFFERER, "null");
    }

    void SendDeviceId(final CallBack volleyCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.start();

        String postURlDeviceId = "https://tr.adsx.bid/affapi.php";
        StringRequest posStringRequest = new StringRequest(Request.Method.POST, postURlDeviceId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String ClickId = obj.getString("clickid");
                    Log.d("ClickID@@", "" + ClickId);
                    volleyCallback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallback.onError(String.valueOf(error));
                Log.d("ClickID@@", "Error :  " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("click", "Y");
                stringMap.put("nc", "1");
                stringMap.put("user_id", "41");
                stringMap.put("offer_id", "942");
                stringMap.put("format", "JSON");
                stringMap.put("track1", DeviceId(mContext));
                Log.d(TAG, ": PARA : " + stringMap);
                return stringMap;
            }
        };

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        posStringRequest.setRetryPolicy(policy);
        posStringRequest.setShouldCache(false);
        requestQueue.add(posStringRequest);
    }

    @SuppressLint({"ObsoleteSdkInt", "HardwareIds"})
    private static String DeviceId(Context context) {
        @SuppressLint("HardwareIds")
        String ANDROID_ID = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return ANDROID_ID;
    }

    // TODO: 20/2/18 Create interface of volley response
    public interface CallBack {
        void onSuccess(String result);

        void onError(String error);
    }
}