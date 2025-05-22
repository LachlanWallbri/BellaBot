package com.aliyun.alink.p022h2.stream.p027a;

import com.aliyun.alink.p022h2.api.Http2StreamListener;
import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.stream.entity.StreamData;
import com.aliyun.alink.p022h2.stream.p028b.C0898a;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2Stream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: AbstractHttp2ResponseListener.java */
/* renamed from: com.aliyun.alink.h2.stream.a.a */
/* loaded from: classes.dex */
public abstract class AbstractC0896a implements Http2StreamListener {
    /* renamed from: a */
    public abstract void mo279a(Connection connection, Http2Stream http2Stream, StreamData streamData);

    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
    public void onDataRead(Connection connection, Http2Stream http2Stream, byte[] bArr, boolean z) {
        C0898a.m280a("AbstractHttp2StreamData", "receive data on connection " + connection + ", streamId " + http2Stream.mo3940id());
        StreamData m278a = m278a(connection, http2Stream);
        if (m278a == null) {
            onStreamError(connection, http2Stream, new IOException(connection.toString() + " received data frame on " + http2Stream.mo3940id() + ", but headers hasn't received"));
            return;
        }
        m278a.addData(bArr);
        if (z) {
            mo279a(connection, http2Stream, m278a);
            http2Stream.closeLocalSide();
        }
    }

    @Override // com.aliyun.alink.p022h2.api.Http2StreamListener
    public void onHeadersRead(Connection connection, Http2Stream http2Stream, Http2Headers http2Headers, boolean z) {
        Http2Connection.PropertyKey propertyKey = connection.getPropertyKey("data_cache_key");
        StreamData m278a = m278a(connection, http2Stream);
        if (m278a == null) {
            m278a = new StreamData(http2Stream.mo3940id());
            http2Stream.setProperty(propertyKey, m278a);
        }
        m278a.addHeaders(http2Headers);
        if (z) {
            mo279a(connection, http2Stream, m278a);
            http2Stream.closeLocalSide();
        }
    }

    /* renamed from: a */
    protected StreamData m278a(Connection connection, Http2Stream http2Stream) {
        return (StreamData) http2Stream.getProperty(connection.getPropertyKey("data_cache_key"));
    }
}
