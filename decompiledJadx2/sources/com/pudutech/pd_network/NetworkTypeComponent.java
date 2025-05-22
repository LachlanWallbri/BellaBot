package com.pudutech.pd_network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import com.pudutech.pd_network.PdNetworkType;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkTypeComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rJ\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/pd_network/NetworkTypeComponent;", "Lcom/pudutech/pd_network/INetworkType;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "connManager", "Landroid/net/ConnectivityManager;", "getConnManager", "()Landroid/net/ConnectivityManager;", "setConnManager", "(Landroid/net/ConnectivityManager;)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "init", "", "context", "networkType", "Lcom/pudutech/pd_network/PdNetworkType;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NetworkTypeComponent implements INetworkType {
    public static final NetworkTypeComponent INSTANCE;
    private static final String TAG;
    public static ConnectivityManager connManager;
    public static Context mContext;

    static {
        NetworkTypeComponent networkTypeComponent = new NetworkTypeComponent();
        INSTANCE = networkTypeComponent;
        TAG = networkTypeComponent.getClass().getSimpleName();
    }

    private NetworkTypeComponent() {
    }

    public final ConnectivityManager getConnManager() {
        ConnectivityManager connectivityManager = connManager;
        if (connectivityManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connManager");
        }
        return connectivityManager;
    }

    public final void setConnManager(ConnectivityManager connectivityManager) {
        Intrinsics.checkParameterIsNotNull(connectivityManager, "<set-?>");
        connManager = connectivityManager;
    }

    public final Context getMContext() {
        Context context = mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        return context;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        mContext = context;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        mContext = applicationContext;
        Context context2 = mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        Object systemService = context2.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        connManager = (ConnectivityManager) systemService;
    }

    @Override // com.pudutech.pd_network.INetworkType
    public PdNetworkType networkType() {
        PdNetworkType.Connect connect;
        if (Build.VERSION.SDK_INT < 23) {
            ConnectivityManager connectivityManager = connManager;
            if (connectivityManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connManager");
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return PdNetworkType.None.INSTANCE;
            }
            Intrinsics.checkExpressionValueIsNotNull(activeNetworkInfo, "connManager.activeNetwor…return PdNetworkType.None");
            int type = activeNetworkInfo.getType();
            if (activeNetworkInfo.isConnected()) {
                if (type == 0) {
                    connect = PdNetworkType.Cellular.INSTANCE;
                } else if (type == 1) {
                    connect = PdNetworkType.Wifi.INSTANCE;
                } else {
                    connect = PdNetworkType.None.INSTANCE;
                }
            } else if (type == 0) {
                connect = PdNetworkType.CellularUnCon.INSTANCE;
            } else if (type == 1) {
                connect = PdNetworkType.WifiUnCon.INSTANCE;
            } else {
                connect = PdNetworkType.None.INSTANCE;
            }
        } else {
            ConnectivityManager connectivityManager2 = connManager;
            if (connectivityManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connManager");
            }
            Network activeNetwork = connectivityManager2.getActiveNetwork();
            if (activeNetwork == null) {
                return PdNetworkType.None.INSTANCE;
            }
            Intrinsics.checkExpressionValueIsNotNull(activeNetwork, "connManager.activeNetwor…return PdNetworkType.None");
            ConnectivityManager connectivityManager3 = connManager;
            if (connectivityManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connManager");
            }
            NetworkCapabilities networkCapabilities = connectivityManager3.getNetworkCapabilities(activeNetwork);
            if (networkCapabilities == null) {
                return PdNetworkType.None.INSTANCE;
            }
            Intrinsics.checkExpressionValueIsNotNull(networkCapabilities, "connManager.getNetworkCa…return PdNetworkType.None");
            if (networkCapabilities.hasTransport(1)) {
                connect = PdNetworkType.Wifi.INSTANCE;
            } else if (networkCapabilities.hasTransport(0)) {
                connect = PdNetworkType.Cellular.INSTANCE;
            } else if (networkCapabilities.hasTransport(3)) {
                connect = PdNetworkType.Ethernet.INSTANCE;
            } else if (networkCapabilities.hasTransport(2)) {
                connect = PdNetworkType.Bluetooth.INSTANCE;
            } else if (networkCapabilities.hasTransport(6)) {
                connect = PdNetworkType.Lowpan.INSTANCE;
            } else if (networkCapabilities.hasTransport(8)) {
                connect = PdNetworkType.Usb.INSTANCE;
            } else if (networkCapabilities.hasTransport(4)) {
                connect = PdNetworkType.Vpn.INSTANCE;
            } else if (networkCapabilities.hasTransport(5)) {
                connect = PdNetworkType.WifiAware.INSTANCE;
            } else {
                connect = PdNetworkType.Connect.INSTANCE;
            }
        }
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "pdNetworkType " + connect);
        return connect;
    }
}
