package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p035d;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p033b.C0923a;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e.C0932g;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DeviceStateListenerMgr.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.d.a */
/* loaded from: classes.dex */
public class C0925a {

    /* renamed from: a */
    private static final String f724a = "[AlcsLPBS]DeviceStateListenerMgr";

    /* renamed from: b */
    private C0923a f725b;

    public C0925a(C0923a c0923a) {
        this.f725b = c0923a;
    }

    /* renamed from: a */
    public boolean m349a(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        if (palDeviceStateListener == null || palDeviceInfo == null) {
            ALog.m480e(f724a, "regDeviceStateListener listener deviceInfo null");
            return false;
        }
        C0932g c0932g = new C0932g(palDeviceInfo, palDeviceStateListener);
        ALog.m479d(f724a, "regDeviceStateListener " + palDeviceStateListener.hashCode() + " listenerProxy:" + c0932g.hashCode());
        IPalConnect m348b = this.f725b.m348b(palDeviceInfo.getDevId());
        if (m348b == null) {
            ALog.m480e(f724a, "regDeviceStateListener connect null");
            return false;
        }
        m348b.regDeviceStateListener(PluginMgr.getInstance().toPrivatePkDn(palDeviceInfo), c0932g);
        return true;
    }

    /* renamed from: b */
    public boolean m350b(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        if (palDeviceStateListener == null || palDeviceInfo == null) {
            ALog.m480e(f724a, "unregDeviceStateListener listener deviceInfo null");
            return false;
        }
        ALog.m479d(f724a, "unregDeviceStateListener listener:" + palDeviceStateListener.hashCode());
        IPalConnect m348b = this.f725b.m348b(palDeviceInfo.getDevId());
        if (m348b == null) {
            ALog.m480e(f724a, "unregDeviceStateListener connect null");
            return false;
        }
        m348b.unregDeviceStateListener(PluginMgr.getInstance().toPrivatePkDn(palDeviceInfo), palDeviceStateListener);
        return true;
    }
}
