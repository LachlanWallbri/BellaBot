package com.aliyun.alink.dm.api;

import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IShadowRRPC {
    void onReceived(ARequest aRequest, AResponse aResponse, IConnectRrpcHandle iConnectRrpcHandle);

    void onResponseFailed(ARequest aRequest, AError aError);

    void onResponseSuccess(ARequest aRequest);

    void onSubscribeFailed(ARequest aRequest, AError aError);

    void onSubscribeSuccess(ARequest aRequest);
}
