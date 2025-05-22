package com.aliyun.alink.linksdk.alcs.lpbs.component.cloud;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IThingCloudChannel {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IChannelActionListener {
        void onFailed(AError aError);

        void onSuccess();
    }

    void addDownDataListener(IDataDownListener iDataDownListener);

    void removeDownDataListener(IDataDownListener iDataDownListener);

    void reportData(String str, Object obj, IChannelActionListener iChannelActionListener);

    void reportData(String str, byte[] bArr);
}
