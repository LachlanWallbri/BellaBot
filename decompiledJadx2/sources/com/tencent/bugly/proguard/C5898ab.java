package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ab */
/* loaded from: classes7.dex */
public final class C5898ab extends Thread {

    /* renamed from: a */
    private static boolean f8021a = false;

    /* renamed from: b */
    private List<RunnableC5897aa> f8022b = Collections.synchronizedList(new ArrayList());

    /* renamed from: c */
    private List<InterfaceC5899ac> f8023c = Collections.synchronizedList(new ArrayList());

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.ab$a */
    /* loaded from: classes7.dex */
    static class a {

        /* renamed from: a */
        public static final C5898ab f8024a = new C5898ab();
    }

    /* renamed from: a */
    public static C5898ab m3634a(Context context) {
        return a.f8024a;
    }

    /* renamed from: a */
    public final void m3637a() {
        m3636a(new Handler(Looper.getMainLooper()), 5000L);
    }

    /* renamed from: b */
    public final void m3639b() {
        m3635a(new Handler(Looper.getMainLooper()));
    }

    /* renamed from: a */
    private void m3636a(Handler handler, long j) {
        if (handler == null) {
            C5940x.m3825e("addThread handler should not be null", new Object[0]);
            return;
        }
        String name = handler.getLooper().getThread().getName();
        for (int i = 0; i < this.f8022b.size(); i++) {
            if (this.f8022b.get(i).m3632e().equals(handler.getLooper().getThread().getName())) {
                C5940x.m3825e("addThread fail ,this thread has been added in monitor queue", new Object[0]);
                return;
            }
        }
        this.f8022b.add(new RunnableC5897aa(handler, name, 5000L));
    }

    /* renamed from: a */
    private void m3635a(Handler handler) {
        if (handler == null) {
            C5940x.m3825e("removeThread handler should not be null", new Object[0]);
            return;
        }
        for (int i = 0; i < this.f8022b.size(); i++) {
            if (this.f8022b.get(i).m3632e().equals(handler.getLooper().getThread().getName())) {
                C5940x.m3823c("remove handler::%s", this.f8022b.get(i));
                this.f8022b.remove(i);
            }
        }
    }

    /* renamed from: c */
    public final boolean m3641c() {
        if (!isAlive()) {
            return false;
        }
        interrupt();
        f8021a = true;
        return true;
    }

    /* renamed from: d */
    public final boolean m3642d() {
        if (isAlive()) {
            return false;
        }
        f8021a = false;
        start();
        return true;
    }

    /* renamed from: a */
    public final void m3638a(InterfaceC5899ac interfaceC5899ac) {
        if (this.f8023c.contains(interfaceC5899ac)) {
            C5940x.m3825e("addThreadMonitorListeners fail ,this threadMonitorListener has been added in monitor queue", new Object[0]);
        } else {
            this.f8023c.add(interfaceC5899ac);
        }
    }

    /* renamed from: b */
    public final void m3640b(InterfaceC5899ac interfaceC5899ac) {
        this.f8023c.remove(interfaceC5899ac);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        setName("Bugly-ThreadMonitor");
        while (!f8021a) {
            for (int i = 0; i < this.f8022b.size(); i++) {
                this.f8022b.get(i).m3627a();
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            for (long j = 2000; j > 0 && !isInterrupted(); j = 2000 - (SystemClock.uptimeMillis() - uptimeMillis)) {
                try {
                    sleep(j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.f8022b.size(); i3++) {
                i2 = Math.max(i2, this.f8022b.get(i3).m3630c());
            }
            if (i2 != 0 && i2 != 1) {
                ArrayList arrayList = new ArrayList();
                for (int i4 = 0; i4 < this.f8022b.size(); i4++) {
                    RunnableC5897aa runnableC5897aa = this.f8022b.get(i4);
                    if (runnableC5897aa.m3629b()) {
                        arrayList.add(runnableC5897aa);
                        runnableC5897aa.m3628a(Long.MAX_VALUE);
                        C5940x.m3824d("to avoid upload block state repeated. as thread is blocked ,it may not be monitor until thread is unblock or this state has not been dealed with.", new Object[0]);
                    }
                }
                int i5 = 0;
                boolean z = false;
                while (i5 < arrayList.size()) {
                    RunnableC5897aa runnableC5897aa2 = (RunnableC5897aa) arrayList.get(i5);
                    Thread m3631d = runnableC5897aa2.m3631d();
                    boolean z2 = z;
                    for (int i6 = 0; i6 < this.f8023c.size(); i6++) {
                        if (this.f8023c.get(i6).mo3522a(m3631d)) {
                            z2 = true;
                        }
                    }
                    if (!z2 && runnableC5897aa2.m3632e().contains("main")) {
                        runnableC5897aa2.m3633f();
                        C5940x.m3824d("although thread is blocked ,may not be anr error,so restore handler check wait time and restart check main thread", new Object[0]);
                    }
                    i5++;
                    z = z2;
                }
            }
        }
    }
}
