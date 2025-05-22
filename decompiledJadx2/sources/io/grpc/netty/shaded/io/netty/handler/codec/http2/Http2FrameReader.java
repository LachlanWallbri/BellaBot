package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2HeadersDecoder;
import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Http2FrameReader extends Closeable {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Configuration {
        Http2FrameSizePolicy frameSizePolicy();

        Http2HeadersDecoder.Configuration headersConfiguration();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    Configuration configuration();

    void readFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, Http2FrameListener http2FrameListener) throws Http2Exception;
}
