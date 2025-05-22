package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.crashreport.InterfaceC5868a;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C5886b;
import com.tencent.bugly.crashreport.crash.C5887c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C5939w;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class NativeCrashHandler implements InterfaceC5868a {

    /* renamed from: a */
    private static NativeCrashHandler f7989a = null;

    /* renamed from: l */
    private static boolean f7990l = false;

    /* renamed from: m */
    private static boolean f7991m = false;

    /* renamed from: o */
    private static boolean f7992o = true;

    /* renamed from: b */
    private final Context f7993b;

    /* renamed from: c */
    private final C5873a f7994c;

    /* renamed from: d */
    private final C5939w f7995d;

    /* renamed from: e */
    private NativeExceptionHandler f7996e;

    /* renamed from: f */
    private String f7997f;

    /* renamed from: g */
    private final boolean f7998g;

    /* renamed from: h */
    private boolean f7999h = false;

    /* renamed from: i */
    private boolean f8000i = false;

    /* renamed from: j */
    private boolean f8001j = false;

    /* renamed from: k */
    private boolean f8002k = false;

    /* renamed from: n */
    private C5886b f8003n;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    private NativeCrashHandler(Context context, C5873a c5873a, C5886b c5886b, C5939w c5939w, boolean z, String str) {
        this.f7993b = C5942z.m3846a(context);
        try {
            if (C5942z.m3868a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + C5873a.m3389a(context).f7755c + "/app_bugly";
        }
        this.f8003n = c5886b;
        this.f7997f = str;
        this.f7994c = c5873a;
        this.f7995d = c5939w;
        this.f7998g = z;
        this.f7996e = new C5893a(context, c5873a, c5886b, C5876a.m3487a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C5873a c5873a, C5886b c5886b, C5876a c5876a, C5939w c5939w, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f7989a == null) {
                f7989a = new NativeCrashHandler(context, c5873a, c5886b, c5939w, z, str);
            }
            nativeCrashHandler = f7989a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f7989a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f7997f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f7997f = str;
    }

    public static void setShouldHandleInJava(boolean z) {
        f7992o = z;
        NativeCrashHandler nativeCrashHandler = f7989a;
        if (nativeCrashHandler != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(z);
            nativeCrashHandler.m3587a(999, sb.toString());
        }
    }

    public static boolean isShouldHandleInJava() {
        return f7992o;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:40|(1:42)(14:65|(1:67)|44|45|(1:47)|48|(1:50)|52|(1:54)(1:63)|55|(1:57)(1:62)|58|59|60)|43|44|45|(0)|48|(0)|52|(0)(0)|55|(0)(0)|58|59|60) */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007c A[Catch: all -> 0x008a, TryCatch #1 {all -> 0x008a, blocks: (B:45:0x0072, B:47:0x007c, B:48:0x007e, B:50:0x0088), top: B:44:0x0072 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088 A[Catch: all -> 0x008a, TRY_LEAVE, TryCatch #1 {all -> 0x008a, blocks: (B:45:0x0072, B:47:0x007c, B:48:0x007e, B:50:0x0088), top: B:44:0x0072 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008e A[Catch: all -> 0x00bd, TryCatch #3 {all -> 0x00bd, blocks: (B:38:0x0015, B:40:0x001d, B:42:0x004f, B:43:0x0059, B:52:0x008a, B:54:0x008e, B:55:0x009d, B:57:0x00a1, B:58:0x00b0, B:62:0x00a9, B:63:0x0096, B:65:0x0061, B:67:0x0067), top: B:37:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1 A[Catch: all -> 0x00bd, TryCatch #3 {all -> 0x00bd, blocks: (B:38:0x0015, B:40:0x001d, B:42:0x004f, B:43:0x0059, B:52:0x008a, B:54:0x008e, B:55:0x009d, B:57:0x00a1, B:58:0x00b0, B:62:0x00a9, B:63:0x0096, B:65:0x0061, B:67:0x0067), top: B:37:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a9 A[Catch: all -> 0x00bd, TryCatch #3 {all -> 0x00bd, blocks: (B:38:0x0015, B:40:0x001d, B:42:0x004f, B:43:0x0059, B:52:0x008a, B:54:0x008e, B:55:0x009d, B:57:0x00a1, B:58:0x00b0, B:62:0x00a9, B:63:0x0096, B:65:0x0061, B:67:0x0067), top: B:37:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0096 A[Catch: all -> 0x00bd, TryCatch #3 {all -> 0x00bd, blocks: (B:38:0x0015, B:40:0x001d, B:42:0x004f, B:43:0x0059, B:52:0x008a, B:54:0x008e, B:55:0x009d, B:57:0x00a1, B:58:0x00b0, B:62:0x00a9, B:63:0x0096, B:65:0x0061, B:67:0x0067), top: B:37:0x0015, outer: #2 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized void m3586a(boolean z) {
        StringBuilder sb;
        String str;
        if (this.f8001j) {
            C5940x.m3824d("[Native] Native crash report has already registered.", new Object[0]);
            return;
        }
        if (this.f8000i) {
            try {
                String regist = regist(this.f7997f, z, 1);
                if (regist != null) {
                    C5940x.m3818a("[Native] Native Crash Report enable.", new Object[0]);
                    C5940x.m3823c("[Native] Check extra jni for Bugly NDK v%s", regist);
                    String replace = "2.1.1".replace(".", "");
                    String replace2 = "2.3.0".replace(".", "");
                    String replace3 = regist.replace(".", "");
                    if (replace3.length() != 2) {
                        if (replace3.length() == 1) {
                            sb = new StringBuilder();
                            sb.append(replace3);
                            str = "00";
                        }
                        if (Integer.parseInt(replace3) >= Integer.parseInt(replace)) {
                            f7990l = true;
                        }
                        if (Integer.parseInt(replace3) >= Integer.parseInt(replace2)) {
                            f7991m = true;
                        }
                        if (f7991m) {
                            C5940x.m3824d("[Native] Info setting jni can not be accessed.", new Object[0]);
                        } else {
                            C5940x.m3818a("[Native] Info setting jni can be accessed.", new Object[0]);
                        }
                        if (f7990l) {
                            C5940x.m3824d("[Native] Extra jni can not be accessed.", new Object[0]);
                        } else {
                            C5940x.m3818a("[Native] Extra jni can be accessed.", new Object[0]);
                        }
                        this.f7994c.f7766n = regist;
                        C5941y.m3831a(f7990l);
                        this.f8001j = true;
                        return;
                    }
                    sb = new StringBuilder();
                    sb.append(replace3);
                    str = "0";
                    sb.append(str);
                    replace3 = sb.toString();
                    if (Integer.parseInt(replace3) >= Integer.parseInt(replace)) {
                    }
                    if (Integer.parseInt(replace3) >= Integer.parseInt(replace2)) {
                    }
                    if (f7991m) {
                    }
                    if (f7990l) {
                    }
                    this.f7994c.f7766n = regist;
                    C5941y.m3831a(f7990l);
                    this.f8001j = true;
                    return;
                }
            } catch (Throwable unused) {
                C5940x.m3823c("[Native] Failed to load Bugly SO file.", new Object[0]);
            }
        } else if (this.f7999h) {
            try {
                Class[] clsArr = {String.class, String.class, Integer.TYPE, Integer.TYPE};
                Object[] objArr = new Object[4];
                objArr[0] = this.f7997f;
                objArr[1] = C5874b.m3451a(this.f7993b, false);
                objArr[2] = Integer.valueOf(z ? 1 : 5);
                objArr[3] = 1;
                String str2 = (String) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler2", null, clsArr, objArr);
                if (str2 == null) {
                    Class[] clsArr2 = {String.class, String.class, Integer.TYPE};
                    C5873a.m3390b();
                    str2 = (String) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, clsArr2, new Object[]{this.f7997f, C5874b.m3451a(this.f7993b, false), Integer.valueOf(C5873a.m3388K())});
                }
                if (str2 != null) {
                    this.f8001j = true;
                    C5873a.m3390b().f7766n = str2;
                    Boolean bool = (Boolean) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "checkExtraJni", null, new Class[]{String.class}, new Object[]{str2});
                    if (bool != null) {
                        boolean booleanValue = bool.booleanValue();
                        f7990l = booleanValue;
                        C5941y.m3831a(booleanValue);
                    }
                    C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{true});
                    C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(z ? 1 : 5)});
                    return;
                }
            } catch (Throwable unused2) {
            }
        }
        this.f8000i = false;
        this.f7999h = false;
    }

    public synchronized void startNativeMonitor() {
        if (!this.f8000i && !this.f7999h) {
            String str = "Bugly";
            boolean z = !C5942z.m3868a(this.f7994c.f7765m);
            String str2 = this.f7994c.f7765m;
            if (z) {
                str = str2;
            } else {
                this.f7994c.getClass();
            }
            this.f8000i = m3589a(str, z);
            if (this.f8000i || this.f7999h) {
                m3586a(this.f7998g);
                if (f7990l) {
                    setNativeAppVersion(this.f7994c.f7762j);
                    setNativeAppChannel(this.f7994c.f7764l);
                    setNativeAppPackage(this.f7994c.f7755c);
                    setNativeUserId(this.f7994c.m3428g());
                    setNativeIsAppForeground(this.f7994c.m3415a());
                    setNativeLaunchTime(this.f7994c.f7728a);
                }
                return;
            }
            return;
        }
        m3586a(this.f7998g);
    }

    public void checkUploadRecordCrash() {
        this.f7995d.m3812a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.1
            @Override // java.lang.Runnable
            public final void run() {
                if (C5942z.m3865a(NativeCrashHandler.this.f7993b, "native_record_lock", 10000L)) {
                    if (!NativeCrashHandler.f7992o) {
                        NativeCrashHandler.this.m3587a(999, "false");
                    }
                    CrashDetailBean m3598a = C5894b.m3598a(NativeCrashHandler.this.f7993b, NativeCrashHandler.this.f7997f, NativeCrashHandler.this.f7996e);
                    if (m3598a != null) {
                        C5940x.m3818a("[Native] Get crash from native record.", new Object[0]);
                        if (!NativeCrashHandler.this.f8003n.m3542a(m3598a)) {
                            NativeCrashHandler.this.f8003n.m3540a(m3598a, SolicitService.CAMERA_OPEN_TIME_OUT, false);
                        }
                        C5894b.m3604a(false, NativeCrashHandler.this.f7997f);
                    }
                    NativeCrashHandler.this.m3597a();
                    C5942z.m3884b(NativeCrashHandler.this.f7993b, "native_record_lock");
                    return;
                }
                C5940x.m3818a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
            }
        });
    }

    /* renamed from: a */
    private static boolean m3589a(String str, boolean z) {
        boolean z2;
        try {
            C5940x.m3818a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C5940x.m3818a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
                C5940x.m3824d(th.getMessage(), new Object[0]);
                C5940x.m3824d("[Native] Failed to load so: %s", str);
                return z2;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    /* renamed from: c */
    private synchronized void m3594c() {
        if (!this.f8001j) {
            C5940x.m3824d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
            if (unregist() != null) {
                C5940x.m3818a("[Native] Successfully closed native crash report.", new Object[0]);
                this.f8001j = false;
                return;
            }
        } catch (Throwable unused) {
            C5940x.m3823c("[Native] Failed to close native crash report.", new Object[0]);
        }
        try {
            C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.f8001j = false;
            C5940x.m3818a("[Native] Successfully closed native crash report.", new Object[0]);
        } catch (Throwable unused2) {
            C5940x.m3823c("[Native] Failed to close native crash report.", new Object[0]);
            this.f8000i = false;
            this.f7999h = false;
        }
    }

    public void testNativeCrash() {
        if (!this.f8000i) {
            C5940x.m3824d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        m3587a(16, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z2);
        m3587a(17, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z3);
        m3587a(18, sb3.toString());
        testNativeCrash();
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f7996e;
    }

    /* renamed from: a */
    protected final void m3597a() {
        long m3876b = C5942z.m3876b() - C5887c.f7923g;
        long m3876b2 = C5942z.m3876b() + 86400000;
        File file = new File(this.f7997f);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    int i = 0;
                    int i2 = 0;
                    for (File file2 : listFiles) {
                        long lastModified = file2.lastModified();
                        if (lastModified < m3876b || lastModified >= m3876b2) {
                            C5940x.m3818a("[Native] Delete record file: %s", file2.getAbsolutePath());
                            i++;
                            if (file2.delete()) {
                                i2++;
                            }
                        }
                    }
                    C5940x.m3823c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i), Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                C5940x.m3819a(th);
            }
        }
    }

    public void removeEmptyNativeRecordFiles() {
        C5894b.m3608c(this.f7997f);
    }

    /* renamed from: b */
    private synchronized void m3591b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m3594c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f8002k;
    }

    /* renamed from: c */
    private synchronized void m3595c(boolean z) {
        if (this.f8002k != z) {
            C5940x.m3818a("user change native %b", Boolean.valueOf(z));
            this.f8002k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        m3595c(z);
        boolean isUserOpened = isUserOpened();
        C5876a m3487a = C5876a.m3487a();
        if (m3487a != null) {
            isUserOpened = isUserOpened && m3487a.m3497c().f7790g;
        }
        if (isUserOpened != this.f8001j) {
            C5940x.m3818a("native changed to %b", Boolean.valueOf(isUserOpened));
            m3591b(isUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f7790g != this.f8001j) {
                C5940x.m3824d("server native changed to %b", Boolean.valueOf(strategyBean.f7790g));
            }
        }
        boolean z = C5876a.m3487a().m3497c().f7790g && this.f8002k;
        if (z != this.f8001j) {
            C5940x.m3818a("native changed to %b", Boolean.valueOf(z));
            m3591b(z);
        }
    }

    @Override // com.tencent.bugly.crashreport.InterfaceC5868a
    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((this.f7999h || this.f8000i) && f7990l && str != null && str2 != null && str3 != null) {
            try {
                if (this.f8000i) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f7990l = false;
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    @Override // com.tencent.bugly.crashreport.InterfaceC5868a
    public String getLogFromNative() {
        if ((!this.f7999h && !this.f8000i) || !f7990l) {
            return null;
        }
        try {
            if (this.f8000i) {
                return getNativeLog();
            }
            return (String) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", null, null, null);
        } catch (UnsatisfiedLinkError unused) {
            f7990l = false;
            return null;
        } catch (Throwable th) {
            if (!C5940x.m3819a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.f7999h || this.f8000i) && f7990l && str != null && str2 != null) {
            try {
                if (this.f8000i) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) C5942z.m3850a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                f7990l = false;
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m3587a(int i, String str) {
        if (this.f8000i && f7991m) {
            try {
                setNativeInfo(i, str);
                return true;
            } catch (UnsatisfiedLinkError unused) {
                f7991m = false;
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public boolean filterSigabrtSysLog() {
        return m3587a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return m3587a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m3587a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m3587a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m3587a(11, str);
    }

    @Override // com.tencent.bugly.crashreport.InterfaceC5868a
    public boolean setNativeIsAppForeground(boolean z) {
        return m3587a(14, z ? "true" : "false");
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m3587a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (C5940x.m3819a(e)) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }
}
