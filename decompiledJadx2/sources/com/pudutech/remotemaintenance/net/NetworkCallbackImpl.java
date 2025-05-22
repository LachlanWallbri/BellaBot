package com.pudutech.remotemaintenance.net;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.pudutech.remotemaintenance.net.NetworkManager;

/* loaded from: classes.dex */
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        NetworkManager.getInstance().onNetworkAvailable();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        NetworkManager.getInstance().onNetworkUnavailable();
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
