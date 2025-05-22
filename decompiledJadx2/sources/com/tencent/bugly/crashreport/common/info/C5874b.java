package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.core.os.EnvironmentCompat;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.common.info.b */
/* loaded from: classes7.dex */
public class C5874b {

    /* renamed from: a */
    private static final String[] f7779a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    /* renamed from: b */
    private static final String[] f7780b = {"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};

    /* renamed from: c */
    private static final String[] f7781c = {"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};

    /* renamed from: d */
    private static String f7782d = null;

    /* renamed from: e */
    private static String f7783e = null;

    /* renamed from: d */
    public static String m3456d() {
        return "null";
    }

    /* renamed from: e */
    public static String m3458e() {
        return "null";
    }

    /* renamed from: f */
    public static String m3460f() {
        return "null";
    }

    /* renamed from: a */
    public static String m3449a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: b */
    public static String m3452b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: c */
    public static int m3454c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public static String m3450a(Context context) {
        String str = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
            return str == null ? "null" : str.toLowerCase();
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                C5940x.m3818a("Failed to get Android ID.", new Object[0]);
            }
            return str;
        }
    }

    /* renamed from: b */
    public static String m3453b(Context context) {
        String str = "fail";
        if (context == null) {
            return "fail";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "fail";
            }
            str = telephonyManager.getSimSerialNumber();
            return str == null ? "null" : str;
        } catch (Throwable unused) {
            C5940x.m3818a("Failed to get SIM serial number.", new Object[0]);
            return str;
        }
    }

    /* renamed from: g */
    public static String m3462g() {
        try {
            return Build.SERIAL;
        } catch (Throwable unused) {
            C5940x.m3818a("Failed to get hardware serial number.", new Object[0]);
            return "fail";
        }
    }

    /* renamed from: t */
    private static boolean m3481t() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x004d, code lost:
    
        r0 = java.lang.System.getProperty("os.arch");
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m3451a(Context context, boolean z) {
        String str = null;
        if (z) {
            try {
                String m3855a = C5942z.m3855a(context, "ro.product.cpu.abilist");
                if (C5942z.m3868a(m3855a) || m3855a.equals("fail")) {
                    m3855a = C5942z.m3855a(context, "ro.product.cpu.abi");
                }
                if (!C5942z.m3868a(m3855a) && !m3855a.equals("fail")) {
                    C5940x.m3820b(C5874b.class, "ABI list: " + m3855a, new Object[0]);
                    str = m3855a.split(",")[0];
                }
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        return str;
    }

    /* renamed from: h */
    public static long m3464h() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    /* renamed from: i */
    public static long m3466i() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    /* renamed from: j */
    public static long m3468j() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    if (!C5940x.m3819a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e2) {
                    if (!C5940x.m3819a(e2)) {
                        e2.printStackTrace();
                    }
                }
                return parseLong;
            }
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                if (!C5940x.m3819a(e3)) {
                    e3.printStackTrace();
                }
            }
            try {
                fileReader.close();
                return -1L;
            } catch (IOException e4) {
                if (C5940x.m3819a(e4)) {
                    return -1L;
                }
                e4.printStackTrace();
                return -1L;
            }
        } catch (Throwable th4) {
            th = th4;
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                        if (!C5940x.m3819a(e5)) {
                            e5.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return -2L;
                }
                try {
                    fileReader.close();
                    return -2L;
                } catch (IOException e6) {
                    if (C5940x.m3819a(e6)) {
                        return -2L;
                    }
                    e6.printStackTrace();
                    return -2L;
                }
            } catch (Throwable th5) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e7) {
                        if (!C5940x.m3819a(e7)) {
                            e7.printStackTrace();
                        }
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e8) {
                        if (!C5940x.m3819a(e8)) {
                            e8.printStackTrace();
                        }
                    }
                }
                throw th5;
            }
        }
    }

    /* renamed from: k */
    public static long m3470k() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            fileReader = null;
        }
        try {
            bufferedReader.readLine();
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    if (!C5940x.m3819a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e2) {
                    if (!C5940x.m3819a(e2)) {
                        e2.printStackTrace();
                    }
                }
                return -1L;
            }
            long parseLong = (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + 0;
            String readLine2 = bufferedReader.readLine();
            if (readLine2 == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    if (!C5940x.m3819a(e3)) {
                        e3.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e4) {
                    if (!C5940x.m3819a(e4)) {
                        e4.printStackTrace();
                    }
                }
                return -1L;
            }
            long parseLong2 = parseLong + (Long.parseLong(readLine2.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
            String readLine3 = bufferedReader.readLine();
            if (readLine3 == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e5) {
                    if (!C5940x.m3819a(e5)) {
                        e5.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e6) {
                    if (!C5940x.m3819a(e6)) {
                        e6.printStackTrace();
                    }
                }
                return -1L;
            }
            long parseLong3 = parseLong2 + (Long.parseLong(readLine3.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
            try {
                bufferedReader.close();
            } catch (IOException e7) {
                if (!C5940x.m3819a(e7)) {
                    e7.printStackTrace();
                }
            }
            try {
                fileReader.close();
            } catch (IOException e8) {
                if (!C5940x.m3819a(e8)) {
                    e8.printStackTrace();
                }
            }
            return parseLong3;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e9) {
                        if (!C5940x.m3819a(e9)) {
                            e9.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return -2L;
                }
                try {
                    fileReader.close();
                    return -2L;
                } catch (IOException e10) {
                    if (C5940x.m3819a(e10)) {
                        return -2L;
                    }
                    e10.printStackTrace();
                    return -2L;
                }
            } catch (Throwable th4) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e11) {
                        if (!C5940x.m3819a(e11)) {
                            e11.printStackTrace();
                        }
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e12) {
                        if (!C5940x.m3819a(e12)) {
                            e12.printStackTrace();
                        }
                    }
                }
                throw th4;
            }
        }
    }

    /* renamed from: l */
    public static long m3473l() {
        if (!m3481t()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    /* renamed from: m */
    public static long m3474m() {
        if (!m3481t()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    /* renamed from: n */
    public static String m3475n() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: o */
    public static String m3476o() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m3455c(Context context) {
        NetworkInfo activeNetworkInfo;
        TelephonyManager telephonyManager;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            if (!C5940x.m3819a(e)) {
            }
        }
        if (activeNetworkInfo == null) {
            return null;
        }
        if (activeNetworkInfo.getType() == 1) {
            return TestConstantKt.WIFI;
        }
        if (activeNetworkInfo.getType() != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDen";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "eHRPD";
            case 15:
                return "HSPA+";
            default:
                return "MOBILE(" + networkType + ")";
        }
        if (!C5940x.m3819a(e)) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        e.printStackTrace();
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* renamed from: d */
    public static String m3457d(Context context) {
        String m3855a = C5942z.m3855a(context, "ro.miui.ui.version.name");
        if (!C5942z.m3868a(m3855a) && !m3855a.equals("fail")) {
            return "XiaoMi/MIUI/" + m3855a;
        }
        String m3855a2 = C5942z.m3855a(context, "ro.build.version.emui");
        if (!C5942z.m3868a(m3855a2) && !m3855a2.equals("fail")) {
            return "HuaWei/EMOTION/" + m3855a2;
        }
        String m3855a3 = C5942z.m3855a(context, "ro.lenovo.series");
        if (!C5942z.m3868a(m3855a3) && !m3855a3.equals("fail")) {
            return "Lenovo/VIBE/" + C5942z.m3855a(context, "ro.build.version.incremental");
        }
        String m3855a4 = C5942z.m3855a(context, "ro.build.nubia.rom.name");
        if (!C5942z.m3868a(m3855a4) && !m3855a4.equals("fail")) {
            return "Zte/NUBIA/" + m3855a4 + "_" + C5942z.m3855a(context, "ro.build.nubia.rom.code");
        }
        String m3855a5 = C5942z.m3855a(context, "ro.meizu.product.model");
        if (!C5942z.m3868a(m3855a5) && !m3855a5.equals("fail")) {
            return "Meizu/FLYME/" + C5942z.m3855a(context, "ro.build.display.id");
        }
        String m3855a6 = C5942z.m3855a(context, "ro.build.version.opporom");
        if (!C5942z.m3868a(m3855a6) && !m3855a6.equals("fail")) {
            return "Oppo/COLOROS/" + m3855a6;
        }
        String m3855a7 = C5942z.m3855a(context, "ro.vivo.os.build.display.id");
        if (!C5942z.m3868a(m3855a7) && !m3855a7.equals("fail")) {
            return "vivo/FUNTOUCH/" + m3855a7;
        }
        String m3855a8 = C5942z.m3855a(context, "ro.aa.romver");
        if (!C5942z.m3868a(m3855a8) && !m3855a8.equals("fail")) {
            return "htc/" + m3855a8 + "/" + C5942z.m3855a(context, "ro.build.description");
        }
        String m3855a9 = C5942z.m3855a(context, "ro.lewa.version");
        if (!C5942z.m3868a(m3855a9) && !m3855a9.equals("fail")) {
            return "tcl/" + m3855a9 + "/" + C5942z.m3855a(context, "ro.build.display.id");
        }
        String m3855a10 = C5942z.m3855a(context, "ro.gn.gnromvernumber");
        if (!C5942z.m3868a(m3855a10) && !m3855a10.equals("fail")) {
            return "amigo/" + m3855a10 + "/" + C5942z.m3855a(context, "ro.build.display.id");
        }
        String m3855a11 = C5942z.m3855a(context, "ro.build.tyd.kbstyle_version");
        if (!C5942z.m3868a(m3855a11) && !m3855a11.equals("fail")) {
            return "dido/" + m3855a11;
        }
        return C5942z.m3855a(context, "ro.build.fingerprint") + "/" + C5942z.m3855a(context, "ro.build.rom.id");
    }

    /* renamed from: e */
    public static String m3459e(Context context) {
        return C5942z.m3855a(context, "ro.board.platform");
    }

    /* renamed from: p */
    public static boolean m3477p() {
        boolean z;
        String[] strArr = f7779a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (new File(strArr[i]).exists()) {
                z = true;
                break;
            }
            i++;
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0090 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m3478q() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        try {
            StringBuilder sb = new StringBuilder();
            if (new File("/sys/block/mmcblk0/device/type").exists()) {
                bufferedReader2 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/type"));
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    }
                    bufferedReader2.close();
                } catch (Throwable unused) {
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                    }
                    return null;
                }
            } else {
                bufferedReader2 = null;
            }
            sb.append(",");
            if (new File("/sys/block/mmcblk0/device/name").exists()) {
                bufferedReader3 = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/name"));
                try {
                    String readLine2 = bufferedReader3.readLine();
                    if (readLine2 != null) {
                        sb.append(readLine2);
                    }
                    bufferedReader3.close();
                } catch (Throwable unused2) {
                    bufferedReader = bufferedReader3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            C5940x.m3819a(e);
                        }
                    }
                    return null;
                }
            } else {
                bufferedReader3 = bufferedReader2;
            }
            sb.append(",");
            if (new File("/sys/block/mmcblk0/device/cid").exists()) {
                bufferedReader = new BufferedReader(new FileReader("/sys/block/mmcblk0/device/cid"));
                try {
                    String readLine3 = bufferedReader.readLine();
                    if (readLine3 != null) {
                        sb.append(readLine3);
                    }
                } catch (Throwable unused3) {
                    if (bufferedReader != null) {
                    }
                    return null;
                }
            } else {
                bufferedReader = bufferedReader3;
            }
            String sb2 = sb.toString();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    C5940x.m3819a(e2);
                }
            }
            return sb2;
        } catch (Throwable unused4) {
            bufferedReader = null;
        }
    }

    /* renamed from: f */
    public static String m3461f(Context context) {
        StringBuilder sb = new StringBuilder();
        String m3855a = C5942z.m3855a(context, "ro.genymotion.version");
        if (m3855a != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(m3855a);
            sb.append("\n");
        }
        String m3855a2 = C5942z.m3855a(context, "androVM.vbox_dpi");
        if (m3855a2 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(m3855a2);
            sb.append("\n");
        }
        String m3855a3 = C5942z.m3855a(context, "qemu.sf.fake_camera");
        if (m3855a3 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(m3855a3);
        }
        return sb.toString();
    }

    /* renamed from: g */
    public static String m3463g(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        String readLine;
        StringBuilder sb = new StringBuilder();
        if (f7782d == null) {
            f7782d = C5942z.m3855a(context, "ro.secure");
        }
        if (f7782d != null) {
            sb.append("ro.secure");
            sb.append("|");
            sb.append(f7782d);
            sb.append("\n");
        }
        if (f7783e == null) {
            f7783e = C5942z.m3855a(context, "ro.debuggable");
        }
        if (f7783e != null) {
            sb.append("ro.debuggable");
            sb.append("|");
            sb.append(f7783e);
            sb.append("\n");
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        C5940x.m3819a(th);
                        return sb.toString();
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                C5940x.m3819a(e);
                            }
                        }
                    }
                }
            } while (!readLine.startsWith("TracerPid:"));
            if (readLine != null) {
                String trim = readLine.substring(10).trim();
                sb.append("tracer_pid");
                sb.append("|");
                sb.append(trim);
            }
            String sb2 = sb.toString();
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                C5940x.m3819a(e2);
            }
            return sb2;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00a6 A[Catch: IOException -> 0x00aa, TRY_LEAVE, TryCatch #0 {IOException -> 0x00aa, blocks: (B:43:0x00a0, B:16:0x00a6), top: B:2:0x000e }] */
    /* renamed from: r */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m3479r() {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                if (new File("/sys/class/power_supply/ac/online").exists()) {
                    BufferedReader bufferedReader3 = new BufferedReader(new FileReader("/sys/class/power_supply/ac/online"));
                    try {
                        String readLine = bufferedReader3.readLine();
                        if (readLine != null) {
                            sb.append("ac_online");
                            sb.append("|");
                            sb.append(readLine);
                        }
                        bufferedReader3.close();
                        bufferedReader2 = bufferedReader3;
                    } catch (Throwable unused) {
                        bufferedReader2 = bufferedReader3;
                        if (bufferedReader2 != null) {
                        }
                        return sb.toString();
                    }
                }
                sb.append("\n");
                if (new File("/sys/class/power_supply/usb/online").exists()) {
                    BufferedReader bufferedReader4 = new BufferedReader(new FileReader("/sys/class/power_supply/usb/online"));
                    try {
                        String readLine2 = bufferedReader4.readLine();
                        if (readLine2 != null) {
                            sb.append("usb_online");
                            sb.append("|");
                            sb.append(readLine2);
                        }
                        bufferedReader4.close();
                        bufferedReader2 = bufferedReader4;
                    } catch (Throwable unused2) {
                        bufferedReader2 = bufferedReader4;
                        if (bufferedReader2 != null) {
                        }
                        return sb.toString();
                    }
                }
                sb.append("\n");
                if (new File("/sys/class/power_supply/battery/capacity").exists()) {
                    bufferedReader = new BufferedReader(new FileReader("/sys/class/power_supply/battery/capacity"));
                    try {
                        String readLine3 = bufferedReader.readLine();
                        if (readLine3 != null) {
                            sb.append("battery_capacity");
                            sb.append("|");
                            sb.append(readLine3);
                        }
                        bufferedReader.close();
                    } catch (Throwable unused3) {
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        return sb.toString();
                    }
                } else {
                    bufferedReader = bufferedReader2;
                }
            } catch (Throwable unused4) {
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            C5940x.m3819a(e);
        }
        return sb.toString();
    }

    /* renamed from: h */
    public static String m3465h(Context context) {
        StringBuilder sb = new StringBuilder();
        String m3855a = C5942z.m3855a(context, "gsm.sim.state");
        if (m3855a != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(m3855a);
        }
        sb.append("\n");
        String m3855a2 = C5942z.m3855a(context, "gsm.sim.state2");
        if (m3855a2 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(m3855a2);
        }
        return sb.toString();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0030 -> B:13:0x0041). Please report as a decompilation issue!!! */
    /* renamed from: s */
    public static long m3480s() {
        BufferedReader bufferedReader;
        float f = 0.0f;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(" ")[0]);
                    }
                    bufferedReader.close();
                } catch (Throwable unused) {
                    try {
                        C5940x.m3818a("Failed to get boot time of device.", new Object[0]);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return f;
                    } catch (Throwable th) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                C5940x.m3819a(e);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable unused2) {
                bufferedReader = null;
            }
        } catch (IOException e2) {
            C5940x.m3819a(e2);
        }
        return f;
    }

    /* renamed from: i */
    public static boolean m3467i(Context context) {
        if (m3471k(context) != null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = f7781c;
            if (i >= strArr.length) {
                break;
            }
            if (i == 0) {
                if (new File(strArr[i]).exists()) {
                    i++;
                }
                arrayList.add(Integer.valueOf(i));
                i++;
            } else {
                if (!new File(strArr[i]).exists()) {
                    i++;
                }
                arrayList.add(Integer.valueOf(i));
                i++;
            }
        }
        return (arrayList.isEmpty() ? null : arrayList.toString()) != null;
    }

    /* renamed from: k */
    private static String m3471k(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = f7780b;
            if (i >= strArr.length) {
                break;
            }
            try {
                packageManager.getPackageInfo(strArr[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toString();
    }

    /* renamed from: j */
    public static boolean m3469j(Context context) {
        return (((m3472l(context) | m3483v()) | m3484w()) | m3482u()) > 0;
    }

    /* renamed from: u */
    private static int m3482u() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            return method.invoke(null, new Object[0]).getClass().getName().startsWith("$Proxy") ? 256 : 0;
        } catch (Exception unused) {
            return 256;
        }
    }

    /* renamed from: l */
    private static int m3472l(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception unused) {
            i = 0;
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception unused2) {
            return i;
        }
    }

    /* renamed from: v */
    private static int m3483v() {
        try {
            throw new Exception("detect hook");
        } catch (Exception e) {
            int i = 0;
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    i |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i2 = i2 + 1) == 2) {
                    i |= 32;
                }
            }
            return i;
        }
    }

    /* renamed from: w */
    private static int m3484w() {
        BufferedReader bufferedReader;
        IOException e;
        UnsupportedEncodingException e2;
        FileNotFoundException e3;
        int i = 0;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    HashSet hashSet = new HashSet();
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/maps"), "utf-8"));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            if (readLine.endsWith(".so") || readLine.endsWith(".jar")) {
                                hashSet.add(readLine.substring(readLine.lastIndexOf(" ") + 1));
                            }
                        } catch (FileNotFoundException e4) {
                            e3 = e4;
                            e3.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return i;
                        } catch (UnsupportedEncodingException e5) {
                            e2 = e5;
                            e2.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return i;
                        } catch (IOException e6) {
                            e = e6;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return i;
                        }
                    }
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        if (((String) next).toLowerCase().contains("xposed")) {
                            i |= 64;
                        }
                        if (((String) next).contains("com.saurik.substrate")) {
                            i |= 128;
                        }
                    }
                    bufferedReader.close();
                } catch (FileNotFoundException e7) {
                    bufferedReader = null;
                    e3 = e7;
                } catch (UnsupportedEncodingException e8) {
                    bufferedReader = null;
                    e2 = e8;
                } catch (IOException e9) {
                    bufferedReader = null;
                    e = e9;
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e11) {
                e11.printStackTrace();
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
