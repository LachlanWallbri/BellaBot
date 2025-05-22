package com.aliyun.alink.linksdk.alcs.lpbs.bridge;

import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPalDiscovery {
    public static final int DISCOVERY_TYPE_FINISH = 0;
    public static final int DISCOVERY_TYPE_FOUND = 1;

    boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener);

    boolean startNotifyMonitor(PalDiscoveryListener palDiscoveryListener);

    boolean stopDiscovery();

    boolean stopNotifyMonitor();
}
