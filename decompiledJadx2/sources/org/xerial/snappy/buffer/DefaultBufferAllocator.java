package org.xerial.snappy.buffer;

/* loaded from: classes9.dex */
public class DefaultBufferAllocator implements BufferAllocator {
    public static BufferAllocatorFactory factory = new BufferAllocatorFactory() { // from class: org.xerial.snappy.buffer.DefaultBufferAllocator.1
        public BufferAllocator singleton = new DefaultBufferAllocator();

        @Override // org.xerial.snappy.buffer.BufferAllocatorFactory
        public BufferAllocator getBufferAllocator(int i) {
            return this.singleton;
        }
    };

    @Override // org.xerial.snappy.buffer.BufferAllocator
    public void release(byte[] bArr) {
    }

    @Override // org.xerial.snappy.buffer.BufferAllocator
    public byte[] allocate(int i) {
        return new byte[i];
    }
}
