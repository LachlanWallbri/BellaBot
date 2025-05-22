package com.pudutech.lidar.rplidar_s1;

import android.support.v4.media.session.PlaybackStateCompat;
import com.pudutech.base.Pdlog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class RPLIDAR_S1_PROTOCOL {
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
        short[] cabins_u16;
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

    short[] bytesToShortArray(byte[] bArr, int i) {
        if (bArr.length % 2 != 0) {
            throw new InvalidParameterException("长度必须是偶数");
        }
        short[] sArr = new short[(bArr.length - i) / 2];
        ByteBuffer.wrap(bArr, i, bArr.length - i).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArr);
        return sArr;
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
        capsule.cabins_u16 = bytesToShortArray(bArr, 4);
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
    public ArrayList<Node> test(byte[] bArr, byte[] bArr2) {
        ArrayList<Node> arrayList = new ArrayList<>();
        Capsule coverBytes = coverBytes(bArr2);
        Capsule coverBytes2 = coverBytes(bArr);
        int i = (coverBytes.startAngle_sync_u16 & 32767) << 2;
        int i2 = (coverBytes2.startAngle_sync_u16 & 32767) << 2;
        int i3 = i - i2;
        if (i2 > i) {
            i3 += 92160;
            Pdlog.m3276v("RPLIDAR_S1", "新的一圈数据开始");
        }
        int i4 = (i3 << 8) / 40;
        int i5 = i2 << 8;
        for (int i6 = 0; i6 < coverBytes2.cabins_u16.length; i6++) {
            int i7 = (coverBytes2.cabins_u16[i6] & UShort.MAX_VALUE) << 2;
            int i8 = i5 >> 10;
            i5 += i4;
            int i9 = i5 % 23592960 < i4 ? 1 : 0;
            if (i8 < 0) {
                i8 += 23040;
            }
            if (i8 >= 23040) {
                i8 -= 23040;
            }
            Node node = new Node();
            node.flag = (byte) (i9 | ((i9 == 1 ? 0 : 1) << 1));
            node.quality = (byte) (i7 == 0 ? 0 : 188);
            node.angle_q14_u16 = 65535 & ((i8 << 8) / 90);
            node.dist_mm_q2_u32 = i7;
            arrayList.add(node);
        }
        return arrayList;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    class ScaleLevel {
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
