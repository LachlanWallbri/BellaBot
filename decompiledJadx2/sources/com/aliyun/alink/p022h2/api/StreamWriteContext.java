package com.aliyun.alink.p022h2.api;

import com.aliyun.alink.p022h2.connection.Connection;
import io.netty.handler.codec.http2.Http2Stream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StreamWriteContext {

    /* renamed from: a */
    private Http2Stream f524a;

    /* renamed from: b */
    private Connection f525b;

    public StreamWriteContext(Http2Stream http2Stream, Connection connection) {
        this.f524a = http2Stream;
        this.f525b = connection;
    }

    public void writeData(byte[] bArr, boolean z, CompletableListener<StreamWriteContext> completableListener) {
        this.f525b.writeData(this.f524a.mo3940id(), bArr, z, completableListener);
    }

    public void closeStream() {
        this.f524a.close();
    }

    public Http2Stream getStream() {
        return this.f524a;
    }
}
