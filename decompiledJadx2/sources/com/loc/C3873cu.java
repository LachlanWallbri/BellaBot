package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ReportUtil.java */
/* renamed from: com.loc.cu */
/* loaded from: classes4.dex */
public final class C3873cu {

    /* renamed from: g */
    private static List<C3843br> f4055g = new ArrayList();

    /* renamed from: a */
    public SparseArray<Long> f4056a = new SparseArray<>();

    /* renamed from: b */
    public int f4057b = -1;

    /* renamed from: c */
    public long f4058c = 0;

    /* renamed from: d */
    String[] f4059d = {"ol", "cl", "gl", "ha", "bs", "ds"};

    /* renamed from: e */
    public int f4060e = -1;

    /* renamed from: f */
    public long f4061f = -1;

    /* compiled from: ReportUtil.java */
    /* renamed from: com.loc.cu$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a */
        static final /* synthetic */ int[] f4062a = new int[AMapLocationClientOption.AMapLocationMode.values().length];

        static {
            try {
                f4062a[AMapLocationClientOption.AMapLocationMode.Battery_Saving.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4062a[AMapLocationClientOption.AMapLocationMode.Device_Sensors.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4062a[AMapLocationClientOption.AMapLocationMode.Hight_Accuracy.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: a */
    public static void m2927a(Context context) {
        if (context != null) {
            try {
                if (C3869cq.m2898o() && f4055g != null && f4055g.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(f4055g);
                    C3844bs.m2642a(arrayList, context);
                    f4055g.clear();
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "ReportUtil", "destroy");
            }
        }
    }

    /* renamed from: a */
    public static void m2928a(Context context, int i, int i2, long j, long j2) {
        if (i == -1 || i2 == -1 || context == null) {
            return;
        }
        try {
            if (C3869cq.m2898o()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("param_int_first", i);
                jSONObject.put("param_int_second", i2);
                jSONObject.put("param_long_first", j);
                jSONObject.put("param_long_second", j2);
                m2934a(context, "O012", jSONObject);
            }
        } catch (Throwable th) {
            try {
                C3880f.m3097a(th, "ReportUtil", "applyStatisticsEx");
            } catch (Throwable th2) {
                C3880f.m3097a(th2, "ReportUtil", "reportServiceAliveTime");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x003f, code lost:
    
        m2933a(r5, "O005", r0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0044, code lost:
    
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m2929a(Context context, int i, AMapLocation aMapLocation) {
        String str;
        int errorCode;
        if (context == null || aMapLocation == null) {
            return;
        }
        try {
            if (C3869cq.m2898o()) {
                boolean z = false;
                int i2 = aMapLocation.getErrorCode() == 0 ? 1 : 0;
                switch (i) {
                    case 2:
                    case 4:
                    case 7:
                    case 8:
                        str = "cache";
                        z = true;
                        break;
                    case 5:
                    case 6:
                        z = true;
                    case 3:
                    default:
                        str = "net";
                        break;
                }
                if (aMapLocation.getErrorCode() != 0 && ((errorCode = aMapLocation.getErrorCode()) == 4 || errorCode == 5 || errorCode == 6 || errorCode == 11)) {
                    z = true;
                    str = "net";
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "reportBatting");
        }
    }

    /* renamed from: a */
    public static void m2930a(Context context, C3872ct c3872ct) {
        int i;
        if (context != null) {
            try {
                if (C3869cq.m2898o()) {
                    AMapLocationServer m2926c = c3872ct.m2926c();
                    int intValue = Long.valueOf(c3872ct.m2924b() - c3872ct.m2921a()).intValue();
                    String str = "net";
                    boolean z = true;
                    boolean z2 = false;
                    if (m2926c != null) {
                        i = Long.valueOf(m2926c.m559i()).intValue();
                        int locationType = m2926c.getLocationType();
                        if (locationType == 1) {
                            z = false;
                        } else if (locationType == 2 || locationType == 4) {
                            str = "cache";
                            z2 = true;
                        } else if (locationType != 5) {
                        }
                    } else {
                        i = 0;
                    }
                    if (z) {
                        if (!z2) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("param_int_first", i);
                            jSONObject.put("param_int_second", intValue);
                            m2934a(context, "O003", jSONObject);
                        }
                        m2933a(context, "O002", intValue, str);
                    }
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "ReportUtil", "reportLBSLocUseTime");
            }
        }
    }

    /* renamed from: a */
    public static void m2931a(Context context, String str) {
        try {
            m2933a(context, "O010", 0, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "reportDex_dexFunction");
        }
    }

    /* renamed from: a */
    public static void m2932a(Context context, String str, int i) {
        try {
            m2933a(context, "O009", i, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "reportDex_dexLoadClass");
        }
    }

    /* renamed from: a */
    private static void m2933a(Context context, String str, int i, String str2) {
        if (context != null) {
            try {
                if (C3869cq.m2898o()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (i != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i);
                    }
                    m2934a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    /* renamed from: a */
    private static void m2934a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (C3869cq.m2898o()) {
                    C3843br c3843br = new C3843br(context, "loc", "3.3.0", str);
                    c3843br.m2638a(jSONObject.toString());
                    f4055g.add(c3843br);
                    if (f4055g.size() >= 30) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(f4055g);
                        C3844bs.m2642a(arrayList, context);
                        f4055g.clear();
                    }
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    /* renamed from: a */
    public static void m2935a(String str, String str2) {
        try {
            C3900z.m3266b(C3880f.m3091a("loc"), str2, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "reportLog");
        }
    }

    /* renamed from: a */
    public static boolean m2936a(Context context, C3893s c3893s) {
        try {
            return C3819au.m2481a(context, c3893s);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static void m2937b(Context context, C3872ct c3872ct) {
        if (context != null) {
            try {
                if (C3869cq.m2898o()) {
                    m2933a(context, "O004", Long.valueOf(c3872ct.m2924b() - c3872ct.m2921a()).intValue(), null);
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    /* renamed from: a */
    public final void m2938a(Context context, int i) {
        try {
            if (this.f4057b == i) {
                return;
            }
            if (this.f4057b != -1 && this.f4057b != i) {
                this.f4056a.append(this.f4057b, Long.valueOf((C3876cx.m2985b() - this.f4058c) + this.f4056a.get(this.f4057b, 0L).longValue()));
            }
            this.f4058c = C3876cx.m2985b() - C3875cw.m2951a(context, "pref", this.f4059d[i]);
            this.f4057b = i;
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "setLocationType");
        }
    }

    /* renamed from: a */
    public final void m2939a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        try {
            int i = AnonymousClass1.f4062a[aMapLocationClientOption.getLocationMode().ordinal()];
            int i2 = 3;
            if (i == 1) {
                i2 = 4;
            } else if (i == 2) {
                i2 = 5;
            } else if (i != 3) {
                i2 = -1;
            }
            if (this.f4060e == i2) {
                return;
            }
            if (this.f4060e != -1 && this.f4060e != i2) {
                this.f4056a.append(this.f4060e, Long.valueOf((C3876cx.m2985b() - this.f4061f) + this.f4056a.get(this.f4060e, 0L).longValue()));
            }
            this.f4061f = C3876cx.m2985b() - C3875cw.m2951a(context, "pref", this.f4059d[i2]);
            this.f4060e = i2;
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "setLocationMode");
        }
    }

    /* renamed from: b */
    public final void m2940b(Context context) {
        try {
            long m2985b = C3876cx.m2985b() - this.f4058c;
            if (this.f4057b != -1) {
                this.f4056a.append(this.f4057b, Long.valueOf(m2985b + this.f4056a.get(this.f4057b, 0L).longValue()));
            }
            long m2985b2 = C3876cx.m2985b() - this.f4061f;
            if (this.f4060e != -1) {
                this.f4056a.append(this.f4060e, Long.valueOf(m2985b2 + this.f4056a.get(this.f4060e, 0L).longValue()));
            }
            for (int i = 0; i < 6; i++) {
                long longValue = this.f4056a.get(i, 0L).longValue();
                if (longValue > 0 && longValue > C3875cw.m2951a(context, "pref", this.f4059d[i])) {
                    C3875cw.m2954a(context, "pref", this.f4059d[i], longValue);
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    /* renamed from: c */
    public final int m2941c(Context context) {
        try {
            long m2951a = C3875cw.m2951a(context, "pref", this.f4059d[2]);
            long m2951a2 = C3875cw.m2951a(context, "pref", this.f4059d[0]);
            long m2951a3 = C3875cw.m2951a(context, "pref", this.f4059d[1]);
            if (m2951a == 0 && m2951a2 == 0 && m2951a3 == 0) {
                return -1;
            }
            long j = m2951a2 - m2951a;
            long j2 = m2951a3 - m2951a;
            return m2951a > j ? m2951a > j2 ? 2 : 1 : j > j2 ? 0 : 1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: d */
    public final int m2942d(Context context) {
        try {
            long m2951a = C3875cw.m2951a(context, "pref", this.f4059d[3]);
            long m2951a2 = C3875cw.m2951a(context, "pref", this.f4059d[4]);
            long m2951a3 = C3875cw.m2951a(context, "pref", this.f4059d[5]);
            if (m2951a == 0 && m2951a2 == 0 && m2951a3 == 0) {
                return -1;
            }
            return m2951a > m2951a2 ? m2951a > m2951a3 ? 3 : 5 : m2951a2 > m2951a3 ? 4 : 5;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: e */
    public final void m2943e(Context context) {
        for (int i = 0; i < this.f4059d.length; i++) {
            try {
                C3875cw.m2954a(context, "pref", this.f4059d[i], 0L);
            } catch (Throwable unused) {
                return;
            }
        }
    }
}
