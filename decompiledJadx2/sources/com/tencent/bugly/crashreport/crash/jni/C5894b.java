package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.jni.b */
/* loaded from: classes7.dex */
public final class C5894b {

    /* renamed from: a */
    private static List<File> f8009a = new ArrayList();

    /* renamed from: d */
    private static Map<String, Integer> m3609d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split = str2.split(":");
                if (split.length != 2) {
                    C5940x.m3825e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split[0], Integer.valueOf(Integer.parseInt(split[1])));
            }
            return hashMap;
        } catch (Exception e) {
            C5940x.m3825e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m3601a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static CrashDetailBean m3599a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap hashMap;
        if (map == null) {
            return null;
        }
        if (C5873a.m3389a(context) == null) {
            C5940x.m3825e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            C5940x.m3825e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> m3609d = m3609d(str3);
        if (m3609d == null) {
            C5940x.m3825e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            m3609d.get("sino").intValue();
            m3609d.get("sud").intValue();
            String str4 = map.get("soVersion");
            if (str4 == null) {
                C5940x.m3825e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map.get("errorAddr");
            String str6 = EnvironmentCompat.MEDIA_UNKNOWN;
            String str7 = str5 == null ? EnvironmentCompat.MEDIA_UNKNOWN : str5;
            String str8 = map.get("codeMsg");
            if (str8 == null) {
                str8 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            String str9 = map.get("tombPath");
            String str10 = str9 == null ? EnvironmentCompat.MEDIA_UNKNOWN : str9;
            String str11 = map.get("signalName");
            if (str11 == null) {
                str11 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            map.get("errnoMsg");
            String str12 = map.get("stack");
            if (str12 == null) {
                str12 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            String str13 = map.get("jstack");
            if (str13 != null) {
                str12 = str12 + "java:\n" + str13;
            }
            Integer num = m3609d.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str8;
                str2 = str11;
            } else {
                str2 = str11 + "(" + str8 + ")";
                str = "KERNEL";
            }
            String str14 = map.get("nativeLog");
            byte[] m3872a = (str14 == null || str14.isEmpty()) ? null : C5942z.m3872a((File) null, str14, "BuglyNativeLog.txt");
            String str15 = map.get("sendingProcess");
            if (str15 == null) {
                str15 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            Integer num2 = m3609d.get("spd");
            if (num2 != null) {
                str15 = str15 + "(" + num2 + ")";
            }
            String str16 = str15;
            String str17 = map.get("threadName");
            if (str17 == null) {
                str17 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            Integer num3 = m3609d.get(LinkFormat.END_POINT_TYPE);
            if (num3 != null) {
                str17 = str17 + "(" + num3 + ")";
            }
            String str18 = str17;
            String str19 = map.get("processName");
            if (str19 != null) {
                str6 = str19;
            }
            Integer num4 = m3609d.get(LinkFormat.END_POINT);
            if (num4 != null) {
                str6 = str6 + "(" + num4 + ")";
            }
            String str20 = str6;
            String str21 = map.get("key-value");
            if (str21 != null) {
                HashMap hashMap2 = new HashMap();
                String[] split = str21.split("\n");
                int length = split.length;
                int i = 0;
                while (i < length) {
                    String[] split2 = split[i].split("=");
                    String[] strArr = split;
                    if (split2.length == 2) {
                        hashMap2.put(split2[0], split2[1]);
                    }
                    i++;
                    split = strArr;
                }
                hashMap = hashMap2;
            } else {
                hashMap = null;
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str20, str18, (m3609d.get("etms").intValue() / 1000) + (m3609d.get("ets").intValue() * 1000), str2, str7, m3601a(str12), str, str16, str10, map.get("sysLogPath"), map.get("jniLogPath"), str4, m3872a, hashMap, false, false);
            if (packageCrashDatas != null) {
                String str22 = map.get("userId");
                if (str22 != null) {
                    C5940x.m3823c("[Native record info] userId: %s", str22);
                    packageCrashDatas.f7860m = str22;
                }
                String str23 = map.get("sysLog");
                if (str23 != null) {
                    packageCrashDatas.f7870w = str23;
                }
                String str24 = map.get("appVersion");
                if (str24 != null) {
                    C5940x.m3823c("[Native record info] appVersion: %s", str24);
                    packageCrashDatas.f7853f = str24;
                }
                String str25 = map.get("isAppForeground");
                if (str25 != null) {
                    C5940x.m3823c("[Native record info] isAppForeground: %s", str25);
                    packageCrashDatas.f7838N = str25.equalsIgnoreCase("true");
                }
                String str26 = map.get("launchTime");
                if (str26 != null) {
                    C5940x.m3823c("[Native record info] launchTime: %s", str26);
                    try {
                        packageCrashDatas.f7837M = Long.parseLong(str26);
                    } catch (NumberFormatException e) {
                        if (!C5940x.m3819a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                packageCrashDatas.f7873z = null;
                packageCrashDatas.f7858k = true;
            }
            return packageCrashDatas;
        } catch (Throwable th) {
            C5940x.m3825e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m3600a(BufferedInputStream bufferedInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bufferedInputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                try {
                    int read = bufferedInputStream.read();
                    if (read == -1) {
                        byteArrayOutputStream.close();
                        break;
                    }
                    if (read == 0) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                        byteArrayOutputStream.close();
                        return str;
                    }
                    byteArrayOutputStream.write(read);
                } catch (Throwable th) {
                    th = th;
                    try {
                        C5940x.m3819a(th);
                        return null;
                    } finally {
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.BufferedInputStream] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* renamed from: a */
    public static CrashDetailBean m3598a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        String str2;
        String m3600a;
        if (context == null || str == null || nativeExceptionHandler == null) {
            C5940x.m3825e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            ?? canRead = file.canRead();
            try {
                if (canRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    } catch (IOException e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        canRead = 0;
                        if (canRead != 0) {
                            try {
                                canRead.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        String m3600a2 = m3600a(bufferedInputStream);
                        if (m3600a2 != null && m3600a2.equals("NATIVE_RQD_REPORT")) {
                            HashMap hashMap = new HashMap();
                            loop0: while (true) {
                                str2 = null;
                                while (true) {
                                    m3600a = m3600a(bufferedInputStream);
                                    if (m3600a == null) {
                                        break loop0;
                                    }
                                    if (str2 == null) {
                                        str2 = m3600a;
                                    }
                                }
                                hashMap.put(str2, m3600a);
                            }
                            if (str2 != null) {
                                C5940x.m3825e("record not pair! drop! %s", str2);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                return null;
                            }
                            CrashDetailBean m3599a = m3599a(context, hashMap, nativeExceptionHandler);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            return m3599a;
                        }
                        C5940x.m3825e("record read fail! %s", m3600a2);
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                        return null;
                    } catch (IOException e6) {
                        e = e6;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x006c, code lost:
    
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0070, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0071, code lost:
    
        com.tencent.bugly.proguard.C5940x.m3819a(r9);
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m3606b(String str, String str2) {
        BufferedReader m3849a = C5942z.m3849a(str, "reg_record.txt");
        if (m3849a == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = m3849a.readLine();
            if (readLine != null && readLine.startsWith(str2)) {
                int i = 18;
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    String readLine2 = m3849a.readLine();
                    if (readLine2 == null) {
                        break;
                    }
                    if (i2 % 4 == 0) {
                        if (i2 > 0) {
                            sb.append("\n");
                        }
                        sb.append("  ");
                    } else {
                        if (readLine2.length() > 16) {
                            i = 28;
                        }
                        sb.append("                ".substring(0, i - i3));
                    }
                    i3 = readLine2.length();
                    sb.append(readLine2);
                    i2++;
                }
                sb.append("\n");
                return sb.toString();
            }
            return null;
        } catch (Throwable th) {
            try {
                C5940x.m3819a(th);
                if (m3849a != null) {
                    try {
                        m3849a.close();
                    } catch (Exception e) {
                        C5940x.m3819a(e);
                    }
                }
                return null;
            } finally {
                if (m3849a != null) {
                    try {
                        m3849a.close();
                    } catch (Exception e2) {
                        C5940x.m3819a(e2);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0041, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0045, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0046, code lost:
    
        com.tencent.bugly.proguard.C5940x.m3819a(r3);
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String m3607c(String str, String str2) {
        BufferedReader m3849a = C5942z.m3849a(str, "map_record.txt");
        if (m3849a == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = m3849a.readLine();
            if (readLine != null && readLine.startsWith(str2)) {
                while (true) {
                    String readLine2 = m3849a.readLine();
                    if (readLine2 == null) {
                        break;
                    }
                    sb.append("  ");
                    sb.append(readLine2);
                    sb.append("\n");
                }
                return sb.toString();
            }
            return null;
        } catch (Throwable th) {
            try {
                C5940x.m3819a(th);
                if (m3849a != null) {
                    try {
                        m3849a.close();
                    } catch (Exception e) {
                        C5940x.m3819a(e);
                    }
                }
                return null;
            } finally {
                if (m3849a != null) {
                    try {
                        m3849a.close();
                    } catch (Exception e2) {
                        C5940x.m3819a(e2);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static String m3603a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String m3606b = m3606b(str, str2);
        if (m3606b != null && !m3606b.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(m3606b);
        }
        String m3607c = m3607c(str, str2);
        if (m3607c != null && !m3607c.isEmpty()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("System SO infos:\n");
            sb.append(m3607c);
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m3605b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    /* renamed from: c */
    public static void m3608c(String str) {
        File[] listFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        C5940x.m3823c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            C5940x.m3819a(th);
        }
    }

    /* renamed from: a */
    public static void m3604a(boolean z, String str) {
        if (str != null) {
            f8009a.add(new File(str, "rqd_record.eup"));
            f8009a.add(new File(str, "reg_record.txt"));
            f8009a.add(new File(str, "map_record.txt"));
            f8009a.add(new File(str, "backup_record.txt"));
            if (z) {
                m3608c(str);
            }
        }
        List<File> list = f8009a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : f8009a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                C5940x.m3823c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    /* renamed from: a */
    public static String m3602a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                C5940x.m3818a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                f8009a.add(file);
                C5940x.m3823c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return C5942z.m3856a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                if (Pattern.compile(str2 + "[ ]*:").matcher(readLine).find()) {
                                    sb.append(readLine);
                                    sb.append("\n");
                                }
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                try {
                                    C5940x.m3819a(th);
                                    sb.append("\n[error:" + th.toString() + "]");
                                    String sb2 = sb.toString();
                                    if (bufferedReader == null) {
                                        return sb2;
                                    }
                                    bufferedReader.close();
                                    sb = sb2;
                                    return sb;
                                } catch (Throwable th2) {
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e) {
                                            C5940x.m3819a(e);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                        String sb3 = sb.toString();
                        bufferedReader.close();
                        sb = sb3;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = null;
                    }
                    return sb;
                } catch (Exception e2) {
                    C5940x.m3819a(e2);
                    return sb;
                }
            }
        }
        return null;
    }
}
