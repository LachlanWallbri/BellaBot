package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromiseNotifier;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Bzip2Encoder extends MessageToByteEncoder<ByteBuf> {
    private Bzip2BlockCompressor blockCompressor;
    private volatile ChannelHandlerContext ctx;
    private State currentState;
    private volatile boolean finished;
    private final int streamBlockSize;
    private int streamCRC;
    private final Bzip2BitWriter writer;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        INIT,
        INIT_BLOCK,
        WRITE_DATA,
        CLOSE_BLOCK
    }

    public Bzip2Encoder() {
        this(9);
    }

    public Bzip2Encoder(int i) {
        this.currentState = State.INIT;
        this.writer = new Bzip2BitWriter();
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("blockSizeMultiplier: " + i + " (expected: 1-9)");
        }
        this.streamBlockSize = i * BZip2Constants.BASEBLOCKSIZE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052 A[SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        if (this.finished) {
            byteBuf2.writeBytes(byteBuf);
            return;
        }
        while (true) {
            int i = C64514.$SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State[this.currentState.ordinal()];
            if (i == 1) {
                byteBuf2.ensureWritable(4);
                byteBuf2.writeMedium(4348520);
                byteBuf2.writeByte((this.streamBlockSize / BZip2Constants.BASEBLOCKSIZE) + 48);
                this.currentState = State.INIT_BLOCK;
            } else if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException();
                    }
                    closeBlock(byteBuf2);
                    this.currentState = State.INIT_BLOCK;
                }
                if (byteBuf.isReadable()) {
                    return;
                }
                Bzip2BlockCompressor bzip2BlockCompressor = this.blockCompressor;
                byteBuf.skipBytes(bzip2BlockCompressor.write(byteBuf, byteBuf.readerIndex(), Math.min(byteBuf.readableBytes(), bzip2BlockCompressor.availableSize())));
                if (!bzip2BlockCompressor.isFull()) {
                    if (!byteBuf.isReadable()) {
                        return;
                    }
                } else {
                    this.currentState = State.CLOSE_BLOCK;
                    closeBlock(byteBuf2);
                    this.currentState = State.INIT_BLOCK;
                }
            }
            this.blockCompressor = new Bzip2BlockCompressor(this.writer, this.streamBlockSize);
            this.currentState = State.WRITE_DATA;
            if (byteBuf.isReadable()) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2Encoder$4 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C64514 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State[State.INIT_BLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State[State.WRITE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Encoder$State[State.CLOSE_BLOCK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void closeBlock(ByteBuf byteBuf) {
        Bzip2BlockCompressor bzip2BlockCompressor = this.blockCompressor;
        if (bzip2BlockCompressor.isEmpty()) {
            return;
        }
        bzip2BlockCompressor.close(byteBuf);
        int crc = bzip2BlockCompressor.crc();
        int i = this.streamCRC;
        this.streamCRC = crc ^ ((i >>> 31) | (i << 1));
    }

    public boolean isClosed() {
        return this.finished;
    }

    public ChannelFuture close() {
        return close(ctx().newPromise());
    }

    public ChannelFuture close(final ChannelPromise channelPromise) {
        ChannelHandlerContext ctx = ctx();
        EventExecutor executor = ctx.executor();
        if (executor.inEventLoop()) {
            return finishEncode(ctx, channelPromise);
        }
        executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2Encoder.1
            @Override // java.lang.Runnable
            public void run() {
                Bzip2Encoder bzip2Encoder = Bzip2Encoder.this;
                bzip2Encoder.finishEncode(bzip2Encoder.ctx(), channelPromise).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(channelPromise));
            }
        });
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(final ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise) throws Exception {
        ChannelFuture finishEncode = finishEncode(channelHandlerContext, channelHandlerContext.newPromise());
        finishEncode.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2Encoder.2
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                channelHandlerContext.close(channelPromise);
            }
        });
        if (finishEncode.isDone()) {
            return;
        }
        channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.compression.Bzip2Encoder.3
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
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        closeBlock(buffer);
        int i = this.streamCRC;
        Bzip2BitWriter bzip2BitWriter = this.writer;
        try {
            bzip2BitWriter.writeBits(buffer, 24, 1536581L);
            bzip2BitWriter.writeBits(buffer, 24, 3690640L);
            bzip2BitWriter.writeInt(buffer, i);
            bzip2BitWriter.flush(buffer);
            this.blockCompressor = null;
            return channelHandlerContext.writeAndFlush(buffer, channelPromise);
        } catch (Throwable th) {
            this.blockCompressor = null;
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ChannelHandlerContext ctx() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return channelHandlerContext;
        }
        throw new IllegalStateException("not added to a pipeline");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }
}
