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
        TrackLib.getInstance().serverKey(serverKey);
        TrackLib.getInstance().apiKey("63498e5007e70c082121eda21c696b6b_5ad49ed585c80f14511897eb");
        TrackLib.getInstance().domainEndPoint("");
    }
}
