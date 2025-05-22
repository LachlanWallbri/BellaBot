package com.pudutech.leaselib.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public abstract class NetworkChangeEvent extends BroadcastReceiver {
    public static String TAG = "NetworkChangeEvent";
    public NetworkInfo lastActiveNetworkInfo = null;
    public WifiInfo lastWifiInfo = null;
    public boolean lastConnected = true;

    public abstract void onNetworkChange();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        Log.e(TAG, "Action====" + intent.getAction());
        NetworkInfo networkInfo = null;
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            Log.i(TAG, "getActiveNetworkInfo failed.");
        }
        checkConnInfo(context, networkInfo);
    }

    public void checkConnInfo(Context context, NetworkInfo networkInfo) {
        if (networkInfo == null) {
            this.lastActiveNetworkInfo = null;
            this.lastWifiInfo = null;
            onNetworkChange();
        } else {
            if (networkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTED) {
                if (this.lastConnected) {
                    this.lastActiveNetworkInfo = null;
                    this.lastWifiInfo = null;
                    onNetworkChange();
                }
                this.lastConnected = false;
                return;
            }
            if (isNetworkChange(context, networkInfo)) {
                onNetworkChange();
            }
            this.lastConnected = true;
        }
    }

    public boolean isNetworkChange(Context context, NetworkInfo networkInfo) {
        WifiInfo wifiInfo;
        if (networkInfo.getType() == 1) {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null && (wifiInfo = this.lastWifiInfo) != null && wifiInfo.getBSSID().equals(connectionInfo.getBSSID()) && this.lastWifiInfo.getSSID().equals(connectionInfo.getSSID()) && this.lastWifiInfo.getNetworkId() == connectionInfo.getNetworkId()) {
                Log.w(TAG, "Same Wifi, do not NetworkChanged");
                return false;
            }
            this.lastWifiInfo = connectionInfo;
        } else {
            NetworkInfo networkInfo2 = this.lastActiveNetworkInfo;
            if (networkInfo2 != null && networkInfo2.getExtraInfo() != null && networkInfo.getExtraInfo() != null && this.lastActiveNetworkInfo.getExtraInfo().equals(networkInfo.getExtraInfo()) && this.lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && this.lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                return false;
            }
            NetworkInfo networkInfo3 = this.lastActiveNetworkInfo;
            if (networkInfo3 != null && networkInfo3.getExtraInfo() == null && networkInfo.getExtraInfo() == null && this.lastActiveNetworkInfo.getSubtype() == networkInfo.getSubtype() && this.lastActiveNetworkInfo.getType() == networkInfo.getType()) {
                Log.w(TAG, "Same Network, do not NetworkChanged");
                return false;
            }
        }
        this.lastActiveNetworkInfo = networkInfo;
        return true;
    }
}
