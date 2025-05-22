package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.io.ByteArrayOutputStream;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.iflytek.aiui.pro.a0 */
/* loaded from: classes4.dex */
public class C3569a0 {

    /* renamed from: f */
    protected Context f2234f;

    /* renamed from: g */
    protected C3626m f2235g;

    /* renamed from: l */
    byte[] f2240l;

    /* renamed from: m */
    byte[] f2241m;

    /* renamed from: a */
    public int f2229a = 20000;

    /* renamed from: b */
    public int f2230b = 20000;

    /* renamed from: c */
    public Proxy f2231c = null;

    /* renamed from: d */
    private String f2232d = "2.1";

    /* renamed from: e */
    String f2233e = "";

    /* renamed from: h */
    private byte[] f2236h = null;

    /* renamed from: i */
    Map<String, String> f2237i = null;

    /* renamed from: j */
    Map<String, String> f2238j = new HashMap();

    /* renamed from: k */
    String f2239k = "";

    public C3569a0() {
    }

    public C3569a0(Context context, C3626m c3626m) {
        this.f2234f = context;
        this.f2235g = c3626m;
    }

    /* renamed from: u */
    private byte[] m876u() {
        if (this.f2240l == null) {
            return new byte[]{0};
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(m888k(this.f2241m));
            byteArrayOutputStream.write(this.f2241m);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: v */
    private byte[] m877v() {
        if (this.f2241m == null) {
            return new byte[]{0};
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] e = C3622k.e(this.f2234f, this.f2241m);
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(m888k(e));
            byteArrayOutputStream.write(e);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    String m878a() {
        byte[] m894q = m894q();
        if (m894q == null || m894q.length == 0) {
            return m893p();
        }
        Map<String, String> m892o = m892o();
        if (m892o == null) {
            return m893p();
        }
        String m1630a = C3652z.m1630a(m892o);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m893p());
        stringBuffer.append("?");
        stringBuffer.append(m1630a);
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public final void m879b(int i) {
        this.f2229a = i;
    }

    /* renamed from: c */
    public void m880c(String str) {
        this.f2239k = str;
    }

    /* renamed from: d */
    public final void m881d(Proxy proxy) {
        this.f2231c = proxy;
    }

    /* renamed from: e */
    public void m882e(Map<String, String> map) {
        this.f2237i = map;
    }

    /* renamed from: f */
    public void m883f(byte[] bArr) {
        this.f2236h = bArr;
    }

    /* renamed from: g */
    public final void m884g(int i) {
        this.f2230b = i;
    }

    /* renamed from: h */
    public void m885h(String str) {
        this.f2233e = str;
    }

    /* renamed from: i */
    public void m886i(Map<String, String> map) {
        this.f2238j = map;
    }

    /* renamed from: j */
    byte[] m887j() {
        byte[] bArr = this.f2236h;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        byte[] m894q = m894q();
        if (m894q != null && m894q.length != 0) {
            return m894q;
        }
        String m1630a = C3652z.m1630a(m892o());
        return !TextUtils.isEmpty(m1630a) ? C3628n.j(m1630a) : m894q;
    }

    /* renamed from: k */
    protected byte[] m888k(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* renamed from: l */
    public Map<String, String> m889l() {
        return this.f2237i;
    }

    /* renamed from: m */
    public void m890m(String str) {
        this.f2232d = str;
    }

    /* renamed from: n */
    public void m891n(byte[] bArr) {
        this.f2241m = (byte[]) bArr.clone();
    }

    /* renamed from: o */
    public Map<String, String> m892o() {
        Map<String, String> map = this.f2238j;
        if (map == null || !map.isEmpty()) {
            return this.f2238j;
        }
        Context context = this.f2234f;
        if (context == null) {
            return null;
        }
        String j = C3618i.m1366j(context);
        String m1385a = C3622k.m1385a();
        String b = C3622k.b(this.f2234f, m1385a, "key=" + j);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", m1385a);
        hashMap.put(TransferTable.COLUMN_KEY, j);
        hashMap.put("scode", b);
        return hashMap;
    }

    /* renamed from: p */
    public String m893p() {
        return this.f2233e;
    }

    /* renamed from: q */
    public byte[] m894q() {
        if (this.f2234f == null || this.f2235g == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(m895r());
            byteArrayOutputStream.write(m896s());
            byteArrayOutputStream.write(m876u());
            byteArrayOutputStream.write(m877v());
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return null;
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: r */
    protected byte[] m895r() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(C3628n.j("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: s */
    protected byte[] m896s() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] d = C3622k.d(this.f2234f);
            byte[] m888k = m888k(d);
            byteArrayOutputStream.write(new byte[]{3});
            byteArrayOutputStream.write(m888k);
            byteArrayOutputStream.write(d);
            byte[] j = C3628n.j(m897t());
            if (j == null || j.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m888k(j));
                byteArrayOutputStream.write(j);
            }
            if (TextUtils.isEmpty(this.f2239k)) {
                C3626m c3626m = this.f2235g;
                this.f2239k = String.format("platform=Android&sdkversion=%s&product=%s", c3626m.f2580b, c3626m.f2579a);
            }
            byte[] j2 = C3628n.j(this.f2239k);
            if (j2 == null || j2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m888k(j2));
                byteArrayOutputStream.write(j2);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: t */
    protected String m897t() {
        return this.f2232d;
    }
}
