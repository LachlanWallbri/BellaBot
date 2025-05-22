package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PkDnChgeConnectListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.i */
/* loaded from: classes.dex */
public class C0934i implements PalConnectListener {

    /* renamed from: a */
    private PalConnectListener f778a;

    public C0934i(PalConnectListener palConnectListener) {
        this.f778a = palConnectListener;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener
    public void onLoad(int i, Map<String, Object> map, PalDeviceInfo palDeviceInfo) {
        PalDeviceInfo aliIoTPkDn = PluginMgr.getInstance().toAliIoTPkDn(palDeviceInfo);
        PalConnectListener palConnectListener = this.f778a;
        if (palConnectListener != null) {
            palConnectListener.onLoad(i, map, aliIoTPkDn);
        }
    }
}
