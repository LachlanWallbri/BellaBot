package com.pudutech.mqtt.component.client.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
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
/* compiled from: SystemTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0003J\b\u0010\t\u001a\u00020\u0004H\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/utils/SystemTool;", "", "()V", "getMac", "", "context", "Landroid/content/Context;", "getMacAddress", "getMacDefault", "getMacFromHardware", "isNetworkAvailable", "", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SystemTool {
    public static final SystemTool INSTANCE = new SystemTool();

    private SystemTool() {
    }

    public final String getMac(Context context) {
        String macFromHardware;
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (Build.VERSION.SDK_INT < 23) {
            macFromHardware = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT >= 23 && Build.VERSION.SDK_INT < 24) {
            macFromHardware = getMacAddress();
        } else {
            macFromHardware = Build.VERSION.SDK_INT >= 24 ? getMacFromHardware() : "00:00:00:00:00:00";
        }
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        if (macFromHardware == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = macFromHardware.toUpperCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    private final String getMacDefault(Context context) {
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

    private final String getMacAddress() {
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

    private final String getMacFromHardware() {
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

    public final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            return networkCapabilities.hasCapability(16);
        }
        return false;
    }
}
