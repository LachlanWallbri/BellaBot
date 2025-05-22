package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class Bzip2BitReader {
    private static final int MAX_COUNT_OF_READABLE_BYTES = 268435455;
    private long bitBuffer;
    private int bitCount;

    /* renamed from: in */
    private ByteBuf f8350in;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setByteBuf(ByteBuf byteBuf) {
        this.f8350in = byteBuf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readBits(int i) {
        long readUnsignedByte;
        int i2;
        if (i < 0 || i > 32) {
            throw new IllegalArgumentException("count: " + i + " (expected: 0-32 )");
        }
        int i3 = this.bitCount;
        long j = this.bitBuffer;
        if (i3 < i) {
            int readableBytes = this.f8350in.readableBytes();
            if (readableBytes == 1) {
                readUnsignedByte = this.f8350in.readUnsignedByte();
                i2 = 8;
            } else if (readableBytes == 2) {
                readUnsignedByte = this.f8350in.readUnsignedShort();
                i2 = 16;
            } else if (readableBytes == 3) {
                readUnsignedByte = this.f8350in.readUnsignedMedium();
                i2 = 24;
            } else {
                readUnsignedByte = this.f8350in.readUnsignedInt();
                i2 = 32;
            }
            j = (j << i2) | readUnsignedByte;
            i3 += i2;
            this.bitBuffer = j;
        }
        int i4 = i3 - i;
        this.bitCount = i4;
        return (int) ((j >>> i4) & (i != 32 ? (1 << i) - 1 : 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean readBoolean() {
        return readBits(1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int readInt() {
        return readBits(32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refill() {
        this.bitBuffer = (this.bitBuffer << 8) | this.f8350in.readUnsignedByte();
        this.bitCount += 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReadable() {
        return this.bitCount > 0 || this.f8350in.isReadable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasReadableBits(int i) {
        if (i >= 0) {
            return this.bitCount >= i || ((this.f8350in.readableBytes() << 3) & Integer.MAX_VALUE) >= i - this.bitCount;
        }
        throw new IllegalArgumentException("count: " + i + " (expected value greater than 0)");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasReadableBytes(int i) {
        if (i < 0 || i > MAX_COUNT_OF_READABLE_BYTES) {
            throw new IllegalArgumentException("count: " + i + " (expected: 0-" + MAX_COUNT_OF_READABLE_BYTES + ')');
        }
        return hasReadableBits(i << 3);
    }
}
