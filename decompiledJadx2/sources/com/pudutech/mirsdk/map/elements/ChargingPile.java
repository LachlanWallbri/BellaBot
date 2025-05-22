package com.pudutech.mirsdk.map.elements;

import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ChargingPile.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0016\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "", "floor", "", "id", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "alignRange", "", MapElement.Key.GROUP, "mac", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;DLjava/lang/String;Ljava/lang/String;)V", "getAlignRange", "()D", "setAlignRange", "(D)V", "getFloor", "()Ljava/lang/String;", "setFloor", "(Ljava/lang/String;)V", "getGroup", "setGroup", "getId", "setId", "getMac", "setMac", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ChargingPile {
    private double alignRange;
    private String floor;
    private String group;
    private String id;
    private String mac;
    private Vector3d pose;

    public ChargingPile(String floor, String id, Vector3d pose, double d, String group, String mac) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.floor = floor;
        this.id = id;
        this.pose = pose;
        this.alignRange = d;
        this.group = group;
        this.mac = mac;
    }

    public /* synthetic */ ChargingPile(String str, String str2, Vector3d vector3d, double d, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, vector3d, d, str3, (i & 32) != 0 ? "" : str4);
    }

    public final double getAlignRange() {
        return this.alignRange;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMac() {
        return this.mac;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setAlignRange(double d) {
        this.alignRange = d;
    }

    public final void setFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.floor = str;
    }

    public final void setGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group = str;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }
}
