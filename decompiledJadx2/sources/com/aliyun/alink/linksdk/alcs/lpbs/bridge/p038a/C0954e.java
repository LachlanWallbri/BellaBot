package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICAAlcsNative;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAAlcsDiscovery.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.e */
/* loaded from: classes.dex */
public class C0954e implements IPalDiscovery {

    /* renamed from: b */
    private static final String f843b = "[AlcsLPBS]ICAAlcsDiscovery";

    /* renamed from: a */
    private C0952c f844a;

    public C0954e(C0952c c0952c) {
        this.f844a = c0952c;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startDiscovery(int i, PalDiscoveryListener palDiscoveryListener) {
        ALog.m479d(f843b, "startDiscovery timeOut:" + i + " listener:" + palDiscoveryListener);
        return ICAAlcsNative.discoveryDevice(i, new C0958i(this.f844a, palDiscoveryListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopDiscovery() {
        ALog.m479d(f843b, "stopDiscovery");
        return ICAAlcsNative.stopDiscoveryDevice();
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean startNotifyMonitor(PalDiscoveryListener palDiscoveryListener) {
        ALog.m479d(f843b, "startNotifyMonitor listener:" + palDiscoveryListener);
        return ICAAlcsNative.regDeviceNotifyListener(new C0958i(this.f844a, palDiscoveryListener));
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalDiscovery
    public boolean stopNotifyMonitor() {
        ALog.m479d(f843b, "stopNotifyMonitor");
        return ICAAlcsNative.unregDeviceNotifyListener();
    }
}
