package com.pudutech.lidar.rplidars1;

import android.support.v4.media.session.PlaybackStateCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.ListNodePool;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import kotlin.UShort;

/* loaded from: classes5.dex */
public class RPLidarS1Protocol {
    static final long RPLIDAR_RESP_MEASUREMENT_QUALITY_SHIFT = 2;
    static final long RPLIDAR_VARBITSCALE_X16_SRC_BIT = 14;
    static final long RPLIDAR_VARBITSCALE_X2_DEST_VAL = 512;
    static final long RPLIDAR_VARBITSCALE_X2_SRC_BIT = 9;
    static final long RPLIDAR_VARBITSCALE_X4_SRC_BIT = 11;
    static final long RPLIDAR_VARBITSCALE_X8_SRC_BIT = 12;
    static final long RPLIDAR_VARBITSCALE_X16_DEST_VAL = 3328;
    static final long RPLIDAR_VARBITSCALE_X8_DEST_VAL = 1792;
    static final long RPLIDAR_VARBITSCALE_X4_DEST_VAL = 1280;
    private static final long[] VBS_SCALED_BASE = {RPLIDAR_VARBITSCALE_X16_DEST_VAL, RPLIDAR_VARBITSCALE_X8_DEST_VAL, RPLIDAR_VARBITSCALE_X4_DEST_VAL, 512, 0};
    private static final long[] VBS_SCALED_LVL = {4, 3, 2, 1, 0};
    private static final long[] VBS_TARGET_BASE = {16384, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, 512, 0};

    int toU16(short s) {
        return s & UShort.MAX_VALUE;
    }

    long toU32(int i) {
        return i & (-1);
    }

    /* loaded from: classes5.dex */
    public static class Capsule {
        static final int FLAG_IN_USE = 1;
        private static final int MAX_POOL_SIZE = 84;
        private static Capsule sPool;
        short[] cabins;
        private int flags;
        private Capsule next;
        int startAngleSync;
        byte syncCheckSumHigh;
        byte syncCheckSumLow;
        public static final Object sPoolSync = new Object();
        private static int sPoolSize = 0;

        public static Capsule obtain() {
            synchronized (sPoolSync) {
                if (sPool != null) {
                    Capsule capsule = sPool;
                    sPool = capsule.next;
                    capsule.next = null;
                    capsule.flags = 0;
                    sPoolSize--;
                    return capsule;
                }
                return new Capsule();
            }
        }

        public void recycle() {
            if (isInUse()) {
                return;
            }
            recycleUnchecked();
        }

        boolean isInUse() {
            return (this.flags & 1) == 1;
        }

        void recycleUnchecked() {
            this.flags = 1;
            this.syncCheckSumLow = (byte) 0;
            this.syncCheckSumHigh = (byte) 0;
            this.startAngleSync = 0;
            this.cabins = null;
            synchronized (sPoolSync) {
                if (sPoolSize < 84) {
                    this.next = sPool;
                    sPool = this;
                    sPoolSize++;
                }
            }
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
        Capsule obtain = Capsule.obtain();
        obtain.syncCheckSumLow = bArr[0];
        obtain.syncCheckSumHigh = bArr[1];
        obtain.startAngleSync = toU16(bytesToShort(cut(bArr, 2, 2)));
        obtain.cabins = bytesToShortArray(bArr, 4);
        return obtain;
    }

    /* loaded from: classes5.dex */
    static class Node {
        static final int FLAG_IN_USE = 1;
        private static final int MAX_POOL_SIZE = 84;
        private static Node sPool;
        int angle;
        long distMm;
        byte flag;
        private int flags;
        private Node next;
        byte quality;
        public static final Object sPoolSync = new Object();
        private static int sPoolSize = 0;

        Node() {
        }

        public String toString() {
            return "origin node angle_q14_u16=" + this.angle + " dist_mm_q2_u32=" + this.distMm + " quality=" + ((int) this.quality) + " flag=" + ((int) this.flag);
        }

        public static Node obtain() {
            synchronized (sPoolSync) {
                if (sPool != null) {
                    Node node = sPool;
                    sPool = node.next;
                    node.next = null;
                    node.flags = 0;
                    sPoolSize--;
                    return node;
                }
                return new Node();
            }
        }

        public void recycle() {
            if (isInUse()) {
                return;
            }
            recycleUnchecked();
        }

        boolean isInUse() {
            return (this.flags & 1) == 1;
        }

        void recycleUnchecked() {
            this.flags = 1;
            this.quality = (byte) 0;
            this.angle = 0;
            this.distMm = 0L;
            this.flag = (byte) 0;
            synchronized (sPoolSync) {
                if (sPoolSize < 84) {
                    this.next = sPool;
                    sPool = this;
                    sPoolSize++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<Node> test(byte[] bArr, byte[] bArr2) {
        ListNodePool<?> obtain = ListNodePool.obtain();
        Capsule coverBytes = coverBytes(bArr2);
        Capsule coverBytes2 = coverBytes(bArr);
        int i = (coverBytes.startAngleSync & 32767) << 2;
        int i2 = (coverBytes2.startAngleSync & 32767) << 2;
        int i3 = i - i2;
        if (i2 > i) {
            i3 += 92160;
            Pdlog.m3276v("RPLIDAR_S1", "新的一圈数据开始");
        }
        int i4 = (i3 << 8) / 40;
        int i5 = i2 << 8;
        for (int i6 = 0; i6 < coverBytes2.cabins.length; i6++) {
            int i7 = (coverBytes2.cabins[i6] & UShort.MAX_VALUE) << 2;
            int i8 = i5 >> 10;
            i5 += i4;
            int i9 = i5 % 23592960 < i4 ? 1 : 0;
            if (i8 < 0) {
                i8 += 23040;
            }
            if (i8 >= 23040) {
                i8 -= 23040;
            }
            Node obtain2 = Node.obtain();
            obtain2.flag = (byte) (i9 | ((i9 == 1 ? 0 : 1) << 1));
            obtain2.quality = (byte) (i7 == 0 ? 0 : 188);
            obtain2.angle = 65535 & ((i8 << 8) / 90);
            obtain2.distMm = i7;
            obtain.add(obtain2);
        }
        coverBytes.recycle();
        coverBytes2.recycle();
        return obtain;
    }

    /* loaded from: classes5.dex */
    class ScaleLevel {
        long u32 = 0;

        ScaleLevel() {
        }
    }

    long varBitScaleDecode(long j, ScaleLevel scaleLevel) {
        int i = 0;
        while (true) {
            long[] jArr = VBS_SCALED_BASE;
            if (i >= jArr.length) {
                return 0L;
            }
            if (((int) j) - ((int) jArr[i]) >= 0) {
                scaleLevel.u32 = VBS_SCALED_LVL[i];
                return VBS_TARGET_BASE[i] + (r2 << ((int) scaleLevel.u32));
            }
            i++;
        }
    }
}
