package com.aliyun.alink.p022h2.netty;

import com.aliyun.alink.p022h2.p025b.C0879a;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NettyHttp2Initializer.java */
/* renamed from: com.aliyun.alink.h2.netty.d */
/* loaded from: classes.dex */
public class C0891d extends ChannelInitializer<SocketChannel> {

    /* renamed from: a */
    private C0890c f607a;

    /* renamed from: b */
    private boolean f608b;

    /* renamed from: c */
    private SSLContext f609c;

    public C0891d(C0890c c0890c, boolean z) {
        this.f608b = false;
        this.f607a = c0890c;
        this.f608b = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.ChannelInitializer
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initChannel(SocketChannel socketChannel) {
        C0879a.m208b("NettyHttp2Initializer", "init http2 handler. enable SSL : " + this.f608b);
        if (this.f608b) {
            if (this.f609c == null) {
                SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                this.f609c = sSLContext;
                sSLContext.init(null, InsecureTrustManagerFactory.INSTANCE.getTrustManagers(), null);
            }
            SSLEngine createSSLEngine = this.f609c.createSSLEngine();
            createSSLEngine.setUseClientMode(true);
            socketChannel.pipeline().addLast(new SslHandler(createSSLEngine));
        }
        socketChannel.pipeline().addLast(this.f607a.build());
    }
}
