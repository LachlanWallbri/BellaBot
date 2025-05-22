package com.aliyun.alink.p022h2.connection.p026a;

import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.connection.InterfaceC0885b;
import com.aliyun.alink.p022h2.connection.InterfaceC0886c;
import com.aliyun.alink.p022h2.netty.C0889b;
import com.aliyun.alink.p022h2.netty.C0890c;
import com.aliyun.alink.p022h2.netty.C0891d;
import com.aliyun.alink.p022h2.p025b.C0879a;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.net.SocketAddress;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ConnectionManagerImpl.java */
/* renamed from: com.aliyun.alink.h2.connection.a.b */
/* loaded from: classes.dex */
public class C0884b implements InterfaceC0886c {

    /* renamed from: a */
    private boolean f587a;

    /* renamed from: b */
    private Bootstrap f588b;

    /* renamed from: c */
    private ChannelGroup f589c;

    /* renamed from: d */
    private EventLoopGroup f590d;

    /* renamed from: e */
    private final long f591e;

    /* renamed from: f */
    private final long f592f;

    /* renamed from: h */
    private InterfaceC0885b f594h = new InterfaceC0885b() { // from class: com.aliyun.alink.h2.connection.a.b.1
        @Override // com.aliyun.alink.p022h2.connection.InterfaceC0885b
        public void onSettingReceive(Connection connection, Http2Settings http2Settings) {
            if (C0884b.this.f593g == null) {
                return;
            }
            for (int i = 0; i < C0884b.this.f593g.size(); i++) {
                ((InterfaceC0885b) C0884b.this.f593g.get(i)).onSettingReceive(connection, http2Settings);
            }
        }

        @Override // com.aliyun.alink.p022h2.connection.InterfaceC0885b
        public void onStatusChange(ConnectionStatus connectionStatus, Connection connection) {
            if (C0884b.this.f593g == null) {
                return;
            }
            for (int i = 0; i < C0884b.this.f593g.size(); i++) {
                ((InterfaceC0885b) C0884b.this.f593g.get(i)).onStatusChange(connectionStatus, connection);
            }
        }
    };

    /* renamed from: g */
    private List<InterfaceC0885b> f593g = new ArrayList();

    public C0884b(boolean z, long j, long j2) {
        this.f587a = z;
        this.f591e = j;
        this.f592f = j2;
        m240c();
    }

    /* renamed from: c */
    private void m240c() {
        C0879a.m208b("ConnectionManagerImpl", "[ConnectionManagerImpl]initialize netty");
        C0891d m241d = m241d();
        this.f590d = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        this.f588b = bootstrap;
        bootstrap.group(this.f590d);
        this.f588b.channel(NioSocketChannel.class);
        this.f588b.option(ChannelOption.SO_KEEPALIVE, true);
        this.f588b.handler(m241d);
        this.f589c = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /* renamed from: d */
    private C0891d m241d() {
        try {
            return new C0891d(new C0890c(this.f592f), this.f587a);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            C0879a.m207a("ConnectionManagerImpl", "failed to initialize netty, {}", e);
            return null;
        }
    }

    @Override // com.aliyun.alink.p022h2.connection.InterfaceC0886c
    /* renamed from: a */
    public List<Connection> mo244a() {
        ArrayList arrayList = new ArrayList();
        List<Channel> m242e = m242e();
        if (m242e == null || m242e.size() < 1) {
            return null;
        }
        for (int i = 0; i < m242e.size(); i++) {
            arrayList.add(m238a(m242e.get(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Connection m238a(Channel channel) {
        return ((C0889b) channel.pipeline().get(C0889b.class)).m253a();
    }

    @Override // com.aliyun.alink.p022h2.connection.InterfaceC0886c
    /* renamed from: a */
    public Connection mo243a(SocketAddress socketAddress) {
        C0879a.m208b("ConnectionManagerImpl", "connecting to {}" + socketAddress);
        ChannelFuture connect = this.f588b.connect(socketAddress);
        connect.get();
        if (connect.isSuccess()) {
            Channel channel = connect.channel();
            this.f589c.add(channel);
            channel.closeFuture().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: com.aliyun.alink.h2.connection.a.b.2
                @Override // io.netty.util.concurrent.GenericFutureListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void operationComplete(ChannelFuture channelFuture) {
                    C0884b.this.m238a(channelFuture.channel()).onConnectionClosed();
                }
            });
            ChannelPipeline pipeline = channel.pipeline();
            long j = this.f591e;
            pipeline.addFirst("heartBeatHandler", new IdleStateHandler(j, j, 0L, TimeUnit.MILLISECONDS));
            Connection m238a = m238a(channel);
            m238a.setConnectionListener(this.f594h);
            return m238a;
        }
        throw new ExecutionException(connect.cause());
    }

    /* renamed from: e */
    private List<Channel> m242e() {
        ArrayList arrayList = new ArrayList();
        for (Channel channel : this.f589c) {
            if (channel != null && channel.isOpen() && channel.isActive()) {
                arrayList.add(channel);
            }
        }
        return arrayList;
    }

    @Override // com.aliyun.alink.p022h2.connection.InterfaceC0886c
    /* renamed from: b */
    public void mo246b() {
        this.f589c.close();
        this.f590d.shutdownGracefully();
    }

    @Override // com.aliyun.alink.p022h2.connection.InterfaceC0886c
    /* renamed from: a */
    public void mo245a(InterfaceC0885b interfaceC0885b) {
        this.f593g.add(interfaceC0885b);
    }

    @Override // com.aliyun.alink.p022h2.connection.InterfaceC0886c
    /* renamed from: b */
    public void mo247b(InterfaceC0885b interfaceC0885b) {
        this.f593g.remove(interfaceC0885b);
    }
}
