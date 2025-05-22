package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

/* compiled from: Cache.java */
/* renamed from: com.loc.cf */
/* loaded from: classes4.dex */
public final class C3858cf {

    /* renamed from: b */
    private static C3858cf f3903b;

    /* renamed from: a */
    Hashtable<String, ArrayList<a>> f3904a = new Hashtable<>();

    /* renamed from: c */
    private long f3905c = 0;

    /* renamed from: d */
    private volatile boolean f3906d = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Cache.java */
    /* renamed from: com.loc.cf$a */
    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: a */
        private AMapLocationServer f3907a = null;

        /* renamed from: b */
        private String f3908b = null;

        protected a() {
        }

        /* renamed from: a */
        public final AMapLocationServer m2818a() {
            return this.f3907a;
        }

        /* renamed from: a */
        public final void m2819a(AMapLocationServer aMapLocationServer) {
            this.f3907a = aMapLocationServer;
        }

        /* renamed from: a */
        public final void m2820a(String str) {
            this.f3908b = TextUtils.isEmpty(str) ? null : str.replace("##", MqttTopic.MULTI_LEVEL_WILDCARD);
        }

        /* renamed from: b */
        public final String m2821b() {
            return this.f3908b;
        }
    }

    private C3858cf() {
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00bd A[Catch: all -> 0x019c, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:9:0x0016, B:14:0x0020, B:16:0x003f, B:18:0x004f, B:20:0x005a, B:23:0x0061, B:25:0x0069, B:30:0x00bd, B:68:0x018e, B:33:0x00d0, B:34:0x00e9, B:36:0x00ef, B:38:0x00fb, B:39:0x0103, B:41:0x0109, B:43:0x0115, B:45:0x012d, B:47:0x0133, B:50:0x014a, B:54:0x0155, B:59:0x015b, B:61:0x016e, B:64:0x017c, B:73:0x0187, B:83:0x0075, B:85:0x0085, B:86:0x0097, B:89:0x009e, B:90:0x0095), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ef A[Catch: all -> 0x019c, LOOP:1: B:34:0x00e9->B:36:0x00ef, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:9:0x0016, B:14:0x0020, B:16:0x003f, B:18:0x004f, B:20:0x005a, B:23:0x0061, B:25:0x0069, B:30:0x00bd, B:68:0x018e, B:33:0x00d0, B:34:0x00e9, B:36:0x00ef, B:38:0x00fb, B:39:0x0103, B:41:0x0109, B:43:0x0115, B:45:0x012d, B:47:0x0133, B:50:0x014a, B:54:0x0155, B:59:0x015b, B:61:0x016e, B:64:0x017c, B:73:0x0187, B:83:0x0075, B:85:0x0085, B:86:0x0097, B:89:0x009e, B:90:0x0095), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0109 A[Catch: all -> 0x019c, LOOP:2: B:39:0x0103->B:41:0x0109, LOOP_END, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:9:0x0016, B:14:0x0020, B:16:0x003f, B:18:0x004f, B:20:0x005a, B:23:0x0061, B:25:0x0069, B:30:0x00bd, B:68:0x018e, B:33:0x00d0, B:34:0x00e9, B:36:0x00ef, B:38:0x00fb, B:39:0x0103, B:41:0x0109, B:43:0x0115, B:45:0x012d, B:47:0x0133, B:50:0x014a, B:54:0x0155, B:59:0x015b, B:61:0x016e, B:64:0x017c, B:73:0x0187, B:83:0x0075, B:85:0x0085, B:86:0x0097, B:89:0x009e, B:90:0x0095), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012d A[Catch: all -> 0x019c, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:9:0x0016, B:14:0x0020, B:16:0x003f, B:18:0x004f, B:20:0x005a, B:23:0x0061, B:25:0x0069, B:30:0x00bd, B:68:0x018e, B:33:0x00d0, B:34:0x00e9, B:36:0x00ef, B:38:0x00fb, B:39:0x0103, B:41:0x0109, B:43:0x0115, B:45:0x012d, B:47:0x0133, B:50:0x014a, B:54:0x0155, B:59:0x015b, B:61:0x016e, B:64:0x017c, B:73:0x0187, B:83:0x0075, B:85:0x0085, B:86:0x0097, B:89:0x009e, B:90:0x0095), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x016e A[Catch: all -> 0x019c, TryCatch #0 {, blocks: (B:4:0x0005, B:6:0x000e, B:9:0x0016, B:14:0x0020, B:16:0x003f, B:18:0x004f, B:20:0x005a, B:23:0x0061, B:25:0x0069, B:30:0x00bd, B:68:0x018e, B:33:0x00d0, B:34:0x00e9, B:36:0x00ef, B:38:0x00fb, B:39:0x0103, B:41:0x0109, B:43:0x0115, B:45:0x012d, B:47:0x0133, B:50:0x014a, B:54:0x0155, B:59:0x015b, B:61:0x016e, B:64:0x017c, B:73:0x0187, B:83:0x0075, B:85:0x0085, B:86:0x0097, B:89:0x009e, B:90:0x0095), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x016d A[ADDED_TO_REGION, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00cf  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private synchronized a m2806a(StringBuilder sb, String str) {
        a aVar;
        boolean z;
        boolean z2;
        Iterator it;
        Iterator it2;
        double[] dArr;
        double[] dArr2;
        Iterator it3;
        int i;
        double[] m2810a;
        String str2;
        if (!this.f3904a.isEmpty() && !TextUtils.isEmpty(sb)) {
            if (!this.f3904a.containsKey(str)) {
                return null;
            }
            Hashtable hashtable = new Hashtable();
            Hashtable hashtable2 = new Hashtable();
            Hashtable hashtable3 = new Hashtable();
            ArrayList<a> arrayList = this.f3904a.get(str);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                a aVar2 = arrayList.get(size);
                if (!TextUtils.isEmpty(aVar2.m2821b())) {
                    String m2821b = aVar2.m2821b();
                    if (!TextUtils.isEmpty(m2821b) && !TextUtils.isEmpty(sb) && m2821b.contains(",access")) {
                        if (sb.indexOf(",access") != -1) {
                            String[] split = m2821b.split(",access");
                            String substring = split[0].contains(MqttTopic.MULTI_LEVEL_WILDCARD) ? split[0].substring(split[0].lastIndexOf(MqttTopic.MULTI_LEVEL_WILDCARD) + 1) : split[0];
                            if (!TextUtils.isEmpty(substring)) {
                                z = sb.toString().contains(substring + ",access");
                                if (!z) {
                                    z2 = false;
                                } else {
                                    if (m2809a(aVar2.m2821b(), sb.toString())) {
                                        aVar = aVar2;
                                        break;
                                    }
                                    z2 = true;
                                }
                                m2808a(aVar2.m2821b(), (Hashtable<String, String>) hashtable);
                                m2808a(sb.toString(), (Hashtable<String, String>) hashtable2);
                                hashtable3.clear();
                                it = hashtable.keySet().iterator();
                                while (it.hasNext()) {
                                    hashtable3.put((String) it.next(), "");
                                }
                                it2 = hashtable2.keySet().iterator();
                                while (it2.hasNext()) {
                                    hashtable3.put((String) it2.next(), "");
                                }
                                Set keySet = hashtable3.keySet();
                                dArr = new double[keySet.size()];
                                dArr2 = new double[keySet.size()];
                                it3 = keySet.iterator();
                                i = 0;
                                while (it3 != null && it3.hasNext()) {
                                    str2 = (String) it3.next();
                                    double d = 1.0d;
                                    dArr[i] = !hashtable.containsKey(str2) ? 1.0d : 0.0d;
                                    if (hashtable2.containsKey(str2)) {
                                        d = 0.0d;
                                    }
                                    dArr2[i] = d;
                                    i++;
                                }
                                keySet.clear();
                                m2810a = m2810a(dArr, dArr2);
                                if (m2810a[0] >= 0.800000011920929d) {
                                    if (m2810a[1] < 0.618d) {
                                        if (z2 && m2810a[0] >= 0.618d) {
                                        }
                                    }
                                }
                                aVar = aVar2;
                                break;
                            }
                        }
                        z = false;
                        if (!z) {
                        }
                        m2808a(aVar2.m2821b(), (Hashtable<String, String>) hashtable);
                        m2808a(sb.toString(), (Hashtable<String, String>) hashtable2);
                        hashtable3.clear();
                        it = hashtable.keySet().iterator();
                        while (it.hasNext()) {
                        }
                        it2 = hashtable2.keySet().iterator();
                        while (it2.hasNext()) {
                        }
                        Set keySet2 = hashtable3.keySet();
                        dArr = new double[keySet2.size()];
                        dArr2 = new double[keySet2.size()];
                        it3 = keySet2.iterator();
                        i = 0;
                        while (it3 != null) {
                            str2 = (String) it3.next();
                            double d2 = 1.0d;
                            dArr[i] = !hashtable.containsKey(str2) ? 1.0d : 0.0d;
                            if (hashtable2.containsKey(str2)) {
                            }
                            dArr2[i] = d2;
                            i++;
                        }
                        keySet2.clear();
                        m2810a = m2810a(dArr, dArr2);
                        if (m2810a[0] >= 0.800000011920929d && m2810a[1] < 0.618d) {
                        }
                        aVar = aVar2;
                        break;
                    }
                    z = false;
                    if (!z) {
                    }
                    m2808a(aVar2.m2821b(), (Hashtable<String, String>) hashtable);
                    m2808a(sb.toString(), (Hashtable<String, String>) hashtable2);
                    hashtable3.clear();
                    it = hashtable.keySet().iterator();
                    while (it.hasNext()) {
                    }
                    it2 = hashtable2.keySet().iterator();
                    while (it2.hasNext()) {
                    }
                    Set keySet22 = hashtable3.keySet();
                    dArr = new double[keySet22.size()];
                    dArr2 = new double[keySet22.size()];
                    it3 = keySet22.iterator();
                    i = 0;
                    while (it3 != null) {
                    }
                    keySet22.clear();
                    m2810a = m2810a(dArr, dArr2);
                    if (m2810a[0] >= 0.800000011920929d) {
                    }
                    aVar = aVar2;
                    break;
                }
            }
            aVar = null;
            hashtable.clear();
            hashtable2.clear();
            hashtable3.clear();
            return aVar;
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized C3858cf m2807a() {
        C3858cf c3858cf;
        synchronized (C3858cf.class) {
            if (f3903b == null) {
                f3903b = new C3858cf();
            }
            c3858cf = f3903b;
        }
        return c3858cf;
    }

    /* renamed from: a */
    private static void m2808a(String str, Hashtable<String, String> hashtable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        hashtable.clear();
        for (String str2 : str.split(MqttTopic.MULTI_LEVEL_WILDCARD)) {
            if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                hashtable.put(str2, "");
            }
        }
    }

    /* renamed from: a */
    public static boolean m2809a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String[] split = str.split(MqttTopic.MULTI_LEVEL_WILDCARD);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < split.length; i++) {
                if (split[i].contains(",nb") || split[i].contains(",access")) {
                    arrayList.add(split[i]);
                }
            }
            String[] split2 = str2.toString().split(MqttTopic.MULTI_LEVEL_WILDCARD);
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < split2.length; i4++) {
                if (split2[i4].contains(",nb") || split2[i4].contains(",access")) {
                    i2++;
                    if (arrayList.contains(split2[i4])) {
                        i3++;
                    }
                }
            }
            if (i3 * 2 >= (arrayList.size() + i2) * 0.618d) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static double[] m2810a(double[] dArr, double[] dArr2) {
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            d2 += dArr[i3] * dArr[i3];
            d3 += dArr2[i3] * dArr2[i3];
            d += dArr[i3] * dArr2[i3];
            if (dArr2[i3] == 1.0d) {
                i2++;
                if (dArr[i3] == 1.0d) {
                    i++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d2) * Math.sqrt(d3));
        double d4 = i;
        dArr3[1] = (d4 * 1.0d) / i2;
        dArr3[2] = d4;
        for (int i4 = 0; i4 < dArr3.length - 1; i4++) {
            if (dArr3[i4] > 1.0d) {
                dArr3[i4] = 1.0d;
            }
        }
        return dArr3;
    }

    /* renamed from: c */
    private boolean m2811c() {
        long m2985b = C3876cx.m2985b();
        long j = this.f3905c;
        return j != 0 && (this.f3904a.size() > 360 || m2985b - j > 36000000);
    }

    /* renamed from: d */
    private void m2812d() {
        this.f3905c = 0L;
        if (!this.f3904a.isEmpty()) {
            this.f3904a.clear();
        }
        this.f3906d = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0088 A[DONT_GENERATE] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized AMapLocationServer m2813a(String str, StringBuilder sb, boolean z) {
        a aVar;
        AMapLocationServer m2818a;
        if (!str.contains("gps") && z && C3869cq.m2903t() && sb != null) {
            if (m2811c()) {
                m2812d();
                return null;
            }
            if (this.f3904a.isEmpty()) {
                return null;
            }
            if (!str.contains("cgiwifi") && !str.contains("wifi")) {
                aVar = (str.contains("cgi") && this.f3904a.containsKey(str)) ? this.f3904a.get(str).get(0) : null;
                if (aVar != null && C3876cx.m2977a(aVar.m2818a())) {
                    m2818a = aVar.m2818a();
                    m2818a.m552d("mem");
                    m2818a.m556f(aVar.m2821b());
                    if (C3869cq.m2877b(m2818a.getTime())) {
                        return m2818a;
                    }
                }
                return null;
            }
            aVar = m2806a(sb, str);
            if (aVar != null) {
                m2818a = aVar.m2818a();
                m2818a.m552d("mem");
                m2818a.m556f(aVar.m2821b());
                if (C3869cq.m2877b(m2818a.getTime())) {
                }
            }
            return null;
        }
        return null;
    }

    /* renamed from: a */
    public final synchronized String m2814a(String str, StringBuilder sb, Context context) {
        return C3860ch.m2824a().m2826a(str, sb, context);
    }

    /* renamed from: a */
    public final synchronized void m2815a(Context context, String str) {
        if (this.f3906d) {
            return;
        }
        try {
            m2812d();
            C3860ch.m2824a().m2828a(context, str);
        } catch (Throwable th) {
            C3880f.m3097a(th, "Cache", "loadDB");
        }
        if (str == null) {
            this.f3906d = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0022 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0024 A[Catch: all -> 0x0174, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:8:0x000f, B:11:0x0018, B:16:0x0024, B:20:0x0032, B:24:0x0040, B:28:0x004e, B:30:0x0054, B:31:0x0057, B:33:0x0063, B:34:0x006b, B:36:0x0073, B:40:0x007b, B:42:0x0085, B:44:0x0093, B:55:0x00b4, B:57:0x00bc, B:59:0x00c6, B:61:0x00d8, B:63:0x0106, B:65:0x0110, B:69:0x0121, B:72:0x013b, B:74:0x0146, B:84:0x016b, B:85:0x0152, B:86:0x0137, B:87:0x00a8, B:92:0x00e4, B:94:0x00ec, B:99:0x00f7, B:79:0x0161), top: B:2:0x0001, inners: #1 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized void m2816a(String str, StringBuilder sb, AMapLocationServer aMapLocationServer, Context context, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str) && C3876cx.m2977a(aMapLocationServer) && !str.startsWith(MqttTopic.MULTI_LEVEL_WILDCARD)) {
            z2 = str.contains("network");
            if (z2) {
                return;
            }
            if (aMapLocationServer.m551d().equals("mem")) {
                return;
            }
            if (aMapLocationServer.m551d().equals("file")) {
                return;
            }
            if (aMapLocationServer.m549c().equals("-3")) {
                return;
            }
            if (m2811c()) {
                m2812d();
            }
            JSONObject m553e = aMapLocationServer.m553e();
            if (C3876cx.m2979a(m553e, "offpct")) {
                m553e.remove("offpct");
                aMapLocationServer.m544a(m553e);
            }
            if (str.contains("wifi")) {
                if (TextUtils.isEmpty(sb)) {
                    return;
                }
                if (aMapLocationServer.getAccuracy() >= 300.0f) {
                    int i = 0;
                    for (String str2 : sb.toString().split(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                        if (str2.contains(",")) {
                            i++;
                        }
                    }
                    if (i >= 8) {
                        return;
                    }
                } else if (aMapLocationServer.getAccuracy() <= 10.0f) {
                    return;
                }
                if (str.contains("cgiwifi") && !TextUtils.isEmpty(aMapLocationServer.m555f())) {
                    String replace = str.replace("cgiwifi", "cgi");
                    AMapLocationServer m557g = aMapLocationServer.m557g();
                    if (C3876cx.m2977a(m557g)) {
                        m2816a(replace, new StringBuilder(), m557g, context, true);
                    }
                }
            } else if (str.contains("cgi")) {
                if (sb.indexOf(",") != -1) {
                    return;
                }
                if (aMapLocationServer.m549c().equals(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD)) {
                    return;
                }
            }
            AMapLocationServer m2813a = m2813a(str, sb, true);
            if (C3876cx.m2977a(m2813a) && m2813a.toStr().equals(aMapLocationServer.toStr(3))) {
                return;
            }
            this.f3905c = C3876cx.m2985b();
            a aVar = new a();
            aVar.m2819a(aMapLocationServer);
            aVar.m2820a(TextUtils.isEmpty(sb) ? null : sb.toString());
            if (this.f3904a.containsKey(str)) {
                this.f3904a.get(str).add(aVar);
            } else {
                ArrayList<a> arrayList = new ArrayList<>();
                arrayList.add(aVar);
                this.f3904a.put(str, arrayList);
            }
            if (z) {
                try {
                    C3860ch.m2824a().m2830a(str, aMapLocationServer, sb, context);
                    return;
                } catch (Throwable th) {
                    C3880f.m3097a(th, "Cache", TmpConstant.GROUP_OP_ADD);
                }
            }
            return;
        }
        z2 = false;
        if (z2) {
        }
    }

    /* renamed from: b */
    public final synchronized boolean m2817b() {
        return this.f3906d;
    }
}
