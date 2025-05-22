package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.lpbs.component.jsengine.IJSEngine;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CustomFormatMsgListenerWrapper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.b */
/* loaded from: classes.dex */
public class C0951b implements PalMsgListener {

    /* renamed from: d */
    private static final String f821d = "[AlcsLPBS]CustomFormatMsgListenerWrapper";

    /* renamed from: a */
    protected PalMsgListener f822a;

    /* renamed from: b */
    protected String f823b;

    /* renamed from: c */
    protected IJSEngine f824c;

    public C0951b(PalMsgListener palMsgListener, String str, IJSEngine iJSEngine) {
        this.f822a = palMsgListener;
        this.f823b = str;
        this.f824c = iJSEngine;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener
    public void onLoad(PalRspMessage palRspMessage) {
        if (palRspMessage == null) {
            ALog.m480e(f821d, "onLoad ioTRspMessage or paylod null");
        } else {
            ALog.m479d(f821d, "onLoad response code:" + palRspMessage.code + "  mJsEngine:" + this.f824c);
            IJSEngine iJSEngine = this.f824c;
            String rawDataToProtocol = iJSEngine != null ? iJSEngine.rawDataToProtocol(this.f823b, palRspMessage.payload) : null;
            if (!TextUtils.isEmpty(rawDataToProtocol)) {
                palRspMessage.payload = rawDataToProtocol.getBytes();
            }
        }
        PalMsgListener palMsgListener = this.f822a;
        if (palMsgListener != null) {
            palMsgListener.onLoad(palRspMessage);
        }
    }
}
