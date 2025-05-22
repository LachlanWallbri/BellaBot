package com.pudutech.mirsdk.hardware.can;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"openOrCloseHeadUsb", "", DebugKt.DEBUG_PROPERTY_VALUE_ON, "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/mirsdk/hardware/can/CANBus$Result;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus", m3970f = "CANBus.kt", m3971i = {0, 0, 0, 0}, m3972l = {747}, m3973m = "openOrCloseHeadUsb", m3974n = {"this", DebugKt.DEBUG_PROPERTY_VALUE_ON, "opened", "canData"}, m3975s = {"L$0", "Z$0", "L$1", "L$2"})
/* loaded from: classes2.dex */
public final class CANBus$openOrCloseHeadUsb$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$openOrCloseHeadUsb$1(CANBus cANBus, Continuation continuation) {
        super(continuation);
        this.this$0 = cANBus;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.openOrCloseHeadUsb(false, this);
    }
}
