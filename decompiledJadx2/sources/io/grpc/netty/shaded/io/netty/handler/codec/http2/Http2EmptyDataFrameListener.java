package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class Http2EmptyDataFrameListener extends Http2FrameListenerDecorator {
    private int emptyDataFrames;
    private final int maxConsecutiveEmptyFrames;
    private boolean violationDetected;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2EmptyDataFrameListener(Http2FrameListener http2FrameListener, int i) {
        super(http2FrameListener);
        this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositive(i, "maxConsecutiveEmptyFrames");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
        if (z || byteBuf.isReadable()) {
            this.emptyDataFrames = 0;
        } else {
            int i3 = this.emptyDataFrames;
            this.emptyDataFrames = i3 + 1;
            if (i3 == this.maxConsecutiveEmptyFrames && !this.violationDetected) {
                this.violationDetected = true;
                throw Http2Exception.connectionError(Http2Error.ENHANCE_YOUR_CALM, "Maximum number %d of empty data frames without end_of_stream flag received", Integer.valueOf(this.maxConsecutiveEmptyFrames));
            }
        }
        return super.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
        this.emptyDataFrames = 0;
        super.onHeadersRead(channelHandlerContext, i, http2Headers, i2, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListenerDecorator, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
        this.emptyDataFrames = 0;
        super.onHeadersRead(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
    }
}
