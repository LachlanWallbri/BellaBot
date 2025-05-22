package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class AppInfo {

    /* renamed from: a */
    private static ActivityManager f7697a;

    static {
        "@buglyAllChannel@".split(",");
        "@buglyAllChannelPriority@".split(",");
    }

    /* renamed from: a */
    public static String m3376a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: b */
    public static PackageInfo m3380b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m3376a(context), 0);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m3379a(Context context, String str) {
        if (context != null && str != null && str.trim().length() > 0) {
            try {
                String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    for (String str2 : strArr) {
                        if (str.equals(str2)) {
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m3375a(int i) {
        FileReader fileReader;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
        } catch (Throwable th) {
            th = th;
        }
        try {
            char[] cArr = new char[512];
            fileReader.read(cArr);
            int i2 = 0;
            while (i2 < cArr.length && cArr[i2] != 0) {
                i2++;
            }
            String substring = new String(cArr).substring(0, i2);
            try {
                fileReader.close();
            } catch (Throwable unused) {
            }
            return substring;
        } catch (Throwable th2) {
            th = th2;
            fileReader2 = fileReader;
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                String valueOf = String.valueOf(i);
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (Throwable unused2) {
                    }
                }
                return valueOf;
            } catch (Throwable th3) {
                if (fileReader2 != null) {
                    try {
                        fileReader2.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th3;
            }
        }
    }

    /* renamed from: c */
    public static String m3381c(Context context) {
        CharSequence applicationLabel;
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (packageManager != null && applicationInfo != null && (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) != null) {
                return applicationLabel.toString();
            }
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: d */
    public static Map<String, String> m3382d(Context context) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
            if (obj != null) {
                hashMap.put("BUGLY_DISABLE", obj.toString());
            }
            Object obj2 = applicationInfo.metaData.get("BUGLY_APPID");
            if (obj2 != null) {
                hashMap.put("BUGLY_APPID", obj2.toString());
            }
            Object obj3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
            if (obj3 != null) {
                hashMap.put("BUGLY_APP_CHANNEL", obj3.toString());
            }
            Object obj4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
            if (obj4 != null) {
                hashMap.put("BUGLY_APP_VERSION", obj4.toString());
            }
            Object obj5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
            if (obj5 != null) {
                hashMap.put("BUGLY_ENABLE_DEBUG", obj5.toString());
            }
            Object obj6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
            if (obj6 != null) {
                hashMap.put("com.tencent.rdm.uuid", obj6.toString());
            }
            return hashMap;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static List<String> m3378a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get("BUGLY_DISABLE");
            if (str != null && str.length() != 0) {
                String[] split = str.split(",");
                for (int i = 0; i < split.length; i++) {
                    split[i] = split[i].trim();
                }
                return Arrays.asList(split);
            }
            return null;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static String m3377a(byte[] bArr) {
        X509Certificate x509Certificate;
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length > 0) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                if (certificateFactory == null || (x509Certificate = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArr))) == null) {
                    return null;
                }
                sb.append("Issuer|");
                Principal issuerDN = x509Certificate.getIssuerDN();
                if (issuerDN != null) {
                    sb.append(issuerDN.toString());
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
                sb.append("\n");
                sb.append("SerialNumber|");
                BigInteger serialNumber = x509Certificate.getSerialNumber();
                if (issuerDN != null) {
                    sb.append(serialNumber.toString(16));
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
                sb.append("\n");
                sb.append("NotBefore|");
                Date notBefore = x509Certificate.getNotBefore();
                if (issuerDN != null) {
                    sb.append(notBefore.toString());
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
                sb.append("\n");
                sb.append("NotAfter|");
                Date notAfter = x509Certificate.getNotAfter();
                if (issuerDN != null) {
                    sb.append(notAfter.toString());
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
                sb.append("\n");
                sb.append("SHA1|");
                String m3859a = C5942z.m3859a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
                if (m3859a != null && m3859a.length() > 0) {
                    sb.append(m3859a.toString());
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
                sb.append("\n");
                sb.append("MD5|");
                String m3859a2 = C5942z.m3859a(MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(x509Certificate.getEncoded()));
                if (m3859a2 != null && m3859a2.length() > 0) {
                    sb.append(m3859a2.toString());
                } else {
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                }
            } catch (CertificateException e) {
                if (!C5940x.m3819a(e)) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return sb.length() == 0 ? EnvironmentCompat.MEDIA_UNKNOWN : sb.toString();
    }

    /* renamed from: e */
    public static String m3383e(Context context) {
        Signature[] signatureArr;
        String m3376a = m3376a(context);
        if (m3376a == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(m3376a, 64);
            if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length != 0) {
                return m3377a(packageInfo.signatures[0].toByteArray());
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    /* renamed from: f */
    public static boolean m3384f(Context context) {
        if (context == null) {
            return false;
        }
        if (f7697a == null) {
            f7697a = (ActivityManager) context.getSystemService("activity");
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            f7697a.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            C5940x.m3823c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: h */
    private static String m3386h(Context context) {
        String str = "";
        InputStream inputStream = null;
        try {
        } catch (IOException e) {
            C5940x.m3819a(e);
        }
        try {
            try {
                String string = C5942z.m3847a("DENGTA_META", context).getString("key_channelpath", "");
                if (C5942z.m3868a(string)) {
                    string = "channel.ini";
                }
                C5940x.m3818a("[AppInfo] Beacon channel file path: " + string, new Object[0]);
                if (!string.equals("")) {
                    inputStream = context.getAssets().open(string);
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    str = properties.getProperty("CHANNEL", "");
                    C5940x.m3818a("[AppInfo] Beacon channel read from assert: " + str, new Object[0]);
                    if (!C5942z.m3868a(str)) {
                        return str;
                    }
                }
            } catch (Exception unused) {
                C5940x.m3824d("[AppInfo] Failed to get get beacon channel", new Object[0]);
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return str;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    C5940x.m3819a(e2);
                }
            }
        }
    }

    /* renamed from: i */
    private static String m3387i(Context context) {
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("CHANNEL_DENGTA");
            return obj != null ? obj.toString() : "";
        } catch (Throwable th) {
            C5940x.m3824d("[AppInfo] Failed to read beacon channel from manifest.", new Object[0]);
            C5940x.m3819a(th);
            return "";
        }
    }

    /* renamed from: g */
    public static String m3385g(Context context) {
        if (context == null) {
            return "";
        }
        String m3386h = m3386h(context);
        return !C5942z.m3868a(m3386h) ? m3386h : m3387i(context);
    }
}
