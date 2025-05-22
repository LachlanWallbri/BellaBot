package org.xerial.snappy;

import androidx.core.os.EnvironmentCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Properties;

/* loaded from: classes9.dex */
public class Snappy {
    private static SnappyApi impl;

    static {
        init();
    }

    public static void cleanUp() {
        SnappyLoader.cleanUpExtractedNativeLib();
        SnappyLoader.setSnappyApi(null);
    }

    static void init() {
        try {
            impl = SnappyLoader.loadSnappyApi();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void arrayCopy(Object obj, int i, int i2, Object obj2, int i3) throws IOException {
        impl.arrayCopy(obj, i, i2, obj2, i3);
    }

    public static byte[] compress(byte[] bArr) throws IOException {
        return rawCompress(bArr, bArr.length);
    }

    public static int compress(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException {
        return rawCompress(bArr, i, i2, bArr2, i3);
    }

    public static int compress(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
        }
        if (!byteBuffer2.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
        }
        int rawCompress = impl.rawCompress(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), byteBuffer2, byteBuffer2.position());
        byteBuffer2.limit(byteBuffer2.position() + rawCompress);
        return rawCompress;
    }

    public static byte[] compress(char[] cArr) throws IOException {
        return rawCompress(cArr, cArr.length * 2);
    }

    public static byte[] compress(double[] dArr) throws IOException {
        return rawCompress(dArr, dArr.length * 8);
    }

    public static byte[] compress(float[] fArr) throws IOException {
        return rawCompress(fArr, fArr.length * 4);
    }

    public static byte[] compress(int[] iArr) throws IOException {
        return rawCompress(iArr, iArr.length * 4);
    }

    public static byte[] compress(long[] jArr) throws IOException {
        return rawCompress(jArr, jArr.length * 8);
    }

    public static byte[] compress(short[] sArr) throws IOException {
        return rawCompress(sArr, sArr.length * 2);
    }

    public static byte[] compress(String str) throws IOException {
        try {
            return compress(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("UTF-8 encoder is not found");
        }
    }

    public static byte[] compress(String str, String str2) throws UnsupportedEncodingException, IOException {
        return compress(str.getBytes(str2));
    }

    public static byte[] compress(String str, Charset charset) throws IOException {
        return compress(str.getBytes(charset));
    }

    public static String getNativeLibraryVersion() {
        URL resource = SnappyLoader.class.getResource("/org/xerial/snappy/VERSION");
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        if (resource != null) {
            InputStream inputStream = null;
            try {
                try {
                    Properties properties = new Properties();
                    inputStream = resource.openStream();
                    properties.load(inputStream);
                    String property = properties.getProperty("version", EnvironmentCompat.MEDIA_UNKNOWN);
                    try {
                        if (property.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                            property = properties.getProperty("SNAPPY_VERSION", property);
                        }
                        str = property.trim().replaceAll("[^0-9\\.]", "");
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static boolean isValidCompressedBuffer(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("input is null");
        }
        return impl.isValidCompressedBuffer(bArr, i, i2);
    }

    public static boolean isValidCompressedBuffer(byte[] bArr) throws IOException {
        return isValidCompressedBuffer(bArr, 0, bArr.length);
    }

    public static boolean isValidCompressedBuffer(ByteBuffer byteBuffer) throws IOException {
        return impl.isValidCompressedBuffer(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static boolean isValidCompressedBuffer(long j, long j2, long j3) throws IOException {
        return impl.isValidCompressedBuffer(j, j2, j3);
    }

    public static int maxCompressedLength(int i) {
        return impl.maxCompressedLength(i);
    }

    public static long rawCompress(long j, long j2, long j3) throws IOException {
        return impl.rawCompress(j, j2, j3);
    }

    public static long rawUncompress(long j, long j2, long j3) throws IOException {
        return impl.rawUncompress(j, j2, j3);
    }

    public static byte[] rawCompress(Object obj, int i) throws IOException {
        byte[] bArr = new byte[maxCompressedLength(i)];
        int rawCompress = impl.rawCompress(obj, 0, i, bArr, 0);
        byte[] bArr2 = new byte[rawCompress];
        System.arraycopy(bArr, 0, bArr2, 0, rawCompress);
        return bArr2;
    }

    public static int rawCompress(Object obj, int i, int i2, byte[] bArr, int i3) throws IOException {
        if (obj == null || bArr == null) {
            throw new NullPointerException("input or output is null");
        }
        return impl.rawCompress(obj, i, i2, bArr, i3);
    }

    public static int rawUncompress(byte[] bArr, int i, int i2, Object obj, int i3) throws IOException {
        if (bArr == null || obj == null) {
            throw new NullPointerException("input or output is null");
        }
        return impl.rawUncompress(bArr, i, i2, obj, i3);
    }

    public static byte[] uncompress(byte[] bArr) throws IOException {
        byte[] bArr2 = new byte[uncompressedLength(bArr)];
        uncompress(bArr, 0, bArr.length, bArr2, 0);
        return bArr2;
    }

    public static int uncompress(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException {
        return rawUncompress(bArr, i, i2, bArr2, i3);
    }

    public static int uncompress(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
        }
        if (!byteBuffer2.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
        }
        int rawUncompress = impl.rawUncompress(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), byteBuffer2, byteBuffer2.position());
        byteBuffer2.limit(byteBuffer2.position() + rawUncompress);
        return rawUncompress;
    }

    public static char[] uncompressCharArray(byte[] bArr) throws IOException {
        return uncompressCharArray(bArr, 0, bArr.length);
    }

    public static char[] uncompressCharArray(byte[] bArr, int i, int i2) throws IOException {
        char[] cArr = new char[uncompressedLength(bArr, i, i2) / 2];
        impl.rawUncompress(bArr, i, i2, cArr, 0);
        return cArr;
    }

    public static double[] uncompressDoubleArray(byte[] bArr) throws IOException {
        double[] dArr = new double[uncompressedLength(bArr, 0, bArr.length) / 8];
        impl.rawUncompress(bArr, 0, bArr.length, dArr, 0);
        return dArr;
    }

    public static int uncompressedLength(byte[] bArr) throws IOException {
        return impl.uncompressedLength(bArr, 0, bArr.length);
    }

    public static int uncompressedLength(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("input is null");
        }
        return impl.uncompressedLength(bArr, i, i2);
    }

    public static int uncompressedLength(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
        }
        return impl.uncompressedLength(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static long uncompressedLength(long j, long j2) throws IOException {
        return impl.uncompressedLength(j, j2);
    }

    public static float[] uncompressFloatArray(byte[] bArr) throws IOException {
        return uncompressFloatArray(bArr, 0, bArr.length);
    }

    public static float[] uncompressFloatArray(byte[] bArr, int i, int i2) throws IOException {
        float[] fArr = new float[uncompressedLength(bArr, i, i2) / 4];
        impl.rawUncompress(bArr, i, i2, fArr, 0);
        return fArr;
    }

    public static int[] uncompressIntArray(byte[] bArr) throws IOException {
        return uncompressIntArray(bArr, 0, bArr.length);
    }

    public static int[] uncompressIntArray(byte[] bArr, int i, int i2) throws IOException {
        int[] iArr = new int[uncompressedLength(bArr, i, i2) / 4];
        impl.rawUncompress(bArr, i, i2, iArr, 0);
        return iArr;
    }

    public static long[] uncompressLongArray(byte[] bArr) throws IOException {
        return uncompressLongArray(bArr, 0, bArr.length);
    }

    public static long[] uncompressLongArray(byte[] bArr, int i, int i2) throws IOException {
        long[] jArr = new long[uncompressedLength(bArr, i, i2) / 8];
        impl.rawUncompress(bArr, i, i2, jArr, 0);
        return jArr;
    }

    public static short[] uncompressShortArray(byte[] bArr) throws IOException {
        return uncompressShortArray(bArr, 0, bArr.length);
    }

    public static short[] uncompressShortArray(byte[] bArr, int i, int i2) throws IOException {
        short[] sArr = new short[uncompressedLength(bArr, i, i2) / 2];
        impl.rawUncompress(bArr, i, i2, sArr, 0);
        return sArr;
    }

    public static String uncompressString(byte[] bArr) throws IOException {
        try {
            return uncompressString(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("UTF-8 decoder is not found");
        }
    }

    public static String uncompressString(byte[] bArr, int i, int i2) throws IOException {
        try {
            return uncompressString(bArr, i, i2, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new IllegalStateException("UTF-8 decoder is not found");
        }
    }

    public static String uncompressString(byte[] bArr, int i, int i2, String str) throws IOException, UnsupportedEncodingException {
        byte[] bArr2 = new byte[uncompressedLength(bArr, i, i2)];
        uncompress(bArr, i, i2, bArr2, 0);
        return new String(bArr2, str);
    }

    public static String uncompressString(byte[] bArr, int i, int i2, Charset charset) throws IOException, UnsupportedEncodingException {
        byte[] bArr2 = new byte[uncompressedLength(bArr, i, i2)];
        uncompress(bArr, i, i2, bArr2, 0);
        return new String(bArr2, charset);
    }

    public static String uncompressString(byte[] bArr, String str) throws IOException, UnsupportedEncodingException {
        return new String(uncompress(bArr), str);
    }

    public static String uncompressString(byte[] bArr, Charset charset) throws IOException, UnsupportedEncodingException {
        return new String(uncompress(bArr), charset);
    }
}
