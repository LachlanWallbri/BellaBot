package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.api.ICAMsgListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICARspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAMsgListenerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.k */
/* loaded from: classes.dex */
public class C0960k implements ICAMsgListener {

    /* renamed from: b */
    private static final String f861b = "[AlcsLPBS]ICAMsgListenerWrapper";

    /* renamed from: a */
    protected PalMsgListener f862a;

    public C0960k(PalMsgListener palMsgListener) {
        this.f862a = palMsgListener;
    }

    @Override // com.aliyun.alink.linksdk.alcs.api.ICAMsgListener
    public void onLoad(ICARspMessage iCARspMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("response code:");
        sb.append(iCARspMessage != null ? String.valueOf(iCARspMessage.code) : "response null");
        ALog.m479d(f861b, sb.toString());
        PalMsgListener palMsgListener = this.f862a;
        if (palMsgListener != null) {
            palMsgListener.onLoad(C0962m.m389a(iCARspMessage));
        }
    }
}
