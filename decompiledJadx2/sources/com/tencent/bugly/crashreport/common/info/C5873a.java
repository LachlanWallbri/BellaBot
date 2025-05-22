package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import androidx.core.os.EnvironmentCompat;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.bugly.C5865b;
import com.tencent.bugly.crashreport.InterfaceC5868a;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.common.info.a */
/* loaded from: classes7.dex */
public final class C5873a {

    /* renamed from: af */
    private static C5873a f7701af;

    /* renamed from: E */
    public SharedPreferences f7706E;

    /* renamed from: F */
    private final Context f7707F;

    /* renamed from: G */
    private String f7708G;

    /* renamed from: H */
    private String f7709H;

    /* renamed from: I */
    private String f7710I;

    /* renamed from: Y */
    private String f7726Y;

    /* renamed from: c */
    public String f7755c;

    /* renamed from: d */
    public final String f7756d;

    /* renamed from: f */
    public final String f7758f;

    /* renamed from: g */
    public final String f7759g;

    /* renamed from: h */
    public final String f7760h;

    /* renamed from: i */
    public long f7761i;

    /* renamed from: j */
    public String f7762j;

    /* renamed from: k */
    public String f7763k;

    /* renamed from: l */
    public String f7764l;

    /* renamed from: o */
    public List<String> f7767o;

    /* renamed from: u */
    public boolean f7773u;

    /* renamed from: v */
    public String f7774v;

    /* renamed from: w */
    public String f7775w;

    /* renamed from: x */
    public String f7776x;

    /* renamed from: z */
    public boolean f7778z;

    /* renamed from: e */
    public boolean f7757e = true;

    /* renamed from: J */
    private String f7711J = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: K */
    private String f7712K = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: L */
    private String f7713L = "";

    /* renamed from: M */
    private String f7714M = null;

    /* renamed from: N */
    private String f7715N = null;

    /* renamed from: O */
    private String f7716O = null;

    /* renamed from: P */
    private String f7717P = null;

    /* renamed from: Q */
    private long f7718Q = -1;

    /* renamed from: R */
    private long f7719R = -1;

    /* renamed from: S */
    private long f7720S = -1;

    /* renamed from: T */
    private String f7721T = null;

    /* renamed from: U */
    private String f7722U = null;

    /* renamed from: V */
    private Map<String, PlugInBean> f7723V = null;

    /* renamed from: W */
    private boolean f7724W = true;

    /* renamed from: X */
    private String f7725X = null;

    /* renamed from: Z */
    private Boolean f7727Z = null;

    /* renamed from: aa */
    private String f7729aa = null;

    /* renamed from: ab */
    private String f7730ab = null;

    /* renamed from: ac */
    private String f7731ac = null;

    /* renamed from: m */
    public String f7765m = null;

    /* renamed from: n */
    public String f7766n = null;

    /* renamed from: ad */
    private Map<String, PlugInBean> f7732ad = null;

    /* renamed from: ae */
    private Map<String, PlugInBean> f7733ae = null;

    /* renamed from: ag */
    private int f7734ag = -1;

    /* renamed from: ah */
    private int f7735ah = -1;

    /* renamed from: ai */
    private Map<String, String> f7736ai = new HashMap();

    /* renamed from: aj */
    private Map<String, String> f7737aj = new HashMap();

    /* renamed from: ak */
    private Map<String, String> f7738ak = new HashMap();

    /* renamed from: al */
    private boolean f7739al = true;

    /* renamed from: p */
    public String f7768p = EnvironmentCompat.MEDIA_UNKNOWN;

    /* renamed from: q */
    public long f7769q = 0;

    /* renamed from: r */
    public long f7770r = 0;

    /* renamed from: s */
    public long f7771s = 0;

    /* renamed from: t */
    public long f7772t = 0;

    /* renamed from: y */
    public boolean f7777y = false;

    /* renamed from: am */
    private Boolean f7740am = null;

    /* renamed from: an */
    private Boolean f7741an = null;

    /* renamed from: A */
    public HashMap<String, String> f7702A = new HashMap<>();

    /* renamed from: ao */
    private String f7742ao = null;

    /* renamed from: ap */
    private String f7743ap = null;

    /* renamed from: aq */
    private String f7744aq = null;

    /* renamed from: ar */
    private String f7745ar = null;

    /* renamed from: as */
    private String f7746as = null;

    /* renamed from: B */
    public boolean f7703B = true;

    /* renamed from: C */
    public List<String> f7704C = new ArrayList();

    /* renamed from: D */
    public InterfaceC5868a f7705D = null;

    /* renamed from: at */
    private final Object f7747at = new Object();

    /* renamed from: au */
    private final Object f7748au = new Object();

    /* renamed from: av */
    private final Object f7749av = new Object();

    /* renamed from: aw */
    private final Object f7750aw = new Object();

    /* renamed from: ax */
    private final Object f7751ax = new Object();

    /* renamed from: ay */
    private final Object f7752ay = new Object();

    /* renamed from: az */
    private final Object f7753az = new Object();

    /* renamed from: a */
    public final long f7728a = System.currentTimeMillis();

    /* renamed from: b */
    public final byte f7754b = 1;

    /* renamed from: c */
    public static String m3391c() {
        return "3.0.0";
    }

    private C5873a(Context context) {
        this.f7762j = null;
        this.f7763k = null;
        this.f7726Y = null;
        this.f7764l = null;
        this.f7767o = null;
        this.f7773u = false;
        this.f7774v = null;
        this.f7775w = null;
        this.f7776x = null;
        this.f7778z = false;
        this.f7707F = C5942z.m3846a(context);
        PackageInfo m3380b = AppInfo.m3380b(context);
        if (m3380b != null) {
            try {
                this.f7762j = m3380b.versionName;
                this.f7774v = this.f7762j;
                this.f7775w = Integer.toString(m3380b.versionCode);
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f7755c = AppInfo.m3376a(context);
        this.f7756d = AppInfo.m3375a(Process.myPid());
        this.f7758f = C5874b.m3476o();
        this.f7759g = C5874b.m3449a();
        this.f7763k = AppInfo.m3381c(context);
        this.f7760h = "Android " + C5874b.m3452b() + ",level " + C5874b.m3454c();
        String str = this.f7759g + VoiceWakeuperAidl.PARAMS_SEPARATE + this.f7760h;
        Map<String, String> m3382d = AppInfo.m3382d(context);
        if (m3382d != null) {
            try {
                this.f7767o = AppInfo.m3378a(m3382d);
                String str2 = m3382d.get("BUGLY_APPID");
                if (str2 != null) {
                    this.f7726Y = str2;
                    m3421c("APP_ID", this.f7726Y);
                }
                String str3 = m3382d.get("BUGLY_APP_VERSION");
                if (str3 != null) {
                    this.f7762j = str3;
                }
                String str4 = m3382d.get("BUGLY_APP_CHANNEL");
                if (str4 != null) {
                    this.f7764l = str4;
                }
                String str5 = m3382d.get("BUGLY_ENABLE_DEBUG");
                if (str5 != null) {
                    this.f7773u = str5.equalsIgnoreCase("true");
                }
                String str6 = m3382d.get("com.tencent.rdm.uuid");
                if (str6 != null) {
                    this.f7776x = str6;
                }
            } catch (Throwable th2) {
                if (!C5940x.m3819a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f7778z = true;
                C5940x.m3823c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (C5865b.f7644c) {
                th3.printStackTrace();
            }
        }
        this.f7706E = C5942z.m3847a("BUGLY_COMMON_VALUES", context);
        C5940x.m3823c("com info create end", new Object[0]);
    }

    /* renamed from: a */
    public final boolean m3415a() {
        return this.f7739al;
    }

    /* renamed from: a */
    public final void m3414a(boolean z) {
        this.f7739al = z;
        InterfaceC5868a interfaceC5868a = this.f7705D;
        if (interfaceC5868a != null) {
            interfaceC5868a.setNativeIsAppForeground(z);
        }
    }

    /* renamed from: a */
    public static synchronized C5873a m3389a(Context context) {
        C5873a c5873a;
        synchronized (C5873a.class) {
            if (f7701af == null) {
                f7701af = new C5873a(context);
            }
            c5873a = f7701af;
        }
        return c5873a;
    }

    /* renamed from: b */
    public static synchronized C5873a m3390b() {
        C5873a c5873a;
        synchronized (C5873a.class) {
            c5873a = f7701af;
        }
        return c5873a;
    }

    /* renamed from: d */
    public final void m3422d() {
        synchronized (this.f7747at) {
            this.f7708G = UUID.randomUUID().toString();
        }
    }

    /* renamed from: e */
    public final String m3424e() {
        String str;
        synchronized (this.f7747at) {
            if (this.f7708G == null) {
                synchronized (this.f7747at) {
                    this.f7708G = UUID.randomUUID().toString();
                }
            }
            str = this.f7708G;
        }
        return str;
    }

    /* renamed from: f */
    public final String m3426f() {
        if (C5942z.m3868a((String) null)) {
            return this.f7726Y;
        }
        return null;
    }

    /* renamed from: a */
    public final void m3412a(String str) {
        this.f7726Y = str;
        m3421c("APP_ID", str);
    }

    /* renamed from: g */
    public final String m3428g() {
        String str;
        synchronized (this.f7752ay) {
            str = this.f7711J;
        }
        return str;
    }

    /* renamed from: b */
    public final void m3417b(String str) {
        synchronized (this.f7752ay) {
            if (str == null) {
                str = "10000";
            }
            this.f7711J = str;
        }
    }

    /* renamed from: b */
    public final void m3419b(boolean z) {
        this.f7724W = z;
    }

    /* renamed from: h */
    public final String m3430h() {
        String str = this.f7710I;
        if (str != null) {
            return str;
        }
        this.f7710I = m3433k() + "|" + m3435m() + "|" + m3436n();
        return this.f7710I;
    }

    /* renamed from: c */
    public final void m3420c(String str) {
        this.f7710I = str;
        synchronized (this.f7753az) {
            this.f7737aj.put("E8", str);
        }
    }

    /* renamed from: i */
    public final synchronized String m3431i() {
        return this.f7712K;
    }

    /* renamed from: d */
    public final synchronized void m3423d(String str) {
        this.f7712K = str;
    }

    /* renamed from: j */
    public final synchronized String m3432j() {
        return this.f7713L;
    }

    /* renamed from: e */
    public final synchronized void m3425e(String str) {
        this.f7713L = str;
    }

    /* renamed from: k */
    public final String m3433k() {
        if (!this.f7724W) {
            return "";
        }
        if (this.f7714M == null) {
            Context context = this.f7707F;
            this.f7714M = C5874b.m3456d();
        }
        return this.f7714M;
    }

    /* renamed from: l */
    public final String m3434l() {
        if (!this.f7724W) {
            return "";
        }
        String str = this.f7715N;
        if (str == null || !str.contains(":")) {
            Context context = this.f7707F;
            this.f7715N = C5874b.m3460f();
        }
        return this.f7715N;
    }

    /* renamed from: m */
    public final String m3435m() {
        if (!this.f7724W) {
            return "";
        }
        if (this.f7716O == null) {
            Context context = this.f7707F;
            this.f7716O = C5874b.m3458e();
        }
        return this.f7716O;
    }

    /* renamed from: n */
    public final String m3436n() {
        if (!this.f7724W) {
            return "";
        }
        if (this.f7717P == null) {
            this.f7717P = C5874b.m3450a(this.f7707F);
        }
        return this.f7717P;
    }

    /* renamed from: o */
    public final long m3437o() {
        if (this.f7718Q <= 0) {
            this.f7718Q = C5874b.m3464h();
        }
        return this.f7718Q;
    }

    /* renamed from: p */
    public final long m3438p() {
        if (this.f7719R <= 0) {
            this.f7719R = C5874b.m3468j();
        }
        return this.f7719R;
    }

    /* renamed from: q */
    public final long m3439q() {
        if (this.f7720S <= 0) {
            this.f7720S = C5874b.m3473l();
        }
        return this.f7720S;
    }

    /* renamed from: r */
    public final String m3440r() {
        if (this.f7721T == null) {
            this.f7721T = C5874b.m3451a(this.f7707F, true);
        }
        return this.f7721T;
    }

    /* renamed from: s */
    public final String m3441s() {
        if (this.f7722U == null) {
            this.f7722U = C5874b.m3459e(this.f7707F);
        }
        return this.f7722U;
    }

    /* renamed from: a */
    public final void m3413a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        synchronized (this.f7748au) {
            this.f7702A.put(str, str2);
        }
    }

    /* renamed from: t */
    public final String m3442t() {
        try {
            Map<String, ?> all = this.f7707F.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.f7748au) {
                    for (Map.Entry<String, ?> entry : all.entrySet()) {
                        try {
                            this.f7702A.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            C5940x.m3819a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C5940x.m3819a(th2);
        }
        if (this.f7702A.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry2 : this.f7702A.entrySet()) {
            sb.append("[");
            sb.append(entry2.getKey());
            sb.append(",");
            sb.append(entry2.getValue());
            sb.append("] ");
        }
        m3421c("SDK_INFO", sb.toString());
        return sb.toString();
    }

    /* renamed from: u */
    public final String m3443u() {
        if (this.f7746as == null) {
            this.f7746as = AppInfo.m3383e(this.f7707F);
        }
        return this.f7746as;
    }

    /* renamed from: v */
    public final synchronized Map<String, PlugInBean> m3444v() {
        return null;
    }

    /* renamed from: w */
    public final String m3445w() {
        if (this.f7725X == null) {
            this.f7725X = C5874b.m3475n();
        }
        return this.f7725X;
    }

    /* renamed from: x */
    public final Boolean m3446x() {
        if (this.f7727Z == null) {
            this.f7727Z = Boolean.valueOf(C5874b.m3477p());
        }
        return this.f7727Z;
    }

    /* renamed from: y */
    public final String m3447y() {
        if (this.f7729aa == null) {
            this.f7729aa = C5874b.m3457d(this.f7707F);
            C5940x.m3818a("ROM ID: %s", this.f7729aa);
        }
        return this.f7729aa;
    }

    /* renamed from: z */
    public final String m3448z() {
        if (this.f7730ab == null) {
            this.f7730ab = C5874b.m3453b(this.f7707F);
            C5940x.m3818a("SIM serial number: %s", this.f7730ab);
        }
        return this.f7730ab;
    }

    /* renamed from: A */
    public final String m3392A() {
        if (this.f7731ac == null) {
            this.f7731ac = C5874b.m3462g();
            C5940x.m3818a("Hardware serial number: %s", this.f7731ac);
        }
        return this.f7731ac;
    }

    /* renamed from: B */
    public final Map<String, String> m3393B() {
        synchronized (this.f7749av) {
            if (this.f7736ai.size() <= 0) {
                return null;
            }
            return new HashMap(this.f7736ai);
        }
    }

    /* renamed from: f */
    public final String m3427f(String str) {
        String remove;
        if (C5942z.m3868a(str)) {
            C5940x.m3824d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f7749av) {
            remove = this.f7736ai.remove(str);
        }
        return remove;
    }

    /* renamed from: C */
    public final void m3394C() {
        synchronized (this.f7749av) {
            this.f7736ai.clear();
        }
    }

    /* renamed from: g */
    public final String m3429g(String str) {
        String str2;
        if (C5942z.m3868a(str)) {
            C5940x.m3824d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f7749av) {
            str2 = this.f7736ai.get(str);
        }
        return str2;
    }

    /* renamed from: b */
    public final void m3418b(String str, String str2) {
        if (C5942z.m3868a(str) || C5942z.m3868a(str2)) {
            C5940x.m3824d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f7749av) {
            this.f7736ai.put(str, str2);
        }
    }

    /* renamed from: D */
    public final int m3395D() {
        int size;
        synchronized (this.f7749av) {
            size = this.f7736ai.size();
        }
        return size;
    }

    /* renamed from: E */
    public final Set<String> m3396E() {
        Set<String> keySet;
        synchronized (this.f7749av) {
            keySet = this.f7736ai.keySet();
        }
        return keySet;
    }

    /* renamed from: F */
    public final Map<String, String> m3397F() {
        synchronized (this.f7753az) {
            if (this.f7737aj.size() <= 0) {
                return null;
            }
            return new HashMap(this.f7737aj);
        }
    }

    /* renamed from: c */
    public final void m3421c(String str, String str2) {
        if (C5942z.m3868a(str) || C5942z.m3868a(str2)) {
            C5940x.m3824d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f7750aw) {
            this.f7738ak.put(str, str2);
        }
    }

    /* renamed from: G */
    public final Map<String, String> m3398G() {
        synchronized (this.f7750aw) {
            if (this.f7738ak.size() <= 0) {
                return null;
            }
            return new HashMap(this.f7738ak);
        }
    }

    /* renamed from: a */
    public final void m3411a(int i) {
        synchronized (this.f7751ax) {
            int i2 = this.f7734ag;
            if (i2 != i) {
                this.f7734ag = i;
                C5940x.m3818a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f7734ag));
            }
        }
    }

    /* renamed from: H */
    public final int m3399H() {
        int i;
        synchronized (this.f7751ax) {
            i = this.f7734ag;
        }
        return i;
    }

    /* renamed from: b */
    public final void m3416b(int i) {
        int i2 = this.f7735ah;
        if (i2 != 24096) {
            this.f7735ah = 24096;
            C5940x.m3818a("server scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f7735ah));
        }
    }

    /* renamed from: I */
    public final int m3400I() {
        return this.f7735ah;
    }

    /* renamed from: J */
    public final synchronized Map<String, PlugInBean> m3401J() {
        return null;
    }

    /* renamed from: K */
    public static int m3388K() {
        return C5874b.m3454c();
    }

    /* renamed from: L */
    public final String m3402L() {
        if (this.f7742ao == null) {
            this.f7742ao = C5874b.m3478q();
        }
        return this.f7742ao;
    }

    /* renamed from: M */
    public final String m3403M() {
        if (this.f7743ap == null) {
            this.f7743ap = C5874b.m3461f(this.f7707F);
        }
        return this.f7743ap;
    }

    /* renamed from: N */
    public final String m3404N() {
        if (this.f7744aq == null) {
            this.f7744aq = C5874b.m3463g(this.f7707F);
        }
        return this.f7744aq;
    }

    /* renamed from: O */
    public final String m3405O() {
        Context context = this.f7707F;
        return C5874b.m3479r();
    }

    /* renamed from: P */
    public final String m3406P() {
        if (this.f7745ar == null) {
            this.f7745ar = C5874b.m3465h(this.f7707F);
        }
        return this.f7745ar;
    }

    /* renamed from: Q */
    public final long m3407Q() {
        Context context = this.f7707F;
        return C5874b.m3480s();
    }

    /* renamed from: R */
    public final boolean m3408R() {
        if (this.f7740am == null) {
            this.f7740am = Boolean.valueOf(C5874b.m3467i(this.f7707F));
            C5940x.m3818a("Is it a virtual machine? " + this.f7740am, new Object[0]);
        }
        return this.f7740am.booleanValue();
    }

    /* renamed from: S */
    public final boolean m3409S() {
        if (this.f7741an == null) {
            this.f7741an = Boolean.valueOf(C5874b.m3469j(this.f7707F));
            C5940x.m3818a("Does it has hook frame? " + this.f7741an, new Object[0]);
        }
        return this.f7741an.booleanValue();
    }

    /* renamed from: T */
    public final String m3410T() {
        if (this.f7709H == null) {
            this.f7709H = AppInfo.m3385g(this.f7707F);
            C5940x.m3818a("Beacon channel " + this.f7709H, new Object[0]);
        }
        return this.f7709H;
    }
}
