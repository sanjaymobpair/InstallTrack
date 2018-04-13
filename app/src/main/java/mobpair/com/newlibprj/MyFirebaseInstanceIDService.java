package mobpair.com.newlibprj;

/**
 * Created by ${Mobpair} on 12/3/18.
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        TrackLib.getInstance().updateFCMToken(refreshedToken);
    }
}