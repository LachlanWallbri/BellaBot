package org.jboss.netty.buffer;

import java.nio.ByteOrder;

/* loaded from: classes7.dex */
public class BigEndianHeapChannelBuffer extends HeapChannelBuffer {
    public BigEndianHeapChannelBuffer(int i) {
        super(i);
    }

    public BigEndianHeapChannelBuffer(byte[] bArr) {
        super(bArr);
    }

    private BigEndianHeapChannelBuffer(byte[] bArr, int i, int i2) {
        super(bArr, i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBufferFactory factory() {
        return HeapChannelBufferFactory.getInstance(ByteOrder.BIG_ENDIAN);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short getShort(int i) {
        return (short) ((this.array[i + 1] & 255) | (this.array[i] << 8));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getUnsignedMedium(int i) {
        return ((this.array[i + 2] & 255) << 0) | ((this.array[i] & 255) << 16) | ((this.array[i + 1] & 255) << 8);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getInt(int i) {
        return ((this.array[i + 3] & 255) << 0) | ((this.array[i] & 255) << 24) | ((this.array[i + 1] & 255) << 16) | ((this.array[i + 2] & 255) << 8);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long getLong(int i) {
        return ((this.array[i] & 255) << 56) | ((this.array[i + 1] & 255) << 48) | ((this.array[i + 2] & 255) << 40) | ((this.array[i + 3] & 255) << 32) | ((this.array[i + 4] & 255) << 24) | ((this.array[i + 5] & 255) << 16) | ((this.array[i + 6] & 255) << 8) | ((255 & this.array[i + 7]) << 0);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setShort(int i, int i2) {
        this.array[i] = (byte) (i2 >>> 8);
        this.array[i + 1] = (byte) (i2 >>> 0);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setMedium(int i, int i2) {
        this.array[i] = (byte) (i2 >>> 16);
        this.array[i + 1] = (byte) (i2 >>> 8);
        this.array[i + 2] = (byte) (i2 >>> 0);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setInt(int i, int i2) {
        this.array[i] = (byte) (i2 >>> 24);
        this.array[i + 1] = (byte) (i2 >>> 16);
        this.array[i + 2] = (byte) (i2 >>> 8);
        this.array[i + 3] = (byte) (i2 >>> 0);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setLong(int i, long j) {
        this.array[i] = (byte) (j >>> 56);
        this.array[i + 1] = (byte) (j >>> 48);
        this.array[i + 2] = (byte) (j >>> 40);
        this.array[i + 3] = (byte) (j >>> 32);
        this.array[i + 4] = (byte) (j >>> 24);
        this.array[i + 5] = (byte) (j >>> 16);
        this.array[i + 6] = (byte) (j >>> 8);
        this.array[i + 7] = (byte) (j >>> 0);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer duplicate() {
        return new BigEndianHeapChannelBuffer(this.array, readerIndex(), writerIndex());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer copy(int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > this.array.length) {
            throw new IndexOutOfBoundsException("Too many bytes to copy - Need " + (i + i2) + ", maximum is " + this.array.length);
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.array, i, bArr, 0, i2);
        return new BigEndianHeapChannelBuffer(bArr);
    }
}
