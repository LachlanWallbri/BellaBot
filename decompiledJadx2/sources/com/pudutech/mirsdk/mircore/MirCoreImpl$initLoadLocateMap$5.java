package com.pudutech.mirsdk.mircore;

import com.pudutech.mirsdk.mircore.module.HardwareLinker;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m3961d2 = {"<anonymous>", "", "it", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MirCoreImpl$initLoadLocateMap$5 extends Lambda implements Function1<Integer, Boolean> {
    public static final MirCoreImpl$initLoadLocateMap$5 INSTANCE = new MirCoreImpl$initLoadLocateMap$5();

    MirCoreImpl$initLoadLocateMap$5() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Integer num) {
        return Boolean.valueOf(invoke(num.intValue()));
    }

    public final boolean invoke(int i) {
        HardwareLinker hardwareLinker;
        MirCoreImpl mirCoreImpl = MirCoreImpl.INSTANCE;
        hardwareLinker = MirCoreImpl.hardwareLinker;
        return hardwareLinker.setExposure(i);
    }
}
