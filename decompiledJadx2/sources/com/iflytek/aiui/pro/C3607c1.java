package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* renamed from: com.iflytek.aiui.pro.c1 */
/* loaded from: classes4.dex */
public class C3607c1 {

    /* renamed from: a */
    private static final Pattern f2456a = Pattern.compile("[0-3][0-9a-f]{64}");

    /* renamed from: b */
    private static final Pattern f2457b = Pattern.compile("[0-3][0-9a-f]{32}");

    /* renamed from: c */
    private static String f2458c = null;

    /* renamed from: d */
    private static String f2459d = Environment.getExternalStorageDirectory().getPath() + "/android/com/iflytek/idata/";

    /* renamed from: e */
    private static String f2460e = Environment.getExternalStorageDirectory().getPath() + "/msc/";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.c1$a */
    /* loaded from: classes4.dex */
    public static final class a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2461a;

        /* renamed from: b */
        final /* synthetic */ String f2462b;

        a(Context context, String str) {
            this.f2461a = context;
            this.f2462b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3641t0.m1548l(this.f2461a, "iflytek.deviceid.key", this.f2462b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.c1$b */
    /* loaded from: classes4.dex */
    public static final class b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2463a;

        /* renamed from: b */
        final /* synthetic */ String f2464b;

        b(Context context, String str) {
            this.f2463a = context;
            this.f2464b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3641t0.m1548l(this.f2463a, "iflytek.deviceid.key", this.f2464b);
            C3607c1.m1238q(this.f2463a, C3607c1.f2460e, ".2F6E2C5B63F0F83B", this.f2464b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.c1$c */
    /* loaded from: classes4.dex */
    public static final class c implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2465a;

        /* renamed from: b */
        final /* synthetic */ String f2466b;

        c(Context context, String str) {
            this.f2465a = context;
            this.f2466b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3641t0.m1548l(this.f2465a, "iflytek.unique.deviceid.key", this.f2466b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.c1$d */
    /* loaded from: classes4.dex */
    public static final class d implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2467a;

        /* renamed from: b */
        final /* synthetic */ String f2468b;

        d(Context context, String str) {
            this.f2467a = context;
            this.f2468b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3641t0.m1548l(this.f2467a, "iflytek.unique.deviceid.key", this.f2468b);
            C3607c1.m1238q(this.f2467a, C3607c1.f2459d, ".1B5A88FBE00E5E45", this.f2468b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.iflytek.aiui.pro.c1$e */
    /* loaded from: classes4.dex */
    public static final class e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2469a;

        /* renamed from: b */
        final /* synthetic */ String f2470b;

        e(Context context, String str) {
            this.f2469a = context;
            this.f2470b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3641t0.m1548l(this.f2469a, "iflytek.unique.deviceid.key", this.f2470b);
            C3607c1.m1238q(this.f2469a, C3607c1.f2459d, ".1B5A88FBE00E5E45", this.f2470b);
            C3607c1.m1237p(this.f2469a, "com.iflytek.unique.id", "pref.deviceid.key", this.f2470b);
        }
    }

    /* renamed from: e */
    private static String m1226e(Context context) {
        Thread thread;
        String m1544h = C3641t0.m1544h(context, "iflytek.unique.deviceid.key");
        Pattern pattern = f2456a;
        if (!m1228g(m1544h, pattern)) {
            return m1544h;
        }
        String m1234m = m1234m(context, f2459d, ".1B5A88FBE00E5E45");
        if (m1228g(m1234m, pattern)) {
            m1234m = m1233l(context, "com.iflytek.unique.id", "pref.deviceid.key", null);
            if (m1228g(m1234m, pattern)) {
                m1234m = m1231j(context);
                thread = new Thread(new e(context, m1234m));
            } else {
                thread = new Thread(new d(context, m1234m));
            }
        } else {
            thread = new Thread(new c(context, m1234m));
        }
        thread.start();
        return m1234m;
    }

    /* renamed from: f */
    private static String m1227f(String str) {
        try {
            return m1240s(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: g */
    private static final boolean m1228g(String str, Pattern pattern) {
        return str == null || "".equals(str.trim()) || !pattern.matcher(str).matches();
    }

    /* renamed from: h */
    private static boolean m1229h(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: i */
    public static void m1230i(Context context) {
        C3641t0.m1548l(context, "iflytek.unique.deviceid.key", "");
        m1237p(context, "com.iflytek.unique.id", "pref.deviceid.key", "");
        new File(f2459d, ".1B5A88FBE00E5E45").delete();
        f2458c = null;
    }

    /* renamed from: j */
    private static String m1231j(Context context) {
        String m1232k = m1232k(context);
        String replace = UUID.randomUUID().toString().replace("-", "");
        if (!TextUtils.isEmpty(m1232k) && !"2f40b4cef39a112b191356c8f0fd0e020".equals(m1232k) && !"29e1aae35d6f41a1ad887f87de4bd08a7".equals(m1232k)) {
            return m1232k + replace;
        }
        String m1539c = C3641t0.m1539c(context);
        StringBuilder sb = new StringBuilder();
        sb.append("2");
        sb.append(TextUtils.isEmpty(m1539c) ? "" : m1227f(m1539c));
        sb.append(replace);
        String sb2 = sb.toString();
        C3651y0.m1620b("UniqueIDUtil", String.format("origin device id %s uuid %s NewID %s", m1539c, replace, sb2));
        return sb2;
    }

    /* renamed from: k */
    private static String m1232k(Context context) {
        Thread thread;
        String m1544h = C3641t0.m1544h(context, "iflytek.deviceid.key");
        Pattern pattern = f2457b;
        if (!m1228g(m1544h, pattern)) {
            return m1544h;
        }
        String m1234m = m1234m(context, f2460e, ".2F6E2C5B63F0F83B");
        if (m1228g(m1234m, pattern)) {
            m1234m = m1233l(context, "com.iflytek.id", "pref.deviceid.key", null);
            if (m1228g(m1234m, pattern)) {
                return "";
            }
            thread = new Thread(new b(context, m1234m));
        } else {
            thread = new Thread(new a(context, m1234m));
        }
        thread.start();
        return m1234m;
    }

    /* renamed from: l */
    private static String m1233l(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable unused) {
            return str3;
        }
    }

    /* renamed from: m */
    private static String m1234m(Context context, String str, String str2) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return m1239r(new File(str, str2));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: n */
    public static String m1235n(Context context) {
        String m1232k = m1232k(context);
        if (!TextUtils.isEmpty(m1232k) && !"2f40b4cef39a112b191356c8f0fd0e020".equals(m1232k) && !"29e1aae35d6f41a1ad887f87de4bd08a7".equals(m1232k)) {
            return m1232k;
        }
        String m1236o = m1236o(context);
        return (TextUtils.isEmpty(m1236o) || m1236o.length() < 33) ? "" : m1236o.substring(0, 33);
    }

    /* renamed from: o */
    public static String m1236o(Context context) {
        String str;
        synchronized (C3607c1.class) {
            if (f2458c == null) {
                f2458c = m1226e(context);
            }
            if (!TextUtils.isEmpty(f2458c) && (f2458c.contains("2f40b4cef39a112b191356c8f0fd0e020") || f2458c.contains("29e1aae35d6f41a1ad887f87de4bd08a7"))) {
                m1230i(context);
                f2458c = m1226e(context);
            }
            str = f2458c;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public static void m1237p(Context context, String str, String str2, String str3) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public static void m1238q(Context context, String str, String str2, String str3) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        m1241t(new File(file, str2), str3);
    }

    /* renamed from: r */
    private static String m1239r(File file) {
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

    /* renamed from: s */
    private static String m1240s(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            int i = b2 & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /* renamed from: t */
    private static void m1241t(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (m1229h(9)) {
                Class<?> cls = file.getClass();
                Class<?> cls2 = Boolean.TYPE;
                cls.getMethod("setReadable", cls2, cls2).invoke(file, Boolean.TRUE, Boolean.FALSE);
            } else {
                Runtime.getRuntime().exec("chmod 444 " + file.getAbsolutePath());
            }
        } catch (Throwable unused) {
        }
    }
}
