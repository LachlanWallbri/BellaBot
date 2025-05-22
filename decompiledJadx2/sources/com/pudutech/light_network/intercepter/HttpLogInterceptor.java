package com.pudutech.light_network.intercepter;

import com.pudutech.base.Pdlog;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class HttpLogInterceptor implements Interceptor {
    private static final String TAG = HttpLogInterceptor.class.getSimpleName();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private boolean mDebug;

    public HttpLogInterceptor(boolean z) {
        this.mDebug = z;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Charset charset = UTF8;
        if (this.mDebug) {
            RequestBody body = request.body();
            if (body != null) {
                MediaType mediaType = body.get$contentType();
                String str = "";
                if (mediaType != null && !mediaType.toString().contains("multipart/form-data")) {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    Charset charset2 = mediaType.charset(UTF8);
                    if (isPlaintext(buffer) && charset2 != null) {
                        str = buffer.readString(charset2);
                    }
                    charset = charset2;
                }
                Pdlog.m3275i(TAG, "request url:" + request.url());
                Pdlog.m3275i(TAG, "request header:" + request.headers());
                Pdlog.m3275i(TAG, "request body:" + str);
            }
            try {
                Response proceed = chain.proceed(request);
                ResponseBody body2 = proceed.body();
                if (body2 != null) {
                    long j = body2.get$contentLength();
                    BufferedSource bufferedSource = body2.get$this_asResponseBody();
                    bufferedSource.request(Long.MAX_VALUE);
                    Buffer buffer2 = bufferedSource.buffer();
                    MediaType mediaType2 = body2.get$contentType();
                    if (mediaType2 != null) {
                        charset = mediaType2.charset(UTF8);
                    }
                    if (isPlaintext(buffer2) && j != 0 && charset != null) {
                        Pdlog.m3275i(TAG, "response header:" + proceed.headers());
                        Pdlog.m3275i(TAG, "response body:" + buffer2.clone().readString(charset));
                    }
                }
                return proceed;
            } catch (Exception e) {
                Pdlog.m3275i(TAG, "<-- HTTP FAILED: " + e);
                throw e;
            }
        }
        return chain.proceed(request);
    }

    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0L, buffer.size() < 64 ? buffer.size() : 64L);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
