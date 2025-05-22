package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface HttpMessage extends HttpObject {
    @Deprecated
    HttpVersion getProtocolVersion();

    HttpHeaders headers();

    HttpVersion protocolVersion();

    HttpMessage setProtocolVersion(HttpVersion httpVersion);
}
