package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.CRC32;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.e */
/* loaded from: classes.dex */
public class C3610e extends C3571aa {

    /* renamed from: a */
    Map<String, String> f2479a;

    /* renamed from: b */
    String f2480b;

    /* renamed from: c */
    public String f2481c;

    /* renamed from: d */
    public String f2482d;

    /* renamed from: e */
    public String f2483e;

    /* renamed from: f */
    public String f2484f;

    /* renamed from: g */
    public String f2485g;

    /* renamed from: h */
    public String f2486h;

    /* renamed from: i */
    public String f2487i;

    /* renamed from: j */
    public String f2488j;

    /* renamed from: k */
    public String f2489k;

    /* renamed from: l */
    public String f2490l;

    /* renamed from: m */
    public String f2491m;

    /* renamed from: n */
    public String f2492n;

    /* renamed from: o */
    public String f2493o;

    /* renamed from: p */
    public String f2494p;

    /* renamed from: q */
    public String f2495q;

    /* renamed from: r */
    public String f2496r;

    /* renamed from: s */
    public String f2497s;

    /* renamed from: t */
    public String f2498t;

    /* renamed from: u */
    public String f2499u;

    /* renamed from: v */
    public String f2500v;

    /* renamed from: w */
    ByteArrayOutputStream f2501w;

    public C3610e(Context context, C3626m c3626m) {
        super(context, c3626m);
        this.f2479a = null;
        this.f2480b = "";
        this.f2481c = null;
        this.f2482d = null;
        this.f2483e = null;
        this.f2484f = null;
        this.f2485g = null;
        this.f2486h = null;
        this.f2487i = null;
        this.f2488j = null;
        this.f2489k = null;
        this.f2490l = null;
        this.f2491m = null;
        this.f2492n = null;
        this.f2493o = null;
        this.f2494p = null;
        this.f2495q = null;
        this.f2496r = null;
        this.f2497s = null;
        this.f2498t = null;
        this.f2499u = null;
        this.f2500v = null;
        this.f2501w = null;
    }

    /* renamed from: a */
    private String m1264a(String str, int i) {
        String[] split = this.f2497s.split("\\*")[i].split(",");
        if (str.equals("lac")) {
            return split[0];
        }
        if (str.equals("cellid")) {
            return split[1];
        }
        if (str.equals("signal")) {
            return split[2];
        }
        return null;
    }

    /* renamed from: a */
    public static void m1265a(byte[] bArr, int i) {
    }

    /* renamed from: e */
    private byte[] m1266e(String str) {
        int i;
        String[] split = str.split(":");
        if (split == null || split.length != 6) {
            split = new String[6];
            for (int i2 = 0; i2 < 6; i2++) {
                split[i2] = "0";
            }
        }
        byte[] bArr = new byte[6];
        for (int i3 = 0; i3 < split.length; i3++) {
            if (split[i3].length() > 2) {
                split[i3] = split[i3].substring(0, 2);
            }
            try {
                i = Integer.parseInt(split[i3], 16);
            } catch (NumberFormatException e) {
                C3614g.m1308a(e, "Req", "getMacBa");
                i = 0;
            }
            bArr[i3] = (byte) i;
        }
        return bArr;
    }

    @Override // com.iflytek.aiui.pro.C3571aa
    /* renamed from: a */
    public Map<String, String> mo919a() {
        return this.f2479a;
    }

    @Override // com.iflytek.aiui.pro.C3571aa
    /* renamed from: a */
    public void mo921a(String str) {
        this.f2480b = str;
    }

    @Override // com.iflytek.aiui.pro.C3571aa
    /* renamed from: a */
    public void mo923a(Map<String, String> map) {
        this.f2479a = map;
    }

    @Override // com.iflytek.aiui.pro.C3571aa
    /* renamed from: b */
    public String mo925b() {
        return this.f2480b;
    }

    /* renamed from: b */
    public String m1267b(String str) {
        if (!this.f2496r.contains(str + ">")) {
            return "0";
        }
        return this.f2496r.substring(this.f2496r.indexOf(str + ">") + str.length() + 1, this.f2496r.indexOf("</" + str));
    }

    /* JADX WARN: Can't wrap try/catch for region: R(17:20|21|22|(1:24)|25|26|(1:28)(5:68|69|70|(1:72)|73)|29|(1:31)|32|(11:37|(7:40|41|42|43|(2:45|46)(1:48)|47|38)|52|53|(1:55)|56|57|58|59|60|61)|67|57|58|59|60|61) */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01ed A[Catch: all -> 0x0329, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01fb A[Catch: all -> 0x0329, TRY_ENTER, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0258 A[Catch: all -> 0x0329, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0268 A[Catch: all -> 0x0329, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0277 A[Catch: all -> 0x0329, TRY_LEAVE, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x02b8 A[Catch: all -> 0x0329, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0201 A[Catch: all -> 0x0329, TRY_LEAVE, TryCatch #5 {all -> 0x0329, blocks: (B:3:0x0010, B:5:0x0076, B:6:0x008e, B:9:0x00d2, B:11:0x00d6, B:12:0x00d8, B:15:0x0119, B:17:0x0123, B:18:0x0125, B:20:0x012d, B:21:0x012f, B:22:0x01e9, B:24:0x01ed, B:25:0x01ef, B:28:0x01fb, B:29:0x0254, B:31:0x0258, B:32:0x025a, B:34:0x0268, B:37:0x026c, B:38:0x0274, B:40:0x0277, B:43:0x029b, B:47:0x02a4, B:51:0x0291, B:53:0x02b4, B:55:0x02b8, B:56:0x02ba, B:57:0x02cf, B:67:0x02ca, B:68:0x0201, B:76:0x0236, B:77:0x0134, B:79:0x0144, B:83:0x0169, B:86:0x0172, B:88:0x017c, B:91:0x01df, B:92:0x007c, B:42:0x0288, B:70:0x0206, B:73:0x0227), top: B:2:0x0010, inners: #0, #1 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public byte[] m1268c() {
        ByteArrayOutputStream byteArrayOutputStream;
        String[] split;
        try {
            try {
                m1269d();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                this.f2501w = byteArrayOutputStream2;
                byteArrayOutputStream2.write(Byte.parseByte("1"));
                this.f2501w.write(C3614g.m1327b(0));
                C3622k.m1387a(this.f2501w, this.f2481c);
                C3622k.m1387a(this.f2501w, this.f2482d);
                C3622k.m1387a(this.f2501w, this.f2487i);
                C3622k.m1387a(this.f2501w, "");
                C3622k.m1387a(this.f2501w, this.f2483e);
                C3622k.m1387a(this.f2501w, this.f2484f);
                C3622k.m1387a(this.f2501w, this.f2491m);
                C3622k.m1387a(this.f2501w, this.f2485g);
                C3622k.m1387a(this.f2501w, this.f2488j);
                C3622k.m1387a(this.f2501w, this.f2489k);
                if (TextUtils.isEmpty(this.f2490l)) {
                    this.f2501w.write(0);
                } else {
                    byte[] m1266e = m1266e(this.f2490l);
                    this.f2501w.write((byte) m1266e.length);
                    this.f2501w.write(m1266e);
                }
                C3622k.m1387a(this.f2501w, this.f2492n);
                this.f2501w.write(0);
                C3622k.m1387a(this.f2501w, this.f2493o);
                this.f2501w.write(Byte.parseByte(this.f2494p));
                this.f2486h = "0";
                this.f2501w.write(Byte.parseByte("0"));
                this.f2501w.write(Byte.parseByte(this.f2495q));
                if (!this.f2495q.equals("1")) {
                    if (this.f2495q.equals("2")) {
                        this.f2501w.write(C3614g.m1328b(m1267b("mcc")));
                        this.f2501w.write(C3614g.m1328b(m1267b("sid")));
                        this.f2501w.write(C3614g.m1328b(m1267b("nid")));
                        this.f2501w.write(C3614g.m1328b(m1267b("bid")));
                        this.f2501w.write(C3614g.m1333c(m1267b("lon")));
                        this.f2501w.write(C3614g.m1333c(m1267b("lat")));
                        int parseInt = Integer.parseInt(m1267b("signal"));
                        if (parseInt > 127) {
                            parseInt = 0;
                        }
                        this.f2501w.write((byte) parseInt);
                        byteArrayOutputStream = this.f2501w;
                        byteArrayOutputStream.write(0);
                    }
                    if (this.f2498t == null) {
                    }
                    if (this.f2498t.length() == 0) {
                    }
                    if (this.f2499u == null) {
                    }
                    split = this.f2499u.split("\\*");
                    if (!TextUtils.isEmpty(this.f2499u)) {
                    }
                    this.f2501w.write(0);
                    this.f2501w.write(0);
                    this.f2501w.write(C3614g.m1327b(0));
                    CRC32 crc32 = new CRC32();
                    crc32.update(this.f2501w.toByteArray());
                    byte[] m1316a = C3614g.m1316a(crc32.getValue());
                    byte[] bArr = new byte[this.f2501w.toByteArray().length + m1316a.length];
                    System.arraycopy(this.f2501w.toByteArray(), 0, bArr, 0, this.f2501w.toByteArray().length);
                    System.arraycopy(m1316a, 0, bArr, this.f2501w.toByteArray().length, m1316a.length);
                    m1265a(bArr, 0);
                    return bArr;
                }
                if (this.f2496r == null) {
                    this.f2496r = "";
                }
                this.f2501w.write(C3614g.m1328b(m1267b("mcc")));
                this.f2501w.write(C3614g.m1328b(m1267b("mnc")));
                this.f2501w.write(C3614g.m1328b(m1267b("lac")));
                this.f2501w.write(C3614g.m1333c(m1267b("cellid")));
                int parseInt2 = Integer.parseInt(m1267b("signal"));
                if (parseInt2 > 127) {
                    parseInt2 = 0;
                }
                this.f2501w.write((byte) parseInt2);
                if (this.f2497s == null) {
                    this.f2497s = "";
                }
                if (this.f2497s.length() != 0) {
                    int length = this.f2497s.split("\\*").length;
                    this.f2501w.write((byte) length);
                    for (int i = 0; i < length; i++) {
                        this.f2501w.write(C3614g.m1328b(m1264a("lac", i)));
                        this.f2501w.write(C3614g.m1333c(m1264a("cellid", i)));
                        int parseInt3 = Integer.parseInt(m1264a("signal", i));
                        if (parseInt3 > 127) {
                            parseInt3 = 0;
                        }
                        this.f2501w.write((byte) parseInt3);
                    }
                    if (this.f2498t == null) {
                    }
                    if (this.f2498t.length() == 0) {
                    }
                    if (this.f2499u == null) {
                    }
                    split = this.f2499u.split("\\*");
                    if (!TextUtils.isEmpty(this.f2499u)) {
                        this.f2501w.write((byte) split.length);
                        while (r2 < split.length) {
                        }
                        if (this.f2500v == null) {
                        }
                        this.f2501w.write(C3614g.m1327b(Integer.parseInt(this.f2500v)));
                        this.f2501w.write(0);
                        this.f2501w.write(C3614g.m1327b(0));
                        CRC32 crc322 = new CRC32();
                        crc322.update(this.f2501w.toByteArray());
                        byte[] m1316a2 = C3614g.m1316a(crc322.getValue());
                        byte[] bArr2 = new byte[this.f2501w.toByteArray().length + m1316a2.length];
                        System.arraycopy(this.f2501w.toByteArray(), 0, bArr2, 0, this.f2501w.toByteArray().length);
                        System.arraycopy(m1316a2, 0, bArr2, this.f2501w.toByteArray().length, m1316a2.length);
                        m1265a(bArr2, 0);
                        return bArr2;
                    }
                    this.f2501w.write(0);
                    this.f2501w.write(0);
                    this.f2501w.write(C3614g.m1327b(0));
                    CRC32 crc3222 = new CRC32();
                    crc3222.update(this.f2501w.toByteArray());
                    byte[] m1316a22 = C3614g.m1316a(crc3222.getValue());
                    byte[] bArr22 = new byte[this.f2501w.toByteArray().length + m1316a22.length];
                    System.arraycopy(this.f2501w.toByteArray(), 0, bArr22, 0, this.f2501w.toByteArray().length);
                    System.arraycopy(m1316a22, 0, bArr22, this.f2501w.toByteArray().length, m1316a22.length);
                    m1265a(bArr22, 0);
                    return bArr22;
                }
                byteArrayOutputStream = this.f2501w;
                byteArrayOutputStream.write(0);
                if (this.f2498t == null) {
                    this.f2498t = "";
                }
                if (this.f2498t.length() == 0) {
                    this.f2501w.write(0);
                } else {
                    this.f2501w.write(1);
                    try {
                        String[] split2 = this.f2498t.split(",");
                        this.f2501w.write(m1266e(split2[0]));
                        C3622k.m1387a(this.f2501w, split2[2]);
                        int parseInt4 = Integer.parseInt(split2[1]);
                        if (parseInt4 > 127) {
                            parseInt4 = 0;
                        }
                        this.f2501w.write(Byte.parseByte(String.valueOf(parseInt4)));
                    } catch (Throwable th) {
                        C3614g.m1308a(th, "Req", "buildV4Dot216");
                        this.f2501w.write(m1266e("00:00:00:00:00:00"));
                        this.f2501w.write(0);
                        this.f2501w.write(Byte.parseByte("0"));
                    }
                }
                if (this.f2499u == null) {
                    this.f2499u = "";
                }
                split = this.f2499u.split("\\*");
                if (!TextUtils.isEmpty(this.f2499u) && split.length != 0) {
                    this.f2501w.write((byte) split.length);
                    for (String str : split) {
                        String[] split3 = str.split(",");
                        this.f2501w.write(m1266e(split3[0]));
                        try {
                            C3622k.m1387a(this.f2501w, split3[2]);
                        } catch (Throwable th2) {
                            C3614g.m1308a(th2, "Req", "buildV4Dot217");
                            this.f2501w.write(0);
                        }
                        int parseInt5 = Integer.parseInt(split3[1]);
                        if (parseInt5 > 127) {
                            parseInt5 = 0;
                        }
                        this.f2501w.write(Byte.parseByte(String.valueOf(parseInt5)));
                    }
                    if (this.f2500v == null) {
                        this.f2500v = "";
                    }
                    this.f2501w.write(C3614g.m1327b(Integer.parseInt(this.f2500v)));
                    this.f2501w.write(0);
                    this.f2501w.write(C3614g.m1327b(0));
                    CRC32 crc32222 = new CRC32();
                    crc32222.update(this.f2501w.toByteArray());
                    byte[] m1316a222 = C3614g.m1316a(crc32222.getValue());
                    byte[] bArr222 = new byte[this.f2501w.toByteArray().length + m1316a222.length];
                    System.arraycopy(this.f2501w.toByteArray(), 0, bArr222, 0, this.f2501w.toByteArray().length);
                    System.arraycopy(m1316a222, 0, bArr222, this.f2501w.toByteArray().length, m1316a222.length);
                    m1265a(bArr222, 0);
                    return bArr222;
                }
                this.f2501w.write(0);
                this.f2501w.write(0);
                this.f2501w.write(C3614g.m1327b(0));
                CRC32 crc322222 = new CRC32();
                crc322222.update(this.f2501w.toByteArray());
                byte[] m1316a2222 = C3614g.m1316a(crc322222.getValue());
                byte[] bArr2222 = new byte[this.f2501w.toByteArray().length + m1316a2222.length];
                System.arraycopy(this.f2501w.toByteArray(), 0, bArr2222, 0, this.f2501w.toByteArray().length);
                System.arraycopy(m1316a2222, 0, bArr2222, this.f2501w.toByteArray().length, m1316a2222.length);
                m1265a(bArr2222, 0);
                return bArr2222;
            } catch (Throwable th3) {
                th3.printStackTrace();
                this.f2501w.close();
                this.f2501w = null;
                return new byte[0];
            }
            th3.printStackTrace();
            try {
                this.f2501w.close();
            } catch (IOException e) {
                C3614g.m1308a(e, "Req", "buildV4Dot2");
            }
            this.f2501w = null;
            return new byte[0];
        } finally {
            try {
                this.f2501w.close();
            } catch (IOException e2) {
                C3614g.m1308a(e2, "Req", "buildV4Dot2");
            }
            this.f2501w = null;
        }
    }

    /* renamed from: d */
    public void m1269d() {
        if (TextUtils.isEmpty(this.f2494p) || (!this.f2494p.equals("1") && !this.f2494p.equals("2"))) {
            this.f2494p = "0";
        }
        if (TextUtils.isEmpty(this.f2495q)) {
            this.f2495q = "0";
        } else {
            if (this.f2495q.equals("1") || this.f2495q.equals("2")) {
                return;
            }
            this.f2495q = "0";
        }
    }
}
