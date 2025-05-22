package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.i */
/* loaded from: classes7.dex */
public final class C5925i {

    /* renamed from: a */
    private ByteBuffer f8161a;

    /* renamed from: b */
    private String f8162b = "GBK";

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.proguard.i$a */
    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a */
        public byte f8163a;

        /* renamed from: b */
        public int f8164b;
    }

    public C5925i() {
    }

    public C5925i(byte[] bArr) {
        this.f8161a = ByteBuffer.wrap(bArr);
    }

    public C5925i(byte[] bArr, int i) {
        this.f8161a = ByteBuffer.wrap(bArr);
        this.f8161a.position(4);
    }

    /* renamed from: a */
    public final void m3693a(byte[] bArr) {
        ByteBuffer byteBuffer = this.f8161a;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        this.f8161a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m3672a(a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.f8163a = (byte) (b & 15);
        aVar.f8164b = (b & 240) >> 4;
        if (aVar.f8164b != 15) {
            return 1;
        }
        aVar.f8164b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private boolean m3676a(int i) {
        a aVar;
        try {
            aVar = new a();
            while (true) {
                int m3672a = m3672a(aVar, this.f8161a.duplicate());
                if (i <= aVar.f8164b || aVar.f8163a == 11) {
                    break;
                }
                this.f8161a.position(this.f8161a.position() + m3672a);
                m3675a(aVar.f8163a);
            }
        } catch (C5923g | BufferUnderflowException unused) {
        }
        return i == aVar.f8164b;
    }

    /* renamed from: a */
    private void m3674a() {
        a aVar = new a();
        do {
            m3672a(aVar, this.f8161a);
            m3675a(aVar.f8163a);
        } while (aVar.f8163a != 11);
    }

    /* renamed from: a */
    private void m3675a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                ByteBuffer byteBuffer = this.f8161a;
                byteBuffer.position(byteBuffer.position() + 1);
                return;
            case 1:
                ByteBuffer byteBuffer2 = this.f8161a;
                byteBuffer2.position(byteBuffer2.position() + 2);
                return;
            case 2:
                ByteBuffer byteBuffer3 = this.f8161a;
                byteBuffer3.position(byteBuffer3.position() + 4);
                return;
            case 3:
                ByteBuffer byteBuffer4 = this.f8161a;
                byteBuffer4.position(byteBuffer4.position() + 8);
                return;
            case 4:
                ByteBuffer byteBuffer5 = this.f8161a;
                byteBuffer5.position(byteBuffer5.position() + 4);
                return;
            case 5:
                ByteBuffer byteBuffer6 = this.f8161a;
                byteBuffer6.position(byteBuffer6.position() + 8);
                return;
            case 6:
                int i2 = this.f8161a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                ByteBuffer byteBuffer7 = this.f8161a;
                byteBuffer7.position(byteBuffer7.position() + i2);
                return;
            case 7:
                int i3 = this.f8161a.getInt();
                ByteBuffer byteBuffer8 = this.f8161a;
                byteBuffer8.position(byteBuffer8.position() + i3);
                return;
            case 8:
                int m3686a = m3686a(0, 0, true);
                while (i < (m3686a << 1)) {
                    a aVar = new a();
                    m3672a(aVar, this.f8161a);
                    m3675a(aVar.f8163a);
                    i++;
                }
                return;
            case 9:
                int m3686a2 = m3686a(0, 0, true);
                while (i < m3686a2) {
                    a aVar2 = new a();
                    m3672a(aVar2, this.f8161a);
                    m3675a(aVar2.f8163a);
                    i++;
                }
                return;
            case 10:
                m3674a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                a aVar3 = new a();
                m3672a(aVar3, this.f8161a);
                if (aVar3.f8163a != 0) {
                    throw new C5923g("skipField with invalid type, type value: " + ((int) b) + ", " + ((int) aVar3.f8163a));
                }
                int m3686a3 = m3686a(0, 0, true);
                ByteBuffer byteBuffer9 = this.f8161a;
                byteBuffer9.position(byteBuffer9.position() + m3686a3);
                return;
            default:
                throw new C5923g("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean m3694a(int i, boolean z) {
        return m3685a((byte) 0, i, z) != 0;
    }

    /* renamed from: a */
    public final byte m3685a(byte b, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return b;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b2 = aVar.f8163a;
        if (b2 == 0) {
            return this.f8161a.get();
        }
        if (b2 == 12) {
            return (byte) 0;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    public final short m3692a(short s, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return s;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 0) {
            return this.f8161a.get();
        }
        if (b == 1) {
            return this.f8161a.getShort();
        }
        if (b == 12) {
            return (short) 0;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    public final int m3686a(int i, int i2, boolean z) {
        if (!m3676a(i2)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return i;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 0) {
            return this.f8161a.get();
        }
        if (b == 1) {
            return this.f8161a.getShort();
        }
        if (b == 2) {
            return this.f8161a.getInt();
        }
        if (b == 12) {
            return 0;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    public final long m3688a(long j, int i, boolean z) {
        int i2;
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return j;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 0) {
            i2 = this.f8161a.get();
        } else if (b == 1) {
            i2 = this.f8161a.getShort();
        } else {
            if (b != 2) {
                if (b == 3) {
                    return this.f8161a.getLong();
                }
                if (b == 12) {
                    return 0L;
                }
                throw new C5923g("type mismatch.");
            }
            i2 = this.f8161a.getInt();
        }
        return i2;
    }

    /* renamed from: a */
    private float m3671a(float f, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return f;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 4) {
            return this.f8161a.getFloat();
        }
        if (b == 12) {
            return 0.0f;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    private double m3670a(double d, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return d;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 4) {
            return this.f8161a.getFloat();
        }
        if (b == 5) {
            return this.f8161a.getDouble();
        }
        if (b == 12) {
            return 0.0d;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: b */
    public final String m3695b(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 6) {
            int i2 = this.f8161a.get();
            if (i2 < 0) {
                i2 += 256;
            }
            byte[] bArr = new byte[i2];
            this.f8161a.get(bArr);
            try {
                return new String(bArr, this.f8162b);
            } catch (UnsupportedEncodingException unused) {
                return new String(bArr);
            }
        }
        if (b == 7) {
            int i3 = this.f8161a.getInt();
            if (i3 > 104857600 || i3 < 0) {
                throw new C5923g("String too long: " + i3);
            }
            byte[] bArr2 = new byte[i3];
            this.f8161a.get(bArr2);
            try {
                return new String(bArr2, this.f8162b);
            } catch (UnsupportedEncodingException unused2) {
                return new String(bArr2);
            }
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> m3691a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m3673a(new HashMap(), map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private <K, V> Map<K, V> m3673a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry<K, V> next = map2.entrySet().iterator().next();
        K key = next.getKey();
        V value = next.getValue();
        if (m3676a(i)) {
            a aVar = new a();
            m3672a(aVar, this.f8161a);
            if (aVar.f8163a == 8) {
                int m3686a = m3686a(0, 0, true);
                if (m3686a < 0) {
                    throw new C5923g("size invalid: " + m3686a);
                }
                for (int i2 = 0; i2 < m3686a; i2++) {
                    map.put(m3690a((C5925i) key, 0, true), m3690a((C5925i) value, 1, true));
                }
            } else {
                throw new C5923g("type mismatch.");
            }
        } else if (z) {
            throw new C5923g("require field not exist.");
        }
        return map;
    }

    /* renamed from: d */
    private boolean[] m3679d(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            boolean[] zArr = new boolean[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                zArr[i2] = m3685a((byte) 0, 0, true) != 0;
            }
            return zArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: c */
    public final byte[] m3696c(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        byte b = aVar.f8163a;
        if (b == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            byte[] bArr = new byte[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                bArr[i2] = m3685a(bArr[0], 0, true);
            }
            return bArr;
        }
        if (b == 13) {
            a aVar2 = new a();
            m3672a(aVar2, this.f8161a);
            if (aVar2.f8163a != 0) {
                throw new C5923g("type mismatch, tag: " + i + ", type: " + ((int) aVar.f8163a) + ", " + ((int) aVar2.f8163a));
            }
            int m3686a2 = m3686a(0, 0, true);
            if (m3686a2 < 0) {
                throw new C5923g("invalid size, tag: " + i + ", type: " + ((int) aVar.f8163a) + ", " + ((int) aVar2.f8163a) + ", size: " + m3686a2);
            }
            byte[] bArr2 = new byte[m3686a2];
            this.f8161a.get(bArr2);
            return bArr2;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: e */
    private short[] m3680e(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            short[] sArr = new short[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                sArr[i2] = m3692a(sArr[0], 0, true);
            }
            return sArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: f */
    private int[] m3681f(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            int[] iArr = new int[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                iArr[i2] = m3686a(iArr[0], 0, true);
            }
            return iArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: g */
    private long[] m3682g(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            long[] jArr = new long[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                jArr[i2] = m3688a(jArr[0], 0, true);
            }
            return jArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: h */
    private float[] m3683h(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            float[] fArr = new float[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                fArr[i2] = m3671a(fArr[0], 0, true);
            }
            return fArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: i */
    private double[] m3684i(int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            double[] dArr = new double[m3686a];
            for (int i2 = 0; i2 < m3686a; i2++) {
                dArr[i2] = m3670a(dArr[0], 0, true);
            }
            return dArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    private <T> T[] m3677a(T[] tArr, int i, boolean z) {
        if (tArr == null || tArr.length == 0) {
            throw new C5923g("unable to get type of key and value.");
        }
        return (T[]) m3678b(tArr[0], i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    private <T> T[] m3678b(T t, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        a aVar = new a();
        m3672a(aVar, this.f8161a);
        if (aVar.f8163a == 9) {
            int m3686a = m3686a(0, 0, true);
            if (m3686a < 0) {
                throw new C5923g("size invalid: " + m3686a);
            }
            T[] tArr = (T[]) ((Object[]) Array.newInstance(t.getClass(), m3686a));
            for (int i2 = 0; i2 < m3686a; i2++) {
                tArr[i2] = m3690a((C5925i) t, 0, true);
            }
            return tArr;
        }
        throw new C5923g("type mismatch.");
    }

    /* renamed from: a */
    public final AbstractC5927k m3689a(AbstractC5927k abstractC5927k, int i, boolean z) {
        if (!m3676a(i)) {
            if (z) {
                throw new C5923g("require field not exist.");
            }
            return null;
        }
        try {
            AbstractC5927k abstractC5927k2 = (AbstractC5927k) abstractC5927k.getClass().newInstance();
            a aVar = new a();
            m3672a(aVar, this.f8161a);
            if (aVar.f8163a != 10) {
                throw new C5923g("type mismatch.");
            }
            abstractC5927k2.mo3649a(this);
            m3674a();
            return abstractC5927k2;
        } catch (Exception e) {
            throw new C5923g(e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public final <T> Object m3690a(T t, int i, boolean z) {
        if (t instanceof Byte) {
            return Byte.valueOf(m3685a((byte) 0, i, z));
        }
        if (t instanceof Boolean) {
            return Boolean.valueOf(m3685a((byte) 0, i, z) != 0);
        }
        if (t instanceof Short) {
            return Short.valueOf(m3692a((short) 0, i, z));
        }
        if (t instanceof Integer) {
            return Integer.valueOf(m3686a(0, i, z));
        }
        if (t instanceof Long) {
            return Long.valueOf(m3688a(0L, i, z));
        }
        if (t instanceof Float) {
            return Float.valueOf(m3671a(0.0f, i, z));
        }
        if (t instanceof Double) {
            return Double.valueOf(m3670a(0.0d, i, z));
        }
        if (t instanceof String) {
            return String.valueOf(m3695b(i, z));
        }
        if (t instanceof Map) {
            return (HashMap) m3673a(new HashMap(), (Map) t, i, z);
        }
        if (t instanceof List) {
            List list = (List) t;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            Object[] m3678b = m3678b(list.get(0), i, z);
            if (m3678b == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : m3678b) {
                arrayList.add(obj);
            }
            return arrayList;
        }
        if (t instanceof AbstractC5927k) {
            return m3689a((AbstractC5927k) t, i, z);
        }
        if (t.getClass().isArray()) {
            if ((t instanceof byte[]) || (t instanceof Byte[])) {
                return m3696c(i, z);
            }
            if (t instanceof boolean[]) {
                return m3679d(i, z);
            }
            if (t instanceof short[]) {
                return m3680e(i, z);
            }
            if (t instanceof int[]) {
                return m3681f(i, z);
            }
            if (t instanceof long[]) {
                return m3682g(i, z);
            }
            if (t instanceof float[]) {
                return m3683h(i, z);
            }
            if (t instanceof double[]) {
                return m3684i(i, z);
            }
            return m3677a((Object[]) t, i, z);
        }
        throw new C5923g("read object error: unsupport type.");
    }

    /* renamed from: a */
    public final int m3687a(String str) {
        this.f8162b = str;
        return 0;
    }
}
