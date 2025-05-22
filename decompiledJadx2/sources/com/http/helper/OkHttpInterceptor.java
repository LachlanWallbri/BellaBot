package com.http.helper;

import com.http.utils.LogUtils;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class OkHttpInterceptor {

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    public static class RetryInterceptor implements Interceptor {
        private static final String TAG = "RetryInterceptor";
        private int maxCount;
        private int retryCount = 0;

        public RetryInterceptor(int i) {
            this.maxCount = 1;
            this.maxCount = i;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            int i;
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            while (!proceed.isSuccessful() && (i = this.retryCount) < this.maxCount) {
                this.retryCount = i + 1;
                LogUtils.print(TAG, "retry request: " + request.url().toString() + " count: " + this.retryCount);
                proceed = chain.proceed(request);
            }
            return proceed;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    public static class ExceptionInterceptor implements Interceptor {
        private static final String TAG = "ExceptionInterceptor";

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            try {
                return chain.proceed(chain.request());
            } catch (SecurityException e) {
                LogUtils.print(TAG, "OkHttpException " + e);
                throw new IOException(e);
            } catch (Exception e2) {
                LogUtils.print(TAG, "OkHttpException " + e2);
                throw new IOException(e2);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    public static class HttpHeaderInterceptor implements Interceptor {
        private static final String TAG = "ExceptionInterceptor";

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            try {
                chain.request().newBuilder().headers(Headers.m3987of(HttpUtils.getDefaultRequestHeader())).build();
                return chain.proceed(chain.request());
            } catch (SecurityException e) {
                LogUtils.print(TAG, "OkHttpException " + e);
                throw new IOException(e);
            } catch (Exception e2) {
                LogUtils.print(TAG, "OkHttpException " + e2);
                throw new IOException(e2);
            }
        }
    }
}
