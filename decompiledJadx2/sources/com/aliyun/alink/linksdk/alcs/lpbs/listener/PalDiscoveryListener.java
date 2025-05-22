package com.aliyun.alink.linksdk.alcs.lpbs.listener;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface PalDiscoveryListener {
    void onDiscoveryDevice(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo);

    void onDiscoveryFinish();
}
