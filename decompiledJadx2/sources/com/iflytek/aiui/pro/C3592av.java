package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.av */
/* loaded from: classes.dex */
public class C3592av {

    /* renamed from: a */
    public static boolean f2376a = true;

    /* renamed from: b */
    private static final Pattern f2377b = Pattern.compile("[0-3][0-9a-f]{64}");

    /* renamed from: c */
    private static final Pattern f2378c = Pattern.compile("[0-3][0-9a-f]{32}");

    /* renamed from: d */
    private static String f2379d = null;

    /* renamed from: e */
    private static String f2380e = Environment.getExternalStorageDirectory().getPath() + "/android/com/iflytek/idata/";

    /* renamed from: f */
    private static String f2381f = Environment.getExternalStorageDirectory().getPath() + "/msc/";

    /* renamed from: a */
    public static String m1075a(Context context) {
        String str;
        synchronized (C3592av.class) {
            if (f2379d == null) {
                f2379d = m1095e(context);
            }
            if (!TextUtils.isEmpty(f2379d) && f2379d.contains("2f40b4cef39a112b191356c8f0fd0e020")) {
                m1092c(context);
                f2379d = m1095e(context);
            }
            str = f2379d;
        }
        return str;
    }

    /* renamed from: a */
    private static String m1076a(Context context, String str) {
        try {
            return Settings.System.getString(context.getContentResolver(), str);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    private static String m1077a(File file) {
        try {
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[65];
            int read = fileInputStream.read(bArr);
            fileInputStream.close();
            return new String(bArr, 0, read);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m1078a(String str) {
        try {
            return m1079a(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m1079a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static void m1082a(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (m1083a(9)) {
                file.getClass().getMethod("setReadable", Boolean.TYPE, Boolean.TYPE).invoke(file, true, false);
                return;
            }
            Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private static boolean m1083a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    private static final boolean m1084a(String str, Pattern pattern) {
        return str == null || "".equals(str.trim()) || !pattern.matcher(str).matches();
    }

    /* renamed from: b */
    public static String m1086b(Context context) {
        String m1093d = m1093d(context);
        if (!TextUtils.isEmpty(m1093d) && !"2f40b4cef39a112b191356c8f0fd0e020".equals(m1093d)) {
            return m1093d;
        }
        String m1075a = m1075a(context);
        return (TextUtils.isEmpty(m1075a) || m1075a.length() < 33) ? "" : m1075a.substring(0, 33);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m1087b(Context context, String str, String str2) {
        try {
            Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    private static boolean m1089b(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: c */
    private static String m1090c(Context context, String str, String str2) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return m1077a(new File(str, str2));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: c */
    private static String m1091c(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable unused) {
            return str3;
        }
    }

    /* renamed from: c */
    public static void m1092c(Context context) {
        m1087b(context, "iflytek.unique.deviceid.key", "");
        m1094d(context, "com.iflytek.unique.id", "pref.deviceid.key", "");
        new File(f2380e, ".1B5A88FBE00E5E45").delete();
        f2379d = null;
    }

    /* renamed from: d */
    private static String m1093d(final Context context) {
        Thread thread;
        String m1076a = m1076a(context, "iflytek.deviceid.key");
        if (!m1084a(m1076a, f2378c)) {
            return m1076a;
        }
        final String m1090c = m1090c(context, f2381f, ".2F6E2C5B63F0F83B");
        if (m1084a(m1090c, f2378c)) {
            m1090c = m1091c(context, "com.iflytek.id", "pref.deviceid.key", null);
            if (m1084a(m1090c, f2378c)) {
                return "";
            }
            thread = new Thread(new Runnable() { // from class: com.iflytek.aiui.pro.av.2
                @Override // java.lang.Runnable
                public void run() {
                    C3592av.m1087b(context, "iflytek.deviceid.key", m1090c);
                    C3592av.m1096e(context, C3592av.f2381f, ".2F6E2C5B63F0F83B", m1090c);
                }
            });
        } else {
            thread = new Thread(new Runnable() { // from class: com.iflytek.aiui.pro.av.1
                @Override // java.lang.Runnable
                public void run() {
                    C3592av.m1087b(context, "iflytek.deviceid.key", m1090c);
                }
            });
        }
        thread.start();
        return m1090c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m1094d(Context context, String str, String str2, String str3) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(str2, str3);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: e */
    private static String m1095e(final Context context) {
        Thread thread;
        String m1076a = m1076a(context, "iflytek.unique.deviceid.key");
        if (!m1084a(m1076a, f2377b)) {
            return m1076a;
        }
        final String m1090c = m1090c(context, f2380e, ".1B5A88FBE00E5E45");
        if (m1084a(m1090c, f2377b)) {
            m1090c = m1091c(context, "com.iflytek.unique.id", "pref.deviceid.key", null);
            if (m1084a(m1090c, f2377b)) {
                m1090c = m1097f(context);
                thread = new Thread(new Runnable() { // from class: com.iflytek.aiui.pro.av.5
                    @Override // java.lang.Runnable
                    public void run() {
                        C3592av.m1087b(context, "iflytek.unique.deviceid.key", m1090c);
                        C3592av.m1096e(context, C3592av.f2380e, ".1B5A88FBE00E5E45", m1090c);
                        C3592av.m1094d(context, "com.iflytek.unique.id", "pref.deviceid.key", m1090c);
                    }
                });
            } else {
                thread = new Thread(new Runnable() { // from class: com.iflytek.aiui.pro.av.4
                    @Override // java.lang.Runnable
                    public void run() {
                        C3592av.m1087b(context, "iflytek.unique.deviceid.key", m1090c);
                        C3592av.m1096e(context, C3592av.f2380e, ".1B5A88FBE00E5E45", m1090c);
                    }
                });
            }
        } else {
            thread = new Thread(new Runnable() { // from class: com.iflytek.aiui.pro.av.3
                @Override // java.lang.Runnable
                public void run() {
                    C3592av.m1087b(context, "iflytek.unique.deviceid.key", m1090c);
                }
            });
        }
        thread.start();
        return m1090c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static void m1096e(Context context, String str, String str2, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        m1082a(new File(file, str2), str3);
    }

    /* renamed from: f */
    private static String m1097f(Context context) {
        String m1093d = m1093d(context);
        String replace = UUID.randomUUID().toString().replace("-", "");
        if (!TextUtils.isEmpty(m1093d) && !"2f40b4cef39a112b191356c8f0fd0e020".equals(m1093d)) {
            return m1093d + replace;
        }
        return "2" + m1078a(m1098g(context)) + replace;
    }

    /* renamed from: g */
    private static String m1098g(Context context) {
        C3581ak m1106a = C3594ax.m1106a(context);
        StringBuilder sb = new StringBuilder();
        sb.append(m1106a.m1015a("imei"));
        sb.append(Soundex.SILENT_MARKER);
        sb.append(m1099h(context));
        if (f2376a) {
            sb.append(Soundex.SILENT_MARKER);
            sb.append(C3601bb.m1129a(context));
        }
        return sb.toString();
    }

    /* renamed from: h */
    private static String m1099h(Context context) {
        try {
            if (m1089b(context, "android.permission.READ_PHONE_STATE")) {
                return Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
