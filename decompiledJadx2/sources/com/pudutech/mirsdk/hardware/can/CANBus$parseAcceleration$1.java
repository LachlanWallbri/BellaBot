package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IAccelerationData;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$parseAcceleration$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseAcceleration$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        final AccelerationType accelerationType;
        String str;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        int m4577getimpl = UByteArray.m4577getimpl(bytes, 1) & 255;
        if (m4577getimpl == 1) {
            accelerationType = AccelerationType.Acceleration;
        } else if (m4577getimpl == 2) {
            accelerationType = AccelerationType.Deceleration;
        } else if (m4577getimpl == 3) {
            accelerationType = AccelerationType.EmergencyStopDeceleration;
        } else {
            accelerationType = AccelerationType.Unknow;
        }
        final double m4577getimpl2 = (((((UByteArray.m4577getimpl(bytes, 2) & 255) << 24) + ((UByteArray.m4577getimpl(bytes, 3) & 255) << 16)) + ((UByteArray.m4577getimpl(bytes, 4) & 255) << 8)) + (UByteArray.m4577getimpl(bytes, 5) & 255)) / 10000;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onAcceleration " + accelerationType + ", " + m4577getimpl2);
        this.this$0.getAccelerationDataProvider().notify(new Function2<IAccelerationData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseAcceleration$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IAccelerationData iAccelerationData, String str2) {
                invoke2(iAccelerationData, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IAccelerationData l, String str2) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                l.onData(AccelerationType.this, m4577getimpl2);
            }
        });
    }
}
