package com.pudutech.mirsdk.map.elements;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccessControlPoint.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/AccessControlPoint;", "", "id", "", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "openedSpendTime", "", "(Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;D)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getOpenedSpendTime", "()D", "setOpenedSpendTime", "(D)V", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AccessControlPoint {
    private String id;
    private double openedSpendTime;
    private Vector3d pose;

    public AccessControlPoint(String id, Vector3d pose, double d) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        this.id = id;
        this.pose = pose;
        this.openedSpendTime = d;
    }

    public final String getId() {
        return this.id;
    }

    public final double getOpenedSpendTime() {
        return this.openedSpendTime;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setOpenedSpendTime(double d) {
        this.openedSpendTime = d;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }
}
