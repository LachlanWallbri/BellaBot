package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAConnectListenerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.h */
/* loaded from: classes.dex */
public class C0957h implements ICAConnectListener {

    /* renamed from: b */
    private static final String f848b = "[AlcsLPBS]ICAConnectListenerWrapper";

    /* renamed from: a */
    protected PalConnectListener f849a;

    public C0957h(PalConnectListener palConnectListener) {
        this.f849a = palConnectListener;
    }

    @Override // com.aliyun.alink.linksdk.alcs.api.ICAConnectListener
    public void onLoad(int i, String str, ICADeviceInfo iCADeviceInfo) {
        ALog.m479d(f848b, "onLoad errorCode:" + i + " deviceInfo:" + iCADeviceInfo);
        if (i == 200) {
            i = 0;
        }
        PalConnectListener palConnectListener = this.f849a;
        if (palConnectListener != null) {
            palConnectListener.onLoad(i, null, new PalDeviceInfo(iCADeviceInfo.productKey, iCADeviceInfo.deviceName));
        }
    }
}
