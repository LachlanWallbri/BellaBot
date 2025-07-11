package io.netty.handler.codec.http.websocketx.extensions;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocketServerExtensionHandler extends ChannelDuplexHandler {
    private final List<WebSocketServerExtensionHandshaker> extensionHandshakers;
    private List<WebSocketServerExtension> validExtensions;

    public WebSocketServerExtensionHandler(WebSocketServerExtensionHandshaker... webSocketServerExtensionHandshakerArr) {
        if (webSocketServerExtensionHandshakerArr == null) {
            throw new NullPointerException("extensionHandshakers");
        }
        if (webSocketServerExtensionHandshakerArr.length == 0) {
            throw new IllegalArgumentException("extensionHandshakers must contains at least one handshaker");
        }
        this.extensionHandshakers = Arrays.asList(webSocketServerExtensionHandshakerArr);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        String asString;
        if (obj instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) obj;
            if (WebSocketExtensionUtil.isWebsocketUpgrade(httpRequest.headers()) && (asString = httpRequest.headers().getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS)) != null) {
                int i = 0;
                for (WebSocketExtensionData webSocketExtensionData : WebSocketExtensionUtil.extractExtensions(asString)) {
                    Iterator<WebSocketServerExtensionHandshaker> it = this.extensionHandshakers.iterator();
                    WebSocketServerExtension webSocketServerExtension = null;
                    while (webSocketServerExtension == null && it.hasNext()) {
                        webSocketServerExtension = it.next().handshakeExtension(webSocketExtensionData);
                    }
                    if (webSocketServerExtension != null && (webSocketServerExtension.rsv() & i) == 0) {
                        if (this.validExtensions == null) {
                            this.validExtensions = new ArrayList(1);
                        }
                        i |= webSocketServerExtension.rsv();
                        this.validExtensions.add(webSocketServerExtension);
                    }
                }
            }
        }
        super.channelRead(channelHandlerContext, obj);
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void write(final ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (obj instanceof HttpResponse) {
            HttpResponse httpResponse = (HttpResponse) obj;
            if (WebSocketExtensionUtil.isWebsocketUpgrade(httpResponse.headers()) && this.validExtensions != null) {
                String asString = httpResponse.headers().getAsString(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS);
                Iterator<WebSocketServerExtension> it = this.validExtensions.iterator();
                while (it.hasNext()) {
                    WebSocketExtensionData newReponseData = it.next().newReponseData();
                    asString = WebSocketExtensionUtil.appendExtension(asString, newReponseData.name(), newReponseData.parameters());
                }
                channelPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler.1
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            for (WebSocketServerExtension webSocketServerExtension : WebSocketServerExtensionHandler.this.validExtensions) {
                                WebSocketExtensionDecoder newExtensionDecoder = webSocketServerExtension.newExtensionDecoder();
                                WebSocketExtensionEncoder newExtensionEncoder = webSocketServerExtension.newExtensionEncoder();
                                channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), newExtensionDecoder.getClass().getName(), newExtensionDecoder);
                                channelHandlerContext.pipeline().addAfter(channelHandlerContext.name(), newExtensionEncoder.getClass().getName(), newExtensionEncoder);
                            }
                        }
                        channelHandlerContext.pipeline().remove(channelHandlerContext.name());
                    }
                });
                if (asString != null) {
                    httpResponse.headers().set(HttpHeaderNames.SEC_WEBSOCKET_EXTENSIONS, asString);
                }
            }
        }
        super.write(channelHandlerContext, obj, channelPromise);
    }
}
