package mobpair.com.mylibrary.InstallTrack;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ${Mobpair} on 6/3/18.
 */

public class InstallReferrerReceiver extends BroadcastReceiver {
    private String TAG = "InstallReferrerReceiver";

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    public void onReceive(Context context, Intent intent) {
        TrackLib.getInstance().onReceive(context, intent);
        Log.d(TAG, "refferer");
    }
}