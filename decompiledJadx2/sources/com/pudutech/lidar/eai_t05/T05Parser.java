package com.pudutech.lidar.eai_t05;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: T05Parser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/lidar/eai_t05/T05Parser;", "", "()V", "TAG", "", "parse", "Lcom/pudutech/lidar/eai_t05/Frame;", "data", "", "Wrapper", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class T05Parser {
    private final String TAG = "T05Parser";

    public final Frame parse(byte[] data) {
        int i = 0;
        if (data == null) {
            Pdlog.m3274e(this.TAG, "data=null. return");
            return null;
        }
        Frame frame = new Frame();
        Wrapper wrapper = new Wrapper(data);
        short popTwoByte = wrapper.popTwoByte();
        if (frame.getHead_16b() != popTwoByte) {
            Pdlog.m3274e(this.TAG, "wrong head=" + UShort.m4796toStringimpl(popTwoByte) + " != " + UShort.m4796toStringimpl(frame.getHead_16b()));
            return null;
        }
        frame.m4343setDeviceType_4b7apg3OU(wrapper.pop4Bit());
        frame.m4346setFrameType_4b7apg3OU(wrapper.pop4Bit());
        frame.m4348setHeadType_4b7apg3OU(wrapper.pop4Bit());
        frame.m4349setId_4b7apg3OU(wrapper.pop4Bit());
        frame.m4351setTimestamp_32bWZ4Q5Ns(wrapper.popFourByte());
        frame.m4347setHeadFlag_4b7apg3OU(wrapper.pop4Bit());
        frame.m4341setDataFormat_4b7apg3OU(wrapper.pop4Bit());
        frame.m4344setDistanceScale_8b7apg3OU(wrapper.popOneByte());
        frame.m4350setStartAngle_16bxj2QHRw(wrapper.popTwoByte());
        frame.m4342setDataNum_16bxj2QHRw(wrapper.popTwoByte());
        frame.m4345setEndAngle_16bxj2QHRw(wrapper.popTwoByte());
        int dataNum_16b = frame.getDataNum_16b() & UShort.MAX_VALUE;
        Point[] pointArr = new Point[dataNum_16b];
        for (int i2 = 0; i2 < dataNum_16b; i2++) {
            pointArr[i2] = new Point();
        }
        frame.setPoints(pointArr);
        Pdlog.m3275i(this.TAG, "one data frame=" + frame);
        double d = 100.0d;
        double startAngle_16b = ((double) (frame.getStartAngle_16b() & UShort.MAX_VALUE)) / 100.0d;
        double d2 = 0.0d;
        double distanceScale_8b = frame.getDistanceScale_8b() & 255;
        int length = frame.getPoints().length;
        while (i < length) {
            frame.getPoints()[i].m4352setIntensity7apg3OU(wrapper.popOneByte());
            int i3 = i;
            d2 += (wrapper.popOneByte() & 255) / d;
            frame.getPoints()[i3].setAngle_degree(startAngle_16b + d2);
            frame.getPoints()[i3].setDistance_mm((wrapper.popTwoByte() & UShort.MAX_VALUE) * distanceScale_8b);
            i = i3 + 1;
            d = 100.0d;
        }
        return frame;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: T05Parser.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\rø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/eai_t05/T05Parser$Wrapper;", "", "data", "", "([B)V", "index", "", "isLow4Bit", "", "pop4Bit", "Lkotlin/UByte;", "()B", "popFourByte", "Lkotlin/UInt;", "()I", "popOneByte", "popTwoByte", "Lkotlin/UShort;", "()S", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Wrapper {
        private final byte[] data;
        private int index;
        private boolean isLow4Bit;

        public Wrapper(byte[] data) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            this.data = data;
        }

        public final byte popOneByte() {
            byte m4528constructorimpl = UByte.m4528constructorimpl(this.data[this.index]);
            this.index++;
            return m4528constructorimpl;
        }

        public final short popTwoByte() {
            short m4761constructorimpl = UShort.m4761constructorimpl((short) ((((UByte.m4528constructorimpl(this.data[this.index]) & 255) << 8) | (UByte.m4528constructorimpl(this.data[this.index + 1]) & 255)) & 65535));
            this.index += 2;
            return m4761constructorimpl;
        }

        public final int popFourByte() {
            int m4595constructorimpl = UInt.m4595constructorimpl((((UByte.m4528constructorimpl(this.data[this.index]) & 255) << 24) | ((UByte.m4528constructorimpl(this.data[this.index + 1]) & 255) << 16) | ((UByte.m4528constructorimpl(this.data[this.index + 2]) & 255) << 8) | (UByte.m4528constructorimpl(this.data[this.index + 3]) & 255)) & ((int) 4294967295L));
            this.index += 4;
            return m4595constructorimpl;
        }

        public final byte pop4Bit() {
            byte m4528constructorimpl;
            if (this.isLow4Bit) {
                m4528constructorimpl = UByte.m4528constructorimpl((byte) (this.data[this.index] & 15));
                this.index++;
            } else {
                m4528constructorimpl = UByte.m4528constructorimpl((byte) ((this.data[this.index] >>> 4) & 15));
            }
            this.isLow4Bit = !this.isLow4Bit;
            return m4528constructorimpl;
        }
    }
}
