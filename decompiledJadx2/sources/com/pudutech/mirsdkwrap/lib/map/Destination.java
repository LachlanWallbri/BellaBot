package com.pudutech.mirsdkwrap.lib.map;

import com.pudutech.mirsdk.compat.topo.MapElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Destination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003JG\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015¨\u0006%"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "", "name", "", MapElement.Key.GROUP, "floor", "type", "Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "x", "", "y", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;DD)V", "getFloor", "()Ljava/lang/String;", "getGroup", "getName", "getType", "()Lcom/pudutech/mirsdkwrap/lib/map/DestinationType;", "getX", "()D", "setX", "(D)V", "getY", "setY", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class Destination {
    private final String floor;
    private final String group;
    private final String name;
    private final DestinationType type;
    private double x;
    private double y;

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getGroup() {
        return this.group;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFloor() {
        return this.floor;
    }

    /* renamed from: component4, reason: from getter */
    public final DestinationType getType() {
        return this.type;
    }

    /* renamed from: component5, reason: from getter */
    public final double getX() {
        return this.x;
    }

    /* renamed from: component6, reason: from getter */
    public final double getY() {
        return this.y;
    }

    public final Destination copy(String name, String group, String floor, DestinationType type, double x, double y) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        return new Destination(name, group, floor, type, x, y);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Destination)) {
            return false;
        }
        Destination destination = (Destination) other;
        return Intrinsics.areEqual(this.name, destination.name) && Intrinsics.areEqual(this.group, destination.group) && Intrinsics.areEqual(this.floor, destination.floor) && Intrinsics.areEqual(this.type, destination.type) && Double.compare(this.x, destination.x) == 0 && Double.compare(this.y, destination.y) == 0;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.group;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.floor;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        DestinationType destinationType = this.type;
        int hashCode4 = (hashCode3 + (destinationType != null ? destinationType.hashCode() : 0)) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.x);
        int i = (hashCode4 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.y);
        return i + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "Destination(name=" + this.name + ", group=" + this.group + ", floor=" + this.floor + ", type=" + this.type + ", x=" + this.x + ", y=" + this.y + ")";
    }

    public Destination(String name, String group, String floor, DestinationType destinationType, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        this.name = name;
        this.group = group;
        this.floor = floor;
        this.type = destinationType;
        this.x = d;
        this.y = d2;
    }

    public final String getName() {
        return this.name;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final DestinationType getType() {
        return this.type;
    }

    public final double getX() {
        return this.x;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final double getY() {
        return this.y;
    }

    public final void setY(double d) {
        this.y = d;
    }
}
