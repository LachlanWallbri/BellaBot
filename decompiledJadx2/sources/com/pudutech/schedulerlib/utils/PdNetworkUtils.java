package com.pudutech.schedulerlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import com.pudutech.base.Pdlog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PdNetworkUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020\u0004H\u0002J\u0006\u0010&\u001a\u00020\u0004J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001dJ\u000e\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020$J\u000e\u0010,\u001a\u00020(2\u0006\u0010+\u001a\u00020$R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/PdNetworkUtils;", "", "()V", "NETWORK_2G", "", "NETWORK_3G", "NETWORK_4G", "NETWORK_5G", "NETWORK_ETHERNET", "NETWORK_MOBILE", "NETWORK_MOBILE_UnCon", "NETWORK_NONE", "NETWORK_WIFI", "TAG", "", "kotlin.jvm.PlatformType", "UNKNOWN", "UnCon_WIFI", "connManager", "Landroid/net/ConnectivityManager;", "getConnManager", "()Landroid/net/ConnectivityManager;", "setConnManager", "(Landroid/net/ConnectivityManager;)V", "currentNetWork", "Landroid/net/Network;", "currentNetWorkBlock", "", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "netWorkChangeCallback", "", "Lcom/pudutech/schedulerlib/utils/NetWorkCallback;", "getCellularNetworkState", "getNetWorkType", "init", "", "context", "registerCallBack", "callback", "unRegisterCallBack", "MyNetworkCallback", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PdNetworkUtils {
    public static final PdNetworkUtils INSTANCE;
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_5G = 5;
    public static final int NETWORK_ETHERNET = 8;
    public static final int NETWORK_MOBILE = 6;
    public static final int NETWORK_MOBILE_UnCon = 10;
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    private static final String TAG;
    public static final int UNKNOWN = 9;
    public static final int UnCon_WIFI = 7;
    public static ConnectivityManager connManager;
    private static Network currentNetWork;
    private static boolean currentNetWorkBlock;
    public static Context mContext;
    private static final List<NetWorkCallback> netWorkChangeCallback;

    static {
        PdNetworkUtils pdNetworkUtils = new PdNetworkUtils();
        INSTANCE = pdNetworkUtils;
        TAG = pdNetworkUtils.getClass().getSimpleName();
        netWorkChangeCallback = new ArrayList();
    }

    private PdNetworkUtils() {
    }

    public static final /* synthetic */ List access$getNetWorkChangeCallback$p(PdNetworkUtils pdNetworkUtils) {
        return netWorkChangeCallback;
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

    public final void registerCallBack(NetWorkCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        netWorkChangeCallback.add(callback);
    }

    public final void unRegisterCallBack(NetWorkCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        netWorkChangeCallback.remove(callback);
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
        NetworkRequest build = new NetworkRequest.Builder().build();
        ConnectivityManager connectivityManager = connManager;
        if (connectivityManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connManager");
        }
        connectivityManager.registerNetworkCallback(build, new MyNetworkCallback());
    }

    public final int getNetWorkType() {
        if (currentNetWork == null) {
            return 0;
        }
        try {
            ConnectivityManager connectivityManager = connManager;
            if (connectivityManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connManager");
            }
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(currentNetWork);
            if (networkCapabilities == null) {
                Intrinsics.throwNpe();
            }
            if (!networkCapabilities.hasTransport(0)) {
                return (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(5)) ? currentNetWorkBlock ? 7 : 1 : networkCapabilities.hasTransport(3) ? 8 : 9;
            }
            if (currentNetWorkBlock) {
                return 10;
            }
            return getCellularNetworkState();
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "getNetWorkType " + e.getMessage());
            return 9;
        }
    }

    /* compiled from: PdNetworkUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0017¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/schedulerlib/utils/PdNetworkUtils$MyNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "()V", "onAvailable", "", "network", "Landroid/net/Network;", "onCapabilitiesChanged", "nc", "Landroid/net/NetworkCapabilities;", "onLosing", "maxMsToLive", "", "onLost", "onNetWokChange", "onUnavailable", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    private static final class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            onNetWokChange(network);
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onAvailable(network);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLosing(Network network, int maxMsToLive) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onLosing(network, maxMsToLive);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            onNetWokChange(null);
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onAvailable(network);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onUnavailable();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities nc) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            Intrinsics.checkParameterIsNotNull(nc, "nc");
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onCapabilitiesChanged(network, nc);
            }
        }

        public void onNetWokChange(Network network) {
            PdNetworkUtils pdNetworkUtils = PdNetworkUtils.INSTANCE;
            PdNetworkUtils.currentNetWork = network;
            PdNetworkUtils pdNetworkUtils2 = PdNetworkUtils.INSTANCE;
            PdNetworkUtils.currentNetWorkBlock = network == null;
            Iterator it = PdNetworkUtils.access$getNetWorkChangeCallback$p(PdNetworkUtils.INSTANCE).iterator();
            while (it.hasNext()) {
                ((NetWorkCallback) it.next()).onNetWokChange(network);
            }
        }
    }

    private final int getCellularNetworkState() {
        ConnectivityManager connectivityManager = connManager;
        if (connectivityManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connManager");
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 6;
        }
        ConnectivityManager connectivityManager2 = connManager;
        if (connectivityManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connManager");
        }
        NetworkInfo networkInfo = connectivityManager2.getNetworkInfo(0);
        if (networkInfo == null) {
            return 6;
        }
        NetworkInfo.State state = networkInfo.getState();
        String subtypeName = networkInfo.getSubtypeName();
        if (state == null) {
            return 6;
        }
        if (state != NetworkInfo.State.CONNECTED && state != NetworkInfo.State.CONNECTING) {
            return 6;
        }
        int subtype = activeNetworkInfo.getSubtype();
        if (subtype != 19) {
            switch (subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 2;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    break;
                case 13:
                    break;
                default:
                    if (!StringsKt.equals(subtypeName, "TD-SCDMA", true) && !StringsKt.equals(subtypeName, "WCDMA", true) && !StringsKt.equals(subtypeName, "CDMA2000", true)) {
                        return 6;
                    }
                    break;
            }
            return 3;
        }
        return 4;
    }
}
