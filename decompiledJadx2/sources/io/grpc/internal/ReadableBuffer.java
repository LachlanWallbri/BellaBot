package io.grpc.internal;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ReadableBuffer extends Closeable {
    byte[] array();

    int arrayOffset();

    boolean byteBufferSupported();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Nullable
    ByteBuffer getByteBuffer();

    boolean hasArray();

    void mark();

    boolean markSupported();

    ReadableBuffer readBytes(int i);

    void readBytes(OutputStream outputStream, int i) throws IOException;

    void readBytes(ByteBuffer byteBuffer);

    void readBytes(byte[] bArr, int i, int i2);

    int readInt();

    int readUnsignedByte();

    int readableBytes();

    void reset();

    void skipBytes(int i);
}
