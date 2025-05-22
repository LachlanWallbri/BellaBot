package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64Dialect;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpHeaders;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Http2ServerUpgradeCodec implements HttpServerUpgradeHandler.UpgradeCodec {
    private final Http2ConnectionHandler connectionHandler;
    private final Http2FrameReader frameReader;
    private final String handlerName;
    private final ChannelHandler[] handlers;
    private Http2Settings settings;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2ServerUpgradeCodec.class);
    private static final List<CharSequence> REQUIRED_UPGRADE_HEADERS = Collections.singletonList(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
    private static final ChannelHandler[] EMPTY_HANDLERS = new ChannelHandler[0];

    public Http2ServerUpgradeCodec(Http2ConnectionHandler http2ConnectionHandler) {
        this(null, http2ConnectionHandler, EMPTY_HANDLERS);
    }

    public Http2ServerUpgradeCodec(Http2MultiplexCodec http2MultiplexCodec) {
        this(null, http2MultiplexCodec, EMPTY_HANDLERS);
    }

    public Http2ServerUpgradeCodec(String str, Http2ConnectionHandler http2ConnectionHandler) {
        this(str, http2ConnectionHandler, EMPTY_HANDLERS);
    }

    public Http2ServerUpgradeCodec(String str, Http2MultiplexCodec http2MultiplexCodec) {
        this(str, http2MultiplexCodec, EMPTY_HANDLERS);
    }

    public Http2ServerUpgradeCodec(Http2FrameCodec http2FrameCodec, ChannelHandler... channelHandlerArr) {
        this(null, http2FrameCodec, channelHandlerArr);
    }

    private Http2ServerUpgradeCodec(String str, Http2ConnectionHandler http2ConnectionHandler, ChannelHandler... channelHandlerArr) {
        this.handlerName = str;
        this.connectionHandler = http2ConnectionHandler;
        this.handlers = channelHandlerArr;
        this.frameReader = new DefaultHttp2FrameReader();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler.UpgradeCodec
    public Collection<CharSequence> requiredUpgradeHeaders() {
        return REQUIRED_UPGRADE_HEADERS;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler.UpgradeCodec
    public boolean prepareUpgradeResponse(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest, HttpHeaders httpHeaders) {
        try {
            List<String> all = fullHttpRequest.headers().getAll(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
            if (all.isEmpty() || all.size() > 1) {
                throw new IllegalArgumentException("There must be 1 and only 1 " + ((Object) Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER) + " header.");
            }
            this.settings = decodeSettingsHeader(channelHandlerContext, all.get(0));
            return true;
        } catch (Throwable th) {
            logger.info("Error during upgrade to HTTP/2", th);
            return false;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler.UpgradeCodec
    public void upgradeTo(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        try {
            channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), this.handlerName, this.connectionHandler);
            if (this.handlers != null) {
                String name = channelHandlerContext.pipeline().context(this.connectionHandler).name();
                for (int length = this.handlers.length - 1; length >= 0; length--) {
                    channelHandlerContext.pipeline().addAfter(name, null, this.handlers[length]);
                }
            }
            this.connectionHandler.onHttpServerUpgrade(this.settings);
        } catch (Http2Exception e) {
            channelHandlerContext.fireExceptionCaught((Throwable) e);
            channelHandlerContext.close();
        }
    }

    private Http2Settings decodeSettingsHeader(ChannelHandlerContext channelHandlerContext, CharSequence charSequence) throws Http2Exception {
        ByteBuf encodeString = ByteBufUtil.encodeString(channelHandlerContext.alloc(), CharBuffer.wrap(charSequence), CharsetUtil.UTF_8);
        try {
            return decodeSettings(channelHandlerContext, createSettingsFrame(channelHandlerContext, Base64.decode(encodeString, Base64Dialect.URL_SAFE)));
        } finally {
            encodeString.release();
        }
    }

    private Http2Settings decodeSettings(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Http2Exception {
        try {
            final Http2Settings http2Settings = new Http2Settings();
            this.frameReader.readFrame(channelHandlerContext, byteBuf, new Http2FrameAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ServerUpgradeCodec.1
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FrameListener
                public void onSettingsRead(ChannelHandlerContext channelHandlerContext2, Http2Settings http2Settings2) {
                    http2Settings.copyFrom(http2Settings2);
                }
            });
            return http2Settings;
        } finally {
            byteBuf.release();
        }
    }

    private static ByteBuf createSettingsFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        ByteBuf buffer = channelHandlerContext.alloc().buffer(byteBuf.readableBytes() + 9);
        Http2CodecUtil.writeFrameHeader(buffer, byteBuf.readableBytes(), (byte) 4, new Http2Flags(), 0);
        buffer.writeBytes(byteBuf);
        byteBuf.release();
        return buffer;
    }
}
