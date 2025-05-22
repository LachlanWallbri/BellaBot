package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PalDevStateListenerProxy.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.g */
/* loaded from: classes.dex */
public class C0932g implements PalDeviceStateListener {

    /* renamed from: a */
    private static final String f772a = "[AlcsLPBS]PalDevStateListenerProxy";

    /* renamed from: b */
    private PalDeviceStateListener f773b;

    /* renamed from: c */
    private PalDeviceInfo f774c;

    public C0932g(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener) {
        this.f773b = palDeviceStateListener;
        this.f774c = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener
    public void onDeviceStateChange(PalDeviceInfo palDeviceInfo, int i) {
        if (palDeviceInfo == null) {
            ALog.m480e(f772a, "deviceInfo null state:" + i);
            return;
        }
        ALog.m479d(f772a, "onDeviceStateChange deviceInfo:" + palDeviceInfo.toString() + " state:" + i + " mDeviceInfo:" + this.f774c.toString());
        PalDeviceStateListener palDeviceStateListener = this.f773b;
        if (palDeviceStateListener != null) {
            palDeviceStateListener.onDeviceStateChange(this.f774c, i);
        }
    }
}
