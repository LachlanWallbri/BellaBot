package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class DefaultHttp2SettingsAckFrame implements Http2SettingsAckFrame {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2SettingsAckFrame, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Frame
    public String name() {
        return "SETTINGS(ACK)";
    }

    public String toString() {
        return StringUtil.simpleClassName(this);
    }
}
