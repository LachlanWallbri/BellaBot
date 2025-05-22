package com.pudutech.mirsdk.hardware.can;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$motorOldProtocol$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$motorOldProtocol$1(CANBus cANBus) {
        super(2);
        this.this$0 = cANBus;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, UByteArray uByteArray) {
        invoke(num.intValue(), uByteArray.getStorage());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, byte[] bytes) {
        Function1 function1;
        Function1 function12;
        String str;
        long j;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        function1 = this.this$0.wheelFlagToList42;
        final List list = (List) function1.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 1) & 255));
        function12 = this.this$0.wheelFlagToList42;
        final List list2 = (List) function12.invoke(Integer.valueOf(UByteArray.m4577getimpl(bytes, 2) & 255));
        if (!list.isEmpty() || !list2.isEmpty()) {
            this.this$0.lastMotorNormalUpdateTimer = 0L;
            this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$motorOldProtocol$1.2
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
                public final void invoke2(IHardware l, String str2) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    Object[] array = list.toArray(new WheelError[0]);
                    if (array == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    WheelError[] wheelErrorArr = (WheelError[]) array;
                    Object[] array2 = list2.toArray(new WheelError[0]);
                    if (array2 != null) {
                        l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            });
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "onWheelError " + list + ' ' + list2);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        j = this.this$0.lastMotorNormalUpdateTimer;
        if (elapsedRealtime - j > 1000) {
            this.this$0.lastMotorNormalUpdateTimer = elapsedRealtime;
            this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$motorOldProtocol$1.1
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
                public final void invoke2(IHardware l, String str2) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                    Object[] array = list.toArray(new WheelError[0]);
                    if (array == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    WheelError[] wheelErrorArr = (WheelError[]) array;
                    Object[] array2 = list2.toArray(new WheelError[0]);
                    if (array2 != null) {
                        l.onWheelError(wheelErrorArr, (WheelError[]) array2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            });
        }
    }
}
