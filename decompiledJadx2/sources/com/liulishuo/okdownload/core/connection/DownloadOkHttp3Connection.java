package com.liulishuo.okdownload.core.connection;

import com.liulishuo.okdownload.RedirectUtil;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes4.dex */
public class DownloadOkHttp3Connection implements DownloadConnection, DownloadConnection.Connected {
    final OkHttpClient client;
    private Request request;
    private final Request.Builder requestBuilder;
    Response response;

    DownloadOkHttp3Connection(OkHttpClient okHttpClient, Request.Builder builder) {
        this.client = okHttpClient;
        this.requestBuilder = builder;
    }

    DownloadOkHttp3Connection(OkHttpClient okHttpClient, String str) {
        this(okHttpClient, new Request.Builder().url(str));
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public void addHeader(String str, String str2) {
        this.requestBuilder.addHeader(str, str2);
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public DownloadConnection.Connected execute() throws IOException {
        this.request = this.requestBuilder.build();
        this.response = this.client.newCall(this.request).execute();
        return this;
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public void release() {
        this.request = null;
        Response response = this.response;
        if (response != null) {
            response.close();
        }
        this.response = null;
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public Map<String, List<String>> getRequestProperties() {
        Request request = this.request;
        if (request != null) {
            return request.headers().toMultimap();
        }
        return this.requestBuilder.build().headers().toMultimap();
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public String getRequestProperty(String str) {
        Request request = this.request;
        if (request != null) {
            return request.header(str);
        }
        return this.requestBuilder.build().header(str);
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Connected
    public int getResponseCode() throws IOException {
        Response response = this.response;
        if (response == null) {
            throw new IOException("Please invoke execute first!");
        }
        return response.code();
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Connected
    public InputStream getInputStream() throws IOException {
        Response response = this.response;
        if (response == null) {
            throw new IOException("Please invoke execute first!");
        }
        ResponseBody body = response.body();
        if (body == null) {
            throw new IOException("no body found on response!");
        }
        return body.byteStream();
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection
    public boolean setRequestMethod(String str) throws ProtocolException {
        this.requestBuilder.method(str, null);
        return true;
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Connected
    public Map<String, List<String>> getResponseHeaderFields() {
        Response response = this.response;
        if (response == null) {
            return null;
        }
        return response.headers().toMultimap();
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Connected
    public String getResponseHeaderField(String str) {
        Response response = this.response;
        if (response == null) {
            return null;
        }
        return response.header(str);
    }

    @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Connected
    public String getRedirectLocation() {
        Response priorResponse = this.response.priorResponse();
        if (priorResponse != null && this.response.isSuccessful() && RedirectUtil.isRedirect(priorResponse.code())) {
            return this.response.request().url().toString();
        }
        return null;
    }

    /* loaded from: classes4.dex */
    public static class Factory implements DownloadConnection.Factory {
        private volatile OkHttpClient client;
        private OkHttpClient.Builder clientBuilder;

        public Factory setBuilder(OkHttpClient.Builder builder) {
            this.clientBuilder = builder;
            return this;
        }

        public OkHttpClient.Builder builder() {
            if (this.clientBuilder == null) {
                this.clientBuilder = new OkHttpClient.Builder();
            }
            return this.clientBuilder;
        }

        @Override // com.liulishuo.okdownload.core.connection.DownloadConnection.Factory
        public DownloadConnection create(String str) throws IOException {
            if (this.client == null) {
                synchronized (Factory.class) {
                    if (this.client == null) {
                        this.client = this.clientBuilder != null ? this.clientBuilder.build() : new OkHttpClient();
                        this.clientBuilder = null;
                    }
                }
            }
            return new DownloadOkHttp3Connection(this.client, str);
        }
    }
}
