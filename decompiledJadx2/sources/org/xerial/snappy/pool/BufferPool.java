package org.xerial.snappy.pool;

import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public interface BufferPool {
    byte[] allocateArray(int i);

    ByteBuffer allocateDirect(int i);

    void releaseArray(byte[] bArr);

    void releaseDirect(ByteBuffer byteBuffer);
}
