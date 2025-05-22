package com.iflytek.cloud.msc.util;

import android.text.TextUtils;
import com.amazonaws.services.p048s3.util.Mimetypes;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class HttpRequest extends Thread {
    public static final int CONNECT_GET = 0;
    public static final int CONNECT_POST = 1;
    private String mUpLoadParam;
    private int mTimeOut = 20000;
    private HttpRequestListener mListener = null;
    private volatile boolean mExit = false;
    private URL mUrl = null;
    private ArrayList<byte[]> postDatas = new ArrayList<>();
    private Object httpbuf = null;
    private int mConnectType = 0;

    /* loaded from: classes3.dex */
    public interface HttpRequestListener {
        void onError(SpeechError speechError);

        void onResult(HttpRequest httpRequest, byte[] bArr);
    }

    public HttpRequest() {
    }

    public HttpRequest(String str, String str2, byte[] bArr) {
        setRequest(str, str2, bArr);
    }

    public void setHttpbuf(Object obj) {
        this.httpbuf = obj;
    }

    public Object getHttpbuf() {
        return this.httpbuf;
    }

    public void setConectType(int i) {
        this.mConnectType = i;
    }

    public void setRequest(String str, String str2, byte[] bArr) {
        this.postDatas.clear();
        appendData(bArr);
        try {
            this.mUrl = encodeUrl(str, str2);
        } catch (MalformedURLException e) {
            DebugLog.LogE(e);
        }
    }

    public void setRequest(String str, String str2, byte[] bArr, String str3) {
        this.mUpLoadParam = str3;
        setRequest(str, str2, bArr);
    }

    public void setTimeOut(int i) {
        this.mTimeOut = i;
    }

    public void startRequest(HttpRequestListener httpRequestListener) {
        this.mListener = httpRequestListener;
        start();
    }

    public static URL encodeUrl(String str, String str2) throws MalformedURLException {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            str = str + str2;
        }
        return new URL(str);
    }

    public void cancel() {
        this.mExit = true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.mConnectType == 1) {
            runPost();
        } else {
            runGet();
        }
    }

    private byte[] readStream(InputStream inputStream) throws IOException {
        int read;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (!this.mExit && (read = bufferedInputStream.read(bArr, 0, 1024)) != -1) {
            byteArrayOutputStream.write(bArr, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00af A[Catch: Exception -> 0x00b2, TRY_LEAVE, TryCatch #4 {Exception -> 0x00b2, blocks: (B:33:0x00aa, B:28:0x00af), top: B:32:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void runGet() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        byte b = 0;
        InputStream inputStream2 = null;
        byte b2 = 0;
        try {
            try {
                try {
                    DebugLog.LogD("Start connect server");
                    httpURLConnection = (HttpURLConnection) this.mUrl.openConnection();
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
                    httpURLConnection.setConnectTimeout(this.mTimeOut);
                    httpURLConnection.setReadTimeout(this.mTimeOut);
                    httpURLConnection.setRequestMethod("GET");
                    int responseCode = httpURLConnection.getResponseCode();
                    DebugLog.LogD("responseCode = " + responseCode);
                    if (200 == responseCode) {
                        inputStream2 = httpURLConnection.getInputStream();
                        throwResult(readStream(inputStream2));
                    } else {
                        DebugLog.LogD("MscHttpRequest connect error:" + responseCode);
                        throwError(new SpeechError(Math.abs(responseCode) + 12000));
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    DebugLog.LogE(e);
                    DebugLog.LogD("MscHttpRequest error:" + e.toString());
                    throwError(new SpeechError(20003));
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

    private void throwResult(byte[] bArr) {
        if (this.mListener == null || this.mExit) {
            return;
        }
        this.mListener.onResult(this, bArr);
    }

    public void appendData(byte[] bArr) {
        if (bArr != null) {
            this.postDatas.add(bArr);
        }
    }

    private void throwError(SpeechError speechError) {
        if (this.mListener == null || this.mExit) {
            return;
        }
        this.mListener.onError(speechError);
    }

    private int getPostLength() {
        int i = 0;
        for (int i2 = 0; i2 < this.postDatas.size(); i2++) {
            i += this.postDatas.get(i2).length;
        }
        return i;
    }

    public void runPost() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        byte b = 0;
        InputStream inputStream2 = null;
        byte b2 = 0;
        try {
            try {
                try {
                    DebugLog.LogD("MscHttpRequest start Post");
                    httpURLConnection = (HttpURLConnection) this.mUrl.openConnection();
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
                    httpURLConnection.setConnectTimeout(this.mTimeOut);
                    httpURLConnection.setReadTimeout(this.mTimeOut);
                    httpURLConnection.setRequestProperty("Content-length", "" + getPostLength());
                    httpURLConnection.setRequestProperty("Connection", HTTP.CONN_KEEP_ALIVE);
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.setRequestProperty("Content-Type", Mimetypes.MIMETYPE_GZIP);
                    if (!TextUtils.isEmpty(this.mUpLoadParam)) {
                        httpURLConnection.setRequestProperty("X-Par", android.util.Base64.encodeToString(this.mUpLoadParam.getBytes(), 2));
                    }
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    Iterator<byte[]> it = this.postDatas.iterator();
                    while (it.hasNext()) {
                        outputStream.write(it.next());
                    }
                    outputStream.flush();
                    outputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (200 == responseCode) {
                        inputStream2 = httpURLConnection.getInputStream();
                        throwResult(readStream(inputStream2));
                    } else {
                        DebugLog.LogD("Http Request Failed." + responseCode);
                        throwError(new SpeechError(responseCode + 12000));
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection == null) {
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    throwError(new SpeechError(20003));
                    DebugLog.LogD("MscHttpRequest error:" + e.toString());
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

    public static boolean hasHttpError(String str) {
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
}
