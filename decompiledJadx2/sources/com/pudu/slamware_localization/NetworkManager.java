package com.pudu.slamware_localization;

import com.pudutech.base.Pdlog;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private NetworkType networkType;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    public enum NetworkType {
        Wifi,
        Cellular,
        Other
    }

    private NetworkManager() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
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
        Pdlog.m3273d(TAG, "onNetworkAvailable()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onNetworkUnavailable() {
        Pdlog.m3273d(TAG, "onNetworkUnavailable()");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateNetworkType(NetworkType networkType) {
        NetworkType networkType2 = this.networkType;
        if (networkType2 != networkType) {
            Pdlog.m3273d(TAG, String.format("NetworkType was updated. last networkType[%s], current networkType[%s]", networkType2, networkType));
            this.networkType = networkType;
        }
    }
}
