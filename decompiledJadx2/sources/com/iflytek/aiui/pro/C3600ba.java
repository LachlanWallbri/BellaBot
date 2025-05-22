package com.iflytek.aiui.pro;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.cloud.SpeechConstant;
import java.text.DecimalFormat;
import java.util.HashMap;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ba */
/* loaded from: classes.dex */
public class C3600ba {

    /* renamed from: a */
    private Context f2438a;

    /* renamed from: b */
    private HashMap<String, String> f2439b;

    public C3600ba(Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        this.f2439b = hashMap;
        this.f2438a = context;
        C3601bb.m1133a(context, hashMap);
        m1124b();
    }

    /* renamed from: a */
    private static boolean m1123a(Context context, String str, long j) {
        return System.currentTimeMillis() - C3595ay.m1110a(context).getLong(str, -1L) > j;
    }

    /* renamed from: b */
    private void m1124b() {
        HashMap<String, String> hashMap;
        String str;
        this.f2439b.put("tz", C3601bb.m1137b());
        this.f2439b.put(LinkFormat.CONTENT_TYPE, C3601bb.m1141c());
        this.f2439b.put("l", C3601bb.m1128a());
        this.f2439b.put("maf", C3601bb.m1130a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"));
        this.f2439b.put("mif", C3601bb.m1130a("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"));
        this.f2439b.put("md", Build.MODEL);
        this.f2439b.put("ov", Build.VERSION.RELEASE);
        this.f2439b.put("ma", Build.MANUFACTURER);
        this.f2439b.put("dp", Build.DISPLAY);
        this.f2439b.put("hw", Build.HARDWARE);
        this.f2439b.put("pd", Build.PRODUCT);
        this.f2439b.put("ou", Build.USER);
        this.f2439b.put("bo", Build.BOARD);
        this.f2439b.put(CompressorStreamFactory.BROTLI, Build.BRAND);
        this.f2439b.put("tg", Build.TAGS);
        this.f2439b.put("ht", Build.HOST);
        this.f2439b.put("dv", Build.DEVICE);
        this.f2439b.put("bt", String.valueOf(Build.TIME));
        this.f2439b.put("sn", Build.SERIAL);
        if (Build.VERSION.SDK_INT >= 14) {
            hashMap = this.f2439b;
            str = Build.getRadioVersion();
        } else {
            hashMap = this.f2439b;
            str = Build.RADIO;
        }
        hashMap.put("rd", str);
        this.f2439b.put("bi", Build.ID);
        if (Build.VERSION.SDK_INT >= 23) {
            this.f2439b.put("bs", Build.VERSION.BASE_OS);
        }
        C3601bb.m1135a(this.f2439b);
        Context context = this.f2438a;
        if (context != null) {
            this.f2439b.put("re", C3601bb.m1142c(context));
            this.f2439b.put("de", C3601bb.m1144d(this.f2438a));
            this.f2439b.put("wm", C3601bb.m1129a(this.f2438a));
            this.f2439b.put("im", C3601bb.m1154g(this.f2438a));
            this.f2439b.put("c", C3601bb.m1147e(this.f2438a));
            this.f2439b.put("is", C3601bb.m1156h(this.f2438a));
            this.f2439b.put("ad", C3601bb.m1138b(this.f2438a));
            this.f2439b.put("bm", C3601bb.m1163l(this.f2438a));
            C3601bb.m1139b(this.f2438a, this.f2439b);
        }
    }

    /* renamed from: c */
    private String m1125c() {
        return C3595ay.m1110a(this.f2438a).getString("peer", "");
    }

    /* renamed from: a */
    public JSONObject m1126a() {
        C3581ak c3581ak = new C3581ak(this.f2439b);
        c3581ak.m1017a("platform", "android");
        C3581ak m1106a = C3594ax.m1106a(this.f2438a);
        c3581ak.m1017a("v", "1.0.1");
        c3581ak.m1017a("ts", String.valueOf(System.currentTimeMillis()));
        c3581ak.m1017a("ro", String.valueOf(C3601bb.m1146d()));
        c3581ak.m1017a("ua", C3601bb.m1164m(this.f2438a));
        c3581ak.m1017a("n", m1106a.m1020b(SpeechConstant.NET_TYPE, ""));
        c3581ak.m1017a("se", m1106a.m1020b("net_subtype", ""));
        c3581ak.m1017a("ra", C3601bb.m1158i(this.f2438a));
        c3581ak.m1017a("rs", C3601bb.m1159j(this.f2438a));
        c3581ak.m1017a("sl", C3601bb.m1161k(this.f2438a));
        c3581ak.m1017a("gid", C3592av.m1075a(this.f2438a));
        String m1125c = m1125c();
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(m1125c) && m1123a(this.f2438a, "report_peer_ts", C3596az.f2419d)) {
            c3581ak.m1017a("pm", m1125c);
            C3595ay.m1111a(this.f2438a, "report_peer_ts", Long.valueOf(currentTimeMillis));
        }
        JSONObject m1166a = C3602bc.m1166a(false, c3581ak, "heart");
        try {
            Location m1150f = C3601bb.m1150f(this.f2438a);
            if (m1150f != null) {
                DecimalFormat decimalFormat = new DecimalFormat("#.########");
                m1166a.put("w", decimalFormat.format(m1150f.getLatitude()));
                m1166a.put("j", decimalFormat.format(m1150f.getLongitude()));
                m1166a.put("t", m1150f.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        C3601bb.m1134a(this.f2438a, m1166a);
        C3601bb.m1151f(this.f2438a, m1166a);
        C3601bb.m1149e(this.f2438a, m1166a);
        C3601bb.m1143c(this.f2438a, m1166a);
        if (m1123a(this.f2438a, "report_config_wifi_ts", C3596az.f2417b)) {
            C3601bb.m1140b(this.f2438a, m1166a);
            C3595ay.m1111a(this.f2438a, "report_config_wifi_ts", Long.valueOf(currentTimeMillis));
        }
        if (m1123a(this.f2438a, "report_install_apps_ts", C3596az.f2418c)) {
            C3601bb.m1145d(this.f2438a, m1166a);
            C3595ay.m1111a(this.f2438a, "report_install_apps_ts", Long.valueOf(currentTimeMillis));
        }
        return m1166a;
    }
}
