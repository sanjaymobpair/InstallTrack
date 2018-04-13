package mobpair.com.newlibprj;

import android.app.Application;

import mobpair.com.mylibrary.InstallTrack.TrackLib;

/**
 * Created by ${Mobpair} on 29/3/18.
 */
public class myapplicationclass extends Application {
    String serverKey = "AAAAb_umDsM:APA91bHbTsKqQUvzxJYXDXzQNFmN-myD165hmT6ROC-3FDg0DFwpa80CM2PcBmI_uUoch5v2GL2490_CkyMcBj3QVHyzBwV69XHMUZW-sCTpSgALCdUfJILn2WmYCR_wbq-oGvjHVjqF";

    @Override
    public void onCreate() {
        super.onCreate();
        TrackLib.getInstance().init(this);
        TrackLib.getInstance().legacyKey(serverKey);
        TrackLib.getInstance().apiKey("apikey");
    }
}
