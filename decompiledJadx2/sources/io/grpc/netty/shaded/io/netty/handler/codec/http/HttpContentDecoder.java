package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel;
import io.grpc.netty.shaded.io.netty.handler.codec.CodecException;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class HttpContentDecoder extends MessageToMessageDecoder<HttpObject> {
    static final String IDENTITY = HttpHeaderValues.IDENTITY.toString();
    private boolean continueResponse;
    protected ChannelHandlerContext ctx;
    private EmbeddedChannel decoder;
    private boolean needRead = true;

    protected abstract EmbeddedChannel newContentDecoder(String str) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageDecoder
    public /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List list) throws Exception {
        decode2(channelHandlerContext, httpObject, (List<Object>) list);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        String str;
        HttpMessage defaultHttpResponse;
        try {
            if ((httpObject instanceof HttpResponse) && ((HttpResponse) httpObject).status().code() == 100) {
                if (!(httpObject instanceof LastHttpContent)) {
                    this.continueResponse = true;
                }
                list.add(ReferenceCountUtil.retain(httpObject));
            } else if (this.continueResponse) {
                if (httpObject instanceof LastHttpContent) {
                    this.continueResponse = false;
                }
                list.add(ReferenceCountUtil.retain(httpObject));
            } else {
                if (httpObject instanceof HttpMessage) {
                    cleanup();
                    HttpMessage httpMessage = (HttpMessage) httpObject;
                    HttpHeaders headers = httpMessage.headers();
                    String str2 = headers.get(HttpHeaderNames.CONTENT_ENCODING);
                    if (str2 != null) {
                        str = str2.trim();
                    } else {
                        String str3 = headers.get(HttpHeaderNames.TRANSFER_ENCODING);
                        if (str3 != null) {
                            int indexOf = str3.indexOf(",");
                            if (indexOf != -1) {
                                str = str3.substring(0, indexOf).trim();
                            } else {
                                str = str3.trim();
                            }
                        } else {
                            str = IDENTITY;
                        }
                    }
                    this.decoder = newContentDecoder(str);
                    if (this.decoder == null) {
                        if (httpMessage instanceof HttpContent) {
                            ((HttpContent) httpMessage).retain();
                        }
                        list.add(httpMessage);
                    } else {
                        if (headers.contains(HttpHeaderNames.CONTENT_LENGTH)) {
                            headers.remove(HttpHeaderNames.CONTENT_LENGTH);
                            headers.set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
                        }
                        String targetContentEncoding = getTargetContentEncoding(str);
                        if (HttpHeaderValues.IDENTITY.contentEquals(targetContentEncoding)) {
                            headers.remove(HttpHeaderNames.CONTENT_ENCODING);
                        } else {
                            headers.set(HttpHeaderNames.CONTENT_ENCODING, targetContentEncoding);
                        }
                        if (httpMessage instanceof HttpContent) {
                            if (httpMessage instanceof HttpRequest) {
                                HttpRequest httpRequest = (HttpRequest) httpMessage;
                                defaultHttpResponse = new DefaultHttpRequest(httpRequest.protocolVersion(), httpRequest.method(), httpRequest.uri());
                            } else if (httpMessage instanceof HttpResponse) {
                                HttpResponse httpResponse = (HttpResponse) httpMessage;
                                defaultHttpResponse = new DefaultHttpResponse(httpResponse.protocolVersion(), httpResponse.status());
                            } else {
                                throw new CodecException("Object of class " + httpMessage.getClass().getName() + " is not an HttpRequest or HttpResponse");
                            }
                            defaultHttpResponse.headers().set(httpMessage.headers());
                            defaultHttpResponse.setDecoderResult(httpMessage.decoderResult());
                            list.add(defaultHttpResponse);
                        } else {
                            list.add(httpMessage);
                        }
                    }
                }
                if (httpObject instanceof HttpContent) {
                    HttpContent httpContent = (HttpContent) httpObject;
                    if (this.decoder == null) {
                        list.add(httpContent.retain());
                    } else {
                        decodeContent(httpContent, list);
                    }
                }
            }
        } finally {
            this.needRead = list.isEmpty();
        }
    }

    private void decodeContent(HttpContent httpContent, List<Object> list) {
        decode(httpContent.content(), list);
        if (httpContent instanceof LastHttpContent) {
            finishDecode(list);
            HttpHeaders trailingHeaders = ((LastHttpContent) httpContent).trailingHeaders();
            if (trailingHeaders.isEmpty()) {
                list.add(LastHttpContent.EMPTY_LAST_CONTENT);
            } else {
                list.add(new ComposedLastHttpContent(trailingHeaders, DecoderResult.SUCCESS));
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        boolean z = this.needRead;
        this.needRead = true;
        try {
            channelHandlerContext.fireChannelReadComplete();
        } finally {
            if (z && !channelHandlerContext.channel().config().isAutoRead()) {
                channelHandlerContext.read();
            }
        }
    }

    protected String getTargetContentEncoding(String str) throws Exception {
        return IDENTITY;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanupSafely(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        cleanupSafely(channelHandlerContext);
        super.channelInactive(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
    }

    private void cleanup() {
        EmbeddedChannel embeddedChannel = this.decoder;
        if (embeddedChannel != null) {
            embeddedChannel.finishAndReleaseAll();
            this.decoder = null;
        }
    }

    private void cleanupSafely(ChannelHandlerContext channelHandlerContext) {
        try {
            cleanup();
        } catch (Throwable th) {
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    private void decode(ByteBuf byteBuf, List<Object> list) {
        this.decoder.writeInbound(byteBuf.retain());
        fetchDecoderOutput(list);
    }

    private void finishDecode(List<Object> list) {
        if (this.decoder.finish()) {
            fetchDecoderOutput(list);
        }
        this.decoder = null;
    }

    private void fetchDecoderOutput(List<Object> list) {
        while (true) {
            ByteBuf byteBuf = (ByteBuf) this.decoder.readInbound();
            if (byteBuf == null) {
                return;
            }
            if (!byteBuf.isReadable()) {
                byteBuf.release();
            } else {
                list.add(new DefaultHttpContent(byteBuf));
            }
        }
    }
}
