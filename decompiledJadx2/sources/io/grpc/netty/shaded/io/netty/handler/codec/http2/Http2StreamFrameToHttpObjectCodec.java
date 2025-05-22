package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.EncoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.DefaultLastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderNames;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaderValues;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObject;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpScheme;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpUtil;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpVersion;
import io.grpc.netty.shaded.io.netty.handler.codec.http.LastHttpContent;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.HttpConversionUtil;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler;
import io.grpc.netty.shaded.io.netty.util.Attribute;
import io.grpc.netty.shaded.io.netty.util.AttributeKey;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public class Http2StreamFrameToHttpObjectCodec extends MessageToMessageCodec<Http2StreamFrame, HttpObject> {
    private static final AttributeKey<HttpScheme> SCHEME_ATTR_KEY = AttributeKey.valueOf(HttpScheme.class, "STREAMFRAMECODEC_SCHEME");
    private final boolean isServer;
    private final boolean validateHeaders;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    protected /* bridge */ /* synthetic */ void decode(ChannelHandlerContext channelHandlerContext, Http2StreamFrame http2StreamFrame, List list) throws Exception {
        decode2(channelHandlerContext, http2StreamFrame, (List<Object>) list);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    protected /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List list) throws Exception {
        encode2(channelHandlerContext, httpObject, (List<Object>) list);
    }

    public Http2StreamFrameToHttpObjectCodec(boolean z, boolean z2) {
        this.isServer = z;
        this.validateHeaders = z2;
    }

    public Http2StreamFrameToHttpObjectCodec(boolean z) {
        this(z, true);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToMessageCodec
    public boolean acceptInboundMessage(Object obj) throws Exception {
        return (obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame);
    }

    /* renamed from: decode, reason: avoid collision after fix types in other method */
    protected void decode2(ChannelHandlerContext channelHandlerContext, Http2StreamFrame http2StreamFrame, List<Object> list) throws Exception {
        if (http2StreamFrame instanceof Http2HeadersFrame) {
            Http2HeadersFrame http2HeadersFrame = (Http2HeadersFrame) http2StreamFrame;
            Http2Headers headers = http2HeadersFrame.headers();
            Http2FrameStream stream = http2HeadersFrame.stream();
            int mo3917id = stream == null ? 0 : stream.mo3917id();
            CharSequence status = headers.status();
            if (status != null && HttpResponseStatus.CONTINUE.codeAsText().contentEquals(status)) {
                list.add(newFullMessage(mo3917id, headers, channelHandlerContext.alloc()));
                return;
            }
            if (http2HeadersFrame.isEndStream()) {
                if (headers.method() == null && status == null) {
                    DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
                    HttpConversionUtil.addHttp2ToHttpHeaders(mo3917id, headers, defaultLastHttpContent.trailingHeaders(), HttpVersion.HTTP_1_1, true, true);
                    list.add(defaultLastHttpContent);
                    return;
                }
                list.add(newFullMessage(mo3917id, headers, channelHandlerContext.alloc()));
                return;
            }
            HttpMessage newMessage = newMessage(mo3917id, headers);
            if (!HttpUtil.isContentLengthSet(newMessage)) {
                newMessage.headers().add(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED);
            }
            list.add(newMessage);
            return;
        }
        if (http2StreamFrame instanceof Http2DataFrame) {
            Http2DataFrame http2DataFrame = (Http2DataFrame) http2StreamFrame;
            if (http2DataFrame.isEndStream()) {
                list.add(new DefaultLastHttpContent(http2DataFrame.content().retain(), this.validateHeaders));
            } else {
                list.add(new DefaultHttpContent(http2DataFrame.content().retain()));
            }
        }
    }

    private void encodeLastContent(LastHttpContent lastHttpContent, List<Object> list) {
        boolean z = !(lastHttpContent instanceof FullHttpMessage) && lastHttpContent.trailingHeaders().isEmpty();
        if (lastHttpContent.content().isReadable() || z) {
            list.add(new DefaultHttp2DataFrame(lastHttpContent.content().retain(), lastHttpContent.trailingHeaders().isEmpty()));
        }
        if (lastHttpContent.trailingHeaders().isEmpty()) {
            return;
        }
        list.add(new DefaultHttp2HeadersFrame(HttpConversionUtil.toHttp2Headers(lastHttpContent.trailingHeaders(), this.validateHeaders), true));
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        boolean z;
        if (httpObject instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) httpObject;
            if (httpResponse.status().equals(HttpResponseStatus.CONTINUE)) {
                if (httpResponse instanceof FullHttpResponse) {
                    list.add(new DefaultHttp2HeadersFrame(toHttp2Headers(channelHandlerContext, httpResponse), false));
                    return;
                }
                throw new EncoderException(HttpResponseStatus.CONTINUE.toString() + " must be a FullHttpResponse");
            }
        }
        if (httpObject instanceof HttpMessage) {
            Http2Headers http2Headers = toHttp2Headers(channelHandlerContext, (HttpMessage) httpObject);
            if (httpObject instanceof FullHttpMessage) {
                FullHttpMessage fullHttpMessage = (FullHttpMessage) httpObject;
                if (!fullHttpMessage.content().isReadable() && fullHttpMessage.trailingHeaders().isEmpty()) {
                    z = true;
                    list.add(new DefaultHttp2HeadersFrame(http2Headers, z));
                }
            }
            z = false;
            list.add(new DefaultHttp2HeadersFrame(http2Headers, z));
        }
        if (httpObject instanceof LastHttpContent) {
            encodeLastContent((LastHttpContent) httpObject, list);
        } else if (httpObject instanceof HttpContent) {
            list.add(new DefaultHttp2DataFrame(((HttpContent) httpObject).content().retain(), false));
        }
    }

    private Http2Headers toHttp2Headers(ChannelHandlerContext channelHandlerContext, HttpMessage httpMessage) {
        if (httpMessage instanceof HttpRequest) {
            httpMessage.headers().set(HttpConversionUtil.ExtensionHeaderNames.SCHEME.text(), connectionScheme(channelHandlerContext));
        }
        return HttpConversionUtil.toHttp2Headers(httpMessage, this.validateHeaders);
    }

    private HttpMessage newMessage(int i, Http2Headers http2Headers) throws Http2Exception {
        if (this.isServer) {
            return HttpConversionUtil.toHttpRequest(i, http2Headers, this.validateHeaders);
        }
        return HttpConversionUtil.toHttpResponse(i, http2Headers, this.validateHeaders);
    }

    private FullHttpMessage newFullMessage(int i, Http2Headers http2Headers, ByteBufAllocator byteBufAllocator) throws Http2Exception {
        if (this.isServer) {
            return HttpConversionUtil.toFullHttpRequest(i, http2Headers, byteBufAllocator, this.validateHeaders);
        }
        return HttpConversionUtil.toFullHttpResponse(i, http2Headers, byteBufAllocator, this.validateHeaders);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerAdded(channelHandlerContext);
        Attribute<HttpScheme> connectionSchemeAttribute = connectionSchemeAttribute(channelHandlerContext);
        if (connectionSchemeAttribute.get() == null) {
            connectionSchemeAttribute.set(isSsl(channelHandlerContext) ? HttpScheme.HTTPS : HttpScheme.HTTP);
        }
    }

    protected boolean isSsl(ChannelHandlerContext channelHandlerContext) {
        return connectionChannel(channelHandlerContext).pipeline().get(SslHandler.class) != null;
    }

    private static HttpScheme connectionScheme(ChannelHandlerContext channelHandlerContext) {
        HttpScheme httpScheme = connectionSchemeAttribute(channelHandlerContext).get();
        return httpScheme == null ? HttpScheme.HTTP : httpScheme;
    }

    private static Attribute<HttpScheme> connectionSchemeAttribute(ChannelHandlerContext channelHandlerContext) {
        return connectionChannel(channelHandlerContext).attr(SCHEME_ATTR_KEY);
    }

    private static Channel connectionChannel(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        return channel instanceof Http2StreamChannel ? channel.parent() : channel;
    }
}
