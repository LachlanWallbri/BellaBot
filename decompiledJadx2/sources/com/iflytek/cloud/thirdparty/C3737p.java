package com.iflytek.cloud.thirdparty;

import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.iflytek.cloud.thirdparty.p */
/* loaded from: classes3.dex */
public class C3737p {

    /* renamed from: c */
    private int f3379c;

    /* renamed from: d */
    private long f3380d;

    /* renamed from: e */
    private long f3381e;

    /* renamed from: i */
    private boolean f3385i;

    /* renamed from: j */
    private boolean f3386j;

    /* renamed from: k */
    private long f3387k;

    /* renamed from: a */
    private final LinkedList<a> f3377a = new LinkedList<>();

    /* renamed from: b */
    private final LinkedList<a> f3378b = new LinkedList<>();

    /* renamed from: f */
    private long f3382f = 0;

    /* renamed from: g */
    private long f3383g = 0;

    /* renamed from: h */
    private long f3384h = 0;

    /* renamed from: l */
    private final Object f3388l = new Object();

    public C3737p(long j, int i, long j2, boolean z, boolean z2) {
        this.f3379c = 512;
        this.f3380d = 5120L;
        this.f3381e = -1L;
        this.f3385i = false;
        this.f3386j = true;
        this.f3387k = 0L;
        this.f3381e = j;
        this.f3380d = j2;
        this.f3379c = i;
        this.f3385i = z;
        this.f3386j = z2;
        this.f3387k = this.f3381e + (this.f3379c * 2);
        if (this.f3385i) {
            return;
        }
        m2217l();
    }

    /* renamed from: a */
    public void m2219a(long j) {
        synchronized (this.f3388l) {
            this.f3381e += j;
            this.f3387k = this.f3381e + (this.f3379c * 2);
        }
    }

    /* renamed from: a */
    public long m2218a() {
        long j;
        synchronized (this.f3388l) {
            j = this.f3381e;
        }
        return j;
    }

    /* renamed from: b */
    public long m2222b() {
        long j;
        synchronized (this.f3388l) {
            j = this.f3384h - this.f3383g;
        }
        return j;
    }

    /* renamed from: c */
    public int m2223c() {
        int size;
        synchronized (this.f3388l) {
            size = this.f3377a.size();
        }
        return size;
    }

    /* renamed from: d */
    public void m2224d() {
        synchronized (this.f3388l) {
            this.f3383g = 0L;
            this.f3384h = 0L;
            while (!this.f3377a.isEmpty()) {
                a pop = this.f3377a.pop();
                if (this.f3385i) {
                    pop.mo2229a();
                }
                this.f3378b.add(pop);
            }
        }
    }

    /* renamed from: e */
    public boolean m2225e() {
        boolean isEmpty;
        synchronized (this.f3388l) {
            isEmpty = this.f3377a.isEmpty();
        }
        return isEmpty;
    }

    /* renamed from: f */
    public a m2226f() {
        a aVar;
        synchronized (this.f3388l) {
            if (this.f3377a.isEmpty()) {
                aVar = null;
            } else {
                aVar = this.f3377a.pop();
                this.f3383g += aVar.getValue().intValue();
                if (!this.f3385i && aVar.getKey() != null) {
                    this.f3382f -= aVar.getKey().length;
                }
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public void m2220a(a aVar) {
        synchronized (this.f3388l) {
            if (aVar != null) {
                if (this.f3385i) {
                    aVar.mo2229a();
                    this.f3378b.add(aVar);
                } else if (this.f3382f < this.f3381e && aVar.getKey() != null) {
                    this.f3378b.add(aVar);
                    this.f3382f += aVar.getKey().length;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0099 A[Catch: all -> 0x00e4, TryCatch #0 {, blocks: (B:13:0x0014, B:16:0x001c, B:18:0x0020, B:21:0x002e, B:22:0x0062, B:24:0x0063, B:26:0x0068, B:29:0x0073, B:32:0x0087, B:35:0x0099, B:37:0x009d, B:38:0x00b7, B:40:0x00d3, B:41:0x00d8, B:44:0x00dd, B:47:0x00a2, B:49:0x0092, B:50:0x00e2), top: B:12:0x0014 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x00dd -> B:43:0x0096). Please report as a decompilation issue!!! */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m2221a(byte[] bArr, int i, int i2) throws IllegalArgumentException, NullPointerException, OutOfMemoryError {
        int i3;
        int min;
        int i4 = i + i2;
        if (bArr == null || bArr.length < i4 || i2 < 0 || (this.f3385i && i > 0)) {
            throw new IllegalArgumentException();
        }
        synchronized (this.f3388l) {
            if (0 < this.f3381e && i2 > 0) {
                if (!this.f3386j && (this.f3384h - this.f3383g) + i2 > this.f3381e) {
                    throw new OutOfMemoryError("current start=" + this.f3383g + ", current end=" + this.f3384h + ", data len=" + i2 + ", has over max len=" + this.f3381e);
                }
                a peekLast = this.f3385i ? null : this.f3377a.peekLast();
                if (peekLast != null && peekLast.getKey().length != peekLast.getValue().intValue()) {
                    i3 = peekLast.getValue().intValue();
                    while (i < i4) {
                        if (this.f3385i) {
                            peekLast.m2230a(bArr);
                            min = i2;
                        } else {
                            min = Math.min(i4 - i, peekLast.getKey().length - i3);
                            System.arraycopy(bArr, i, peekLast.getKey(), i3, min);
                        }
                        i += min;
                        peekLast.setValue(Integer.valueOf(i3 + min));
                        this.f3384h += min;
                        if (!peekLast.equals(this.f3377a.peekLast())) {
                            this.f3377a.add(peekLast);
                        }
                        m2216k();
                        if (i < i4) {
                            peekLast = m2214i();
                            i3 = 0;
                            while (i < i4) {
                            }
                        }
                    }
                }
                peekLast = m2214i();
                i3 = 0;
                while (i < i4) {
                }
            }
        }
    }

    /* renamed from: g */
    public long m2227g() {
        long size;
        synchronized (this.f3388l) {
            size = this.f3378b.size() * this.f3379c;
        }
        return size;
    }

    /* renamed from: h */
    public void m2228h() {
        boolean z;
        synchronized (this.f3388l) {
            z = false;
            while (this.f3380d < this.f3382f && !this.f3378b.isEmpty()) {
                this.f3378b.remove();
                this.f3382f -= this.f3379c;
                z = true;
            }
        }
        if (z) {
            System.gc();
        }
    }

    /* renamed from: i */
    private a m2214i() throws OutOfMemoryError {
        byte[] bArr = null;
        a remove = !this.f3378b.isEmpty() ? this.f3378b.remove() : null;
        if (remove != null) {
            return remove;
        }
        if (this.f3385i || !m2215j()) {
            int i = 0;
            if (!this.f3385i) {
                i = this.f3379c;
                bArr = new byte[i];
                this.f3382f += i;
            }
            return new a(bArr, Integer.valueOf(i));
        }
        throw new OutOfMemoryError("current buffer len=" + this.f3382f + ", has match max len: " + this.f3381e);
    }

    /* renamed from: j */
    private boolean m2215j() {
        return -1 != this.f3381e && this.f3387k <= this.f3382f;
    }

    /* renamed from: k */
    private void m2216k() throws OutOfMemoryError {
        if (this.f3377a.peek() != null) {
            long intValue = this.f3383g + r0.getValue().intValue();
            if (m2218a() <= this.f3384h - intValue) {
                a remove = this.f3377a.remove();
                if (this.f3385i) {
                    remove.mo2229a();
                }
                this.f3378b.add(remove);
                this.f3383g = intValue;
            }
        }
    }

    /* renamed from: l */
    private void m2217l() {
        long j = this.f3380d;
        if (0 < j) {
            int i = (int) ((j / this.f3379c) + 2);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = this.f3379c;
                this.f3378b.add(new a(new byte[i3], Integer.valueOf(i3)));
                this.f3382f += this.f3379c;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.p$b */
    /* loaded from: classes3.dex */
    public static class b<K, V> implements Map.Entry<K, V> {

        /* renamed from: a */
        private K f3389a;

        /* renamed from: b */
        private V f3390b;

        public b(K k, V v) {
            this.f3389a = null;
            this.f3390b = null;
            this.f3389a = k;
            this.f3390b = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f3389a;
        }

        /* renamed from: a */
        public K m2230a(K k) {
            this.f3389a = k;
            return k;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f3390b;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f3390b;
            this.f3390b = v;
            return v2;
        }

        /* renamed from: a */
        public void mo2229a() {
            this.f3389a = null;
            this.f3390b = null;
        }
    }

    /* renamed from: com.iflytek.cloud.thirdparty.p$a */
    /* loaded from: classes3.dex */
    public static class a extends b<byte[], Integer> {
        @Override // com.iflytek.cloud.thirdparty.C3737p.b
        /* renamed from: a */
        public /* bridge */ /* synthetic */ void mo2229a() {
            super.mo2229a();
        }

        public a(byte[] bArr, Integer num) {
            super(bArr, num);
        }
    }
}
