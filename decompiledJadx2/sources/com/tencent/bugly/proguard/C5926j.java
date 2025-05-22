package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.j */
/* loaded from: classes7.dex */
public final class C5926j {

    /* renamed from: a */
    private ByteBuffer f8165a;

    /* renamed from: b */
    private String f8166b;

    public C5926j(int i) {
        this.f8166b = "GBK";
        this.f8165a = ByteBuffer.allocate(i);
    }

    public C5926j() {
        this(128);
    }

    /* renamed from: a */
    public final ByteBuffer m3700a() {
        return this.f8165a;
    }

    /* renamed from: b */
    public final byte[] m3712b() {
        byte[] bArr = new byte[this.f8165a.position()];
        System.arraycopy(this.f8165a.array(), 0, bArr, 0, this.f8165a.position());
        return bArr;
    }

    /* renamed from: a */
    private void m3697a(int i) {
        if (this.f8165a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f8165a.capacity() + i) << 1);
            allocate.put(this.f8165a.array(), 0, this.f8165a.position());
            this.f8165a = allocate;
        }
    }

    /* renamed from: b */
    private void m3698b(byte b, int i) {
        if (i < 15) {
            this.f8165a.put((byte) (b | (i << 4)));
        } else if (i < 256) {
            this.f8165a.put((byte) (b | 240));
            this.f8165a.put((byte) i);
        } else {
            throw new C5918b("tag is too large: " + i);
        }
    }

    /* renamed from: a */
    public final void m3710a(boolean z, int i) {
        m3701a(z ? (byte) 1 : (byte) 0, i);
    }

    /* renamed from: a */
    public final void m3701a(byte b, int i) {
        m3697a(3);
        if (b == 0) {
            m3698b((byte) 12, i);
        } else {
            m3698b((byte) 0, i);
            this.f8165a.put(b);
        }
    }

    /* renamed from: a */
    public final void m3709a(short s, int i) {
        m3697a(4);
        if (s >= -128 && s <= 127) {
            m3701a((byte) s, i);
        } else {
            m3698b((byte) 1, i);
            this.f8165a.putShort(s);
        }
    }

    /* renamed from: a */
    public final void m3702a(int i, int i2) {
        m3697a(6);
        if (i >= -32768 && i <= 32767) {
            m3709a((short) i, i2);
        } else {
            m3698b((byte) 2, i2);
            this.f8165a.putInt(i);
        }
    }

    /* renamed from: a */
    public final void m3703a(long j, int i) {
        m3697a(10);
        if (j >= -2147483648L && j <= 2147483647L) {
            m3702a((int) j, i);
        } else {
            m3698b((byte) 3, i);
            this.f8165a.putLong(j);
        }
    }

    /* renamed from: a */
    public final void m3706a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f8166b);
        } catch (UnsupportedEncodingException unused) {
            bytes = str.getBytes();
        }
        m3697a(bytes.length + 10);
        if (bytes.length > 255) {
            m3698b((byte) 7, i);
            this.f8165a.putInt(bytes.length);
            this.f8165a.put(bytes);
        } else {
            m3698b((byte) 6, i);
            this.f8165a.put((byte) bytes.length);
            this.f8165a.put(bytes);
        }
    }

    /* renamed from: a */
    public final <K, V> void m3708a(Map<K, V> map, int i) {
        m3697a(8);
        m3698b((byte) 8, i);
        m3702a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                m3705a(entry.getKey(), 0);
                m3705a(entry.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    public final void m3711a(byte[] bArr, int i) {
        m3697a(bArr.length + 8);
        m3698b((byte) 13, i);
        m3698b((byte) 0, 0);
        m3702a(bArr.length, 0);
        this.f8165a.put(bArr);
    }

    /* renamed from: a */
    public final <T> void m3707a(Collection<T> collection, int i) {
        m3697a(8);
        m3698b((byte) 9, i);
        m3702a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                m3705a(it.next(), 0);
            }
        }
    }

    /* renamed from: a */
    public final void m3704a(AbstractC5927k abstractC5927k, int i) {
        m3697a(2);
        m3698b((byte) 10, i);
        abstractC5927k.mo3650a(this);
        m3697a(2);
        m3698b((byte) 11, 0);
    }

    /* renamed from: a */
    public final void m3705a(Object obj, int i) {
        if (obj instanceof Byte) {
            m3701a(((Byte) obj).byteValue(), i);
            return;
        }
        if (obj instanceof Boolean) {
            m3701a(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0, i);
            return;
        }
        if (obj instanceof Short) {
            m3709a(((Short) obj).shortValue(), i);
            return;
        }
        if (obj instanceof Integer) {
            m3702a(((Integer) obj).intValue(), i);
            return;
        }
        if (obj instanceof Long) {
            m3703a(((Long) obj).longValue(), i);
            return;
        }
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            m3697a(6);
            m3698b((byte) 4, i);
            this.f8165a.putFloat(floatValue);
            return;
        }
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            m3697a(10);
            m3698b((byte) 5, i);
            this.f8165a.putDouble(doubleValue);
            return;
        }
        if (obj instanceof String) {
            m3706a((String) obj, i);
            return;
        }
        if (obj instanceof Map) {
            m3708a((Map) obj, i);
            return;
        }
        if (obj instanceof List) {
            m3707a((Collection) obj, i);
            return;
        }
        if (obj instanceof AbstractC5927k) {
            m3697a(2);
            m3698b((byte) 10, i);
            ((AbstractC5927k) obj).mo3650a(this);
            m3697a(2);
            m3698b((byte) 11, 0);
            return;
        }
        if (obj instanceof byte[]) {
            m3711a((byte[]) obj, i);
            return;
        }
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(zArr.length, 0);
            for (boolean z : zArr) {
                m3701a(z ? (byte) 1 : (byte) 0, 0);
            }
            return;
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(sArr.length, 0);
            for (short s : sArr) {
                m3709a(s, 0);
            }
            return;
        }
        if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(iArr.length, 0);
            for (int i2 : iArr) {
                m3702a(i2, 0);
            }
            return;
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(jArr.length, 0);
            for (long j : jArr) {
                m3703a(j, 0);
            }
            return;
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(fArr.length, 0);
            for (float f : fArr) {
                m3697a(6);
                m3698b((byte) 4, 0);
                this.f8165a.putFloat(f);
            }
            return;
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(dArr.length, 0);
            for (double d : dArr) {
                m3697a(10);
                m3698b((byte) 5, 0);
                this.f8165a.putDouble(d);
            }
            return;
        }
        if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m3697a(8);
            m3698b((byte) 9, i);
            m3702a(objArr.length, 0);
            for (Object obj2 : objArr) {
                m3705a(obj2, 0);
            }
            return;
        }
        if (obj instanceof Collection) {
            m3707a((Collection) obj, i);
        } else {
            throw new C5918b("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int m3699a(String str) {
        this.f8166b = str;
        return 0;
    }
}
