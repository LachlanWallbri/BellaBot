package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$parseBattery$2 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseBattery$2(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        this.this$0.lastPowerPecent = UByteArray.m4577getimpl(bytes, 1) & 255;
        this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseBattery$2.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                invoke2(iHardware, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware l, String str) {
                int i2;
                ChargeState chargeState;
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                i2 = CANBus$parseBattery$2.this.this$0.lastPowerPecent;
                chargeState = CANBus$parseBattery$2.this.this$0.lastChargeState;
                l.onBattery(i2, chargeState);
            }
        });
    }
}
