package com.loc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo.java */
/* renamed from: com.loc.k */
/* loaded from: classes4.dex */
public final class C3885k {

    /* renamed from: a */
    private static String f4224a = "";

    /* renamed from: b */
    private static String f4225b = "";

    /* renamed from: c */
    private static String f4226c = "";

    /* renamed from: d */
    private static String f4227d = "";

    /* renamed from: e */
    private static String f4228e;

    /* renamed from: a */
    public static String m3120a(Context context) {
        try {
            return m3129h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f4227d;
        }
    }

    /* renamed from: a */
    public static void m3121a(String str) {
        f4225b = str;
    }

    /* renamed from: b */
    public static String m3122b(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "AppInfo", "getApplicationName");
        }
        if (!"".equals(f4224a)) {
            return f4224a;
        }
        PackageManager packageManager = context.getPackageManager();
        f4224a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return f4224a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m3123b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f4227d = str;
    }

    /* renamed from: c */
    public static String m3124c(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "AppInfo", "getpckn");
        }
        if (f4225b != null && !"".equals(f4225b)) {
            return f4225b;
        }
        f4225b = context.getPackageName();
        return f4225b;
    }

    /* renamed from: d */
    public static String m3125d(Context context) {
        try {
        } catch (Throwable th) {
            C3897w.m3249a(th, "AppInfo", "getApplicationVersion");
        }
        if (!"".equals(f4226c)) {
            return f4226c;
        }
        f4226c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        String str = f4226c;
        return str == null ? "" : str;
    }

    /* renamed from: e */
    public static String m3126e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(TextUtils.isEmpty(f4225b) ? packageInfo.packageName : m3124c(context));
            String stringBuffer2 = stringBuffer.toString();
            f4228e = stringBuffer2;
            return stringBuffer2;
        } catch (Throwable th) {
            C3897w.m3249a(th, "AppInfo", "getpck");
            return f4228e;
        }
    }

    /* renamed from: f */
    public static String m3127f(Context context) {
        try {
            return m3129h(context);
        } catch (Throwable th) {
            C3897w.m3249a(th, "AppInfo", "getKey");
            return f4227d;
        }
    }

    /* renamed from: g */
    private static String m3128g(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(C3898x.m3254a(context, "k.store"));
        if (!file.exists()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String m3226a = C3894t.m3226a(bArr);
            if (m3226a.length() != 32) {
                m3226a = "";
            }
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return m3226a;
        } catch (Throwable th4) {
            th = th4;
            try {
                C3897w.m3249a(th, "AppInfo", "getKeyFromFile");
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
                return "";
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: h */
    private static String m3129h(Context context) throws PackageManager.NameNotFoundException {
        String str = f4227d;
        if (str == null || str.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f4227d;
            }
            String string = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            f4227d = string;
            if (string == null) {
                f4227d = m3128g(context);
            }
        }
        return f4227d;
    }
}
