package org.jboss.netty.handler.codec.http.websocketx;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class WebSocketServerHandshaker00 extends WebSocketServerHandshaker {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketServerHandshaker00.class);

    public WebSocketServerHandshaker00(String str, String str2) {
        this(str, str2, Long.MAX_VALUE);
    }

    public WebSocketServerHandshaker00(String str, String str2, long j) {
        super(WebSocketVersion.V00, str, str2, j);
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    public ChannelFuture handshake(Channel channel, HttpRequest httpRequest) {
        boolean z = false;
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Channel %s WS Version 00 server handshake", channel.getId()));
        }
        if (!"Upgrade".equalsIgnoreCase(httpRequest.getHeader("Connection")) || !"WebSocket".equalsIgnoreCase(httpRequest.getHeader("Upgrade"))) {
            throw new WebSocketHandshakeException("not a WebSocket handshake request: missing upgrade");
        }
        if (httpRequest.containsHeader("Sec-WebSocket-Key1") && httpRequest.containsHeader("Sec-WebSocket-Key2")) {
            z = true;
        }
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, new HttpResponseStatus(101, z ? "WebSocket Protocol Handshake" : "Web Socket Protocol Handshake"));
        defaultHttpResponse.addHeader("Upgrade", "WebSocket");
        defaultHttpResponse.addHeader("Connection", "Upgrade");
        if (z) {
            defaultHttpResponse.addHeader("Sec-WebSocket-Origin", httpRequest.getHeader("Origin"));
            defaultHttpResponse.addHeader("Sec-WebSocket-Location", getWebSocketUrl());
            String header = httpRequest.getHeader("Sec-WebSocket-Protocol");
            if (header != null) {
                String selectSubprotocol = selectSubprotocol(header);
                if (selectSubprotocol == null) {
                    throw new WebSocketHandshakeException("Requested subprotocol(s) not supported: " + header);
                }
                defaultHttpResponse.addHeader("Sec-WebSocket-Protocol", selectSubprotocol);
                setSelectedSubprotocol(selectSubprotocol);
            }
            String header2 = httpRequest.getHeader("Sec-WebSocket-Key1");
            String header3 = httpRequest.getHeader("Sec-WebSocket-Key2");
            int parseLong = (int) (Long.parseLong(header2.replaceAll("[^0-9]", "")) / header2.replaceAll("[^ ]", "").length());
            int parseLong2 = (int) (Long.parseLong(header3.replaceAll("[^0-9]", "")) / header3.replaceAll("[^ ]", "").length());
            long readLong = httpRequest.getContent().readLong();
            ChannelBuffer buffer = ChannelBuffers.buffer(16);
            buffer.writeInt(parseLong);
            buffer.writeInt(parseLong2);
            buffer.writeLong(readLong);
            defaultHttpResponse.setContent(ChannelBuffers.wrappedBuffer(WebSocketUtil.md5(buffer.array())));
        } else {
            defaultHttpResponse.addHeader("WebSocket-Origin", httpRequest.getHeader("Origin"));
            defaultHttpResponse.addHeader("WebSocket-Location", getWebSocketUrl());
            String header4 = httpRequest.getHeader("WebSocket-Protocol");
            if (header4 != null) {
                defaultHttpResponse.addHeader("WebSocket-Protocol", selectSubprotocol(header4));
            }
        }
        ChannelPipeline pipeline = channel.getPipeline();
        if (pipeline.get(HttpChunkAggregator.class) != null) {
            pipeline.remove(HttpChunkAggregator.class);
        }
        pipeline.replace(HttpRequestDecoder.class, "wsdecoder", new WebSocket00FrameDecoder(getMaxFramePayloadLength()));
        ChannelFuture write = channel.write(defaultHttpResponse);
        pipeline.replace(HttpResponseEncoder.class, "wsencoder", new WebSocket00FrameEncoder());
        return write;
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        return channel.write(closeWebSocketFrame);
    }
}
