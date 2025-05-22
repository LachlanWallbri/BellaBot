package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class CANBus$regProtocol$21 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$regProtocol$21(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        String str;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "geomagneticAntiDrop:  " + CommonKt.m4421toHexStringGBYM_sE(bytes));
        String replace$default = StringsKt.replace$default(CommonKt.m4421toHexStringGBYM_sE(bytes), " ", "", false, 4, (Object) null);
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = (short) Integer.valueOf(String.valueOf(replace$default.charAt(2)) + String.valueOf(replace$default.charAt(3)) + String.valueOf(replace$default.charAt(4)) + String.valueOf(replace$default.charAt(5)), 16).intValue();
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = (short) Integer.valueOf(String.valueOf(replace$default.charAt(6)) + String.valueOf(replace$default.charAt(7)) + String.valueOf(replace$default.charAt(8)) + String.valueOf(replace$default.charAt(9)), 16).intValue();
        final Ref.IntRef intRef3 = new Ref.IntRef();
        intRef3.element = (short) Integer.valueOf(String.valueOf(replace$default.charAt(10)) + String.valueOf(replace$default.charAt(11)) + String.valueOf(replace$default.charAt(12)) + String.valueOf(replace$default.charAt(13)), 16).intValue();
        this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$21.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                invoke2(iHardware, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware it, String name) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(name, "name");
                it.geomagneticAntiDrop("right", Math.abs(Ref.IntRef.this.element), Math.abs(intRef2.element), Math.abs(intRef3.element));
            }
        });
    }
}
