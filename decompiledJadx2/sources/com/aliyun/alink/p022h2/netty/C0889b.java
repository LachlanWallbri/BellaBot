package com.aliyun.alink.p022h2.netty;

import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.connection.p026a.C0883a;
import com.aliyun.alink.p022h2.p025b.C0879a;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2FrameListener;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NettyHttp2Handler.java */
/* renamed from: com.aliyun.alink.h2.netty.b */
/* loaded from: classes.dex */
public class C0889b extends Http2ConnectionHandler implements Http2FrameListener {

    /* renamed from: a */
    private long f601a;

    /* renamed from: b */
    private long f602b;

    /* renamed from: c */
    private Connection f603c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0889b(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings, long j) {
        super(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings);
        this.f601a = j;
    }

    /* renamed from: a */
    public Connection m253a() {
        if (this.f603c == null) {
            C0879a.m210d("NettyHttp2Handler", "failed to get connection, netty handler not initialized correctly");
        }
        return this.f603c;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        super.channelRead(channelHandlerContext, obj);
        m252c();
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        super.handlerAdded(channelHandlerContext);
        C0883a c0883a = new C0883a(this, channelHandlerContext);
        this.f603c = c0883a;
        c0883a.setStatus(ConnectionStatus.CREATING);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, boolean z) {
        C0879a.m206a("NettyHttp2Handler", "onDataRead, streamId: " + i + ", size: " + byteBuf.readableBytes() + ", ES: " + z);
        return this.f603c.onDataRead(channelHandlerContext, i, byteBuf, i2, z);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, boolean z) {
        onHeadersRead(channelHandlerContext, i, http2Headers, connection().connectionStream().mo3940id(), (short) 16, false, i2, z);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, Http2Headers http2Headers, int i2, short s, boolean z, int i3, boolean z2) {
        C0879a.m206a("NettyHttp2Handler", "onHeadersRead, streamId: " + i + ", header: " + http2Headers + ", weight: " + ((int) s) + ", dependency: " + i2 + ", exclusive: " + z + ", isEnd: " + z2);
        this.f603c.onHeadersRead(channelHandlerContext, i, http2Headers, i2, s, z, i3, z2);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPriorityRead(ChannelHandlerContext channelHandlerContext, int i, int i2, short s, boolean z) {
        C0879a.m206a("NettyHttp2Handler", "onPriorityRead, streamId: " + i + ", streamDependency: " + i2 + ", weight: " + ((int) s) + ", exclusive: " + z);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) {
        C0879a.m206a("NettyHttp2Handler", "onRstStreamRead, streamId: " + i + ", errorCode: " + j);
        this.f603c.onRstStreamRead(channelHandlerContext, i, j);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsAckRead(ChannelHandlerContext channelHandlerContext) {
        C0879a.m206a("NettyHttp2Handler", "onSettingsAckRead");
        this.f603c.setStatus(ConnectionStatus.CREATED);
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.handler.codec.http2.Http2LifecycleManager
    public void onError(ChannelHandlerContext channelHandlerContext, boolean z, Throwable th) {
        super.onError(channelHandlerContext, z, th);
        C0879a.m210d("NettyHttp2Handler", "error occurs, close channel. channel id: " + channelHandlerContext.channel() + ", outbound: " + z + ", error:" + th);
        this.f603c.onError(channelHandlerContext, z, th);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
        C0879a.m206a("onSettingsRead, settings: {}", http2Settings.toString());
        this.f603c.onSettingsRead(channelHandlerContext, http2Settings);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingRead(ChannelHandlerContext channelHandlerContext, long j) {
        C0879a.m206a("NettyHttp2Handler", "onPingRead, data: " + j);
        encoder().frameWriter().writePing(channelHandlerContext, true, j, channelHandlerContext.voidPromise());
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPingAckRead(ChannelHandlerContext channelHandlerContext, long j) {
        C0879a.m206a("NettyHttp2Handler", "onPingAckRead, data: " + j);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onPushPromiseRead(ChannelHandlerContext channelHandlerContext, int i, int i2, Http2Headers http2Headers, int i3) {
        C0879a.m206a("NettyHttp2Handler", "onPushPromiseRead, streamId: " + i + ", promisedStreamId: " + i2 + ", headers size: " + http2Headers.size());
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
        C0879a.m206a("NettyHttp2Handler", "onGoAwayRead, lastStreamId: " + i + ", errorCode: " + j + ", " + new String(ByteBufUtil.getBytes(byteBuf)));
        this.f603c.onGoAwayRead(channelHandlerContext, i, j, byteBuf);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onWindowUpdateRead(ChannelHandlerContext channelHandlerContext, int i, int i2) {
        C0879a.m206a("NettyHttp2Handler", "onWindowUpdateRead, streamId: " + i + ", increment size: " + i2);
    }

    @Override // io.netty.handler.codec.http2.Http2FrameListener
    public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, int i, Http2Flags http2Flags, ByteBuf byteBuf) {
        C0879a.m206a("NettyHttp2Handler", "onUnknownFrame, frameType: " + ((int) b) + ", streamId: " + i + ", size: " + byteBuf.readableBytes() + ", flags: " + http2Flags.toString());
        this.f603c.onUnknownFrame(channelHandlerContext, b, i, http2Flags, byteBuf);
    }

    @Override // io.netty.handler.codec.http2.Http2ConnectionHandler, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        super.exceptionCaught(channelHandlerContext, th);
        C0879a.m207a("NettyHttp2Handler", "exceptionCaught: ", new Exception(th));
        this.f603c.onError(channelHandlerContext, false, th);
        this.f603c.close();
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) {
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) obj;
            if (idleStateEvent.state() == IdleState.READER_IDLE || idleStateEvent.state() == IdleState.WRITER_IDLE) {
                if (idleStateEvent.state() == IdleState.READER_IDLE && m251b()) {
                    C0879a.m210d("NettyHttp2Handler", "connection heartbeat timeout, channel:[" + channelHandlerContext.channel().mo3929id() + "], remote address:[" + channelHandlerContext.channel().remoteAddress() + "] ");
                    throw new IOException("connection heartbeat timeout");
                }
                C0879a.m206a("NettyHttp2Handler", "send heartbeat, channel:[" + channelHandlerContext.channel().mo3929id() + "], remote address:[" + channelHandlerContext.channel().remoteAddress() + "] ");
                encoder().frameWriter().writePing(channelHandlerContext, false, System.currentTimeMillis(), channelHandlerContext.voidPromise());
                channelHandlerContext.pipeline().flush();
            }
        }
    }

    /* renamed from: b */
    private boolean m251b() {
        return System.currentTimeMillis() - this.f602b > this.f601a;
    }

    /* renamed from: c */
    private void m252c() {
        this.f602b = System.currentTimeMillis();
    }
}
