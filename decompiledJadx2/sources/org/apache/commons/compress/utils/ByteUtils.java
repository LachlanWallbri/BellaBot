package org.apache.commons.compress.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes9.dex */
public final class ByteUtils {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /* loaded from: classes9.dex */
    public interface ByteConsumer {
        void accept(int i) throws IOException;
    }

    /* loaded from: classes9.dex */
    public interface ByteSupplier {
        int getAsByte() throws IOException;
    }

    private ByteUtils() {
    }

    public static long fromLittleEndian(byte[] bArr) {
        return fromLittleEndian(bArr, 0, bArr.length);
    }

    public static long fromLittleEndian(byte[] bArr, int i, int i2) {
        checkReadLength(i2);
        long j = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            j |= (bArr[i + i3] & 255) << (i3 * 8);
        }
        return j;
    }

    public static long fromLittleEndian(InputStream inputStream, int i) throws IOException {
        checkReadLength(i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long read = inputStream.read();
            if (read == -1) {
                throw new IOException("Premature end of data");
            }
            j |= read << (i2 * 8);
        }
        return j;
    }

    public static long fromLittleEndian(ByteSupplier byteSupplier, int i) throws IOException {
        checkReadLength(i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            long asByte = byteSupplier.getAsByte();
            if (asByte == -1) {
                throw new IOException("Premature end of data");
            }
            j |= asByte << (i2 * 8);
        }
        return j;
    }

    public static long fromLittleEndian(DataInput dataInput, int i) throws IOException {
        checkReadLength(i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j |= dataInput.readUnsignedByte() << (i2 * 8);
        }
        return j;
    }

    public static void toLittleEndian(byte[] bArr, long j, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = (byte) (255 & j);
            j >>= 8;
        }
    }

    public static void toLittleEndian(OutputStream outputStream, long j, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            outputStream.write((int) (255 & j));
            j >>= 8;
        }
    }

    public static void toLittleEndian(ByteConsumer byteConsumer, long j, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            byteConsumer.accept((int) (255 & j));
            j >>= 8;
        }
    }

    public static void toLittleEndian(DataOutput dataOutput, long j, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            dataOutput.write((int) (255 & j));
            j >>= 8;
        }
    }

    /* loaded from: classes9.dex */
    public static class InputStreamByteSupplier implements ByteSupplier {

        /* renamed from: is */
        private final InputStream f8973is;

        public InputStreamByteSupplier(InputStream inputStream) {
            this.f8973is = inputStream;
        }

        @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
        public int getAsByte() throws IOException {
            return this.f8973is.read();
        }
    }

    /* loaded from: classes9.dex */
    public static class OutputStreamByteConsumer implements ByteConsumer {

        /* renamed from: os */
        private final OutputStream f8974os;

        public OutputStreamByteConsumer(OutputStream outputStream) {
            this.f8974os = outputStream;
        }

        @Override // org.apache.commons.compress.utils.ByteUtils.ByteConsumer
        public void accept(int i) throws IOException {
            this.f8974os.write(i);
        }
    }

    private static void checkReadLength(int i) {
        if (i > 8) {
            throw new IllegalArgumentException("Can't read more than eight bytes into a long value");
        }
    }
}
