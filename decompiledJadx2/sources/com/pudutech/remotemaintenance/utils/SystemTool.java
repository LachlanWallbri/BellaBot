package com.pudutech.remotemaintenance.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.pudutech.mirsdk.update.ApiConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: SystemTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\u0004H\u0007J\b\u0010\t\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0003J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\n\u0010\f\u001a\u00020\u0004*\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/utils/SystemTool;", "", "()V", "TAG", "", "getMac", "context", "Landroid/content/Context;", "getMacAddr", "getMacAddress", "getMacDefault", "getMacFromHardware", "toHexString", "", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SystemTool {
    public static final SystemTool INSTANCE = new SystemTool();
    public static final String TAG = "SystemTool";

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
            macFromHardware = Build.VERSION.SDK_INT >= 24 ? getMacFromHardware() : "";
        }
        if (macFromHardware.length() == 0) {
            return ApiConstants.MAC_ADDRESS;
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

    public final String getMacAddr() {
        try {
            ArrayList<NetworkInterface> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            Intrinsics.checkExpressionValueIsNotNull(list, "Collections.list(Network…e.getNetworkInterfaces())");
            for (NetworkInterface networkInterface : list) {
                if (StringsKt.equals(networkInterface.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    String hexString = toHexString(hardwareAddress);
                    if (hexString.length() == 0) {
                        return ApiConstants.MAC_ADDRESS;
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hexString.length();
                    for (int i = 0; i < length; i++) {
                        sb.append(hexString.charAt(i));
                        if (i > 0 && i < hexString.length() - 1 && (i & 1) == 1) {
                            sb.append(":");
                        }
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb2, "tmp.toString()");
                    return sb2;
                }
            }
            return ApiConstants.MAC_ADDRESS;
        } catch (Exception unused) {
            return ApiConstants.MAC_ADDRESS;
        }
    }

    public final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return ArraysKt.joinToString$default(toHexString, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, String>() { // from class: com.pudutech.remotemaintenance.utils.SystemTool$toHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Byte b) {
                return invoke(b.byteValue());
            }

            public final String invoke(byte b) {
                String format = String.format("%02x", Byte.valueOf(b));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(\"%02x\", it)");
                return format;
            }
        }, 30, (Object) null);
    }

    private final String getMacAddress() {
        try {
            Process pp = Runtime.getRuntime().exec("cat/sys/class/net/wlan0/address");
            Intrinsics.checkExpressionValueIsNotNull(pp, "pp");
            new LineNumberReader(new InputStreamReader(pp.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getMacAddr();
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
                        String format = String.format("%02X:", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
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
