package com.aliyun.alink.linksdk.alcs.lpbs.component.cloud;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDevInfoTrans {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IDevInfoTransListener {
        void onComplete(boolean z, Object obj);
    }

    void init(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo, IDevInfoTransListener iDevInfoTransListener);

    PalDeviceInfo toAliIoTDeviceInfo(PalDeviceInfo palDeviceInfo);

    PalDeviceInfo toPrivateDeviceInfo(PalDeviceInfo palDeviceInfo);
}
