package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class SnappyNative implements SnappyApi {
    @Override // org.xerial.snappy.SnappyApi
    public native void arrayCopy(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native boolean isValidCompressedBuffer(long j, long j2, long j3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native boolean isValidCompressedBuffer(Object obj, int i, int i2) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native boolean isValidCompressedBuffer(ByteBuffer byteBuffer, int i, int i2) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int maxCompressedLength(int i);

    public native String nativeLibraryVersion();

    @Override // org.xerial.snappy.SnappyApi
    public native int rawCompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int rawCompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native long rawCompress(long j, long j2, long j3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int rawUncompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int rawUncompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native long rawUncompress(long j, long j2, long j3) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int uncompressedLength(Object obj, int i, int i2) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native int uncompressedLength(ByteBuffer byteBuffer, int i, int i2) throws IOException;

    @Override // org.xerial.snappy.SnappyApi
    public native long uncompressedLength(long j, long j2) throws IOException;

    public void throw_error(int i) throws IOException {
        throw new IOException(String.format("%s(%d)", SnappyErrorCode.getErrorMessage(i), Integer.valueOf(i)));
    }
}
