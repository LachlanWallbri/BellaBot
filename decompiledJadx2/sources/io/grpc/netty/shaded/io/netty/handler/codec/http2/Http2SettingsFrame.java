package io.grpc.netty.shaded.io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2SettingsFrame extends Http2Frame {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    String name();

    Http2Settings settings();
}
