package com.pudu.slamware_localization;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.pudu.slamware_localization.NetworkManager;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes4.dex */
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
    private OnWifiStateChangedListener mOnWifiStateChangedListener;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes4.dex */
    public interface OnWifiStateChangedListener {
        void onAvailable();

        void onUnavailable();
    }

    public NetworkCallbackImpl(OnWifiStateChangedListener onWifiStateChangedListener) {
        this.mOnWifiStateChangedListener = onWifiStateChangedListener;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        NetworkManager.getInstance().onNetworkAvailable();
        OnWifiStateChangedListener onWifiStateChangedListener = this.mOnWifiStateChangedListener;
        if (onWifiStateChangedListener != null) {
            onWifiStateChangedListener.onAvailable();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        NetworkManager.getInstance().onNetworkUnavailable();
        OnWifiStateChangedListener onWifiStateChangedListener = this.mOnWifiStateChangedListener;
        if (onWifiStateChangedListener != null) {
            onWifiStateChangedListener.onUnavailable();
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        if (networkCapabilities.hasCapability(16)) {
            if (networkCapabilities.hasTransport(1)) {
                NetworkManager.getInstance().updateNetworkType(NetworkManager.NetworkType.Wifi);
            } else if (networkCapabilities.hasTransport(0)) {
                NetworkManager.getInstance().updateNetworkType(NetworkManager.NetworkType.Cellular);
            } else {
                NetworkManager.getInstance().updateNetworkType(NetworkManager.NetworkType.Other);
            }
        }
    }
}
