package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/hardware/IHardware;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$controlCameraIRDLED$3 extends Lambda implements Function2<IHardware, String, Unit> {
    final /* synthetic */ boolean $lightOn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$controlCameraIRDLED$3(boolean z) {
        super(2);
        this.$lightOn = z;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
        invoke2(iHardware, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(IHardware l, String str) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
        l.onCameraIRDLED(this.$lightOn);
    }
}
