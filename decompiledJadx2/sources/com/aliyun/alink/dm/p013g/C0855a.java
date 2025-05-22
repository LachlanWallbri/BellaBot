package com.aliyun.alink.dm.p013g;

import android.text.TextUtils;
import com.aliyun.alink.dm.coap.p009a.C0849a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DMNotifyDispatcher.java */
/* renamed from: com.aliyun.alink.dm.g.a */
/* loaded from: classes.dex */
public class C0855a extends C0849a<IConnectNotifyListener> implements IConnectNotifyListener {
    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public boolean shouldHandle(String str, String str2) {
        return true;
    }

    private C0855a() {
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: DMNotifyDispatcher.java */
    /* renamed from: com.aliyun.alink.dm.g.a$a */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a */
        private static final C0855a f405a = new C0855a();
    }

    /* renamed from: c */
    public static C0855a m123c() {
        return a.f405a;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onNotify(String str, String str2, AMessage aMessage) {
        C0859a.m131a("DMNotifyDispatcher", "onNotify() called with: connectId = [" + str + "], topic = [" + str2 + "], aMessage = [" + aMessage + "]");
        if (TextUtils.isEmpty(str2) || this.f364a == null || m106a() < 1) {
            return;
        }
        for (int i = 0; i < m106a(); i++) {
            IConnectNotifyListener a2 = m107a(i);
            if (a2 != null && a2.shouldHandle(str, str2)) {
                a2.onNotify(str, str2, aMessage);
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
    public void onConnectStateChange(String str, ConnectState connectState) {
        if (!ConnectSDK.getInstance().getPersistentConnectId().equals(str)) {
            C0859a.m134c("DMNotifyDispatcher", "connectId=" + str + " not support.");
            return;
        }
        if (this.f364a == null || m106a() < 1) {
            return;
        }
        for (int i = 0; i < m106a(); i++) {
            IConnectNotifyListener a2 = m107a(i);
            if (a2 != null) {
                a2.onConnectStateChange(str, connectState);
            }
        }
    }

    /* renamed from: a */
    public void m124a(IConnectNotifyListener iConnectNotifyListener) {
        if (iConnectNotifyListener == null) {
            return;
        }
        m108a((C0855a) iConnectNotifyListener);
    }

    /* renamed from: b */
    public void m125b(IConnectNotifyListener iConnectNotifyListener) {
        if (iConnectNotifyListener == null) {
            return;
        }
        m110b((C0855a) iConnectNotifyListener);
    }
}
