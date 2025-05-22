package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IDownRgbdData;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "name", "", "iDownRgbdData", "Lcom/pudutech/mirsdk/hardware/IDownRgbdData;", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initializerOnLoadTopoMap$6 extends Lambda implements Function2<String, IDownRgbdData, Unit> {
    public static final MirCoreImpl$initializerOnLoadTopoMap$6 INSTANCE = new MirCoreImpl$initializerOnLoadTopoMap$6();

    MirCoreImpl$initializerOnLoadTopoMap$6() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(String str, IDownRgbdData iDownRgbdData) {
        invoke2(str, iDownRgbdData);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String name, IDownRgbdData iDownRgbdData) {
        HardwareLinker hardwareLinker;
        RGBDInterface rGBDInterface;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(iDownRgbdData, "iDownRgbdData");
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        hardwareLinker = MirCoreImpl.hardwareLinker;
        HardwareInterface hardwareService = hardwareLinker.getHardwareService();
        if (hardwareService == null || (rGBDInterface = hardwareService.getRGBDInterface()) == null) {
            return;
        }
        rGBDInterface.addDownRgbdListener(name, iDownRgbdData);
    }
}
