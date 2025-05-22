package io.netty.handler.codec.http2;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2SettingsFrame extends Http2Frame {
    @Override // io.netty.handler.codec.http2.Http2Frame
    String name();

    Http2Settings settings();
}
