package com.aliyun.alink.p022h2.stream.biz;

import com.aliyun.alink.p022h2.api.Http2StreamListener;
import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.stream.api.IDownStreamListener;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DownStreamListenerAdapter.java */
/* renamed from: com.aliyun.alink.h2.stream.biz.a */
/* loaded from: classes.dex */
public class C0901a implements Http2StreamListener {

    /* renamed from: a */
    IDownStreamListener f632a;

    /* renamed from: b */
    String f633b;

    /* JADX INFO: Access modifiers changed from: protected */
    public C0901a(IDownStreamListener iDownStreamListener) {
        this.f632a = iDownStreamListener;
    }

    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
    public void onDataRead(Connection connection, Http2Stream http2Stream, byte[] bArr, boolean z) {
        IDownStreamListener iDownStreamListener = this.f632a;
        if (iDownStreamListener != null) {
            iDownStreamListener.onDataRead(this.f633b, bArr, z);
        }
    }

    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
    public void onHeadersRead(Connection connection, Http2Stream http2Stream, Http2Headers http2Headers, boolean z) {
        String dataStreamId = StreamUtil.getDataStreamId(http2Headers);
        this.f633b = dataStreamId;
        IDownStreamListener iDownStreamListener = this.f632a;
        if (iDownStreamListener != null) {
            iDownStreamListener.onHeadersRead(dataStreamId, http2Headers, z);
        }
    }

    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
    public void onStreamError(Connection connection, Http2Stream http2Stream, IOException iOException) {
        IDownStreamListener iDownStreamListener = this.f632a;
        if (iDownStreamListener != null) {
            iDownStreamListener.onStreamError(this.f633b, iOException);
        }
    }
}
