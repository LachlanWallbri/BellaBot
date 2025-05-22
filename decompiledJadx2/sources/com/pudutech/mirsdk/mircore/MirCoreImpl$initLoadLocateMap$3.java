package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "p0", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "<anonymous parameter 1>", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initLoadLocateMap$3 extends Lambda implements Function2<Vector3d, Vector3d, Unit> {
    public static final MirCoreImpl$initLoadLocateMap$3 INSTANCE = new MirCoreImpl$initLoadLocateMap$3();

    MirCoreImpl$initLoadLocateMap$3() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d, Vector3d vector3d2) {
        invoke2(vector3d, vector3d2);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Vector3d p0, Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        Intrinsics.checkParameterIsNotNull(vector3d, "<anonymous parameter 1>");
        Perception.INSTANCE.updateRobotPose(p0);
        synchronized (MirCoreImpl.INSTANCE.getPosition$mircore_packRelease()) {
            MirCoreImpl.INSTANCE.setPosition$mircore_packRelease(p0);
            Unit unit = Unit.INSTANCE;
        }
    }
}
