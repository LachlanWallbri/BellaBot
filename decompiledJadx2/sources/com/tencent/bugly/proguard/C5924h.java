package com.tencent.bugly.proguard;

import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.h */
/* loaded from: classes7.dex */
public final class C5924h {

    /* renamed from: a */
    private StringBuilder f8159a;

    /* renamed from: b */
    private int f8160b;

    /* renamed from: a */
    private void m3660a(String str) {
        for (int i = 0; i < this.f8160b; i++) {
            this.f8159a.append('\t');
        }
        if (str != null) {
            StringBuilder sb = this.f8159a;
            sb.append(str);
            sb.append(": ");
        }
    }

    public C5924h(StringBuilder sb, int i) {
        this.f8160b = 0;
        this.f8159a = sb;
        this.f8160b = i;
    }

    /* renamed from: a */
    public final C5924h m3668a(boolean z, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append(z ? 'T' : 'F');
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C5924h m3661a(byte b, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append((int) b);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C5924h m3667a(short s, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append((int) s);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C5924h m3662a(int i, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append(i);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C5924h m3663a(long j, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append(j);
        sb.append('\n');
        return this;
    }

    /* renamed from: a */
    public final C5924h m3665a(String str, String str2) {
        m3660a(str2);
        if (str == null) {
            this.f8159a.append("null\n");
        } else {
            StringBuilder sb = this.f8159a;
            sb.append(str);
            sb.append('\n');
        }
        return this;
    }

    /* renamed from: a */
    public final C5924h m3669a(byte[] bArr, String str) {
        m3660a(str);
        if (bArr == null) {
            this.f8159a.append("null\n");
            return this;
        }
        if (bArr.length == 0) {
            StringBuilder sb = this.f8159a;
            sb.append(bArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f8159a;
        sb2.append(bArr.length);
        sb2.append(", [\n");
        C5924h c5924h = new C5924h(this.f8159a, this.f8160b + 1);
        for (byte b : bArr) {
            c5924h.m3660a(null);
            StringBuilder sb3 = c5924h.f8159a;
            sb3.append((int) b);
            sb3.append('\n');
        }
        m3660a(null);
        StringBuilder sb4 = this.f8159a;
        sb4.append(']');
        sb4.append('\n');
        return this;
    }

    /* renamed from: a */
    public final <K, V> C5924h m3666a(Map<K, V> map, String str) {
        m3660a(str);
        if (map == null) {
            this.f8159a.append("null\n");
            return this;
        }
        if (map.isEmpty()) {
            StringBuilder sb = this.f8159a;
            sb.append(map.size());
            sb.append(", {}\n");
            return this;
        }
        StringBuilder sb2 = this.f8159a;
        sb2.append(map.size());
        sb2.append(", {\n");
        C5924h c5924h = new C5924h(this.f8159a, this.f8160b + 1);
        C5924h c5924h2 = new C5924h(this.f8159a, this.f8160b + 2);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            c5924h.m3660a(null);
            StringBuilder sb3 = c5924h.f8159a;
            sb3.append('(');
            sb3.append('\n');
            c5924h2.m3658a((C5924h) entry.getKey(), (String) null);
            c5924h2.m3658a((C5924h) entry.getValue(), (String) null);
            c5924h.m3660a(null);
            StringBuilder sb4 = c5924h.f8159a;
            sb4.append(')');
            sb4.append('\n');
        }
        m3660a(null);
        StringBuilder sb5 = this.f8159a;
        sb5.append('}');
        sb5.append('\n');
        return this;
    }

    /* renamed from: a */
    private <T> C5924h m3659a(T[] tArr, String str) {
        m3660a(str);
        if (tArr == null) {
            this.f8159a.append("null\n");
            return this;
        }
        if (tArr.length == 0) {
            StringBuilder sb = this.f8159a;
            sb.append(tArr.length);
            sb.append(", []\n");
            return this;
        }
        StringBuilder sb2 = this.f8159a;
        sb2.append(tArr.length);
        sb2.append(", [\n");
        C5924h c5924h = new C5924h(this.f8159a, this.f8160b + 1);
        for (T t : tArr) {
            c5924h.m3658a((C5924h) t, (String) null);
        }
        m3660a(null);
        StringBuilder sb3 = this.f8159a;
        sb3.append(']');
        sb3.append('\n');
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <T> C5924h m3658a(T t, String str) {
        if (t == 0) {
            this.f8159a.append("null\n");
        } else if (t instanceof Byte) {
            byte byteValue = ((Byte) t).byteValue();
            m3660a(str);
            StringBuilder sb = this.f8159a;
            sb.append((int) byteValue);
            sb.append('\n');
        } else if (t instanceof Boolean) {
            boolean booleanValue = ((Boolean) t).booleanValue();
            m3660a(str);
            StringBuilder sb2 = this.f8159a;
            sb2.append(booleanValue ? 'T' : 'F');
            sb2.append('\n');
        } else if (t instanceof Short) {
            short shortValue = ((Short) t).shortValue();
            m3660a(str);
            StringBuilder sb3 = this.f8159a;
            sb3.append((int) shortValue);
            sb3.append('\n');
        } else if (t instanceof Integer) {
            int intValue = ((Integer) t).intValue();
            m3660a(str);
            StringBuilder sb4 = this.f8159a;
            sb4.append(intValue);
            sb4.append('\n');
        } else if (t instanceof Long) {
            long longValue = ((Long) t).longValue();
            m3660a(str);
            StringBuilder sb5 = this.f8159a;
            sb5.append(longValue);
            sb5.append('\n');
        } else if (t instanceof Float) {
            float floatValue = ((Float) t).floatValue();
            m3660a(str);
            StringBuilder sb6 = this.f8159a;
            sb6.append(floatValue);
            sb6.append('\n');
        } else if (t instanceof Double) {
            double doubleValue = ((Double) t).doubleValue();
            m3660a(str);
            StringBuilder sb7 = this.f8159a;
            sb7.append(doubleValue);
            sb7.append('\n');
        } else if (t instanceof String) {
            m3665a((String) t, str);
        } else if (t instanceof Map) {
            m3666a((Map) t, str);
        } else if (t instanceof List) {
            List list = (List) t;
            if (list == null) {
                m3660a(str);
                this.f8159a.append("null\t");
            } else {
                m3659a(list.toArray(), str);
            }
        } else if (t instanceof AbstractC5927k) {
            m3664a((AbstractC5927k) t, str);
        } else if (t instanceof byte[]) {
            m3669a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            m3658a((C5924h) t, str);
        } else {
            int i = 0;
            if (t instanceof short[]) {
                short[] sArr = (short[]) t;
                m3660a(str);
                if (sArr == null) {
                    this.f8159a.append("null\n");
                } else if (sArr.length == 0) {
                    StringBuilder sb8 = this.f8159a;
                    sb8.append(sArr.length);
                    sb8.append(", []\n");
                } else {
                    StringBuilder sb9 = this.f8159a;
                    sb9.append(sArr.length);
                    sb9.append(", [\n");
                    C5924h c5924h = new C5924h(this.f8159a, this.f8160b + 1);
                    int length = sArr.length;
                    while (i < length) {
                        short s = sArr[i];
                        c5924h.m3660a(null);
                        StringBuilder sb10 = c5924h.f8159a;
                        sb10.append((int) s);
                        sb10.append('\n');
                        i++;
                    }
                    m3660a(null);
                    StringBuilder sb11 = this.f8159a;
                    sb11.append(']');
                    sb11.append('\n');
                }
            } else if (t instanceof int[]) {
                int[] iArr = (int[]) t;
                m3660a(str);
                if (iArr == null) {
                    this.f8159a.append("null\n");
                } else if (iArr.length == 0) {
                    StringBuilder sb12 = this.f8159a;
                    sb12.append(iArr.length);
                    sb12.append(", []\n");
                } else {
                    StringBuilder sb13 = this.f8159a;
                    sb13.append(iArr.length);
                    sb13.append(", [\n");
                    C5924h c5924h2 = new C5924h(this.f8159a, this.f8160b + 1);
                    int length2 = iArr.length;
                    while (i < length2) {
                        int i2 = iArr[i];
                        c5924h2.m3660a(null);
                        StringBuilder sb14 = c5924h2.f8159a;
                        sb14.append(i2);
                        sb14.append('\n');
                        i++;
                    }
                    m3660a(null);
                    StringBuilder sb15 = this.f8159a;
                    sb15.append(']');
                    sb15.append('\n');
                }
            } else if (t instanceof long[]) {
                long[] jArr = (long[]) t;
                m3660a(str);
                if (jArr == null) {
                    this.f8159a.append("null\n");
                } else if (jArr.length == 0) {
                    StringBuilder sb16 = this.f8159a;
                    sb16.append(jArr.length);
                    sb16.append(", []\n");
                } else {
                    StringBuilder sb17 = this.f8159a;
                    sb17.append(jArr.length);
                    sb17.append(", [\n");
                    C5924h c5924h3 = new C5924h(this.f8159a, this.f8160b + 1);
                    int length3 = jArr.length;
                    while (i < length3) {
                        long j = jArr[i];
                        c5924h3.m3660a(null);
                        StringBuilder sb18 = c5924h3.f8159a;
                        sb18.append(j);
                        sb18.append('\n');
                        i++;
                    }
                    m3660a(null);
                    StringBuilder sb19 = this.f8159a;
                    sb19.append(']');
                    sb19.append('\n');
                }
            } else if (t instanceof float[]) {
                float[] fArr = (float[]) t;
                m3660a(str);
                if (fArr == null) {
                    this.f8159a.append("null\n");
                } else if (fArr.length == 0) {
                    StringBuilder sb20 = this.f8159a;
                    sb20.append(fArr.length);
                    sb20.append(", []\n");
                } else {
                    StringBuilder sb21 = this.f8159a;
                    sb21.append(fArr.length);
                    sb21.append(", [\n");
                    C5924h c5924h4 = new C5924h(this.f8159a, this.f8160b + 1);
                    int length4 = fArr.length;
                    while (i < length4) {
                        float f = fArr[i];
                        c5924h4.m3660a(null);
                        StringBuilder sb22 = c5924h4.f8159a;
                        sb22.append(f);
                        sb22.append('\n');
                        i++;
                    }
                    m3660a(null);
                    StringBuilder sb23 = this.f8159a;
                    sb23.append(']');
                    sb23.append('\n');
                }
            } else if (t instanceof double[]) {
                double[] dArr = (double[]) t;
                m3660a(str);
                if (dArr == null) {
                    this.f8159a.append("null\n");
                } else if (dArr.length == 0) {
                    StringBuilder sb24 = this.f8159a;
                    sb24.append(dArr.length);
                    sb24.append(", []\n");
                } else {
                    StringBuilder sb25 = this.f8159a;
                    sb25.append(dArr.length);
                    sb25.append(", [\n");
                    C5924h c5924h5 = new C5924h(this.f8159a, this.f8160b + 1);
                    int length5 = dArr.length;
                    while (i < length5) {
                        double d = dArr[i];
                        c5924h5.m3660a(null);
                        StringBuilder sb26 = c5924h5.f8159a;
                        sb26.append(d);
                        sb26.append('\n');
                        i++;
                    }
                    m3660a(null);
                    StringBuilder sb27 = this.f8159a;
                    sb27.append(']');
                    sb27.append('\n');
                }
            } else if (t.getClass().isArray()) {
                m3659a((Object[]) t, str);
            } else {
                throw new C5918b("write object error: unsupport type.");
            }
        }
        return this;
    }

    /* renamed from: a */
    public final C5924h m3664a(AbstractC5927k abstractC5927k, String str) {
        m3660a(str);
        StringBuilder sb = this.f8159a;
        sb.append('{');
        sb.append('\n');
        if (abstractC5927k == null) {
            StringBuilder sb2 = this.f8159a;
            sb2.append('\t');
            sb2.append("null");
        } else {
            abstractC5927k.mo3651a(this.f8159a, this.f8160b + 1);
        }
        m3660a(null);
        StringBuilder sb3 = this.f8159a;
        sb3.append('}');
        sb3.append('\n');
        return this;
    }
}
