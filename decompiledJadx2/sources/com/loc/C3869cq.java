package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.loc.C3886l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AuthUtil.java */
/* renamed from: com.loc.cq */
/* loaded from: classes4.dex */
public final class C3869cq {

    /* renamed from: A */
    private static boolean f3985A = true;

    /* renamed from: B */
    private static boolean f3986B = true;

    /* renamed from: C */
    private static int f3987C = -1;

    /* renamed from: D */
    private static long f3988D = 0;

    /* renamed from: J */
    private static String f3994J = null;

    /* renamed from: K */
    private static String f3995K = null;

    /* renamed from: a */
    static boolean f4011a = true;

    /* renamed from: e */
    private static String f4020e = "提示信息";

    /* renamed from: f */
    private static String f4021f = "确认";

    /* renamed from: g */
    private static String f4022g = "取消";

    /* renamed from: h */
    private static String f4023h = "";

    /* renamed from: i */
    private static String f4024i = "";

    /* renamed from: j */
    private static String f4025j = "";

    /* renamed from: k */
    private static boolean f4026k = false;

    /* renamed from: l */
    private static long f4027l = 0;

    /* renamed from: m */
    private static long f4028m = 0;

    /* renamed from: n */
    private static long f4029n = 5000;

    /* renamed from: o */
    private static boolean f4030o = false;

    /* renamed from: p */
    private static int f4031p = 0;

    /* renamed from: q */
    private static boolean f4032q = false;

    /* renamed from: r */
    private static int f4033r = 0;

    /* renamed from: s */
    private static boolean f4034s = false;

    /* renamed from: t */
    private static int f4035t = 3600000;

    /* renamed from: u */
    private static int f4036u = 0;

    /* renamed from: v */
    private static int f4037v = 0;

    /* renamed from: w */
    private static boolean f4038w = true;

    /* renamed from: x */
    private static int f4039x = 1000;

    /* renamed from: y */
    private static int f4040y = 200;

    /* renamed from: z */
    private static boolean f4041z = false;

    /* renamed from: E */
    private static ArrayList<String> f3989E = new ArrayList<>();

    /* renamed from: F */
    private static boolean f3990F = true;

    /* renamed from: G */
    private static int f3991G = -1;

    /* renamed from: H */
    private static long f3992H = 0;

    /* renamed from: I */
    private static ArrayList<String> f3993I = new ArrayList<>();

    /* renamed from: L */
    private static boolean f3996L = false;

    /* renamed from: M */
    private static boolean f3997M = false;

    /* renamed from: N */
    private static int f3998N = 3000;

    /* renamed from: O */
    private static int f3999O = 3000;

    /* renamed from: P */
    private static boolean f4000P = true;

    /* renamed from: Q */
    private static long f4001Q = 300000;

    /* renamed from: R */
    private static int f4002R = -1;

    /* renamed from: S */
    private static boolean f4003S = false;

    /* renamed from: T */
    private static boolean f4004T = false;

    /* renamed from: U */
    private static boolean f4005U = false;

    /* renamed from: V */
    private static boolean f4006V = false;

    /* renamed from: b */
    static boolean f4017b = false;

    /* renamed from: W */
    private static List<C3870cr> f4007W = new ArrayList();

    /* renamed from: X */
    private static boolean f4008X = false;

    /* renamed from: Y */
    private static long f4009Y = 0;

    /* renamed from: Z */
    private static int f4010Z = 0;

    /* renamed from: aa */
    private static int f4012aa = 0;

    /* renamed from: ab */
    private static List<String> f4013ab = new ArrayList();

    /* renamed from: ac */
    private static boolean f4014ac = true;

    /* renamed from: ad */
    private static int f4015ad = 80;

    /* renamed from: c */
    static int f4018c = 1800000;

    /* renamed from: d */
    static int f4019d = 3600000;

    /* renamed from: ae */
    private static boolean f4016ae = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthUtil.java */
    /* renamed from: com.loc.cq$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        boolean f4042a = false;

        /* renamed from: b */
        String f4043b = "0";

        /* renamed from: c */
        boolean f4044c = false;

        /* renamed from: d */
        int f4045d = 5;

        a() {
        }
    }

    /* renamed from: A */
    public static int m2866A() {
        return f4018c;
    }

    /* renamed from: B */
    public static int m2867B() {
        return f4019d;
    }

    /* renamed from: C */
    public static boolean m2868C() {
        return f4016ae;
    }

    /* renamed from: a */
    private static a m2869a(JSONObject jSONObject, String str) {
        a aVar;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (jSONObject2 != null) {
                    aVar = new a();
                    try {
                        aVar.f4042a = C3886l.m3134a(jSONObject2.optString("b"), false);
                        aVar.f4043b = jSONObject2.optString("t");
                        aVar.f4044c = C3886l.m3134a(jSONObject2.optString("st"), false);
                        aVar.f4045d = jSONObject2.optInt("i", 0);
                        return aVar;
                    } catch (Throwable th) {
                        th = th;
                        C3880f.m3097a(th, "AuthUtil", "getLocateObj");
                        return aVar;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                aVar = null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m2870a() {
        return f4030o;
    }

    /* renamed from: a */
    public static boolean m2871a(long j) {
        long m2985b = C3876cx.m2985b();
        return f4026k && m2985b - f4028m <= f4027l && m2985b - j >= f4029n;
    }

    /* renamed from: a */
    public static boolean m2872a(Context context) {
        f3985A = true;
        try {
            C3886l.a m3130a = C3886l.m3130a(context, C3880f.m3091a("loc"), C3880f.m3100b(context));
            if (m3130a == null) {
                return false;
            }
            f4002R = m3130a.f4232b;
            return m2875a(context, m3130a);
        } finally {
        }
    }

    /* renamed from: a */
    public static boolean m2873a(Context context, long j) {
        if (!f3997M) {
            return false;
        }
        long m2969a = C3876cx.m2969a();
        if (m2969a - j < f3998N) {
            return false;
        }
        if (f3999O == -1) {
            return true;
        }
        if (C3876cx.m2999c(m2969a, C3875cw.m2951a(context, "pref", "ngpsTime"))) {
            int m2957b = C3875cw.m2957b(context, "pref", "ngpsCount", 0);
            if (m2957b >= f3999O) {
                return false;
            }
            C3875cw.m2953a(context, "pref", "ngpsCount", m2957b + 1);
            return true;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("ngpsTime", m2969a);
            edit.putInt("ngpsCount", 0);
            C3875cw.m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AuthUtil", "resetPrefsNGPS");
        }
        C3875cw.m2953a(context, "pref", "ngpsCount", 1);
        return true;
    }

    /* renamed from: a */
    private static boolean m2874a(Context context, C3886l.a.b bVar, String str, String str2) {
        boolean z = false;
        if (bVar != null) {
            try {
                z = bVar.f4256a;
                String str3 = bVar.f4257b;
                String str4 = bVar.f4258c;
                String str5 = bVar.f4259d;
                if (z && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str5)) {
                    C3819au.m2479a(context, new C3818at(str3, str4), C3880f.m3092a(str, str2));
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "AuthUtil", "downLoadPluginDex");
            }
        }
        return z;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(45:1|(2:2|3)|(5:(2:5|(4:7|(3:11|(2:14|12)|15)|16|(49:20|21|22|23|(1:25)|27|28|(2:30|(4:32|(3:36|(2:39|37)|40)|41|(2:45|(1:47))))|49|50|(4:52|(1:54)|55|(1:61))|67|68|(2:70|(35:77|78|79|(2:81|(34:85|86|87|88|89|90|91|(5:93|(1:95)|96|(1:98)|99)|101|102|(6:106|(3:108|109|110)|114|(5:116|117|118|119|(1:121))|125|(26:127|(4:129|130|131|132)(1:314)|133|134|135|136|(2:138|(3:140|(1:142)(1:145)|143))|146|147|(2:149|(1:151))|153|154|(1:156)|158|159|(2:161|(1:165))|167|168|(2:170|(13:176|(9:179|180|(5:187|(7:189|190|(14:194|195|196|197|198|199|200|201|202|203|205|206|191|192)|213|214|215|216)(1:223)|217|219|186)|182|183|184|185|186|177)|226|227|228|229|(2:231|(4:234|(2:236|237)(1:239)|238|232))|241|242|(2:244|(1:246))|248|249|(10:251|252|254|255|(3:274|275|(1:277))|257|258|260|261|(2:263|265)(1:267))(1:286)))|296|241|242|(0)|248|249|(0)(0)))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|324|89|90|91|(0)|101|102|(7:104|106|(0)|114|(0)|125|(0))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0))))|248|249|(0)(0)|(3:(1:310)|(0)|(1:281)))|339|27|28|(0)|49|50|(0)|67|68|(0)|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(49:1|(2:2|3)|(2:5|(4:7|(3:11|(2:14|12)|15)|16|(49:20|21|22|23|(1:25)|27|28|(2:30|(4:32|(3:36|(2:39|37)|40)|41|(2:45|(1:47))))|49|50|(4:52|(1:54)|55|(1:61))|67|68|(2:70|(35:77|78|79|(2:81|(34:85|86|87|88|89|90|91|(5:93|(1:95)|96|(1:98)|99)|101|102|(6:106|(3:108|109|110)|114|(5:116|117|118|119|(1:121))|125|(26:127|(4:129|130|131|132)(1:314)|133|134|135|136|(2:138|(3:140|(1:142)(1:145)|143))|146|147|(2:149|(1:151))|153|154|(1:156)|158|159|(2:161|(1:165))|167|168|(2:170|(13:176|(9:179|180|(5:187|(7:189|190|(14:194|195|196|197|198|199|200|201|202|203|205|206|191|192)|213|214|215|216)(1:223)|217|219|186)|182|183|184|185|186|177)|226|227|228|229|(2:231|(4:234|(2:236|237)(1:239)|238|232))|241|242|(2:244|(1:246))|248|249|(10:251|252|254|255|(3:274|275|(1:277))|257|258|260|261|(2:263|265)(1:267))(1:286)))|296|241|242|(0)|248|249|(0)(0)))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|324|89|90|91|(0)|101|102|(7:104|106|(0)|114|(0)|125|(0))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0))))|339|27|28|(0)|49|50|(0)|67|68|(0)|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)|(3:(1:310)|(0)|(1:281))) */
    /* JADX WARN: Can't wrap try/catch for region: R(50:1|2|3|(2:5|(4:7|(3:11|(2:14|12)|15)|16|(49:20|21|22|23|(1:25)|27|28|(2:30|(4:32|(3:36|(2:39|37)|40)|41|(2:45|(1:47))))|49|50|(4:52|(1:54)|55|(1:61))|67|68|(2:70|(35:77|78|79|(2:81|(34:85|86|87|88|89|90|91|(5:93|(1:95)|96|(1:98)|99)|101|102|(6:106|(3:108|109|110)|114|(5:116|117|118|119|(1:121))|125|(26:127|(4:129|130|131|132)(1:314)|133|134|135|136|(2:138|(3:140|(1:142)(1:145)|143))|146|147|(2:149|(1:151))|153|154|(1:156)|158|159|(2:161|(1:165))|167|168|(2:170|(13:176|(9:179|180|(5:187|(7:189|190|(14:194|195|196|197|198|199|200|201|202|203|205|206|191|192)|213|214|215|216)(1:223)|217|219|186)|182|183|184|185|186|177)|226|227|228|229|(2:231|(4:234|(2:236|237)(1:239)|238|232))|241|242|(2:244|(1:246))|248|249|(10:251|252|254|255|(3:274|275|(1:277))|257|258|260|261|(2:263|265)(1:267))(1:286)))|296|241|242|(0)|248|249|(0)(0)))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|324|89|90|91|(0)|101|102|(7:104|106|(0)|114|(0)|125|(0))|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)))|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0))))|339|27|28|(0)|49|50|(0)|67|68|(0)|327|78|79|(0)|324|89|90|91|(0)|101|102|(0)|315|135|136|(0)|146|147|(0)|153|154|(0)|158|159|(0)|167|168|(0)|296|241|242|(0)|248|249|(0)(0)|(3:(1:310)|(0)|(1:281))) */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x04c0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x04c1, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_gpsGeoAble");
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x049e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x049f, code lost:
    
        r22 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x037d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x037e, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_dnsDex");
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x035a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x035b, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_CollectorDex");
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0347, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x0348, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_cacheAble");
     */
    /* JADX WARN: Code restructure failed: missing block: B:305:0x0324, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x0325, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_ngps");
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x02f1, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x02f2, code lost:
    
        r17 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0262, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:319:0x0263, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_uploadException");
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x01fb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:326:0x01fc, code lost:
    
        r14 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x01c5, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x01c6, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_sdkUpdate");
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x0190, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x0191, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_openAMap");
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x00f1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x00f2, code lost:
    
        com.loc.C3880f.m3097a(r0, "AuthUtil", "loadConfigData_callAMapPush");
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x026c A[Catch: all -> 0x02f1, TryCatch #11 {all -> 0x02f1, blocks: (B:102:0x0268, B:104:0x026c, B:106:0x0277, B:108:0x027f, B:113:0x028d, B:114:0x0292, B:116:0x029a, B:119:0x02b1, B:121:0x02b6, B:124:0x02ac, B:125:0x02b9, B:127:0x02c1, B:129:0x02c7, B:118:0x02a2, B:110:0x0283), top: B:101:0x0268, inners: #8, #24 }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x027f A[Catch: all -> 0x02f1, TRY_LEAVE, TryCatch #11 {all -> 0x02f1, blocks: (B:102:0x0268, B:104:0x026c, B:106:0x0277, B:108:0x027f, B:113:0x028d, B:114:0x0292, B:116:0x029a, B:119:0x02b1, B:121:0x02b6, B:124:0x02ac, B:125:0x02b9, B:127:0x02c1, B:129:0x02c7, B:118:0x02a2, B:110:0x0283), top: B:101:0x0268, inners: #8, #24 }] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x029a A[Catch: all -> 0x02f1, TRY_LEAVE, TryCatch #11 {all -> 0x02f1, blocks: (B:102:0x0268, B:104:0x026c, B:106:0x0277, B:108:0x027f, B:113:0x028d, B:114:0x0292, B:116:0x029a, B:119:0x02b1, B:121:0x02b6, B:124:0x02ac, B:125:0x02b9, B:127:0x02c1, B:129:0x02c7, B:118:0x02a2, B:110:0x0283), top: B:101:0x0268, inners: #8, #24 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02c1 A[Catch: all -> 0x02f1, TryCatch #11 {all -> 0x02f1, blocks: (B:102:0x0268, B:104:0x026c, B:106:0x0277, B:108:0x027f, B:113:0x028d, B:114:0x0292, B:116:0x029a, B:119:0x02b1, B:121:0x02b6, B:124:0x02ac, B:125:0x02b9, B:127:0x02c1, B:129:0x02c7, B:118:0x02a2, B:110:0x0283), top: B:101:0x0268, inners: #8, #24 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02fd A[Catch: all -> 0x0324, TryCatch #5 {all -> 0x0324, blocks: (B:136:0x02f9, B:138:0x02fd, B:140:0x030a, B:142:0x0310, B:143:0x0319, B:145:0x0315), top: B:135:0x02f9, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x032e A[Catch: all -> 0x0347, TryCatch #22 {all -> 0x0347, blocks: (B:147:0x032a, B:149:0x032e, B:151:0x033b), top: B:146:0x032a, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0351 A[Catch: all -> 0x035a, TRY_LEAVE, TryCatch #29 {all -> 0x035a, blocks: (B:154:0x034d, B:156:0x0351), top: B:153:0x034d, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0364 A[Catch: all -> 0x037d, TryCatch #17 {all -> 0x037d, blocks: (B:159:0x0360, B:161:0x0364, B:163:0x036d, B:165:0x0377), top: B:158:0x0360, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0387 A[Catch: all -> 0x049e, TryCatch #32 {all -> 0x049e, blocks: (B:168:0x0383, B:170:0x0387, B:172:0x03b1, B:174:0x03b5, B:176:0x03bb, B:177:0x03c2), top: B:167:0x0383 }] */
    /* JADX WARN: Removed duplicated region for block: B:244:0x04aa A[Catch: all -> 0x04c0, TryCatch #19 {all -> 0x04c0, blocks: (B:242:0x04a6, B:244:0x04aa, B:246:0x04b7), top: B:241:0x04a6, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x04ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:286:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0091 A[Catch: all -> 0x00f1, TryCatch #12 {all -> 0x00f1, blocks: (B:28:0x008d, B:30:0x0091, B:32:0x00a0, B:34:0x00ba, B:37:0x00c1, B:39:0x00c7, B:41:0x00d3, B:43:0x00d7, B:45:0x00df, B:47:0x00ed), top: B:27:0x008d, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fb A[Catch: all -> 0x0190, TryCatch #25 {all -> 0x0190, blocks: (B:50:0x00f7, B:52:0x00fb, B:54:0x0129, B:55:0x012e, B:57:0x016a, B:59:0x0186, B:61:0x018c, B:63:0x0174, B:65:0x017c), top: B:49:0x00f7, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019e A[Catch: all -> 0x01c5, TryCatch #21 {all -> 0x01c5, blocks: (B:68:0x0196, B:70:0x019e, B:72:0x01aa, B:74:0x01b0, B:77:0x01b7, B:327:0x01c0), top: B:67:0x0196, outer: #30 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01cf A[Catch: all -> 0x01fb, TryCatch #16 {all -> 0x01fb, blocks: (B:79:0x01cb, B:81:0x01cf, B:83:0x01df, B:85:0x01e7), top: B:78:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0209 A[Catch: all -> 0x0262, TryCatch #2 {all -> 0x0262, blocks: (B:91:0x0205, B:93:0x0209, B:95:0x022e, B:96:0x0232, B:98:0x0236, B:99:0x0238), top: B:90:0x0205, outer: #30 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m2875a(Context context, C3886l.a aVar) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        String str6;
        String str7;
        JSONArray jSONArray;
        JSONObject optJSONObject;
        C3870cr c3870cr;
        boolean m3134a;
        String str8;
        C3886l.a.b bVar;
        C3886l.a.b bVar2;
        JSONObject jSONObject4;
        JSONObject jSONObject5;
        JSONObject jSONObject6;
        a m2869a;
        a m2869a2;
        a m2869a3;
        C3886l.a.C9033a c9033a;
        C3886l.a.c cVar;
        C3886l.a.d dVar;
        JSONObject jSONObject7;
        JSONObject jSONObject8;
        JSONObject jSONObject9;
        String str9 = "";
        String str10 = "pref";
        try {
            jSONObject9 = aVar.f4234d;
        } catch (Throwable th) {
            th = th;
            str = "HttpDNS";
            str2 = "loc";
        }
        try {
            if (jSONObject9 != null) {
                boolean m3134a2 = C3886l.m3134a(jSONObject9.optString("callamapflag"), true);
                f3986B = m3134a2;
                if (m3134a2) {
                    f3987C = jSONObject9.optInt("count", f3987C);
                    f3988D = jSONObject9.optLong("sysTime", f3988D);
                    JSONArray optJSONArray = jSONObject9.optJSONArray("sn");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            f3989E.add(optJSONArray.getString(i));
                        }
                    }
                    if (f3987C != -1 && f3988D != 0) {
                        str = "HttpDNS";
                        str2 = "loc";
                        try {
                            if (!C3876cx.m2990b(f3988D, C3875cw.m2951a(context, "pref", "nowtime"))) {
                                m2890h(context);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                C3880f.m3097a(th, "AuthUtil", "loadConfigData_callAMapSer");
                                jSONObject8 = aVar.f4237g;
                                if (jSONObject8 != null) {
                                }
                                jSONObject7 = aVar.f4238h;
                                if (jSONObject7 != null) {
                                }
                                C3893s m3091a = C3880f.m3091a(str2);
                                dVar = aVar.f4247q;
                                if (dVar != null) {
                                }
                                C3819au.m2479a(context, (C3818at) null, m3091a);
                                cVar = aVar.f4248r;
                                if (cVar != null) {
                                }
                                str3 = str2;
                                c9033a = aVar.f4246p;
                                if (c9033a != null) {
                                }
                                jSONObject6 = aVar.f4239i;
                                if (jSONObject6 != null) {
                                }
                                str4 = str3;
                                jSONObject5 = aVar.f4241k;
                                if (jSONObject5 != null) {
                                }
                                jSONObject4 = aVar.f4242l;
                                if (jSONObject4 != null) {
                                }
                                bVar2 = aVar.f4249s;
                                if (bVar2 != null) {
                                }
                                bVar = aVar.f4251u;
                                if (bVar != null) {
                                }
                                jSONObject3 = aVar.f4236f;
                                if (jSONObject3 != null) {
                                }
                                str5 = "pref";
                                jSONObject2 = aVar.f4235e;
                                if (jSONObject2 != null) {
                                }
                                jSONObject = aVar.f4233c;
                                if (jSONObject == null) {
                                }
                            } catch (Throwable unused) {
                                return false;
                            }
                        }
                        jSONObject8 = aVar.f4237g;
                        if (jSONObject8 != null) {
                            boolean m3134a3 = C3886l.m3134a(jSONObject8.optString("amappushflag"), false);
                            f3990F = m3134a3;
                            if (m3134a3) {
                                f3991G = jSONObject8.optInt("count", f3991G);
                                f3992H = jSONObject8.optLong("sysTime", f3992H);
                                JSONArray optJSONArray2 = jSONObject8.optJSONArray("sn");
                                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                        f3993I.add(optJSONArray2.getString(i2));
                                    }
                                }
                                if (f3991G != -1 && f3992H != 0) {
                                    if (!C3876cx.m2990b(f3992H, C3875cw.m2951a(context, "pref", "pushSerTime"))) {
                                        m2892i(context);
                                    }
                                }
                            }
                        }
                        jSONObject7 = aVar.f4238h;
                        if (jSONObject7 != null) {
                            f3996L = C3886l.m3134a(jSONObject7.optString(C3898x.f4339h), false);
                            f4036u = jSONObject7.optInt("mco", 0);
                            f4037v = jSONObject7.optInt("co", 0);
                            int optInt = jSONObject7.optInt("it", 3600) * 1000;
                            f4035t = optInt;
                            if (optInt < 3600000) {
                                f4035t = 3600000;
                            }
                            f4020e = jSONObject7.optString("a", "提示信息");
                            f4021f = jSONObject7.optString("o", "确认");
                            f4022g = jSONObject7.optString("c", "取消");
                            f4023h = jSONObject7.optString("i", "");
                            f4024i = jSONObject7.optString("u", "");
                            f4025j = jSONObject7.optString("t", "");
                            if (((TextUtils.isEmpty(f4023h) || "null".equals(f4023h)) && (TextUtils.isEmpty(f4024i) || "null".equals(f4024i))) || f4037v > f4036u) {
                                f3996L = false;
                            }
                        }
                        C3893s m3091a2 = C3880f.m3091a(str2);
                        dVar = aVar.f4247q;
                        if (dVar != null) {
                            String str11 = dVar.f4263b;
                            String str12 = dVar.f4262a;
                            String str13 = dVar.f4264c;
                            if (!TextUtils.isEmpty(str11) && !TextUtils.isEmpty(str12) && !TextUtils.isEmpty(str13)) {
                                C3819au.m2479a(context, new C3818at(str12, str11), m3091a2);
                                cVar = aVar.f4248r;
                                if (cVar != null) {
                                    f3994J = cVar.f4260a;
                                    f3995K = cVar.f4261b;
                                    if (!TextUtils.isEmpty(f3994J) && !TextUtils.isEmpty(f3995K)) {
                                        str3 = str2;
                                        try {
                                            new C3892r(context, str3, f3994J, f3995K).m3201a();
                                        } catch (Throwable th3) {
                                            th = th3;
                                            C3880f.m3097a(th, "AuthUtil", "loadConfigData_groupOffset");
                                            c9033a = aVar.f4246p;
                                            if (c9033a != null) {
                                            }
                                            jSONObject6 = aVar.f4239i;
                                            if (jSONObject6 != null) {
                                            }
                                            str4 = str3;
                                            jSONObject5 = aVar.f4241k;
                                            if (jSONObject5 != null) {
                                            }
                                            jSONObject4 = aVar.f4242l;
                                            if (jSONObject4 != null) {
                                            }
                                            bVar2 = aVar.f4249s;
                                            if (bVar2 != null) {
                                            }
                                            bVar = aVar.f4251u;
                                            if (bVar != null) {
                                            }
                                            jSONObject3 = aVar.f4236f;
                                            if (jSONObject3 != null) {
                                            }
                                            str5 = "pref";
                                            jSONObject2 = aVar.f4235e;
                                            if (jSONObject2 != null) {
                                            }
                                            jSONObject = aVar.f4233c;
                                            if (jSONObject == null) {
                                            }
                                        }
                                        c9033a = aVar.f4246p;
                                        if (c9033a != null) {
                                            f4038w = c9033a.f4253a;
                                            C3875cw.m2955a(context, "pref", MqttServiceConstants.TRACE_EXCEPTION, f4038w);
                                            JSONObject jSONObject10 = c9033a.f4255c;
                                            f4039x = jSONObject10.optInt("fn", f4039x);
                                            int optInt2 = jSONObject10.optInt("mpn", f4040y);
                                            f4040y = optInt2;
                                            if (optInt2 > 500) {
                                                f4040y = 500;
                                            }
                                            if (f4040y < 30) {
                                                f4040y = 30;
                                            }
                                            f4041z = C3886l.m3134a(jSONObject10.optString("igu"), false);
                                            C3842bq.m2632a(f4039x, f4041z);
                                            C3875cw.m2953a(context, "pref", "fn", f4039x);
                                            C3875cw.m2953a(context, "pref", "mpn", f4040y);
                                            C3875cw.m2955a(context, "pref", "igu", f4041z);
                                        }
                                        jSONObject6 = aVar.f4239i;
                                        if (jSONObject6 != null && C3886l.m3134a(jSONObject6.optString("able"), false)) {
                                            m2869a = m2869a(jSONObject6, "fs");
                                            if (m2869a != null) {
                                                f4030o = m2869a.f4044c;
                                                try {
                                                    f4031p = Integer.parseInt(m2869a.f4043b);
                                                } catch (Throwable th4) {
                                                    C3880f.m3097a(th4, "AuthUtil", "loadconfig part2");
                                                }
                                            }
                                            m2869a2 = m2869a(jSONObject6, "us");
                                            if (m2869a2 != null) {
                                                f4032q = m2869a2.f4044c;
                                                f4034s = m2869a2.f4042a;
                                                try {
                                                    f4033r = Integer.parseInt(m2869a2.f4043b);
                                                } catch (Throwable th5) {
                                                    C3880f.m3097a(th5, "AuthUtil", "loadconfig part1");
                                                }
                                                if (f4033r < 2) {
                                                    f4032q = false;
                                                }
                                            }
                                            m2869a3 = m2869a(jSONObject6, "rs");
                                            if (m2869a3 != null) {
                                                boolean z = m2869a3.f4044c;
                                                f4026k = z;
                                                if (z) {
                                                    f4028m = C3876cx.m2985b();
                                                    str4 = str3;
                                                    try {
                                                        f4029n = m2869a3.f4045d * 1000;
                                                    } catch (Throwable th6) {
                                                        th = th6;
                                                        C3880f.m3097a(th, "AuthUtil", "loadConfigData_locate");
                                                        jSONObject5 = aVar.f4241k;
                                                        if (jSONObject5 != null) {
                                                        }
                                                        jSONObject4 = aVar.f4242l;
                                                        if (jSONObject4 != null) {
                                                        }
                                                        bVar2 = aVar.f4249s;
                                                        if (bVar2 != null) {
                                                        }
                                                        bVar = aVar.f4251u;
                                                        if (bVar != null) {
                                                        }
                                                        jSONObject3 = aVar.f4236f;
                                                        if (jSONObject3 != null) {
                                                        }
                                                        str5 = "pref";
                                                        jSONObject2 = aVar.f4235e;
                                                        if (jSONObject2 != null) {
                                                        }
                                                        jSONObject = aVar.f4233c;
                                                        if (jSONObject == null) {
                                                        }
                                                    }
                                                } else {
                                                    str4 = str3;
                                                }
                                                try {
                                                    f4027l = Integer.parseInt(m2869a3.f4043b) * 1000;
                                                } catch (Throwable th7) {
                                                    C3880f.m3097a(th7, "AuthUtil", "loadconfig part");
                                                }
                                                jSONObject5 = aVar.f4241k;
                                                if (jSONObject5 != null) {
                                                    boolean m3134a4 = C3886l.m3134a(jSONObject5.optString("able"), false);
                                                    f3997M = m3134a4;
                                                    if (m3134a4) {
                                                        int optInt3 = jSONObject5.optInt("c", 0);
                                                        if (optInt3 == 0) {
                                                            f3998N = 3000;
                                                        } else {
                                                            f3998N = optInt3 * 1000;
                                                        }
                                                        f3999O = jSONObject5.getInt("t") / 2;
                                                    }
                                                }
                                                jSONObject4 = aVar.f4242l;
                                                if (jSONObject4 != null) {
                                                    boolean m3134a5 = C3886l.m3134a(jSONObject4.optString("able"), true);
                                                    f4000P = m3134a5;
                                                    if (m3134a5) {
                                                        f4001Q = jSONObject4.optInt("c", 300) * 1000;
                                                    }
                                                }
                                                bVar2 = aVar.f4249s;
                                                if (bVar2 != null) {
                                                    f4003S = m2874a(context, bVar2, "Collection", "1.0.0");
                                                }
                                                bVar = aVar.f4251u;
                                                if (bVar != null) {
                                                    String str14 = str;
                                                    boolean m2874a = m2874a(context, bVar, str14, "1.0.0");
                                                    f4005U = m2874a;
                                                    if (!m2874a && C3873cu.m2936a(context, C3880f.m3092a(str14, "1.0.0"))) {
                                                        C3874cv.m2947a(context, str14, "config|get dnsDex able is false");
                                                    }
                                                }
                                                jSONObject3 = aVar.f4236f;
                                                if (jSONObject3 != null) {
                                                    f4008X = C3886l.m3134a(jSONObject3.optString("able"), false);
                                                    f4009Y = jSONObject3.optLong("sysTime", C3876cx.m2969a());
                                                    f4010Z = jSONObject3.optInt("n", 1);
                                                    f4012aa = jSONObject3.optInt("nh", 1);
                                                    if (f4008X && (f4010Z == -1 || f4010Z >= f4012aa)) {
                                                        JSONArray optJSONArray3 = jSONObject3.optJSONArray("l");
                                                        int i3 = 0;
                                                        while (i3 < optJSONArray3.length()) {
                                                            try {
                                                                optJSONObject = optJSONArray3.optJSONObject(i3);
                                                                c3870cr = new C3870cr();
                                                                m3134a = C3886l.m3134a(optJSONObject.optString("able", "false"), false);
                                                                c3870cr.f4046a = m3134a;
                                                            } catch (Throwable unused2) {
                                                            }
                                                            if (m3134a) {
                                                                c3870cr.f4047b = optJSONObject.optString("pn", str9);
                                                                c3870cr.f4048c = optJSONObject.optString("cn", str9);
                                                                c3870cr.f4050e = optJSONObject.optString("a", str9);
                                                                JSONArray optJSONArray4 = optJSONObject.optJSONArray("b");
                                                                if (optJSONArray4 != null) {
                                                                    ArrayList arrayList = new ArrayList();
                                                                    jSONArray = optJSONArray3;
                                                                    int i4 = 0;
                                                                    while (i4 < optJSONArray4.length()) {
                                                                        try {
                                                                            JSONObject optJSONObject2 = optJSONArray4.optJSONObject(i4);
                                                                            str7 = str9;
                                                                            try {
                                                                                HashMap hashMap = new HashMap();
                                                                                JSONArray jSONArray2 = optJSONArray4;
                                                                                try {
                                                                                    str8 = str10;
                                                                                    try {
                                                                                        hashMap.put(optJSONObject2.optString("k"), optJSONObject2.optString("v"));
                                                                                        arrayList.add(hashMap);
                                                                                    } catch (Throwable unused3) {
                                                                                    }
                                                                                } catch (Throwable unused4) {
                                                                                    str8 = str10;
                                                                                }
                                                                                i4++;
                                                                                str9 = str7;
                                                                                optJSONArray4 = jSONArray2;
                                                                                str10 = str8;
                                                                            } catch (Throwable unused5) {
                                                                            }
                                                                        } catch (Throwable unused6) {
                                                                        }
                                                                    }
                                                                    str7 = str9;
                                                                    str6 = str10;
                                                                    try {
                                                                        c3870cr.f4049d = arrayList;
                                                                    } catch (Throwable unused7) {
                                                                    }
                                                                } else {
                                                                    jSONArray = optJSONArray3;
                                                                    str7 = str9;
                                                                    str6 = str10;
                                                                }
                                                                c3870cr.f4051f = C3886l.m3134a(optJSONObject.optString("is", "false"), false);
                                                                f4007W.add(c3870cr);
                                                                i3++;
                                                                optJSONArray3 = jSONArray;
                                                                str9 = str7;
                                                                str10 = str6;
                                                            }
                                                            jSONArray = optJSONArray3;
                                                            str7 = str9;
                                                            str6 = str10;
                                                            i3++;
                                                            optJSONArray3 = jSONArray;
                                                            str9 = str7;
                                                            str10 = str6;
                                                        }
                                                        str5 = str10;
                                                        try {
                                                            JSONArray optJSONArray5 = jSONObject3.optJSONArray("sl");
                                                            if (optJSONArray5 != null) {
                                                                for (int i5 = 0; i5 < optJSONArray5.length(); i5++) {
                                                                    String optString = optJSONArray5.optJSONObject(i5).optString("pan");
                                                                    if (!TextUtils.isEmpty(optString)) {
                                                                        f4013ab.add(optString);
                                                                    }
                                                                }
                                                            }
                                                        } catch (Throwable th8) {
                                                            th = th8;
                                                            C3880f.m3097a(th, "AuthUtil", "loadConfigData_otherServiceList");
                                                            jSONObject2 = aVar.f4235e;
                                                            if (jSONObject2 != null) {
                                                            }
                                                            jSONObject = aVar.f4233c;
                                                            if (jSONObject == null) {
                                                            }
                                                        }
                                                        jSONObject2 = aVar.f4235e;
                                                        if (jSONObject2 != null) {
                                                            boolean m3134a6 = C3886l.m3134a(jSONObject2.optString("able"), true);
                                                            f4014ac = m3134a6;
                                                            if (m3134a6) {
                                                                f4015ad = jSONObject2.optInt("c", f4015ad);
                                                            }
                                                        }
                                                        jSONObject = aVar.f4233c;
                                                        if (jSONObject == null) {
                                                            return true;
                                                        }
                                                        try {
                                                            f4018c = jSONObject.optInt("ht", 30) * 60 * 1000;
                                                            f4019d = jSONObject.optInt("at", 60) * 60 * 1000;
                                                        } catch (Throwable th9) {
                                                            C3880f.m3097a(th9, "AuthUtil", "requestSdkAuthInterval");
                                                        }
                                                        try {
                                                            boolean m3134a7 = C3886l.m3134a(jSONObject.optString("ofl"), true);
                                                            f4011a = m3134a7;
                                                            C3848bw.f3831a = m3134a7;
                                                            if (f4011a) {
                                                                try {
                                                                    C3886l.a.b bVar3 = aVar.f4250t;
                                                                    if (bVar3 != null) {
                                                                        f4004T = m2874a(context, bVar3, "OfflineLocation", "1.0.0");
                                                                        C3875cw.m2955a(context, str5, "oAble", f4004T);
                                                                    }
                                                                } catch (Throwable th10) {
                                                                    C3880f.m3097a(th10, "AuthUtil", "loadConfigData_OfflineDex");
                                                                }
                                                            }
                                                        } catch (Throwable th11) {
                                                            C3880f.m3097a(th11, "AuthUtil", "loadConfigData_offlineLoc");
                                                        }
                                                        try {
                                                            f4016ae = C3886l.m3134a(jSONObject.optString("ila"), f4016ae);
                                                        } catch (Throwable th12) {
                                                            C3880f.m3097a(th12, "AuthUtil", "loadConfigData_indoor");
                                                        }
                                                        try {
                                                            boolean m3134a8 = C3886l.m3134a(jSONObject.optString("icbd"), true);
                                                            f4017b = m3134a8;
                                                            if (!m3134a8) {
                                                                return true;
                                                            }
                                                            C3819au.m2480a(context, str4);
                                                            return true;
                                                        } catch (Throwable th13) {
                                                            C3880f.m3097a(th13, "AuthUtil", "loadConfigData_CallBackDex");
                                                            return true;
                                                        }
                                                    }
                                                }
                                                str5 = "pref";
                                                jSONObject2 = aVar.f4235e;
                                                if (jSONObject2 != null) {
                                                }
                                                jSONObject = aVar.f4233c;
                                                if (jSONObject == null) {
                                                }
                                            }
                                        }
                                        str4 = str3;
                                        jSONObject5 = aVar.f4241k;
                                        if (jSONObject5 != null) {
                                        }
                                        jSONObject4 = aVar.f4242l;
                                        if (jSONObject4 != null) {
                                        }
                                        bVar2 = aVar.f4249s;
                                        if (bVar2 != null) {
                                        }
                                        bVar = aVar.f4251u;
                                        if (bVar != null) {
                                        }
                                        jSONObject3 = aVar.f4236f;
                                        if (jSONObject3 != null) {
                                        }
                                        str5 = "pref";
                                        jSONObject2 = aVar.f4235e;
                                        if (jSONObject2 != null) {
                                        }
                                        jSONObject = aVar.f4233c;
                                        if (jSONObject == null) {
                                        }
                                    }
                                }
                                str3 = str2;
                                c9033a = aVar.f4246p;
                                if (c9033a != null) {
                                }
                                jSONObject6 = aVar.f4239i;
                                if (jSONObject6 != null) {
                                    m2869a = m2869a(jSONObject6, "fs");
                                    if (m2869a != null) {
                                    }
                                    m2869a2 = m2869a(jSONObject6, "us");
                                    if (m2869a2 != null) {
                                    }
                                    m2869a3 = m2869a(jSONObject6, "rs");
                                    if (m2869a3 != null) {
                                    }
                                }
                                str4 = str3;
                                jSONObject5 = aVar.f4241k;
                                if (jSONObject5 != null) {
                                }
                                jSONObject4 = aVar.f4242l;
                                if (jSONObject4 != null) {
                                }
                                bVar2 = aVar.f4249s;
                                if (bVar2 != null) {
                                }
                                bVar = aVar.f4251u;
                                if (bVar != null) {
                                }
                                jSONObject3 = aVar.f4236f;
                                if (jSONObject3 != null) {
                                }
                                str5 = "pref";
                                jSONObject2 = aVar.f4235e;
                                if (jSONObject2 != null) {
                                }
                                jSONObject = aVar.f4233c;
                                if (jSONObject == null) {
                                }
                            }
                        }
                        C3819au.m2479a(context, (C3818at) null, m3091a2);
                        cVar = aVar.f4248r;
                        if (cVar != null) {
                        }
                        str3 = str2;
                        c9033a = aVar.f4246p;
                        if (c9033a != null) {
                        }
                        jSONObject6 = aVar.f4239i;
                        if (jSONObject6 != null) {
                        }
                        str4 = str3;
                        jSONObject5 = aVar.f4241k;
                        if (jSONObject5 != null) {
                        }
                        jSONObject4 = aVar.f4242l;
                        if (jSONObject4 != null) {
                        }
                        bVar2 = aVar.f4249s;
                        if (bVar2 != null) {
                        }
                        bVar = aVar.f4251u;
                        if (bVar != null) {
                        }
                        jSONObject3 = aVar.f4236f;
                        if (jSONObject3 != null) {
                        }
                        str5 = "pref";
                        jSONObject2 = aVar.f4235e;
                        if (jSONObject2 != null) {
                        }
                        jSONObject = aVar.f4233c;
                        if (jSONObject == null) {
                        }
                    }
                }
            }
            jSONObject = aVar.f4233c;
            if (jSONObject == null) {
            }
        } catch (Throwable th14) {
            C3880f.m3097a(th14, "AuthUtil", "loadConfigData_hotUpdate");
            return true;
        }
        str = "HttpDNS";
        str2 = "loc";
        jSONObject8 = aVar.f4237g;
        if (jSONObject8 != null) {
        }
        jSONObject7 = aVar.f4238h;
        if (jSONObject7 != null) {
        }
        C3893s m3091a22 = C3880f.m3091a(str2);
        dVar = aVar.f4247q;
        if (dVar != null) {
        }
        C3819au.m2479a(context, (C3818at) null, m3091a22);
        cVar = aVar.f4248r;
        if (cVar != null) {
        }
        str3 = str2;
        c9033a = aVar.f4246p;
        if (c9033a != null) {
        }
        jSONObject6 = aVar.f4239i;
        if (jSONObject6 != null) {
        }
        str4 = str3;
        jSONObject5 = aVar.f4241k;
        if (jSONObject5 != null) {
        }
        jSONObject4 = aVar.f4242l;
        if (jSONObject4 != null) {
        }
        bVar2 = aVar.f4249s;
        if (bVar2 != null) {
        }
        bVar = aVar.f4251u;
        if (bVar != null) {
        }
        jSONObject3 = aVar.f4236f;
        if (jSONObject3 != null) {
        }
        str5 = "pref";
        jSONObject2 = aVar.f4235e;
        if (jSONObject2 != null) {
        }
    }

    /* renamed from: b */
    public static int m2876b() {
        return f4031p;
    }

    /* renamed from: b */
    public static boolean m2877b(long j) {
        if (!f4000P) {
            return false;
        }
        long m2969a = C3876cx.m2969a() - j;
        long j2 = f4001Q;
        return j2 < 0 || m2969a < j2;
    }

    /* renamed from: b */
    public static boolean m2878b(Context context) {
        if (!f3986B) {
            return false;
        }
        if (f3987C == -1 || f3988D == 0) {
            return true;
        }
        if (!C3876cx.m2990b(f3988D, C3875cw.m2951a(context, "pref", "nowtime"))) {
            m2890h(context);
            C3875cw.m2953a(context, "pref", "count", 1);
            return true;
        }
        int m2957b = C3875cw.m2957b(context, "pref", "count", 0);
        if (m2957b >= f3987C) {
            return false;
        }
        C3875cw.m2953a(context, "pref", "count", m2957b + 1);
        return true;
    }

    /* renamed from: c */
    public static boolean m2879c() {
        return f4032q;
    }

    /* renamed from: c */
    public static boolean m2880c(Context context) {
        if (!f3990F) {
            return false;
        }
        if (f3991G == -1 || f3992H == 0) {
            return true;
        }
        if (!C3876cx.m2990b(f3992H, C3875cw.m2951a(context, "pref", "pushSerTime"))) {
            m2892i(context);
            C3875cw.m2953a(context, "pref", "pushCount", 1);
            return true;
        }
        int m2957b = C3875cw.m2957b(context, "pref", "pushCount", 0);
        if (m2957b >= f3991G) {
            return false;
        }
        C3875cw.m2953a(context, "pref", "pushCount", m2957b + 1);
        return true;
    }

    /* renamed from: d */
    public static int m2881d() {
        return f4033r;
    }

    /* renamed from: d */
    public static boolean m2882d(Context context) {
        int i;
        int i2;
        int m2957b;
        if (!f3996L || (i = f4037v) <= 0 || (i2 = f4036u) <= 0 || i > i2) {
            return false;
        }
        long m2951a = C3875cw.m2951a(context, "abcd", "lct");
        long m2951a2 = C3875cw.m2951a(context, "abcd", "lst");
        long m2985b = C3876cx.m2985b();
        if (m2985b < m2951a) {
            C3875cw.m2954a(context, "abcd", "lct", m2985b);
            return false;
        }
        if (m2985b - m2951a > 86400000) {
            C3875cw.m2954a(context, "abcd", "lct", m2985b);
            C3875cw.m2953a(context, "abcd", "t", 0);
        }
        if (m2985b - m2951a2 < f4035t || (m2957b = C3875cw.m2957b(context, "abcd", "t", 0) + 1) > f4036u) {
            return false;
        }
        C3875cw.m2954a(context, "abcd", "lst", m2985b);
        C3875cw.m2953a(context, "abcd", "t", m2957b);
        return true;
    }

    /* renamed from: e */
    public static void m2883e(Context context) {
        try {
            f4038w = C3875cw.m2958b(context, "pref", MqttServiceConstants.TRACE_EXCEPTION, true);
            m2885f(context);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            f4004T = C3875cw.m2958b(context, "pref", "oAble", false);
        } catch (Throwable th2) {
            C3880f.m3097a(th2, "AuthUtil", "loadLastAbleState p2");
        }
        try {
            f4039x = C3875cw.m2957b(context, "pref", "fn", f4039x);
            f4040y = C3875cw.m2957b(context, "pref", "mpn", f4040y);
            f4041z = C3875cw.m2958b(context, "pref", "igu", f4041z);
            C3842bq.m2632a(f4039x, f4041z);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: e */
    public static boolean m2884e() {
        return f4034s;
    }

    /* renamed from: f */
    public static void m2885f(Context context) {
        try {
            C3893s m3091a = C3880f.m3091a("loc");
            m3091a.m3207a(f4038w);
            C3900z.m3263a(context, m3091a);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: f */
    public static boolean m2886f() {
        C3848bw.f3831a = f4011a;
        return f4011a;
    }

    /* renamed from: g */
    public static String m2887g() {
        return f4020e;
    }

    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* renamed from: g */
    public static boolean m2888g(Context context) {
        int i;
        int i2;
        if (f4008X && (i = f4010Z) != 0 && (i2 = f4012aa) != 0 && f4009Y != 0 && (i == -1 || i >= i2)) {
            List<String> list = f4013ab;
            if (list != null && list.size() > 0) {
                Iterator<String> it = f4013ab.iterator();
                while (it.hasNext()) {
                    if (C3876cx.m2991b(context, it.next())) {
                        return false;
                    }
                }
            }
            ?? r3 = 1;
            if (f4010Z == -1 && f4012aa == -1) {
                return true;
            }
            long m2951a = C3875cw.m2951a(context, "pref", "ots");
            long m2951a2 = C3875cw.m2951a(context, "pref", "otsh");
            int m2957b = C3875cw.m2957b(context, "pref", "otn", 0);
            int m2957b2 = C3875cw.m2957b(context, "pref", "otnh", 0);
            if (f4010Z != -1) {
                if (!C3876cx.m2990b(f4009Y, m2951a)) {
                    try {
                        SharedPreferences.Editor edit = context.getSharedPreferences("pref", 0).edit();
                        edit.putLong("ots", f4009Y);
                        edit.putLong("otsh", f4009Y);
                        edit.putInt("otn", 0);
                        edit.putInt("otnh", 0);
                        C3875cw.m2956a(edit);
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "AuthUtil", "resetPrefsBind");
                    }
                    C3875cw.m2953a(context, "pref", "otn", 1);
                    C3875cw.m2953a(context, "pref", "otnh", 1);
                    return true;
                }
                r3 = 1;
                r3 = 1;
                if (m2957b < f4010Z) {
                    if (f4012aa == -1) {
                        C3875cw.m2953a(context, "pref", "otn", m2957b + 1);
                        C3875cw.m2953a(context, "pref", "otnh", 0);
                        return true;
                    }
                    if (!C3876cx.m2973a(f4009Y, m2951a2)) {
                        C3875cw.m2954a(context, "pref", "otsh", f4009Y);
                        C3875cw.m2953a(context, "pref", "otn", m2957b + 1);
                        C3875cw.m2953a(context, "pref", "otnh", 1);
                        return true;
                    }
                    if (m2957b2 < f4012aa) {
                        C3875cw.m2953a(context, "pref", "otn", m2957b + 1);
                        C3875cw.m2953a(context, "pref", "otnh", m2957b2 + 1);
                        return true;
                    }
                }
            }
            if (f4010Z == -1) {
                C3875cw.m2953a(context, "pref", "otn", 0);
                if (f4012aa == -1) {
                    C3875cw.m2953a(context, "pref", "otnh", 0);
                    return r3;
                }
                if (!C3876cx.m2973a(f4009Y, m2951a2)) {
                    C3875cw.m2954a(context, "pref", "otsh", f4009Y);
                    C3875cw.m2953a(context, "pref", "otnh", (int) r3);
                    return r3;
                }
                if (m2957b2 < f4012aa) {
                    C3875cw.m2953a(context, "pref", "otnh", m2957b2 + r3);
                    return r3;
                }
            }
        }
        return false;
    }

    /* renamed from: h */
    public static String m2889h() {
        return f4021f;
    }

    /* renamed from: h */
    private static void m2890h(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("nowtime", f3988D);
            edit.putInt("count", 0);
            C3875cw.m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    /* renamed from: i */
    public static String m2891i() {
        return f4022g;
    }

    /* renamed from: i */
    private static void m2892i(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("pushSerTime", f3992H);
            edit.putInt("pushCount", 0);
            C3875cw.m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    /* renamed from: j */
    public static String m2893j() {
        return f4023h;
    }

    /* renamed from: k */
    public static String m2894k() {
        return f4024i;
    }

    /* renamed from: l */
    public static String m2895l() {
        return f4025j;
    }

    /* renamed from: m */
    public static ArrayList<String> m2896m() {
        return f3989E;
    }

    /* renamed from: n */
    public static ArrayList<String> m2897n() {
        return f3993I;
    }

    /* renamed from: o */
    public static boolean m2898o() {
        return f4038w;
    }

    /* renamed from: p */
    public static int m2899p() {
        return f4040y;
    }

    /* renamed from: q */
    public static boolean m2900q() {
        return f3985A;
    }

    /* renamed from: r */
    public static void m2901r() {
        f3985A = false;
    }

    /* renamed from: s */
    public static boolean m2902s() {
        return f3997M;
    }

    /* renamed from: t */
    public static boolean m2903t() {
        return f4000P;
    }

    /* renamed from: u */
    public static int m2904u() {
        return f4002R;
    }

    /* renamed from: v */
    public static boolean m2905v() {
        return f4003S;
    }

    /* renamed from: w */
    public static boolean m2906w() {
        return f4004T;
    }

    /* renamed from: x */
    public static List<C3870cr> m2907x() {
        return f4007W;
    }

    /* renamed from: y */
    public static boolean m2908y() {
        return f4014ac;
    }

    /* renamed from: z */
    public static int m2909z() {
        return f4015ad;
    }
}
