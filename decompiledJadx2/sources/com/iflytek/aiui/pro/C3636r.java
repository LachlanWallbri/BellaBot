package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.r */
/* loaded from: classes.dex */
class C3636r {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3642u m1502a(Context context, String str) {
        String string;
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("dynamicfile", 0);
        String string2 = sharedPreferences.getString("AVERSION", "0.0.0");
        String string3 = sharedPreferences.getString("ADYNAMICVERSION", "0.0.0");
        if (str.contains("dex")) {
            string = sharedPreferences.getString("ODEXSIGN", "");
            str2 = "useodex";
        } else {
            string = sharedPreferences.getString("DEXSIGN", "");
            str2 = "usedex";
        }
        if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
            return null;
        }
        C3642u c3642u = new C3642u(string, string2, string3);
        c3642u.m1549a(str2);
        return c3642u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m1503a(Context context) {
        return context.getSharedPreferences("dynamicfile", 0).getString("ERRORVERSION", "0.0.0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m1504a(Context context, String str, String str2) {
        return m1513c(context, m1512b(context, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m1505a(String str) {
        return str + ".dex";
    }

    /* renamed from: a */
    public static void m1506a(Context context, C3626m c3626m) {
        String m1512b = m1512b(context, c3626m.f2580b);
        String m1513c = m1513c(context, m1512b);
        String m1513c2 = m1513c(context, m1505a(m1512b));
        File file = new File(m1513c);
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(m1513c2);
        if (file2.exists()) {
            file2.delete();
        }
        m1514c(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1507a(Context context, C3626m c3626m, C3642u c3642u, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            String str2 = c3626m.f2579a;
            m1516d(context, m1512b(context, c3626m.f2580b));
            FileInputStream fileInputStream2 = new FileInputStream(new File(str));
            try {
                fileOutputStream = new FileOutputStream(new File(m1504a(context, str2, c3626m.f2580b)), true);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, read);
                    }
                }
                m1508a(context, c3642u);
                try {
                    fileInputStream2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                try {
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m1508a(Context context, C3642u c3642u) {
        String str;
        String str2;
        SharedPreferences.Editor edit = context.getSharedPreferences("dynamicfile", 0).edit();
        edit.putString("AVERSION", c3642u.f2661b);
        edit.putString("ADYNAMICVERSION", c3642u.f2662c);
        if (!"usedex".equals(c3642u.f2663d)) {
            if ("useodex".equals(c3642u.f2663d)) {
                str = c3642u.f2660a;
                str2 = "ODEXSIGN";
            }
            edit.apply();
        }
        str = c3642u.f2660a;
        str2 = "DEXSIGN";
        edit.putString(str2, str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m1509a(Context context, String str, String str2, C3626m c3626m) {
        C3642u m1502a = m1502a(context, str);
        return m1502a != null && c3626m.f2580b.equals(m1502a.f2661b) && m1510a(str2, m1502a.f2660a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m1510a(String str, String str2) {
        String m1392a = C3624l.m1392a(str);
        return m1392a != null && m1392a.equalsIgnoreCase(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m1511b(Context context) {
        return context.getSharedPreferences("dynamicfile", 0).getString("ADYNAMICVERSION", "0.0.0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m1512b(Context context, String str) {
        return C3624l.m1396b(C3618i.m1368l(context) + str) + ".jar";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m1513c(Context context, String str) {
        return m1515d(context) + File.separator + str;
    }

    /* renamed from: c */
    static void m1514c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dynamicfile", 0);
        String string = sharedPreferences.getString("ADYNAMICVERSION", "0.0.0");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("ERRORVERSION", string);
        edit.remove("AVERSION");
        edit.remove("DEXSIGN");
        edit.remove("ODEXSIGN");
        edit.remove("ADYNAMICVERSION");
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static String m1515d(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "dex";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static void m1516d(Context context, String str) {
        m1517e(context, str);
        m1517e(context, m1505a(str));
    }

    /* renamed from: e */
    private static void m1517e(Context context, String str) {
        File file = new File(m1513c(context, str));
        if (file.exists()) {
            file.delete();
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("dynamicfile", 0).edit();
        edit.remove("DEXSIGN");
        edit.remove("ODEXSIGN");
        edit.remove("ADYNAMICVERSION");
        edit.remove("AVERSION");
        edit.apply();
    }
}
