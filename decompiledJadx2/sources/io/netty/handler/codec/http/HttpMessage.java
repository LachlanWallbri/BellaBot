package io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface HttpMessage extends HttpObject {
    @Deprecated
    HttpVersion getProtocolVersion();

    HttpHeaders headers();

    HttpVersion protocolVersion();

    HttpMessage setProtocolVersion(HttpVersion httpVersion);
}
