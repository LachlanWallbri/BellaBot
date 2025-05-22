package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.reactivex.annotations.SchedulerSupport;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* compiled from: LocNetManager.java */
/* renamed from: com.loc.cm */
/* loaded from: classes4.dex */
public final class C3865cm {

    /* renamed from: f */
    private static C3865cm f3924f;

    /* renamed from: a */
    C3834bi f3925a;

    /* renamed from: e */
    boolean f3929e;

    /* renamed from: g */
    private Object f3930g;

    /* renamed from: i */
    private Context f3932i;

    /* renamed from: h */
    private String f3931h = "apilocatesrc.amap.com";

    /* renamed from: b */
    boolean f3926b = false;

    /* renamed from: c */
    volatile int f3927c = 0;

    /* renamed from: d */
    public String f3928d = "com.autonavi.httpdns.HttpDnsManager";

    /* renamed from: j */
    private int f3933j = 0;

    /* renamed from: k */
    private ExecutorService f3934k = null;

    /* renamed from: l */
    private int f3935l = C3880f.f4181c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LocNetManager.java */
    /* renamed from: com.loc.cm$a */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: a */
        C3866cn f3936a;

        a(C3866cn c3866cn) {
            this.f3936a = null;
            this.f3936a = c3866cn;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C3865cm.this.f3927c++;
            C3865cm.this.m2855a(this.f3936a);
            C3865cm c3865cm = C3865cm.this;
            c3865cm.f3927c--;
        }
    }

    private C3865cm(Context context) {
        this.f3925a = null;
        this.f3930g = null;
        int i = 0;
        this.f3932i = null;
        this.f3929e = false;
        this.f3932i = context;
        try {
            if (this.f3930g == null && !this.f3929e) {
                C3893s m3092a = C3880f.m3092a("HttpDNS", "1.0.0");
                this.f3929e = C3873cu.m2936a(context, m3092a);
                if (this.f3929e) {
                    try {
                        this.f3930g = C3819au.m2476a(context, m3092a, this.f3928d, null, new Class[]{Context.class}, new Object[]{context});
                    } catch (Throwable unused) {
                    }
                    if (this.f3930g != null) {
                        i = 1;
                    }
                    C3873cu.m2932a(context, "HttpDns", i);
                } else {
                    this.f3929e = true;
                }
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "APS", "initHttpDns");
        }
        this.f3925a = C3834bi.m2600a();
    }

    /* renamed from: a */
    public static C3865cm m2848a(Context context) {
        if (f3924f == null) {
            f3924f = new C3865cm(context);
        }
        return f3924f;
    }

    /* renamed from: a */
    private String m2849a(Context context, String str) {
        if (!m2851c(context)) {
            return null;
        }
        try {
            return (String) C3871cs.m2917a(this.f3930g, "getIpByHostAsync", str);
        } catch (Throwable unused) {
            C3873cu.m2931a(context, "HttpDns");
            return null;
        }
    }

    /* renamed from: b */
    private static boolean m2850b(Context context) {
        int i;
        String str = null;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                str = System.getProperty("http.proxyHost");
                String property = System.getProperty("http.proxyPort");
                if (property == null) {
                    property = "-1";
                }
                i = Integer.parseInt(property);
            } else {
                str = Proxy.getHost(context);
                i = Proxy.getPort(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            i = -1;
        }
        return (str == null || i == -1) ? false : true;
    }

    /* renamed from: c */
    private boolean m2851c(Context context) {
        return (this.f3930g == null || m2850b(context)) ? false : true;
    }

    /* renamed from: a */
    public final int m2852a() {
        return this.f3933j;
    }

    /* renamed from: a */
    public final C3840bo m2853a(Context context, JSONObject jSONObject, byte[] bArr, String str, boolean z) throws Throwable {
        if (C3876cx.m2979a(jSONObject, "httptimeout")) {
            try {
                this.f3935l = jSONObject.getInt("httptimeout");
            } catch (Throwable th) {
                C3880f.m3097a(th, "LocNetManager", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ);
            }
        }
        if (C3876cx.m2967a(C3876cx.m2997c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        C3866cn c3866cn = new C3866cn(context, C3880f.m3091a("loc"));
        hashMap.clear();
        hashMap.put("Content-Type", "application/octet-stream");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("gzipped", "1");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        hashMap.put("KEY", C3885k.m3127f(context));
        hashMap.put("enginever", "4.7");
        String m3136a = C3887m.m3136a();
        String m3137a = C3887m.m3137a(context, m3136a, "key=" + C3885k.m3127f(context));
        hashMap.put("ts", m3136a);
        hashMap.put("scode", m3137a);
        String str2 = z ? "loc" : "locf";
        hashMap.put("encr", "1");
        c3866cn.f3942j = z;
        c3866cn.f3943k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "3.3.0", str2, 3);
        c3866cn.f3938f = hashMap;
        c3866cn.f3939g = str;
        c3866cn.f3940h = C3876cx.m2982a(bArr);
        c3866cn.m2627a(C3891q.m3194a(context));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("output", "bin");
        hashMap2.put("policy", "3103");
        c3866cn.f3944l = hashMap2;
        c3866cn.m2626a(this.f3935l);
        c3866cn.m2628b(this.f3935l);
        this.f3926b = false;
        boolean optBoolean = jSONObject.optBoolean("locationProtocol", false);
        if (optBoolean) {
            c3866cn.f3939g = c3866cn.mo2488b().replace(HttpHost.DEFAULT_SCHEME_NAME, "https");
        } else {
            if ((C3875cw.m2951a(context, "pref", "dns_faile_count_total") < 2) && m2851c(context) && !"http://abroad.apilocate.amap.com/mobile/binary".equals(c3866cn.mo2488b())) {
                String m2849a = m2849a(context, this.f3931h);
                if (!z && TextUtils.isEmpty(m2849a)) {
                    m2849a = C3875cw.m2952a(context, "ip", "last_ip", "");
                }
                if (!TextUtils.isEmpty(m2849a)) {
                    this.f3926b = true;
                    try {
                        SharedPreferences.Editor edit = context.getSharedPreferences("ip", 0).edit();
                        edit.putString("last_ip", m2849a);
                        C3875cw.m2956a(edit);
                    } catch (Throwable th2) {
                        C3880f.m3097a(th2, "SPUtil", "setPrefsInt");
                    }
                    c3866cn.f3939g = C3880f.m3099b().replace("apilocatesrc.amap.com", m2849a);
                    c3866cn.mo2487a().put("host", "apilocatesrc.amap.com");
                }
            }
        }
        long m2985b = C3876cx.m2985b();
        try {
            C3834bi c3834bi = this.f3925a;
            C3840bo m2601a = C3834bi.m2601a(c3866cn, optBoolean);
            this.f3933j = Long.valueOf(C3876cx.m2985b() - m2985b).intValue();
            if (this.f3926b) {
                C3875cw.m2954a(context, "pref", "dns_faile_count_total", 0L);
            }
            return m2601a;
        } catch (Throwable th3) {
            if (this.f3926b) {
                try {
                    if (this.f3927c <= 5) {
                        if (this.f3934k == null) {
                            this.f3934k = C3900z.m3265b();
                        }
                        if (!this.f3934k.isShutdown()) {
                            this.f3934k.submit(new a(c3866cn));
                        }
                    }
                } catch (Throwable unused) {
                }
            }
            throw th3;
        }
    }

    /* renamed from: a */
    public final String m2854a(byte[] bArr, Context context, String str) {
        if (C3876cx.m2967a(C3876cx.m2997c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        C3866cn c3866cn = new C3866cn(context, C3880f.m3091a("loc"));
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(SchedulerSupport.CUSTOM, "26260A1F00020002");
        hashMap2.put(TransferTable.COLUMN_KEY, C3885k.m3127f(context));
        String m3136a = C3887m.m3136a();
        String m3137a = C3887m.m3137a(context, m3136a, C3894t.m3234b(hashMap2));
        hashMap2.put("ts", m3136a);
        hashMap2.put("scode", m3137a);
        c3866cn.m2857b(bArr);
        c3866cn.f3942j = true;
        c3866cn.f3943k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "3.3.0", "loc", 3);
        c3866cn.f3944l = hashMap2;
        c3866cn.f3938f = hashMap;
        c3866cn.f3939g = str;
        c3866cn.m2627a(C3891q.m3194a(context));
        c3866cn.m2626a(C3880f.f4181c);
        c3866cn.m2628b(C3880f.f4181c);
        try {
            C3834bi c3834bi = this.f3925a;
            return new String(C3834bi.m2602a(c3866cn), "utf-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "LocNetManager", "post");
            return null;
        }
    }

    /* renamed from: a */
    final synchronized void m2855a(C3866cn c3866cn) {
        try {
            c3866cn.f3939g = C3880f.m3099b();
            long m2951a = C3875cw.m2951a(this.f3932i, "pref", "dns_faile_count_total");
            if (m2951a >= 2) {
                return;
            }
            C3834bi c3834bi = this.f3925a;
            C3834bi.m2601a(c3866cn, false);
            long j = m2951a + 1;
            if (j >= 2) {
                C3874cv.m2947a(this.f3932i, "HttpDNS", "dns faile too much");
            }
            C3875cw.m2954a(this.f3932i, "pref", "dns_faile_count_total", j);
        } catch (Throwable unused) {
            C3875cw.m2954a(this.f3932i, "pref", "dns_faile_count_total", 0L);
        }
    }

    /* renamed from: b */
    public final String m2856b(byte[] bArr, Context context, String str) {
        if (C3876cx.m2967a(C3876cx.m2997c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        C3864cl c3864cl = new C3864cl();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        c3864cl.f3920a = hashMap;
        c3864cl.f3922f = str;
        c3864cl.f3923g = bArr;
        c3864cl.m2627a(C3891q.m3194a(context));
        c3864cl.m2626a(C3880f.f4181c);
        c3864cl.m2628b(C3880f.f4181c);
        try {
            C3834bi c3834bi = this.f3925a;
            return new String(C3834bi.m2602a(c3864cl), "utf-8");
        } catch (Throwable th) {
            C3880f.m3097a(th, "LocNetManager", "post");
            return null;
        }
    }
}
