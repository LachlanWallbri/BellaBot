package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import net.jpountz.xxhash.XXHash32;
import net.jpountz.xxhash.XXHashFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Lz4XXHash32 extends ByteBufChecksum {
    private static final XXHash32 XXHASH32 = XXHashFactory.fastestInstance().hash32();
    private final int seed;
    private boolean used;
    private int value;

    public Lz4XXHash32(int i) {
        this.seed = i;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        if (this.used) {
            throw new IllegalStateException();
        }
        this.value = XXHASH32.hash(bArr, i, i2, this.seed);
        this.used = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.compression.ByteBufChecksum
    public void update(ByteBuf byteBuf, int i, int i2) {
        if (this.used) {
            throw new IllegalStateException();
        }
        if (byteBuf.hasArray()) {
            this.value = XXHASH32.hash(byteBuf.array(), byteBuf.arrayOffset() + i, i2, this.seed);
        } else {
            this.value = XXHASH32.hash(CompressionUtil.safeNioBuffer(byteBuf, i, i2), this.seed);
        }
        this.used = true;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        if (!this.used) {
            throw new IllegalStateException();
        }
        return this.value & 268435455;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.used = false;
    }
}
