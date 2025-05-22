package com.aliyun.alink.p022h2.entity;

import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BaseHttpEntity {
    public static final String REQUEST_ID = "x-request-id";

    /* renamed from: a */
    private int f597a;
    protected byte[] content;
    protected Http2Headers headers;

    public BaseHttpEntity() {
        this.f597a = 0;
        this.headers = new DefaultHttp2Headers();
    }

    public BaseHttpEntity(Http2Headers http2Headers, byte[] bArr) {
        this.f597a = 0;
        this.headers = http2Headers;
        this.content = bArr;
    }

    public Http2Headers getHeaders() {
        return this.headers;
    }

    public void setHeaders(Http2Headers http2Headers) {
        this.headers = http2Headers;
    }

    public String getRequestId() {
        return this.headers.get(REQUEST_ID).toString();
    }

    public void setRequestId(String str) {
        this.headers.set((Http2Headers) REQUEST_ID, str);
    }

    public void setContent(byte[] bArr) {
        this.content = bArr;
    }

    public byte[] getContent() {
        return this.content;
    }

    public int getH2StreamId() {
        return this.f597a;
    }

    public void setH2StreamId(int i) {
        this.f597a = i;
    }
}
