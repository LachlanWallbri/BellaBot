package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.headers.EmptyHeadersInjector;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public class HttpUrlSource implements Source {
    private static final Logger LOG = LoggerFactory.getLogger("HttpUrlSource");
    private static final int MAX_REDIRECTS = 5;
    private HttpURLConnection connection;
    private final HeaderInjector headerInjector;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    public HttpUrlSource(String str) {
        this(str, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage) {
        this(str, sourceInfoStorage, new EmptyHeadersInjector());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage, HeaderInjector headerInjector) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage);
        this.headerInjector = (HeaderInjector) Preconditions.checkNotNull(headerInjector);
        SourceInfo sourceInfo = sourceInfoStorage.get(str);
        this.sourceInfo = sourceInfo == null ? new SourceInfo(str, -2147483648L, ProxyCacheUtils.getSupposablyMime(str)) : sourceInfo;
    }

    public HttpUrlSource(HttpUrlSource httpUrlSource) {
        this.sourceInfo = httpUrlSource.sourceInfo;
        this.sourceInfoStorage = httpUrlSource.sourceInfoStorage;
        this.headerInjector = httpUrlSource.headerInjector;
    }

    @Override // com.danikula.videocache.Source
    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    @Override // com.danikula.videocache.Source
    public void open(long j) throws ProxyCacheException {
        try {
            this.connection = openConnection(j, -1);
            String contentType = this.connection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            this.sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(this.connection, j, this.connection.getResponseCode()), contentType);
            this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + j, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long contentLength = getContentLength(httpURLConnection);
        return i == 200 ? contentLength : i == 206 ? contentLength + j : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1L;
        }
        return Long.parseLong(headerField);
    }

    @Override // com.danikula.videocache.Source
    public void close() throws ProxyCacheException {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (ArrayIndexOutOfBoundsException e) {
                LOG.error("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", (Throwable) e);
            } catch (IllegalArgumentException e2) {
                e = e2;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (NullPointerException e3) {
                e = e3;
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            }
        }
    }

    @Override // com.danikula.videocache.Source
    public int read(byte[] bArr) throws ProxyCacheException {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return inputStream.read(bArr, 0, bArr.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x008e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0089, code lost:
    
        if (r0 == null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0060, code lost:
    
        if (r0 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x008b, code lost:
    
        r0.disconnect();
        r0 = r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094  */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void fetchContentInfo() throws ProxyCacheException {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        LOG.debug("Read content info from " + this.sourceInfo.url);
        ?? r0 = 0;
        InputStream inputStream = null;
        try {
            try {
                httpURLConnection = openConnection(0L, 10000);
            } catch (IOException e) {
                e = e;
                httpURLConnection = null;
            } catch (Throwable th) {
                th = th;
                r0 = 0;
                ProxyCacheUtils.close(inputStream);
                if (r0 != 0) {
                }
                throw th;
            }
            try {
                long contentLength = getContentLength(httpURLConnection);
                String contentType = httpURLConnection.getContentType();
                inputStream = httpURLConnection.getInputStream();
                this.sourceInfo = new SourceInfo(this.sourceInfo.url, contentLength, contentType);
                this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
                LOG.debug("Source info fetched: " + this.sourceInfo);
                ProxyCacheUtils.close(inputStream);
                httpURLConnection2 = httpURLConnection;
                r0 = httpURLConnection;
            } catch (IOException e2) {
                e = e2;
                LOG.error("Error fetching info from " + this.sourceInfo.url, (Throwable) e);
                ProxyCacheUtils.close(inputStream);
                httpURLConnection2 = httpURLConnection;
                r0 = httpURLConnection;
            }
        } catch (Throwable th2) {
            th = th2;
            ProxyCacheUtils.close(inputStream);
            if (r0 != 0) {
                r0.disconnect();
            }
            throw th;
        }
    }

    private HttpURLConnection openConnection(long j, int i) throws IOException, ProxyCacheException {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.sourceInfo.url;
        int i2 = 0;
        do {
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            if (j > 0) {
                str = " with offset " + j;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(str2);
            logger.debug(sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            injectCustomHeaders(httpURLConnection, str2);
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField("Location");
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    private void injectCustomHeaders(HttpURLConnection httpURLConnection, String str) {
        for (Map.Entry<String, String> entry : this.headerInjector.addHeaders(str).entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
