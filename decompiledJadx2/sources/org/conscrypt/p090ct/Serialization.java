package org.conscrypt.p090ct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class Serialization {
    private static final int DER_LENGTH_LONG_FORM_FLAG = 128;
    private static final int DER_TAG_MASK = 63;
    private static final int DER_TAG_OCTET_STRING = 4;

    private Serialization() {
    }

    public static byte[] readDEROctetString(byte[] bArr) throws SerializationException {
        return readDEROctetString(new ByteArrayInputStream(bArr));
    }

    public static byte[] readDEROctetString(InputStream inputStream) throws SerializationException {
        int readByte = readByte(inputStream) & 63;
        if (readByte != 4) {
            throw new SerializationException("Wrong DER tag, expected OCTET STRING, got " + readByte);
        }
        int readNumber = readNumber(inputStream, 1);
        if ((readNumber & 128) != 0) {
            readNumber = readNumber(inputStream, readNumber & (-129));
        }
        return readFixedBytes(inputStream, readNumber);
    }

    public static byte[][] readList(byte[] bArr, int i, int i2) throws SerializationException {
        return readList(new ByteArrayInputStream(bArr), i, i2);
    }

    public static byte[][] readList(InputStream inputStream, int i, int i2) throws SerializationException {
        ArrayList arrayList = new ArrayList();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readVariableBytes(inputStream, i));
        while (byteArrayInputStream.available() > 0) {
            try {
                arrayList.add(readVariableBytes(byteArrayInputStream, i2));
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        }
        return (byte[][]) arrayList.toArray(new byte[arrayList.size()]);
    }

    public static byte[] readVariableBytes(InputStream inputStream, int i) throws SerializationException {
        return readFixedBytes(inputStream, readNumber(inputStream, i));
    }

    public static byte[] readFixedBytes(InputStream inputStream, int i) throws SerializationException {
        try {
            if (i < 0) {
                throw new SerializationException("Negative length: " + i);
            }
            byte[] bArr = new byte[i];
            int read = inputStream.read(bArr);
            if (read >= i) {
                return bArr;
            }
            throw new SerializationException("Premature end of input, expected " + i + " bytes, only read " + read);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static int readNumber(InputStream inputStream, int i) throws SerializationException {
        if (i > 4 || i < 0) {
            throw new SerializationException("Invalid width: " + i);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) | (readByte(inputStream) & 255);
        }
        return i2;
    }

    public static long readLong(InputStream inputStream, int i) throws SerializationException {
        if (i > 8 || i < 0) {
            throw new IllegalArgumentException("Invalid width: " + i);
        }
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | (readByte(inputStream) & 255);
        }
        return j;
    }

    public static byte readByte(InputStream inputStream) throws SerializationException {
        try {
            int read = inputStream.read();
            if (read != -1) {
                return (byte) read;
            }
            throw new SerializationException("Premature end of input, could not read byte.");
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static void writeVariableBytes(OutputStream outputStream, byte[] bArr, int i) throws SerializationException {
        writeNumber(outputStream, bArr.length, i);
        writeFixedBytes(outputStream, bArr);
    }

    public static void writeFixedBytes(OutputStream outputStream, byte[] bArr) throws SerializationException {
        try {
            outputStream.write(bArr);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static void writeNumber(OutputStream outputStream, long j, int i) throws SerializationException {
        if (i < 0) {
            throw new SerializationException("Negative width: " + i);
        }
        if (i < 8 && j >= (1 << (i * 8))) {
            throw new SerializationException("Number too large, " + j + " does not fit in " + i + " bytes");
        }
        while (i > 0) {
            if ((i - 1) * 8 < 64) {
                try {
                    outputStream.write((byte) ((j >> ((int) r0)) & 255));
                } catch (IOException e) {
                    throw new SerializationException(e);
                }
            } else {
                outputStream.write(0);
            }
            i--;
        }
    }
}
