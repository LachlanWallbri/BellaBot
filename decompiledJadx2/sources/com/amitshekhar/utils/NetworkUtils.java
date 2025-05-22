package com.amitshekhar.utils;

import android.content.Context;
import android.net.wifi.WifiManager;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class NetworkUtils {
    private NetworkUtils() {
    }

    public static String getAddressLog(Context context, int i) {
        int ipAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        return "Open http://" + String.format("%d.%d.%d.%d", Integer.valueOf(ipAddress & 255), Integer.valueOf((ipAddress >> 8) & 255), Integer.valueOf((ipAddress >> 16) & 255), Integer.valueOf((ipAddress >> 24) & 255)) + ":" + i + " in your browser";
    }
}
