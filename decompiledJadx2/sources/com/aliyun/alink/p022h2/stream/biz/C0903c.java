package com.aliyun.alink.p022h2.stream.biz;

import com.aliyun.alink.p022h2.api.CompletableListener;
import com.aliyun.alink.p022h2.api.H2ClientException;
import com.aliyun.alink.p022h2.api.IotHttp2Client;
import com.aliyun.alink.p022h2.api.Profile;
import com.aliyun.alink.p022h2.api.StreamServiceContext;
import com.aliyun.alink.p022h2.api.StreamWriteContext;
import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.entity.Http2Request;
import com.aliyun.alink.p022h2.entity.Http2Response;
import com.aliyun.alink.p022h2.stream.api.CompletableDataListener;
import com.aliyun.alink.p022h2.stream.api.IDownStreamListener;
import com.aliyun.alink.p022h2.stream.api.IStreamSender;
import com.aliyun.alink.p022h2.stream.biz.FileManager;
import com.aliyun.alink.p022h2.stream.entity.StreamData;
import com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a;
import com.aliyun.alink.p022h2.stream.p028b.C0898a;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import com.aliyun.alink.p022h2.utils.ThreadPool;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: StreamSenderImpl.java */
/* renamed from: com.aliyun.alink.h2.stream.biz.c */
/* loaded from: classes.dex */
public class C0903c implements IStreamSender {

    /* renamed from: a */
    private IotHttp2Client f634a;

    /* renamed from: e */
    private Profile f638e;

    /* renamed from: d */
    private AtomicBoolean f637d = new AtomicBoolean(false);

    /* renamed from: f */
    private Connection f639f = null;

    /* renamed from: g */
    private int f640g = 90000;

    /* renamed from: b */
    private AtomicBoolean f635b = new AtomicBoolean(false);

    /* renamed from: c */
    private AtomicBoolean f636c = new AtomicBoolean(false);

    public C0903c(Profile profile) {
        this.f638e = profile;
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public void connect(final CompletableListener completableListener) {
        C0898a.m281b("StreamSenderImpl", "connect() called started=" + this.f635b.get() + ", isClosed=" + m302a());
        if (m302a() && this.f635b.compareAndSet(false, true)) {
            ThreadPool.execute(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        C0898a.m280a("StreamSenderImpl", "connect started=" + C0903c.this.f635b.get());
                        C0903c.this.f634a = new IotHttp2Client(C0903c.this.f638e, 1);
                        Connection randomConnection = C0903c.this.f634a.randomConnection();
                        if (randomConnection == null) {
                            C0903c.this.f639f = C0903c.this.f634a.newConnection();
                            C0898a.m280a("StreamSenderImpl", "connect new connection " + C0903c.this.f639f);
                        } else {
                            C0898a.m280a("StreamSenderImpl", "connect reuse connection " + randomConnection);
                            C0903c.this.f639f = randomConnection;
                        }
                        StreamUtil.setupConnection(C0903c.this.f639f, null);
                        C0903c.this.f639f.setStatus(ConnectionStatus.CREATED);
                        C0903c.this.f636c.set(true);
                        C0903c.this.f635b.set(false);
                        if (completableListener != null) {
                            completableListener.complete(true);
                        }
                    } catch (Exception e) {
                        C0903c.this.f636c.set(false);
                        C0903c.this.f635b.set(false);
                        e.printStackTrace();
                        CompletableListener completableListener2 = completableListener;
                        if (completableListener2 != null) {
                            completableListener2.completeExceptionally(e);
                        }
                    }
                }
            });
            return;
        }
        if (this.f636c.get()) {
            this.f635b.set(false);
            if (completableListener != null) {
                completableListener.complete(true);
                return;
            }
            return;
        }
        C0898a.m282c("StreamSenderImpl", "is connecting.");
        if (completableListener != null) {
            completableListener.completeExceptionally(new IllegalStateException("H2 is connecting."));
        }
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public void openStream(String str, Http2Request http2Request, final CompletableListener<Http2Response> completableListener) {
        if (m303a(str, http2Request, completableListener)) {
            if (this.f637d.compareAndSet(false, true)) {
                http2Request.getHeaders().add(this.f634a.authHeader());
                m299a(str, http2Request, this.f639f, new CompletableListener<Http2Response>() { // from class: com.aliyun.alink.h2.stream.biz.c.3
                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void complete(Http2Response http2Response) {
                        C0898a.m280a("StreamSenderImpl", "openStream complete() called with: result = [" + http2Response + "]");
                        C0903c.this.f637d.set(false);
                        CompletableListener completableListener2 = completableListener;
                        if (completableListener2 != null) {
                            completableListener2.complete(http2Response);
                        }
                    }

                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    public void completeExceptionally(Throwable th) {
                        C0898a.m280a("StreamSenderImpl", "openStream completeExceptionally() called with: throwable = [" + th + "]");
                        C0903c.this.f637d.set(false);
                        CompletableListener completableListener2 = completableListener;
                        if (completableListener2 != null) {
                            completableListener2.completeExceptionally(th);
                        }
                    }
                });
            } else if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("Pls wait until last openStream finish."));
            }
        }
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public boolean isConnected() {
        Connection connection = this.f639f;
        if (connection != null && connection.getStatus() != ConnectionStatus.CLOSED) {
            return (!this.f636c.get() || this.f634a == null || this.f639f == null) ? false : true;
        }
        this.f636c.set(false);
        return false;
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public ConnectionStatus getState() {
        Connection connection = this.f639f;
        if (connection == null) {
            return ConnectionStatus.CLOSED;
        }
        return connection.getStatus();
    }

    /* renamed from: a */
    private boolean m302a() {
        return getState() == ConnectionStatus.CLOSED;
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public void sendStream(final String str, final Http2Request http2Request, final IDownStreamListener iDownStreamListener, final CompletableListener<StreamWriteContext> completableListener) {
        IotHttp2Client iotHttp2Client;
        C0898a.m281b("StreamSenderImpl", "sendStream() called with: dataStreamId = [" + str + "], request = [" + http2Request + "], downStreamListener = [" + iDownStreamListener + "], completableListener = [" + completableListener + "]");
        if (!isConnected()) {
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("not connected."));
                return;
            }
            return;
        }
        if (http2Request == null) {
            C0898a.m283d("StreamSenderImpl", "sendStream request is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("sendStream request is null."));
                return;
            }
            return;
        }
        if (this.f639f == null || (iotHttp2Client = this.f634a) == null) {
            C0898a.m283d("StreamSenderImpl", "sendStream connection is not created.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("sendStream failed. connection not created."));
                return;
            }
            return;
        }
        final StreamServiceContext dataStreamContext = StreamUtil.getDataStreamContext(iotHttp2Client.allConnections(), str);
        if (dataStreamContext == null) {
            C0898a.m283d("StreamSenderImpl", "sendStream StreamServiceContext=null");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("send stream StreamServiceContext is null."));
                return;
            }
            return;
        }
        ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.4
            @Override // java.lang.Runnable
            public void run() {
                Connection connection = dataStreamContext.getConnection();
                String serviceName = dataStreamContext.getServiceName();
                Http2Headers headers = http2Request.getHeaders();
                headers.path(StreamUtil.PATH_STREAM_SEND + serviceName);
                headers.set((Http2Headers) StreamUtil.DATA_STREAM_ID, str);
                C0903c.this.f634a.sendRequest(connection, http2Request, new C0901a(iDownStreamListener), completableListener);
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public void closeStream(final String str, final Http2Request http2Request, final CompletableListener<Http2Response> completableListener) {
        IotHttp2Client iotHttp2Client;
        C0898a.m281b("StreamSenderImpl", "closeStream() called with: dataStreamId = [" + str + "], request = [" + http2Request + "], listener = [" + completableListener + "]");
        if (!isConnected()) {
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("not connected."));
                return;
            }
            return;
        }
        if (http2Request == null) {
            C0898a.m283d("StreamSenderImpl", "closeStream request is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("closeStream request is null."));
                return;
            }
            return;
        }
        if (this.f639f == null || (iotHttp2Client = this.f634a) == null) {
            C0898a.m283d("StreamSenderImpl", "closeStream connection is not created.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("closeStream failed. connection not created."));
                return;
            }
            return;
        }
        final StreamServiceContext dataStreamContext = StreamUtil.getDataStreamContext(iotHttp2Client.allConnections(), str);
        if (dataStreamContext == null) {
            C0898a.m283d("StreamSenderImpl", "closeStream StreamServiceContext=null");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("close stream StreamServiceContext is null."));
                return;
            }
            return;
        }
        ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.5
            @Override // java.lang.Runnable
            public void run() {
                Connection connection = dataStreamContext.getConnection();
                String serviceName = dataStreamContext.getServiceName();
                final Http2Headers headers = http2Request.getHeaders();
                headers.path(StreamUtil.PATH_STREAM_CLOSE + serviceName);
                headers.set((Http2Headers) StreamUtil.DATA_STREAM_ID, str);
                StreamUtil.removeDataStreamContext(connection, str);
                C0903c.this.f634a.sendRequest(connection, http2Request, new AbstractC0896a() { // from class: com.aliyun.alink.h2.stream.biz.c.5.1
                    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
                    public void onStreamError(Connection connection2, Http2Stream http2Stream, IOException iOException) {
                        if (completableListener != null) {
                            completableListener.completeExceptionally(iOException);
                        }
                    }

                    @Override // com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a
                    /* renamed from: a */
                    public void mo279a(Connection connection2, Http2Stream http2Stream, StreamData streamData) {
                        C0902b c0902b = new C0902b(new Http2Response(streamData.getHeaders(), streamData.readAllData()));
                        if (HttpResponseStatus.f8500OK.equals(c0902b.getStatus())) {
                            c0902b.getHeaders().path(headers.path());
                            if (completableListener != null) {
                                completableListener.complete(c0902b);
                            }
                            C0898a.m281b("StreamSenderImpl", "close stream success, streamId: " + c0902b.m292a());
                            return;
                        }
                        C0898a.m283d("StreamSenderImpl", "onStreamDataReceived closeStream failed " + c0902b);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(new H2ClientException("close stream failed " + c0902b));
                        }
                    }
                }, new CompletableListener<StreamWriteContext>() { // from class: com.aliyun.alink.h2.stream.biz.c.5.2
                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void complete(StreamWriteContext streamWriteContext) {
                    }

                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    public void completeExceptionally(Throwable th) {
                        C0898a.m283d("StreamSenderImpl", "completeExceptionally closeStream failed " + th);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(new H2ClientException("close stream failed " + th));
                        }
                    }
                });
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IStreamSender
    public void disconnect(final CompletableListener completableListener) {
        C0898a.m281b("StreamSenderImpl", "disconnect() called");
        this.f635b.set(false);
        this.f636c.set(false);
        this.f637d.set(false);
        ThreadPool.execute(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (C0903c.this.f634a != null) {
                        C0903c.this.f634a.shutdown();
                        C0903c.this.f634a = null;
                    }
                    if (completableListener != null) {
                        completableListener.complete(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CompletableListener completableListener2 = completableListener;
                    if (completableListener2 != null) {
                        completableListener2.completeExceptionally(e);
                    }
                } catch (Throwable th) {
                    CompletableListener completableListener3 = completableListener;
                    if (completableListener3 != null) {
                        completableListener3.completeExceptionally(th);
                    }
                }
            }
        });
    }

    /* renamed from: a */
    private void m299a(final String str, final Http2Request http2Request, final Connection connection, final CompletableListener<Http2Response> completableListener) {
        if (isConnected()) {
            ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.7
                @Override // java.lang.Runnable
                public void run() {
                    final Http2Headers headers = http2Request.getHeaders();
                    headers.path(StreamUtil.PATH_STREAM_OPEN + str);
                    C0903c.this.f634a.sendRequest(connection, http2Request, new AbstractC0896a() { // from class: com.aliyun.alink.h2.stream.biz.c.7.1
                        @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
                        public void onStreamError(Connection connection2, Http2Stream http2Stream, IOException iOException) {
                            if (completableListener != null) {
                                completableListener.completeExceptionally(iOException);
                            }
                        }

                        @Override // com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a
                        /* renamed from: a */
                        public void mo279a(Connection connection2, Http2Stream http2Stream, StreamData streamData) {
                            C0902b c0902b = new C0902b(new Http2Response(streamData.getHeaders(), streamData.readAllData()));
                            if (HttpResponseStatus.f8500OK.equals(c0902b.getStatus())) {
                                connection2.setStatus(ConnectionStatus.AUTHORIZED);
                                c0902b.getHeaders().path(headers.path());
                                StreamUtil.putDataStreamContext(connection2, c0902b.m292a(), new StreamServiceContext(connection2, c0902b, str));
                                if (completableListener != null) {
                                    completableListener.complete(c0902b);
                                }
                                C0898a.m281b("StreamSenderImpl", "open stream success, streamId: " + c0902b.m292a());
                                return;
                            }
                            C0898a.m283d("StreamSenderImpl", "onStreamDataReceived openStream failed " + c0902b);
                            if (completableListener != null) {
                                completableListener.completeExceptionally(new H2ClientException("open stream failed " + c0902b));
                            }
                        }
                    }, new CompletableListener<StreamWriteContext>() { // from class: com.aliyun.alink.h2.stream.biz.c.7.2
                        @Override // com.aliyun.alink.p022h2.api.CompletableListener
                        /* renamed from: a, reason: merged with bridge method [inline-methods] */
                        public void complete(StreamWriteContext streamWriteContext) {
                        }

                        @Override // com.aliyun.alink.p022h2.api.CompletableListener
                        public void completeExceptionally(Throwable th) {
                            C0898a.m283d("StreamSenderImpl", "completeExceptionally openStream failed " + th);
                            if (completableListener != null) {
                                completableListener.completeExceptionally(new H2ClientException("open stream failed " + th));
                            }
                        }
                    });
                }
            });
        } else if (completableListener != null) {
            completableListener.completeExceptionally(new H2ClientException("not connected."));
        }
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IH2FileManager
    public void upload(final String str, final String str2, final Http2Request http2Request, final CompletableListener<Http2Response> completableListener) {
        if (!isConnected()) {
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("not connected."));
                return;
            }
            return;
        }
        if (str2 == null || str2.isEmpty()) {
            C0898a.m283d("StreamSenderImpl", "upload file failed path is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("file path is null."));
                return;
            }
            return;
        }
        if (http2Request == null) {
            C0898a.m282c("StreamSenderImpl", "upload file request is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("request is null."));
                return;
            }
            return;
        }
        try {
            ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.8
                @Override // java.lang.Runnable
                public void run() {
                    FileManager fileManager = new FileManager();
                    fileManager.m289a(C0903c.this.f640g);
                    fileManager.m290a(C0903c.this.f634a, str2, str, "", http2Request, completableListener, FileManager.Type.ONCEHEAD, 0);
                }
            });
        } catch (Exception e) {
            C0898a.m283d("StreamSenderImpl", "upload failed exception=" + e);
            e.printStackTrace();
            if (completableListener != null) {
                completableListener.completeExceptionally(e);
            }
        }
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IH2FileManager
    public void uploadFile(String str, final Http2Request http2Request, final String str2, final CompletableDataListener<Http2Response> completableDataListener) {
        if (m304a(str, http2Request, str2, completableDataListener)) {
            http2Request.getHeaders().add(this.f634a.authHeader());
            m299a(str, http2Request, this.f639f, new CompletableListener<Http2Response>() { // from class: com.aliyun.alink.h2.stream.biz.c.9
                @Override // com.aliyun.alink.p022h2.api.CompletableListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void complete(Http2Response http2Response) {
                    C0898a.m280a("StreamSenderImpl", "openStream complete() called with: result = [" + http2Response + "]");
                    String dataStreamId = StreamUtil.getDataStreamId(http2Response.getHeaders());
                    String fileUploadId = StreamUtil.getFileUploadId(http2Request.getHeaders());
                    int m307c = (fileUploadId == null || fileUploadId.isEmpty()) ? 0 : C0903c.this.m307c(StreamUtil.getNextAppendPosition(http2Response.getHeaders()));
                    String fileUploadId2 = StreamUtil.getFileUploadId(http2Response.getHeaders());
                    http2Request.getHeaders().clear();
                    CompletableDataListener completableDataListener2 = completableDataListener;
                    if (completableDataListener2 != null) {
                        completableDataListener2.callBack(fileUploadId2);
                    }
                    C0903c.this.m301a(str2, dataStreamId, fileUploadId2, m307c, http2Request, completableDataListener);
                }

                @Override // com.aliyun.alink.p022h2.api.CompletableListener
                public void completeExceptionally(Throwable th) {
                    C0898a.m283d("StreamSenderImpl", "openStream completeExceptionally() called with: throwable = [" + th + "]");
                    CompletableDataListener completableDataListener2 = completableDataListener;
                    if (completableDataListener2 != null) {
                        completableDataListener2.completeExceptionally(th);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m301a(final String str, final String str2, final String str3, final int i, final Http2Request http2Request, final CompletableListener<Http2Response> completableListener) {
        if (http2Request == null) {
            C0898a.m282c("StreamSenderImpl", "uploadFile file request is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("request is null."));
                return;
            }
            return;
        }
        try {
            ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.10
                @Override // java.lang.Runnable
                public void run() {
                    FileManager fileManager = new FileManager();
                    fileManager.m289a(C0903c.this.f640g);
                    fileManager.m290a(C0903c.this.f634a, str, str2, str3, http2Request, new CompletableListener<Http2Response>() { // from class: com.aliyun.alink.h2.stream.biz.c.10.1
                        @Override // com.aliyun.alink.p022h2.api.CompletableListener
                        /* renamed from: a, reason: merged with bridge method [inline-methods] */
                        public void complete(Http2Response http2Response) {
                            if (http2Request.isEndOfStream()) {
                                http2Request.getHeaders().clear();
                                C0903c.this.m300a(str2, str3, http2Request, (CompletableListener<Http2Response>) completableListener);
                            }
                        }

                        @Override // com.aliyun.alink.p022h2.api.CompletableListener
                        public void completeExceptionally(Throwable th) {
                            C0898a.m283d("StreamSenderImpl", "uploadFile failed exception=" + th);
                            if (completableListener != null) {
                                completableListener.completeExceptionally(th);
                            }
                            http2Request.getHeaders().clear();
                            http2Request.setEndOfStream(true);
                            C0903c.this.m300a(str2, str3, http2Request, (CompletableListener<Http2Response>) null);
                        }
                    }, FileManager.Type.REPEATEDLYHEAD, i);
                }
            });
        } catch (Exception e) {
            C0898a.m283d("StreamSenderImpl", "upload failed exception=" + e);
            e.printStackTrace();
            if (completableListener != null) {
                completableListener.completeExceptionally(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m300a(final String str, final String str2, final Http2Request http2Request, final CompletableListener<Http2Response> completableListener) {
        C0898a.m281b("StreamSenderImpl", "closeStream() called with: dataStreamId = [" + str + "], request = [" + http2Request + "], listener = [" + completableListener + "]");
        if (http2Request == null) {
            C0898a.m283d("StreamSenderImpl", "closeStream request is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("closeStream request is null."));
                return;
            }
            return;
        }
        final StreamServiceContext dataStreamContext = StreamUtil.getDataStreamContext(this.f634a.allConnections(), str);
        if (dataStreamContext == null) {
            C0898a.m283d("StreamSenderImpl", "closeStream StreamServiceContext=null");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("close stream StreamServiceContext is null."));
                return;
            }
            return;
        }
        ThreadPool.submit(new Runnable() { // from class: com.aliyun.alink.h2.stream.biz.c.2
            @Override // java.lang.Runnable
            public void run() {
                Connection connection = dataStreamContext.getConnection();
                String serviceName = dataStreamContext.getServiceName();
                final Http2Headers headers = http2Request.getHeaders();
                headers.path(StreamUtil.PATH_STREAM_CLOSE + serviceName);
                headers.set((Http2Headers) StreamUtil.DATA_STREAM_ID, str);
                headers.set((Http2Headers) StreamUtil.FILE_UPLOAD_ID, str2);
                StreamUtil.removeDataStreamContext(connection, str);
                C0903c.this.f634a.sendRequest(connection, http2Request, new AbstractC0896a() { // from class: com.aliyun.alink.h2.stream.biz.c.2.1
                    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
                    public void onStreamError(Connection connection2, Http2Stream http2Stream, IOException iOException) {
                        if (completableListener != null) {
                            completableListener.completeExceptionally(iOException);
                        }
                    }

                    @Override // com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a
                    /* renamed from: a */
                    public void mo279a(Connection connection2, Http2Stream http2Stream, StreamData streamData) {
                        C0902b c0902b = new C0902b(new Http2Response(streamData.getHeaders(), streamData.readAllData()));
                        if (HttpResponseStatus.f8500OK.equals(c0902b.getStatus())) {
                            c0902b.getHeaders().path(headers.path());
                            if (completableListener != null) {
                                completableListener.complete(c0902b);
                            }
                            C0898a.m281b("StreamSenderImpl", "close stream success, streamId: " + c0902b.m292a());
                            return;
                        }
                        C0898a.m283d("StreamSenderImpl", "onStreamDataReceived closeStream failed " + c0902b);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(new H2ClientException("close stream failed " + c0902b));
                        }
                    }
                }, new CompletableListener<StreamWriteContext>() { // from class: com.aliyun.alink.h2.stream.biz.c.2.2
                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void complete(StreamWriteContext streamWriteContext) {
                    }

                    @Override // com.aliyun.alink.p022h2.api.CompletableListener
                    public void completeExceptionally(Throwable th) {
                        C0898a.m283d("StreamSenderImpl", "completeExceptionally closeStream failed " + th);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(new H2ClientException("close stream failed " + th));
                        }
                    }
                });
            }
        });
    }

    @Override // com.aliyun.alink.p022h2.stream.api.IH2FileManager
    public void setDataFragmentSendTimeout(int i) {
        if (i < 90000) {
            this.f640g = 90000;
        } else {
            this.f640g = i;
        }
        C0898a.m280a("StreamSenderImpl", "setFragmentSendTimeout() called with: timeout = [" + i + ", sendTimeout=" + this.f640g + "]");
    }

    /* renamed from: a */
    private boolean m303a(String str, Http2Request http2Request, CompletableListener<Http2Response> completableListener) {
        C0898a.m281b("StreamSenderImpl", "openStreamThenUploadFile() called with: serviceName = [" + str + "], request = [" + http2Request + "], listener = [" + completableListener + "]");
        try {
            StreamUtil.checkServiceName(str);
            if (!isConnected()) {
                if (completableListener != null) {
                    completableListener.completeExceptionally(new H2ClientException("not connected."));
                }
                return false;
            }
            if (http2Request == null) {
                C0898a.m283d("StreamSenderImpl", "openStream streamRequest is null.");
                if (completableListener != null) {
                    completableListener.completeExceptionally(new H2ClientException("openStream request is null."));
                }
                return false;
            }
            if (this.f639f != null && this.f634a != null) {
                return true;
            }
            C0898a.m283d("StreamSenderImpl", "openStream connection is not created.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("openStream failed. connection not created."));
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            if (completableListener != null) {
                completableListener.completeExceptionally(e);
            }
            return false;
        }
    }

    /* renamed from: a */
    private boolean m304a(String str, Http2Request http2Request, String str2, CompletableListener<Http2Response> completableListener) {
        C0898a.m281b("StreamSenderImpl", "checkOpenStreamAndUploadCondition() called with: serviceName = [" + str + "], request = [" + http2Request + "], listener = [" + completableListener + "]");
        if (!m303a(str, http2Request, completableListener)) {
            return false;
        }
        if (str2 == null || str2.isEmpty()) {
            C0898a.m283d("StreamSenderImpl", "upload file failed path is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("file path is null."));
            }
            return false;
        }
        if (!m313a(str2)) {
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("fileUpdate failed file path invalid."));
            }
            return false;
        }
        if (m306b(str2)) {
            return true;
        }
        if (completableListener != null) {
            completableListener.completeExceptionally(new H2ClientException("uploadFile failed file data size empty."));
        }
        return false;
    }

    /* renamed from: b */
    private boolean m306b(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return new File(str).length() >= 1;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m313a(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return new File(str).exists();
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public int m307c(String str) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    return Integer.parseInt(str);
                }
            } catch (Throwable unused) {
            }
        }
        return 0;
    }
}
