package com.pudutech.voiceinteraction.component.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkStateReceive.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/NetworkStateReceive;", "Landroid/content/BroadcastReceiver;", "()V", "ACTION_SIM_STATE_CHANGED", "", "networkStateCallback", "Lcom/pudutech/voiceinteraction/component/utils/NetworkStateReceive$NetworkStateCallback;", "isWifiConnected", "", "context", "Landroid/content/Context;", "onReceive", "", "intent", "Landroid/content/Intent;", "setNetworkStateCallback", "callback", "NetworkStateCallback", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class NetworkStateReceive extends BroadcastReceiver {
    private final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    private NetworkStateCallback networkStateCallback;

    /* compiled from: NetworkStateReceive.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/NetworkStateReceive$NetworkStateCallback;", "", "onNetWorkStateChanged", "", "isConnet", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface NetworkStateCallback {
        void onNetWorkStateChanged(boolean isConnet);
    }

    public final void setNetworkStateCallback(NetworkStateCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.networkStateCallback = callback;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkStateCallback networkStateCallback;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if ((Intrinsics.areEqual(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE") || Intrinsics.areEqual(intent.getAction(), "android.net.wifi.STATE_CHANGE") || Intrinsics.areEqual(intent.getAction(), "android.net.wifi.RSSI_CHANGED") || Intrinsics.areEqual(intent.getAction(), "android.net.wifi.WIFI_STATE_CHANGED")) && (networkStateCallback = this.networkStateCallback) != null) {
            networkStateCallback.onNetWorkStateChanged(isWifiConnected(context));
        }
    }

    public final boolean isWifiConnected(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        connectivityManager.getNetworkCapabilities(connectivityManager != null ? connectivityManager.getActiveNetwork() : null);
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        Intrinsics.checkExpressionValueIsNotNull(allNetworkInfo, "conMan.allNetworkInfo");
        boolean z = false;
        for (NetworkInfo it : allNetworkInfo) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (it.isConnected()) {
                z = true;
            }
        }
        return z;
    }
}
