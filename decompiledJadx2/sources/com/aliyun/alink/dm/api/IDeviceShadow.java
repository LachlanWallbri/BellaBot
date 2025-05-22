package com.aliyun.alink.dm.api;

import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDeviceShadow {
    void setShadowChangeListener(IShadowRRPC iShadowRRPC);

    void shadowUpload(String str, IConnectSendListener iConnectSendListener);
}
