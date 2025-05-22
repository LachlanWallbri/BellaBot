package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.hardware.IHardware;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Timer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, m3961d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CANBus$regProtocol$14$$special$$inlined$schedule$2 extends TimerTask {
    final /* synthetic */ CANBus$regProtocol$14 this$0;

    public CANBus$regProtocol$14$$special$$inlined$schedule$2(CANBus$regProtocol$14 cANBus$regProtocol$14) {
        this.this$0 = cANBus$regProtocol$14;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.this$0.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$regProtocol$14$3$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                invoke2(iHardware, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onChargingFail();
            }
        });
    }
}
