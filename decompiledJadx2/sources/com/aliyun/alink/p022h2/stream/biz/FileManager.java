package com.aliyun.alink.p022h2.stream.biz;

import android.util.Log;
import com.aliyun.alink.p022h2.api.CompletableListener;
import com.aliyun.alink.p022h2.api.H2ClientException;
import com.aliyun.alink.p022h2.api.IotHttp2Client;
import com.aliyun.alink.p022h2.api.StreamServiceContext;
import com.aliyun.alink.p022h2.api.StreamWriteContext;
import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.entity.Http2Request;
import com.aliyun.alink.p022h2.entity.Http2Response;
import com.aliyun.alink.p022h2.stream.entity.StreamData;
import com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a;
import com.aliyun.alink.p022h2.stream.p028b.C0898a;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class FileManager {

    /* renamed from: c */
    private int f625c = 0;

    /* renamed from: d */
    private int f626d = 90000;

    /* renamed from: e */
    private AtomicBoolean f627e = new AtomicBoolean(false);

    /* renamed from: f */
    private final Object f628f = new Object();

    /* renamed from: g */
    private CompletableListener<Http2Response> f629g = null;

    /* renamed from: a */
    AbstractC0896a f623a = new AbstractC0896a() { // from class: com.aliyun.alink.h2.stream.biz.FileManager.1
        @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
        public void onStreamError(Connection connection, Http2Stream http2Stream, IOException iOException) {
            Log.d("FileManager", "onStreamError() called with: connection = [" + connection + "], http2Stream = [" + http2Stream + "], e = [" + iOException + "]");
            FileManager.this.f625c = 0;
            if (FileManager.this.f627e.compareAndSet(false, true) && FileManager.this.f629g != null) {
                C0898a.m283d("FileManager", "uploadFile onStreamError " + iOException);
                FileManager.this.f629g.completeExceptionally(iOException);
            }
            synchronized (FileManager.this.f628f) {
                FileManager.this.f628f.notify();
                C0898a.m280a("FileManager", "notify");
            }
        }

        @Override // com.aliyun.alink.p022h2.stream.p027a.AbstractC0896a
        /* renamed from: a */
        public void mo279a(Connection connection, Http2Stream http2Stream, StreamData streamData) {
            Log.d("FileManager", "onStreamDataReceived() called with: connection = [" + connection + "], stream = [" + http2Stream + "], streamData = [" + streamData + "]");
            FileManager.this.f627e.set(true);
            synchronized (FileManager.this.f628f) {
                FileManager.this.f628f.notify();
                C0898a.m280a("FileManager", "notify");
            }
            C0902b c0902b = new C0902b(new Http2Response(streamData.getHeaders(), streamData.readAllData()));
            if (HttpResponseStatus.f8500OK.equals(c0902b.getStatus())) {
                if (FileManager.this.f629g != null) {
                    FileManager.this.f629g.complete(c0902b);
                }
                C0898a.m281b("FileManager", "upload file stream success, streamId: " + c0902b.m292a());
                return;
            }
            C0898a.m283d("FileManager", "upload file stream failed, response=" + c0902b);
            if (FileManager.this.f629g != null) {
                FileManager.this.f629g.completeExceptionally(new IllegalStateException("upload file failed " + c0902b));
            }
        }
    };

    /* renamed from: b */
    CompletableListener<StreamWriteContext> f624b = new CompletableListener<StreamWriteContext>() { // from class: com.aliyun.alink.h2.stream.biz.FileManager.2
        @Override // com.aliyun.alink.p022h2.api.CompletableListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void complete(StreamWriteContext streamWriteContext) {
            C0898a.m280a("FileManager", "complete() called with: streamWriteContext = [" + streamWriteContext + "]");
            if (streamWriteContext != null && FileManager.this.f625c == 0 && streamWriteContext.getStream() != null && streamWriteContext.getStream().mo3940id() != 0) {
                FileManager.this.f625c = streamWriteContext.getStream().mo3940id();
            }
            synchronized (FileManager.this.f628f) {
                FileManager.this.f628f.notify();
                C0898a.m280a("FileManager", "notify");
            }
        }

        @Override // com.aliyun.alink.p022h2.api.CompletableListener
        public void completeExceptionally(Throwable th) {
            if (FileManager.this.f627e.compareAndSet(false, true) && FileManager.this.f629g != null) {
                C0898a.m283d("FileManager", "sendFile completeExceptionally throwable=" + th);
                FileManager.this.f629g.completeExceptionally(th);
            }
            FileManager.this.f625c = 0;
            synchronized (FileManager.this.f628f) {
                FileManager.this.f628f.notify();
                C0898a.m280a("FileManager", "notify");
            }
            C0898a.m283d("FileManager", "completeExceptionally() called with: throwable = [" + th + "]");
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    enum Type {
        ONCEHEAD,
        REPEATEDLYHEAD
    }

    /* renamed from: a */
    public void m289a(int i) {
        if (i < 90000) {
            this.f626d = 90000;
        } else {
            this.f626d = i;
        }
        C0898a.m280a("FileManager", "setFragmentSendTimeout() called with: timeout = [" + i + ", sendTimeout=" + this.f626d + "]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:83:0x01d9, code lost:
    
        r20.f627e.set(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01df, code lost:
    
        if (r26 == null) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01e1, code lost:
    
        r26.completeExceptionally(new com.aliyun.alink.p022h2.api.H2ClientException("send File timeout > " + r7 + " no response."));
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m290a(IotHttp2Client iotHttp2Client, String str, String str2, String str3, Http2Request http2Request, CompletableListener<Http2Response> completableListener, Type type, int i) {
        FileInputStream fileInputStream;
        int available;
        int i2;
        int i3;
        byte[] bArr;
        StreamServiceContext streamServiceContext;
        String str4 = str2;
        Http2Request http2Request2 = http2Request;
        if (str == null || str.isEmpty()) {
            C0898a.m283d("FileManager", "uploadFile failed path is null.");
            if (completableListener != null) {
                completableListener.completeExceptionally(new H2ClientException("file path is null."));
                return;
            }
            return;
        }
        this.f629g = completableListener;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        available = fileInputStream.available();
                    } catch (IOException e) {
                        e = e;
                        fileInputStream2 = fileInputStream;
                        e.printStackTrace();
                        C0898a.m283d("FileManager", "uploadFile failed e=" + e);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(e);
                        }
                        this.f627e.set(true);
                        this.f625c = 0;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                        e.printStackTrace();
                        C0898a.m283d("FileManager", "uploadFile failed e=" + e);
                        if (completableListener != null) {
                            completableListener.completeExceptionally(e);
                        }
                        this.f627e.set(true);
                        this.f625c = 0;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        C0898a.m283d("FileManager", "uploadFile failed e=" + th);
                        th.printStackTrace();
                        if (completableListener != null) {
                            completableListener.completeExceptionally(th);
                        }
                        this.f627e.set(true);
                        this.f625c = 0;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                } catch (Exception e4) {
                    e = e4;
                } catch (Throwable th2) {
                    th = th2;
                }
                if (available < 1) {
                    C0898a.m283d("FileManager", "uploadFile failed file data size empty.");
                    if (completableListener != null) {
                        completableListener.completeExceptionally(new H2ClientException("inputstream data size empty."));
                    }
                    try {
                        this.f627e.set(true);
                        this.f625c = 0;
                        fileInputStream.close();
                        return;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return;
                    } catch (Exception e6) {
                        e6.printStackTrace();
                        return;
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                        return;
                    }
                }
                C0898a.m280a("FileManager", "upload fileSzie = " + available);
                byte[] bArr2 = new byte[524288];
                StreamServiceContext dataStreamContext = StreamUtil.getDataStreamContext(iotHttp2Client.allConnections(), str4);
                if (dataStreamContext == null) {
                    C0898a.m283d("FileManager", "uploadFile failed StreamServiceContext is null.");
                    if (completableListener != null) {
                        completableListener.completeExceptionally(new H2ClientException("uploadFile error, StreamServiceContext=null."));
                    }
                    try {
                        this.f627e.set(true);
                        this.f625c = 0;
                        fileInputStream.close();
                        return;
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        return;
                    } catch (Exception e8) {
                        e8.printStackTrace();
                        return;
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                        return;
                    }
                }
                Connection connection = dataStreamContext.getConnection();
                String serviceName = dataStreamContext.getServiceName();
                Http2Headers headers = http2Request.getHeaders();
                headers.path(StreamUtil.PATH_STREAM_SEND + serviceName);
                headers.set((Http2Headers) StreamUtil.DATA_STREAM_ID, str4);
                if (i != 0) {
                    available -= i;
                }
                headers.set((Http2Headers) StreamUtil.CONTENT_LENGTH, String.valueOf(available));
                if (type != Type.ONCEHEAD && str3 != null && !str3.isEmpty()) {
                    headers.set((Http2Headers) StreamUtil.FILE_UPLOAD_ID, str3);
                }
                this.f627e.set(false);
                if (i != 0) {
                    fileInputStream.skip(i);
                }
                int i4 = 0;
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1 || !this.f627e.compareAndSet(false, false)) {
                        break;
                    }
                    StreamUtil.getDataStreamContext(connection, str4);
                    if (dataStreamContext != null) {
                        C0898a.m280a("FileManager", "toSend readSize=" + read);
                        i2 = i4 + read;
                        http2Request2.setEndOfStream(false);
                        if (i2 >= available) {
                            http2Request2.setEndOfStream(true);
                        } else {
                            http2Request2.setEndOfStream(false);
                        }
                        http2Request2.setContent(read < 524288 ? Arrays.copyOf(bArr2, read) : bArr2);
                        if (type == Type.ONCEHEAD && this.f625c != 0) {
                            http2Request2.setH2StreamId(this.f625c);
                        }
                        int i5 = this.f626d;
                        long currentTimeMillis = System.currentTimeMillis();
                        StringBuilder sb = new StringBuilder();
                        i3 = available;
                        sb.append("waitTime=");
                        sb.append(currentTimeMillis);
                        C0898a.m280a("FileManager", sb.toString());
                        synchronized (this.f628f) {
                            iotHttp2Client.sendRequest(connection, http2Request2, this.f623a, this.f624b);
                            long j = i5;
                            this.f628f.wait(j);
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("interval=");
                            bArr = bArr2;
                            streamServiceContext = dataStreamContext;
                            sb2.append(System.currentTimeMillis() - currentTimeMillis);
                            C0898a.m280a("FileManager", sb2.toString());
                            if ((System.currentTimeMillis() - currentTimeMillis) + 100 > j) {
                                break;
                            }
                        }
                    } else {
                        C0898a.m283d("FileManager", "uploadFile failed StreamServiceContext is null.");
                        if (completableListener != null) {
                            completableListener.completeExceptionally(new H2ClientException("uploadFile error, StreamServiceContext=null. maybe stream closed."));
                        }
                        try {
                            this.f627e.set(true);
                            this.f625c = 0;
                            fileInputStream.close();
                            return;
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            return;
                        } catch (Exception e10) {
                            e10.printStackTrace();
                            return;
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                            return;
                        }
                    }
                    str4 = str2;
                    i4 = i2;
                    bArr2 = bArr;
                    available = i3;
                    dataStreamContext = streamServiceContext;
                    http2Request2 = http2Request;
                }
                this.f627e.set(true);
                this.f625c = 0;
                fileInputStream.close();
            } catch (Throwable th6) {
                try {
                    this.f627e.set(true);
                    this.f625c = 0;
                    if (0 == 0) {
                        throw th6;
                    }
                    fileInputStream2.close();
                    throw th6;
                } catch (IOException e11) {
                    e11.printStackTrace();
                    throw th6;
                } catch (Exception e12) {
                    e12.printStackTrace();
                    throw th6;
                } catch (Throwable th7) {
                    th7.printStackTrace();
                    throw th6;
                }
            }
        } catch (IOException e13) {
            e13.printStackTrace();
        } catch (Exception e14) {
            e14.printStackTrace();
        } catch (Throwable th8) {
            th8.printStackTrace();
        }
    }
}
