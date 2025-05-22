package com.aliyun.alink.p022h2.stream.biz;

import com.aliyun.alink.p022h2.entity.Http2Response;
import com.aliyun.alink.p022h2.stream.utils.StreamUtil;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: StreamResponse.java */
/* renamed from: com.aliyun.alink.h2.stream.biz.b */
/* loaded from: classes.dex */
public class C0902b extends Http2Response {
    public C0902b(Http2Response http2Response) {
        super(http2Response.getHeaders(), http2Response.getContent());
    }

    /* renamed from: a */
    public String m292a() {
        return StreamUtil.getDataStreamId(this.headers);
    }

    public String toString() {
        return this.headers + ", content:[" + new String(this.content) + "]";
    }
}
