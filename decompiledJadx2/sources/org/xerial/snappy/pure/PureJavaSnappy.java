package org.xerial.snappy.pure;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.xerial.snappy.SnappyApi;
import sun.misc.Unsafe;

/* loaded from: classes9.dex */
public class PureJavaSnappy implements SnappyApi {
    private static final ConcurrentLinkedDeque<SoftReference<short[]>> CACHED_TABLES = new ConcurrentLinkedDeque<>();
    private static final int MAX_OUTPUT_LENGTH = Integer.MAX_VALUE;

    @Override // org.xerial.snappy.SnappyApi
    public long rawCompress(long j, long j2, long j3) throws IOException {
        short[] table = getTable();
        try {
            return SnappyRawCompressor.compress(null, j, j2, null, j3, 2147483647L, table);
        } finally {
            returnTable(table);
        }
    }

    @Override // org.xerial.snappy.SnappyApi
    public long rawUncompress(long j, long j2, long j3) throws IOException {
        return SnappyRawDecompressor.decompress(null, j, j2, null, j3, 2147483647L);
    }

    @Override // org.xerial.snappy.SnappyApi
    public int rawCompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException {
        long arrayOffset;
        long arrayOffset2;
        byte[] bArr;
        byte[] array;
        long arrayOffset3;
        long arrayOffset4;
        int compress;
        if (byteBuffer.isDirect()) {
            long address = UnsafeUtil.getAddress(byteBuffer);
            arrayOffset = byteBuffer.position() + address;
            arrayOffset2 = address + byteBuffer.limit();
            bArr = null;
        } else if (byteBuffer.hasArray()) {
            byte[] array2 = byteBuffer.array();
            arrayOffset = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer.arrayOffset() + byteBuffer.position();
            arrayOffset2 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer.arrayOffset() + byteBuffer.limit();
            bArr = array2;
        } else {
            throw new IllegalArgumentException("Unsupported input ByteBuffer implementation " + byteBuffer.getClass().getName());
        }
        long j = arrayOffset2;
        long j2 = arrayOffset;
        if (byteBuffer2.isDirect()) {
            long address2 = UnsafeUtil.getAddress(byteBuffer2);
            array = null;
            arrayOffset4 = address2 + byteBuffer2.limit();
            arrayOffset3 = byteBuffer2.position() + address2;
        } else if (byteBuffer2.hasArray()) {
            array = byteBuffer2.array();
            arrayOffset3 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer2.arrayOffset() + byteBuffer2.position();
            arrayOffset4 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer2.arrayOffset() + byteBuffer2.limit();
        } else {
            throw new IllegalArgumentException("Unsupported output ByteBuffer implementation " + byteBuffer2.getClass().getName());
        }
        synchronized (byteBuffer) {
            synchronized (byteBuffer2) {
                short[] table = getTable();
                try {
                    compress = SnappyRawCompressor.compress(bArr, j2, j, array, arrayOffset3, arrayOffset4, table);
                    byteBuffer2.position(byteBuffer2.position() + compress);
                } finally {
                    returnTable(table);
                }
            }
        }
        return compress;
    }

    @Override // org.xerial.snappy.SnappyApi
    public int rawCompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException {
        long j = Unsafe.ARRAY_BYTE_BASE_OFFSET + i;
        long j2 = j + i2;
        long j3 = Unsafe.ARRAY_BYTE_BASE_OFFSET + i3;
        long j4 = j3 + 2147483647L;
        short[] table = getTable();
        try {
            return SnappyRawCompressor.compress(obj, j, j2, obj2, j3, j4, table);
        } finally {
            returnTable(table);
        }
    }

    @Override // org.xerial.snappy.SnappyApi
    public int rawUncompress(ByteBuffer byteBuffer, int i, int i2, ByteBuffer byteBuffer2, int i3) throws IOException {
        long arrayOffset;
        long arrayOffset2;
        byte[] bArr;
        byte[] array;
        long arrayOffset3;
        long arrayOffset4;
        int decompress;
        if (byteBuffer.isDirect()) {
            long address = UnsafeUtil.getAddress(byteBuffer);
            arrayOffset = byteBuffer.position() + address;
            arrayOffset2 = address + byteBuffer.limit();
            bArr = null;
        } else if (byteBuffer.hasArray()) {
            byte[] array2 = byteBuffer.array();
            arrayOffset = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer.arrayOffset() + byteBuffer.position();
            arrayOffset2 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer.arrayOffset() + byteBuffer.limit();
            bArr = array2;
        } else {
            throw new IllegalArgumentException("Unsupported input ByteBuffer implementation " + byteBuffer.getClass().getName());
        }
        long j = arrayOffset2;
        long j2 = arrayOffset;
        if (byteBuffer2.isDirect()) {
            long address2 = UnsafeUtil.getAddress(byteBuffer2);
            array = null;
            arrayOffset4 = address2 + byteBuffer2.limit();
            arrayOffset3 = byteBuffer2.position() + address2;
        } else if (byteBuffer2.hasArray()) {
            array = byteBuffer2.array();
            arrayOffset3 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer2.arrayOffset() + byteBuffer2.position();
            arrayOffset4 = Unsafe.ARRAY_BYTE_BASE_OFFSET + byteBuffer2.arrayOffset() + byteBuffer2.limit();
        } else {
            throw new IllegalArgumentException("Unsupported output ByteBuffer implementation " + byteBuffer2.getClass().getName());
        }
        synchronized (byteBuffer) {
            synchronized (byteBuffer2) {
                decompress = SnappyRawDecompressor.decompress(bArr, j2, j, array, arrayOffset3, arrayOffset4);
                byteBuffer2.position(byteBuffer2.position() + decompress);
            }
        }
        return decompress;
    }

    @Override // org.xerial.snappy.SnappyApi
    public int rawUncompress(Object obj, int i, int i2, Object obj2, int i3) throws IOException {
        long j = Unsafe.ARRAY_BYTE_BASE_OFFSET + i;
        long j2 = j + i2;
        long j3 = Unsafe.ARRAY_BYTE_BASE_OFFSET + i3;
        return SnappyRawDecompressor.decompress(obj, j, j2, obj2, j3, j3 + 2147483647L);
    }

    @Override // org.xerial.snappy.SnappyApi
    public int maxCompressedLength(int i) {
        return SnappyRawCompressor.maxCompressedLength(i);
    }

    @Override // org.xerial.snappy.SnappyApi
    public int uncompressedLength(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        byte[] array;
        long j;
        long j2;
        if (byteBuffer.isDirect()) {
            long address = UnsafeUtil.getAddress(byteBuffer);
            j = byteBuffer.position() + address;
            j2 = address + byteBuffer.limit();
            array = null;
        } else if (byteBuffer.hasArray()) {
            array = byteBuffer.array();
            j = Unsafe.ARRAY_BYTE_BASE_OFFSET + i;
            j2 = Unsafe.ARRAY_BYTE_BASE_OFFSET + i2;
        } else {
            throw new IllegalArgumentException("Unsupported input ByteBuffer implementation: " + byteBuffer.getClass().getName());
        }
        return SnappyRawDecompressor.getUncompressedLength(array, j, j2);
    }

    @Override // org.xerial.snappy.SnappyApi
    public int uncompressedLength(Object obj, int i, int i2) throws IOException {
        return SnappyRawDecompressor.getUncompressedLength(obj, Unsafe.ARRAY_BYTE_BASE_OFFSET + i, Unsafe.ARRAY_BYTE_BASE_OFFSET + i2);
    }

    @Override // org.xerial.snappy.SnappyApi
    public long uncompressedLength(long j, long j2) throws IOException {
        return SnappyRawDecompressor.getUncompressedLength(null, j, j2 + j);
    }

    @Override // org.xerial.snappy.SnappyApi
    public boolean isValidCompressedBuffer(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        throw new UnsupportedOperationException("isValidCompressedBuffer is not supported in pure-java mode");
    }

    @Override // org.xerial.snappy.SnappyApi
    public boolean isValidCompressedBuffer(Object obj, int i, int i2) throws IOException {
        throw new UnsupportedOperationException("isValidCompressedBuffer is not supported in pure-java mode");
    }

    @Override // org.xerial.snappy.SnappyApi
    public boolean isValidCompressedBuffer(long j, long j2, long j3) throws IOException {
        throw new UnsupportedOperationException("isValidCompressedBuffer is not supported in pure-java mode");
    }

    @Override // org.xerial.snappy.SnappyApi
    public void arrayCopy(Object obj, int i, int i2, Object obj2, int i3) throws IOException {
        System.arraycopy(obj, i, obj2, i3, i2);
    }

    private static short[] getTable() {
        short[] sArr;
        do {
            SoftReference<short[]> poll = CACHED_TABLES.poll();
            if (poll == null) {
                return new short[16384];
            }
            sArr = poll.get();
        } while (sArr == null);
        boolean z = true;
        while (z) {
            SoftReference<short[]> peekLast = CACHED_TABLES.peekLast();
            if (peekLast == null) {
                break;
            }
            if (peekLast.get() == null) {
                CACHED_TABLES.removeLastOccurrence(peekLast);
            } else {
                z = false;
            }
        }
        return sArr;
    }

    private static void returnTable(short[] sArr) {
        CACHED_TABLES.addFirst(new SoftReference<>(sArr));
    }
}
