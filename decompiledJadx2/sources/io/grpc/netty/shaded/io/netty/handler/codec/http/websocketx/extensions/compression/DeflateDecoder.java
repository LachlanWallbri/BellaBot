package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.CodecException;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibCodecFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.compression.ZlibWrapper;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilter;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class DeflateDecoder extends WebSocketExtensionDecoder {
    private EmbeddedChannel decoder;
    private final WebSocketExtensionFilter extensionDecoderFilter;
    private final boolean noContext;
    static final ByteBuf FRAME_TAIL = Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(new byte[]{0, 0, -1, -1})).asReadOnly();
    static final ByteBuf EMPTY_DEFLATE_BLOCK = Unpooled.unreleasableBuffer(Unpooled.wrappedBuffer(new byte[]{0})).asReadOnly();

    protected abstract boolean appendFrameTail(WebSocketFrame webSocketFrame);

    protected abstract int newRsv(WebSocketFrame webSocketFrame);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List list) throws Exception {
        decode2(channelHandlerContext, webSocketFrame, (List<Object>) list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeflateDecoder(boolean z, WebSocketExtensionFilter webSocketExtensionFilter) {
        this.noContext = z;
        this.extensionDecoderFilter = (WebSocketExtensionFilter) ObjectUtil.checkNotNull(webSocketExtensionFilter, "extensionDecoderFilter");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WebSocketExtensionFilter extensionDecoderFilter() {
        return this.extensionDecoderFilter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: decode, reason: avoid collision after fix types in other method */
    public void decode2(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        Object continuationWebSocketFrame;
        ByteBuf decompressContent = decompressContent(channelHandlerContext, webSocketFrame);
        if (webSocketFrame instanceof TextWebSocketFrame) {
            continuationWebSocketFrame = new TextWebSocketFrame(webSocketFrame.isFinalFragment(), newRsv(webSocketFrame), decompressContent);
        } else if (webSocketFrame instanceof BinaryWebSocketFrame) {
            continuationWebSocketFrame = new BinaryWebSocketFrame(webSocketFrame.isFinalFragment(), newRsv(webSocketFrame), decompressContent);
        } else if (webSocketFrame instanceof ContinuationWebSocketFrame) {
            continuationWebSocketFrame = new ContinuationWebSocketFrame(webSocketFrame.isFinalFragment(), newRsv(webSocketFrame), decompressContent);
        } else {
            throw new CodecException("unexpected frame type: " + webSocketFrame.getClass().getName());
        }
        list.add(continuationWebSocketFrame);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanup();
        super.handlerRemoved(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanup();
        super.channelInactive(channelHandlerContext);
    }

    private ByteBuf decompressContent(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) {
        if (this.decoder == null) {
            if (!(webSocketFrame instanceof TextWebSocketFrame) && !(webSocketFrame instanceof BinaryWebSocketFrame)) {
                throw new CodecException("unexpected initial frame type: " + webSocketFrame.getClass().getName());
            }
            this.decoder = new EmbeddedChannel(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.NONE));
        }
        boolean isReadable = webSocketFrame.content().isReadable();
        boolean equals = EMPTY_DEFLATE_BLOCK.equals(webSocketFrame.content());
        this.decoder.writeInbound(webSocketFrame.content().retain());
        if (appendFrameTail(webSocketFrame)) {
            this.decoder.writeInbound(FRAME_TAIL.duplicate());
        }
        CompositeByteBuf compositeBuffer = channelHandlerContext.alloc().compositeBuffer();
        while (true) {
            ByteBuf byteBuf = (ByteBuf) this.decoder.readInbound();
            if (byteBuf == null) {
                break;
            }
            if (!byteBuf.isReadable()) {
                byteBuf.release();
            } else {
                compositeBuffer.addComponent(true, byteBuf);
            }
        }
        if (!equals && isReadable && compositeBuffer.numComponents() <= 0 && !(webSocketFrame instanceof ContinuationWebSocketFrame)) {
            compositeBuffer.release();
            throw new CodecException("cannot read uncompressed buffer");
        }
        if (webSocketFrame.isFinalFragment() && this.noContext) {
            cleanup();
        }
        return compositeBuffer;
    }

    private void cleanup() {
        EmbeddedChannel embeddedChannel = this.decoder;
        if (embeddedChannel != null) {
            embeddedChannel.finishAndReleaseAll();
            this.decoder = null;
        }
    }
}
