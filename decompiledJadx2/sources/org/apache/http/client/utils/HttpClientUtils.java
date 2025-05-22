package org.apache.http.client.utils;

import java.io.Closeable;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class HttpClientUtils {
    private HttpClientUtils() {
    }

    public static void closeQuietly(HttpResponse httpResponse) {
        HttpEntity entity;
        if (httpResponse == null || (entity = httpResponse.getEntity()) == null) {
            return;
        }
        try {
            EntityUtils.consume(entity);
        } catch (IOException unused) {
        }
    }

    public static void closeQuietly(CloseableHttpResponse closeableHttpResponse) {
        try {
            if (closeableHttpResponse != null) {
                try {
                    EntityUtils.consume(closeableHttpResponse.getEntity());
                    closeableHttpResponse.close();
                } catch (Throwable th) {
                    closeableHttpResponse.close();
                    throw th;
                }
            }
        } catch (IOException unused) {
        }
    }

    public static void closeQuietly(HttpClient httpClient) {
        if (httpClient == null || !(httpClient instanceof Closeable)) {
            return;
        }
        try {
            ((Closeable) httpClient).close();
        } catch (IOException unused) {
        }
    }
}
