package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalProbeResult;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PkDnChgeProbeListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.j */
/* loaded from: classes.dex */
public class C0935j implements PalProbeListener {

    /* renamed from: a */
    private PalProbeListener f779a;

    public C0935j(PalProbeListener palProbeListener) {
        this.f779a = palProbeListener;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener
    public void onComplete(PalDeviceInfo palDeviceInfo, PalProbeResult palProbeResult) {
        PalDeviceInfo aliIoTPkDn = PluginMgr.getInstance().toAliIoTPkDn(palDeviceInfo);
        PalProbeListener palProbeListener = this.f779a;
        if (palProbeListener != null) {
            palProbeListener.onComplete(aliIoTPkDn, palProbeResult);
        }
    }
}
