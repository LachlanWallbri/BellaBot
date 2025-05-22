package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.os.Process;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C5886b;
import com.tencent.bugly.crashreport.crash.C5887c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.proguard.C5898ab;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5899ac;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.anr.b */
/* loaded from: classes7.dex */
public final class C5885b implements InterfaceC5899ac {

    /* renamed from: c */
    private final Context f7898c;

    /* renamed from: d */
    private final C5873a f7899d;

    /* renamed from: e */
    private final C5939w f7900e;

    /* renamed from: f */
    private final C5876a f7901f;

    /* renamed from: g */
    private final String f7902g;

    /* renamed from: h */
    private final C5886b f7903h;

    /* renamed from: i */
    private FileObserver f7904i;

    /* renamed from: a */
    private AtomicInteger f7896a = new AtomicInteger(0);

    /* renamed from: b */
    private long f7897b = -1;

    /* renamed from: j */
    private boolean f7905j = true;

    public C5885b(Context context, C5876a c5876a, C5873a c5873a, C5939w c5939w, C5886b c5886b) {
        this.f7898c = C5942z.m3846a(context);
        this.f7902g = context.getDir("bugly", 0).getAbsolutePath();
        this.f7899d = c5873a;
        this.f7900e = c5939w;
        this.f7901f = c5876a;
        this.f7903h = c5886b;
    }

    /* renamed from: a */
    private static ActivityManager.ProcessErrorStateInfo m3508a(Context context, long j) {
        C5940x.m3823c("to find!", new Object[0]);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int i = 0;
        while (true) {
            C5940x.m3823c("waiting!", new Object[0]);
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        C5940x.m3823c("found!", new Object[0]);
                        return processErrorStateInfo;
                    }
                }
            }
            C5942z.m3881b(500L);
            int i2 = i + 1;
            if (i >= 20) {
                C5940x.m3823c("end!", new Object[0]);
                return null;
            }
            i = i2;
        }
    }

    /* renamed from: a */
    private CrashDetailBean m3509a(C5884a c5884a) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f7827C = C5874b.m3470k();
            crashDetailBean.f7828D = C5874b.m3466i();
            crashDetailBean.f7829E = C5874b.m3474m();
            crashDetailBean.f7830F = this.f7899d.m3438p();
            crashDetailBean.f7831G = this.f7899d.m3437o();
            crashDetailBean.f7832H = this.f7899d.m3439q();
            crashDetailBean.f7870w = C5942z.m3854a(this.f7898c, C5887c.f7921e, (String) null);
            crashDetailBean.f7849b = 3;
            crashDetailBean.f7852e = this.f7899d.m3430h();
            crashDetailBean.f7853f = this.f7899d.f7762j;
            crashDetailBean.f7854g = this.f7899d.m3445w();
            crashDetailBean.f7860m = this.f7899d.m3428g();
            crashDetailBean.f7861n = "ANR_EXCEPTION";
            crashDetailBean.f7862o = c5884a.f7894f;
            crashDetailBean.f7864q = c5884a.f7895g;
            crashDetailBean.f7839O = new HashMap();
            crashDetailBean.f7839O.put("BUGLY_CR_01", c5884a.f7893e);
            int indexOf = crashDetailBean.f7864q != null ? crashDetailBean.f7864q.indexOf("\n") : -1;
            crashDetailBean.f7863p = indexOf > 0 ? crashDetailBean.f7864q.substring(0, indexOf) : "GET_FAIL";
            crashDetailBean.f7865r = c5884a.f7891c;
            if (crashDetailBean.f7864q != null) {
                crashDetailBean.f7868u = C5942z.m3879b(crashDetailBean.f7864q.getBytes());
            }
            crashDetailBean.f7873z = c5884a.f7890b;
            crashDetailBean.f7825A = c5884a.f7889a;
            crashDetailBean.f7826B = "main(1)";
            crashDetailBean.f7833I = this.f7899d.m3447y();
            crashDetailBean.f7855h = this.f7899d.m3444v();
            crashDetailBean.f7856i = this.f7899d.m3401J();
            crashDetailBean.f7869v = c5884a.f7892d;
            crashDetailBean.f7836L = this.f7899d.f7766n;
            crashDetailBean.f7837M = this.f7899d.f7728a;
            crashDetailBean.f7838N = this.f7899d.m3415a();
            crashDetailBean.f7840P = this.f7899d.m3399H();
            crashDetailBean.f7841Q = this.f7899d.m3400I();
            crashDetailBean.f7842R = this.f7899d.m3393B();
            crashDetailBean.f7843S = this.f7899d.m3398G();
            this.f7903h.m3545c(crashDetailBean);
            crashDetailBean.f7872y = C5941y.m3832a();
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m3511a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        TraceFileHelper.C5882a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.f7888d == null || readTargetDumpInfo.f7888d.size() <= 0) {
            C5940x.m3825e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (!file.exists() || !file.canWrite()) {
                C5940x.m3825e("backup file create fail %s", str2);
                return false;
            }
            BufferedWriter bufferedWriter2 = null;
            try {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = readTargetDumpInfo.f7888d.get("main");
                        int i = 3;
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            String str5 = strArr[1];
                            bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + str5 + "\n\n");
                            bufferedWriter.flush();
                        }
                        for (Map.Entry<String, String[]> entry : readTargetDumpInfo.f7888d.entrySet()) {
                            if (!entry.getKey().equals("main")) {
                                if (entry.getValue() != null && entry.getValue().length >= i) {
                                    String str6 = entry.getValue()[0];
                                    String str7 = entry.getValue()[1];
                                    bufferedWriter.write("\"" + entry.getKey() + "\" tid=" + entry.getValue()[2] + " :\n" + str6 + "\n" + str7 + "\n\n");
                                    bufferedWriter.flush();
                                }
                                i = 3;
                            }
                        }
                        try {
                            bufferedWriter.close();
                        } catch (IOException e) {
                            if (!C5940x.m3819a(e)) {
                                e.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter2 = bufferedWriter;
                        if (!C5940x.m3819a(e)) {
                            e.printStackTrace();
                        }
                        C5940x.m3825e("dump trace fail %s", e.getClass().getName() + ":" + e.getMessage());
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e3) {
                                if (!C5940x.m3819a(e3)) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                                throw th2;
                            } catch (IOException e4) {
                                if (!C5940x.m3819a(e4)) {
                                    e4.printStackTrace();
                                    throw th2;
                                }
                                throw th2;
                            }
                        }
                        throw th2;
                    }
                } catch (IOException e5) {
                    e = e5;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedWriter = null;
            }
        } catch (Exception e6) {
            if (!C5940x.m3819a(e6)) {
                e6.printStackTrace();
            }
            C5940x.m3825e("backup file create error! %s  %s", e6.getClass().getName() + ":" + e6.getMessage(), str2);
            return false;
        }
    }

    /* renamed from: a */
    public final boolean m3521a() {
        return this.f7896a.get() != 0;
    }

    /* renamed from: a */
    private boolean m3510a(Context context, String str, ActivityManager.ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        File file = new File(context.getFilesDir(), "bugly/bugly_trace_" + j + ".txt");
        C5884a c5884a = new C5884a();
        c5884a.f7891c = j;
        c5884a.f7892d = file.getAbsolutePath();
        c5884a.f7889a = processErrorStateInfo != null ? processErrorStateInfo.processName : "";
        c5884a.f7894f = processErrorStateInfo != null ? processErrorStateInfo.shortMsg : "";
        c5884a.f7893e = processErrorStateInfo != null ? processErrorStateInfo.longMsg : "";
        c5884a.f7890b = map;
        if (map != null) {
            for (String str2 : map.keySet()) {
                if (str2.startsWith("main(")) {
                    c5884a.f7895g = map.get(str2);
                }
            }
        }
        Object[] objArr = new Object[6];
        objArr[0] = Long.valueOf(c5884a.f7891c);
        objArr[1] = c5884a.f7892d;
        objArr[2] = c5884a.f7889a;
        objArr[3] = c5884a.f7894f;
        objArr[4] = c5884a.f7893e;
        objArr[5] = Integer.valueOf(c5884a.f7890b == null ? 0 : c5884a.f7890b.size());
        C5940x.m3823c("anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d", objArr);
        if (!this.f7901f.m3496b()) {
            C5940x.m3825e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new Object[0]);
            C5886b.m3531a("ANR", C5942z.m3852a(), c5884a.f7889a, "main", c5884a.f7893e, null);
            return false;
        }
        if (!this.f7901f.m3497c().f7793j) {
            C5940x.m3824d("ANR Report is closed!", new Object[0]);
            return false;
        }
        C5940x.m3818a("found visiable anr , start to upload!", new Object[0]);
        CrashDetailBean m3509a = m3509a(c5884a);
        if (m3509a == null) {
            C5940x.m3825e("pack anr fail!", new Object[0]);
            return false;
        }
        C5887c.m3547a().m3553a(m3509a);
        if (m3509a.f7848a >= 0) {
            C5940x.m3818a("backup anr record success!", new Object[0]);
        } else {
            C5940x.m3824d("backup anr record fail!", new Object[0]);
        }
        if (str != null && new File(str).exists()) {
            this.f7896a.set(3);
            if (m3511a(str, c5884a.f7892d, c5884a.f7889a)) {
                C5940x.m3818a("backup trace success", new Object[0]);
            }
        }
        C5886b.m3531a("ANR", C5942z.m3852a(), c5884a.f7889a, "main", c5884a.f7893e, m3509a);
        if (!this.f7903h.m3542a(m3509a)) {
            this.f7903h.m3540a(m3509a, SolicitService.CAMERA_OPEN_TIME_OUT, true);
        }
        this.f7903h.m3544b(m3509a);
        return true;
    }

    /* renamed from: a */
    public final void m3519a(String str) {
        synchronized (this) {
            if (this.f7896a.get() != 0) {
                C5940x.m3823c("trace started return ", new Object[0]);
                return;
            }
            this.f7896a.set(1);
            try {
                C5940x.m3823c("read trace first dump for create time!", new Object[0]);
                TraceFileHelper.C5882a readFirstDumpInfo = TraceFileHelper.readFirstDumpInfo(str, false);
                long j = readFirstDumpInfo != null ? readFirstDumpInfo.f7887c : -1L;
                if (j == -1) {
                    C5940x.m3824d("trace dump fail could not get time!", new Object[0]);
                    j = System.currentTimeMillis();
                }
                long j2 = j;
                if (Math.abs(j2 - this.f7897b) < 10000) {
                    C5940x.m3824d("should not process ANR too Fre in %d", 10000);
                } else {
                    this.f7897b = j2;
                    this.f7896a.set(1);
                    try {
                        Map<String, String> m3861a = C5942z.m3861a(C5887c.f7922f, false);
                        if (m3861a != null && m3861a.size() > 0) {
                            ActivityManager.ProcessErrorStateInfo m3508a = m3508a(this.f7898c, 10000L);
                            if (m3508a == null) {
                                C5940x.m3823c("proc state is unvisiable!", new Object[0]);
                            } else if (m3508a.pid != Process.myPid()) {
                                C5940x.m3823c("not mind proc!", m3508a.processName);
                            } else {
                                C5940x.m3818a("found visiable anr , start to process!", new Object[0]);
                                m3510a(this.f7898c, str, m3508a, j2, m3861a);
                            }
                        }
                        C5940x.m3824d("can't get all thread skip this anr", new Object[0]);
                    } catch (Throwable th) {
                        C5940x.m3819a(th);
                        C5940x.m3825e("get all thread stack fail!", new Object[0]);
                    }
                }
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    /* renamed from: e */
    private synchronized void m3514e() {
        if (m3516g()) {
            C5940x.m3824d("start when started!", new Object[0]);
            return;
        }
        this.f7904i = new FileObserver("/data/anr/", 8) { // from class: com.tencent.bugly.crashreport.crash.anr.b.1
            @Override // android.os.FileObserver
            public final void onEvent(int i, String str) {
                if (str == null) {
                    return;
                }
                String str2 = "/data/anr/" + str;
                if (!str2.contains(MqttServiceConstants.TRACE_ACTION)) {
                    C5940x.m3824d("not anr file %s", str2);
                } else {
                    C5885b.this.m3519a(str2);
                }
            }
        };
        try {
            this.f7904i.startWatching();
            C5940x.m3818a("start anr monitor!", new Object[0]);
            this.f7900e.m3812a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.2
                @Override // java.lang.Runnable
                public final void run() {
                    C5885b.this.m3523b();
                }
            });
        } catch (Throwable th) {
            this.f7904i = null;
            C5940x.m3824d("start anr monitor failed!", new Object[0]);
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: f */
    private synchronized void m3515f() {
        if (!m3516g()) {
            C5940x.m3824d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.f7904i.stopWatching();
            this.f7904i = null;
            C5940x.m3824d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            C5940x.m3824d("stop anr monitor failed!", new Object[0]);
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: g */
    private synchronized boolean m3516g() {
        return this.f7904i != null;
    }

    /* renamed from: b */
    private synchronized void m3512b(boolean z) {
        if (z) {
            m3514e();
        } else {
            m3515f();
        }
    }

    /* renamed from: h */
    private synchronized boolean m3517h() {
        return this.f7905j;
    }

    /* renamed from: c */
    private synchronized void m3513c(boolean z) {
        if (this.f7905j != z) {
            C5940x.m3818a("user change anr %b", Boolean.valueOf(z));
            this.f7905j = z;
        }
    }

    /* renamed from: a */
    public final void m3520a(boolean z) {
        m3513c(z);
        boolean m3517h = m3517h();
        C5876a m3487a = C5876a.m3487a();
        if (m3487a != null) {
            m3517h = m3517h && m3487a.m3497c().f7790g;
        }
        if (m3517h != m3516g()) {
            C5940x.m3818a("anr changed to %b", Boolean.valueOf(m3517h));
            m3512b(m3517h);
        }
    }

    /* renamed from: b */
    protected final void m3523b() {
        long m3876b = C5942z.m3876b() - C5887c.f7923g;
        File file = new File(this.f7902g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    int i = 0;
                    for (File file2 : listFiles) {
                        String name = file2.getName();
                        if (name.startsWith("bugly_trace_")) {
                            try {
                                int indexOf = name.indexOf(".txt");
                                if (indexOf > 0 && Long.parseLong(name.substring(12, indexOf)) >= m3876b) {
                                }
                            } catch (Throwable unused) {
                                C5940x.m3823c("Trace file that has invalid format: " + name, new Object[0]);
                            }
                            if (file2.delete()) {
                                i++;
                            }
                        }
                    }
                    C5940x.m3823c("Number of overdue trace files that has deleted: " + i, new Object[0]);
                }
            } catch (Throwable th) {
                C5940x.m3819a(th);
            }
        }
    }

    /* renamed from: a */
    public final synchronized void m3518a(StrategyBean strategyBean) {
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.f7793j != m3516g()) {
            C5940x.m3824d("server anr changed to %b", Boolean.valueOf(strategyBean.f7793j));
        }
        if (Build.VERSION.SDK_INT <= 19) {
            boolean z = strategyBean.f7793j && m3517h();
            if (z != m3516g()) {
                C5940x.m3818a("anr changed to %b", Boolean.valueOf(z));
                m3512b(z);
            }
            return;
        }
        if (strategyBean.f7793j) {
            m3524c();
        } else {
            m3525d();
        }
    }

    @Override // com.tencent.bugly.proguard.InterfaceC5899ac
    /* renamed from: a */
    public final boolean mo3522a(Thread thread) {
        new HashMap();
        if (thread.getName().contains("main")) {
            ActivityManager.ProcessErrorStateInfo m3508a = m3508a(this.f7898c, 10000L);
            if (m3508a == null) {
                C5940x.m3823c("anr handler onThreadBlock proc state is unvisiable!", new Object[0]);
                return false;
            }
            if (m3508a.pid != Process.myPid()) {
                C5940x.m3823c("onThreadBlock not mind proc!", m3508a.processName);
                return false;
            }
            try {
                Map<String, String> m3861a = C5942z.m3861a(200000, false);
                C5940x.m3818a("onThreadBlock found visiable anr , start to process!", new Object[0]);
                m3510a(this.f7898c, "", m3508a, System.currentTimeMillis(), m3861a);
            } catch (Throwable unused) {
                return false;
            }
        } else {
            C5940x.m3823c("anr handler onThreadBlock only care main thread", new Object[0]);
        }
        return true;
    }

    /* renamed from: c */
    public final void m3524c() {
        C5898ab.m3634a(this.f7898c).m3637a();
        C5898ab.m3634a(this.f7898c).m3638a(this);
        C5898ab.m3634a(this.f7898c).m3642d();
    }

    /* renamed from: d */
    public final void m3525d() {
        C5898ab.m3634a(this.f7898c).m3639b();
        C5898ab.m3634a(this.f7898c).m3640b(this);
        C5898ab.m3634a(this.f7898c).m3641c();
    }
}
