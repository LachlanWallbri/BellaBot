package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.hardware.cloud.BatteryHealth;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$parseOther$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseOther$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        BatteryHealth batteryHealth;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        batteryHealth = this.this$0.reportBatteryHealth;
        batteryHealth.m4432reportGBYM_sE(UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(bytes, 1, 6)));
    }
}
