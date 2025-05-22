package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.DataType;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.tencent.bugly.AbstractC5864a;
import com.tencent.bugly.C5865b;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.C5873a;
import com.tencent.bugly.crashreport.common.info.C5874b;
import com.tencent.bugly.crashreport.common.strategy.C5876a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.a */
/* loaded from: classes7.dex */
public class C5896a {

    /* renamed from: e */
    private static Proxy f8010e;

    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f8011a = new HashMap<>();

    /* renamed from: b */
    protected String f8012b;

    /* renamed from: c */
    C5925i f8013c;

    /* renamed from: d */
    private HashMap<String, Object> f8014d;

    /* renamed from: a */
    public static InterfaceC5906aj m3610a(int i) {
        if (i == 1) {
            return new C5905ai();
        }
        if (i == 3) {
            return new C5904ah();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C5896a() {
        new HashMap();
        this.f8014d = new HashMap<>();
        this.f8012b = "GBK";
        this.f8013c = new C5925i();
    }

    /* renamed from: a */
    public static void m3617a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            f8010e = null;
        } else {
            f8010e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    /* renamed from: a */
    public static void m3618a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            f8010e = null;
        } else {
            f8010e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    /* renamed from: a */
    public void mo3623a(String str) {
        this.f8012b = str;
    }

    /* renamed from: b */
    public static Proxy m3622b() {
        return f8010e;
    }

    /* renamed from: a */
    public static C5916at m3613a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C5916at c5916at = new C5916at();
        c5916at.f8124a = userInfoBean.f7654e;
        c5916at.f8128e = userInfoBean.f7659j;
        c5916at.f8127d = userInfoBean.f7652c;
        c5916at.f8126c = userInfoBean.f7653d;
        c5916at.f8130g = C5873a.m3390b().m3431i();
        c5916at.f8131h = userInfoBean.f7664o == 1;
        int i = userInfoBean.f7651b;
        if (i == 1) {
            c5916at.f8125b = (byte) 1;
        } else if (i == 2) {
            c5916at.f8125b = (byte) 4;
        } else if (i == 3) {
            c5916at.f8125b = (byte) 2;
        } else if (i == 4) {
            c5916at.f8125b = (byte) 3;
        } else {
            if (userInfoBean.f7651b < 10 || userInfoBean.f7651b >= 20) {
                C5940x.m3825e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f7651b));
                return null;
            }
            c5916at.f8125b = (byte) userInfoBean.f7651b;
        }
        c5916at.f8129f = new HashMap();
        if (userInfoBean.f7665p >= 0) {
            Map<String, String> map = c5916at.f8129f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.f7665p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.f7666q >= 0) {
            Map<String, String> map2 = c5916at.f8129f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.f7666q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.f7667r != null && userInfoBean.f7667r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.f7667r.entrySet()) {
                c5916at.f8129f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.f7668s != null && userInfoBean.f7668s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.f7668s.entrySet()) {
                c5916at.f8129f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = c5916at.f8129f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.f7661l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = c5916at.f8129f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.f7656g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = c5916at.f8129f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.f7657h);
        map5.put("F03", sb5.toString());
        c5916at.f8129f.put("F04", userInfoBean.f7659j);
        Map<String, String> map6 = c5916at.f8129f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.f7658i);
        map6.put("F05", sb6.toString());
        c5916at.f8129f.put("F06", userInfoBean.f7662m);
        Map<String, String> map7 = c5916at.f8129f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.f7660k);
        map7.put("F10", sb7.toString());
        C5940x.m3823c("summary type %d vm:%d", Byte.valueOf(c5916at.f8125b), Integer.valueOf(c5916at.f8129f.size()));
        return c5916at;
    }

    /* renamed from: a */
    public static String m3616a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int size = arrayList.size();
            String str = MapElement.Key.MAP;
            if (i < size) {
                String str2 = arrayList.get(i);
                if (str2.equals("java.lang.Integer") || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals("java.lang.Boolean") || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals("java.lang.Byte") || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals("java.lang.Double") || str2.equals(TmpConstant.TYPE_VALUE_DOUBLE)) {
                    str = TmpConstant.TYPE_VALUE_DOUBLE;
                } else if (str2.equals("java.lang.Float") || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals("java.lang.Long") || str2.equals(DataType.LONG)) {
                    str = "int64";
                } else if (str2.equals("java.lang.Short") || str2.equals("short")) {
                    str = "short";
                } else {
                    if (str2.equals("java.lang.Character")) {
                        throw new IllegalArgumentException("can not support java.lang.Character");
                    }
                    if (str2.equals("java.lang.String")) {
                        str = "string";
                    } else if (str2.equals("java.util.List")) {
                        str = "list";
                    } else if (!str2.equals("java.util.Map")) {
                        str = str2;
                    }
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, "<" + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals(MapElement.Key.MAP)) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, "<" + arrayList.get(i4) + ",");
                        arrayList.set(0, arrayList.get(0) + ">");
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, "<" + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + ">");
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    /* renamed from: a */
    public <T> void mo3624a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        C5926j c5926j = new C5926j();
        c5926j.m3699a(this.f8012b);
        c5926j.m3705a(t, 0);
        byte[] m3717a = C5928l.m3717a(c5926j.m3700a());
        HashMap<String, byte[]> hashMap = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        m3619a(arrayList, t);
        hashMap.put(m3616a(arrayList), m3717a);
        this.f8014d.remove(str);
        this.f8011a.put(str, hashMap);
    }

    /* renamed from: a */
    public static C5917au m3614a(List<UserInfoBean> list, int i) {
        C5873a m3390b;
        if (list == null || list.size() == 0 || (m3390b = C5873a.m3390b()) == null) {
            return null;
        }
        m3390b.m3442t();
        C5917au c5917au = new C5917au();
        c5917au.f8135b = m3390b.f7756d;
        c5917au.f8136c = m3390b.m3430h();
        ArrayList<C5916at> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            C5916at m3613a = m3613a(it.next());
            if (m3613a != null) {
                arrayList.add(m3613a);
            }
        }
        c5917au.f8137d = arrayList;
        c5917au.f8138e = new HashMap();
        c5917au.f8138e.put("A7", m3390b.f7758f);
        c5917au.f8138e.put("A6", m3390b.m3441s());
        c5917au.f8138e.put("A5", m3390b.m3440r());
        Map<String, String> map = c5917au.f8138e;
        StringBuilder sb = new StringBuilder();
        sb.append(m3390b.m3438p());
        map.put("A2", sb.toString());
        Map<String, String> map2 = c5917au.f8138e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(m3390b.m3438p());
        map2.put("A1", sb2.toString());
        c5917au.f8138e.put("A24", m3390b.f7760h);
        Map<String, String> map3 = c5917au.f8138e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(m3390b.m3439q());
        map3.put("A17", sb3.toString());
        c5917au.f8138e.put("A15", m3390b.m3445w());
        Map<String, String> map4 = c5917au.f8138e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(m3390b.m3446x());
        map4.put("A13", sb4.toString());
        c5917au.f8138e.put("F08", m3390b.f7774v);
        c5917au.f8138e.put("F09", m3390b.f7775w);
        Map<String, String> m3398G = m3390b.m3398G();
        if (m3398G != null && m3398G.size() > 0) {
            for (Map.Entry<String, String> entry : m3398G.entrySet()) {
                c5917au.f8138e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        if (i == 1) {
            c5917au.f8134a = (byte) 1;
        } else {
            if (i != 2) {
                C5940x.m3825e("unknown up type %d ", Integer.valueOf(i));
                return null;
            }
            c5917au.f8134a = (byte) 2;
        }
        return c5917au;
    }

    /* renamed from: a */
    public static <T extends AbstractC5927k> T m3615a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T newInstance = cls.newInstance();
                C5925i c5925i = new C5925i(bArr);
                c5925i.m3687a("utf-8");
                newInstance.mo3649a(c5925i);
                return newInstance;
            } catch (Throwable th) {
                if (!C5940x.m3822b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C5912ap m3611a(Context context, int i, byte[] bArr) {
        C5873a m3390b = C5873a.m3390b();
        StrategyBean m3497c = C5876a.m3487a().m3497c();
        if (m3390b == null || m3497c == null) {
            C5940x.m3825e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            C5912ap c5912ap = new C5912ap();
            synchronized (m3390b) {
                c5912ap.f8072a = 1;
                c5912ap.f8073b = m3390b.m3426f();
                c5912ap.f8074c = m3390b.f7755c;
                c5912ap.f8075d = m3390b.f7762j;
                c5912ap.f8076e = m3390b.f7764l;
                m3390b.getClass();
                c5912ap.f8077f = "3.0.0";
                c5912ap.f8078g = i;
                c5912ap.f8079h = bArr == null ? "".getBytes() : bArr;
                c5912ap.f8080i = m3390b.f7759g;
                c5912ap.f8081j = m3390b.f7760h;
                c5912ap.f8082k = new HashMap();
                c5912ap.f8083l = m3390b.m3424e();
                c5912ap.f8084m = m3497c.f7799p;
                c5912ap.f8086o = m3390b.m3430h();
                c5912ap.f8087p = C5874b.m3455c(context);
                c5912ap.f8088q = System.currentTimeMillis();
                c5912ap.f8089r = m3390b.m3433k();
                c5912ap.f8090s = m3390b.m3432j();
                c5912ap.f8091t = m3390b.m3435m();
                c5912ap.f8092u = m3390b.m3434l();
                c5912ap.f8093v = m3390b.m3436n();
                c5912ap.f8094w = c5912ap.f8087p;
                m3390b.getClass();
                c5912ap.f8085n = "com.tencent.bugly";
                c5912ap.f8082k.put("A26", m3390b.m3447y());
                c5912ap.f8082k.put("A60", m3390b.m3448z());
                c5912ap.f8082k.put("A61", m3390b.m3392A());
                Map<String, String> map = c5912ap.f8082k;
                StringBuilder sb = new StringBuilder();
                sb.append(m3390b.m3408R());
                map.put("A62", sb.toString());
                Map<String, String> map2 = c5912ap.f8082k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(m3390b.m3409S());
                map2.put("A63", sb2.toString());
                Map<String, String> map3 = c5912ap.f8082k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(m3390b.f7778z);
                map3.put("F11", sb3.toString());
                Map<String, String> map4 = c5912ap.f8082k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(m3390b.f7777y);
                map4.put("F12", sb4.toString());
                c5912ap.f8082k.put("G1", m3390b.m3443u());
                c5912ap.f8082k.put("A64", m3390b.m3410T());
                if (m3390b.f7703B) {
                    c5912ap.f8082k.put("G2", m3390b.m3402L());
                    c5912ap.f8082k.put("G3", m3390b.m3403M());
                    c5912ap.f8082k.put("G4", m3390b.m3404N());
                    c5912ap.f8082k.put("G5", m3390b.m3405O());
                    c5912ap.f8082k.put("G6", m3390b.m3406P());
                    c5912ap.f8082k.put("G7", Long.toString(m3390b.m3407Q()));
                }
                c5912ap.f8082k.put("D3", m3390b.f7763k);
                if (C5865b.f7643b != null) {
                    for (AbstractC5864a abstractC5864a : C5865b.f7643b) {
                        if (abstractC5864a.versionKey != null && abstractC5864a.version != null) {
                            c5912ap.f8082k.put(abstractC5864a.versionKey, abstractC5864a.version);
                        }
                    }
                }
                c5912ap.f8082k.put("G15", C5942z.m3877b("G15", ""));
                c5912ap.f8082k.put("D4", C5942z.m3877b("D4", "0"));
            }
            C5937u m3773a = C5937u.m3773a();
            if (m3773a != null && !m3773a.f8221a && bArr != null) {
                c5912ap.f8079h = C5942z.m3874a(c5912ap.f8079h, 2, 1, m3497c.f7804u);
                if (c5912ap.f8079h == null) {
                    C5940x.m3825e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map<String, String> m3397F = m3390b.m3397F();
            if (m3397F != null) {
                for (Map.Entry<String, String> entry : m3397F.entrySet()) {
                    c5912ap.f8082k.put(entry.getKey(), entry.getValue());
                }
            }
            return c5912ap;
        } catch (Throwable th) {
            if (!C5940x.m3822b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private void m3619a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                m3619a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                m3619a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                m3619a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    /* renamed from: a */
    public byte[] mo3626a() {
        C5926j c5926j = new C5926j(0);
        c5926j.m3699a(this.f8012b);
        c5926j.m3708a((Map) this.f8011a, 0);
        return C5928l.m3717a(c5926j.m3700a());
    }

    /* renamed from: a */
    public void mo3625a(byte[] bArr) {
        this.f8013c.m3693a(bArr);
        this.f8013c.m3687a(this.f8012b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f8011a = this.f8013c.m3691a((Map) hashMap, 0, false);
    }

    /* renamed from: a */
    public static byte[] m3621a(Object obj) {
        try {
            C5920d c5920d = new C5920d();
            c5920d.mo3653c();
            c5920d.mo3623a("utf-8");
            c5920d.m3654b(1);
            c5920d.m3655b("RqdServer");
            c5920d.m3656c(InvokeServiceData.CALL_TYPE_SYNC);
            c5920d.mo3624a("detail", (String) obj);
            return c5920d.mo3626a();
        } catch (Throwable th) {
            if (C5940x.m3822b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static C5913aq m3612a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                C5920d c5920d = new C5920d();
                c5920d.mo3653c();
                c5920d.mo3623a("utf-8");
                c5920d.mo3625a(bArr);
                Object b = c5920d.m3652b("detail", new C5913aq());
                C5913aq c5913aq = C5913aq.class.isInstance(b) ? (C5913aq) C5913aq.class.cast(b) : null;
                if (!z && c5913aq != null && c5913aq.f8100c != null && c5913aq.f8100c.length > 0) {
                    C5940x.m3823c("resp buf %d", Integer.valueOf(c5913aq.f8100c.length));
                    c5913aq.f8100c = C5942z.m3887b(c5913aq.f8100c, 2, 1, StrategyBean.f7787d);
                    if (c5913aq.f8100c == null) {
                        C5940x.m3825e("resp sbuffer error!", new Object[0]);
                        return null;
                    }
                }
                return c5913aq;
            } catch (Throwable th) {
                if (!C5940x.m3822b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m3620a(AbstractC5927k abstractC5927k) {
        try {
            C5926j c5926j = new C5926j();
            c5926j.m3699a("utf-8");
            abstractC5927k.mo3650a(c5926j);
            return c5926j.m3712b();
        } catch (Throwable th) {
            if (C5940x.m3822b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
