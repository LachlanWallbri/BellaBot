package com.danikula.videocache;

import java.io.ByteArrayInputStream;

/* loaded from: classes.dex */
public class ByteArraySource implements Source {
    private ByteArrayInputStream arrayInputStream;
    private final byte[] data;

    @Override // com.danikula.videocache.Source
    public void close() throws ProxyCacheException {
    }

    public ByteArraySource(byte[] bArr) {
        this.data = bArr;
    }

    @Override // com.danikula.videocache.Source
    public int read(byte[] bArr) throws ProxyCacheException {
        return this.arrayInputStream.read(bArr, 0, bArr.length);
    }

    @Override // com.danikula.videocache.Source
    public long length() throws ProxyCacheException {
        return this.data.length;
    }

    @Override // com.danikula.videocache.Source
    public void open(long j) throws ProxyCacheException {
        this.arrayInputStream = new ByteArrayInputStream(this.data);
        this.arrayInputStream.skip(j);
    }
}
