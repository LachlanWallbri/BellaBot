package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.spdy.SpdyHeaders;
import io.netty.handler.codec.spdy.SpdyHttpHeaders;
import io.netty.util.AsciiString;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SpdyHttpEncoder extends MessageToMessageEncoder<HttpObject> {
    private int currentStreamId;
    private final boolean headersToLowerCase;
    private final boolean validateHeaders;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List list) throws Exception {
        encode2(channelHandlerContext, httpObject, (List<Object>) list);
    }

    public SpdyHttpEncoder(SpdyVersion spdyVersion) {
        this(spdyVersion, true, true);
    }

    public SpdyHttpEncoder(SpdyVersion spdyVersion, boolean z, boolean z2) {
        if (spdyVersion == null) {
            throw new NullPointerException("version");
        }
        this.headersToLowerCase = z;
        this.validateHeaders = z2;
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, HttpObject httpObject, List<Object> list) throws Exception {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (httpObject instanceof HttpRequest) {
            SpdySynStreamFrame createSynStreamFrame = createSynStreamFrame((HttpRequest) httpObject);
            list.add(createSynStreamFrame);
            z = createSynStreamFrame.isLast() || createSynStreamFrame.isUnidirectional();
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        if (httpObject instanceof HttpResponse) {
            SpdyHeadersFrame createHeadersFrame = createHeadersFrame((HttpResponse) httpObject);
            list.add(createHeadersFrame);
            z = createHeadersFrame.isLast();
            z2 = true;
        }
        if (!(httpObject instanceof HttpContent) || z) {
            z3 = z2;
        } else {
            HttpContent httpContent = (HttpContent) httpObject;
            httpContent.content().retain();
            DefaultSpdyDataFrame defaultSpdyDataFrame = new DefaultSpdyDataFrame(this.currentStreamId, httpContent.content());
            if (httpContent instanceof LastHttpContent) {
                HttpHeaders trailingHeaders = ((LastHttpContent) httpContent).trailingHeaders();
                if (trailingHeaders.isEmpty()) {
                    defaultSpdyDataFrame.setLast(true);
                    list.add(defaultSpdyDataFrame);
                } else {
                    DefaultSpdyHeadersFrame defaultSpdyHeadersFrame = new DefaultSpdyHeadersFrame(this.currentStreamId, this.validateHeaders);
                    defaultSpdyHeadersFrame.setLast(true);
                    Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = trailingHeaders.iteratorCharSequence();
                    while (iteratorCharSequence.hasNext()) {
                        Map.Entry<CharSequence, CharSequence> next = iteratorCharSequence.next();
                        defaultSpdyHeadersFrame.headers().add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.m3944of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
                    }
                    list.add(defaultSpdyDataFrame);
                    list.add(defaultSpdyHeadersFrame);
                }
            } else {
                list.add(defaultSpdyDataFrame);
            }
        }
        if (!z3) {
            throw new UnsupportedMessageTypeException(httpObject, (Class<?>[]) new Class[0]);
        }
    }

    private SpdySynStreamFrame createSynStreamFrame(HttpRequest httpRequest) throws Exception {
        HttpHeaders headers = httpRequest.headers();
        int intValue = headers.getInt(SpdyHttpHeaders.Names.STREAM_ID).intValue();
        int i = headers.getInt(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID, 0);
        byte b = (byte) headers.getInt(SpdyHttpHeaders.Names.PRIORITY, 0);
        String str = headers.get(SpdyHttpHeaders.Names.SCHEME);
        headers.remove(SpdyHttpHeaders.Names.STREAM_ID);
        headers.remove(SpdyHttpHeaders.Names.ASSOCIATED_TO_STREAM_ID);
        headers.remove(SpdyHttpHeaders.Names.PRIORITY);
        headers.remove(SpdyHttpHeaders.Names.SCHEME);
        headers.remove(HttpHeaderNames.CONNECTION);
        headers.remove(HTTP.CONN_KEEP_ALIVE);
        headers.remove("Proxy-Connection");
        headers.remove(HttpHeaderNames.TRANSFER_ENCODING);
        DefaultSpdySynStreamFrame defaultSpdySynStreamFrame = new DefaultSpdySynStreamFrame(intValue, i, b, this.validateHeaders);
        SpdyHeaders headers2 = defaultSpdySynStreamFrame.headers();
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.METHOD, (AsciiString) httpRequest.method().name());
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.PATH, (AsciiString) httpRequest.uri());
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (AsciiString) httpRequest.protocolVersion().text());
        String str2 = headers.get(HttpHeaderNames.HOST);
        headers.remove(HttpHeaderNames.HOST);
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.HOST, (AsciiString) str2);
        if (str == null) {
            str = "https";
        }
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.SCHEME, (AsciiString) str);
        Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = headers.iteratorCharSequence();
        while (iteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = iteratorCharSequence.next();
            headers2.add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.m3944of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
        }
        this.currentStreamId = defaultSpdySynStreamFrame.streamId();
        if (i == 0) {
            defaultSpdySynStreamFrame.setLast(isLast(httpRequest));
        } else {
            defaultSpdySynStreamFrame.setUnidirectional(true);
        }
        return defaultSpdySynStreamFrame;
    }

    private SpdyHeadersFrame createHeadersFrame(HttpResponse httpResponse) throws Exception {
        SpdyHeadersFrame defaultSpdySynReplyFrame;
        HttpHeaders headers = httpResponse.headers();
        int intValue = headers.getInt(SpdyHttpHeaders.Names.STREAM_ID).intValue();
        headers.remove(SpdyHttpHeaders.Names.STREAM_ID);
        headers.remove(HttpHeaderNames.CONNECTION);
        headers.remove(HTTP.CONN_KEEP_ALIVE);
        headers.remove("Proxy-Connection");
        headers.remove(HttpHeaderNames.TRANSFER_ENCODING);
        if (SpdyCodecUtil.isServerId(intValue)) {
            defaultSpdySynReplyFrame = new DefaultSpdyHeadersFrame(intValue, this.validateHeaders);
        } else {
            defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(intValue, this.validateHeaders);
        }
        SpdyHeaders headers2 = defaultSpdySynReplyFrame.headers();
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.STATUS, httpResponse.status().codeAsText());
        headers2.set((SpdyHeaders) SpdyHeaders.HttpNames.VERSION, (AsciiString) httpResponse.protocolVersion().text());
        Iterator<Map.Entry<CharSequence, CharSequence>> iteratorCharSequence = headers.iteratorCharSequence();
        while (iteratorCharSequence.hasNext()) {
            Map.Entry<CharSequence, CharSequence> next = iteratorCharSequence.next();
            defaultSpdySynReplyFrame.headers().add((SpdyHeaders) (this.headersToLowerCase ? AsciiString.m3944of(next.getKey()).toLowerCase() : (CharSequence) next.getKey()), (Object) next.getValue());
        }
        this.currentStreamId = intValue;
        defaultSpdySynReplyFrame.setLast(isLast(httpResponse));
        return defaultSpdySynReplyFrame;
    }

    private static boolean isLast(HttpMessage httpMessage) {
        if (!(httpMessage instanceof FullHttpMessage)) {
            return false;
        }
        FullHttpMessage fullHttpMessage = (FullHttpMessage) httpMessage;
        return fullHttpMessage.trailingHeaders().isEmpty() && !fullHttpMessage.content().isReadable();
    }
}
