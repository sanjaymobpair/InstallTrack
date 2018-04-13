package mobpair.com.newlibprj;

import android.app.Application;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

/**
 * Created by ${Mobpair} on 29/3/18.
 */
public class myapplicationclass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TrackLib.getInstance().init(this);
    }
}
