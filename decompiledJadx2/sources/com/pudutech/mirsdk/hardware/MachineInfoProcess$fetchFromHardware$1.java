package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfoProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012*\u0010\u0002\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0004\u0012\u00020\u00070\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0086@"}, m3961d2 = {"fetchFromHardware", "", "registerCAN", "Lkotlin/Function2;", "Lkotlin/UByte;", "", "Lkotlin/UByteArray;", "", "unRegisterCAN", "Lkotlin/reflect/KFunction1;", "sendCAN", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/mirsdk/hardware/MachineInfoProcess$FetchResult;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.MachineInfoProcess", m3970f = "MachineInfoProcess.kt", m3971i = {0, 0, 0, 0}, m3972l = {428}, m3973m = "fetchFromHardware", m3974n = {"this", "registerCAN", "unRegisterCAN", "sendCAN"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes.dex */
public final class MachineInfoProcess$fetchFromHardware$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MachineInfoProcess this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MachineInfoProcess$fetchFromHardware$1(MachineInfoProcess machineInfoProcess, Continuation continuation) {
        super(continuation);
        this.this$0 = machineInfoProcess;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetchFromHardware(null, null, null, this);
    }
}
