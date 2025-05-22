package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import com.iflytek.speech.UtilityConfig;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.v */
/* loaded from: classes7.dex */
public final class RunnableC5938v implements Runnable {

    /* renamed from: a */
    private int f8249a;

    /* renamed from: b */
    private int f8250b;

    /* renamed from: c */
    private final Context f8251c;

    /* renamed from: d */
    private final int f8252d;

    /* renamed from: e */
    private final byte[] f8253e;

    /* renamed from: f */
    private final C5873a f8254f;

    /* renamed from: g */
    private final C5876a f8255g;

    /* renamed from: h */
    private final C5935s f8256h;

    /* renamed from: i */
    private final C5937u f8257i;

    /* renamed from: j */
    private final int f8258j;

    /* renamed from: k */
    private final InterfaceC5936t f8259k;

    /* renamed from: l */
    private final InterfaceC5936t f8260l;

    /* renamed from: m */
    private String f8261m;

    /* renamed from: n */
    private final String f8262n;

    /* renamed from: o */
    private final Map<String, String> f8263o;

    /* renamed from: p */
    private int f8264p;

    /* renamed from: q */
    private long f8265q;

    /* renamed from: r */
    private long f8266r;

    /* renamed from: s */
    private boolean f8267s;

    /* renamed from: t */
    private boolean f8268t;

    public RunnableC5938v(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC5936t interfaceC5936t, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, interfaceC5936t, z, 2, BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH, z2, null);
    }

    public RunnableC5938v(Context context, int i, int i2, byte[] bArr, String str, String str2, InterfaceC5936t interfaceC5936t, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.f8249a = 2;
        this.f8250b = BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH;
        this.f8261m = null;
        this.f8264p = 0;
        this.f8265q = 0L;
        this.f8266r = 0L;
        this.f8267s = true;
        this.f8268t = false;
        this.f8251c = context;
        this.f8254f = C5873a.m3389a(context);
        this.f8253e = bArr;
        this.f8255g = C5876a.m3487a();
        this.f8256h = C5935s.m3767a(context);
        this.f8257i = C5937u.m3773a();
        this.f8258j = i;
        this.f8261m = str;
        this.f8262n = str2;
        this.f8259k = interfaceC5936t;
        C5937u c5937u = this.f8257i;
        this.f8260l = null;
        this.f8267s = z;
        this.f8252d = i2;
        if (i3 > 0) {
            this.f8249a = i3;
        }
        if (i4 > 0) {
            this.f8250b = i4;
        }
        this.f8268t = z2;
        this.f8263o = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x002a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m3806a(C5913aq c5913aq, boolean z, int i, String str, int i2) {
        String str2;
        InterfaceC5936t interfaceC5936t;
        InterfaceC5936t interfaceC5936t2;
        int i3 = this.f8252d;
        if (i3 != 630) {
            if (i3 != 640) {
                if (i3 != 830) {
                    if (i3 != 840) {
                        str2 = String.valueOf(i3);
                        if (z) {
                            C5940x.m3818a("[Upload] Success: %s", str2);
                        } else {
                            C5940x.m3825e("[Upload] Failed to upload(%d) %s: %s", Integer.valueOf(i), str2, str);
                            if (this.f8267s) {
                                this.f8257i.m3797a(i2, (C5913aq) null);
                            }
                        }
                        if (this.f8265q + this.f8266r > 0) {
                            this.f8257i.m3798a(this.f8257i.m3792a(this.f8268t) + this.f8265q + this.f8266r, this.f8268t);
                        }
                        interfaceC5936t = this.f8259k;
                        if (interfaceC5936t != null) {
                            int i4 = this.f8252d;
                            long j = this.f8265q;
                            long j2 = this.f8266r;
                            interfaceC5936t.mo3353a(z);
                        }
                        interfaceC5936t2 = this.f8260l;
                        if (interfaceC5936t2 != null) {
                            int i5 = this.f8252d;
                            long j3 = this.f8265q;
                            long j4 = this.f8266r;
                            interfaceC5936t2.mo3353a(z);
                            return;
                        }
                        return;
                    }
                }
            }
            str2 = "userinfo";
            if (z) {
            }
            if (this.f8265q + this.f8266r > 0) {
            }
            interfaceC5936t = this.f8259k;
            if (interfaceC5936t != null) {
            }
            interfaceC5936t2 = this.f8260l;
            if (interfaceC5936t2 != null) {
            }
        }
        str2 = "crash";
        if (z) {
        }
        if (this.f8265q + this.f8266r > 0) {
        }
        interfaceC5936t = this.f8259k;
        if (interfaceC5936t != null) {
        }
        interfaceC5936t2 = this.f8260l;
        if (interfaceC5936t2 != null) {
        }
    }

    /* renamed from: a */
    private static boolean m3807a(C5913aq c5913aq, C5873a c5873a, C5876a c5876a) {
        if (c5913aq == null) {
            C5940x.m3824d("resp == null!", new Object[0]);
            return false;
        }
        if (c5913aq.f8098a != 0) {
            C5940x.m3825e("resp result error %d", Byte.valueOf(c5913aq.f8098a));
            return false;
        }
        try {
            if (!C5942z.m3868a(c5913aq.f8101d) && !C5873a.m3390b().m3431i().equals(c5913aq.f8101d)) {
                C5932p.m3740a().m3761a(C5876a.f7809a, "gateway", c5913aq.f8101d.getBytes("UTF-8"), (InterfaceC5931o) null, true);
                c5873a.m3423d(c5913aq.f8101d);
            }
            if (!C5942z.m3868a(c5913aq.f8103f) && !C5873a.m3390b().m3432j().equals(c5913aq.f8103f)) {
                C5932p.m3740a().m3761a(C5876a.f7809a, UtilityConfig.KEY_DEVICE_INFO, c5913aq.f8103f.getBytes("UTF-8"), (InterfaceC5931o) null, true);
                c5873a.m3425e(c5913aq.f8103f);
            }
        } catch (Throwable th) {
            C5940x.m3819a(th);
        }
        c5873a.f7761i = c5913aq.f8102e;
        if (c5913aq.f8099b == 510) {
            if (c5913aq.f8100c == null) {
                C5940x.m3825e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(c5913aq.f8099b));
                return false;
            }
            C5915as c5915as = (C5915as) C5896a.m3615a(c5913aq.f8100c, C5915as.class);
            if (c5915as == null) {
                C5940x.m3825e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(c5913aq.f8099b));
                return false;
            }
            c5876a.m3495a(c5915as);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x024c A[Catch: all -> 0x0487, TryCatch #1 {all -> 0x0487, blocks: (B:3:0x0007, B:5:0x0019, B:9:0x0027, B:12:0x002c, B:14:0x0040, B:16:0x0072, B:18:0x0085, B:20:0x0089, B:22:0x008d, B:25:0x0093, B:27:0x009b, B:29:0x00a7, B:31:0x00cd, B:32:0x00d2, B:34:0x00d6, B:36:0x0109, B:38:0x0115, B:40:0x011b, B:42:0x0127, B:44:0x012f, B:46:0x013b, B:48:0x014a, B:49:0x014e, B:51:0x0152, B:52:0x0156, B:53:0x015d, B:56:0x0165, B:58:0x017c, B:59:0x0189, B:61:0x019b, B:62:0x01a0, B:158:0x01d1, B:66:0x01e6, B:70:0x01f2, B:73:0x01f9, B:76:0x0201, B:80:0x024c, B:82:0x0278, B:83:0x0280, B:85:0x0286, B:87:0x02a7, B:100:0x02e5, B:102:0x02f0, B:103:0x0305, B:105:0x034d, B:107:0x0395, B:109:0x03a7, B:111:0x03aa, B:112:0x03b2, B:114:0x03b8, B:116:0x03d3, B:118:0x03df, B:120:0x03e7, B:122:0x03f3, B:124:0x03fa, B:126:0x0406, B:128:0x040e, B:130:0x041a, B:132:0x041e, B:133:0x0423, B:136:0x0438, B:138:0x044b, B:140:0x0457, B:142:0x0435, B:144:0x036b, B:147:0x020b, B:149:0x0211, B:150:0x0219, B:152:0x0227, B:153:0x0233, B:154:0x0240, B:161:0x0463, B:163:0x046f, B:165:0x047b), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        byte[] m3772a;
        Map<String, String> map;
        int i;
        String str;
        boolean z;
        long j;
        String str2 = "Bugly-Version";
        int i2 = 0;
        try {
            this.f8264p = 0;
            this.f8265q = 0L;
            this.f8266r = 0L;
            byte[] bArr = this.f8253e;
            if (C5874b.m3455c(this.f8251c) == null) {
                m3806a(null, false, 0, "network is not available", 0);
                return;
            }
            if (bArr != null && bArr.length != 0) {
                long m3792a = this.f8257i.m3792a(this.f8268t);
                int i3 = 2;
                if (bArr.length + m3792a >= 2097152) {
                    C5940x.m3825e("[Upload] Upload too much data, try next time: %d/%d", Long.valueOf(m3792a), 2097152L);
                    m3806a(null, false, 0, "over net consume: " + PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH + "K", 0);
                    return;
                }
                C5940x.m3823c("[Upload] Run upload task with cmd: %d", Integer.valueOf(this.f8252d));
                if (this.f8251c != null && this.f8254f != null && this.f8255g != null && this.f8256h != null) {
                    StrategyBean m3497c = this.f8255g.m3497c();
                    if (m3497c == null) {
                        m3806a(null, false, 0, "illegal local strategy", 0);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("prodId", this.f8254f.m3426f());
                    hashMap.put("bundleId", this.f8254f.f7755c);
                    hashMap.put("appVer", this.f8254f.f7762j);
                    if (this.f8263o != null) {
                        hashMap.putAll(this.f8263o);
                    }
                    if (this.f8267s) {
                        hashMap.put("cmd", Integer.toString(this.f8252d));
                        hashMap.put("platformId", Byte.toString((byte) 1));
                        this.f8254f.getClass();
                        hashMap.put("sdkVer", "3.0.0");
                        hashMap.put("strategylastUpdateTime", Long.toString(m3497c.f7799p));
                        if (!this.f8257i.m3799a(hashMap)) {
                            m3806a(null, false, 0, "failed to add security info to HTTP headers", 0);
                            return;
                        }
                        byte[] m3873a = C5942z.m3873a(bArr, 2);
                        if (m3873a == null) {
                            m3806a(null, false, 0, "failed to zip request body", 0);
                            return;
                        }
                        bArr = this.f8257i.m3800a(m3873a);
                        if (bArr == null) {
                            m3806a(null, false, 0, "failed to encrypt request body", 0);
                            return;
                        }
                    }
                    this.f8257i.m3794a(this.f8258j, System.currentTimeMillis());
                    if (this.f8259k != null) {
                        InterfaceC5936t interfaceC5936t = this.f8259k;
                        int i4 = this.f8252d;
                    }
                    if (this.f8260l != null) {
                        InterfaceC5936t interfaceC5936t2 = this.f8260l;
                        int i5 = this.f8252d;
                    }
                    String str3 = this.f8261m;
                    int i6 = -1;
                    int i7 = 0;
                    int i8 = 0;
                    while (true) {
                        int i9 = i7 + 1;
                        if (i7 < this.f8249a) {
                            if (i9 > 1) {
                                C5940x.m3824d("[Upload] Failed to upload last time, wait and try(%d) again.", Integer.valueOf(i9));
                                C5942z.m3881b(this.f8250b);
                                if (i9 == this.f8249a) {
                                    C5940x.m3824d("[Upload] Use the back-up url at the last time: %s", this.f8262n);
                                    str3 = this.f8262n;
                                }
                            }
                            C5940x.m3823c("[Upload] Send %d bytes", Integer.valueOf(bArr.length));
                            if (this.f8267s) {
                                str3 = m3805a(str3);
                            }
                            Object[] objArr = new Object[4];
                            objArr[0] = str3;
                            objArr[1] = Integer.valueOf(this.f8252d);
                            objArr[i3] = Integer.valueOf(Process.myPid());
                            objArr[3] = Integer.valueOf(Process.myTid());
                            C5940x.m3823c("[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).", objArr);
                            m3772a = this.f8256h.m3772a(str3, bArr, this, hashMap);
                            if (m3772a == null) {
                                Object[] objArr2 = new Object[i3];
                                objArr2[0] = 1;
                                objArr2[1] = "Failed to upload for no response!";
                                C5940x.m3825e("[Upload] Failed to upload(%d): %s", objArr2);
                                i8 = 1;
                                i7 = i9;
                            } else {
                                map = this.f8256h.f8218a;
                                if (!this.f8267s) {
                                    i = i6;
                                    break;
                                }
                                if (map != null && map.size() != 0) {
                                    if (!map.containsKey("status")) {
                                        C5940x.m3824d("[Upload] Headers does not contain %s", "status");
                                    } else if (map.containsKey(str2)) {
                                        String str4 = map.get(str2);
                                        if (str4.contains("bugly")) {
                                            str = str2;
                                            C5940x.m3823c("[Upload] Bugly version from headers is: %s", str4);
                                            z = true;
                                            if (z) {
                                            }
                                            i3 = 2;
                                        } else {
                                            str = str2;
                                            C5940x.m3824d("[Upload] Bugly version is not valid: %s", str4);
                                            z = false;
                                            if (z) {
                                                C5940x.m3823c("[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                                C5940x.m3825e("[Upload] Failed to upload(%d): %s", 1, "[Upload] Failed to upload for no status header.");
                                                if (map != null) {
                                                    for (Map.Entry<String, String> entry : map.entrySet()) {
                                                        C5940x.m3823c(String.format("[key]: %s, [value]: %s", entry.getKey(), entry.getValue()), new Object[0]);
                                                    }
                                                }
                                                C5940x.m3823c("[Upload] Failed to upload for no status header.", new Object[0]);
                                                i8 = 1;
                                                i7 = i9;
                                                str2 = str;
                                            } else {
                                                try {
                                                    i = Integer.parseInt(map.get("status"));
                                                } catch (Throwable unused) {
                                                    j = 0;
                                                }
                                                try {
                                                    C5940x.m3823c("[Upload] Status from server is %d (pid=%d | tid=%d).", Integer.valueOf(i), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                                    if (i != 0) {
                                                        if (i == 2) {
                                                            if (this.f8265q + this.f8266r > 0) {
                                                                this.f8257i.m3798a(this.f8257i.m3792a(this.f8268t) + this.f8265q + this.f8266r, this.f8268t);
                                                            }
                                                            this.f8257i.m3797a(i, (C5913aq) null);
                                                            C5940x.m3818a("[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                                                            this.f8257i.m3793a(this.f8258j, this.f8252d, this.f8253e, this.f8261m, this.f8262n, this.f8259k, this.f8249a, this.f8250b, true, this.f8263o);
                                                            return;
                                                        }
                                                        m3806a(null, false, 1, "status of server is " + i, i);
                                                        return;
                                                    }
                                                } catch (Throwable unused2) {
                                                    j = 0;
                                                    i6 = i;
                                                    C5940x.m3825e("[Upload] Failed to upload(%d): %s", 1, "[Upload] Failed to upload for format of status header is invalid: " + Integer.toString(i6));
                                                    i8 = 1;
                                                    i7 = i9;
                                                    str2 = str;
                                                    i3 = 2;
                                                }
                                            }
                                            i3 = 2;
                                        }
                                    } else {
                                        C5940x.m3824d("[Upload] Headers does not contain %s", str2);
                                    }
                                    str = str2;
                                    z = false;
                                    if (z) {
                                    }
                                    i3 = 2;
                                }
                                str = str2;
                                C5940x.m3824d("[Upload] Headers is empty.", new Object[0]);
                                z = false;
                                if (z) {
                                }
                                i3 = 2;
                            }
                        } else {
                            m3806a(null, false, i8, "failed after many attempts", 0);
                            return;
                        }
                    }
                    C5940x.m3823c("[Upload] Received %d bytes", Integer.valueOf(m3772a.length));
                    if (this.f8267s) {
                        if (m3772a.length == 0) {
                            for (Map.Entry<String, String> entry2 : map.entrySet()) {
                                C5940x.m3823c("[Upload] HTTP headers from server: key = %s, value = %s", entry2.getKey(), entry2.getValue());
                            }
                            m3806a(null, false, 1, "response data from server is empty", 0);
                            return;
                        }
                        byte[] m3804b = this.f8257i.m3804b(m3772a);
                        if (m3804b == null) {
                            m3806a(null, false, 1, "failed to decrypt response from server", 0);
                            return;
                        }
                        m3772a = C5942z.m3886b(m3804b, 2);
                        if (m3772a == null) {
                            m3806a(null, false, 1, "failed unzip(Gzip) response from server", 0);
                            return;
                        }
                    }
                    C5913aq m3612a = C5896a.m3612a(m3772a, this.f8267s);
                    if (m3612a == null) {
                        m3806a(null, false, 1, "failed to decode response package", 0);
                        return;
                    }
                    if (this.f8267s) {
                        this.f8257i.m3797a(i, m3612a);
                    }
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = Integer.valueOf(m3612a.f8099b);
                    if (m3612a.f8100c != null) {
                        i2 = m3612a.f8100c.length;
                    }
                    objArr3[1] = Integer.valueOf(i2);
                    C5940x.m3823c("[Upload] Response cmd is: %d, length of sBuffer is: %d", objArr3);
                    if (!m3807a(m3612a, this.f8254f, this.f8255g)) {
                        m3806a(m3612a, false, 2, "failed to process response package", 0);
                        return;
                    } else {
                        m3806a(m3612a, true, 2, "successfully uploaded", 0);
                        return;
                    }
                }
                m3806a(null, false, 0, "illegal access error", 0);
                return;
            }
            m3806a(null, false, 0, "request package is empty!", 0);
        } catch (Throwable th) {
            if (C5940x.m3819a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m3808a(long j) {
        this.f8264p++;
        this.f8265q += j;
    }

    /* renamed from: b */
    public final void m3809b(long j) {
        this.f8266r += j;
    }

    /* renamed from: a */
    private static String m3805a(String str) {
        if (C5942z.m3868a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            C5940x.m3819a(th);
            return str;
        }
    }
}
