package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class MessageAggregator<I, S, C extends ByteBufHolder, O extends ByteBufHolder> extends MessageToMessageDecoder<I> {
    private static final int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS = 1024;
    private ChannelFutureListener continueResponseWriteListener;
    private ChannelHandlerContext ctx;
    private O currentMessage;
    private boolean handlingOversizedMessage;
    private final int maxContentLength;
    private int maxCumulationBufferComponents;

    protected void aggregate(O o, C c) throws Exception {
    }

    protected abstract O beginAggregation(S s, ByteBuf byteBuf) throws Exception;

    protected abstract boolean closeAfterContinueResponse(Object obj) throws Exception;

    protected void finishAggregation(O o) throws Exception {
    }

    protected abstract boolean ignoreContentAfterContinueResponse(Object obj) throws Exception;

    protected abstract boolean isAggregated(I i) throws Exception;

    protected abstract boolean isContentLengthInvalid(S s, int i) throws Exception;

    protected abstract boolean isContentMessage(I i) throws Exception;

    protected abstract boolean isLastContentMessage(C c) throws Exception;

    protected abstract boolean isStartMessage(I i) throws Exception;

    protected abstract Object newContinueResponse(S s, int i, ChannelPipeline channelPipeline) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageAggregator(int i) {
        this.maxCumulationBufferComponents = 1024;
        validateMaxContentLength(i);
        this.maxContentLength = i;
    }

    protected MessageAggregator(int i, Class<? extends I> cls) {
        super(cls);
        this.maxCumulationBufferComponents = 1024;
        validateMaxContentLength(i);
        this.maxContentLength = i;
    }

    private static void validateMaxContentLength(int i) {
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException("maxContentLength: " + i + " (expected: >= 0)");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.handler.codec.MessageToMessageDecoder
    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (super.acceptInboundMessage(obj)) {
            return (isContentMessage(obj) || isStartMessage(obj)) && !isAggregated(obj);
        }
        return false;
    }

    public final int maxContentLength() {
        return this.maxContentLength;
    }

    public final int maxCumulationBufferComponents() {
        return this.maxCumulationBufferComponents;
    }

    public final void setMaxCumulationBufferComponents(int i) {
        if (i < 2) {
            throw new IllegalArgumentException("maxCumulationBufferComponents: " + i + " (expected: >= 2)");
        }
        if (this.ctx == null) {
            this.maxCumulationBufferComponents = i;
            return;
        }
        throw new IllegalStateException("decoder properties cannot be changed once the decoder is added to a pipeline.");
    }

    @Deprecated
    public final boolean isHandlingOversizedMessage() {
        return this.handlingOversizedMessage;
    }

    protected final ChannelHandlerContext ctx() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return channelHandlerContext;
        }
        throw new IllegalStateException("not added to a pipeline yet");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.handler.codec.MessageToMessageDecoder
    public void decode(final ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception {
        boolean isLastContentMessage;
        ByteBufHolder beginAggregation;
        if (isStartMessage(i)) {
            this.handlingOversizedMessage = false;
            O o = this.currentMessage;
            if (o != null) {
                o.release();
                this.currentMessage = null;
                throw new MessageAggregationException();
            }
            Object newContinueResponse = newContinueResponse(i, this.maxContentLength, channelHandlerContext.pipeline());
            if (newContinueResponse != null) {
                ChannelFutureListener channelFutureListener = this.continueResponseWriteListener;
                if (channelFutureListener == null) {
                    channelFutureListener = new ChannelFutureListener() { // from class: io.netty.handler.codec.MessageAggregator.1
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                return;
                            }
                            channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                        }
                    };
                    this.continueResponseWriteListener = channelFutureListener;
                }
                boolean closeAfterContinueResponse = closeAfterContinueResponse(newContinueResponse);
                this.handlingOversizedMessage = ignoreContentAfterContinueResponse(newContinueResponse);
                Future<Void> addListener = channelHandlerContext.writeAndFlush(newContinueResponse).addListener((GenericFutureListener<? extends Future<? super Void>>) channelFutureListener);
                if (closeAfterContinueResponse) {
                    addListener.addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
                    return;
                } else if (this.handlingOversizedMessage) {
                    return;
                }
            } else if (isContentLengthInvalid(i, this.maxContentLength)) {
                invokeHandleOversizedMessage(channelHandlerContext, i);
                return;
            }
            if ((i instanceof DecoderResultProvider) && !((DecoderResultProvider) i).decoderResult().isSuccess()) {
                if (i instanceof ByteBufHolder) {
                    beginAggregation = beginAggregation(i, ((ByteBufHolder) i).content().retain());
                } else {
                    beginAggregation = beginAggregation(i, Unpooled.EMPTY_BUFFER);
                }
                finishAggregation(beginAggregation);
                list.add(beginAggregation);
                return;
            }
            CompositeByteBuf compositeBuffer = channelHandlerContext.alloc().compositeBuffer(this.maxCumulationBufferComponents);
            if (i instanceof ByteBufHolder) {
                appendPartialContent(compositeBuffer, ((ByteBufHolder) i).content());
            }
            this.currentMessage = (O) beginAggregation(i, compositeBuffer);
            return;
        }
        if (isContentMessage(i)) {
            O o2 = this.currentMessage;
            if (o2 == null) {
                return;
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) o2.content();
            ByteBufHolder byteBufHolder = (ByteBufHolder) i;
            if (compositeByteBuf.readableBytes() > this.maxContentLength - byteBufHolder.content().readableBytes()) {
                invokeHandleOversizedMessage(channelHandlerContext, this.currentMessage);
                return;
            }
            appendPartialContent(compositeByteBuf, byteBufHolder.content());
            aggregate(this.currentMessage, byteBufHolder);
            if (byteBufHolder instanceof DecoderResultProvider) {
                DecoderResult decoderResult = ((DecoderResultProvider) byteBufHolder).decoderResult();
                if (!decoderResult.isSuccess()) {
                    O o3 = this.currentMessage;
                    if (o3 instanceof DecoderResultProvider) {
                        ((DecoderResultProvider) o3).setDecoderResult(DecoderResult.failure(decoderResult.cause()));
                    }
                    isLastContentMessage = true;
                } else {
                    isLastContentMessage = isLastContentMessage(byteBufHolder);
                }
            } else {
                isLastContentMessage = isLastContentMessage(byteBufHolder);
            }
            if (isLastContentMessage) {
                finishAggregation(this.currentMessage);
                list.add(this.currentMessage);
                this.currentMessage = null;
                return;
            }
            return;
        }
        throw new MessageAggregationException();
    }

    private static void appendPartialContent(CompositeByteBuf compositeByteBuf, ByteBuf byteBuf) {
        if (byteBuf.isReadable()) {
            compositeByteBuf.addComponent(true, byteBuf.retain());
        }
    }

    private void invokeHandleOversizedMessage(ChannelHandlerContext channelHandlerContext, S s) throws Exception {
        this.handlingOversizedMessage = true;
        this.currentMessage = null;
        try {
            handleOversizedMessage(channelHandlerContext, s);
        } finally {
            ReferenceCountUtil.release(s);
        }
    }

    protected void handleOversizedMessage(ChannelHandlerContext channelHandlerContext, S s) throws Exception {
        channelHandlerContext.fireExceptionCaught((Throwable) new TooLongFrameException("content length exceeded " + maxContentLength() + " bytes."));
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.currentMessage != null && !channelHandlerContext.channel().config().isAutoRead()) {
            channelHandlerContext.read();
        }
        channelHandlerContext.fireChannelReadComplete();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            super.channelInactive(channelHandlerContext);
        } finally {
            releaseCurrentMessage();
        }
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            super.handlerRemoved(channelHandlerContext);
        } finally {
            releaseCurrentMessage();
        }
    }

    private void releaseCurrentMessage() {
        O o = this.currentMessage;
        if (o != null) {
            o.release();
            this.currentMessage = null;
            this.handlingOversizedMessage = false;
        }
    }
}
