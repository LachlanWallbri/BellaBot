package com.squareup.okhttp;

import androidx.core.app.NotificationCompat;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.RequestException;
import com.squareup.okhttp.internal.http.RouteException;
import com.squareup.okhttp.internal.http.StreamAllocation;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Call {
    volatile boolean canceled;
    private final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;

    /* JADX INFO: Access modifiers changed from: protected */
    public Call(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient.copyWithDefaults();
        this.originalRequest = request;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        try {
            this.client.getDispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.getDispatcher().finished(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object tag() {
        return this.originalRequest.tag();
    }

    public void enqueue(Callback callback) {
        enqueue(callback, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueue(Callback callback, boolean z) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.getDispatcher().enqueue(new AsyncCall(callback, z));
    }

    public void cancel() {
        this.canceled = true;
        HttpEngine httpEngine = this.engine;
        if (httpEngine != null) {
            httpEngine.cancel();
        }
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class AsyncCall extends NamedRunnable {
        private final boolean forWebSocket;
        private final Callback responseCallback;

        private AsyncCall(Callback callback, boolean z) {
            super("OkHttp %s", Call.this.originalRequest.urlString());
            this.responseCallback = callback;
            this.forWebSocket = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String host() {
            return Call.this.originalRequest.httpUrl().host();
        }

        Request request() {
            return Call.this.originalRequest;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Object tag() {
            return Call.this.originalRequest.tag();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void cancel() {
            Call.this.cancel();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Call get() {
            return Call.this;
        }

        @Override // com.squareup.okhttp.internal.NamedRunnable
        protected void execute() {
            IOException e;
            boolean z = true;
            try {
                try {
                    Response responseWithInterceptorChain = Call.this.getResponseWithInterceptorChain(this.forWebSocket);
                    try {
                        if (Call.this.canceled) {
                            this.responseCallback.onFailure(Call.this.originalRequest, new IOException("Canceled"));
                        } else {
                            this.responseCallback.onResponse(responseWithInterceptorChain);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        if (z) {
                            Internal.logger.log(Level.INFO, "Callback failure for " + Call.this.toLoggableString(), (Throwable) e);
                        } else {
                            this.responseCallback.onFailure(Call.this.engine == null ? Call.this.originalRequest : Call.this.engine.getRequest(), e);
                        }
                    }
                } finally {
                    Call.this.client.getDispatcher().finished(this);
                }
            } catch (IOException e3) {
                e = e3;
                z = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String toLoggableString() {
        return (this.canceled ? "canceled call" : NotificationCompat.CATEGORY_CALL) + " to " + this.originalRequest.httpUrl().resolve("/...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Response getResponseWithInterceptorChain(boolean z) throws IOException {
        return new ApplicationInterceptorChain(0, this.originalRequest, z).proceed(this.originalRequest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class ApplicationInterceptorChain implements Interceptor.Chain {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Connection connection() {
            return null;
        }

        ApplicationInterceptorChain(int i, Request request, boolean z) {
            this.index = i;
            this.request = request;
            this.forWebSocket = z;
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Request request() {
            return this.request;
        }

        @Override // com.squareup.okhttp.Interceptor.Chain
        public Response proceed(Request request) throws IOException {
            if (this.index < Call.this.client.interceptors().size()) {
                ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(this.index + 1, request, this.forWebSocket);
                Interceptor interceptor = Call.this.client.interceptors().get(this.index);
                Response intercept = interceptor.intercept(applicationInterceptorChain);
                if (intercept != null) {
                    return intercept;
                }
                throw new NullPointerException("application interceptor " + interceptor + " returned null");
            }
            return Call.this.getResponse(request, this.forWebSocket);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    Response getResponse(Request request, boolean z) throws IOException {
        Response response;
        Request followUpRequest;
        StreamAllocation streamAllocation;
        RequestBody body = request.body();
        if (body != null) {
            Request.Builder newBuilder = request.newBuilder();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                newBuilder.header("Content-Type", contentType.toString());
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                newBuilder.header("Content-Length", Long.toString(contentLength));
                newBuilder.removeHeader("Transfer-Encoding");
            } else {
                newBuilder.header("Transfer-Encoding", "chunked");
                newBuilder.removeHeader("Content-Length");
            }
            request = newBuilder.build();
        }
        this.engine = new HttpEngine(this.client, request, false, false, z, null, null, null);
        int i = 0;
        while (!this.canceled) {
            boolean z2 = true;
            try {
                try {
                    try {
                        this.engine.sendRequest();
                        this.engine.readResponse();
                        response = this.engine.getResponse();
                        followUpRequest = this.engine.followUpRequest();
                    } catch (RequestException e) {
                        throw e.getCause();
                    } catch (RouteException e2) {
                        HttpEngine recover = this.engine.recover(e2);
                        if (recover != null) {
                            this.engine = recover;
                        } else {
                            throw e2.getLastConnectException();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (z2) {
                    }
                    throw th;
                }
            } catch (IOException e3) {
                HttpEngine recover2 = this.engine.recover(e3, null);
                if (recover2 != null) {
                    try {
                        this.engine = recover2;
                    } catch (Throwable th2) {
                        z2 = false;
                        th = th2;
                        if (z2) {
                            this.engine.close().release();
                        }
                        throw th;
                    }
                } else {
                    throw e3;
                }
            }
            if (followUpRequest == null) {
                if (!z) {
                    this.engine.releaseStreamAllocation();
                }
                return response;
            }
            StreamAllocation close = this.engine.close();
            i++;
            if (i > 20) {
                close.release();
                throw new ProtocolException("Too many follow-up requests: " + i);
            }
            if (this.engine.sameConnection(followUpRequest.httpUrl())) {
                streamAllocation = close;
            } else {
                close.release();
                streamAllocation = null;
            }
            this.engine = new HttpEngine(this.client, followUpRequest, false, false, z, streamAllocation, null, response);
        }
        this.engine.releaseStreamAllocation();
        throw new IOException("Canceled");
    }
}
