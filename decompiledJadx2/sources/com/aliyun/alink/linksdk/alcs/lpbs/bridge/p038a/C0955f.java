package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalProbeResult;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;
import com.aliyun.alink.linksdk.alcs.pal.ica.ICAAlcsNative;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAAlcsProbe.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.f */
/* loaded from: classes.dex */
public class C0955f implements IPalProbe {

    /* renamed from: a */
    private static final String f845a = "[AlcsLPBS]ICAAlcsProbe";

    /* renamed from: b */
    private C0952c f846b;

    public C0955f(C0952c c0952c) {
        this.f846b = c0952c;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalProbe
    public void probeDevice(PalDeviceInfo palDeviceInfo, PalProbeListener palProbeListener) {
        ALog.m479d(f845a, "probeDevice deviceInfo:" + palDeviceInfo + " listener:" + palProbeListener);
        if (palProbeListener == null) {
            ALog.m479d(f845a, "probeDevice listener null");
            return;
        }
        ICADiscoveryDeviceInfo m381a = this.f846b.m381a(palDeviceInfo.getDevId());
        if (m381a == null) {
            ALog.m480e(f845a, "probeDevice icaDiscoveryDeviceInfo null");
            palProbeListener.onComplete(palDeviceInfo, new PalProbeResult(2));
        } else {
            ICAAlcsNative.probeDevice(m381a.addr, m381a.port, m381a.deviceInfo, new C0961l(palDeviceInfo, palProbeListener));
        }
    }
}
