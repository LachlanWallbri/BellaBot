package com.pudutech.lidar.eai_t05;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Frame.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010:\u001a\u00020;H\u0016R\u001f\u0010\u0003\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001f\u0010\n\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0011\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001f\u0010\u0014\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001f\u0010\u0017\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001f\u0010\u001a\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001f\u0010\u001d\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001f\u0010 \u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u0019\u0010#\u001a\u00020\u000bX\u0086Dø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b$\u0010\rR\u001f\u0010%\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR\"\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0086.¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001f\u00100\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR\u001f\u00103\u001a\u000204X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, m3961d2 = {"Lcom/pudutech/lidar/eai_t05/Frame;", "", "()V", "dataFormat_4b", "Lkotlin/UByte;", "getDataFormat_4b", "()B", "setDataFormat_4b-7apg3OU", "(B)V", "B", "dataNum_16b", "Lkotlin/UShort;", "getDataNum_16b", "()S", "setDataNum_16b-xj2QHRw", "(S)V", ExifInterface.LATITUDE_SOUTH, "deviceType_4b", "getDeviceType_4b", "setDeviceType_4b-7apg3OU", "distanceScale_8b", "getDistanceScale_8b", "setDistanceScale_8b-7apg3OU", "endAngle_16b", "getEndAngle_16b", "setEndAngle_16b-xj2QHRw", "frameType_4b", "getFrameType_4b", "setFrameType_4b-7apg3OU", "headFlag_4b", "getHeadFlag_4b", "setHeadFlag_4b-7apg3OU", "headType_4b", "getHeadType_4b", "setHeadType_4b-7apg3OU", "head_16b", "getHead_16b", "id_4b", "getId_4b", "setId_4b-7apg3OU", "points", "", "Lcom/pudutech/lidar/eai_t05/Point;", "getPoints", "()[Lcom/pudutech/lidar/eai_t05/Point;", "setPoints", "([Lcom/pudutech/lidar/eai_t05/Point;)V", "[Lcom/pudutech/lidar/eai_t05/Point;", "startAngle_16b", "getStartAngle_16b", "setStartAngle_16b-xj2QHRw", "timestamp_32b", "Lkotlin/UInt;", "getTimestamp_32b", "()I", "setTimestamp_32b-WZ4Q5Ns", "(I)V", "I", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class Frame {
    private byte dataFormat_4b;
    private short dataNum_16b;
    private byte deviceType_4b;
    private byte distanceScale_8b;
    private short endAngle_16b;
    private byte frameType_4b;
    private byte headFlag_4b;
    private byte headType_4b;
    private final short head_16b = -18;
    private byte id_4b;
    public Point[] points;
    private short startAngle_16b;
    private int timestamp_32b;

    public final short getHead_16b() {
        return this.head_16b;
    }

    public final byte getDeviceType_4b() {
        return this.deviceType_4b;
    }

    /* renamed from: setDeviceType_4b-7apg3OU, reason: not valid java name */
    public final void m4343setDeviceType_4b7apg3OU(byte b) {
        this.deviceType_4b = b;
    }

    public final byte getFrameType_4b() {
        return this.frameType_4b;
    }

    /* renamed from: setFrameType_4b-7apg3OU, reason: not valid java name */
    public final void m4346setFrameType_4b7apg3OU(byte b) {
        this.frameType_4b = b;
    }

    public final byte getHeadType_4b() {
        return this.headType_4b;
    }

    /* renamed from: setHeadType_4b-7apg3OU, reason: not valid java name */
    public final void m4348setHeadType_4b7apg3OU(byte b) {
        this.headType_4b = b;
    }

    public final byte getId_4b() {
        return this.id_4b;
    }

    /* renamed from: setId_4b-7apg3OU, reason: not valid java name */
    public final void m4349setId_4b7apg3OU(byte b) {
        this.id_4b = b;
    }

    public final int getTimestamp_32b() {
        return this.timestamp_32b;
    }

    /* renamed from: setTimestamp_32b-WZ4Q5Ns, reason: not valid java name */
    public final void m4351setTimestamp_32bWZ4Q5Ns(int i) {
        this.timestamp_32b = i;
    }

    public final byte getHeadFlag_4b() {
        return this.headFlag_4b;
    }

    /* renamed from: setHeadFlag_4b-7apg3OU, reason: not valid java name */
    public final void m4347setHeadFlag_4b7apg3OU(byte b) {
        this.headFlag_4b = b;
    }

    public final byte getDataFormat_4b() {
        return this.dataFormat_4b;
    }

    /* renamed from: setDataFormat_4b-7apg3OU, reason: not valid java name */
    public final void m4341setDataFormat_4b7apg3OU(byte b) {
        this.dataFormat_4b = b;
    }

    public final byte getDistanceScale_8b() {
        return this.distanceScale_8b;
    }

    /* renamed from: setDistanceScale_8b-7apg3OU, reason: not valid java name */
    public final void m4344setDistanceScale_8b7apg3OU(byte b) {
        this.distanceScale_8b = b;
    }

    public final short getStartAngle_16b() {
        return this.startAngle_16b;
    }

    /* renamed from: setStartAngle_16b-xj2QHRw, reason: not valid java name */
    public final void m4350setStartAngle_16bxj2QHRw(short s) {
        this.startAngle_16b = s;
    }

    public final short getDataNum_16b() {
        return this.dataNum_16b;
    }

    /* renamed from: setDataNum_16b-xj2QHRw, reason: not valid java name */
    public final void m4342setDataNum_16bxj2QHRw(short s) {
        this.dataNum_16b = s;
    }

    public final short getEndAngle_16b() {
        return this.endAngle_16b;
    }

    /* renamed from: setEndAngle_16b-xj2QHRw, reason: not valid java name */
    public final void m4345setEndAngle_16bxj2QHRw(short s) {
        this.endAngle_16b = s;
    }

    public final Point[] getPoints() {
        Point[] pointArr = this.points;
        if (pointArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("points");
        }
        return pointArr;
    }

    public final void setPoints(Point[] pointArr) {
        Intrinsics.checkParameterIsNotNull(pointArr, "<set-?>");
        this.points = pointArr;
    }

    public String toString() {
        return "\n            timestamp=" + UInt.m4632toStringimpl(this.timestamp_32b) + "\n            id=" + UByte.m4563toStringimpl(this.id_4b) + "\n            headFlag_4b=" + UByte.m4563toStringimpl(this.headFlag_4b) + "\n            distanceScale=" + UByte.m4563toStringimpl(this.distanceScale_8b) + "\n            dataNum=" + UShort.m4796toStringimpl(this.dataNum_16b) + "\n            startAngle=" + UShort.m4796toStringimpl(this.startAngle_16b) + "\n            endAngle=" + UShort.m4796toStringimpl(this.endAngle_16b) + "\n            ";
    }
}
