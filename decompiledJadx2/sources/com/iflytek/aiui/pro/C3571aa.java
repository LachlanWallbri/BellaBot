package com.iflytek.aiui.pro;

import android.content.Context;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.io.ByteArrayOutputStream;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.aa */
/* loaded from: classes.dex */
public class C3571aa {

    /* renamed from: B */
    protected Context f2254B;

    /* renamed from: C */
    protected C3626m f2255C;

    /* renamed from: G */
    byte[] f2259G;

    /* renamed from: H */
    byte[] f2260H;

    /* renamed from: x */
    public int f2263x = 20000;

    /* renamed from: y */
    public int f2264y = 20000;

    /* renamed from: z */
    public Proxy f2265z = null;

    /* renamed from: a */
    private String f2261a = "2.1";

    /* renamed from: A */
    String f2253A = "";

    /* renamed from: b */
    private byte[] f2262b = null;

    /* renamed from: D */
    Map<String, String> f2256D = null;

    /* renamed from: E */
    Map<String, String> f2257E = new HashMap();

    /* renamed from: F */
    String f2258F = "";

    public C3571aa() {
    }

    public C3571aa(Context context, C3626m c3626m) {
        this.f2254B = context;
        this.f2255C = c3626m;
    }

    /* renamed from: c */
    private byte[] m917c() {
        if (this.f2259G == null) {
            return new byte[]{0};
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(m928b(this.f2260H));
            byteArrayOutputStream.write(this.f2260H);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                return byteArray;
            } catch (Throwable th) {
                return byteArray;
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private byte[] m918d() {
        if (this.f2260H == null) {
            return new byte[]{0};
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] m1389a = C3622k.m1389a(this.f2254B, this.f2260H);
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(m928b(m1389a));
            byteArrayOutputStream.write(m1389a);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                return byteArray;
            } catch (Throwable th) {
                return byteArray;
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public Map<String, String> mo919a() {
        return this.f2256D;
    }

    /* renamed from: a */
    public final void m920a(int i) {
        this.f2263x = i;
    }

    /* renamed from: a */
    public void mo921a(String str) {
        this.f2253A = str;
    }

    /* renamed from: a */
    public final void m922a(Proxy proxy) {
        this.f2265z = proxy;
    }

    /* renamed from: a */
    public void mo923a(Map<String, String> map) {
        this.f2256D = map;
    }

    /* renamed from: a */
    public void m924a(byte[] bArr) {
        this.f2262b = bArr;
    }

    /* renamed from: b */
    public String mo925b() {
        return this.f2253A;
    }

    /* renamed from: b */
    public final void m926b(int i) {
        this.f2264y = i;
    }

    /* renamed from: b */
    public void m927b(Map<String, String> map) {
        this.f2257E = map;
    }

    /* renamed from: b */
    protected byte[] m928b(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    /* renamed from: c */
    public void m929c(String str) {
        this.f2258F = str;
    }

    /* renamed from: c */
    public void m930c(byte[] bArr) {
        this.f2260H = (byte[]) bArr.clone();
    }

    /* renamed from: d */
    public void m931d(String str) {
        this.f2261a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public String m932e() {
        byte[] m935h = m935h();
        if (m935h == null || m935h.length == 0) {
            return mo925b();
        }
        Map<String, String> m934g = m934g();
        if (m934g == null) {
            return mo925b();
        }
        String m1630a = C3652z.m1630a(m934g);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mo925b());
        stringBuffer.append("?");
        stringBuffer.append(m1630a);
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public byte[] m933f() {
        byte[] bArr = this.f2262b;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        byte[] m935h = m935h();
        if (m935h != null && m935h.length != 0) {
            return m935h;
        }
        String m1630a = C3652z.m1630a(m934g());
        return !TextUtils.isEmpty(m1630a) ? C3628n.m1415a(m1630a) : m935h;
    }

    /* renamed from: g */
    public Map<String, String> m934g() {
        Map<String, String> map = this.f2257E;
        if (map == null || !map.isEmpty()) {
            return this.f2257E;
        }
        Context context = this.f2254B;
        if (context == null) {
            return null;
        }
        String m1361e = C3618i.m1361e(context);
        String m1385a = C3622k.m1385a();
        String m1386a = C3622k.m1386a(this.f2254B, m1385a, "key=" + m1361e);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", m1385a);
        hashMap.put(TransferTable.COLUMN_KEY, m1361e);
        hashMap.put("scode", m1386a);
        return hashMap;
    }

    /* renamed from: h */
    public byte[] m935h() {
        byte[] bArr = null;
        if (this.f2254B == null || this.f2255C == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                byteArrayOutputStream.write(m936i());
                byteArrayOutputStream.write(m937j());
                byteArrayOutputStream.write(m917c());
                byteArrayOutputStream.write(m918d());
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr;
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th2) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            th4.printStackTrace();
            return bArr;
        }
    }

    /* renamed from: i */
    protected byte[] m936i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(C3628n.m1415a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                return byteArray;
            } catch (Throwable th) {
                return byteArray;
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    return null;
                }
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: j */
    protected byte[] m937j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] m1388a = C3622k.m1388a(this.f2254B);
            byte[] m928b = m928b(m1388a);
            byteArrayOutputStream.write(new byte[]{3});
            byteArrayOutputStream.write(m928b);
            byteArrayOutputStream.write(m1388a);
            byte[] m1415a = C3628n.m1415a(m938k());
            if (m1415a == null || m1415a.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m928b(m1415a));
                byteArrayOutputStream.write(m1415a);
            }
            if (TextUtils.isEmpty(this.f2258F)) {
                this.f2258F = String.format("platform=Android&sdkversion=%s&product=%s", this.f2255C.f2580b, this.f2255C.f2579a);
            }
            byte[] m1415a2 = C3628n.m1415a(this.f2258F);
            if (m1415a2 == null || m1415a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(m928b(m1415a2));
                byteArrayOutputStream.write(m1415a2);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                return byteArray;
            } catch (Throwable th) {
                return byteArray;
            }
        } catch (Throwable th2) {
            try {
                th2.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                return new byte[]{0};
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
            }
        }
    }

    /* renamed from: k */
    protected String m938k() {
        return this.f2261a;
    }
}
