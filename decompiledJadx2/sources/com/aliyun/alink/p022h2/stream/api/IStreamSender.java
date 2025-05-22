package com.aliyun.alink.p022h2.stream.api;

import com.aliyun.alink.p022h2.api.CompletableListener;
import com.aliyun.alink.p022h2.api.StreamWriteContext;
import com.aliyun.alink.p022h2.connection.ConnectionStatus;
import com.aliyun.alink.p022h2.entity.Http2Request;
import com.aliyun.alink.p022h2.entity.Http2Response;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IStreamSender extends IH2FileManager {
    void closeStream(String str, Http2Request http2Request, CompletableListener<Http2Response> completableListener);

    void connect(CompletableListener completableListener);

    void disconnect(CompletableListener completableListener);

    ConnectionStatus getState();

    boolean isConnected();

    void openStream(String str, Http2Request http2Request, CompletableListener<Http2Response> completableListener);

    void sendStream(String str, Http2Request http2Request, IDownStreamListener iDownStreamListener, CompletableListener<StreamWriteContext> completableListener);
}
