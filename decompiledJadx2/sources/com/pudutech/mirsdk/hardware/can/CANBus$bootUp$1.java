package com.pudutech.mirsdk.hardware.can;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, m3961d2 = {"bootUp", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/mirsdk/hardware/can/CANBus$Result;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus", m3970f = "CANBus.kt", m3971i = {0, 1, 1, 2, 2, 2}, m3972l = {91, 145, 162}, m3973m = "bootUp", m3974n = {"this", "this", "tryTimes", "this", "tryTimes", "tickerChannel"}, m3975s = {"L$0", "L$0", "I$0", "L$0", "I$0", "L$1"})
/* loaded from: classes2.dex */
public final class CANBus$bootUp$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$bootUp$1(CANBus cANBus, Continuation continuation) {
        super(continuation);
        this.this$0 = cANBus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.bootUp(this);
    }
}
