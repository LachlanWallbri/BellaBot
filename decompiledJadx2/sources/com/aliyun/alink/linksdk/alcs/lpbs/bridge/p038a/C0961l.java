package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.api.ICAProbeListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalProbeResult;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalProbeListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAProbeDevListenerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.l */
/* loaded from: classes.dex */
public class C0961l implements ICAProbeListener {

    /* renamed from: a */
    private PalProbeListener f863a;

    /* renamed from: b */
    private PalDeviceInfo f864b;

    public C0961l(PalDeviceInfo palDeviceInfo, PalProbeListener palProbeListener) {
        this.f863a = palProbeListener;
        this.f864b = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.api.ICAProbeListener
    public void onComplete(ICADeviceInfo iCADeviceInfo, int i) {
        PalProbeListener palProbeListener = this.f863a;
        if (palProbeListener != null) {
            palProbeListener.onComplete(this.f864b, new PalProbeResult(i));
        }
    }
}
