package com.pudutech.remotemaintenance.net;

import android.util.Log;
import com.pudutech.remotemaintenance.IoTManagerFactory;

/* loaded from: classes.dex */
class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private NetworkType networkType;

    /* loaded from: classes.dex */
    public enum NetworkType {
        Wifi,
        Cellular,
        Other
    }

    private NetworkManager() {
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static final NetworkManager INSTANCE = new NetworkManager();

        private SingletonHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NetworkManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNetworkAvailable() {
        Log.d(TAG, "onNetworkAvailable()");
        IoTManagerFactory.INSTANCE.getIoTManager().setNetworkAvailable(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNetworkUnavailable() {
        Log.d(TAG, "onNetworkUnavailable()");
        IoTManagerFactory.INSTANCE.getIoTManager().setNetworkAvailable(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateNetworkType(NetworkType networkType) {
        NetworkType networkType2 = this.networkType;
        if (networkType2 != networkType) {
            Log.d(TAG, String.format("NetworkType was updated. last networkType[%s], current networkType[%s]", networkType2, networkType));
            this.networkType = networkType;
        }
    }
}
