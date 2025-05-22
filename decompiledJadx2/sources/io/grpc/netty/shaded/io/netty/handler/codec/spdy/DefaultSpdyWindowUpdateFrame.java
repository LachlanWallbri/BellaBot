package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultSpdyWindowUpdateFrame implements SpdyWindowUpdateFrame {
    private int deltaWindowSize;
    private int streamId;

    public DefaultSpdyWindowUpdateFrame(int i, int i2) {
        setStreamId(i);
        setDeltaWindowSize(i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public int streamId() {
        return this.streamId;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public SpdyWindowUpdateFrame setStreamId(int i) {
        ObjectUtil.checkPositiveOrZero(i, "streamId");
        this.streamId = i;
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public int deltaWindowSize() {
        return this.deltaWindowSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.spdy.SpdyWindowUpdateFrame
    public SpdyWindowUpdateFrame setDeltaWindowSize(int i) {
        ObjectUtil.checkPositive(i, "deltaWindowSize");
        this.deltaWindowSize = i;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName(this) + StringUtil.NEWLINE + "--> Stream-ID = " + streamId() + StringUtil.NEWLINE + "--> Delta-Window-Size = " + deltaWindowSize();
    }
}
