package com.loc;

import android.os.Build;
import com.iflytek.aiui.constant.InternalConstant;
import com.loc.C3834bi;
import com.loc.C3836bk;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.PushbackInputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil.java */
/* renamed from: com.loc.bl */
/* loaded from: classes4.dex */
public final class C3837bl {

    /* renamed from: a */
    private static InterfaceC3838bm f3683a;

    /* renamed from: b */
    private int f3684b;

    /* renamed from: c */
    private int f3685c;

    /* renamed from: d */
    private boolean f3686d;

    /* renamed from: e */
    private SSLContext f3687e;

    /* renamed from: f */
    private Proxy f3688f;

    /* renamed from: g */
    private volatile boolean f3689g;

    /* renamed from: h */
    private long f3690h;

    /* renamed from: i */
    private long f3691i;

    /* renamed from: j */
    private String f3692j;

    /* renamed from: k */
    private C3834bi.a f3693k;

    /* renamed from: l */
    private HostnameVerifier f3694l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3837bl(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3837bl(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, (byte) 0);
    }

    private C3837bl(int i, int i2, Proxy proxy, boolean z, byte b) {
        this.f3689g = false;
        this.f3690h = -1L;
        this.f3691i = 0L;
        this.f3694l = new HostnameVerifier() { // from class: com.loc.bl.1
            @Override // javax.net.ssl.HostnameVerifier
            public final boolean verify(String str, SSLSession sSLSession) {
                HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                return defaultHostnameVerifier.verify("*.amap.com", sSLSession) || defaultHostnameVerifier.verify("*.apilocate.amap.com", sSLSession);
            }
        };
        this.f3684b = i;
        this.f3685c = i2;
        this.f3688f = proxy;
        this.f3686d = z;
        this.f3693k = null;
        try {
            this.f3692j = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            C3897w.m3249a(th, "HttpUrlUtil", "initCSID");
        }
        if (z) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                this.f3687e = sSLContext;
            } catch (Throwable th2) {
                C3897w.m3249a(th2, "HttpUtil", "HttpUtil");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0121 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private C3840bo m2616a(HttpURLConnection httpURLConnection) throws C3884j, IOException {
        InputStream inputStream;
        InputStream inputStream2;
        PushbackInputStream pushbackInputStream;
        List<String> list;
        ByteArrayOutputStream byteArrayOutputStream = null;
        r2 = null;
        InputStream inputStream3 = null;
        byteArrayOutputStream = null;
        try {
            httpURLConnection.connect();
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                String str = "";
                if (headerFields != null && (list = headerFields.get("gsid")) != null && list.size() > 0) {
                    str = list.get(0);
                }
                throw new C3884j("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode + "  " + str + " " + this.f3692j);
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                inputStream2 = httpURLConnection.getInputStream();
                try {
                    pushbackInputStream = new PushbackInputStream(inputStream2, 2);
                } catch (IOException e) {
                    e = e;
                    pushbackInputStream = null;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    inputStream = pushbackInputStream;
                    try {
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable th2) {
                                C3897w.m3249a(th2, "HttpUrlUtil", "parseResult");
                            }
                        }
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (Throwable th3) {
                                C3897w.m3249a(th3, "HttpUrlUtil", "parseResult");
                            }
                        }
                        if (pushbackInputStream != null) {
                            try {
                                pushbackInputStream.close();
                            } catch (Throwable th4) {
                                C3897w.m3249a(th4, "HttpUrlUtil", "parseResult");
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th5) {
                                C3897w.m3249a(th5, "HttpUrlUtil", "parseResult");
                            }
                        }
                        if (httpURLConnection == null) {
                            throw th;
                        }
                        try {
                            httpURLConnection.disconnect();
                            throw th;
                        } catch (Throwable th6) {
                            C3897w.m3249a(th6, "HttpUrlUtil", "parseResult");
                            throw th;
                        }
                    }
                } catch (Throwable th7) {
                    th = th7;
                    pushbackInputStream = null;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    inputStream = pushbackInputStream;
                    if (byteArrayOutputStream != null) {
                    }
                    if (inputStream2 != null) {
                    }
                    if (pushbackInputStream != null) {
                    }
                    if (inputStream != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                }
                try {
                    byte[] bArr = new byte[2];
                    pushbackInputStream.read(bArr);
                    pushbackInputStream.unread(bArr);
                    inputStream3 = (bArr[0] == 31 && bArr[1] == -117) ? new GZIPInputStream(pushbackInputStream) : pushbackInputStream;
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = inputStream3.read(bArr2);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr2, 0, read);
                    }
                    if (f3683a != null) {
                        f3683a.mo2625a();
                    }
                    C3840bo c3840bo = new C3840bo();
                    c3840bo.f3699a = byteArrayOutputStream2.toByteArray();
                    c3840bo.f3700b = headerFields;
                    c3840bo.f3701c = this.f3692j;
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Throwable th8) {
                        C3897w.m3249a(th8, "HttpUrlUtil", "parseResult");
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable th9) {
                            C3897w.m3249a(th9, "HttpUrlUtil", "parseResult");
                        }
                    }
                    try {
                        pushbackInputStream.close();
                    } catch (Throwable th10) {
                        C3897w.m3249a(th10, "HttpUrlUtil", "parseResult");
                    }
                    try {
                        inputStream3.close();
                    } catch (Throwable th11) {
                        C3897w.m3249a(th11, "HttpUrlUtil", "parseResult");
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th12) {
                            C3897w.m3249a(th12, "HttpUrlUtil", "parseResult");
                        }
                    }
                    return c3840bo;
                } catch (IOException e2) {
                    e = e2;
                    inputStream = inputStream3;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    throw e;
                } catch (Throwable th13) {
                    th = th13;
                    inputStream = inputStream3;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                    }
                    if (inputStream2 != null) {
                    }
                    if (pushbackInputStream != null) {
                    }
                    if (inputStream != null) {
                    }
                    if (httpURLConnection == null) {
                    }
                }
            } catch (IOException e3) {
                e = e3;
                inputStream2 = null;
                pushbackInputStream = null;
            } catch (Throwable th14) {
                th = th14;
                inputStream2 = null;
                pushbackInputStream = null;
            }
        } catch (IOException e4) {
            e = e4;
            inputStream = null;
            inputStream2 = null;
            pushbackInputStream = null;
        } catch (Throwable th15) {
            th = th15;
            inputStream = null;
            inputStream2 = null;
            pushbackInputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2617a(Map<String, String> map) {
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
    private HttpURLConnection m2618a(String str, Map<String, String> map, boolean z) throws IOException {
        URLConnection uRLConnection;
        HttpURLConnection httpURLConnection;
        C3888n.m3148a();
        URL url = new URL(str);
        C3834bi.a aVar = this.f3693k;
        if (aVar != null) {
            Proxy proxy = this.f3688f;
            uRLConnection = aVar.m2603a();
        } else {
            uRLConnection = null;
        }
        if (uRLConnection == null) {
            Proxy proxy2 = this.f3688f;
            uRLConnection = proxy2 != null ? url.openConnection(proxy2) : url.openConnection();
        }
        if (this.f3686d) {
            httpURLConnection = (HttpsURLConnection) uRLConnection;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(this.f3687e.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(this.f3694l);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnection;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        m2620a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    /* renamed from: a */
    public static void m2619a(InterfaceC3838bm interfaceC3838bm) {
        f3683a = interfaceC3838bm;
    }

    /* renamed from: a */
    private void m2620a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty(InternalConstant.KEY_CSID, this.f3692j);
        } catch (Throwable th) {
            C3897w.m3249a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.f3684b);
        httpURLConnection.setReadTimeout(this.f3685c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C3840bo m2621a(String str, Map<String, String> map, byte[] bArr) throws C3884j {
        try {
            HttpURLConnection m2618a = m2618a(str, map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(m2618a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            return m2616a(m2618a);
        } catch (C3884j e) {
            C3897w.m3249a(e, "HttpUrlUtil", "makePostReqeust");
            throw e;
        } catch (ConnectException e2) {
            e2.printStackTrace();
            throw new C3884j("http连接失败 - ConnectionException");
        } catch (SocketTimeoutException e3) {
            e3.printStackTrace();
            throw new C3884j("socket 连接超时 - SocketTimeoutException");
        } catch (InterruptedIOException unused) {
            throw new C3884j("未知的错误");
        } catch (MalformedURLException e4) {
            e4.printStackTrace();
            throw new C3884j("url异常 - MalformedURLException");
        } catch (SocketException e5) {
            e5.printStackTrace();
            throw new C3884j("socket 连接异常 - SocketException");
        } catch (UnknownHostException e6) {
            e6.printStackTrace();
            throw new C3884j("未知主机 - UnKnowHostException");
        } catch (IOException e7) {
            e7.printStackTrace();
            throw new C3884j("IO 操作异常 - IOException");
        } catch (Throwable th) {
            C3897w.m3249a(th, "HttpUrlUtil", "makePostReqeust");
            throw new C3884j("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2622a() {
        this.f3691i = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m2623a(String str, Map<String, String> map, Map<String, String> map2, C3836bk.a aVar) {
        HttpURLConnection httpURLConnection;
        int read;
        if (aVar == null) {
            return;
        }
        InputStream inputStream = null;
        try {
            String m2617a = m2617a(map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (m2617a != null) {
                stringBuffer.append("?");
                stringBuffer.append(m2617a);
            }
            httpURLConnection = m2618a(stringBuffer.toString(), map, false);
            try {
                httpURLConnection.setRequestProperty("RANGE", "bytes=" + this.f3691i + "-");
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                boolean z = true;
                boolean z2 = responseCode != 200;
                if (responseCode == 206) {
                    z = false;
                }
                if (z2 & z) {
                    new C3884j("网络异常原因：" + httpURLConnection.getResponseMessage() + " 网络异常状态码：" + responseCode);
                    aVar.mo2473d();
                }
                InputStream inputStream2 = httpURLConnection.getInputStream();
                byte[] bArr = new byte[1024];
                while (!Thread.interrupted() && !this.f3689g && (read = inputStream2.read(bArr, 0, 1024)) > 0 && (this.f3690h == -1 || this.f3691i < this.f3690h)) {
                    if (read == 1024) {
                        aVar.mo2470a(bArr, this.f3691i);
                    } else {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        aVar.mo2470a(bArr2, this.f3691i);
                    }
                    this.f3691i += read;
                }
                if (this.f3689g) {
                    aVar.mo2471b();
                } else {
                    aVar.mo2472c();
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e) {
                        C3897w.m3249a(e, "HttpUrlUtil", "makeDownloadGetRequest");
                    }
                }
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Throwable th) {
                        C3897w.m3249a(th, "HttpUrlUtil", "makeDownloadGetRequest");
                    }
                }
            } catch (Throwable unused) {
                try {
                    aVar.mo2473d();
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            C3897w.m3249a(e2, "HttpUrlUtil", "makeDownloadGetRequest");
                        }
                    }
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Throwable th2) {
                            C3897w.m3249a(th2, "HttpUrlUtil", "makeDownloadGetRequest");
                        }
                    }
                } catch (Throwable th3) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            C3897w.m3249a(e3, "HttpUrlUtil", "makeDownloadGetRequest");
                        }
                    }
                    if (httpURLConnection == null) {
                        throw th3;
                    }
                    try {
                        httpURLConnection.disconnect();
                        throw th3;
                    } catch (Throwable th4) {
                        C3897w.m3249a(th4, "HttpUrlUtil", "makeDownloadGetRequest");
                        throw th3;
                    }
                }
            }
        } catch (Throwable unused2) {
            httpURLConnection = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final void m2624b() {
        this.f3690h = -1L;
    }
}
