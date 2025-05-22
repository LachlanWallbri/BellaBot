package com.amazonaws.http;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* loaded from: classes.dex */
public class UrlHttpClient implements HttpClient {
    private static final int BUFFER_SIZE_MULTIPLIER = 8;
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final String TAG = "amazonaws";
    private static final Log log = LogFactory.getLog((Class<?>) UrlHttpClient.class);
    private final ClientConfiguration config;

    /* renamed from: sc */
    private SSLContext f1139sc = null;

    @Override // com.amazonaws.http.HttpClient
    public void shutdown() {
    }

    public UrlHttpClient(ClientConfiguration clientConfiguration) {
        this.config = clientConfiguration;
    }

    @Override // com.amazonaws.http.HttpClient
    public HttpResponse execute(HttpRequest httpRequest) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpRequest.getUri().toURL().openConnection();
        CurlBuilder curlBuilder = this.config.isCurlLogging() ? new CurlBuilder(httpRequest.getUri().toURL()) : null;
        configureConnection(httpRequest, httpURLConnection);
        applyHeadersAndMethod(httpRequest, httpURLConnection, curlBuilder);
        writeContentToConnection(httpRequest, httpURLConnection, curlBuilder);
        if (curlBuilder != null) {
            if (curlBuilder.isValid()) {
                printToLog(curlBuilder.build());
            } else {
                printToLog("Failed to create curl, content too long");
            }
        }
        return createHttpResponse(httpRequest, httpURLConnection);
    }

    HttpResponse createHttpResponse(HttpRequest httpRequest, HttpURLConnection httpURLConnection) throws IOException {
        String responseMessage = httpURLConnection.getResponseMessage();
        int responseCode = httpURLConnection.getResponseCode();
        InputStream errorStream = httpURLConnection.getErrorStream();
        if (errorStream == null && !"HEAD".equals(httpRequest.getMethod())) {
            try {
                errorStream = httpURLConnection.getInputStream();
            } catch (IOException unused) {
            }
        }
        HttpResponse.Builder content = HttpResponse.builder().statusCode(responseCode).statusText(responseMessage).content(errorStream);
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                content.header(entry.getKey(), entry.getValue().get(0));
            }
        }
        return content.build();
    }

    void writeContentToConnection(HttpRequest httpRequest, HttpURLConnection httpURLConnection) throws IOException {
        writeContentToConnection(httpRequest, httpURLConnection, null);
    }

    void writeContentToConnection(HttpRequest httpRequest, HttpURLConnection httpURLConnection, CurlBuilder curlBuilder) throws IOException {
        if (httpRequest.getContent() == null || httpRequest.getContentLength() < 0) {
            return;
        }
        httpURLConnection.setDoOutput(true);
        if (!httpRequest.isStreaming()) {
            httpURLConnection.setFixedLengthStreamingMode((int) httpRequest.getContentLength());
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        ByteBuffer byteBuffer = null;
        if (curlBuilder != null) {
            if (httpRequest.getContentLength() < 2147483647L) {
                byteBuffer = ByteBuffer.allocate((int) httpRequest.getContentLength());
            } else {
                curlBuilder.setContentOverflow(true);
            }
        }
        write(httpRequest.getContent(), outputStream, curlBuilder, byteBuffer);
        if (curlBuilder != null && byteBuffer != null && byteBuffer.position() != 0) {
            curlBuilder.setContent(new String(byteBuffer.array(), "UTF-8"));
        }
        outputStream.flush();
        outputStream.close();
    }

    HttpURLConnection applyHeadersAndMethod(HttpRequest httpRequest, HttpURLConnection httpURLConnection) throws ProtocolException {
        return applyHeadersAndMethod(httpRequest, httpURLConnection, null);
    }

    HttpURLConnection applyHeadersAndMethod(HttpRequest httpRequest, HttpURLConnection httpURLConnection, CurlBuilder curlBuilder) throws ProtocolException {
        if (httpRequest.getHeaders() != null && !httpRequest.getHeaders().isEmpty()) {
            if (curlBuilder != null) {
                curlBuilder.setHeaders(httpRequest.getHeaders());
            }
            for (Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
                String key = entry.getKey();
                if (!key.equals("Content-Length") && !key.equals("Host")) {
                    key.equals("Expect");
                    httpURLConnection.setRequestProperty(key, entry.getValue());
                }
            }
        }
        String method = httpRequest.getMethod();
        httpURLConnection.setRequestMethod(method);
        if (curlBuilder != null) {
            curlBuilder.setMethod(method);
        }
        return httpURLConnection;
    }

    protected void printToLog(String str) {
        log.debug(str);
    }

    protected HttpURLConnection getUrlConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private void write(InputStream inputStream, OutputStream outputStream, CurlBuilder curlBuilder, ByteBuffer byteBuffer) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            if (byteBuffer != null) {
                try {
                    byteBuffer.put(bArr, 0, read);
                } catch (BufferOverflowException unused) {
                    curlBuilder.setContentOverflow(true);
                }
            }
            outputStream.write(bArr, 0, read);
        }
    }

    void configureConnection(HttpRequest httpRequest, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(this.config.getConnectionTimeout());
        httpURLConnection.setReadTimeout(this.config.getSocketTimeout());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setUseCaches(false);
        if (httpRequest.isStreaming()) {
            httpURLConnection.setChunkedStreamingMode(0);
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (this.config.getTrustManager() != null) {
                enableCustomTrustManager(httpsURLConnection);
            }
        }
    }

    private void enableCustomTrustManager(HttpsURLConnection httpsURLConnection) {
        if (this.f1139sc == null) {
            TrustManager[] trustManagerArr = {this.config.getTrustManager()};
            try {
                this.f1139sc = SSLContext.getInstance("TLS");
                this.f1139sc.init(null, trustManagerArr, null);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }
        httpsURLConnection.setSSLSocketFactory(this.f1139sc.getSocketFactory());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public final class CurlBuilder {
        private final URL url;
        private String method = null;
        private final HashMap<String, String> headers = new HashMap<>();
        private String content = null;
        private boolean contentOverflow = false;

        public CurlBuilder(URL url) {
            if (url == null) {
                throw new IllegalArgumentException("Must have a valid url");
            }
            this.url = url;
        }

        public CurlBuilder setMethod(String str) {
            this.method = str;
            return this;
        }

        public CurlBuilder setHeaders(Map<String, String> map) {
            this.headers.clear();
            this.headers.putAll(map);
            return this;
        }

        public CurlBuilder setContent(String str) {
            this.content = str;
            return this;
        }

        public CurlBuilder setContentOverflow(boolean z) {
            this.contentOverflow = z;
            return this;
        }

        public boolean isValid() {
            return !this.contentOverflow;
        }

        public String build() {
            if (!isValid()) {
                throw new IllegalStateException("Invalid state, cannot create curl command");
            }
            StringBuilder sb = new StringBuilder("curl");
            if (this.method != null) {
                sb.append(" -X ");
                sb.append(this.method);
            }
            for (Map.Entry<String, String> entry : this.headers.entrySet()) {
                sb.append(" -H \"");
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(entry.getValue());
                sb.append("\"");
            }
            if (this.content != null) {
                sb.append(" -d '");
                sb.append(this.content);
                sb.append("'");
            }
            sb.append(" ");
            sb.append(this.url.toString());
            return sb.toString();
        }
    }
}
