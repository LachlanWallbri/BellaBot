package com.aliyun.alink.p022h2.connection.p026a;

import com.aliyun.alink.p022h2.api.CompletableListener;
import com.aliyun.alink.p022h2.api.Http2StreamListener;
import com.aliyun.alink.p022h2.api.StreamWriteContext;
import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.connection.InterfaceC0882a;
import com.aliyun.alink.p022h2.connection.InterfaceC0885b;
import com.aliyun.alink.p022h2.connection.InterfaceC0887d;
import com.aliyun.alink.p022h2.netty.C0889b;
import com.aliyun.alink.p022h2.p025b.C0879a;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.codec.http2.Http2Flags;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.Http2StreamVisitor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ConnectionImpl.java */
/* renamed from: com.aliyun.alink.h2.connection.a.a */
/* loaded from: classes.dex */
public class C0883a implements Connection {

    /* renamed from: a */
    private final Http2Connection.PropertyKey f528a;

    /* renamed from: b */
    private Http2Connection f529b;

    /* renamed from: c */
    private ChannelHandlerContext f530c;

    /* renamed from: d */
    private Http2ConnectionDecoder f531d;

    /* renamed from: e */
    private Http2ConnectionEncoder f532e;

    /* renamed from: f */
    private InterfaceC0885b f533f;

    /* renamed from: g */
    private ConnectionStatus f534g;

    /* renamed from: h */
    private Map<String, Http2Connection.PropertyKey> f535h = new ConcurrentHashMap();

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public void onGoAwayRead(ChannelHandlerContext channelHandlerContext, int i, long j, ByteBuf byteBuf) {
    }

    public C0883a(C0889b c0889b, ChannelHandlerContext channelHandlerContext) {
        this.f529b = c0889b.connection();
        this.f531d = c0889b.decoder();
        this.f532e = c0889b.encoder();
        this.f530c = channelHandlerContext;
        this.f528a = this.f529b.newKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m225a(Http2Stream http2Stream, Http2StreamListener http2StreamListener) {
        http2Stream.setProperty(this.f528a, http2StreamListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Http2Stream m221a(int i) {
        return this.f529b.stream(i);
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public int onDataRead(ChannelHandlerContext channelHandlerContext, int i, ByteBuf byteBuf, int i2, final boolean z) {
        final byte[] copyOf;
        final Http2Stream m221a = m221a(i);
        int readableBytes = byteBuf.readableBytes();
        if (i2 == 0 && byteBuf.hasArray()) {
            copyOf = byteBuf.readBytes(readableBytes).array();
        } else {
            int i3 = readableBytes - i2;
            byte[] bArr = new byte[readableBytes];
            byteBuf.readBytes(bArr, 0, readableBytes);
            copyOf = i2 == 0 ? bArr : Arrays.copyOf(bArr, i3);
        }
        m227a(m221a, new InterfaceC0887d<Http2StreamListener>() { // from class: com.aliyun.alink.h2.connection.a.a.1
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0887d
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo230a(Http2StreamListener http2StreamListener) {
                http2StreamListener.onDataRead(C0883a.this, m221a, copyOf, z);
            }
        });
        return readableBytes;
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public void onHeadersRead(ChannelHandlerContext channelHandlerContext, int i, final Http2Headers http2Headers, int i2, short s, boolean z, int i3, final boolean z2) {
        final Http2Stream m221a = m221a(i);
        if (m219a(m221a) == null && m218a() != null) {
            C0879a.m206a("ConnectionImpl", "set default stream listener for streamId:{}" + m221a.mo3940id());
            m225a(m221a, m218a());
        }
        m227a(m221a, new InterfaceC0887d<Http2StreamListener>() { // from class: com.aliyun.alink.h2.connection.a.a.8
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0887d
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo230a(Http2StreamListener http2StreamListener) {
                http2StreamListener.onHeadersRead(C0883a.this, m221a, http2Headers, z2);
            }
        });
    }

    /* renamed from: a */
    private Http2StreamListener m218a() {
        return m219a(this.f529b.connectionStream());
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void setConnectionListener(InterfaceC0885b interfaceC0885b) {
        this.f533f = interfaceC0885b;
        if (interfaceC0885b != null) {
            interfaceC0885b.onStatusChange(getStatus(), this);
        }
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void removeConnectListener() {
        setConnectionListener(null);
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public void onSettingsRead(ChannelHandlerContext channelHandlerContext, Http2Settings http2Settings) {
        InterfaceC0885b interfaceC0885b = this.f533f;
        if (interfaceC0885b != null) {
            interfaceC0885b.onSettingReceive(this, http2Settings);
        }
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, final int i, final long j) {
        m227a(m221a(i), new InterfaceC0887d<Http2StreamListener>() { // from class: com.aliyun.alink.h2.connection.a.a.9
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0887d
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo230a(Http2StreamListener http2StreamListener) {
                C0883a c0883a = C0883a.this;
                http2StreamListener.onStreamError(c0883a, c0883a.m221a(i), new IOException("rst frame received, code : " + j));
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionReader
    public void onUnknownFrame(ChannelHandlerContext channelHandlerContext, byte b, final int i, Http2Flags http2Flags, final ByteBuf byteBuf) {
        m227a(m221a(i), new InterfaceC0887d<Http2StreamListener>() { // from class: com.aliyun.alink.h2.connection.a.a.10
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0887d
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo230a(Http2StreamListener http2StreamListener) {
                C0883a c0883a = C0883a.this;
                http2StreamListener.onStreamError(c0883a, c0883a.m221a(i), new IOException("unknown frame received, hex dump: " + ByteBufUtil.hexDump(byteBuf)));
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void onConnectionClosed() {
        setStatus(ConnectionStatus.CLOSED);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void setStatus(ConnectionStatus connectionStatus) {
        InterfaceC0885b interfaceC0885b = this.f533f;
        if (interfaceC0885b != null) {
            interfaceC0885b.onStatusChange(connectionStatus, this);
        }
        this.f534g = connectionStatus;
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public ConnectionStatus getStatus() {
        return this.f534g;
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public boolean isAuthorized() {
        return this.f534g.equals(ConnectionStatus.AUTHORIZED);
    }

    public String toString() {
        return this.f530c.channel().mo3929id().asShortText();
    }

    /* renamed from: a */
    private Http2StreamListener m219a(Http2Stream http2Stream) {
        return (Http2StreamListener) http2Stream.getProperty(this.f528a);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void onError(ChannelHandlerContext channelHandlerContext, boolean z, final Throwable th) {
        try {
            this.f529b.forEachActiveStream(new Http2StreamVisitor() { // from class: com.aliyun.alink.h2.connection.a.a.11
                @Override // io.netty.handler.codec.http2.Http2StreamVisitor
                public boolean visit(final Http2Stream http2Stream) {
                    C0883a.this.m227a(http2Stream, new InterfaceC0887d<Http2StreamListener>() { // from class: com.aliyun.alink.h2.connection.a.a.11.1
                        @Override // com.aliyun.alink.p022h2.connection.InterfaceC0887d
                        /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                        public void mo230a(Http2StreamListener http2StreamListener) {
                            http2StreamListener.onStreamError(C0883a.this, http2Stream, new IOException(th));
                        }
                    });
                    return true;
                }
            });
        } catch (Http2Exception e) {
            C0879a.m207a("ConnectionImpl", "error occurs when notify listener. exception: ", e);
        }
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public Http2Connection.PropertyKey getPropertyKey(String str) {
        Map<String, Http2Connection.PropertyKey> map = this.f535h;
        if (map != null && map.get(str) == null) {
            this.f535h.put(str, this.f529b.newKey());
        }
        return this.f535h.get(str);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void setProperty(Http2Connection.PropertyKey propertyKey, Object obj) {
        this.f529b.connectionStream().setProperty(propertyKey, obj);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public Object getProperty(Http2Connection.PropertyKey propertyKey) {
        return this.f529b.connectionStream().getProperty(propertyKey);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void setDefaultStreamListener(Http2StreamListener http2StreamListener) {
        m225a(this.f529b.connectionStream(), http2StreamListener);
    }

    @Override // com.aliyun.alink.p022h2.connection.Connection
    public void close() {
        this.f530c.close().syncUninterruptibly();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m227a(Http2Stream http2Stream, InterfaceC0887d<Http2StreamListener> interfaceC0887d) {
        Http2StreamListener m219a = m219a(http2Stream);
        if (m219a == null) {
            return false;
        }
        interfaceC0887d.mo230a(m219a);
        return true;
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionWriter
    public void writeHeaders(final Http2Headers http2Headers, final boolean z, final Http2StreamListener http2StreamListener, final CompletableListener<StreamWriteContext> completableListener) {
        m224a(new InterfaceC0882a<a<StreamWriteContext>, ChannelPromise>() { // from class: com.aliyun.alink.h2.connection.a.a.12
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0882a
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo217a(a<StreamWriteContext> aVar, ChannelPromise channelPromise) {
                int incrementAndGetNextStreamId = C0883a.this.f529b.local().incrementAndGetNextStreamId();
                C0879a.m206a("ConnectionImpl", "write headers, streamId:" + incrementAndGetNextStreamId + ", headers:" + http2Headers);
                Http2Stream stream = C0883a.this.f529b.stream(incrementAndGetNextStreamId);
                if (stream == null) {
                    try {
                        stream = C0883a.this.f529b.local().createStream(incrementAndGetNextStreamId, false);
                    } catch (Http2Exception e) {
                        channelPromise.tryFailure(e);
                    }
                }
                Http2StreamListener http2StreamListener2 = http2StreamListener;
                if (http2StreamListener2 != null) {
                    C0883a.this.m225a(stream, http2StreamListener2);
                }
                aVar.m236a(new StreamWriteContext(stream, C0883a.this));
                C0883a.this.f532e.writeHeaders(C0883a.this.f530c, incrementAndGetNextStreamId, http2Headers, 0, z, channelPromise);
                C0883a.this.f530c.pipeline().flush();
            }
        }, new a<StreamWriteContext>() { // from class: com.aliyun.alink.h2.connection.a.a.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void complete(StreamWriteContext streamWriteContext) {
                C0879a.m206a("ConnectionImpl", "writeHeaders complete() called with: result = [" + streamWriteContext + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.complete(streamWriteContext);
                }
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void completeExceptionally(Throwable th) {
                C0879a.m206a("ConnectionImpl", "writeHeaders completeExceptionally() called with: throwable = [" + th + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.completeExceptionally(th);
                }
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionWriter
    public void writeData(final int i, final byte[] bArr, final boolean z, final CompletableListener<StreamWriteContext> completableListener) {
        m224a(new InterfaceC0882a<a<StreamWriteContext>, ChannelPromise>() { // from class: com.aliyun.alink.h2.connection.a.a.14
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0882a
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo217a(a<StreamWriteContext> aVar, ChannelPromise channelPromise) {
                C0879a.m208b("ConnectionImpl", "write data on connection " + C0883a.this.f530c.channel().mo3929id() + ", stream id: " + i + ", size : " + bArr.length);
                aVar.m236a(new StreamWriteContext(C0883a.this.f529b.stream(i), C0883a.this));
                C0883a.this.f532e.writeData(C0883a.this.f530c, i, Unpooled.wrappedBuffer(bArr), 0, z, channelPromise);
                C0883a.this.f530c.pipeline().flush();
            }
        }, new a<StreamWriteContext>() { // from class: com.aliyun.alink.h2.connection.a.a.15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void complete(StreamWriteContext streamWriteContext) {
                StringBuilder sb = new StringBuilder();
                sb.append("writeData complete() called with: streamId=");
                sb.append(i);
                sb.append(",dataLen=");
                byte[] bArr2 = bArr;
                sb.append(bArr2 == null ? 0 : bArr2.length);
                sb.append(",result = [");
                sb.append(streamWriteContext);
                sb.append("]");
                C0879a.m206a("ConnectionImpl", sb.toString());
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.complete(streamWriteContext);
                }
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void completeExceptionally(Throwable th) {
                StringBuilder sb = new StringBuilder();
                sb.append("writeData completeExceptionally() called with: streamId=");
                sb.append(i);
                sb.append(",dataLen=");
                byte[] bArr2 = bArr;
                sb.append(bArr2 == null ? 0 : bArr2.length);
                sb.append(",throwable = [");
                sb.append(th);
                sb.append("]");
                C0879a.m206a("ConnectionImpl", sb.toString());
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.completeExceptionally(th);
                }
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionWriter
    public void writeRst(final int i, final int i2, final CompletableListener<Connection> completableListener) {
        m224a(new InterfaceC0882a<a<Connection>, ChannelPromise>() { // from class: com.aliyun.alink.h2.connection.a.a.2
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0882a
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo217a(a<Connection> aVar, ChannelPromise channelPromise) {
                C0879a.m208b("ConnectionImpl", "write data on connection " + C0883a.this.f530c.channel().mo3929id() + ", stream id: " + i + ", error code: " + i2);
            }
        }, new a<Connection>() { // from class: com.aliyun.alink.h2.connection.a.a.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void complete(Connection connection) {
                C0879a.m206a("ConnectionImpl", "complete() called with: result = [" + connection + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.complete(connection);
                }
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void completeExceptionally(Throwable th) {
                C0879a.m206a("ConnectionImpl", "completeExceptionally() called with: throwable = [" + th + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.completeExceptionally(th);
                }
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.connection.ConnectionWriter
    public void writeGoAway(final int i, final int i2, final byte[] bArr, final CompletableListener<Connection> completableListener) {
        C0879a.m206a("ConnectionImpl", "writeGoAway() called with: lastStreamId = [" + i + "], errorCode = [" + i2 + "], debugData = [" + bArr + "], completableListener = [" + completableListener + "]");
        StringBuilder sb = new StringBuilder();
        sb.append("who called");
        sb.append(new Throwable().getStackTrace());
        C0879a.m206a("ConnectionImpl", sb.toString());
        m224a(new InterfaceC0882a<a<Connection>, ChannelPromise>() { // from class: com.aliyun.alink.h2.connection.a.a.4
            @Override // com.aliyun.alink.p022h2.connection.InterfaceC0882a
            /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public void mo217a(a<Connection> aVar, ChannelPromise channelPromise) {
                C0879a.m208b("ConnectionImpl", "write goaway on connection " + C0883a.this.f530c.channel().mo3929id() + ", stream id: " + i + ", size : " + bArr.length);
                aVar.m236a(C0883a.this);
                C0883a.this.f532e.writeGoAway(C0883a.this.f530c, i, (long) i2, Unpooled.wrappedBuffer(bArr), channelPromise);
                C0883a.this.f530c.pipeline().flush();
            }
        }, new a<Connection>() { // from class: com.aliyun.alink.h2.connection.a.a.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void complete(Connection connection) {
                C0879a.m206a("ConnectionImpl", "complete() called with: result = [" + connection + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.complete(connection);
                }
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void completeExceptionally(Throwable th) {
                C0879a.m206a("ConnectionImpl", "completeExceptionally() called with: throwable = [" + th + "]");
                CompletableListener completableListener2 = completableListener;
                if (completableListener2 != null) {
                    completableListener2.completeExceptionally(th);
                }
            }
        });
    }

    /* renamed from: a */
    private <R> void m224a(final InterfaceC0882a<a<R>, ChannelPromise> interfaceC0882a, final a<R> aVar) {
        final ChannelPromise newPromise = this.f530c.newPromise();
        newPromise.addListener((GenericFutureListener<? extends Future<? super Void>>) new GenericFutureListener<Future<? super Void>>() { // from class: com.aliyun.alink.h2.connection.a.a.6
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<? super Void> future) {
                C0879a.m206a("ConnectionImpl", "operationComplete() called with: future = [" + future + "]");
                if (future.isSuccess()) {
                    aVar.m235a();
                } else {
                    aVar.completeExceptionally(future.cause());
                }
            }
        });
        if (this.f530c.channel().eventLoop().inEventLoop()) {
            interfaceC0882a.mo217a(aVar, newPromise);
        } else {
            this.f530c.channel().eventLoop().submit(new Runnable() { // from class: com.aliyun.alink.h2.connection.a.a.7
                @Override // java.lang.Runnable
                public void run() {
                    interfaceC0882a.mo217a(aVar, newPromise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: ConnectionImpl.java */
    /* renamed from: com.aliyun.alink.h2.connection.a.a$a */
    /* loaded from: classes.dex */
    public abstract class a<T> implements CompletableListener<T> {

        /* renamed from: a */
        private T f585a;

        a() {
        }

        /* renamed from: a */
        public void m236a(T t) {
            this.f585a = t;
        }

        /* renamed from: a */
        void m235a() {
            complete(this.f585a);
        }
    }
}
