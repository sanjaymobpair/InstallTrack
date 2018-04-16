package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import mobpair.com.mylibrary.AuthFailureError;
import mobpair.com.mylibrary.DefaultRetryPolicy;
import mobpair.com.mylibrary.NetworkError;
import mobpair.com.mylibrary.ParseError;
import mobpair.com.mylibrary.Request;
import mobpair.com.mylibrary.RequestQueue;
import mobpair.com.mylibrary.Response;
import mobpair.com.mylibrary.RetryPolicy;
import mobpair.com.mylibrary.ServerError;
import mobpair.com.mylibrary.TimeoutError;
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
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static String CURRENT_DATE = "currentdate";
    private static String REFFERER = "refferer", CLICKID = "clickid", FCMTOKEN = "fcmtoken";
    private static String APIKEY = "apikey", SERVERKEY = "serverkey", USERAGENT = "useragent", BOOLEAN = "boolean", ISFIRSTTIME = "isFirst";
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

    private void putBoolean(String name, Boolean value) {
        mPrefs.edit().putBoolean(name, value).apply();
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

    void setBoolean(Boolean value) {
        putBoolean(BOOLEAN, value);
    }

    boolean getBoolean() {
        return mPrefs.getBoolean(BOOLEAN, false);
    }

    void setIsFirstTime(Boolean isFirstTime) {
        putBoolean(ISFIRSTTIME, isFirstTime);
    }

    boolean getIsFirstTime() {
        return mPrefs.getBoolean(ISFIRSTTIME, true);
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

    /**
     * setclick id get from api data
     *
     * @param clickId store clickid
     */
    void setClickId(String clickId) {
        putString(CLICKID, "null");
    }

    String getClickID() {
        return mPrefs.getString(CLICKID, "null");
    }

    // TODO: 16/4/18 set fcm token
    void setFCMToken(String fcmtoken) {
        putString(FCMTOKEN, "null");
    }

    // TODO: 16/4/18 get fcm token
    String getFCMToken() {
        return mPrefs.getString(FCMTOKEN, "null");
    }

    // TODO: 16/4/18 set api key
    void setApiKey(String apikey) {
        putString(APIKEY, "null");
    }

    // TODO: 16/4/18 get api key
    String getApiKey() {
        return mPrefs.getString(APIKEY, "null");
    }

    // TODO: 16/4/18 set server key
    void setServerKey(String serverkey) {
        putString(SERVERKEY, "null");
    }

    // TODO: 16/4/18 get server key
    String getServerKey() {
        return mPrefs.getString(SERVERKEY, "null");
    }

    // TODO: 16/4/18 set useragent
    void setUserAgent(String userAgent) {
        putString(USERAGENT, "null");
    }

    // TODO: 16/4/18 get useragent
    String getUserAgent() {
        return mPrefs.getString(USERAGENT, "null");
    }

    void SendDeviceId(final CallBack volleyCallback) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.start();

        String postURlDeviceId = "http://technology.makeaff.com:8081/frontend/web/site/track";
        StringRequest posStringRequest = new StringRequest(Request.Method.POST, postURlDeviceId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ClickID@@", "Error :  " + response);
                /*try {
                    JSONObject obj = new JSONObject(response);
                    String ClickId = obj.getString("clickid");
                    Log.d("ClickID@@", "" + ClickId);
                    volleyCallback.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyCallback.onError(String.valueOf(volleyError));
                Log.d("ClickID@@", "Error :  " + volleyError);
                String message = null;
                if (volleyError instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (volleyError instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (volleyError instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (volleyError instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }
                Log.d(TAG, "" + message);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("Content-Type", "text/html");
                stringMap.put("mtoken", "dfd8PvCg-34:APA91bEMXuHEbCobUorCp0ZyEIgnqOuUT00u3WBw__Cl7-N9LYp_SSJjmGfhFXxOUoy-uvo0OD3D1N0n65-X_4lPsn3JOJTltyyTue0WLJwGQyLJ5QZvFjy-ze-xxhabqbs7swlOV7f7");
                stringMap.put("eventid", "INSTALL");
                stringMap.put("deviceid", DeviceId(mContext));
                stringMap.put("apikey", "dda15837a386ce7a5b7e3f99fefa3394_5acda0ab85c80f0af969fc8e");
                stringMap.put("legacy", "AAAAYPhe458:APA91bEjDh0sBtjxcAB0pLTD11e88i1D4hiR5GQO9bOLnlxFKQuN5rLL2K4QiS8Jv0PTZ17DBdSRZb9nnIjXsXxAKwXwvG3OXSO6DkaxPrBaxvp-vs_OLrCuXCXn2LAKGY3_T9UU9W-A");
                Log.d(TAG, ": PARA : " + stringMap);
                return stringMap;
            }
        };

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        posStringRequest.setRetryPolicy(policy);
        posStringRequest.setShouldCache(false);
        requestQueue.add(posStringRequest);
        requestQueue.start();
    }

    @SuppressLint({"ObsoleteSdkInt", "HardwareIds"})
    public static String DeviceId(Context context) {
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


    public static String getResponseofPost(String URL, HashMap<String, String> postDataParams) {
        java.net.URL url;
        String response = "";
        try {
            url = new URL(URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            Log.d("URL - ResponseCode", URL + " - " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
            Log.d("jai", "response :" + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String getResponseofGet(String URL) {
        URL url;
        StringBuilder response = new StringBuilder();
        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            url = new URL(URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            Log.d("URL - ResponseCode", URL + " - " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            } else {
                response = new StringBuilder();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("jai", "response :" + response);
        return response.toString();
    }

    private static String getPostDataString(HashMap<String, String> params) throws
            UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }


    public static class callapi extends AsyncTask<String, String, String> {
        String token, apikey, serverkey, usergent, refferer;

        public callapi(String token, String apikey, String serverkey, String userAgent, String refferer) {
            this.token = token;
            this.apikey = apikey;
            this.serverkey = serverkey;
            this.usergent = userAgent;
            this.refferer = refferer;
        }

        @Override
        protected void onPreExecute() {
            Log.d("Util", "" + usergent);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Util", "Response : " + s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String message = jsonObject.getString("message");
                Boolean response = jsonObject.getBoolean("response");
                String data = jsonObject.getString("data");
                Util util = new Util(mContext);
                util.setClickId(data);
                util.setBoolean(response);
                Log.d("Util", "Response ParaMeter : " + message + ":" + response + ":" + data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("mtoken", token);
            hashMap.put("eventid", "INSTALL");
            hashMap.put("deviceid", Util.DeviceId(mContext));
            hashMap.put("apikey", apikey);
            hashMap.put("legacy", serverkey);
            hashMap.put("useragent", usergent);
            hashMap.put("refferer", refferer);
            Log.d("Util", "HashMap " + hashMap.toString());
            String url = Util.getResponseofPost("http://technology.makeaff.com:8081/frontend/web/site/track?", hashMap);
            Log.d("Util", "Url " + url);
            return url;
        }
    }
}