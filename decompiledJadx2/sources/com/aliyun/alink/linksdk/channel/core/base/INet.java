package com.aliyun.alink.linksdk.channel.core.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface INet {
    ASend asyncSend(ARequest aRequest, IOnCallListener iOnCallListener);

    void retry(ASend aSend);
}
