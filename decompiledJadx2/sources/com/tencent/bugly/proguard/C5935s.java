package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.C5874b;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.s */
/* loaded from: classes7.dex */
public final class C5935s {

    /* renamed from: b */
    private static C5935s f8217b;

    /* renamed from: a */
    public Map<String, String> f8218a = null;

    /* renamed from: c */
    private Context f8219c;

    private C5935s(Context context) {
        this.f8219c = context;
    }

    /* renamed from: a */
    public static C5935s m3767a(Context context) {
        if (f8217b == null) {
            f8217b = new C5935s(context);
        }
        return f8217b;
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x018b A[Catch: all -> 0x017e, TRY_LEAVE, TryCatch #12 {all -> 0x017e, blocks: (B:24:0x009b, B:26:0x00a3, B:29:0x00b4, B:39:0x00b2, B:85:0x00dd, B:103:0x00e5, B:91:0x0116, B:94:0x0120, B:52:0x0140, B:57:0x0163, B:70:0x0185, B:72:0x018b), top: B:23:0x009b }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final byte[] m3772a(String str, byte[] bArr, RunnableC5938v runnableC5938v, Map<String, String> map) {
        int i;
        long j;
        int i2;
        int responseCode;
        Object[] objArr;
        int i3;
        byte[] bArr2 = null;
        int i4 = 0;
        if (str == null) {
            C5940x.m3825e("Failed for no URL.", new Object[0]);
            return null;
        }
        long length = bArr == null ? 0L : bArr.length;
        int i5 = 1;
        C5940x.m3823c("request: %s, send: %d (pid=%d | tid=%d)", str, Long.valueOf(length), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        String str2 = str;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 <= 0 && i7 <= 0) {
            if (i8 != 0) {
                i = i6;
                i8 = i4;
            } else {
                int i9 = i6 + 1;
                if (i9 > i5) {
                    C5940x.m3823c("try time: " + i9, new Object[i4]);
                    SystemClock.sleep(((long) new Random(System.currentTimeMillis()).nextInt(10000)) + 10000);
                }
                i = i9;
            }
            String m3455c = C5874b.m3455c(this.f8219c);
            if (m3455c == null) {
                C5940x.m3824d("Failed to request for network not avail", new Object[i4]);
                i6 = i;
                i5 = 1;
            } else {
                runnableC5938v.m3808a(length);
                HttpURLConnection m3769a = m3769a(str2, bArr, m3455c, map);
                if (m3769a != null) {
                    try {
                        try {
                            responseCode = m3769a.getResponseCode();
                        } finally {
                        }
                    } catch (IOException e) {
                        e = e;
                        j = length;
                    }
                    if (responseCode == 200) {
                        this.f8218a = m3770a(m3769a);
                        byte[] m3771b = m3771b(m3769a);
                        runnableC5938v.m3809b(m3771b == null ? 0L : m3771b.length);
                        try {
                            m3769a.disconnect();
                        } catch (Throwable th) {
                            if (!C5940x.m3819a(th)) {
                                th.printStackTrace();
                            }
                        }
                        return m3771b;
                    }
                    if (responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307) {
                        try {
                            String headerField = m3769a.getHeaderField("Location");
                            if (headerField == null) {
                                try {
                                    C5940x.m3825e("Failed to redirect: %d" + responseCode, new Object[0]);
                                    try {
                                        m3769a.disconnect();
                                        return null;
                                    } catch (Throwable th2) {
                                        if (C5940x.m3819a(th2)) {
                                            return null;
                                        }
                                        th2.printStackTrace();
                                        return null;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    j = length;
                                    i8 = 1;
                                    i2 = 1;
                                    if (!C5940x.m3819a(e)) {
                                    }
                                    try {
                                        m3769a.disconnect();
                                    } catch (Throwable th3) {
                                        if (!C5940x.m3819a(th3)) {
                                            th3.printStackTrace();
                                        }
                                    }
                                    i6 = i;
                                    i4 = 0;
                                    i5 = i2;
                                    length = j;
                                    bArr2 = null;
                                }
                            } else {
                                i7++;
                                try {
                                    objArr = new Object[2];
                                    objArr[0] = Integer.valueOf(responseCode);
                                    i2 = 1;
                                } catch (IOException e3) {
                                    e = e3;
                                    i2 = 1;
                                }
                                try {
                                    objArr[1] = headerField;
                                    C5940x.m3823c("redirect code: %d ,to:%s", objArr);
                                    str2 = headerField;
                                    i8 = 1;
                                    i3 = 0;
                                } catch (IOException e4) {
                                    e = e4;
                                    str2 = headerField;
                                    j = length;
                                    i8 = i2;
                                    i = 0;
                                    if (!C5940x.m3819a(e)) {
                                        e.printStackTrace();
                                    }
                                    m3769a.disconnect();
                                    i6 = i;
                                    i4 = 0;
                                    i5 = i2;
                                    length = j;
                                    bArr2 = null;
                                }
                            }
                        } catch (IOException e5) {
                            e = e5;
                            i2 = 1;
                            j = length;
                            i8 = 1;
                        }
                    } else {
                        i2 = 1;
                        i3 = i;
                    }
                    try {
                        C5940x.m3824d("response code " + responseCode, new Object[0]);
                        j = length;
                        long contentLength = m3769a.getContentLength();
                        if (contentLength < 0) {
                            contentLength = 0;
                        }
                        try {
                            runnableC5938v.m3809b(contentLength);
                            try {
                                m3769a.disconnect();
                            } catch (Throwable th4) {
                                if (!C5940x.m3819a(th4)) {
                                    th4.printStackTrace();
                                }
                            }
                            i6 = i3;
                        } catch (IOException e6) {
                            e = e6;
                            i = i3;
                            if (!C5940x.m3819a(e)) {
                            }
                            m3769a.disconnect();
                            i6 = i;
                            i4 = 0;
                            i5 = i2;
                            length = j;
                            bArr2 = null;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        j = length;
                    }
                    i4 = 0;
                } else {
                    j = length;
                    i2 = 1;
                    C5940x.m3823c("Failed to execute post.", new Object[i4]);
                    runnableC5938v.m3809b(0L);
                    i6 = i;
                }
                i5 = i2;
                length = j;
                bArr2 = null;
            }
        }
        return bArr2;
    }

    /* renamed from: a */
    private static Map<String, String> m3770a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List<String> list = headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* renamed from: b */
    private static byte[] m3771b(HttpURLConnection httpURLConnection) {
        BufferedInputStream bufferedInputStream;
        if (httpURLConnection == null) {
            return null;
        }
        try {
            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        } catch (Throwable th) {
            th = th;
            bufferedInputStream = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return null;
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private HttpURLConnection m3769a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C5940x.m3825e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection m3768a = m3768a(str2, str);
        if (m3768a == null) {
            C5940x.m3825e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            m3768a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    m3768a.setRequestProperty(entry.getKey(), URLEncoder.encode(entry.getValue(), "utf-8"));
                }
            }
            m3768a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            m3768a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = m3768a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return m3768a;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            C5940x.m3825e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m3768a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (C5896a.m3622b() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(C5896a.m3622b());
            } else if (str != null && str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
            httpURLConnection.setConnectTimeout(BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
