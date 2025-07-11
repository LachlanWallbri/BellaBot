package io.netty.handler.codec.compression;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelPromiseNotifier;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.concurrent.TimeUnit;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class JdkZlibEncoder extends ZlibEncoder {
    private static final byte[] gzipHeader = {Ascii.f1926US, -117, 8, 0, 0, 0, 0, 0, 0, 0};
    private final CRC32 crc;
    private volatile ChannelHandlerContext ctx;
    private final Deflater deflater;
    private volatile boolean finished;
    private final ZlibWrapper wrapper;
    private boolean writeHeader;

    public JdkZlibEncoder() {
        this(6);
    }

    public JdkZlibEncoder(int i) {
        this(ZlibWrapper.ZLIB, i);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper) {
        this(zlibWrapper, 6);
    }

    public JdkZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (zlibWrapper == null) {
            throw new NullPointerException("wrapper");
        }
        if (zlibWrapper == ZlibWrapper.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not allowed for compression.");
        }
        this.wrapper = zlibWrapper;
        this.deflater = new Deflater(i, zlibWrapper != ZlibWrapper.ZLIB);
    }

    public JdkZlibEncoder(byte[] bArr) {
        this(6, bArr);
    }

    public JdkZlibEncoder(int i, byte[] bArr) {
        this.crc = new CRC32();
        this.writeHeader = true;
        if (i < 0 || i > 9) {
            throw new IllegalArgumentException("compressionLevel: " + i + " (expected: 0-9)");
        }
        if (bArr == null) {
            throw new NullPointerException("dictionary");
        }
        this.wrapper = ZlibWrapper.ZLIB;
        Deflater deflater = new Deflater(i);
        this.deflater = deflater;
        deflater.setDictionary(bArr);
    }

    @Override // io.netty.handler.codec.compression.ZlibEncoder
    public ChannelFuture close() {
        return close(ctx().newPromise());
    }

    @Override // io.netty.handler.codec.compression.ZlibEncoder
    public ChannelFuture close(final ChannelPromise channelPromise) {
        ChannelHandlerContext ctx = ctx();
        EventExecutor executor = ctx.executor();
        if (executor.inEventLoop()) {
            return finishEncode(ctx, channelPromise);
        }
        final ChannelPromise newPromise = ctx.newPromise();
        executor.execute(new Runnable() { // from class: io.netty.handler.codec.compression.JdkZlibEncoder.1
            @Override // java.lang.Runnable
            public void run() {
                JdkZlibEncoder jdkZlibEncoder = JdkZlibEncoder.this;
                jdkZlibEncoder.finishEncode(jdkZlibEncoder.ctx(), newPromise).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(channelPromise));
            }
        });
        return newPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelHandlerContext ctx() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return channelHandlerContext;
        }
        throw new IllegalStateException("not added to a pipeline");
    }

    @Override // io.netty.handler.codec.compression.ZlibEncoder
    public boolean isClosed() {
        return this.finished;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        byte[] bArr;
        int i;
        if (this.finished) {
            byteBuf2.writeBytes(byteBuf);
            return;
        }
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            return;
        }
        if (byteBuf.hasArray()) {
            bArr = byteBuf.array();
            i = byteBuf.arrayOffset() + byteBuf.readerIndex();
            byteBuf.skipBytes(readableBytes);
        } else {
            bArr = new byte[readableBytes];
            byteBuf.readBytes(bArr);
            i = 0;
        }
        if (this.writeHeader) {
            this.writeHeader = false;
            if (this.wrapper == ZlibWrapper.GZIP) {
                byteBuf2.writeBytes(gzipHeader);
            }
        }
        if (this.wrapper == ZlibWrapper.GZIP) {
            this.crc.update(bArr, i, readableBytes);
        }
        this.deflater.setInput(bArr, i, readableBytes);
        while (!this.deflater.needsInput()) {
            deflate(byteBuf2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToByteEncoder
    public final ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, boolean z) throws Exception {
        int ceil = ((int) Math.ceil(byteBuf.readableBytes() * 1.001d)) + 12;
        if (this.writeHeader) {
            int i = C71224.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[this.wrapper.ordinal()];
            if (i == 1) {
                ceil += gzipHeader.length;
            } else if (i == 2) {
                ceil += 2;
            }
        }
        return channelHandlerContext.alloc().heapBuffer(ceil);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.compression.JdkZlibEncoder$4 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C71224 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper;

        static {
            int[] iArr = new int[ZlibWrapper.values().length];
            $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = iArr;
            try {
                iArr[ZlibWrapper.GZIP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandlerAdapter, io.netty.channel.ChannelOutboundHandler
    public void close(final ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise) throws Exception {
        ChannelFuture finishEncode = finishEncode(channelHandlerContext, channelHandlerContext.newPromise());
        finishEncode.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.compression.JdkZlibEncoder.2
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                channelHandlerContext.close(channelPromise);
            }
        });
        if (finishEncode.isDone()) {
            return;
        }
        channelHandlerContext.executor().schedule(new Runnable() { // from class: io.netty.handler.codec.compression.JdkZlibEncoder.3
            @Override // java.lang.Runnable
            public void run() {
                channelHandlerContext.close(channelPromise);
            }
        }, 10L, TimeUnit.SECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelFuture finishEncode(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        if (this.finished) {
            channelPromise.setSuccess();
            return channelPromise;
        }
        this.finished = true;
        ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer();
        if (this.writeHeader && this.wrapper == ZlibWrapper.GZIP) {
            this.writeHeader = false;
            heapBuffer.writeBytes(gzipHeader);
        }
        this.deflater.finish();
        while (!this.deflater.finished()) {
            deflate(heapBuffer);
            if (!heapBuffer.isWritable()) {
                channelHandlerContext.write(heapBuffer);
                heapBuffer = channelHandlerContext.alloc().heapBuffer();
            }
        }
        if (this.wrapper == ZlibWrapper.GZIP) {
            int value = (int) this.crc.getValue();
            int totalIn = this.deflater.getTotalIn();
            heapBuffer.writeByte(value);
            heapBuffer.writeByte(value >>> 8);
            heapBuffer.writeByte(value >>> 16);
            heapBuffer.writeByte(value >>> 24);
            heapBuffer.writeByte(totalIn);
            heapBuffer.writeByte(totalIn >>> 8);
            heapBuffer.writeByte(totalIn >>> 16);
            heapBuffer.writeByte(totalIn >>> 24);
        }
        this.deflater.end();
        return channelHandlerContext.writeAndFlush(heapBuffer, channelPromise);
    }

    private void deflate(ByteBuf byteBuf) {
        int deflate;
        do {
            int writerIndex = byteBuf.writerIndex();
            deflate = this.deflater.deflate(byteBuf.array(), byteBuf.arrayOffset() + writerIndex, byteBuf.writableBytes(), 2);
            byteBuf.writerIndex(writerIndex + deflate);
        } while (deflate > 0);
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }
}
