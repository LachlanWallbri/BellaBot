package com.pudutech.lidar.rplidara2m7;

import android.support.v4.media.session.PlaybackStateCompat;
import com.pudutech.lidar.ListNodePool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import kotlin.UShort;

/* loaded from: classes5.dex */
public class RPLidarA2M7Protocol {
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

    /* loaded from: classes5.dex */
    public class Capsule {
        long[] cabinsU32;
        int startAngleSyncU16;
        byte syncCheckSum1;
        byte syncCheckSum2;

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
            return new long[0];
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
        capsule.syncCheckSum1 = bArr[0];
        capsule.syncCheckSum2 = bArr[1];
        capsule.startAngleSyncU16 = toU16(bytesToShort(cut(bArr, 2, 2)));
        capsule.cabinsU32 = bytesToU32Array(cut(bArr, 4, bArr.length - 4));
        return capsule;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class Node {
        int angleQ14U16;
        long distMMQ2U32;
        byte flag;
        byte quality;

        Node() {
        }

        public String toString() {
            return "origin node angle_q14_u16=" + this.angleQ14U16 + " dist_mm_q2_u32=" + this.distMMQ2U32 + " quality=" + ((int) this.quality) + " flag=" + ((int) this.flag);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<Node> test(byte[] bArr, byte[] bArr2) {
        Capsule coverBytes = coverBytes(bArr2);
        Capsule coverBytes2 = coverBytes(bArr);
        int i = (coverBytes.startAngleSyncU16 & 32767) << 2;
        int i2 = (coverBytes2.startAngleSyncU16 & 32767) << 2;
        int i3 = i - i2;
        if (i2 > i) {
            i3 += 92160;
        }
        return parseNode(coverBytes, coverBytes2, (i3 << 3) / 3, i2 << 8);
    }

    private ArrayList<Node> parseNode(Capsule capsule, Capsule capsule2, int i, int i2) {
        ListNodePool<?> obtain = ListNodePool.obtain();
        for (int i3 = 0; i3 < capsule2.cabinsU32.length; i3++) {
            i2 = getCurrentAngleRaw(i, i2, obtain, getQ2Array(capsule, capsule2, i3));
        }
        return obtain;
    }

    private int getCurrentAngleRaw(int i, int i2, ArrayList<Node> arrayList, int[] iArr) {
        int[] iArr2 = new int[3];
        int[] iArr3 = new int[3];
        int i3 = i2;
        int i4 = 0;
        while (i4 < 3) {
            int i5 = i3 + i;
            iArr3[i4] = i5 % 23592960 < i ? 1 : 0;
            int i6 = 8578;
            if (iArr[i4] >= 200) {
                int i7 = 98361 / iArr[i4];
                i6 = (9150 - (i7 << 6)) - (((i7 * i7) * i7) / 98304);
            }
            iArr2[i4] = (i3 - ((int) ((i6 * 180) / 3.14159265d))) >> 10;
            if (iArr2[i4] < 0) {
                iArr2[i4] = iArr2[i4] + 23040;
            }
            if (iArr2[i4] >= 23040) {
                iArr2[i4] = iArr2[i4] - 23040;
            }
            Node node = new Node();
            node.flag = (byte) (((iArr3[i4] == 1 ? 0 : 1) << 1) | iArr3[i4]);
            node.quality = (byte) (iArr2[i4] == 0 ? 0 : 188);
            node.angleQ14U16 = ((iArr2[i4] << 8) / 90) & 65535;
            node.distMMQ2U32 = iArr[i4];
            arrayList.add(node);
            i4++;
            i3 = i5;
        }
        return i3;
    }

    private int[] getQ2Array(Capsule capsule, Capsule capsule2, int i) {
        long j;
        int i2;
        long j2 = capsule2.cabinsU32[i];
        int i3 = (int) (j2 & 4095);
        int i4 = ((int) (j2 << 10)) >> 22;
        int i5 = ((int) j2) >> 22;
        if (i == capsule2.cabinsU32.length - 1) {
            j = capsule.cabinsU32[0];
        } else {
            j = capsule2.cabinsU32[i + 1];
        }
        int i6 = (int) (j & 4095);
        ScaleLevel scaleLevel = new ScaleLevel();
        ScaleLevel scaleLevel2 = new ScaleLevel();
        int varBitScaleDecode = (int) varBitScaleDecode(toU32(i3), scaleLevel);
        int varBitScaleDecode2 = (int) varBitScaleDecode(toU32(i6), scaleLevel2);
        if (varBitScaleDecode != 0 || varBitScaleDecode2 == 0) {
            i2 = varBitScaleDecode;
        } else {
            scaleLevel.u32 = scaleLevel2.u32;
            i2 = varBitScaleDecode2;
        }
        int[] iArr = new int[3];
        iArr[0] = varBitScaleDecode << 2;
        if (i4 == -512 || i4 == 511) {
            iArr[1] = 0;
        } else {
            iArr[1] = ((i4 << ((int) scaleLevel.u32)) + i2) << 2;
        }
        if (i5 == -512 || i5 == 511) {
            iArr[2] = 0;
        } else {
            iArr[2] = ((i5 << ((int) scaleLevel2.u32)) + varBitScaleDecode2) << 2;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public class ScaleLevel {
        long u32 = 0;

        ScaleLevel() {
        }
    }

    long varBitScaleDecode(long j, ScaleLevel scaleLevel) {
        long[] jArr = {RPLIDAR_VARBITSCALE_X16_DEST_VAL, RPLIDAR_VARBITSCALE_X8_DEST_VAL, RPLIDAR_VARBITSCALE_X4_DEST_VAL, 512, 0};
        long[] jArr2 = {4, 3, 2, 1, 0};
        long[] jArr3 = {16384, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 512, 0};
        for (int i = 0; i < jArr.length; i++) {
            if (((int) j) - ((int) jArr[i]) >= 0) {
                scaleLevel.u32 = jArr2[i];
                return jArr3[i] + (r4 << ((int) scaleLevel.u32));
            }
        }
        return 0L;
    }
}
