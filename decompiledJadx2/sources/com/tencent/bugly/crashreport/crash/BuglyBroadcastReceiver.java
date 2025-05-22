package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.crashreport.biz.C5871b;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.proguard.C5937u;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class BuglyBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: d */
    private static BuglyBroadcastReceiver f7818d;

    /* renamed from: b */
    private Context f7820b;

    /* renamed from: c */
    private String f7821c;

    /* renamed from: e */
    private boolean f7822e = true;

    /* renamed from: a */
    private IntentFilter f7819a = new IntentFilter();

    public static synchronized BuglyBroadcastReceiver getInstance() {
        BuglyBroadcastReceiver buglyBroadcastReceiver;
        synchronized (BuglyBroadcastReceiver.class) {
            if (f7818d == null) {
                f7818d = new BuglyBroadcastReceiver();
            }
            buglyBroadcastReceiver = f7818d;
        }
        return buglyBroadcastReceiver;
    }

    public synchronized void addFilter(String str) {
        if (!this.f7819a.hasAction(str)) {
            this.f7819a.addAction(str);
        }
        C5940x.m3823c("add action %s", str);
    }

    public synchronized void register(Context context) {
        this.f7820b = context;
        C5942z.m3867a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    C5940x.m3817a(BuglyBroadcastReceiver.f7818d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        BuglyBroadcastReceiver.this.f7820b.registerReceiver(BuglyBroadcastReceiver.f7818d, BuglyBroadcastReceiver.this.f7819a);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void unregister(Context context) {
        try {
            C5940x.m3817a(getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.f7820b = context;
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            m3500a(context, intent);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private synchronized boolean m3500a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (this.f7822e) {
                    this.f7822e = false;
                    return true;
                }
                String m3455c = C5874b.m3455c(this.f7820b);
                C5940x.m3823c("is Connect BC " + m3455c, new Object[0]);
                C5940x.m3818a("network %s changed to %s", this.f7821c, m3455c);
                if (m3455c == null) {
                    this.f7821c = null;
                    return true;
                }
                String str = this.f7821c;
                this.f7821c = m3455c;
                long currentTimeMillis = System.currentTimeMillis();
                C5876a m3487a = C5876a.m3487a();
                C5937u m3773a = C5937u.m3773a();
                C5873a m3389a = C5873a.m3389a(context);
                if (m3487a != null && m3773a != null && m3389a != null) {
                    if (!m3455c.equals(str)) {
                        if (currentTimeMillis - m3773a.m3791a(C5887c.f7917a) > 30000) {
                            C5940x.m3818a("try to upload crash on network changed.", new Object[0]);
                            C5887c m3547a = C5887c.m3547a();
                            if (m3547a != null) {
                                m3547a.m3551a(0L);
                            }
                        }
                        if (currentTimeMillis - m3773a.m3791a(1001) > 30000) {
                            C5940x.m3818a("try to upload userinfo on network changed.", new Object[0]);
                            C5871b.f7682a.m3352b();
                        }
                    }
                    return true;
                }
                C5940x.m3824d("not inited BC not work", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
