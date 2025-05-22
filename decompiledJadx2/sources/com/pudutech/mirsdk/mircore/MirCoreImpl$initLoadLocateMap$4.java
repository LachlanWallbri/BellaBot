package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initLoadLocateMap$4 extends Lambda implements Function1<Vector3d, Unit> {
    public static final MirCoreImpl$initLoadLocateMap$4 INSTANCE = new MirCoreImpl$initLoadLocateMap$4();

    MirCoreImpl$initLoadLocateMap$4() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Vector3d vector3d) {
        invoke2(vector3d);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Vector3d it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        Perception.INSTANCE.updateRobotOdom(it);
    }
}
