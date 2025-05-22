package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.biz.C5871b;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.proguard.C5930n;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5937u;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.b */
/* loaded from: classes7.dex */
public final class C5865b {

    /* renamed from: a */
    public static boolean f7642a = true;

    /* renamed from: b */
    public static List<AbstractC5864a> f7643b = new ArrayList();

    /* renamed from: c */
    public static boolean f7644c;

    /* renamed from: d */
    private static C5932p f7645d;

    /* renamed from: e */
    private static boolean f7646e;

    /* renamed from: a */
    private static boolean m3335a(C5873a c5873a) {
        List<String> list = c5873a.f7767o;
        c5873a.getClass();
        return list != null && list.contains("bugly");
    }

    /* renamed from: a */
    public static synchronized void m3331a(Context context) {
        synchronized (C5865b.class) {
            m3332a(context, null);
        }
    }

    /* renamed from: a */
    public static synchronized void m3332a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (C5865b.class) {
            if (f7646e) {
                C5940x.m3824d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(C5940x.f8272a, "[init] context of init() is null, check it.");
                return;
            }
            C5873a m3389a = C5873a.m3389a(context);
            if (m3335a(m3389a)) {
                f7642a = false;
                return;
            }
            String m3426f = m3389a.m3426f();
            if (m3426f == null) {
                Log.e(C5940x.f8272a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
            } else {
                m3333a(context, m3426f, m3389a.f7773u, buglyStrategy);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m3333a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        byte[] bArr;
        synchronized (C5865b.class) {
            if (f7646e) {
                C5940x.m3824d("[init] initial Multi-times, ignore this.", new Object[0]);
                return;
            }
            if (context == null) {
                Log.w(C5940x.f8272a, "[init] context is null, check it.");
                return;
            }
            if (str == null) {
                Log.e(C5940x.f8272a, "init arg 'crashReportAppID' should not be null!");
                return;
            }
            f7646e = true;
            if (z) {
                f7644c = true;
                C5940x.f8273b = true;
                C5940x.m3824d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                C5940x.m3825e("--------------------------------------------------------------------------------------------", new Object[0]);
                C5940x.m3824d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                C5940x.m3824d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                C5940x.m3824d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                C5940x.m3824d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                C5940x.m3825e("--------------------------------------------------------------------------------------------", new Object[0]);
                C5940x.m3821b("[init] Open debug mode of Bugly.", new Object[0]);
            }
            C5940x.m3818a("[init] Bugly version: v%s", "3.0.0");
            C5940x.m3818a(" crash report start initializing...", new Object[0]);
            C5940x.m3821b("[init] Bugly start initializing...", new Object[0]);
            C5940x.m3818a("[init] Bugly complete version: v%s", "3.0.0");
            Context m3846a = C5942z.m3846a(context);
            C5873a m3389a = C5873a.m3389a(m3846a);
            m3389a.m3442t();
            C5941y.m3828a(m3846a);
            f7645d = C5932p.m3741a(m3846a, f7643b);
            C5937u.m3774a(m3846a);
            C5876a m3488a = C5876a.m3488a(m3846a, f7643b);
            C5930n m3719a = C5930n.m3719a(m3846a);
            if (m3335a(m3389a)) {
                f7642a = false;
                return;
            }
            m3389a.m3412a(str);
            C5940x.m3818a("[param] Set APP ID:%s", str);
            if (buglyStrategy != null) {
                String appVersion = buglyStrategy.getAppVersion();
                if (!TextUtils.isEmpty(appVersion)) {
                    if (appVersion.length() > 100) {
                        String substring = appVersion.substring(0, 100);
                        C5940x.m3824d("appVersion %s length is over limit %d substring to %s", appVersion, 100, substring);
                        appVersion = substring;
                    }
                    m3389a.f7762j = appVersion;
                    C5940x.m3818a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                }
                try {
                    if (buglyStrategy.isReplaceOldChannel()) {
                        String appChannel = buglyStrategy.getAppChannel();
                        if (!TextUtils.isEmpty(appChannel)) {
                            if (appChannel.length() > 100) {
                                String substring2 = appChannel.substring(0, 100);
                                C5940x.m3824d("appChannel %s length is over limit %d substring to %s", appChannel, 100, substring2);
                                appChannel = substring2;
                            }
                            f7645d.m3761a(556, "app_channel", appChannel.getBytes(), (InterfaceC5931o) null, false);
                            m3389a.f7764l = appChannel;
                        }
                    } else {
                        Map<String, byte[]> m3758a = f7645d.m3758a(556, (InterfaceC5931o) null, true);
                        if (m3758a != null && (bArr = m3758a.get("app_channel")) != null) {
                            m3389a.f7764l = new String(bArr);
                        }
                    }
                    C5940x.m3818a("[param] Set App channel: %s", m3389a.f7764l);
                } catch (Exception e) {
                    if (f7644c) {
                        e.printStackTrace();
                    }
                }
                String appPackageName = buglyStrategy.getAppPackageName();
                if (!TextUtils.isEmpty(appPackageName)) {
                    if (appPackageName.length() > 100) {
                        String substring3 = appPackageName.substring(0, 100);
                        C5940x.m3824d("appPackageName %s length is over limit %d substring to %s", appPackageName, 100, substring3);
                        appPackageName = substring3;
                    }
                    m3389a.f7755c = appPackageName;
                    C5940x.m3818a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                }
                String deviceID = buglyStrategy.getDeviceID();
                if (deviceID != null) {
                    if (deviceID.length() > 100) {
                        String substring4 = deviceID.substring(0, 100);
                        C5940x.m3824d("deviceId %s length is over limit %d substring to %s", deviceID, 100, substring4);
                        deviceID = substring4;
                    }
                    m3389a.m3420c(deviceID);
                    C5940x.m3818a("[param] Set device ID: %s", deviceID);
                }
                m3389a.f7757e = buglyStrategy.isUploadProcess();
                C5941y.f8275a = buglyStrategy.isBuglyLogUpload();
            }
            C5871b.m3358a(m3846a, buglyStrategy);
            for (int i = 0; i < f7643b.size(); i++) {
                try {
                    if (m3719a.m3730a(f7643b.get(i).f7641id)) {
                        f7643b.get(i).init(m3846a, z, buglyStrategy);
                    }
                } catch (Throwable th) {
                    if (!C5940x.m3819a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            m3488a.m3493a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0L);
            C5940x.m3821b("[init] Bugly initialization finished.", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized void m3334a(AbstractC5864a abstractC5864a) {
        synchronized (C5865b.class) {
            if (!f7643b.contains(abstractC5864a)) {
                f7643b.add(abstractC5864a);
            }
        }
    }
}
