package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "name", "", "iLidarData", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initializerOnLoadTopoMap$2 extends Lambda implements Function2<String, ILidarData, Unit> {
    public static final MirCoreImpl$initializerOnLoadTopoMap$2 INSTANCE = new MirCoreImpl$initializerOnLoadTopoMap$2();

    MirCoreImpl$initializerOnLoadTopoMap$2() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, ILidarData iLidarData) {
        invoke2(str, iLidarData);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String name, ILidarData iLidarData) {
        HardwareLinker hardwareLinker;
        LidarInterface lidarInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(iLidarData, "iLidarData");
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        hardwareLinker = MirCoreImpl.hardwareLinker;
        HardwareInterface hardwareService = hardwareLinker.getHardwareService();
        if (hardwareService == null || (lidarInterface = hardwareService.getLidarInterface()) == null) {
            return;
        }
        lidarInterface.addSecondLidarDataListener(name, iLidarData);
    }
}
