package com.aliyun.alink.p022h2.connection;

import com.aliyun.alink.p022h2.api.Http2StreamListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2Connection;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Connection extends ConnectionReader, ConnectionWriter {
    void close();

    Object getProperty(Http2Connection.PropertyKey propertyKey);

    Http2Connection.PropertyKey getPropertyKey(String str);

    ConnectionStatus getStatus();

    boolean isAuthorized();

    void onConnectionClosed();

    void onError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th);

    void removeConnectListener();

    void setConnectionListener(InterfaceC0885b interfaceC0885b);

    void setDefaultStreamListener(Http2StreamListener http2StreamListener);

    void setProperty(Http2Connection.PropertyKey propertyKey, Object obj);

    void setStatus(ConnectionStatus connectionStatus);
}
