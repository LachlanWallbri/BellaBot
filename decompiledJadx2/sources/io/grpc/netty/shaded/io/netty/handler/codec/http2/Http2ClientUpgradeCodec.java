package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64;
import io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64Dialect;
import io.grpc.netty.shaded.io.netty.handler.codec.http.FullHttpResponse;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.collection.CharObjectMap;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Http2ClientUpgradeCodec implements HttpClientUpgradeHandler.UpgradeCodec {
    private static final List<CharSequence> UPGRADE_HEADERS = Collections.singletonList(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER);
    private final Http2ConnectionHandler connectionHandler;
    private final String handlerName;
    private final ChannelHandler http2MultiplexHandler;
    private final ChannelHandler upgradeToHandler;

    public Http2ClientUpgradeCodec(Http2FrameCodec http2FrameCodec, ChannelHandler channelHandler) {
        this((String) null, http2FrameCodec, channelHandler);
    }

    public Http2ClientUpgradeCodec(String str, Http2FrameCodec http2FrameCodec, ChannelHandler channelHandler) {
        this(str, http2FrameCodec, channelHandler, null);
    }

    public Http2ClientUpgradeCodec(Http2ConnectionHandler http2ConnectionHandler) {
        this((String) null, http2ConnectionHandler);
    }

    public Http2ClientUpgradeCodec(Http2ConnectionHandler http2ConnectionHandler, Http2MultiplexHandler http2MultiplexHandler) {
        this((String) null, http2ConnectionHandler, http2MultiplexHandler);
    }

    public Http2ClientUpgradeCodec(String str, Http2ConnectionHandler http2ConnectionHandler) {
        this(str, http2ConnectionHandler, http2ConnectionHandler, null);
    }

    public Http2ClientUpgradeCodec(String str, Http2ConnectionHandler http2ConnectionHandler, Http2MultiplexHandler http2MultiplexHandler) {
        this(str, http2ConnectionHandler, http2ConnectionHandler, http2MultiplexHandler);
    }

    private Http2ClientUpgradeCodec(String str, Http2ConnectionHandler http2ConnectionHandler, ChannelHandler channelHandler, Http2MultiplexHandler http2MultiplexHandler) {
        this.handlerName = str;
        this.connectionHandler = (Http2ConnectionHandler) ObjectUtil.checkNotNull(http2ConnectionHandler, "connectionHandler");
        this.upgradeToHandler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "upgradeToHandler");
        this.http2MultiplexHandler = http2MultiplexHandler;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public CharSequence protocol() {
        return Http2CodecUtil.HTTP_UPGRADE_PROTOCOL_NAME;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public Collection<CharSequence> setUpgradeHeaders(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        httpRequest.headers().set(Http2CodecUtil.HTTP_UPGRADE_SETTINGS_HEADER, getSettingsHeaderValue(channelHandlerContext));
        return UPGRADE_HEADERS;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
    public void upgradeTo(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
        try {
            channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), this.handlerName, this.upgradeToHandler);
            if (this.http2MultiplexHandler != null) {
                channelHandlerContext.pipeline().addAfter(channelHandlerContext.pipeline().context(this.connectionHandler).name(), null, this.http2MultiplexHandler);
            }
            this.connectionHandler.onHttpClientUpgrade();
        } catch (Http2Exception e) {
            channelHandlerContext.fireExceptionCaught((Throwable) e);
            channelHandlerContext.close();
        }
    }

    private CharSequence getSettingsHeaderValue(ChannelHandlerContext channelHandlerContext) {
        ByteBuf byteBuf;
        Http2Settings localSettings;
        ByteBuf byteBuf2 = null;
        try {
            localSettings = this.connectionHandler.decoder().localSettings();
            byteBuf = channelHandlerContext.alloc().buffer(localSettings.size() * 6);
        } catch (Throwable th) {
            th = th;
            byteBuf = null;
        }
        try {
            for (CharObjectMap.PrimitiveEntry<Long> primitiveEntry : localSettings.entries()) {
                byteBuf.writeChar(primitiveEntry.key());
                byteBuf.writeInt(primitiveEntry.value().intValue());
            }
            byteBuf2 = Base64.encode(byteBuf, Base64Dialect.URL_SAFE);
            String byteBuf3 = byteBuf2.toString(CharsetUtil.UTF_8);
            ReferenceCountUtil.release(byteBuf);
            ReferenceCountUtil.release(byteBuf2);
            return byteBuf3;
        } catch (Throwable th2) {
            th = th2;
            ReferenceCountUtil.release(byteBuf);
            ReferenceCountUtil.release(byteBuf2);
            throw th;
        }
    }
}
