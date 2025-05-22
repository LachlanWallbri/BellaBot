package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class SslClientHelloHandler<T> extends ByteToMessageDecoder implements ChannelOutboundHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslClientHelloHandler.class);
    private ByteBuf handshakeBuffer;
    private boolean handshakeFailed;
    private boolean readPending;
    private boolean suppressRead;

    protected abstract Future<T> lookup(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception;

    protected abstract void onLookupComplete(ChannelHandlerContext channelHandlerContext, Future<T> future) throws Exception;

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x001b. Please report as an issue. */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.suppressRead || this.handshakeFailed) {
            return;
        }
        try {
            int readerIndex = byteBuf.readerIndex();
            int readableBytes = byteBuf.readableBytes();
            int i = -1;
            while (readableBytes >= 5) {
                switch (byteBuf.getUnsignedByte(readerIndex)) {
                    case 20:
                    case 21:
                        int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBuf, readerIndex);
                        if (encryptedPacketLength != -2) {
                            if (encryptedPacketLength == -1) {
                                return;
                            }
                            select(channelHandlerContext, null);
                            return;
                        }
                        this.handshakeFailed = true;
                        NotSslRecordException notSslRecordException = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(byteBuf));
                        byteBuf.skipBytes(byteBuf.readableBytes());
                        channelHandlerContext.fireUserEventTriggered((Object) new SniCompletionEvent(notSslRecordException));
                        SslUtils.handleHandshakeFailure(channelHandlerContext, notSslRecordException, true);
                        throw notSslRecordException;
                    case 22:
                        if (byteBuf.getUnsignedByte(readerIndex + 1) == 3) {
                            int unsignedShort = byteBuf.getUnsignedShort(readerIndex + 3) + 5;
                            if (readableBytes < unsignedShort) {
                                return;
                            }
                            if (unsignedShort == 5) {
                                select(channelHandlerContext, null);
                                return;
                            }
                            int i2 = readerIndex + unsignedShort;
                            if (i == -1) {
                                int i3 = readerIndex + 4;
                                if (i3 > i2) {
                                    return;
                                }
                                int i4 = readerIndex + 5;
                                if (byteBuf.getUnsignedByte(i4) != 1) {
                                    select(channelHandlerContext, null);
                                    return;
                                }
                                int unsignedMedium = byteBuf.getUnsignedMedium(i4 + 1);
                                unsignedShort -= 4;
                                if (unsignedMedium + 4 + 5 <= unsignedShort) {
                                    select(channelHandlerContext, byteBuf.retainedSlice(i3 + 5, unsignedMedium));
                                    return;
                                }
                                if (this.handshakeBuffer == null) {
                                    this.handshakeBuffer = channelHandlerContext.alloc().buffer(unsignedMedium);
                                } else {
                                    this.handshakeBuffer.clear();
                                }
                                i = unsignedMedium;
                                readerIndex = i3;
                            }
                            this.handshakeBuffer.writeBytes(byteBuf, readerIndex + 5, unsignedShort - 5);
                            readerIndex += unsignedShort;
                            readableBytes -= unsignedShort;
                            if (i <= this.handshakeBuffer.readableBytes()) {
                                ByteBuf index = this.handshakeBuffer.setIndex(0, i);
                                this.handshakeBuffer = null;
                                select(channelHandlerContext, index);
                                return;
                            }
                        } else {
                            select(channelHandlerContext, null);
                            return;
                        }
                    default:
                        select(channelHandlerContext, null);
                        return;
                }
            }
        } catch (NotSslRecordException e) {
            throw e;
        } catch (Exception e2) {
            if (logger.isDebugEnabled()) {
                logger.debug("Unexpected client hello packet: " + ByteBufUtil.hexDump(byteBuf), (Throwable) e2);
            }
            select(channelHandlerContext, null);
        }
    }

    private void releaseHandshakeBuffer() {
        releaseIfNotNull(this.handshakeBuffer);
        this.handshakeBuffer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void releaseIfNotNull(ByteBuf byteBuf) {
        if (byteBuf != null) {
            byteBuf.release();
        }
    }

    private void select(final ChannelHandlerContext channelHandlerContext, final ByteBuf byteBuf) throws Exception {
        try {
            Future<T> lookup = lookup(channelHandlerContext, byteBuf);
            if (lookup.isDone()) {
                onLookupComplete(channelHandlerContext, lookup);
            } else {
                this.suppressRead = true;
                lookup.addListener(new FutureListener<T>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslClientHelloHandler.1
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<T> future) {
                        SslClientHelloHandler.releaseIfNotNull(byteBuf);
                        try {
                            SslClientHelloHandler.this.suppressRead = false;
                            try {
                                SslClientHelloHandler.this.onLookupComplete(channelHandlerContext, future);
                            } catch (DecoderException e) {
                                channelHandlerContext.fireExceptionCaught((Throwable) e);
                            } catch (Exception e2) {
                                channelHandlerContext.fireExceptionCaught((Throwable) new DecoderException(e2));
                            } catch (Throwable th) {
                                channelHandlerContext.fireExceptionCaught(th);
                            }
                        } finally {
                            if (SslClientHelloHandler.this.readPending) {
                                SslClientHelloHandler.this.readPending = false;
                                channelHandlerContext.read();
                            }
                        }
                    }
                });
                byteBuf = null;
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        releaseHandshakeBuffer();
        super.handlerRemoved0(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.suppressRead) {
            this.readPending = true;
        } else {
            channelHandlerContext.read();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.close(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.write(obj, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }
}
