package com.pudutech.mqtt.component.client.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: ContextExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\b\u0010\u0002\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0005H\u0007\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0005\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0005H\u0003\u001a\n\u0010\b\u001a\u00020\t*\u00020\u0005\u001a\n\u0010\n\u001a\u00020\t*\u00020\u0005¨\u0006\u000b"}, m3961d2 = {"getMacAddress", "", "getMacFromHardware", "getActiveNetworkInfo", "Landroid/net/NetworkInfo;", "Landroid/content/Context;", "getMac", "getMacDefault", "isConnected", "", "isNetworkAvailable", "component_mqtt_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ContextExtKt {
    public static final boolean isNetworkAvailable(Context isNetworkAvailable) {
        NetworkCapabilities networkCapabilities;
        Intrinsics.checkParameterIsNotNull(isNetworkAvailable, "$this$isNetworkAvailable");
        Object systemService = isNetworkAvailable.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(2);
    }

    public static final boolean isConnected(Context isConnected) {
        Intrinsics.checkParameterIsNotNull(isConnected, "$this$isConnected");
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(isConnected);
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public static final NetworkInfo getActiveNetworkInfo(Context getActiveNetworkInfo) {
        Intrinsics.checkParameterIsNotNull(getActiveNetworkInfo, "$this$getActiveNetworkInfo");
        Object systemService = getActiveNetworkInfo.getApplicationContext().getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        return ((ConnectivityManager) systemService).getActiveNetworkInfo();
    }

    public static final String getMac(Context getMac) {
        Intrinsics.checkParameterIsNotNull(getMac, "$this$getMac");
        if (Build.VERSION.SDK_INT < 23) {
            return getMacDefault(getMac);
        }
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 24) {
            return Build.VERSION.SDK_INT >= 24 ? getMacFromHardware() : "";
        }
        return getMacAddress();
    }

    private static final String getMacDefault(Context context) {
        Object systemService = context.getApplicationContext().getSystemService("wifi");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.wifi.WifiManager");
        }
        WifiInfo wifiInfo = (WifiInfo) null;
        try {
            wifiInfo = ((WifiManager) systemService).getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wifiInfo == null) {
            return "";
        }
        String macAddress = wifiInfo.getMacAddress();
        Intrinsics.checkExpressionValueIsNotNull(macAddress, "info.macAddress");
        if (TextUtils.isEmpty(macAddress)) {
            return macAddress;
        }
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ENGLISH");
        if (macAddress == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = macAddress.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    private static final String getMacAddress() {
        try {
            Process pp = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address");
            Intrinsics.checkExpressionValueIsNotNull(pp, "pp");
            new LineNumberReader(new InputStreamReader(pp.getInputStream()));
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static final String getMacFromHardware() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (!TextUtils.isEmpty(sb)) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "res1.toString()");
                    return sb2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
