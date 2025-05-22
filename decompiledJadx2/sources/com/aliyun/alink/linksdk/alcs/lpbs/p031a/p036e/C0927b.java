package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DataDownListenerProxy.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.b */
/* loaded from: classes.dex */
public class C0927b implements IDataDownListener {

    /* renamed from: a */
    private static final String f741a = "[AlcsLPBS]DataDownListenerProxy";

    /* renamed from: b */
    private IPalConnect f742b;

    /* renamed from: c */
    private PalDeviceInfo f743c;

    public C0927b(IPalConnect iPalConnect, PalDeviceInfo palDeviceInfo) {
        this.f742b = iPalConnect;
        this.f743c = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener
    public void onDataDown(String str, byte[] bArr) {
        ALog.m479d(f741a, "onDataDown mConnect:" + this.f742b + " topic:" + str + " payload:" + bArr);
        if (this.f742b != null) {
            PalReqMessage palReqMessage = new PalReqMessage();
            palReqMessage.topic = str;
            palReqMessage.payload = bArr;
            palReqMessage.deviceInfo = this.f743c;
            this.f742b.asyncSendRequest(palReqMessage, new PalMsgListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.b.1
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
                public void onLoad(PalRspMessage palRspMessage) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onDataDown asyncSendRequest onLoad code:");
                    sb.append(palRspMessage == null ? "error" : Integer.valueOf(palRspMessage.code));
                    ALog.m479d(C0927b.f741a, sb.toString());
                }
            });
        }
    }
}
