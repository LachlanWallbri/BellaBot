package com.aliyun.alink.linksdk.tmp.network;

import android.content.Context;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.network.NetworkManager;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class NetConnected {
    private static final String TAG = "[Tmp]NetConnected";
    protected static boolean isListening = false;

    public static void init(Context context) {
        NetworkManager.instance().init(context);
    }

    public static void startNetChangeListen() {
        ALog.m479d(TAG, "startNetChangeListen isListening:" + isListening);
        if (isListening) {
            return;
        }
        isListening = true;
        NetworkManager.instance().registerStateChangedListener(new NetworkManager.INetworkListener() { // from class: com.aliyun.alink.linksdk.tmp.network.NetConnected.1
            @Override // com.aliyun.alink.linksdk.tmp.network.NetworkManager.INetworkListener
            public void onNetworkChanged(boolean z, boolean z2) {
                int networkType = NetworkManager.getNetworkType(NetworkManager.instance().getApplicationContext());
                ALog.m479d(NetConnected.TAG, "onNetworkChanged isConnected:" + z + " lastIsConnected:" + z2 + " netType:" + networkType);
                if (!z) {
                    DeviceManager.getInstance().discoverDevices(null, true, 5000L, null);
                    return;
                }
                if (networkType == 1) {
                    DeviceManager.getInstance().discoverDevices(null, 5000L, null);
                } else if (networkType == 0) {
                    DeviceManager.getInstance().clearBasicDataList();
                } else {
                    DeviceManager.getInstance().discoverDevices(null, 5000L, null);
                }
            }
        });
    }
}
