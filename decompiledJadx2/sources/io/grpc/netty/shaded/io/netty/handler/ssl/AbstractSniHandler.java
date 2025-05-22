package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractSniHandler<T> extends SslClientHelloHandler<T> {
    private String hostname;

    protected abstract Future<T> lookup(ChannelHandlerContext channelHandlerContext, String str) throws Exception;

    protected abstract void onLookupComplete(ChannelHandlerContext channelHandlerContext, String str, Future<T> future) throws Exception;

    private static String extractSniHostname(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        int i = readerIndex + 34;
        if (writerIndex - i < 6) {
            return null;
        }
        int unsignedByte = i + byteBuf.getUnsignedByte(i) + 1;
        int unsignedShort = unsignedByte + byteBuf.getUnsignedShort(unsignedByte) + 2;
        int unsignedByte2 = unsignedShort + byteBuf.getUnsignedByte(unsignedShort) + 1;
        int unsignedShort2 = byteBuf.getUnsignedShort(unsignedByte2);
        int i2 = unsignedByte2 + 2;
        int i3 = unsignedShort2 + i2;
        if (i3 > writerIndex) {
            return null;
        }
        while (i3 - i2 >= 4) {
            int unsignedShort3 = byteBuf.getUnsignedShort(i2);
            int i4 = i2 + 2;
            int unsignedShort4 = byteBuf.getUnsignedShort(i4);
            int i5 = i4 + 2;
            if (i3 - i5 < unsignedShort4) {
                return null;
            }
            if (unsignedShort3 == 0) {
                int i6 = i5 + 2;
                if (i3 - i6 < 3) {
                    return null;
                }
                short unsignedByte3 = byteBuf.getUnsignedByte(i6);
                int i7 = i6 + 1;
                if (unsignedByte3 != 0) {
                    return null;
                }
                int unsignedShort5 = byteBuf.getUnsignedShort(i7);
                int i8 = i7 + 2;
                if (i3 - i8 < unsignedShort5) {
                    return null;
                }
                return byteBuf.toString(i8, unsignedShort5, CharsetUtil.US_ASCII).toLowerCase(Locale.US);
            }
            i2 = i5 + unsignedShort4;
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslClientHelloHandler
    protected Future<T> lookup(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        this.hostname = byteBuf == null ? null : extractSniHostname(byteBuf);
        return lookup(channelHandlerContext, this.hostname);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslClientHelloHandler
    protected void onLookupComplete(ChannelHandlerContext channelHandlerContext, Future<T> future) throws Exception {
        fireSniCompletionEvent(channelHandlerContext, this.hostname, future);
        onLookupComplete(channelHandlerContext, this.hostname, future);
    }

    private void fireSniCompletionEvent(ChannelHandlerContext channelHandlerContext, String str, Future<T> future) {
        Throwable cause = future.cause();
        if (cause == null) {
            channelHandlerContext.fireUserEventTriggered((Object) new SniCompletionEvent(str));
        } else {
            channelHandlerContext.fireUserEventTriggered((Object) new SniCompletionEvent(str, cause));
        }
    }
}
