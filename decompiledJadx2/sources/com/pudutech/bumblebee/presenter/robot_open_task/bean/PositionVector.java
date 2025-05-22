package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* compiled from: PositionInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/PositionVector;", "", "x", "", "y", CompressorStreamFactory.f8930Z, "floor", "", "(DDDLjava/lang/String;)V", "getFloor", "()Ljava/lang/String;", "getX", "()D", "getY", "getZ", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class PositionVector {
    private final String floor;
    private final double x;
    private final double y;
    private final double z;

    /* renamed from: component1, reason: from getter */
    public final double getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final double getY() {
        return this.y;
    }

    /* renamed from: component3, reason: from getter */
    public final double getZ() {
        return this.z;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFloor() {
        return this.floor;
    }

    public final PositionVector copy(double x, double y, double z, String floor) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        return new PositionVector(x, y, z, floor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionVector)) {
            return false;
        }
        PositionVector positionVector = (PositionVector) other;
        return Double.compare(this.x, positionVector.x) == 0 && Double.compare(this.y, positionVector.y) == 0 && Double.compare(this.z, positionVector.z) == 0 && Intrinsics.areEqual(this.floor, positionVector.floor);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.x);
        long doubleToLongBits2 = Double.doubleToLongBits(this.y);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        long doubleToLongBits3 = Double.doubleToLongBits(this.z);
        int i2 = (i + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3))) * 31;
        String str = this.floor;
        return i2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "PositionVector(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", floor=" + this.floor + ")";
    }

    public PositionVector(double d, double d2, double d3, String floor) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.floor = floor;
    }

    public final double getX() {
        return this.x;
    }

    public final double getY() {
        return this.y;
    }

    public final double getZ() {
        return this.z;
    }

    public final String getFloor() {
        return this.floor;
    }
}
