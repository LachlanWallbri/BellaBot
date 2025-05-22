package com.iflytek.idata.extension;

import android.text.TextUtils;
import com.amazonaws.services.p048s3.util.Mimetypes;
import com.iflytek.cloud.thirdparty.C3723bh;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* renamed from: com.iflytek.idata.extension.b */
/* loaded from: classes3.dex */
public class C3752b extends Thread {

    /* renamed from: a */
    private int f3502a = 20000;

    /* renamed from: b */
    private a f3503b = null;

    /* renamed from: c */
    private volatile boolean f3504c = false;

    /* renamed from: d */
    private URL f3505d = null;

    /* renamed from: e */
    private ArrayList<byte[]> f3506e = new ArrayList<>();

    /* renamed from: f */
    private Object f3507f = null;

    /* renamed from: g */
    private int f3508g = 0;

    /* renamed from: com.iflytek.idata.extension.b$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo2328a(C3752b c3752b, byte[] bArr);

        /* renamed from: a */
        void mo2329a(Exception exc);
    }

    /* renamed from: a */
    public static URL m2330a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            str = str + str2;
        }
        C3723bh.m2046a("Collector", "url:" + str);
        return new URL(str);
    }

    /* renamed from: a */
    private void m2331a(Exception exc) {
        if (this.f3503b == null || this.f3504c) {
            return;
        }
        this.f3503b.mo2329a(exc);
    }

    /* renamed from: a */
    public static boolean m2332a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("rsp")) {
                return jSONObject.getJSONObject("rsp").getInt("code") != 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    private byte[] m2333a(InputStream inputStream) {
        int read;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (!this.f3504c && (read = bufferedInputStream.read(bArr, 0, 1024)) != -1) {
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006e A[Catch: Exception -> 0x0071, TRY_LEAVE, TryCatch #5 {Exception -> 0x0071, blocks: (B:33:0x0069, B:28:0x006e), top: B:32:0x0069 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m2334b() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        byte b = 0;
        InputStream inputStream2 = null;
        byte b2 = 0;
        try {
            try {
                try {
                    httpURLConnection = (HttpURLConnection) this.f3505d.openConnection();
                } catch (Exception e) {
                    e = e;
                    httpURLConnection = null;
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                    }
                    if (0 != 0) {
                    }
                    throw th;
                }
                try {
                    httpURLConnection.setConnectTimeout(this.f3502a);
                    httpURLConnection.setReadTimeout(this.f3502a);
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 == responseCode) {
                        inputStream2 = httpURLConnection.getInputStream();
                        m2335b(m2333a(inputStream2));
                    } else {
                        m2331a(new Exception("HttpRequest Failed: " + responseCode));
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    m2331a(e);
                    if (0 != 0) {
                        (b2 == true ? 1 : 0).close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                    httpURLConnection.disconnect();
                }
                httpURLConnection.disconnect();
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (Exception unused) {
                        throw th;
                    }
                }
                if (0 != 0) {
                    (b == true ? 1 : 0).disconnect();
                }
                throw th;
            }
        } catch (Exception unused2) {
        }
    }

    /* renamed from: b */
    private void m2335b(byte[] bArr) {
        if (this.f3503b == null || this.f3504c) {
            return;
        }
        this.f3503b.mo2328a(this, bArr);
    }

    /* renamed from: c */
    private int m2336c() {
        int i = 0;
        for (int i2 = 0; i2 < this.f3506e.size(); i2++) {
            i += this.f3506e.get(i2).length;
        }
        return i;
    }

    /* renamed from: a */
    public void m2337a() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        byte b = 0;
        InputStream inputStream2 = null;
        byte b2 = 0;
        try {
            try {
                try {
                    httpURLConnection = (HttpURLConnection) this.f3505d.openConnection();
                } catch (Exception e) {
                    e = e;
                    httpURLConnection = null;
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Exception unused) {
                            throw th;
                        }
                    }
                    if (0 != 0) {
                        (b == true ? 1 : 0).disconnect();
                    }
                    throw th;
                }
                try {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(this.f3502a);
                    httpURLConnection.setReadTimeout(this.f3502a);
                    httpURLConnection.setRequestProperty("Content-length", "" + m2336c());
                    httpURLConnection.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setRequestProperty("Content-Type", Mimetypes.MIMETYPE_GZIP);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    Iterator<byte[]> it = this.f3506e.iterator();
                    while (it.hasNext()) {
                        outputStream.write(it.next());
                    }
                    outputStream.flush();
                    outputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 == responseCode) {
                        inputStream2 = httpURLConnection.getInputStream();
                        m2335b(m2333a(inputStream2));
                    } else {
                        m2331a(new Exception("Http Request Failed: " + responseCode));
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    m2331a(e);
                    if (0 != 0) {
                        (b2 == true ? 1 : 0).close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                    httpURLConnection.disconnect();
                }
                httpURLConnection.disconnect();
            } catch (Exception unused2) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    public void m2338a(int i) {
        this.f3508g = i;
    }

    /* renamed from: a */
    public void m2339a(a aVar) {
        this.f3503b = aVar;
    }

    /* renamed from: a */
    public void m2340a(String str, String str2, byte[] bArr) {
        this.f3506e.clear();
        m2341a(bArr);
        try {
            this.f3505d = m2330a(str, str2);
        } catch (MalformedURLException e) {
            C3723bh.m2048c("Collector", "url error:" + e);
        }
    }

    /* renamed from: a */
    public void m2341a(byte[] bArr) {
        if (bArr != null) {
            this.f3506e.add(bArr);
        }
    }

    /* renamed from: b */
    public void m2342b(int i) {
        this.f3502a = i;
    }

    /* renamed from: b */
    public void m2343b(a aVar) {
        this.f3503b = aVar;
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.f3508g == 1) {
            m2337a();
        } else {
            m2334b();
        }
    }
}
