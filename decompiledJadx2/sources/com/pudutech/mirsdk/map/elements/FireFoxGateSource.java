package com.pudutech.mirsdk.map.elements;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FireFoxGateSource.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00063"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/FireFoxGateSource;", "", "id", "", "up_stop_distance", "", "down_stop_distance", "up_slow_distance", "down_slow_distance", "up_speed", "down_speed", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "(Ljava/lang/String;DDDDDDLcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "getDown_slow_distance", "()D", "setDown_slow_distance", "(D)V", "getDown_speed", "setDown_speed", "getDown_stop_distance", "setDown_stop_distance", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "getUp_slow_distance", "setUp_slow_distance", "getUp_speed", "setUp_speed", "getUp_stop_distance", "setUp_stop_distance", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FireFoxGateSource {
    private double down_slow_distance;
    private double down_speed;
    private double down_stop_distance;
    private String id;
    private Vector3d pose;
    private double up_slow_distance;
    private double up_speed;
    private double up_stop_distance;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final double getUp_stop_distance() {
        return this.up_stop_distance;
    }

    /* renamed from: component3, reason: from getter */
    public final double getDown_stop_distance() {
        return this.down_stop_distance;
    }

    /* renamed from: component4, reason: from getter */
    public final double getUp_slow_distance() {
        return this.up_slow_distance;
    }

    /* renamed from: component5, reason: from getter */
    public final double getDown_slow_distance() {
        return this.down_slow_distance;
    }

    /* renamed from: component6, reason: from getter */
    public final double getUp_speed() {
        return this.up_speed;
    }

    /* renamed from: component7, reason: from getter */
    public final double getDown_speed() {
        return this.down_speed;
    }

    /* renamed from: component8, reason: from getter */
    public final Vector3d getPose() {
        return this.pose;
    }

    public final FireFoxGateSource copy(String id, double up_stop_distance, double down_stop_distance, double up_slow_distance, double down_slow_distance, double up_speed, double down_speed, Vector3d pose) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        return new FireFoxGateSource(id, up_stop_distance, down_stop_distance, up_slow_distance, down_slow_distance, up_speed, down_speed, pose);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FireFoxGateSource)) {
            return false;
        }
        FireFoxGateSource fireFoxGateSource = (FireFoxGateSource) other;
        return Intrinsics.areEqual(this.id, fireFoxGateSource.id) && Double.compare(this.up_stop_distance, fireFoxGateSource.up_stop_distance) == 0 && Double.compare(this.down_stop_distance, fireFoxGateSource.down_stop_distance) == 0 && Double.compare(this.up_slow_distance, fireFoxGateSource.up_slow_distance) == 0 && Double.compare(this.down_slow_distance, fireFoxGateSource.down_slow_distance) == 0 && Double.compare(this.up_speed, fireFoxGateSource.up_speed) == 0 && Double.compare(this.down_speed, fireFoxGateSource.down_speed) == 0 && Intrinsics.areEqual(this.pose, fireFoxGateSource.pose);
    }

    public int hashCode() {
        String str = this.id;
        int hashCode = str != null ? str.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.up_stop_distance);
        int i = ((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.down_stop_distance);
        int i2 = (i + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31;
        long doubleToLongBits3 = Double.doubleToLongBits(this.up_slow_distance);
        int i3 = (i2 + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31;
        long doubleToLongBits4 = Double.doubleToLongBits(this.down_slow_distance);
        int i4 = (i3 + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31;
        long doubleToLongBits5 = Double.doubleToLongBits(this.up_speed);
        int i5 = (i4 + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31;
        long doubleToLongBits6 = Double.doubleToLongBits(this.down_speed);
        int i6 = (i5 + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31;
        Vector3d vector3d = this.pose;
        return i6 + (vector3d != null ? vector3d.hashCode() : 0);
    }

    public String toString() {
        return "FireFoxGateSource(id=" + this.id + ", up_stop_distance=" + this.up_stop_distance + ", down_stop_distance=" + this.down_stop_distance + ", up_slow_distance=" + this.up_slow_distance + ", down_slow_distance=" + this.down_slow_distance + ", up_speed=" + this.up_speed + ", down_speed=" + this.down_speed + ", pose=" + this.pose + ")";
    }

    public FireFoxGateSource(String id, double d, double d2, double d3, double d4, double d5, double d6, Vector3d pose) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        this.id = id;
        this.up_stop_distance = d;
        this.down_stop_distance = d2;
        this.up_slow_distance = d3;
        this.down_slow_distance = d4;
        this.up_speed = d5;
        this.down_speed = d6;
        this.pose = pose;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final double getUp_stop_distance() {
        return this.up_stop_distance;
    }

    public final void setUp_stop_distance(double d) {
        this.up_stop_distance = d;
    }

    public final double getDown_stop_distance() {
        return this.down_stop_distance;
    }

    public final void setDown_stop_distance(double d) {
        this.down_stop_distance = d;
    }

    public final double getUp_slow_distance() {
        return this.up_slow_distance;
    }

    public final void setUp_slow_distance(double d) {
        this.up_slow_distance = d;
    }

    public final double getDown_slow_distance() {
        return this.down_slow_distance;
    }

    public final void setDown_slow_distance(double d) {
        this.down_slow_distance = d;
    }

    public final double getUp_speed() {
        return this.up_speed;
    }

    public final void setUp_speed(double d) {
        this.up_speed = d;
    }

    public final double getDown_speed() {
        return this.down_speed;
    }

    public final void setDown_speed(double d) {
        this.down_speed = d;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }
}
