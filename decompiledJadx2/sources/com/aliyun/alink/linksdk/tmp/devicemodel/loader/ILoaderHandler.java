package com.aliyun.alink.linksdk.tmp.devicemodel.loader;

import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ILoaderHandler {
    void onDeserialize(DeviceModel deviceModel);

    void onDeserializeError(String str);

    void onSerialize(String str);

    void onSerializeError(String str);
}
