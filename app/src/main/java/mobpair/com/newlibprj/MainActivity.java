package mobpair.com.newlibprj;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class MainActivity extends Activity {
    String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userAgent = new WebView(this).getSettings().getUserAgentString();
        String deviceName = android.os.Build.MODEL;
        String deviceMan = android.os.Build.MANUFACTURER;

        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(browserIntent, PackageManager.MATCH_DEFAULT_ONLY);
        String packageName = resolveInfo.activityInfo.packageName;

        Log.d(TAG, "UserAgent : " + userAgent + "DeviceName : " + deviceName + " = DeviceMan : " + deviceMan + " = PackageName : " + packageName);

        String manufacturer = Build.MANUFACTURER;

        String model = Build.MODEL + " " + android.os.Build.BRAND + " ("
                + android.os.Build.VERSION.RELEASE + ")"
                + " API-" + android.os.Build.VERSION.SDK_INT;

        if (model.startsWith(manufacturer)) {
            Log.d(TAG, "IF::" + model);
            //return capitalize(model);
        } else {
            Log.d(TAG, "Else::" + model);
            //return capitalize(manufacturer) + " " + model;
        }
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("MissingPermission")
            String build = Build.getSerial();
            Log.d(TAG, "" + build);
        }*/
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
