package mobpair.com.newlibprj;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import mobpair.com.mylibrary.AuthFailureError;
import mobpair.com.mylibrary.DefaultRetryPolicy;
import mobpair.com.mylibrary.NetworkError;
import mobpair.com.mylibrary.NoConnectionError;
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

public class MainActivity extends Activity {
    String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* SendDeviceId(new CallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "Error :  " + result);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "Error :  " + error);
            }
        });*/
    }


    public interface CallBack {
        void onSuccess(String result);

        void onError(String error);
    }
}
