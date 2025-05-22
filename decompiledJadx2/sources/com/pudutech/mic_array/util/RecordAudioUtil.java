package com.pudutech.mic_array.util;

/* loaded from: classes5.dex */
public class RecordAudioUtil {
    public static int mic1 = 8;
    public static int mic2 = 2;
    public static int mic3 = 7;
    public static int mic4 = 1;
    public static int mic5 = 9;
    public static int mic6 = 3;
    public static int ref1 = 10;
    public static int ref2 = 4;

    public static byte[] resetAudio(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[(length * 4) / 6];
        int i = (length / 6) / 4;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 6;
            int i4 = i2 * 4;
            System.arraycopy(bArr, i3 * 4, bArr2, i4 * 4, 4);
            System.arraycopy(bArr, (i3 + 3) * 4, bArr2, (i4 + 1) * 4, 4);
            System.arraycopy(bArr, (i3 + 4) * 4, bArr2, (i4 + 2) * 4, 8);
        }
        return bArr2;
    }

    public static byte[] addCnFor1Mic(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length / 4; i++) {
            int i2 = i * 8;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 4;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
        }
        return bArr2;
    }

    public static byte[] addCnFor2Mic(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length / 8; i++) {
            int i2 = i * 16;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 8;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 0;
            bArr2[i2 + 10] = bArr[i3 + 4];
            bArr2[i2 + 11] = bArr[i3 + 5];
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 0;
            bArr2[i2 + 14] = bArr[i3 + 6];
            bArr2[i2 + 15] = bArr[i3 + 7];
        }
        return bArr2;
    }

    public static byte[] addCnFor2MicN1(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 4];
        for (int i = 0; i < bArr.length / 4; i++) {
            int i2 = i * 16;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 4;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 0;
            bArr2[i2 + 10] = 0;
            bArr2[i2 + 11] = 0;
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 0;
            bArr2[i2 + 14] = 0;
            bArr2[i2 + 15] = 0;
        }
        return bArr2;
    }

    public static byte[] addCnFor2MicN2(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length * 2) / 3];
        for (int i = 0; i < bArr.length / 24; i++) {
            int i2 = i * 16;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 24;
            bArr2[i2 + 2] = bArr[i3 + 6];
            bArr2[i2 + 3] = bArr[i3 + 7];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 0;
            bArr2[i2 + 10] = bArr[i3 + 4];
            bArr2[i2 + 11] = bArr[i3 + 5];
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 0;
            bArr2[i2 + 14] = bArr[i3 + 0];
            bArr2[i2 + 15] = bArr[i3 + 1];
        }
        return bArr2;
    }

    public static byte[] addCnFor4Mic(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length / 12; i++) {
            int i2 = i * 24;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 12;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 0;
            bArr2[i2 + 10] = bArr[i3 + 4];
            bArr2[i2 + 11] = bArr[i3 + 5];
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 0;
            bArr2[i2 + 14] = bArr[i3 + 6];
            bArr2[i2 + 15] = bArr[i3 + 7];
            bArr2[i2 + 16] = 0;
            bArr2[i2 + 17] = 0;
            bArr2[i2 + 18] = bArr[i3 + 8];
            bArr2[i2 + 19] = bArr[i3 + 9];
            bArr2[i2 + 20] = 0;
            bArr2[i2 + 21] = 0;
            bArr2[i2 + 22] = bArr[i3 + 10];
            bArr2[i2 + 23] = bArr[i3 + 11];
        }
        return bArr2;
    }

    public static byte[] parse2mic(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length / 12) * 4 * 2];
        for (int i = 0; i < bArr.length / 24; i++) {
            int i2 = i * 16;
            bArr2[i2] = 0;
            bArr2[i2 + 1] = 0;
            int i3 = i * 24;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 0;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 0;
            bArr2[i2 + 10] = 0;
            bArr2[i2 + 11] = 0;
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 0;
            bArr2[i2 + 14] = 0;
            bArr2[i2 + 15] = 0;
        }
        return bArr2;
    }

    public static byte[] adapeter2Mic(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length / 16; i++) {
            int i2 = i * 16;
            bArr2[i2] = 0;
            int i3 = i2 + 1;
            bArr2[i3] = 1;
            int i4 = i2 + 2;
            bArr2[i4] = bArr[i2 + 0];
            int i5 = i2 + 3;
            bArr2[i5] = bArr[i3];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 2;
            bArr2[i2 + 6] = bArr[i4];
            bArr2[i2 + 7] = bArr[i5];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 3;
            int i6 = i2 + 12;
            bArr2[i2 + 10] = bArr[i6];
            int i7 = i2 + 13;
            bArr2[i2 + 11] = bArr[i7];
            bArr2[i6] = 0;
            bArr2[i7] = 4;
            int i8 = i2 + 14;
            bArr2[i8] = bArr[i8];
            int i9 = i2 + 15;
            bArr2[i9] = bArr[i9];
        }
        return bArr2;
    }

    public static byte[] adapeter4Mic(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length / 8) * 6 * 2];
        for (int i = 0; i < bArr.length / 16; i++) {
            int i2 = i * 24;
            bArr2[i2 + 0] = 0;
            bArr2[i2 + 1] = 1;
            int i3 = i * 16;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 2;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 3;
            bArr2[i2 + 10] = bArr[i3 + 4];
            bArr2[i2 + 11] = bArr[i3 + 5];
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 4;
            bArr2[i2 + 14] = bArr[i3 + 6];
            bArr2[i2 + 15] = bArr[i3 + 7];
            bArr2[i2 + 16] = 0;
            bArr2[i2 + 17] = 5;
            bArr2[i2 + 18] = bArr[i3 + 12];
            bArr2[i2 + 19] = bArr[i3 + 13];
            bArr2[i2 + 20] = 0;
            bArr2[i2 + 21] = 6;
            bArr2[i2 + 22] = bArr[i3 + 14];
            bArr2[i2 + 23] = bArr[i3 + 15];
        }
        return bArr2;
    }

    public static byte[] adapeter6Mic(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length / 16; i++) {
            int i2 = i * 32;
            bArr2[i2 + 0] = 0;
            bArr2[i2 + 1] = 1;
            int i3 = i * 16;
            bArr2[i2 + 2] = bArr[i3 + 0];
            bArr2[i2 + 3] = bArr[i3 + 1];
            bArr2[i2 + 4] = 0;
            bArr2[i2 + 5] = 2;
            bArr2[i2 + 6] = bArr[i3 + 2];
            bArr2[i2 + 7] = bArr[i3 + 3];
            bArr2[i2 + 8] = 0;
            bArr2[i2 + 9] = 3;
            bArr2[i2 + 10] = bArr[i3 + 4];
            bArr2[i2 + 11] = bArr[i3 + 5];
            bArr2[i2 + 12] = 0;
            bArr2[i2 + 13] = 4;
            bArr2[i2 + 14] = bArr[i3 + 6];
            bArr2[i2 + 15] = bArr[i3 + 7];
            bArr2[i2 + 16] = 0;
            bArr2[i2 + 17] = 5;
            bArr2[i2 + 18] = bArr[i3 + 8];
            bArr2[i2 + 19] = bArr[i3 + 9];
            bArr2[i2 + 20] = 0;
            bArr2[i2 + 21] = 6;
            bArr2[i2 + 22] = bArr[i3 + 10];
            bArr2[i2 + 23] = bArr[i3 + 11];
            bArr2[i2 + 24] = 0;
            bArr2[i2 + 25] = 7;
            bArr2[i2 + 26] = bArr[i3 + 12];
            bArr2[i2 + 27] = bArr[i3 + 13];
            bArr2[i2 + 28] = 0;
            bArr2[i2 + 29] = 8;
            bArr2[i2 + 30] = bArr[i3 + 14];
            bArr2[i2 + 31] = bArr[i3 + 15];
        }
        return bArr2;
    }

    public static byte[] adapeter1C(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length / 8];
        for (int i = 0; i < bArr.length / 16; i++) {
            int i2 = i * 2;
            int i3 = i * 16;
            bArr2[i2 + 0] = bArr[i3 + 0];
            bArr2[i2 + 1] = bArr[i3 + 1];
        }
        return bArr2;
    }

    public static byte[] changer12to8(byte[] bArr) {
        int i = 0;
        while (i < 48 && (bArr[i + 1] & 15) != 1) {
            i += 4;
        }
        int length = (bArr.length - i) / 48;
        byte[] bArr2 = new byte[((length * 48) * 8) / 12];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 48;
            int i4 = i2 * 32;
            System.arraycopy(bArr, ((mic1 - 1) * 4) + i + i3, bArr2, i4, 4);
            System.arraycopy(bArr, ((mic2 - 1) * 4) + i + i3, bArr2, i4 + 4, 4);
            System.arraycopy(bArr, ((mic3 - 1) * 4) + i + i3, bArr2, i4 + 8, 4);
            System.arraycopy(bArr, ((mic4 - 1) * 4) + i + i3, bArr2, i4 + 12, 4);
            System.arraycopy(bArr, ((mic5 - 1) * 4) + i + i3, bArr2, i4 + 16, 4);
            System.arraycopy(bArr, ((mic6 - 1) * 4) + i + i3, bArr2, i4 + 20, 4);
            System.arraycopy(bArr, ((ref1 - 1) * 4) + i + i3, bArr2, i4 + 24, 4);
            System.arraycopy(bArr, ((ref2 - 1) * 4) + i + i3, bArr2, i4 + 28, 4);
            int i5 = i4 + 1;
            bArr2[i5] = (byte) (bArr2[i5] & 240);
            int i6 = i4 + 5;
            bArr2[i6] = (byte) (bArr2[i6] & 240);
            int i7 = i4 + 9;
            bArr2[i7] = (byte) (bArr2[i7] & 240);
            int i8 = i4 + 13;
            bArr2[i8] = (byte) (bArr2[i8] & 240);
            int i9 = i4 + 17;
            bArr2[i9] = (byte) (bArr2[i9] & 240);
            int i10 = i4 + 21;
            bArr2[i10] = (byte) (bArr2[i10] & 240);
            int i11 = i4 + 25;
            bArr2[i11] = (byte) (bArr2[i11] & 240);
            int i12 = i4 + 29;
            bArr2[i12] = (byte) (bArr2[i12] & 240);
        }
        return bArr2;
    }
}
