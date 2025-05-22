package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "bytes", "Lkotlin/UByteArray;", "invoke", "(I[B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class CANBus$parseChargePileConnect$1 extends Lambda implements Function2<Integer, UByteArray, Unit> {
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$parseChargePileConnect$1(CANBus cANBus) {
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
        String str2;
        String str3;
        String str4;
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "chargepile connected" + UByte.m4563toStringimpl(UByteArray.m4577getimpl(bytes, 1)));
        byte m4577getimpl = UByteArray.m4577getimpl(bytes, 1);
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 1)) {
            if (CANBus.access$isDoSuspendChargePile$p(this.this$0)) {
                str4 = this.this$0.TAG;
                Pdlog.m3273d(str4, "chargepile is doing suspend charge pile task");
                return;
            } else {
                this.this$0.notifyCharging();
                CANBus.access$lockWheel(this.this$0);
                return;
            }
        }
        if (m4577getimpl == UByte.m4528constructorimpl((byte) 2)) {
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "onChargingFail  suspendUsingCharingPile");
            this.this$0.suspendUsingCharingPile();
            new Timer().schedule(new TimerTask() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseChargePileConnect$1$$special$$inlined$schedule$1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    CANBus$parseChargePileConnect$1.this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$parseChargePileConnect$1$1$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str5) {
                            invoke2(iHardware, str5);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str5) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                            l.onChargingFail();
                        }
                    });
                }
            }, 350L);
            return;
        }
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "no such value to do");
    }
}
