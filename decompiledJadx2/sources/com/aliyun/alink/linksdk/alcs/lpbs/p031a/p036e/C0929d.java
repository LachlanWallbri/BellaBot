package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DiscoveryPkDnChgListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.d */
/* loaded from: classes.dex */
public class C0929d implements PalDiscoveryListener {

    /* renamed from: a */
    private static final String f751a = "[AlcsLPBS]DiscoveryPkDnChgListener";

    /* renamed from: b */
    private PalDiscoveryListener f752b;

    /* renamed from: c */
    private String f753c;

    public C0929d(String str, PalDiscoveryListener palDiscoveryListener) {
        this.f752b = palDiscoveryListener;
        this.f753c = str;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
    public void onDiscoveryDevice(final PalDiscoveryDeviceInfo palDiscoveryDeviceInfo) {
        if (this.f752b == null || palDiscoveryDeviceInfo == null) {
            ALog.m480e(f751a, "onDiscoveryDevice mListener or discoveryDeviceInfo null");
            return;
        }
        ALog.m479d(f751a, "onDiscoveryDevice discoveryDeviceInfo:" + palDiscoveryDeviceInfo.getDevId() + " isPkDnNeedConvert:" + palDiscoveryDeviceInfo.isPkDnNeedConvert());
        if (palDiscoveryDeviceInfo.isPkDnNeedConvert()) {
            PluginMgr.getInstance().initDevTrans(palDiscoveryDeviceInfo, new IDevInfoTrans.IDevInfoTransListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.d.1
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDevInfoTrans.IDevInfoTransListener
                public void onComplete(boolean z, Object obj) {
                    ALog.m479d(C0929d.f751a, "initDevTrans onComplete isSuccess:" + z + " id:" + palDiscoveryDeviceInfo.getDevId());
                    if (!z) {
                        ALog.m480e(C0929d.f751a, "initDevTrans onComplete isSuccess error onDiscoveryDevice not Success");
                        return;
                    }
                    palDiscoveryDeviceInfo.deviceInfo = PluginMgr.getInstance().toAliIoTPkDn(palDiscoveryDeviceInfo.deviceInfo);
                    palDiscoveryDeviceInfo.pluginId = C0929d.this.f753c;
                    PluginMgr.getInstance().insertDiscoveryDev(palDiscoveryDeviceInfo.deviceInfo.getDevId(), C0929d.this.f753c, palDiscoveryDeviceInfo);
                    C0929d.this.f752b.onDiscoveryDevice(palDiscoveryDeviceInfo);
                }
            });
            return;
        }
        palDiscoveryDeviceInfo.pluginId = this.f753c;
        if (PluginMgr.getInstance().getDiscoveryedDevInfo(palDiscoveryDeviceInfo.deviceInfo.getDevId()) != null) {
            ALog.m484w(f751a, "onDiscoveryDevice dev found before");
        }
        PluginMgr.getInstance().insertDiscoveryDev(palDiscoveryDeviceInfo.deviceInfo.getDevId(), this.f753c, palDiscoveryDeviceInfo);
        this.f752b.onDiscoveryDevice(palDiscoveryDeviceInfo);
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
    public void onDiscoveryFinish() {
        PalDiscoveryListener palDiscoveryListener = this.f752b;
        if (palDiscoveryListener != null) {
            palDiscoveryListener.onDiscoveryFinish();
        }
    }
}
