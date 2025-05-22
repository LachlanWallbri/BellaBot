package com.aliyun.alink.linkkit.api;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ILinkKitConnectListener {
    void onError(AError aError);

    void onInitDone(Object obj);
}
