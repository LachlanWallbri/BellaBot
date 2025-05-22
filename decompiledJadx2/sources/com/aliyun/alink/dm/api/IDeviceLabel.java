package com.aliyun.alink.dm.api;

import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDeviceLabel {
    void labelDelete(RequestModel requestModel, IConnectSendListener iConnectSendListener);

    void labelUpdate(RequestModel requestModel, IConnectSendListener iConnectSendListener);
}
