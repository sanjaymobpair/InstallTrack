package mobpair.com.newlibprj;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

/**
 * Created by ${Mobpair} on 9/4/18.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        Log.d(TAG, "Message Notification Body: " + Objects.requireNonNull(remoteMessage.getNotification()).getBody());

        super.onMessageReceived(remoteMessage);
    }
}
