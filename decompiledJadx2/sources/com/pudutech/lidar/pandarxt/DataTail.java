package com.pudutech.lidar.pandarxt;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseConfigOfPandarXT.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0018\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u0019\u001a\u00020\u0005HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u001b\u001a\u00020\tHÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0011\u0010\u001c\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000fJE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010#\u001a\u00020$J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020(HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\n\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0014\u0010\u000fR\u0016\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, m3961d2 = {"Lcom/pudutech/lidar/pandarxt/DataTail;", "", "returnMode", "Lkotlin/UByte;", "motorSpeed", "Lkotlin/UShort;", "dataTime", "Lcom/pudutech/lidar/pandarxt/DataTime;", "timeStamp", "Lkotlin/UInt;", "factoryInformation", "(BSLcom/pudutech/lidar/pandarxt/DataTime;IBLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDataTime", "()Lcom/pudutech/lidar/pandarxt/DataTime;", "getFactoryInformation", "()B", "B", "getMotorSpeed", "()S", ExifInterface.LATITUDE_SOUTH, "getReturnMode", "getTimeStamp", "()I", "I", "component1", "component2", "component3", "component4", "component5", "copy", "copy-smCNPyQ", "(BSLcom/pudutech/lidar/pandarxt/DataTime;IB)Lcom/pudutech/lidar/pandarxt/DataTail;", "equals", "", "other", "getAbsoluteTime", "", "hashCode", "", "toString", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class DataTail {
    private final DataTime dataTime;
    private final byte factoryInformation;
    private final short motorSpeed;
    private final byte returnMode;
    private final int timeStamp;

    /* renamed from: copy-smCNPyQ$default, reason: not valid java name */
    public static /* synthetic */ DataTail m4385copysmCNPyQ$default(DataTail dataTail, byte b, short s, DataTime dataTime, int i, byte b2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            b = dataTail.returnMode;
        }
        if ((i2 & 2) != 0) {
            s = dataTail.motorSpeed;
        }
        short s2 = s;
        if ((i2 & 4) != 0) {
            dataTime = dataTail.dataTime;
        }
        DataTime dataTime2 = dataTime;
        if ((i2 & 8) != 0) {
            i = dataTail.timeStamp;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            b2 = dataTail.factoryInformation;
        }
        return dataTail.m4386copysmCNPyQ(b, s2, dataTime2, i3, b2);
    }

    /* renamed from: component1, reason: from getter */
    public final byte getReturnMode() {
        return this.returnMode;
    }

    /* renamed from: component2, reason: from getter */
    public final short getMotorSpeed() {
        return this.motorSpeed;
    }

    /* renamed from: component3, reason: from getter */
    public final DataTime getDataTime() {
        return this.dataTime;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTimeStamp() {
        return this.timeStamp;
    }

    /* renamed from: component5, reason: from getter */
    public final byte getFactoryInformation() {
        return this.factoryInformation;
    }

    /* renamed from: copy-smCNPyQ, reason: not valid java name */
    public final DataTail m4386copysmCNPyQ(byte returnMode, short motorSpeed, DataTime dataTime, int timeStamp, byte factoryInformation) {
        Intrinsics.checkParameterIsNotNull(dataTime, "dataTime");
        return new DataTail(returnMode, motorSpeed, dataTime, timeStamp, factoryInformation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataTail)) {
            return false;
        }
        DataTail dataTail = (DataTail) other;
        return this.returnMode == dataTail.returnMode && this.motorSpeed == dataTail.motorSpeed && Intrinsics.areEqual(this.dataTime, dataTail.dataTime) && this.timeStamp == dataTail.timeStamp && this.factoryInformation == dataTail.factoryInformation;
    }

    public int hashCode() {
        int i = ((this.returnMode * Ascii.f1926US) + this.motorSpeed) * 31;
        DataTime dataTime = this.dataTime;
        return ((((i + (dataTime != null ? dataTime.hashCode() : 0)) * 31) + this.timeStamp) * 31) + this.factoryInformation;
    }

    public String toString() {
        return "DataTail(returnMode=" + UByte.m4563toStringimpl(this.returnMode) + ", motorSpeed=" + UShort.m4796toStringimpl(this.motorSpeed) + ", dataTime=" + this.dataTime + ", timeStamp=" + UInt.m4632toStringimpl(this.timeStamp) + ", factoryInformation=" + UByte.m4563toStringimpl(this.factoryInformation) + ")";
    }

    private DataTail(byte b, short s, DataTime dataTime, int i, byte b2) {
        this.returnMode = b;
        this.motorSpeed = s;
        this.dataTime = dataTime;
        this.timeStamp = i;
        this.factoryInformation = b2;
    }

    public /* synthetic */ DataTail(byte b, short s, DataTime dataTime, int i, byte b2, DefaultConstructorMarker defaultConstructorMarker) {
        this(b, s, dataTime, i, b2);
    }

    public final byte getReturnMode() {
        return this.returnMode;
    }

    public final short getMotorSpeed() {
        return this.motorSpeed;
    }

    public final DataTime getDataTime() {
        return this.dataTime;
    }

    public final int getTimeStamp() {
        return this.timeStamp;
    }

    public final byte getFactoryInformation() {
        return this.factoryInformation;
    }

    public final double getAbsoluteTime() {
        return this.dataTime.getSecondTime() + (UnsignedKt.uintToDouble(this.timeStamp) / 1000000.0d);
    }
}
