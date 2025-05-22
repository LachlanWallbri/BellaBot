package org.jboss.netty.handler.codec.http;

/* loaded from: classes7.dex */
public interface HttpResponse extends HttpMessage {
    HttpResponseStatus getStatus();

    void setStatus(HttpResponseStatus httpResponseStatus);
}
