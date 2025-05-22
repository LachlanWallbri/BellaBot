package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractSniHandler<T> extends ByteToMessageDecoder implements ChannelOutboundHandler {
    private static final int MAX_SSL_RECORDS = 4;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractSniHandler.class);
    private boolean handshakeFailed;
    private boolean readPending;
    private boolean suppressRead;

    protected abstract Future<T> lookup(ChannelHandlerContext channelHandlerContext, String str) throws Exception;

    protected abstract void onLookupComplete(ChannelHandlerContext channelHandlerContext, String str, Future<T> future) throws Exception;

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x001f. Please report as an issue. */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.suppressRead || this.handshakeFailed) {
            return;
        }
        int writerIndex = byteBuf.writerIndex();
        int i = 0;
        while (true) {
            if (i < 4) {
                try {
                    int readerIndex = byteBuf.readerIndex();
                    int i2 = writerIndex - readerIndex;
                    if (i2 >= 5) {
                        switch (byteBuf.getUnsignedByte(readerIndex)) {
                            case 20:
                            case 21:
                                int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBuf, readerIndex);
                                if (encryptedPacketLength != -2) {
                                    if (encryptedPacketLength != -1 && i2 - 5 >= encryptedPacketLength) {
                                        byteBuf.skipBytes(encryptedPacketLength);
                                        i++;
                                    }
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
                                    if (i2 >= unsignedShort) {
                                        int i3 = unsignedShort + readerIndex;
                                        int i4 = readerIndex + 43;
                                        if (i3 - i4 >= 6) {
                                            int unsignedByte = i4 + byteBuf.getUnsignedByte(i4) + 1;
                                            int unsignedShort2 = unsignedByte + byteBuf.getUnsignedShort(unsignedByte) + 2;
                                            int unsignedByte2 = unsignedShort2 + byteBuf.getUnsignedByte(unsignedShort2) + 1;
                                            int unsignedShort3 = byteBuf.getUnsignedShort(unsignedByte2);
                                            int i5 = unsignedByte2 + 2;
                                            int i6 = unsignedShort3 + i5;
                                            if (i6 <= i3) {
                                                while (true) {
                                                    if (i6 - i5 >= 4) {
                                                        int unsignedShort4 = byteBuf.getUnsignedShort(i5);
                                                        int i7 = i5 + 2;
                                                        int unsignedShort5 = byteBuf.getUnsignedShort(i7);
                                                        int i8 = i7 + 2;
                                                        if (i6 - i8 < unsignedShort5) {
                                                            break;
                                                        } else if (unsignedShort4 == 0) {
                                                            int i9 = i8 + 2;
                                                            if (i6 - i9 < 3) {
                                                                break;
                                                            } else {
                                                                short unsignedByte3 = byteBuf.getUnsignedByte(i9);
                                                                int i10 = i9 + 1;
                                                                if (unsignedByte3 == 0) {
                                                                    int unsignedShort6 = byteBuf.getUnsignedShort(i10);
                                                                    int i11 = i10 + 2;
                                                                    if (i6 - i11 >= unsignedShort6) {
                                                                        try {
                                                                            select(channelHandlerContext, byteBuf.toString(i11, unsignedShort6, CharsetUtil.US_ASCII).toLowerCase(Locale.US));
                                                                            return;
                                                                        } catch (Throwable th) {
                                                                            PlatformDependent.throwException(th);
                                                                            return;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            i5 = i8 + unsignedShort5;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    } else {
                                        return;
                                    }
                                }
                                break;
                        }
                    } else {
                        return;
                    }
                } catch (NotSslRecordException e) {
                    throw e;
                } catch (Exception e2) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Unexpected client hello packet: " + ByteBufUtil.hexDump(byteBuf), (Throwable) e2);
                    }
                }
            }
        }
        select(channelHandlerContext, null);
    }

    private void select(final ChannelHandlerContext channelHandlerContext, final String str) throws Exception {
        Future<T> lookup = lookup(channelHandlerContext, str);
        if (lookup.isDone()) {
            fireSniCompletionEvent(channelHandlerContext, str, lookup);
            onLookupComplete(channelHandlerContext, str, lookup);
        } else {
            this.suppressRead = true;
            lookup.addListener(new FutureListener<T>() { // from class: io.netty.handler.ssl.AbstractSniHandler.1
                @Override // io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(Future<T> future) throws Exception {
                    try {
                        AbstractSniHandler.this.suppressRead = false;
                        try {
                            try {
                                AbstractSniHandler.this.fireSniCompletionEvent(channelHandlerContext, str, future);
                                AbstractSniHandler.this.onLookupComplete(channelHandlerContext, str, future);
                            } catch (DecoderException e) {
                                channelHandlerContext.fireExceptionCaught((Throwable) e);
                            } catch (Exception e2) {
                                channelHandlerContext.fireExceptionCaught((Throwable) new DecoderException(e2));
                            }
                        } catch (Throwable th) {
                            channelHandlerContext.fireExceptionCaught(th);
                        }
                    } finally {
                        if (AbstractSniHandler.this.readPending) {
                            AbstractSniHandler.this.readPending = false;
                            channelHandlerContext.read();
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireSniCompletionEvent(ChannelHandlerContext channelHandlerContext, String str, Future<T> future) {
        Throwable cause = future.cause();
        if (cause == null) {
            channelHandlerContext.fireUserEventTriggered((Object) new SniCompletionEvent(str));
        } else {
            channelHandlerContext.fireUserEventTriggered((Object) new SniCompletionEvent(str, cause));
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.suppressRead) {
            this.readPending = true;
        } else {
            channelHandlerContext.read();
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.close(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.write(obj, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }
}
