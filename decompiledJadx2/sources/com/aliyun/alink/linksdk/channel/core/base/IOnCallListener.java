package com.aliyun.alink.linksdk.channel.core.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IOnCallListener {
    boolean needUISafety();

    void onFailed(ARequest aRequest, AError aError);

    void onSuccess(ARequest aRequest, AResponse aResponse);
}
