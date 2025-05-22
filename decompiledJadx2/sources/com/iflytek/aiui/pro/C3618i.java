package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.i */
/* loaded from: classes.dex */
public class C3618i {

    /* renamed from: a */
    private static String f2542a = "";

    /* renamed from: b */
    private static String f2543b = "";

    /* renamed from: c */
    private static String f2544c = "";

    /* renamed from: d */
    private static String f2545d = "";

    /* renamed from: e */
    private static String f2546e = null;

    /* renamed from: f */
    private static String f2547f = "";

    /* renamed from: g */
    private static boolean f2548g = false;

    /* renamed from: h */
    private static String f2549h = "";

    /* renamed from: i */
    private static String f2550i = "";

    /* renamed from: j */
    private static String f2551j = "";

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.i$a */
    /* loaded from: classes.dex */
    static class a extends DefaultHandler {
        a() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (C3618i.f2548g) {
                String unused = C3618i.f2547f = new String(cArr, i, i2);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            boolean unused = C3618i.f2548g = false;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                boolean unused = C3618i.f2548g = true;
            }
        }
    }

    /* renamed from: a */
    public static String m1352a(Context context) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!"".equals(f2542a)) {
            return f2542a;
        }
        PackageManager packageManager = context.getPackageManager();
        f2542a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return f2542a;
    }

    /* renamed from: a */
    public static void m1353a(String str) {
        f2545d = str;
    }

    /* renamed from: a */
    private static boolean m1355a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    /* renamed from: b */
    public static String m1357b(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f2543b != null && !"".equals(f2543b)) {
            return f2543b;
        }
        f2543b = context.getApplicationContext().getPackageName();
        return f2543b;
    }

    /* renamed from: c */
    public static String m1359c(Context context) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!"".equals(f2544c)) {
            return f2544c;
        }
        f2544c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        return f2544c;
    }

    /* renamed from: d */
    public static String m1360d(Context context) {
        try {
            if (f2546e != null && !"".equals(f2546e)) {
                return f2546e;
            }
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
            stringBuffer.append(packageInfo.packageName);
            String stringBuffer2 = stringBuffer.toString();
            f2546e = stringBuffer2;
            return stringBuffer2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return f2546e;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return f2546e;
        } catch (Throwable th) {
            th.printStackTrace();
            return f2546e;
        }
    }

    /* renamed from: e */
    public static String m1361e(Context context) {
        try {
            if (f2545d == null || f2545d.equals("")) {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo == null) {
                    return f2545d;
                }
                f2545d = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            }
            return f2545d;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return f2545d;
        } catch (Throwable th) {
            th.printStackTrace();
            return f2545d;
        }
    }

    /* renamed from: f */
    public static String m1362f(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (f2547f != null && !"".equals(f2547f)) {
            return f2547f;
        }
        if (m1355a(context, "android.permission.WRITE_SETTINGS")) {
            f2547f = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (f2547f != null && !"".equals(f2547f)) {
            return f2547f;
        }
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                if (file.exists()) {
                    SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return f2547f;
    }

    /* renamed from: g */
    public static String m1363g(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!m1355a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String networkOperator = m1371o(context).getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator) || networkOperator.length() >= 3) {
            return networkOperator.substring(3);
        }
        return "";
    }

    /* renamed from: h */
    public static int m1364h(Context context) {
        try {
            if (m1355a(context, "android.permission.READ_PHONE_STATE")) {
                return m1371o(context).getNetworkType();
            }
            return -1;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    /* renamed from: i */
    public static int m1365i(Context context) {
        ConnectivityManager m1370n;
        NetworkInfo activeNetworkInfo;
        if (context != null) {
            try {
                if (m1355a(context, "android.permission.ACCESS_NETWORK_STATE") && (m1370n = m1370n(context)) != null && (activeNetworkInfo = m1370n.getActiveNetworkInfo()) != null) {
                    return activeNetworkInfo.getType();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return -1;
    }

    /* renamed from: j */
    public static NetworkInfo m1366j(Context context) {
        ConnectivityManager m1370n;
        if (m1355a(context, "android.permission.ACCESS_NETWORK_STATE") && (m1370n = m1370n(context)) != null) {
            return m1370n.getActiveNetworkInfo();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: k */
    public static String m1367k(Context context) {
        try {
            NetworkInfo m1366j = m1366j(context);
            if (m1366j == null) {
                return null;
            }
            return m1366j.getExtraInfo();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: l */
    public static String m1368l(Context context) {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if ((f2550i == null || "".equals(f2550i)) && m1355a(context, "android.permission.READ_PHONE_STATE")) {
            String deviceId = m1371o(context).getDeviceId();
            f2550i = deviceId;
            if (deviceId == null) {
                f2550i = "";
            }
            return f2550i;
        }
        return f2550i;
    }

    /* renamed from: m */
    public static String m1369m(Context context) {
        try {
            if ((f2551j == null || "".equals(f2551j)) && m1355a(context, "android.permission.READ_PHONE_STATE")) {
                String subscriberId = m1371o(context).getSubscriberId();
                f2551j = subscriberId;
                if (subscriberId == null) {
                    f2551j = "";
                }
                return f2551j;
            }
            return f2551j;
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public static ConnectivityManager m1370n(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* renamed from: o */
    private static TelephonyManager m1371o(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
