package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderResult;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator;
import io.grpc.netty.shaded.io.netty.handler.codec.TooLongFrameException;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpObjectAggregator extends MessageAggregator<HttpObject, HttpMessage, HttpContent, FullHttpMessage> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean closeOnExpectationFailed;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) HttpObjectAggregator.class);
    private static final FullHttpResponse CONTINUE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);
    private static final FullHttpResponse EXPECTATION_FAILED = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EXPECTATION_FAILED, Unpooled.EMPTY_BUFFER);
    private static final FullHttpResponse TOO_LARGE_CLOSE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE, Unpooled.EMPTY_BUFFER);
    private static final FullHttpResponse TOO_LARGE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE, Unpooled.EMPTY_BUFFER);

    static {
        EXPECTATION_FAILED.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        TOO_LARGE.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        TOO_LARGE_CLOSE.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        TOO_LARGE_CLOSE.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
    }

    public HttpObjectAggregator(int i) {
        this(i, false);
    }

    public HttpObjectAggregator(int i, boolean z) {
        super(i);
        this.closeOnExpectationFailed = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public boolean isStartMessage(HttpObject httpObject) throws Exception {
        return httpObject instanceof HttpMessage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public boolean isContentMessage(HttpObject httpObject) throws Exception {
        return httpObject instanceof HttpContent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public boolean isLastContentMessage(HttpContent httpContent) throws Exception {
        return httpContent instanceof LastHttpContent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public boolean isAggregated(HttpObject httpObject) throws Exception {
        return httpObject instanceof FullHttpMessage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public boolean isContentLengthInvalid(HttpMessage httpMessage, int i) {
        try {
            return HttpUtil.getContentLength(httpMessage, -1L) > ((long) i);
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static Object continueResponse(HttpMessage httpMessage, int i, ChannelPipeline channelPipeline) {
        if (HttpUtil.isUnsupportedExpectation(httpMessage)) {
            channelPipeline.fireUserEventTriggered((Object) HttpExpectationFailedEvent.INSTANCE);
            return EXPECTATION_FAILED.retainedDuplicate();
        }
        if (!HttpUtil.is100ContinueExpected(httpMessage)) {
            return null;
        }
        if (HttpUtil.getContentLength(httpMessage, -1L) <= i) {
            return CONTINUE.retainedDuplicate();
        }
        channelPipeline.fireUserEventTriggered((Object) HttpExpectationFailedEvent.INSTANCE);
        return TOO_LARGE.retainedDuplicate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public Object newContinueResponse(HttpMessage httpMessage, int i, ChannelPipeline channelPipeline) {
        Object continueResponse = continueResponse(httpMessage, i, channelPipeline);
        if (continueResponse != null) {
            httpMessage.headers().remove(HttpHeaderNames.EXPECT);
        }
        return continueResponse;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    protected boolean closeAfterContinueResponse(Object obj) {
        return this.closeOnExpectationFailed && ignoreContentAfterContinueResponse(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    protected boolean ignoreContentAfterContinueResponse(Object obj) {
        if (obj instanceof HttpResponse) {
            return ((HttpResponse) obj).status().codeClass().equals(HttpStatusClass.CLIENT_ERROR);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public FullHttpMessage beginAggregation(HttpMessage httpMessage, ByteBuf byteBuf) throws Exception {
        HttpUtil.setTransferEncodingChunked(httpMessage, false);
        if (httpMessage instanceof HttpRequest) {
            return new AggregatedFullHttpRequest((HttpRequest) httpMessage, byteBuf, null);
        }
        if (httpMessage instanceof HttpResponse) {
            return new AggregatedFullHttpResponse((HttpResponse) httpMessage, byteBuf, null);
        }
        throw new Error();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public void aggregate(FullHttpMessage fullHttpMessage, HttpContent httpContent) throws Exception {
        if (httpContent instanceof LastHttpContent) {
            ((AggregatedFullHttpMessage) fullHttpMessage).setTrailingHeaders(((LastHttpContent) httpContent).trailingHeaders());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public void finishAggregation(FullHttpMessage fullHttpMessage) throws Exception {
        if (HttpUtil.isContentLengthSet(fullHttpMessage)) {
            return;
        }
        fullHttpMessage.headers().set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(fullHttpMessage.content().readableBytes()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageAggregator
    public void handleOversizedMessage(final ChannelHandlerContext channelHandlerContext, HttpMessage httpMessage) throws Exception {
        if (httpMessage instanceof HttpRequest) {
            if ((httpMessage instanceof FullHttpMessage) || (!HttpUtil.is100ContinueExpected(httpMessage) && !HttpUtil.isKeepAlive(httpMessage))) {
                channelHandlerContext.writeAndFlush(TOO_LARGE_CLOSE.retainedDuplicate()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.1
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            HttpObjectAggregator.logger.debug("Failed to send a 413 Request Entity Too Large.", channelFuture.cause());
                        }
                        channelHandlerContext.close();
                    }
                });
                return;
            } else {
                channelHandlerContext.writeAndFlush(TOO_LARGE.retainedDuplicate()).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.2
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            return;
                        }
                        HttpObjectAggregator.logger.debug("Failed to send a 413 Request Entity Too Large.", channelFuture.cause());
                        channelHandlerContext.close();
                    }
                });
                return;
            }
        }
        if (httpMessage instanceof HttpResponse) {
            channelHandlerContext.close();
            throw new TooLongFrameException("Response entity too large: " + httpMessage);
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class AggregatedFullHttpMessage implements FullHttpMessage {
        private final ByteBuf content;
        protected final HttpMessage message;
        private HttpHeaders trailingHeaders;

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public abstract FullHttpMessage copy();

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public abstract FullHttpMessage duplicate();

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public abstract FullHttpMessage retainedDuplicate();

        AggregatedFullHttpMessage(HttpMessage httpMessage, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            this.message = httpMessage;
            this.content = byteBuf;
            this.trailingHeaders = httpHeaders;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent
        public HttpHeaders trailingHeaders() {
            HttpHeaders httpHeaders = this.trailingHeaders;
            return httpHeaders == null ? EmptyHttpHeaders.INSTANCE : httpHeaders;
        }

        void setTrailingHeaders(HttpHeaders httpHeaders) {
            this.trailingHeaders = httpHeaders;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpVersion getProtocolVersion() {
            return this.message.protocolVersion();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpVersion protocolVersion() {
            return this.message.protocolVersion();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpMessage setProtocolVersion(HttpVersion httpVersion) {
            this.message.setProtocolVersion(httpVersion);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage
        public HttpHeaders headers() {
            return this.message.headers();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
        public DecoderResult decoderResult() {
            return this.message.decoderResult();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject
        public DecoderResult getDecoderResult() {
            return this.message.decoderResult();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.DecoderResultProvider
        public void setDecoderResult(DecoderResult decoderResult) {
            this.message.setDecoderResult(decoderResult);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public ByteBuf content() {
            return this.content;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public int refCnt() {
            return this.content.refCnt();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpMessage retain() {
            this.content.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpMessage retain(int i) {
            this.content.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpMessage touch(Object obj) {
            this.content.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpMessage touch() {
            this.content.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release() {
            return this.content.release();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public boolean release(int i) {
            return this.content.release(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class AggregatedFullHttpRequest extends AggregatedFullHttpMessage implements FullHttpRequest {
        AggregatedFullHttpRequest(HttpRequest httpRequest, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            super(httpRequest, byteBuf, httpHeaders);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest copy() {
            return replace(content().copy());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest duplicate() {
            return replace(content().duplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpRequest replace(ByteBuf byteBuf) {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(protocolVersion(), method(), uri(), byteBuf, headers().copy(), trailingHeaders().copy());
            defaultFullHttpRequest.setDecoderResult(decoderResult());
            return defaultFullHttpRequest;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest retain(int i) {
            super.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest retain() {
            super.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest touch() {
            super.touch();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpRequest touch(Object obj) {
            super.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setMethod(HttpMethod httpMethod) {
            ((HttpRequest) this.message).setMethod(httpMethod);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setUri(String str) {
            ((HttpRequest) this.message).setUri(str);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public HttpMethod getMethod() {
            return ((HttpRequest) this.message).method();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public String getUri() {
            return ((HttpRequest) this.message).uri();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public HttpMethod method() {
            return getMethod();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest
        public String uri() {
            return getUri();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpRequest setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        public String toString() {
            return HttpMessageUtil.appendFullRequest(new StringBuilder(256), this).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class AggregatedFullHttpResponse extends AggregatedFullHttpMessage implements FullHttpResponse {
        AggregatedFullHttpResponse(HttpResponse httpResponse, ByteBuf byteBuf, HttpHeaders httpHeaders) {
            super(httpResponse, byteBuf, httpHeaders);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpResponse copy() {
            return replace(content().copy());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpResponse duplicate() {
            return replace(content().duplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpResponse retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent, io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
        public FullHttpResponse replace(ByteBuf byteBuf) {
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(getProtocolVersion(), getStatus(), byteBuf, headers().copy(), trailingHeaders().copy());
            defaultFullHttpResponse.setDecoderResult(decoderResult());
            return defaultFullHttpResponse;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse
        public FullHttpResponse setStatus(HttpResponseStatus httpResponseStatus) {
            ((HttpResponse) this.message).setStatus(httpResponseStatus);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse
        public HttpResponseStatus getStatus() {
            return ((HttpResponse) this.message).status();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse
        public HttpResponseStatus status() {
            return getStatus();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest, io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest
        public FullHttpResponse setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpResponse retain(int i) {
            super.retain(i);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpResponse retain() {
            super.retain();
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpResponse touch(Object obj) {
            super.touch(obj);
            return this;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectAggregator.AggregatedFullHttpMessage, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
        public FullHttpResponse touch() {
            super.touch();
            return this;
        }

        public String toString() {
            return HttpMessageUtil.appendFullResponse(new StringBuilder(256), this).toString();
        }
    }
}
