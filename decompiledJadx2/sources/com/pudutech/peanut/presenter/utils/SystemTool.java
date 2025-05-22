package com.pudutech.peanut.presenter.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: SystemTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0003J\b\u0010\u0010\u001a\u00020\u0004H\u0002J\u0016\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0004J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004J \u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/utils/SystemTool;", "", "()V", "TAG", "", "execCommand", "Landroid/util/Pair;", "", "command", "use_su", "", "getMac", "context", "Landroid/content/Context;", "getMacAddress", "getMacDefault", "getMacFromHardware", "getProperty", TransferTable.COLUMN_KEY, "setProperty", "", ES6Iterator.VALUE_PROPERTY, "waitFor", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "process", "Ljava/lang/Process;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SystemTool {
    public static final SystemTool INSTANCE = new SystemTool();
    public static final String TAG = "SystemTool";

    private SystemTool() {
    }

    public final Pair<Integer, String> execCommand(String command, boolean use_su) {
        Intrinsics.checkParameterIsNotNull(command, "command");
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(command);
        sb.append(use_su ? " with su" : " with sh");
        objArr[0] = sb.toString();
        Pdlog.m3273d("SystemTool", objArr);
        String[] strArr = new String[3];
        strArr[0] = use_su ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = command;
        try {
            Process proc = Runtime.getRuntime().exec(strArr);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            Intrinsics.checkExpressionValueIsNotNull(proc, "proc");
            if (!waitFor(30L, timeUnit, proc)) {
                Pair<Integer, String> create = Pair.create(-1, "exec commond timeout.");
                Intrinsics.checkExpressionValueIsNotNull(create, "Pair.create(-1, \"exec commond timeout.\")");
                return create;
            }
            InputStream inputStream = proc.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();
            int exitValue = proc.exitValue();
            Pdlog.m3273d("SystemTool", "su exit value = " + exitValue + ' ' + ((Object) sb2));
            Pair<Integer, String> create2 = Pair.create(Integer.valueOf(exitValue), sb2.toString());
            Intrinsics.checkExpressionValueIsNotNull(create2, "Pair.create(exitValue, sb.toString())");
            return create2;
        } catch (Throwable th) {
            Pdlog.m3277w("SystemTool", new Object[]{th});
            Pair<Integer, String> create3 = Pair.create(-1, "exec commond failure.");
            Intrinsics.checkExpressionValueIsNotNull(create3, "Pair.create<Int, String>… \"exec commond failure.\")");
            return create3;
        }
    }

    private final boolean waitFor(long timeout, TimeUnit unit, Process process) throws InterruptedException {
        long nanoTime = System.nanoTime();
        long nanos = unit.toNanos(timeout);
        do {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(RangesKt.coerceAtMost(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = unit.toNanos(timeout) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }

    public final String getMac(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (Build.VERSION.SDK_INT < 23) {
            return getMacDefault(context);
        }
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 24) {
            return Build.VERSION.SDK_INT >= 24 ? getMacFromHardware() : "";
        }
        return getMacAddress();
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

    public final String getProperty(Context context, String key) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(key, "key");
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class).invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            Pdlog.m3274e("SystemProperty get e:", " stack:" + e.getMessage());
            return "";
        }
    }

    public final void setProperty(Context context, String key, String value) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_SET, String.class, String.class).invoke(loadClass, key, value);
        } catch (Exception e) {
            Pdlog.m3274e("SystemProperty set e:", " stack:" + e.getMessage());
        }
    }
}
