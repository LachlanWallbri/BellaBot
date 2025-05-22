package com.aliyun.alink.p022h2.api;

import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.connection.InterfaceC0885b;
import com.aliyun.alink.p022h2.connection.InterfaceC0886c;
import com.aliyun.alink.p022h2.connection.p026a.C0884b;
import com.aliyun.alink.p022h2.entity.Http2Request;
import com.aliyun.alink.p022h2.netty.C0894g;
import com.aliyun.alink.p022h2.p023a.C0876b;
import com.aliyun.alink.p022h2.p023a.InterfaceC0872a;
import com.aliyun.alink.p022h2.p025b.C0879a;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Settings;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IotHttp2Client {
    public static final int CONNECTION_COUNT_UNLIMITED = -1;

    /* renamed from: b */
    private static long[] f488b = {0, 1000, 10000, 60000, 600000};

    /* renamed from: a */
    private final SocketAddress f489a;

    /* renamed from: e */
    private int f492e;

    /* renamed from: f */
    private ScheduledExecutorService f493f;

    /* renamed from: h */
    private InterfaceC0886c f495h;

    /* renamed from: i */
    private InterfaceC0872a f496i;

    /* renamed from: c */
    private int f490c = 0;

    /* renamed from: d */
    private AtomicInteger f491d = new AtomicInteger(1);

    /* renamed from: g */
    private AtomicBoolean f494g = new AtomicBoolean(false);

    /* renamed from: j */
    private InterfaceC0885b f497j = new InterfaceC0885b() { // from class: com.aliyun.alink.h2.api.IotHttp2Client.1
        @Override // com.aliyun.alink.p022h2.connection.InterfaceC0885b
        public void onSettingReceive(Connection connection, Http2Settings http2Settings) {
            if (http2Settings.containsKey('c')) {
                int intValue = http2Settings.getIntValue('c').intValue();
                if (IotHttp2Client.this.f492e != -1 && IotHttp2Client.this.f492e >= 0) {
                    intValue = Math.min(IotHttp2Client.this.f492e, intValue);
                    C0879a.m208b("IotHttp2Client", "maxConnectionCount: " + IotHttp2Client.this.f492e + ", server setting: " + intValue);
                }
                IotHttp2Client.this.f491d.set(intValue);
                C0879a.m208b("IotHttp2Client", "receive setting, connection: " + connection + ", subscription count : " + IotHttp2Client.this.f491d);
            }
        }

        @Override // com.aliyun.alink.p022h2.connection.InterfaceC0885b
        public void onStatusChange(ConnectionStatus connectionStatus, Connection connection) {
            C0879a.m208b("IotHttp2Client", "connection status changed, connection: " + connection + ", status: " + connectionStatus);
            if (connectionStatus == ConnectionStatus.AUTHORIZED && IotHttp2Client.this.f494g.compareAndSet(false, true) && IotHttp2Client.this.f493f != null) {
                IotHttp2Client.this.f493f.scheduleWithFixedDelay(new Runnable() { // from class: com.aliyun.alink.h2.api.IotHttp2Client.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IotHttp2Client.this.m198a();
                    }
                }, 10000L, 10000L, TimeUnit.MILLISECONDS);
            }
            if (connection.getStatus() == ConnectionStatus.AUTHORIZED && connectionStatus == ConnectionStatus.CLOSED) {
                IotHttp2Client.this.f493f.submit(new Runnable() { // from class: com.aliyun.alink.h2.api.IotHttp2Client.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        IotHttp2Client.this.m198a();
                    }
                });
            }
        }
    };

    public IotHttp2Client(Profile profile, int i) {
        this.f492e = -1;
        this.f489a = new InetSocketAddress(profile.getHost(), profile.getPort());
        C0884b c0884b = new C0884b(true, profile.getHeartBeatInterval(), profile.getHeartBeatTimeOut());
        this.f495h = c0884b;
        c0884b.mo245a(this.f497j);
        this.f492e = i;
        this.f493f = new ScheduledThreadPoolExecutor(1, new C0894g().m276a(true).m275a("iot-client-thread-%d").m277a());
        this.f496i = C0876b.m196a(profile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m198a() {
        long size = allConnections().size();
        int i = this.f491d.get();
        int i2 = this.f492e;
        if (i2 >= 0) {
            i = Math.min(i2, i);
        }
        long j = i;
        if (size >= j) {
            return;
        }
        C0879a.m208b("IotHttp2Client", "update connection count, current count " + size + ", expected count " + i);
        int i3 = this.f490c;
        if (i3 != 0) {
            long[] jArr = f488b;
            long j2 = jArr[i3 % jArr.length];
            try {
                C0879a.m208b("IotHttp2Client", "backoff, create connection after " + j2 + "ms");
                Thread.sleep(j2);
            } catch (InterruptedException e) {
                C0879a.m207a("IotHttp2Client", "error occurs while backoff, exception: ", e);
            }
        }
        for (int i4 = 0; i4 < j - size; i4++) {
            try {
                newConnection();
            } catch (Throwable th) {
                this.f490c++;
                C0879a.m210d("failed to create connection, {}", th.getMessage());
            }
        }
        long size2 = allConnections().size();
        if (size2 == j) {
            this.f490c = 0;
        }
        C0879a.m208b("IotHttp2Client", "finish updating connection count, current count " + size2);
    }

    public Connection newConnection() {
        return this.f495h.mo243a(this.f489a);
    }

    public Http2Headers authHeader() {
        DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers();
        Map<String, String> mo193a = this.f496i.mo193a();
        if (mo193a != null) {
            for (Map.Entry<String, String> entry : mo193a.entrySet()) {
                defaultHttp2Headers.set((DefaultHttp2Headers) ("x-auth-" + entry.getKey()), entry.getValue());
            }
        }
        return defaultHttp2Headers;
    }

    public void shutdown() {
        C0879a.m208b("IotHttp2Client", "shutdown http2 client");
        try {
            List<Connection> allConnections = allConnections();
            if (allConnections != null) {
                for (int i = 0; i < allConnections.size(); i++) {
                    Connection connection = allConnections.get(i);
                    if (connection != null) {
                        connection.removeConnectListener();
                    }
                }
            }
            this.f495h.mo247b(this.f497j);
            this.f495h.mo246b();
            if (this.f493f != null) {
                this.f493f.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addConnectionListener(InterfaceC0885b interfaceC0885b) {
        this.f495h.mo245a(interfaceC0885b);
    }

    public void removeConnectionListener(InterfaceC0885b interfaceC0885b) {
        this.f495h.mo247b(interfaceC0885b);
    }

    public List<Connection> allConnections() {
        return this.f495h.mo244a();
    }

    public void sendRequest(Connection connection, Http2Request http2Request, Http2StreamListener http2StreamListener, final CompletableListener<StreamWriteContext> completableListener) {
        if (http2Request == null) {
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("param request is invalid."));
                return;
            }
            return;
        }
        final boolean isEndOfStream = http2Request.isEndOfStream();
        final byte[] content = http2Request.getContent();
        Http2Headers headers = http2Request.getHeaders();
        int length = content != null ? content.length : 0;
        if (!headers.contains(StreamUtil.CONTENT_LENGTH)) {
            headers.set((Http2Headers) StreamUtil.CONTENT_LENGTH, String.valueOf(length));
        }
        if (http2Request.getH2StreamId() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("sendRequest send data=");
            sb.append(content == null ? "null" : Integer.valueOf(content.length));
            C0879a.m208b("IotHttp2Client", sb.toString());
            if (content != null && content.length > 0) {
                connection.writeData(http2Request.getH2StreamId(), content, isEndOfStream, completableListener);
                return;
            } else {
                if (completableListener != null) {
                    completableListener.completeExceptionally(new H2ClientException("send stream with stream id, data is null."));
                    return;
                }
                return;
            }
        }
        boolean z = (content == null || content.length < 1) && isEndOfStream;
        final boolean z2 = z;
        connection.writeHeaders(headers, z, http2StreamListener, new CompletableListener<StreamWriteContext>() { // from class: com.aliyun.alink.h2.api.IotHttp2Client.2
            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void complete(StreamWriteContext streamWriteContext) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("sendAsync complete() called with: result = [");
                sb2.append(streamWriteContext);
                sb2.append("]");
                sb2.append("state=");
                sb2.append((streamWriteContext == null || streamWriteContext.getStream() == null) ? "null" : streamWriteContext.getStream().state());
                C0879a.m206a("IotHttp2Client", sb2.toString());
                try {
                    if (content != null && content.length > 0) {
                        IotHttp2Client.this.m200a(streamWriteContext, content, isEndOfStream, completableListener);
                    } else if (completableListener != null) {
                        completableListener.complete(streamWriteContext);
                    }
                } catch (Exception e) {
                    CompletableListener completableListener2 = completableListener;
                    if (completableListener2 != null) {
                        completableListener2.completeExceptionally(e);
                    }
                }
            }

            @Override // com.aliyun.alink.p022h2.api.CompletableListener
            public void completeExceptionally(Throwable th) {
                CompletableListener completableListener2;
                C0879a.m206a("IotHttp2Client", "sendAsync completeExceptionally() called with: throwable = [" + th + "]");
                if (!z2 || (completableListener2 = completableListener) == null) {
                    return;
                }
                completableListener2.completeExceptionally(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m200a(StreamWriteContext streamWriteContext, byte[] bArr, boolean z, CompletableListener<StreamWriteContext> completableListener) {
        C0879a.m206a("IotHttp2Client", "writeData() called with: streamWriteContext = [" + streamWriteContext + "], data = [" + bArr + "], endStream = [" + z + "], completableListener = [" + completableListener + "]");
        if (streamWriteContext != null) {
            streamWriteContext.writeData(bArr, z, completableListener);
        } else if (completableListener != null) {
            completableListener.completeExceptionally(new H2ClientException("writeData streamWriteContext is null."));
        }
    }

    public Connection randomConnection() {
        List<Connection> allConnections = allConnections();
        ArrayList arrayList = new ArrayList();
        if (allConnections == null || allConnections.isEmpty()) {
            return null;
        }
        for (int i = 0; i < allConnections.size(); i++) {
            Connection connection = allConnections.get(i);
            if (connection != null && connection.isAuthorized()) {
                arrayList.add(connection);
            }
        }
        return (Connection) arrayList.get(new Random().nextInt(arrayList.size()));
    }
}
