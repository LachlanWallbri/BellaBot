package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RequestExpectContinue implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        Args.notNull(httpRequest, "HTTP request");
        if (httpRequest.containsHeader("Expect") || !(httpRequest instanceof HttpEntityEnclosingRequest)) {
            return;
        }
        ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
        HttpEntity entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
        if (entity == null || entity.getContentLength() == 0 || protocolVersion.lessEquals(HttpVersion.HTTP_1_0) || !HttpClientContext.adapt(httpContext).getRequestConfig().isExpectContinueEnabled()) {
            return;
        }
        httpRequest.addHeader("Expect", "100-continue");
    }
}
