package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.HttpConversionUtil;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class InboundHttp2ToHttpAdapter extends Http2EventAdapter {
    private static final ImmediateSendDetector DEFAULT_SEND_DETECTOR = new ImmediateSendDetector() { // from class: io.netty.handler.codec.http2.InboundHttp2ToHttpAdapter.1
        @Override // io.netty.handler.codec.http2.InboundHttp2ToHttpAdapter.ImmediateSendDetector
        public boolean mustSendImmediately(FullHttpMessage fullHttpMessage) {
            if (fullHttpMessage instanceof FullHttpResponse) {
                return ((FullHttpResponse) fullHttpMessage).status().codeClass() == HttpStatusClass.INFORMATIONAL;
            }
            if (fullHttpMessage instanceof FullHttpRequest) {
                return fullHttpMessage.headers().contains(HttpHeaderNames.EXPECT);
            }
            return false;
        }

        @Override // io.netty.handler.codec.http2.InboundHttp2ToHttpAdapter.ImmediateSendDetector
        public FullHttpMessage copyIfNeeded(FullHttpMessage fullHttpMessage) {
            if (!(fullHttpMessage instanceof FullHttpRequest)) {
                return null;
            }
            FullHttpRequest replace = ((FullHttpRequest) fullHttpMessage).replace(Unpooled.buffer(0));
            replace.headers().remove(HttpHeaderNames.EXPECT);
            return replace;
        }
    };
    protected final Http2Connection connection;
    private final int maxContentLength;
    private final Http2Connection.PropertyKey messageKey;
    private final boolean propagateSettings;
    private final ImmediateSendDetector sendDetector;
    protected final boolean validateHttpHeaders;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface ImmediateSendDetector {
        FullHttpMessage copyIfNeeded(FullHttpMessage fullHttpMessage);

        boolean mustSendImmediately(FullHttpMessage fullHttpMessage);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InboundHttp2ToHttpAdapter(Http2Connection http2Connection, int i, boolean z, boolean z2) {
        ObjectUtil.checkNotNull(http2Connection, "connection");
        if (i <= 0) {
            throw new IllegalArgumentException("maxContentLength: " + i + " (expected: > 0)");
        }
        this.connection = http2Connection;
        this.maxContentLength = i;
        this.validateHttpHeaders = z;
        this.propagateSettings = z2;
        this.sendDetector = DEFAULT_SEND_DETECTOR;
        this.messageKey = http2Connection.newKey();
    }

    protected final void removeMessage(Http2Stream http2Stream, boolean z) {
        FullHttpMessage fullHttpMessage = (FullHttpMessage) http2Stream.removeProperty(this.messageKey);
        if (!z || fullHttpMessage == null) {
            return;
        }
        fullHttpMessage.release();
    }

    protected final FullHttpMessage getMessage(Http2Stream http2Stream) {
        return (FullHttpMessage) http2Stream.getProperty(this.messageKey);
    }

    protected final void putMessage(Http2Stream http2Stream, FullHttpMessage fullHttpMessage) {
        FullHttpMessage fullHttpMessage2 = (FullHttpMessage) http2Stream.setProperty(this.messageKey, fullHttpMessage);
        if (fullHttpMessage2 == fullHttpMessage || fullHttpMessage2 == null) {
            return;
        }
        fullHttpMessage2.release();
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2Connection.Listener
    public void onStreamRemoved(Http2Stream http2Stream) {
        removeMessage(http2Stream, true);
    }

    protected void fireChannelRead(ChannelHandlerContext channelHandlerContext, FullHttpMessage fullHttpMessage, boolean z, Http2Stream http2Stream) {
        removeMessage(http2Stream, z);
        HttpUtil.setContentLength(fullHttpMessage, fullHttpMessage.content().readableBytes());
        channelHandlerContext.fireChannelRead((Object) fullHttpMessage);
    }

    protected FullHttpMessage newMessage(Http2Stream http2Stream, Http2Headers http2Headers, boolean z, ByteBufAllocator byteBufAllocator) throws Http2Exception {
        return this.connection.isServer() ? HttpConversionUtil.toFullHttpRequest(http2Stream.mo3940id(), http2Headers, byteBufAllocator, z) : HttpConversionUtil.toFullHttpResponse(http2Stream.mo3940id(), http2Headers, byteBufAllocator, z);
    }

    protected FullHttpMessage processHeadersBegin(ChannelHandlerContext channelHandlerContext, Http2Stream http2Stream, Http2Headers http2Headers, boolean z, boolean z2, boolean z3) throws Http2Exception {
        FullHttpMessage message = getMessage(http2Stream);
        boolean z4 = false;
        if (message == null) {
            message = newMessage(http2Stream, http2Headers, this.validateHttpHeaders, channelHandlerContext.alloc());
            z4 = true;
        } else if (z2) {
            HttpConversionUtil.addHttp2ToHttpHeaders(http2Stream.mo3940id(), http2Headers, message, z3);
        } else {
            message = null;
        }
        if (!this.sendDetector.mustSendImmediately(message)) {
            return message;
        }
        FullHttpMessage copyIfNeeded = z ? null : this.sendDetector.copyIfNeeded(message);
        fireChannelRead(channelHandlerContext, message, z4, http2Stream);
        return copyIfNeeded;
    }

    private void processHeadersEnd(ChannelHandlerContext channelHandlerContext, Http2Stream http2Stream, FullHttpMessage fullHttpMessage, boolean z) {
        if (z) {
            fireChannelRead(channelHandlerContext, fullHttpMessage, getMessage(http2Stream) != fullHttpMessage, http2Stream);
        } else {
            putMessage(http2Stream, fullHttpMessage);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i);
        FullHttpMessage message = getMessage(stream);
        if (message == null) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Data Frame received for unknown stream id %d", Integer.valueOf(i));
        }
        ByteBuf content = message.content();
        int readableBytes = byteBuf.readableBytes();
        if (content.readableBytes() > this.maxContentLength - readableBytes) {
            throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Content length exceeded max of %d for stream id %d", Integer.valueOf(this.maxContentLength), Integer.valueOf(i));
        }
        content.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
        if (z) {
            fireChannelRead(channelHandlerContext, message, false, stream);
        }
        return readableBytes + i2;
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i);
        FullHttpMessage processHeadersBegin = processHeadersBegin(channelHandlerContext, stream, http2Headers, z, true, true);
        if (processHeadersBegin != null) {
            processHeadersEnd(channelHandlerContext, stream, processHeadersBegin, z);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i);
        FullHttpMessage processHeadersBegin = processHeadersBegin(channelHandlerContext, stream, http2Headers, z2, true, true);
        if (processHeadersBegin != null) {
            if (i2 != 0) {
                processHeadersBegin.headers().setInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_DEPENDENCY_ID.text(), i2);
            }
            processHeadersBegin.headers().setShort(HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT.text(), s);
            processHeadersEnd(channelHandlerContext, stream, processHeadersBegin, z2);
        }
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i);
        FullHttpMessage message = getMessage(stream);
        if (message != null) {
            onRstStreamRead(stream, message);
        }
        channelHandlerContext.fireExceptionCaught((Throwable) Http2Exception.streamError(i, Http2Error.valueOf(j), "HTTP/2 to HTTP layer caught stream reset", new Object[0]));
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) throws Http2Exception {
        Http2Stream stream = this.connection.stream(i2);
        if (http2Headers.status() == null) {
            http2Headers.status(HttpResponseStatus.f8500OK.codeAsText());
        }
        FullHttpMessage processHeadersBegin = processHeadersBegin(channelHandlerContext, stream, http2Headers, false, false, false);
        if (processHeadersBegin == null) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Push Promise Frame received for pre-existing stream id %d", Integer.valueOf(i2));
        }
        processHeadersBegin.headers().setInt(HttpConversionUtil.ExtensionHeaderNames.STREAM_PROMISE_ID.text(), i);
        processHeadersBegin.headers().setShort(HttpConversionUtil.ExtensionHeaderNames.STREAM_WEIGHT.text(), (short) 16);
        processHeadersEnd(channelHandlerContext, stream, processHeadersBegin, false);
    }

    @Override // io.netty.handler.codec.http2.Http2EventAdapter, io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) throws Http2Exception {
        if (this.propagateSettings) {
            channelHandlerContext.fireChannelRead((Object) http2Settings);
        }
    }

    protected void onRstStreamRead(Http2Stream http2Stream, FullHttpMessage fullHttpMessage) {
        removeMessage(http2Stream, true);
    }
}
