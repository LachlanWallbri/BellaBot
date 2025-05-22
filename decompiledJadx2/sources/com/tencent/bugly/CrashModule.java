package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.C5887c;
import com.tencent.bugly.crashreport.crash.C5888d;
import com.tencent.bugly.proguard.C5930n;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.InterfaceC5931o;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class CrashModule extends AbstractC5864a {
    public static final int MODULE_ID = 1004;

    /* renamed from: c */
    private static int f7636c;

    /* renamed from: e */
    private static CrashModule f7637e = new CrashModule();

    /* renamed from: a */
    private long f7638a;

    /* renamed from: b */
    private BuglyStrategy.C5863a f7639b;

    /* renamed from: d */
    private boolean f7640d = false;

    public static CrashModule getInstance() {
        CrashModule crashModule = f7637e;
        crashModule.f7641id = 1004;
        return crashModule;
    }

    public synchronized boolean hasInitialized() {
        return this.f7640d;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006a A[Catch: all -> 0x0094, TryCatch #0 {, blocks: (B:7:0x0003, B:10:0x0009, B:12:0x003c, B:15:0x0043, B:17:0x0053, B:20:0x005a, B:22:0x006a, B:23:0x0071, B:28:0x0065, B:29:0x004e), top: B:6:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    @Override // com.tencent.bugly.AbstractC5864a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!this.f7640d) {
                C5940x.m3818a("Initializing crash module.", new Object[0]);
                C5930n m3718a = C5930n.m3718a();
                int i = f7636c + 1;
                f7636c = i;
                m3718a.m3729a(1004, i);
                this.f7640d = true;
                CrashReport.setContext(context);
                m3330a(context, buglyStrategy);
                C5887c m3548a = C5887c.m3548a(1004, context, z, this.f7639b, (InterfaceC5931o) null, (String) null);
                m3548a.m3559e();
                m3548a.m3567m();
                if (buglyStrategy != null && !buglyStrategy.isEnableNativeCrashMonitor()) {
                    C5940x.m3818a("[crash] Closed native crash monitor!", new Object[0]);
                    m3548a.m3560f();
                    if (buglyStrategy != null && !buglyStrategy.isEnableANRCrashMonitor()) {
                        C5940x.m3818a("[crash] Closed ANR monitor!", new Object[0]);
                        m3548a.m3563i();
                        m3548a.m3551a(buglyStrategy == null ? buglyStrategy.getAppReportDelay() : 0L);
                        m3548a.m3566l();
                        C5888d.m3569a(context);
                        BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
                        buglyBroadcastReceiver.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                        buglyBroadcastReceiver.register(context);
                        C5930n m3718a2 = C5930n.m3718a();
                        int i2 = f7636c - 1;
                        f7636c = i2;
                        m3718a2.m3729a(1004, i2);
                    }
                    m3548a.m3562h();
                    m3548a.m3551a(buglyStrategy == null ? buglyStrategy.getAppReportDelay() : 0L);
                    m3548a.m3566l();
                    C5888d.m3569a(context);
                    BuglyBroadcastReceiver buglyBroadcastReceiver2 = BuglyBroadcastReceiver.getInstance();
                    buglyBroadcastReceiver2.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    buglyBroadcastReceiver2.register(context);
                    C5930n m3718a22 = C5930n.m3718a();
                    int i22 = f7636c - 1;
                    f7636c = i22;
                    m3718a22.m3729a(1004, i22);
                }
                m3548a.m3561g();
                if (buglyStrategy != null) {
                    C5940x.m3818a("[crash] Closed ANR monitor!", new Object[0]);
                    m3548a.m3563i();
                    m3548a.m3551a(buglyStrategy == null ? buglyStrategy.getAppReportDelay() : 0L);
                    m3548a.m3566l();
                    C5888d.m3569a(context);
                    BuglyBroadcastReceiver buglyBroadcastReceiver22 = BuglyBroadcastReceiver.getInstance();
                    buglyBroadcastReceiver22.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                    buglyBroadcastReceiver22.register(context);
                    C5930n m3718a222 = C5930n.m3718a();
                    int i222 = f7636c - 1;
                    f7636c = i222;
                    m3718a222.m3729a(1004, i222);
                }
                m3548a.m3562h();
                m3548a.m3551a(buglyStrategy == null ? buglyStrategy.getAppReportDelay() : 0L);
                m3548a.m3566l();
                C5888d.m3569a(context);
                BuglyBroadcastReceiver buglyBroadcastReceiver222 = BuglyBroadcastReceiver.getInstance();
                buglyBroadcastReceiver222.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                buglyBroadcastReceiver222.register(context);
                C5930n m3718a2222 = C5930n.m3718a();
                int i2222 = f7636c - 1;
                f7636c = i2222;
                m3718a2222.m3729a(1004, i2222);
            }
        }
    }

    /* renamed from: a */
    private synchronized void m3330a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy == null) {
            return;
        }
        String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
        if (!TextUtils.isEmpty(libBuglySOFilePath)) {
            C5873a.m3389a(context).f7765m = libBuglySOFilePath;
            C5940x.m3818a("setted libBugly.so file path :%s", libBuglySOFilePath);
        }
        if (buglyStrategy.getCrashHandleCallback() != null) {
            this.f7639b = buglyStrategy.getCrashHandleCallback();
            C5940x.m3818a("setted CrashHanldeCallback", new Object[0]);
        }
        if (buglyStrategy.getAppReportDelay() > 0) {
            this.f7638a = buglyStrategy.getAppReportDelay();
            C5940x.m3818a("setted delay: %d", Long.valueOf(this.f7638a));
        }
    }

    @Override // com.tencent.bugly.AbstractC5864a
    public void onServerStrategyChanged(StrategyBean strategyBean) {
        C5887c m3547a;
        if (strategyBean == null || (m3547a = C5887c.m3547a()) == null) {
            return;
        }
        m3547a.m3552a(strategyBean);
    }

    @Override // com.tencent.bugly.AbstractC5864a
    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
