package org.jboss.netty.handler.codec.spdy;

import java.util.HashMap;
import java.util.Map;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpMessage;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/* loaded from: classes7.dex */
public class SpdyHttpDecoder extends OneToOneDecoder {
    private final int maxContentLength;
    private final Map<Integer, HttpMessage> messageMap;
    private final int spdyVersion;

    @Deprecated
    public SpdyHttpDecoder(int i) {
        this(2, i);
    }

    public SpdyHttpDecoder(int i, int i2) {
        this.messageMap = new HashMap();
        if (i < 2 || i > 3) {
            throw new IllegalArgumentException("unsupported version: " + i);
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxContentLength must be a positive integer: " + i2);
        }
        this.spdyVersion = i;
        this.maxContentLength = i2;
    }

    @Override // org.jboss.netty.handler.codec.oneone.OneToOneDecoder
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        if (obj instanceof SpdySynStreamFrame) {
            SpdySynStreamFrame spdySynStreamFrame = (SpdySynStreamFrame) obj;
            int streamId = spdySynStreamFrame.getStreamId();
            if (SpdyCodecUtil.isServerId(streamId)) {
                int associatedToStreamId = spdySynStreamFrame.getAssociatedToStreamId();
                if (associatedToStreamId == 0) {
                    Channels.write(channelHandlerContext, Channels.future(channel), new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.INVALID_STREAM));
                }
                String url = SpdyHeaders.getUrl(this.spdyVersion, spdySynStreamFrame);
                if (url == null) {
                    Channels.write(channelHandlerContext, Channels.future(channel), new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.PROTOCOL_ERROR));
                }
                try {
                    HttpResponse createHttpResponse = createHttpResponse(this.spdyVersion, spdySynStreamFrame);
                    SpdyHttpHeaders.setStreamId(createHttpResponse, streamId);
                    SpdyHttpHeaders.setAssociatedToStreamId(createHttpResponse, associatedToStreamId);
                    SpdyHttpHeaders.setPriority(createHttpResponse, spdySynStreamFrame.getPriority());
                    SpdyHttpHeaders.setUrl(createHttpResponse, url);
                    if (spdySynStreamFrame.isLast()) {
                        HttpHeaders.setContentLength(createHttpResponse, 0L);
                        return createHttpResponse;
                    }
                    this.messageMap.put(new Integer(streamId), createHttpResponse);
                } catch (Exception unused) {
                    Channels.write(channelHandlerContext, Channels.future(channel), new DefaultSpdyRstStreamFrame(streamId, SpdyStreamStatus.PROTOCOL_ERROR));
                }
            } else {
                try {
                    HttpRequest createHttpRequest = createHttpRequest(this.spdyVersion, spdySynStreamFrame);
                    SpdyHttpHeaders.setStreamId(createHttpRequest, streamId);
                    if (spdySynStreamFrame.isLast()) {
                        return createHttpRequest;
                    }
                    this.messageMap.put(new Integer(streamId), createHttpRequest);
                } catch (Exception unused2) {
                    DefaultSpdySynReplyFrame defaultSpdySynReplyFrame = new DefaultSpdySynReplyFrame(streamId);
                    defaultSpdySynReplyFrame.setLast(true);
                    SpdyHeaders.setStatus(this.spdyVersion, defaultSpdySynReplyFrame, HttpResponseStatus.BAD_REQUEST);
                    SpdyHeaders.setVersion(this.spdyVersion, defaultSpdySynReplyFrame, HttpVersion.HTTP_1_0);
                    Channels.write(channelHandlerContext, Channels.future(channel), defaultSpdySynReplyFrame);
                }
            }
        } else if (obj instanceof SpdySynReplyFrame) {
            SpdySynReplyFrame spdySynReplyFrame = (SpdySynReplyFrame) obj;
            int streamId2 = spdySynReplyFrame.getStreamId();
            try {
                HttpResponse createHttpResponse2 = createHttpResponse(this.spdyVersion, spdySynReplyFrame);
                SpdyHttpHeaders.setStreamId(createHttpResponse2, streamId2);
                if (spdySynReplyFrame.isLast()) {
                    HttpHeaders.setContentLength(createHttpResponse2, 0L);
                    return createHttpResponse2;
                }
                this.messageMap.put(new Integer(streamId2), createHttpResponse2);
            } catch (Exception unused3) {
                Channels.write(channelHandlerContext, Channels.future(channel), new DefaultSpdyRstStreamFrame(streamId2, SpdyStreamStatus.PROTOCOL_ERROR));
            }
        } else if (obj instanceof SpdyHeadersFrame) {
            SpdyHeadersFrame spdyHeadersFrame = (SpdyHeadersFrame) obj;
            HttpMessage httpMessage = this.messageMap.get(new Integer(spdyHeadersFrame.getStreamId()));
            if (httpMessage == null) {
                return null;
            }
            for (Map.Entry<String, String> entry : spdyHeadersFrame.getHeaders()) {
                httpMessage.addHeader(entry.getKey(), entry.getValue());
            }
        } else if (obj instanceof SpdyDataFrame) {
            SpdyDataFrame spdyDataFrame = (SpdyDataFrame) obj;
            Integer num = new Integer(spdyDataFrame.getStreamId());
            HttpMessage httpMessage2 = this.messageMap.get(num);
            if (httpMessage2 == null) {
                return null;
            }
            ChannelBuffer content = httpMessage2.getContent();
            if (content.readableBytes() > this.maxContentLength - spdyDataFrame.getData().readableBytes()) {
                this.messageMap.remove(num);
                throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
            }
            if (content == ChannelBuffers.EMPTY_BUFFER) {
                content = ChannelBuffers.dynamicBuffer(channel.getConfig().getBufferFactory());
                content.writeBytes(spdyDataFrame.getData());
                httpMessage2.setContent(content);
            } else {
                content.writeBytes(spdyDataFrame.getData());
            }
            if (spdyDataFrame.isLast()) {
                HttpHeaders.setContentLength(httpMessage2, content.readableBytes());
                this.messageMap.remove(num);
                return httpMessage2;
            }
        } else if (obj instanceof SpdyRstStreamFrame) {
            this.messageMap.remove(new Integer(((SpdyRstStreamFrame) obj).getStreamId()));
        }
        return null;
    }

    private static HttpRequest createHttpRequest(int i, SpdyHeaderBlock spdyHeaderBlock) throws Exception {
        HttpMethod method = SpdyHeaders.getMethod(i, spdyHeaderBlock);
        String url = SpdyHeaders.getUrl(i, spdyHeaderBlock);
        HttpVersion version = SpdyHeaders.getVersion(i, spdyHeaderBlock);
        SpdyHeaders.removeMethod(i, spdyHeaderBlock);
        SpdyHeaders.removeUrl(i, spdyHeaderBlock);
        SpdyHeaders.removeVersion(i, spdyHeaderBlock);
        DefaultHttpRequest defaultHttpRequest = new DefaultHttpRequest(version, method, url);
        SpdyHeaders.removeScheme(i, spdyHeaderBlock);
        if (i >= 3) {
            String host = SpdyHeaders.getHost(spdyHeaderBlock);
            SpdyHeaders.removeHost(spdyHeaderBlock);
            HttpHeaders.setHost(defaultHttpRequest, host);
        }
        for (Map.Entry<String, String> entry : spdyHeaderBlock.getHeaders()) {
            defaultHttpRequest.addHeader(entry.getKey(), entry.getValue());
        }
        HttpHeaders.setKeepAlive(defaultHttpRequest, true);
        defaultHttpRequest.removeHeader("Transfer-Encoding");
        return defaultHttpRequest;
    }

    private static HttpResponse createHttpResponse(int i, SpdyHeaderBlock spdyHeaderBlock) throws Exception {
        HttpResponseStatus status = SpdyHeaders.getStatus(i, spdyHeaderBlock);
        HttpVersion version = SpdyHeaders.getVersion(i, spdyHeaderBlock);
        SpdyHeaders.removeStatus(i, spdyHeaderBlock);
        SpdyHeaders.removeVersion(i, spdyHeaderBlock);
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(version, status);
        for (Map.Entry<String, String> entry : spdyHeaderBlock.getHeaders()) {
            defaultHttpResponse.addHeader(entry.getKey(), entry.getValue());
        }
        HttpHeaders.setKeepAlive(defaultHttpResponse, true);
        defaultHttpResponse.removeHeader("Transfer-Encoding");
        defaultHttpResponse.removeHeader("Trailer");
        return defaultHttpResponse;
    }
}
