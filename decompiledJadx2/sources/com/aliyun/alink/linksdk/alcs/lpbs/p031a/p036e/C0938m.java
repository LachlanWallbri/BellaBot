package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: UpToCloud.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.m */
/* loaded from: classes.dex */
public class C0938m implements PalMsgListener {

    /* renamed from: a */
    private static final String f786a = "[AlcsLPBS]UpToCloud";

    /* renamed from: b */
    private PalMsgListener f787b;

    /* renamed from: c */
    private String f788c;

    /* renamed from: d */
    private IThingCloudChannel f789d;

    /* renamed from: e */
    private PalDeviceInfo f790e;

    public C0938m(PalDeviceInfo palDeviceInfo, IThingCloudChannel iThingCloudChannel, String str, PalMsgListener palMsgListener) {
        ALog.m479d(f786a, "UpToCloud cloudChannel:" + iThingCloudChannel + " topic:" + iThingCloudChannel + " listener:" + palMsgListener);
        this.f787b = palMsgListener;
        this.f788c = str;
        this.f789d = iThingCloudChannel;
        this.f790e = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
    public void onLoad(PalRspMessage palRspMessage) {
        ALog.m479d(f786a, "onLoad mCloudChannel:" + this.f789d + " mListener:" + this.f787b + " topic:" + this.f788c + " response:" + palRspMessage);
        if (this.f789d != null && PluginMgr.getInstance().isDataToCloud(this.f790e)) {
            this.f789d.reportData(this.f788c, palRspMessage.payload);
        }
        PalMsgListener palMsgListener = this.f787b;
        if (palMsgListener != null) {
            palMsgListener.onLoad(palRspMessage);
        }
    }
}
