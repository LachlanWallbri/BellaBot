package com.pudutech.lidar.rplidar_a3;

import android.support.v4.media.session.PlaybackStateCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class RPLIDAR_A3_PROTOCOL {
    static final long RPLIDAR_RESP_MEASUREMENT_QUALITY_SHIFT = 2;
    static final long RPLIDAR_VARBITSCALE_X16_DEST_VAL = 3328;
    static final long RPLIDAR_VARBITSCALE_X16_SRC_BIT = 14;
    static final long RPLIDAR_VARBITSCALE_X2_DEST_VAL = 512;
    static final long RPLIDAR_VARBITSCALE_X2_SRC_BIT = 9;
    static final long RPLIDAR_VARBITSCALE_X4_DEST_VAL = 1280;
    static final long RPLIDAR_VARBITSCALE_X4_SRC_BIT = 11;
    static final long RPLIDAR_VARBITSCALE_X8_DEST_VAL = 1792;
    static final long RPLIDAR_VARBITSCALE_X8_SRC_BIT = 12;

    int toU16(short s) {
        return s & UShort.MAX_VALUE;
    }

    long toU32(int i) {
        return i & (-1);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    public class Capsule {
        long[] cabins_u32;
        int startAngle_sync_u16;
        byte sync_checkSum_1;
        byte sync_checkSum_2;

        public Capsule() {
        }
    }

    int bytesToInt(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    short bytesToShort(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    long[] bytesToU32Array(byte[] bArr) {
        if (bArr.length % 4 != 0) {
            return null;
        }
        long[] jArr = new long[bArr.length / 4];
        for (int i = 0; i < bArr.length / 4; i++) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i * 4, bArr2, 0, 4);
            jArr[i] = toU32(bytesToInt(bArr2));
        }
        return jArr;
    }

    byte[] cut(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    Capsule coverBytes(byte[] bArr) {
        Capsule capsule = new Capsule();
        capsule.sync_checkSum_1 = bArr[0];
        capsule.sync_checkSum_2 = bArr[1];
        capsule.startAngle_sync_u16 = toU16(bytesToShort(cut(bArr, 2, 2)));
        capsule.cabins_u32 = bytesToU32Array(cut(bArr, 4, bArr.length - 4));
        return capsule;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    class Node {
        int angle_q14_u16;
        long dist_mm_q2_u32;
        byte flag;
        byte quality;

        Node() {
        }

        public String toString() {
            return "origin node angle_q14_u16=" + this.angle_q14_u16 + " dist_mm_q2_u32=" + this.dist_mm_q2_u32 + " quality=" + ((int) this.quality) + " flag=" + ((int) this.flag);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ArrayList<Node> test(byte[] bArr, byte[] bArr2) {
        long j;
        ArrayList<Node> arrayList;
        int i;
        int i2;
        ScaleLevel scaleLevel;
        char c;
        int i3;
        int i4;
        ArrayList<Node> arrayList2 = new ArrayList<>();
        Capsule coverBytes = coverBytes(bArr2);
        Capsule coverBytes2 = coverBytes(bArr);
        int i5 = (coverBytes.startAngle_sync_u16 & 32767) << 2;
        int i6 = (coverBytes2.startAngle_sync_u16 & 32767) << 2;
        int i7 = i5 - i6;
        if (i6 > i5) {
            i7 += 92160;
        }
        int i8 = 3;
        int i9 = (i7 << 3) / 3;
        int i10 = i6 << 8;
        int i11 = 0;
        while (i11 < coverBytes2.cabins_u32.length) {
            int[] iArr = new int[i8];
            int[] iArr2 = new int[i8];
            int[] iArr3 = new int[i8];
            long j2 = coverBytes2.cabins_u32[i11];
            int i12 = (int) (j2 & 4095);
            int i13 = i11;
            int i14 = ((int) (j2 << 10)) >> 22;
            int i15 = ((int) j2) >> 22;
            if (i13 == coverBytes2.cabins_u32.length - 1) {
                j = coverBytes.cabins_u32[0];
            } else {
                j = coverBytes2.cabins_u32[i13 + 1];
            }
            int i16 = (int) (j & 4095);
            ScaleLevel scaleLevel2 = new ScaleLevel();
            ScaleLevel scaleLevel3 = new ScaleLevel();
            Capsule capsule = coverBytes;
            Capsule capsule2 = coverBytes2;
            int varBitScaleDecode = (int) varBitScaleDecode(toU32(i12), scaleLevel2);
            int i17 = i10;
            int varBitScaleDecode2 = (int) varBitScaleDecode(toU32(i16), scaleLevel3);
            if (varBitScaleDecode != 0 || varBitScaleDecode2 == 0) {
                arrayList = arrayList2;
                i = i17;
                i2 = varBitScaleDecode;
            } else {
                arrayList = arrayList2;
                i = i17;
                scaleLevel2.u32 = scaleLevel3.u32;
                i2 = varBitScaleDecode2;
            }
            iArr[0] = varBitScaleDecode << 2;
            if (i14 == -512 || i14 == 511) {
                scaleLevel = scaleLevel3;
                iArr[1] = 0;
            } else {
                scaleLevel = scaleLevel3;
                iArr[1] = ((i14 << ((int) scaleLevel2.u32)) + i2) << 2;
            }
            if (i15 == -512) {
                c = 2;
                i3 = 0;
            } else if (i15 == 511) {
                i3 = 0;
                c = 2;
            } else {
                iArr[2] = ((i15 << ((int) scaleLevel.u32)) + varBitScaleDecode2) << 2;
                i10 = i;
                i4 = 0;
                while (i4 < 3) {
                    int i18 = i10 + i9;
                    iArr3[i4] = i18 % 23592960 < i9 ? 1 : 0;
                    int i19 = 8578;
                    if (iArr[i4] >= 200) {
                        int i20 = 98361 / iArr[i4];
                        i19 = (9150 - (i20 << 6)) - (((i20 * i20) * i20) / 98304);
                    }
                    iArr2[i4] = (i10 - ((int) ((i19 * 180) / 3.14159265d))) >> 10;
                    if (iArr2[i4] < 0) {
                        iArr2[i4] = iArr2[i4] + 23040;
                    }
                    if (iArr2[i4] >= 23040) {
                        iArr2[i4] = iArr2[i4] - 23040;
                    }
                    Node node = new Node();
                    node.flag = (byte) (iArr3[i4] | ((iArr3[i4] == 1 ? 0 : 1) << 1));
                    node.quality = (byte) (iArr[i4] == 0 ? 0 : 188);
                    node.angle_q14_u16 = ((iArr2[i4] << 8) / 90) & 65535;
                    node.dist_mm_q2_u32 = iArr[i4];
                    arrayList.add(node);
                    i4++;
                    i10 = i18;
                }
                i11 = i13 + 1;
                arrayList2 = arrayList;
                coverBytes = capsule;
                coverBytes2 = capsule2;
                i8 = 3;
            }
            iArr[c] = i3;
            i10 = i;
            i4 = 0;
            while (i4 < 3) {
            }
            i11 = i13 + 1;
            arrayList2 = arrayList;
            coverBytes = capsule;
            coverBytes2 = capsule2;
            i8 = 3;
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    public class ScaleLevel {
        long u32 = 0;

        ScaleLevel() {
        }
    }

    long varBitScaleDecode(long j, ScaleLevel scaleLevel) {
        long[] jArr = {RPLIDAR_VARBITSCALE_X16_DEST_VAL, RPLIDAR_VARBITSCALE_X8_DEST_VAL, RPLIDAR_VARBITSCALE_X4_DEST_VAL, 512, 0};
        long[] jArr2 = {4, 3, 2, 1, 0};
        long[] jArr3 = {16384, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 512, 0};
        for (int i = 0; i < 5; i++) {
            if (((int) j) - ((int) jArr[i]) >= 0) {
                scaleLevel.u32 = jArr2[i];
                return jArr3[i] + (r5 << ((int) scaleLevel.u32));
            }
        }
        return 0L;
    }
}
