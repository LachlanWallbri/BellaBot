package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public interface SnappyApi {
    void arrayCopy(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    boolean isValidCompressedBuffer(long j, long j2, long j3) throws IOException;

    boolean isValidCompressedBuffer(Object obj, int i, int i2) throws IOException;

    boolean isValidCompressedBuffer(ByteBuffer byteBuffer, int i, int i2) throws IOException;

    int maxCompressedLength(int i);

    int rawCompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    int rawCompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException;

    long rawCompress(long j, long j2, long j3) throws IOException;

    int rawUncompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    int rawUncompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException;

    long rawUncompress(long j, long j2, long j3) throws IOException;

    int uncompressedLength(Object obj, int i, int i2) throws IOException;

    int uncompressedLength(ByteBuffer byteBuffer, int i, int i2) throws IOException;

    long uncompressedLength(long j, long j2) throws IOException;
}
