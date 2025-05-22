package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: PkDnChangeListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.h */
/* loaded from: classes.dex */
public class C0933h implements PalMsgListener {

    /* renamed from: a */
    private static final String f775a = "[AlcsLPBS]PkDnChangeListener";

    /* renamed from: b */
    private PalMsgListener f776b;

    /* renamed from: c */
    private PalDeviceInfo f777c;

    public C0933h(PalDeviceInfo palDeviceInfo, PalMsgListener palMsgListener) {
        this.f776b = palMsgListener;
        this.f777c = palDeviceInfo;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
    public void onLoad(PalRspMessage palRspMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("onLoad response code:");
        sb.append(palRspMessage != null ? String.valueOf(palRspMessage.code) : " response null");
        ALog.m479d(f775a, sb.toString());
        PalMsgListener palMsgListener = this.f776b;
        if (palMsgListener != null) {
            palMsgListener.onLoad(palRspMessage);
        }
    }
}
