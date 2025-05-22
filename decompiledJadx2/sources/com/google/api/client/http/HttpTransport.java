package com.google.api.client.http;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class HttpTransport {
    static final Logger LOGGER = Logger.getLogger(HttpTransport.class.getName());
    private static final String[] SUPPORTED_METHODS = {"DELETE", "GET", "POST", "PUT"};

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract LowLevelHttpRequest buildRequest(String str, String str2) throws IOException;

    public boolean isMtls() {
        return false;
    }

    public void shutdown() throws IOException {
    }

    static {
        Arrays.sort(SUPPORTED_METHODS);
    }

    public final HttpRequestFactory createRequestFactory() {
        return createRequestFactory(null);
    }

    public final HttpRequestFactory createRequestFactory(HttpRequestInitializer httpRequestInitializer) {
        return new HttpRequestFactory(this, httpRequestInitializer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequest buildRequest() {
        return new HttpRequest(this, null);
    }

    public boolean supportsMethod(String str) throws IOException {
        return Arrays.binarySearch(SUPPORTED_METHODS, str) >= 0;
    }
}
