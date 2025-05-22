package org.jboss.netty.handler.codec.http.websocketx;

import java.io.UnsupportedEncodingException;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
public class WebSocketServerHandshaker13 extends WebSocketServerHandshaker {
    public static final String WEBSOCKET_13_ACCEPT_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocketServerHandshaker13.class);
    private final boolean allowExtensions;

    public WebSocketServerHandshaker13(String str, String str2, boolean z) {
        this(str, str2, z, Long.MAX_VALUE);
    }

    public WebSocketServerHandshaker13(String str, String str2, boolean z, long j) {
        super(WebSocketVersion.V13, str, str2, j);
        this.allowExtensions = z;
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    public ChannelFuture handshake(Channel channel, HttpRequest httpRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Channel %s WS Version 13 server handshake", channel.getId()));
        }
        DefaultHttpResponse defaultHttpResponse = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS);
        String header = httpRequest.getHeader("Sec-WebSocket-Key");
        if (header == null) {
            throw new WebSocketHandshakeException("not a WebSocket request: missing key");
        }
        try {
            String base64 = WebSocketUtil.base64(WebSocketUtil.sha1((header + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(CharsetUtil.US_ASCII.name())));
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("WS Version 13 Server Handshake key: %s. Response: %s.", header, base64));
            }
            defaultHttpResponse.setStatus(HttpResponseStatus.SWITCHING_PROTOCOLS);
            defaultHttpResponse.addHeader("Upgrade", "WebSocket".toLowerCase());
            defaultHttpResponse.addHeader("Connection", "Upgrade");
            defaultHttpResponse.addHeader("Sec-WebSocket-Accept", base64);
            String header2 = httpRequest.getHeader("Sec-WebSocket-Protocol");
            if (header2 != null) {
                String selectSubprotocol = selectSubprotocol(header2);
                if (selectSubprotocol == null) {
                    throw new WebSocketHandshakeException("Requested subprotocol(s) not supported: " + header2);
                }
                defaultHttpResponse.addHeader("Sec-WebSocket-Protocol", selectSubprotocol);
                setSelectedSubprotocol(selectSubprotocol);
            }
            ChannelFuture write = channel.write(defaultHttpResponse);
            ChannelPipeline pipeline = channel.getPipeline();
            if (pipeline.get(HttpChunkAggregator.class) != null) {
                pipeline.remove(HttpChunkAggregator.class);
            }
            pipeline.replace(HttpRequestDecoder.class, "wsdecoder", new WebSocket13FrameDecoder(true, this.allowExtensions, getMaxFramePayloadLength()));
            pipeline.replace(HttpResponseEncoder.class, "wsencoder", new WebSocket13FrameEncoder(false));
            return write;
        } catch (UnsupportedEncodingException e) {
            return Channels.failedFuture(channel, e);
        }
    }

    @Override // org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker
    public ChannelFuture close(Channel channel, CloseWebSocketFrame closeWebSocketFrame) {
        ChannelFuture write = channel.write(closeWebSocketFrame);
        write.addListener(ChannelFutureListener.CLOSE);
        return write;
    }
}
