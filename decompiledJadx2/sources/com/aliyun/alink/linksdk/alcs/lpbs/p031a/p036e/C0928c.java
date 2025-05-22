package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import com.aliyun.alink.linksdk.alcs.lpbs.api.AlcsPalSdk;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: DiscoveryForceStopListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.c */
/* loaded from: classes.dex */
public class C0928c implements PalDiscoveryListener {

    /* renamed from: a */
    private static final String f745a = "[AlcsLPBS]DiscoveryForceStopListener";

    /* renamed from: b */
    private PalDiscoveryListener f746b;

    /* renamed from: d */
    private int f748d;

    /* renamed from: c */
    private AtomicInteger f747c = new AtomicInteger(PluginMgr.getInstance().getPluginCount());

    /* renamed from: e */
    private a f749e = new a();

    public C0928c(int i, PalDiscoveryListener palDiscoveryListener) {
        this.f746b = palDiscoveryListener;
        this.f748d = i;
        if (AlcsPalSdk.getHandler() != null) {
            AlcsPalSdk.getHandler().postDelayed(this.f749e, this.f748d + 1000);
        }
        ALog.m479d(f745a, "DiscoveryForceStopListener mFinishedPluginCount:" + this.f747c.get());
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
    public void onDiscoveryDevice(PalDiscoveryDeviceInfo palDiscoveryDeviceInfo) {
        PalDiscoveryListener palDiscoveryListener = this.f746b;
        if (palDiscoveryListener != null) {
            palDiscoveryListener.onDiscoveryDevice(palDiscoveryDeviceInfo);
        }
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDiscoveryListener
    public void onDiscoveryFinish() {
        int decrementAndGet = this.f747c.decrementAndGet();
        ALog.m479d(f745a, "onDiscoveryFinish count:" + decrementAndGet);
        if (decrementAndGet == 0) {
            if (this.f749e != null) {
                AlcsPalSdk.getHandler().removeCallbacks(this.f749e);
            }
            this.f749e = null;
            PalDiscoveryListener palDiscoveryListener = this.f746b;
            if (palDiscoveryListener != null) {
                palDiscoveryListener.onDiscoveryFinish();
            }
        }
    }

    /* renamed from: a */
    public void m355a() {
        this.f749e = null;
        this.f747c.set(0);
        PalDiscoveryListener palDiscoveryListener = this.f746b;
        if (palDiscoveryListener != null) {
            palDiscoveryListener.onDiscoveryFinish();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: DiscoveryForceStopListener.java */
    /* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.c$a */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0928c.this.m355a();
        }
    }
}
