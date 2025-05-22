package com.pudutech.factory_test.test_pack;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: WifiUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00048F@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/WifiUtil;", "", "()V", "TAG", "", "isOpening", "", "mWifiManager", "Landroid/net/wifi/WifiManager;", "<set-?>", "mac", "getMac", "()Ljava/lang/String;", "getLocalIpAddress", "context", "Landroid/content/Context;", "getWIFIMac", "int2ip", "ipInt", "", "isNetworkAvailable", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class WifiUtil {
    private static boolean isOpening;
    private static WifiManager mWifiManager;
    private static String mac;
    public static final WifiUtil INSTANCE = new WifiUtil();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private WifiUtil() {
    }

    public final String getMac() {
        String str = mac;
        return str != null ? str : getWIFIMac();
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final String int2ip(int ipInt) {
        String str = (ipInt & 255) + "." + ((ipInt >> 8) & 255) + "." + ((ipInt >> 16) & 255) + "." + ((ipInt >> 24) & 255);
        Intrinsics.checkExpressionValueIsNotNull(str, "sb.toString()");
        return str;
    }

    public final String getLocalIpAddress(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            Object systemService = context.getSystemService("wifi");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
            }
            WifiInfo wifiInfo = ((WifiManager) systemService).getConnectionInfo();
            Intrinsics.checkExpressionValueIsNotNull(wifiInfo, "wifiInfo");
            return int2ip(wifiInfo.getIpAddress());
        } catch (Exception e) {
            return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + e.getMessage();
        }
    }

    public final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        try {
            Intrinsics.checkExpressionValueIsNotNull(activeNetInfo, "activeNetInfo");
            return activeNetInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }
}
