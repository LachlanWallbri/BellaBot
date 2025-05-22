package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$motorNewProtocol$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$motorNewProtocol$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        Function2 function2;
        Function2 function22;
        List list;
        List list2;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        function2 = this.this$0.wheelFlagToList43;
        List list3 = (List) function2.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 1) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 2) & 255));
        function22 = this.this$0.wheelFlagToList43;
        List list4 = (List) function22.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 3) & 255), Integer.valueOf(UByteArray.m4577getimpl(bytes, 4) & 255));
        List list5 = list3;
        if (!list5.isEmpty()) {
            list2 = this.this$0.leftWheelLastError;
            list2.addAll(list5);
        }
        List list6 = list4;
        if (!list6.isEmpty()) {
            list = this.this$0.rightWheelLastError;
            list.addAll(list6);
        }
        if (this.this$0.getMachineType().getModel() == MachineModel.BellaBot && this.this$0.getMachineType().getMainVersion() == 0 && this.this$0.getMachineType().getMinorVersion() == 1) {
            this.this$0.notifyWheelError(list3, list4);
        }
    }
}
