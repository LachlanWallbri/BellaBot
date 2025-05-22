package com.aliyun.alink.linksdk.alcs.lpbs.bridge;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalInitData;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPalBridge {
    void deInitBridge();

    IPalAuthRegister getPalAuthRegister();

    IPalConnect getPalConnect(PalDeviceInfo palDeviceInfo);

    IPalDiscovery getPalDiscovery();

    IPalProbe getPalProbe();

    void initBridge(PalInitData palInitData);
}
