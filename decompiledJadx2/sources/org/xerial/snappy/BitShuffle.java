package org.xerial.snappy;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: classes9.dex */
public class BitShuffle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static BitShuffleNative impl;

    static {
        try {
            impl = SnappyLoader.loadBitShuffleApi();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static int shuffle(ByteBuffer byteBuffer, BitShuffleType bitShuffleType, ByteBuffer byteBuffer2) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
        }
        if (!byteBuffer2.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
        }
        int position = byteBuffer.position();
        int remaining = byteBuffer.remaining();
        int typeSize = bitShuffleType.getTypeSize();
        if (remaining % typeSize != 0) {
            throw new IllegalArgumentException("input length must be a multiple of the given type size: " + typeSize);
        }
        if (byteBuffer2.remaining() < remaining) {
            throw new IllegalArgumentException("not enough space for output");
        }
        int shuffleDirectBuffer = impl.shuffleDirectBuffer(byteBuffer, position, typeSize, remaining, byteBuffer2, byteBuffer2.position());
        byteBuffer2.limit(byteBuffer2.position() + shuffleDirectBuffer);
        return shuffleDirectBuffer;
    }

    public static byte[] shuffle(short[] sArr) throws IOException {
        byte[] bArr = new byte[sArr.length * 2];
        impl.shuffle(sArr, 0, 2, sArr.length * 2, bArr, 0);
        return bArr;
    }

    public static byte[] shuffle(int[] iArr) throws IOException {
        byte[] bArr = new byte[iArr.length * 4];
        impl.shuffle(iArr, 0, 4, iArr.length * 4, bArr, 0);
        return bArr;
    }

    public static byte[] shuffle(long[] jArr) throws IOException {
        byte[] bArr = new byte[jArr.length * 8];
        impl.shuffle(jArr, 0, 8, jArr.length * 8, bArr, 0);
        return bArr;
    }

    public static byte[] shuffle(float[] fArr) throws IOException {
        byte[] bArr = new byte[fArr.length * 4];
        impl.shuffle(fArr, 0, 4, fArr.length * 4, bArr, 0);
        return bArr;
    }

    public static byte[] shuffle(double[] dArr) throws IOException {
        byte[] bArr = new byte[dArr.length * 8];
        impl.shuffle(dArr, 0, 8, dArr.length * 8, bArr, 0);
        return bArr;
    }

    public static int unshuffle(ByteBuffer byteBuffer, BitShuffleType bitShuffleType, ByteBuffer byteBuffer2) throws IOException {
        if (!byteBuffer.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "input is not a direct buffer");
        }
        if (!byteBuffer2.isDirect()) {
            throw new SnappyError(SnappyErrorCode.NOT_A_DIRECT_BUFFER, "destination is not a direct buffer");
        }
        int position = byteBuffer.position();
        int remaining = byteBuffer.remaining();
        int typeSize = bitShuffleType.getTypeSize();
        if (remaining % typeSize != 0) {
            throw new IllegalArgumentException("length of input shuffled data must be a multiple of the given type size: " + typeSize);
        }
        if (byteBuffer2.remaining() < remaining) {
            throw new IllegalArgumentException("not enough space for output");
        }
        int unshuffleDirectBuffer = impl.unshuffleDirectBuffer(byteBuffer, position, typeSize, remaining, byteBuffer2, byteBuffer.position());
        byteBuffer.limit(byteBuffer.position() + unshuffleDirectBuffer);
        return unshuffleDirectBuffer;
    }

    public static short[] unshuffleShortArray(byte[] bArr) throws IOException {
        short[] sArr = new short[bArr.length / 2];
        impl.unshuffle(bArr, 0, 2, bArr.length, sArr, 0);
        return sArr;
    }

    public static int[] unshuffleIntArray(byte[] bArr) throws IOException {
        int[] iArr = new int[bArr.length / 4];
        impl.unshuffle(bArr, 0, 4, bArr.length, iArr, 0);
        return iArr;
    }

    public static long[] unshuffleLongArray(byte[] bArr) throws IOException {
        long[] jArr = new long[bArr.length / 8];
        impl.unshuffle(bArr, 0, 8, bArr.length, jArr, 0);
        return jArr;
    }

    public static float[] unshuffleFloatArray(byte[] bArr) throws IOException {
        float[] fArr = new float[bArr.length / 4];
        impl.unshuffle(bArr, 0, 4, bArr.length, fArr, 0);
        return fArr;
    }

    public static double[] unshuffleDoubleArray(byte[] bArr) throws IOException {
        double[] dArr = new double[bArr.length / 8];
        impl.unshuffle(bArr, 0, 8, bArr.length, dArr, 0);
        return dArr;
    }
}
