package com.aliyun.alink.linksdk.channel.core.persistent;

import com.aliyun.alink.linksdk.channel.core.base.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IOnSubscribeListener {
    boolean needUISafety();

    void onFailed(String str, AError aError);

    void onSuccess(String str);
}
