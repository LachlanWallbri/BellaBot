package org.apache.commons.compress.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* loaded from: classes9.dex */
public class BitInputStream implements Closeable {
    private static final long[] MASKS = new long[64];
    private static final int MAXIMUM_CACHE_SIZE = 63;
    private long bitsCached;
    private int bitsCachedSize;
    private final ByteOrder byteOrder;

    /* renamed from: in */
    private final CountingInputStream f8971in;

    static {
        for (int i = 1; i <= 63; i++) {
            long[] jArr = MASKS;
            jArr[i] = (jArr[i - 1] << 1) + 1;
        }
    }

    public BitInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.f8971in = new CountingInputStream(inputStream);
        this.byteOrder = byteOrder;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8971in.close();
    }

    public void clearBitCache() {
        this.bitsCached = 0L;
        this.bitsCachedSize = 0;
    }

    public long readBits(int i) throws IOException {
        if (i < 0 || i > 63) {
            throw new IOException("count must not be negative or greater than 63");
        }
        if (ensureCache(i)) {
            return -1L;
        }
        if (this.bitsCachedSize < i) {
            return processBitsGreater57(i);
        }
        return readCachedBits(i);
    }

    public int bitsCached() {
        return this.bitsCachedSize;
    }

    public long bitsAvailable() throws IOException {
        return this.bitsCachedSize + (this.f8971in.available() * 8);
    }

    public void alignWithByteBoundary() {
        int i = this.bitsCachedSize % 8;
        if (i > 0) {
            readCachedBits(i);
        }
    }

    public long getBytesRead() {
        return this.f8971in.getBytesRead();
    }

    private long processBitsGreater57(int i) throws IOException {
        long j;
        int i2 = i - this.bitsCachedSize;
        int i3 = 8 - i2;
        long read = this.f8971in.read();
        if (read < 0) {
            return read;
        }
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            long[] jArr = MASKS;
            this.bitsCached = ((jArr[i2] & read) << this.bitsCachedSize) | this.bitsCached;
            j = (read >>> i2) & jArr[i3];
        } else {
            this.bitsCached <<= i2;
            long[] jArr2 = MASKS;
            this.bitsCached = ((read >>> i3) & jArr2[i2]) | this.bitsCached;
            j = read & jArr2[i3];
        }
        long j2 = this.bitsCached & MASKS[i];
        this.bitsCached = j;
        this.bitsCachedSize = i3;
        return j2;
    }

    private long readCachedBits(int i) {
        long j;
        if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
            long j2 = this.bitsCached;
            j = j2 & MASKS[i];
            this.bitsCached = j2 >>> i;
        } else {
            j = (this.bitsCached >> (this.bitsCachedSize - i)) & MASKS[i];
        }
        this.bitsCachedSize -= i;
        return j;
    }

    private boolean ensureCache(int i) throws IOException {
        while (true) {
            int i2 = this.bitsCachedSize;
            if (i2 >= i || i2 >= 57) {
                return false;
            }
            long read = this.f8971in.read();
            if (read < 0) {
                return true;
            }
            if (this.byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.bitsCached = (read << this.bitsCachedSize) | this.bitsCached;
            } else {
                this.bitsCached <<= 8;
                this.bitsCached = read | this.bitsCached;
            }
            this.bitsCachedSize += 8;
        }
    }
}
