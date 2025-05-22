package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RequestAcceptEncoding implements HttpRequestInterceptor {
    private final String acceptEncoding;

    public RequestAcceptEncoding(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(list.get(i));
            }
            this.acceptEncoding = sb.toString();
            return;
        }
        this.acceptEncoding = "gzip,deflate";
    }

    public RequestAcceptEncoding() {
        this(null);
    }

    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        RequestConfig requestConfig = HttpClientContext.adapt(httpContext).getRequestConfig();
        if (httpRequest.containsHeader("Accept-Encoding") || !requestConfig.isContentCompressionEnabled()) {
            return;
        }
        httpRequest.addHeader("Accept-Encoding", this.acceptEncoding);
    }
}
