package com.pudutech.disinfect.baselib.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NetWorkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u0010J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0007R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/NetWorkUtil;", "", "()V", "NET_CNNT_BAIDU_OK", "", "getNET_CNNT_BAIDU_OK", "()I", "NET_CNNT_BAIDU_TIMEOUT", "getNET_CNNT_BAIDU_TIMEOUT", "NET_ERROR", "getNET_ERROR", "NET_NOT_PREPARE", "getNET_NOT_PREPARE", "TIMEOUT", "getTIMEOUT", "url", "", "getUrl", "()Ljava/lang/String;", "connectionNetwork", "", "getLocalIpAddress", "getNetState", "context", "Landroid/content/Context;", "is2G", "is3G", "isNetworkAvailable", "isWifi", "isWifiEnable", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetWorkUtil {
    public static final NetWorkUtil INSTANCE = new NetWorkUtil();
    private static final String url = url;
    private static final String url = url;
    private static final int NET_CNNT_BAIDU_OK = 1;
    private static final int NET_CNNT_BAIDU_TIMEOUT = 2;
    private static final int NET_NOT_PREPARE = 3;
    private static final int NET_ERROR = 4;
    private static final int TIMEOUT = 3000;

    private NetWorkUtil() {
    }

    public final String getUrl() {
        return url;
    }

    public final int getNET_CNNT_BAIDU_OK() {
        return NET_CNNT_BAIDU_OK;
    }

    public final int getNET_CNNT_BAIDU_TIMEOUT() {
        return NET_CNNT_BAIDU_TIMEOUT;
    }

    public final int getNET_NOT_PREPARE() {
        return NET_NOT_PREPARE;
    }

    public final int getNET_ERROR() {
        return NET_ERROR;
    }

    public final int getTIMEOUT() {
        return TIMEOUT;
    }

    public final boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? false : true;
    }

    public final String getLocalIpAddress() {
        String str = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkExpressionValueIsNotNull(networkInterfaces, "NetworkInterface.getNetworkInterfaces()");
            Iterator it = CollectionsKt.iterator(networkInterfaces);
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(networkInterface, "networkInterface");
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                Intrinsics.checkExpressionValueIsNotNull(inetAddresses, "networkInterface.inetAddresses");
                Iterator it2 = CollectionsKt.iterator(inetAddresses);
                while (it2.hasNext()) {
                    InetAddress inetAddress = (InetAddress) it2.next();
                    Intrinsics.checkExpressionValueIsNotNull(inetAddress, "inetAddress");
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        Intrinsics.checkExpressionValueIsNotNull(hostAddress, "inetAddress.hostAddress");
                        str = hostAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return str;
    }

    public final int getNetState(Context context) {
        Object systemService;
        NetworkInfo activeNetworkInfo;
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            systemService = context.getSystemService("connectivity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
            activeNetworkInfo.isConnected();
        }
        return NET_ERROR;
    }

    private final boolean connectionNetwork() {
        HttpURLConnection httpURLConnection = (HttpURLConnection) null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(url);
            try {
                httpURLConnection2.setConnectTimeout(TIMEOUT);
                httpURLConnection2.connect();
                httpURLConnection2.disconnect();
                return true;
            } catch (IOException unused) {
                httpURLConnection = httpURLConnection2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                httpURLConnection = httpURLConnection2;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean is3G(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public final boolean isWifi(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public final boolean is2G(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getSubtype() == 2 || activeNetworkInfo.getSubtype() == 1 || activeNetworkInfo.getSubtype() == 4;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r0.getState() != android.net.NetworkInfo.State.CONNECTED) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean isWifiEnable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Object systemService2 = context.getSystemService("telecom");
        if (systemService2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
        }
        TelephonyManager telephonyManager = (TelephonyManager) systemService2;
        if (connectivityManager.getActiveNetworkInfo() != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                Intrinsics.throwNpe();
            }
        }
        return telephonyManager.getDataNetworkType() == 3;
    }
}
