package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyPingFrame implements SpdyPingFrame {

    /* renamed from: id */
    private int f8407id;

    public DefaultSpdyPingFrame(int i) {
        setId(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyPingFrame
    /* renamed from: id */
    public int mo3918id() {
        return this.f8407id;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyPingFrame
    public SpdyPingFrame setId(int i) {
        this.f8407id = i;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + StringUtil.NEWLINE + "--> ID = " + mo3918id();
    }
}
