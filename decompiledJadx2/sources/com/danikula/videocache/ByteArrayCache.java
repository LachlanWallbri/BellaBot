package com.danikula.videocache;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ByteArrayCache implements Cache {
    private volatile boolean completed;
    private volatile byte[] data;

    @Override // com.danikula.videocache.Cache
    public void close() throws ProxyCacheException {
    }

    public ByteArrayCache() {
        this(new byte[0]);
    }

    public ByteArrayCache(byte[] bArr) {
        this.data = (byte[]) Preconditions.checkNotNull(bArr);
    }

    @Override // com.danikula.videocache.Cache
    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        if (j >= this.data.length) {
            return -1;
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("Too long offset for memory cache " + j);
        }
        return new ByteArrayInputStream(this.data).read(bArr, (int) j, i);
    }

    @Override // com.danikula.videocache.Cache
    public long available() throws ProxyCacheException {
        return this.data.length;
    }

    @Override // com.danikula.videocache.Cache
    public void append(byte[] bArr, int i) throws ProxyCacheException {
        Preconditions.checkNotNull(this.data);
        Preconditions.checkArgument(i >= 0 && i <= bArr.length);
        byte[] copyOf = Arrays.copyOf(this.data, this.data.length + i);
        System.arraycopy(bArr, 0, copyOf, this.data.length, i);
        this.data = copyOf;
    }

    @Override // com.danikula.videocache.Cache
    public void complete() {
        this.completed = true;
    }

    @Override // com.danikula.videocache.Cache
    public boolean isCompleted() {
        return this.completed;
    }
}
