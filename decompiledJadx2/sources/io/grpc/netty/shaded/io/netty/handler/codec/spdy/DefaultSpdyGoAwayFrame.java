package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyGoAwayFrame implements SpdyGoAwayFrame {
    private int lastGoodStreamId;
    private SpdySessionStatus status;

    public DefaultSpdyGoAwayFrame(int i) {
        this(i, 0);
    }

    public DefaultSpdyGoAwayFrame(int i, int i2) {
        this(i, SpdySessionStatus.valueOf(i2));
    }

    public DefaultSpdyGoAwayFrame(int i, SpdySessionStatus spdySessionStatus) {
        setLastGoodStreamId(i);
        setStatus(spdySessionStatus);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyGoAwayFrame
    public int lastGoodStreamId() {
        return this.lastGoodStreamId;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyGoAwayFrame
    public SpdyGoAwayFrame setLastGoodStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "lastGoodStreamId");
        this.lastGoodStreamId = i;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyGoAwayFrame
    public SpdySessionStatus status() {
        return this.status;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyGoAwayFrame
    public SpdyGoAwayFrame setStatus(SpdySessionStatus spdySessionStatus) {
        this.status = spdySessionStatus;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + StringUtil.NEWLINE + "--> Last-good-stream-ID = " + lastGoodStreamId() + StringUtil.NEWLINE + "--> Status: " + status();
    }
}
