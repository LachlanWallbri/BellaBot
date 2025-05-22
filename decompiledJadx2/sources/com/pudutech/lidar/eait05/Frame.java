package com.pudutech.lidar.eait05;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Frame.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010:\u001a\u00020;H\u0016R\u001f\u0010\u0003\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001f\u0010\n\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0011\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001f\u0010\u0014\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\bR\u001f\u0010\u0017\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001f\u0010\u001a\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u0019\u0010\u001d\u001a\u00020\u000bX\u0086Dø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u001e\u0010\rR\u001f\u0010\u001f\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\bR\u001f\u0010\"\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001f\u0010%\u001a\u00020\u0004X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR\"\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0086.¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001f\u00100\u001a\u00020\u000bX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b1\u0010\r\"\u0004\b2\u0010\u000fR\u001f\u00103\u001a\u000204X\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, m3961d2 = {"Lcom/pudutech/lidar/eait05/Frame;", "", "()V", "dataFormat", "Lkotlin/UByte;", "getDataFormat", "()B", "setDataFormat-7apg3OU", "(B)V", "B", "dataNum", "Lkotlin/UShort;", "getDataNum", "()S", "setDataNum-xj2QHRw", "(S)V", ExifInterface.LATITUDE_SOUTH, "deviceType", "getDeviceType", "setDeviceType-7apg3OU", "distanceScale", "getDistanceScale", "setDistanceScale-7apg3OU", "endAngle", "getEndAngle", "setEndAngle-xj2QHRw", "frameType", "getFrameType", "setFrameType-7apg3OU", "head", "getHead", "headFlag", "getHeadFlag", "setHeadFlag-7apg3OU", "headType", "getHeadType", "setHeadType-7apg3OU", "id", "getId", "setId-7apg3OU", "points", "", "Lcom/pudutech/lidar/eait05/Point;", "getPoints", "()[Lcom/pudutech/lidar/eait05/Point;", "setPoints", "([Lcom/pudutech/lidar/eait05/Point;)V", "[Lcom/pudutech/lidar/eait05/Point;", "startAngle", "getStartAngle", "setStartAngle-xj2QHRw", "timestamp", "Lkotlin/UInt;", "getTimestamp", "()I", "setTimestamp-WZ4Q5Ns", "(I)V", "I", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Frame {
    private byte dataFormat;
    private short dataNum;
    private byte deviceType;
    private byte distanceScale;
    private short endAngle;
    private byte frameType;
    private final short head = -18;
    private byte headFlag;
    private byte headType;
    private byte id;
    public Point[] points;
    private short startAngle;
    private int timestamp;

    public final short getHead() {
        return this.head;
    }

    public final byte getDeviceType() {
        return this.deviceType;
    }

    /* renamed from: setDeviceType-7apg3OU, reason: not valid java name */
    public final void m4355setDeviceType7apg3OU(byte b) {
        this.deviceType = b;
    }

    public final byte getFrameType() {
        return this.frameType;
    }

    /* renamed from: setFrameType-7apg3OU, reason: not valid java name */
    public final void m4358setFrameType7apg3OU(byte b) {
        this.frameType = b;
    }

    public final byte getHeadType() {
        return this.headType;
    }

    /* renamed from: setHeadType-7apg3OU, reason: not valid java name */
    public final void m4360setHeadType7apg3OU(byte b) {
        this.headType = b;
    }

    public final byte getId() {
        return this.id;
    }

    /* renamed from: setId-7apg3OU, reason: not valid java name */
    public final void m4361setId7apg3OU(byte b) {
        this.id = b;
    }

    public final int getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: setTimestamp-WZ4Q5Ns, reason: not valid java name */
    public final void m4363setTimestampWZ4Q5Ns(int i) {
        this.timestamp = i;
    }

    public final byte getHeadFlag() {
        return this.headFlag;
    }

    /* renamed from: setHeadFlag-7apg3OU, reason: not valid java name */
    public final void m4359setHeadFlag7apg3OU(byte b) {
        this.headFlag = b;
    }

    public final byte getDataFormat() {
        return this.dataFormat;
    }

    /* renamed from: setDataFormat-7apg3OU, reason: not valid java name */
    public final void m4353setDataFormat7apg3OU(byte b) {
        this.dataFormat = b;
    }

    public final byte getDistanceScale() {
        return this.distanceScale;
    }

    /* renamed from: setDistanceScale-7apg3OU, reason: not valid java name */
    public final void m4356setDistanceScale7apg3OU(byte b) {
        this.distanceScale = b;
    }

    public final short getStartAngle() {
        return this.startAngle;
    }

    /* renamed from: setStartAngle-xj2QHRw, reason: not valid java name */
    public final void m4362setStartAnglexj2QHRw(short s) {
        this.startAngle = s;
    }

    public final short getDataNum() {
        return this.dataNum;
    }

    /* renamed from: setDataNum-xj2QHRw, reason: not valid java name */
    public final void m4354setDataNumxj2QHRw(short s) {
        this.dataNum = s;
    }

    public final short getEndAngle() {
        return this.endAngle;
    }

    /* renamed from: setEndAngle-xj2QHRw, reason: not valid java name */
    public final void m4357setEndAnglexj2QHRw(short s) {
        this.endAngle = s;
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
        return "\n            timestamp=" + UInt.m4632toStringimpl(this.timestamp) + "\n            id=" + UByte.m4563toStringimpl(this.id) + "\n            headFlag_4b=" + UByte.m4563toStringimpl(this.headFlag) + "\n            distanceScale=" + UByte.m4563toStringimpl(this.distanceScale) + "\n            dataNum=" + UShort.m4796toStringimpl(this.dataNum) + "\n            startAngle=" + UShort.m4796toStringimpl(this.startAngle) + "\n            endAngle=" + UShort.m4796toStringimpl(this.endAngle) + "\n            ";
    }
}
