package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CustomEventRspMsglistenerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.a */
/* loaded from: classes.dex */
public class C0944a implements PalMsgListener {

    /* renamed from: d */
    private static final String f796d = "[AlcsLPBS]CustomEventRspMsglistenerWrapper";

    /* renamed from: a */
    protected PalMsgListener f797a;

    /* renamed from: b */
    protected String f798b;

    /* renamed from: c */
    protected IJSEngine f799c;

    public C0944a(PalMsgListener palMsgListener, String str, IJSEngine iJSEngine) {
        this.f797a = palMsgListener;
        this.f798b = str;
        this.f799c = iJSEngine;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
    public void onLoad(PalRspMessage palRspMessage) {
        if (palRspMessage == null) {
            ALog.m480e(f796d, "onLoad ioTRspMessage or paylod null");
        } else {
            String str = null;
            ALog.m479d(f796d, "onLoad response code:" + palRspMessage.code + " mJsEngine:" + this.f799c + " mJsCode isempty:" + TextUtils.isEmpty(this.f798b));
            if (this.f799c != null && !TextUtils.isEmpty(this.f798b)) {
                str = this.f799c.rawDataToProtocol(this.f798b, palRspMessage.payload);
            }
            if (!TextUtils.isEmpty(str)) {
                palRspMessage.payload = str.getBytes();
            }
        }
        PalMsgListener palMsgListener = this.f797a;
        if (palMsgListener != null) {
            palMsgListener.onLoad(palRspMessage);
        }
    }
}
