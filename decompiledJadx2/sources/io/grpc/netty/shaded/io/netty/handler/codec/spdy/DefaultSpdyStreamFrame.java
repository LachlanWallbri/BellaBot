package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class DefaultSpdyStreamFrame implements SpdyStreamFrame {
    private boolean last;
    private int streamId;

    /* JADX INFO: Access modifiers changed from: protected */
    public DefaultSpdyStreamFrame(int i) {
        setStreamId(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame
    public int streamId() {
        return this.streamId;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyStreamFrame setStreamId(int i) {
        ObjectUtil.checkPositive(i, "streamId");
        this.streamId = i;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame
    public boolean isLast() {
        return this.last;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyStreamFrame, io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyDataFrame
    public SpdyStreamFrame setLast(boolean z) {
        this.last = z;
        return this;
    }
}
