package com.pudutech.mirsdk.map.elements;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorWaiter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/ElevatorWaiter;", "", "floor", "", "id", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "getFloor", "()Ljava/lang/String;", "setFloor", "(Ljava/lang/String;)V", "getId", "setId", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ElevatorWaiter {
    private String floor;
    private String id;
    private Vector3d pose;

    public ElevatorWaiter(String floor, String id, Vector3d pose) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        this.floor = floor;
        this.id = id;
        this.pose = pose;
    }

    public /* synthetic */ ElevatorWaiter(String str, String str2, Vector3d vector3d, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, vector3d);
    }

    public final String getFloor() {
        return this.floor;
    }

    public final String getId() {
        return this.id;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.floor = str;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }
}
