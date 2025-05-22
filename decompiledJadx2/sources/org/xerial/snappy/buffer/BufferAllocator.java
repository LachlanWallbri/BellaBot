package org.xerial.snappy.buffer;

/* loaded from: classes9.dex */
public interface BufferAllocator {
    byte[] allocate(int i);

    void release(byte[] bArr);
}
