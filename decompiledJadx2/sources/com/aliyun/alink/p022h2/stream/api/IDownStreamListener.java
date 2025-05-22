package com.aliyun.alink.p022h2.stream.api;

import io.netty.handler.codec.http2.Http2Headers;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDownStreamListener {
    void onDataRead(String str, byte[] bArr, boolean z);

    void onHeadersRead(String str, Http2Headers http2Headers, boolean z);

    void onStreamError(String str, IOException iOException);
}
