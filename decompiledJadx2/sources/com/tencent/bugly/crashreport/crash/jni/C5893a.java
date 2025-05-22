package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.pudutech.mirsdk.SolicitService;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.crash.C5886b;
import com.tencent.bugly.crashreport.crash.C5887c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5941y;
import com.tencent.bugly.proguard.C5942z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.jni.a */
/* loaded from: classes7.dex */
public final class C5893a implements NativeExceptionHandler {

    /* renamed from: a */
    private final Context f8005a;

    /* renamed from: b */
    private final C5886b f8006b;

    /* renamed from: c */
    private final C5873a f8007c;

    /* renamed from: d */
    private final C5876a f8008d;

    public C5893a(Context context, C5873a c5873a, C5886b c5886b, C5876a c5876a) {
        this.f8005a = context;
        this.f8006b = c5886b;
        this.f8007c = c5873a;
        this.f8008d = c5876a;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int indexOf;
        boolean m3565k = C5887c.m3547a().m3565k();
        if (m3565k) {
            C5940x.m3825e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f7849b = 1;
        crashDetailBean.f7852e = this.f8007c.m3430h();
        crashDetailBean.f7853f = this.f8007c.f7762j;
        crashDetailBean.f7854g = this.f8007c.m3445w();
        crashDetailBean.f7860m = this.f8007c.m3428g();
        crashDetailBean.f7861n = str3;
        crashDetailBean.f7862o = m3565k ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.f7863p = str4;
        crashDetailBean.f7864q = str5 != null ? str5 : "";
        crashDetailBean.f7865r = j;
        crashDetailBean.f7868u = C5942z.m3879b(crashDetailBean.f7864q.getBytes());
        crashDetailBean.f7825A = str;
        crashDetailBean.f7826B = str2;
        crashDetailBean.f7833I = this.f8007c.m3447y();
        crashDetailBean.f7855h = this.f8007c.m3444v();
        crashDetailBean.f7856i = this.f8007c.m3401J();
        crashDetailBean.f7869v = str8;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        String dumpFilePath = nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null;
        String m3603a = C5894b.m3603a(dumpFilePath, str8);
        if (!C5942z.m3868a(m3603a)) {
            crashDetailBean.f7845U = m3603a;
        }
        crashDetailBean.f7846V = C5894b.m3605b(dumpFilePath);
        crashDetailBean.f7870w = C5894b.m3602a(str9, C5887c.f7921e, null, false);
        crashDetailBean.f7871x = C5894b.m3602a(str10, C5887c.f7921e, null, true);
        crashDetailBean.f7834J = str7;
        crashDetailBean.f7835K = str6;
        crashDetailBean.f7836L = str11;
        crashDetailBean.f7830F = this.f8007c.m3438p();
        crashDetailBean.f7831G = this.f8007c.m3437o();
        crashDetailBean.f7832H = this.f8007c.m3439q();
        if (z) {
            crashDetailBean.f7827C = C5874b.m3470k();
            crashDetailBean.f7828D = C5874b.m3466i();
            crashDetailBean.f7829E = C5874b.m3474m();
            if (crashDetailBean.f7870w == null) {
                crashDetailBean.f7870w = C5942z.m3854a(this.f8005a, C5887c.f7921e, (String) null);
            }
            crashDetailBean.f7872y = C5941y.m3832a();
            crashDetailBean.f7837M = this.f8007c.f7728a;
            crashDetailBean.f7838N = this.f8007c.m3415a();
            crashDetailBean.f7840P = this.f8007c.m3399H();
            crashDetailBean.f7841Q = this.f8007c.m3400I();
            crashDetailBean.f7842R = this.f8007c.m3393B();
            crashDetailBean.f7843S = this.f8007c.m3398G();
            crashDetailBean.f7873z = C5942z.m3861a(C5887c.f7922f, false);
            int indexOf2 = crashDetailBean.f7864q.indexOf("java:\n");
            if (indexOf2 > 0 && (i = indexOf2 + 6) < crashDetailBean.f7864q.length()) {
                String substring = crashDetailBean.f7864q.substring(i, crashDetailBean.f7864q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.f7873z.containsKey(crashDetailBean.f7826B) && (indexOf = (str12 = crashDetailBean.f7873z.get(crashDetailBean.f7826B)).indexOf(substring)) > 0) {
                    String substring2 = str12.substring(indexOf);
                    crashDetailBean.f7873z.put(crashDetailBean.f7826B, substring2);
                    crashDetailBean.f7864q = crashDetailBean.f7864q.substring(0, i);
                    crashDetailBean.f7864q += substring2;
                }
            }
            if (str == null) {
                crashDetailBean.f7825A = this.f8007c.f7756d;
            }
            this.f8006b.m3545c(crashDetailBean);
        } else {
            crashDetailBean.f7827C = -1L;
            crashDetailBean.f7828D = -1L;
            crashDetailBean.f7829E = -1L;
            if (crashDetailBean.f7870w == null) {
                crashDetailBean.f7870w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.f7837M = -1L;
            crashDetailBean.f7840P = -1;
            crashDetailBean.f7841Q = -1;
            crashDetailBean.f7842R = map;
            crashDetailBean.f7843S = this.f8007c.m3398G();
            crashDetailBean.f7873z = null;
            if (str == null) {
                crashDetailBean.f7825A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.f7872y = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        C5940x.m3818a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0124 A[Catch: all -> 0x029f, TryCatch #2 {all -> 0x029f, blocks: (B:3:0x0012, B:6:0x001e, B:7:0x0070, B:10:0x0078, B:12:0x007b, B:14:0x007f, B:16:0x009a, B:19:0x00a3, B:18:0x00ad, B:23:0x00b7, B:25:0x00c1, B:27:0x00c9, B:28:0x00d5, B:30:0x00df, B:33:0x00e6, B:34:0x00f5, B:36:0x0101, B:39:0x0108, B:40:0x011e, B:42:0x0124, B:45:0x0134, B:47:0x0154, B:49:0x0193, B:51:0x01b6, B:52:0x01bd, B:55:0x01c9, B:57:0x01d1, B:94:0x016f, B:95:0x00f1, B:97:0x00b0, B:100:0x0044, B:101:0x004a, B:103:0x0054), top: B:2:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0154 A[Catch: all -> 0x029f, TryCatch #2 {all -> 0x029f, blocks: (B:3:0x0012, B:6:0x001e, B:7:0x0070, B:10:0x0078, B:12:0x007b, B:14:0x007f, B:16:0x009a, B:19:0x00a3, B:18:0x00ad, B:23:0x00b7, B:25:0x00c1, B:27:0x00c9, B:28:0x00d5, B:30:0x00df, B:33:0x00e6, B:34:0x00f5, B:36:0x0101, B:39:0x0108, B:40:0x011e, B:42:0x0124, B:45:0x0134, B:47:0x0154, B:49:0x0193, B:51:0x01b6, B:52:0x01bd, B:55:0x01c9, B:57:0x01d1, B:94:0x016f, B:95:0x00f1, B:97:0x00b0, B:100:0x0044, B:101:0x004a, B:103:0x0054), top: B:2:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01b6 A[Catch: all -> 0x029f, TryCatch #2 {all -> 0x029f, blocks: (B:3:0x0012, B:6:0x001e, B:7:0x0070, B:10:0x0078, B:12:0x007b, B:14:0x007f, B:16:0x009a, B:19:0x00a3, B:18:0x00ad, B:23:0x00b7, B:25:0x00c1, B:27:0x00c9, B:28:0x00d5, B:30:0x00df, B:33:0x00e6, B:34:0x00f5, B:36:0x0101, B:39:0x0108, B:40:0x011e, B:42:0x0124, B:45:0x0134, B:47:0x0154, B:49:0x0193, B:51:0x01b6, B:52:0x01bd, B:55:0x01c9, B:57:0x01d1, B:94:0x016f, B:95:0x00f1, B:97:0x00b0, B:100:0x0044, B:101:0x004a, B:103:0x0054), top: B:2:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01c9 A[Catch: all -> 0x029f, TRY_ENTER, TryCatch #2 {all -> 0x029f, blocks: (B:3:0x0012, B:6:0x001e, B:7:0x0070, B:10:0x0078, B:12:0x007b, B:14:0x007f, B:16:0x009a, B:19:0x00a3, B:18:0x00ad, B:23:0x00b7, B:25:0x00c1, B:27:0x00c9, B:28:0x00d5, B:30:0x00df, B:33:0x00e6, B:34:0x00f5, B:36:0x0101, B:39:0x0108, B:40:0x011e, B:42:0x0124, B:45:0x0134, B:47:0x0154, B:49:0x0193, B:51:0x01b6, B:52:0x01bd, B:55:0x01c9, B:57:0x01d1, B:94:0x016f, B:95:0x00f1, B:97:0x00b0, B:100:0x0044, B:101:0x004a, B:103:0x0054), top: B:2:0x0012 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0231 A[Catch: all -> 0x029b, TryCatch #1 {all -> 0x029b, blocks: (B:63:0x022b, B:65:0x0231, B:67:0x023a), top: B:62:0x022b }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x023a A[Catch: all -> 0x029b, TRY_LEAVE, TryCatch #1 {all -> 0x029b, blocks: (B:63:0x022b, B:65:0x0231, B:67:0x023a), top: B:62:0x022b }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0150 A[SYNTHETIC] */
    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        String str10;
        boolean z;
        String str11;
        String str12;
        String str13;
        CrashDetailBean packageCrashDatas;
        Iterator<Thread> it;
        String str14;
        boolean z2;
        C5940x.m3818a("Native Crash Happen v2", new Object[0]);
        try {
            String m3601a = C5894b.m3601a(str3);
            String str15 = "UNKNOWN";
            if (i3 > 0) {
                str9 = str + "(" + str5 + ")";
                str8 = "UNKNOWN";
                str10 = "KERNEL";
            } else {
                if (i4 > 0) {
                    Context context = this.f8005a;
                    str15 = AppInfo.m3375a(i4);
                }
                str8 = str15.equals(String.valueOf(i4)) ? str15 : str15 + "(" + i4 + ")";
                str9 = str;
                str10 = str5;
            }
            HashMap hashMap = new HashMap();
            if (strArr != null) {
                for (int i7 = 0; i7 < strArr.length; i7++) {
                    String str16 = strArr[i7];
                    if (str16 != null) {
                        C5940x.m3818a("Extra message[%d]: %s", Integer.valueOf(i7), str16);
                        String[] split = str16.split("=");
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            C5940x.m3824d("bad extraMsg %s", str16);
                        }
                    }
                }
            } else {
                C5940x.m3823c("not found extraMsg", new Object[0]);
            }
            String str17 = (String) hashMap.get("HasPendingException");
            if (str17 == null || !str17.equals("true")) {
                z = false;
            } else {
                C5940x.m3818a("Native crash happened with a Java pending exception.", new Object[0]);
                z = true;
            }
            String str18 = (String) hashMap.get("ExceptionProcessName");
            try {
                if (str18 != null && str18.length() != 0) {
                    C5940x.m3823c("Name of crash process: %s", str18);
                    String str19 = str18;
                    str11 = (String) hashMap.get("ExceptionThreadName");
                    if (str11 != null && str11.length() != 0) {
                        C5940x.m3823c("Name of crash thread: %s", str11);
                        it = Thread.getAllStackTraces().keySet().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                str14 = str11;
                                z2 = false;
                                break;
                            }
                            Thread next = it.next();
                            if (next.getName().equals(str11)) {
                                str14 = str11 + "(" + next.getId() + ")";
                                z2 = true;
                                break;
                            }
                        }
                        if (z2) {
                            str12 = str14 + "(" + i2 + ")";
                            str13 = str12;
                            long j3 = (j * 1000) + (j2 / 1000);
                            String str20 = (String) hashMap.get("SysLogPath");
                            String str21 = (String) hashMap.get("JniLogPath");
                            if (!this.f8008d.m3496b()) {
                                C5940x.m3824d("no remote but still store!", new Object[0]);
                            }
                            if (this.f8008d.m3497c().f7790g && this.f8008d.m3496b()) {
                                C5940x.m3825e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                                C5886b.m3531a("NATIVE_CRASH", C5942z.m3852a(), str19, str13, str9 + "\n" + str2 + "\n" + m3601a, null);
                                C5942z.m3883b(str4);
                                return;
                            }
                            String str22 = str9;
                            packageCrashDatas = packageCrashDatas(str19, str13, j3, str9, str2, m3601a, str10, str8, str4, str20, str21, str7, null, null, true, z);
                            if (packageCrashDatas != null) {
                                C5940x.m3825e("pkg crash datas fail!", new Object[0]);
                                return;
                            }
                            C5886b.m3531a("NATIVE_CRASH", C5942z.m3852a(), str19, str13, str22 + "\n" + str2 + "\n" + m3601a, packageCrashDatas);
                            try {
                                boolean z3 = this.f8006b.m3543a(packageCrashDatas, i3) ? false : true;
                                NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                                C5894b.m3604a(true, nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null);
                                if (z3) {
                                    this.f8006b.m3540a(packageCrashDatas, SolicitService.CAMERA_OPEN_TIME_OUT, true);
                                }
                                this.f8006b.m3544b(packageCrashDatas);
                                return;
                            } catch (Throwable th) {
                                th = th;
                                if (C5940x.m3819a(th)) {
                                    return;
                                }
                                th.printStackTrace();
                                return;
                            }
                        }
                        str13 = str14;
                        long j32 = (j * 1000) + (j2 / 1000);
                        String str202 = (String) hashMap.get("SysLogPath");
                        String str212 = (String) hashMap.get("JniLogPath");
                        if (!this.f8008d.m3496b()) {
                        }
                        if (this.f8008d.m3497c().f7790g) {
                        }
                        String str222 = str9;
                        packageCrashDatas = packageCrashDatas(str19, str13, j32, str9, str2, m3601a, str10, str8, str4, str202, str212, str7, null, null, true, z);
                        if (packageCrashDatas != null) {
                        }
                    }
                    Thread currentThread = Thread.currentThread();
                    str12 = currentThread.getName() + "(" + currentThread.getId() + ")";
                    str13 = str12;
                    long j322 = (j * 1000) + (j2 / 1000);
                    String str2022 = (String) hashMap.get("SysLogPath");
                    String str2122 = (String) hashMap.get("JniLogPath");
                    if (!this.f8008d.m3496b()) {
                    }
                    if (this.f8008d.m3497c().f7790g) {
                    }
                    String str2222 = str9;
                    packageCrashDatas = packageCrashDatas(str19, str13, j322, str9, str2, m3601a, str10, str8, str4, str2022, str2122, str7, null, null, true, z);
                    if (packageCrashDatas != null) {
                    }
                }
                packageCrashDatas = packageCrashDatas(str19, str13, j322, str9, str2, m3601a, str10, str8, str4, str2022, str2122, str7, null, null, true, z);
                if (packageCrashDatas != null) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
            str18 = this.f8007c.f7756d;
            String str192 = str18;
            str11 = (String) hashMap.get("ExceptionThreadName");
            if (str11 != null) {
                C5940x.m3823c("Name of crash thread: %s", str11);
                it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                    }
                }
                if (z2) {
                }
            }
            Thread currentThread2 = Thread.currentThread();
            str12 = currentThread2.getName() + "(" + currentThread2.getId() + ")";
            str13 = str12;
            long j3222 = (j * 1000) + (j2 / 1000);
            String str20222 = (String) hashMap.get("SysLogPath");
            String str21222 = (String) hashMap.get("JniLogPath");
            if (!this.f8008d.m3496b()) {
            }
            if (this.f8008d.m3497c().f7790g) {
            }
            String str22222 = str9;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
