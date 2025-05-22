package com.aliyun.alink.p022h2.stream.api;

import com.aliyun.alink.p022h2.api.CompletableListener;
import com.aliyun.alink.p022h2.entity.Http2Request;
import com.aliyun.alink.p022h2.entity.Http2Response;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IH2FileManager {
    void setDataFragmentSendTimeout(int i);

    @Deprecated
    void upload(String str, String str2, Http2Request http2Request, CompletableListener<Http2Response> completableListener);

    void uploadFile(String str, Http2Request http2Request, String str2, CompletableDataListener<Http2Response> completableDataListener);
}
