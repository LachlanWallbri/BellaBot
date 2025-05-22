package com.aliyun.alink.linkkit.p029a;

import com.aliyun.alink.dm.api.DeviceManager;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: LinkKitNotifyDispatcher.java */
/* renamed from: com.aliyun.alink.linkkit.a.a */
/* loaded from: classes.dex */
public class C0906a {
    private C0906a() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: LinkKitNotifyDispatcher.java */
    /* renamed from: com.aliyun.alink.linkkit.a.a$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        private static final C0906a f704a = new C0906a();
    }

    /* renamed from: a */
    public static C0906a m321a() {
        return a.f704a;
    }

    /* renamed from: a */
    public void m322a(IConnectNotifyListener iConnectNotifyListener) {
        DeviceManager.getInstance().registerOnPushListener(iConnectNotifyListener);
    }

    /* renamed from: b */
    public void m323b(IConnectNotifyListener iConnectNotifyListener) {
        DeviceManager.getInstance().unRegisterOnPushListener(iConnectNotifyListener);
    }
}
