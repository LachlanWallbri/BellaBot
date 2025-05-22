package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.hardware.ISensorData;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$parseIMU$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseIMU$1(CANBus cANBus) {
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
        this.this$0.getGyroscope().update((short) ((((UByteArray.m4577getimpl(bytes, 1) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 2) & 255)), (short) ((((UByteArray.m4577getimpl(bytes, 3) & 255) & 255) << 8) | (UByteArray.m4577getimpl(bytes, 4) & 255)), (short) ((UByteArray.m4577getimpl(bytes, 6) & 255) | (((UByteArray.m4577getimpl(bytes, 5) & 255) & 255) << 8)));
        this.this$0.setReceivedIMU(true);
        this.this$0.getSensorDataProvider().notify(new Function2<ISensorData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseIMU$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISensorData iSensorData, String str) {
                invoke2(iSensorData, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISensorData it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.onIMU(CANBus$parseIMU$1.this.this$0.getGyroscope().get_last().getX(), CANBus$parseIMU$1.this.this$0.getGyroscope().get_last().getY(), CANBus$parseIMU$1.this.this$0.getGyroscope().get_last().getZ(), 0.025d);
            }
        });
    }
}
