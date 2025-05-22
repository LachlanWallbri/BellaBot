package com.aliyun.alink.linksdk.cmp.core.listener;

import com.aliyun.alink.linksdk.cmp.core.base.ARequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IConnectDiscovery {
    void discoveryCertainDevice(ARequest aRequest, IDiscoveryListener iDiscoveryListener);

    void startDiscovery(int i, IDiscoveryListener iDiscoveryListener);

    void startDiscovery(IDiscoveryListener iDiscoveryListener);

    void startNotifyMonitor(IDiscoveryListener iDiscoveryListener);

    void stopDiscovery();

    void stopNotifyMonitor();
}
