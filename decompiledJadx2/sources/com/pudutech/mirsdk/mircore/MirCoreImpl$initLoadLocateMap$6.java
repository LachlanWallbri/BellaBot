package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initLoadLocateMap$6 extends Lambda implements Function0<Integer> {
    public static final MirCoreImpl$initLoadLocateMap$6 INSTANCE = new MirCoreImpl$initLoadLocateMap$6();

    MirCoreImpl$initLoadLocateMap$6() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Integer invoke() {
        return Integer.valueOf(invoke2());
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final int invoke2() {
        HardwareLinker hardwareLinker;
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        hardwareLinker = MirCoreImpl.hardwareLinker;
        return hardwareLinker.getExposure();
    }
}
