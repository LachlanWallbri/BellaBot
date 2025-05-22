package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.Signal;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ReplayingDecoder<S> extends ByteToMessageDecoder {
    static final Signal REPLAY = Signal.valueOf(ReplayingDecoder.class, "REPLAY");
    private int checkpoint;
    private final ReplayingDecoderByteBuf replayable;
    private S state;

    /* JADX INFO: Access modifiers changed from: protected */
    public ReplayingDecoder() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ReplayingDecoder(S s) {
        this.replayable = new ReplayingDecoderByteBuf();
        this.checkpoint = -1;
        this.state = s;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkpoint() {
        this.checkpoint = internalBuffer().readerIndex();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkpoint(S s) {
        checkpoint();
        state(s);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public S state() {
        return this.state;
    }

    protected S state(S s) {
        S s2 = this.state;
        this.state = s;
        return s2;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    final void channelInputClosed(ChannelHandlerContext channelHandlerContext, List<Object> list) throws Exception {
        try {
            this.replayable.terminate();
            if (this.cumulation != null) {
                callDecode(channelHandlerContext, internalBuffer(), list);
            } else {
                this.replayable.setCumulation(Unpooled.EMPTY_BUFFER);
            }
            decodeLast(channelHandlerContext, this.replayable, list);
        } catch (Signal e) {
            e.expect(REPLAY);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void callDecode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        int i;
        this.replayable.setCumulation(byteBuf);
        while (byteBuf.isReadable()) {
            try {
                int readerIndex = byteBuf.readerIndex();
                this.checkpoint = readerIndex;
                int size = list.size();
                if (size > 0) {
                    fireChannelRead(channelHandlerContext, list, size);
                    list.clear();
                    if (channelHandlerContext.isRemoved()) {
                        return;
                    } else {
                        size = 0;
                    }
                }
                S s = this.state;
                int readableBytes = byteBuf.readableBytes();
                try {
                    decodeRemovalReentryProtection(channelHandlerContext, this.replayable, list);
                    if (channelHandlerContext.isRemoved()) {
                        return;
                    }
                    if (size == list.size()) {
                        if (readableBytes == byteBuf.readableBytes() && s == this.state) {
                            throw new DecoderException(StringUtil.simpleClassName(getClass()) + ".decode() must consume the inbound data or change its state if it did not decode anything.");
                        }
                    } else {
                        if (readerIndex == byteBuf.readerIndex() && s == this.state) {
                            throw new DecoderException(StringUtil.simpleClassName(getClass()) + ".decode() method must consume the inbound data or change its state if it decoded something.");
                        }
                        if (isSingleDecode()) {
                            return;
                        }
                    }
                } catch (Signal e) {
                    e.expect(REPLAY);
                    if (!channelHandlerContext.isRemoved() && (i = this.checkpoint) >= 0) {
                        byteBuf.readerIndex(i);
                        return;
                    }
                    return;
                }
            } catch (DecoderException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new DecoderException(e3);
            }
        }
    }
}
