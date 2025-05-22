package com.iflytek.aiui.pro;

import android.os.Build;
import com.iflytek.aiui.constant.InternalConstant;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.z */
/* loaded from: classes.dex */
public class C3652z {

    /* renamed from: a */
    private int f2730a;

    /* renamed from: b */
    private int f2731b;

    /* renamed from: c */
    private boolean f2732c;

    /* renamed from: d */
    private SSLContext f2733d;

    /* renamed from: e */
    private Proxy f2734e;

    /* renamed from: f */
    private volatile boolean f2735f;

    /* renamed from: g */
    private long f2736g;

    /* renamed from: h */
    private long f2737h;

    /* renamed from: i */
    private HostnameVerifier f2738i;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* renamed from: com.iflytek.aiui.pro.z$a */
    /* loaded from: classes.dex */
    class a implements HostnameVerifier {
        a() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.z$b */
    /* loaded from: classes4.dex */
    class b implements HostnameVerifier {
        b(C3652z c3652z) {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify("*.amap.com", sSLSession);
        }
    }

    public C3652z(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    C3652z(int i, int i2, Proxy proxy, boolean z) {
        this.f2735f = false;
        this.f2736g = -1L;
        this.f2737h = 0L;
        this.f2738i = new a();
        this.f2730a = i;
        this.f2731b = i2;
        this.f2734e = proxy;
        this.f2732c = z;
        if (z) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                this.f2733d = sSLContext;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m1630a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(key));
            sb.append("=");
            sb.append(URLEncoder.encode(value));
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static void m1631a() {
        try {
            C3573ac.m947b().submit(new Runnable() { // from class: com.iflytek.aiui.pro.z.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        C3573ac.m950c();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m1632a(C3571aa c3571aa) throws Exception {
        if (c3571aa == null) {
            throw new Exception("requeust is null");
        }
        if (c3571aa.mo925b() == null || "".equals(c3571aa.mo925b())) {
            throw new Exception("request url is empty");
        }
    }

    /* renamed from: a */
    private void m1633a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty(InternalConstant.KEY_CSID, UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        httpURLConnection.setConnectTimeout(this.f2730a);
        httpURLConnection.setReadTimeout(this.f2731b);
    }

    /* renamed from: a */
    public static byte[] m1634a(C3571aa c3571aa, boolean z) throws IOException, Exception {
        m1632a(c3571aa);
        byte[] m1639a = new C3652z(c3571aa.f2263x, c3571aa.f2264y, c3571aa.f2265z == null ? null : c3571aa.f2265z, z).m1639a(c3571aa.m932e(), c3571aa.mo919a(), c3571aa.m933f());
        m1631a();
        return m1639a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00cb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private byte[] m1635a(HttpURLConnection httpURLConnection) throws IOException, Exception {
        Throwable th;
        PushbackInputStream pushbackInputStream;
        InputStream inputStream;
        PushbackInputStream pushbackInputStream2;
        IOException e;
        ByteArrayOutputStream byteArrayOutputStream;
        PushbackInputStream pushbackInputStream3;
        PushbackInputStream pushbackInputStream4;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            httpURLConnection.getHeaderFields();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new Exception("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode);
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                inputStream = httpURLConnection.getInputStream();
                try {
                    pushbackInputStream3 = new PushbackInputStream(inputStream, 2);
                    try {
                        try {
                            byte[] bArr = new byte[2];
                            pushbackInputStream3.read(bArr);
                            pushbackInputStream3.unread(bArr);
                            GZIPInputStream gZIPInputStream = (bArr[0] == 31 && bArr[1] == -117) ? new GZIPInputStream(pushbackInputStream3) : pushbackInputStream3;
                            byte[] bArr2 = new byte[1024];
                            while (true) {
                                int read = gZIPInputStream.read(bArr2);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr2, 0, read);
                            }
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            try {
                                pushbackInputStream3.close();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            try {
                                gZIPInputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (Throwable th2) {
                                    th2.printStackTrace();
                                }
                            }
                            return byteArray;
                        } catch (Throwable th3) {
                            th = th3;
                            ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                            pushbackInputStream = null;
                            byteArrayOutputStream2 = byteArrayOutputStream3;
                            pushbackInputStream2 = pushbackInputStream3;
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                }
                            }
                            if (pushbackInputStream2 != null) {
                                try {
                                    pushbackInputStream2.close();
                                } catch (Exception e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (pushbackInputStream != null) {
                                try {
                                    pushbackInputStream.close();
                                } catch (Exception e9) {
                                    e9.printStackTrace();
                                }
                            }
                            if (httpURLConnection != null) {
                                throw th;
                            }
                            try {
                                httpURLConnection.disconnect();
                                throw th;
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                                throw th;
                            }
                        }
                    } catch (IOException e10) {
                        throw e10;
                    }
                } catch (IOException e11) {
                    throw e11;
                } catch (Throwable th5) {
                    th = th5;
                    pushbackInputStream4 = null;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    pushbackInputStream = pushbackInputStream4;
                    pushbackInputStream2 = pushbackInputStream4;
                    if (byteArrayOutputStream2 != null) {
                    }
                    if (inputStream != null) {
                    }
                    if (pushbackInputStream2 != null) {
                    }
                    if (pushbackInputStream != null) {
                    }
                    if (httpURLConnection != null) {
                    }
                }
            } catch (IOException e12) {
                e = e12;
                inputStream = null;
                pushbackInputStream3 = inputStream;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                inputStream = null;
                pushbackInputStream4 = null;
            }
        } catch (IOException e13) {
            e = e13;
            byteArrayOutputStream = null;
            inputStream = null;
        } catch (Throwable th7) {
            th = th7;
            pushbackInputStream = null;
            inputStream = null;
            pushbackInputStream2 = null;
        }
    }

    /* renamed from: a */
    HttpURLConnection m1636a(String str, Map<String, String> map, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        Proxy proxy = this.f2734e;
        URLConnection openConnection = proxy != null ? url.openConnection(proxy) : (HttpURLConnection) url.openConnection();
        if (this.f2732c) {
            httpURLConnection = (HttpsURLConnection) openConnection;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(this.f2733d.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(this.f2738i);
        } else {
            httpURLConnection = (HttpURLConnection) openConnection;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        m1633a(map, httpURLConnection);
        if (!z) {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    /* renamed from: a */
    public void m1637a(long j) {
        this.f2737h = j;
    }

    /* renamed from: a */
    public void m1638a(String str, Map<String, String> map, Map<String, String> map2, C3630o c3630o) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        int read;
        if (c3630o == null) {
            return;
        }
        InputStream inputStream = null;
        try {
            String m1630a = m1630a(map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (m1630a != null) {
                stringBuffer.append("?");
                stringBuffer.append(m1630a);
            }
            httpURLConnection = m1636a(stringBuffer.toString(), map, false);
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setRequestProperty("RANGE", "bytes=" + this.f2737h + "-");
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            boolean z = true;
            boolean z2 = responseCode != 200;
            if (responseCode == 206) {
                z = false;
            }
            if (z2 & z) {
                c3630o.m1458a(new Exception("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode));
            }
            inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (!Thread.interrupted() && !this.f2735f && (read = inputStream.read(bArr, 0, 1024)) > 0 && (this.f2736g == -1 || this.f2737h < this.f2736g)) {
                if (read == 1024) {
                    c3630o.m1459a(bArr, this.f2737h);
                } else {
                    byte[] bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    c3630o.m1459a(bArr2, this.f2737h);
                }
                this.f2737h += read;
            }
            if (!this.f2735f) {
                c3630o.m1460b();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        } catch (Throwable th5) {
            th = th5;
            try {
                c3630o.m1458a(th);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable th7) {
                        th7.printStackTrace();
                    }
                }
            } finally {
            }
        }
    }

    /* renamed from: a */
    byte[] m1639a(String str, Map<String, String> map, byte[] bArr) throws IOException, Exception {
        HttpURLConnection m1636a = m1636a(str, map, true);
        if (bArr != null && bArr.length > 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(m1636a.getOutputStream());
            dataOutputStream.write(bArr);
            dataOutputStream.close();
        }
        m1636a.connect();
        return m1635a(m1636a);
    }

    /* renamed from: b */
    public void m1640b(long j) {
        this.f2736g = j;
    }
}
