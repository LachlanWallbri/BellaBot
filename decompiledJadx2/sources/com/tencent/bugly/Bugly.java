package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.proguard.C5932p;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import com.tencent.bugly.proguard.InterfaceC5931o;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class Bugly {
    public static final String SDK_IS_DEV = "false";

    /* renamed from: a */
    private static boolean f7618a = false;
    public static Context applicationContext = null;

    /* renamed from: b */
    private static String[] f7619b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};

    /* renamed from: c */
    private static String[] f7620c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            if (f7618a) {
                return;
            }
            f7618a = true;
            Context m3846a = C5942z.m3846a(context);
            applicationContext = m3846a;
            if (m3846a == null) {
                Log.e(C5940x.f8272a, "init arg 'context' should not be null!");
                return;
            }
            if (isDev()) {
                f7619b = f7620c;
            }
            for (String str2 : f7619b) {
                try {
                    if (str2.equals("BuglyCrashModule")) {
                        C5865b.m3334a(CrashModule.getInstance());
                    } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                        str2.equals("BuglyFeedbackModule");
                    }
                } catch (Throwable th) {
                    C5940x.m3822b(th);
                }
            }
            C5865b.f7642a = enable;
            C5865b.m3333a(applicationContext, str, z, buglyStrategy);
        }
    }

    public static synchronized String getAppChannel() {
        byte[] bArr;
        synchronized (Bugly.class) {
            C5873a m3390b = C5873a.m3390b();
            if (m3390b == null) {
                return null;
            }
            if (TextUtils.isEmpty(m3390b.f7764l)) {
                C5932p m3740a = C5932p.m3740a();
                if (m3740a == null) {
                    return m3390b.f7764l;
                }
                Map<String, byte[]> m3758a = m3740a.m3758a(556, (InterfaceC5931o) null, true);
                if (m3758a != null && (bArr = m3758a.get("app_channel")) != null) {
                    return new String(bArr);
                }
            }
            return m3390b.f7764l;
        }
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean("false".replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
