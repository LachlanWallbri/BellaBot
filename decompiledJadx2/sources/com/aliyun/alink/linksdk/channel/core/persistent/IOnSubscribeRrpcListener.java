package com.aliyun.alink.linksdk.channel.core.persistent;

import com.aliyun.alink.linksdk.channel.core.base.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IOnSubscribeRrpcListener {
    boolean needUISafety();

    void onReceived(String str, PersistentRequest persistentRequest, IOnRrpcResponseHandle iOnRrpcResponseHandle);

    void onResponseFailed(String str, AError aError);

    void onResponseSuccess(String str);

    void onSubscribeFailed(String str, AError aError);

    void onSubscribeSuccess(String str);
}
