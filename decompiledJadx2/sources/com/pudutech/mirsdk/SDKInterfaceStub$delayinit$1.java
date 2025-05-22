package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, m3961d2 = {"delayinit", "", "state", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub", m3970f = "SDKService.kt", m3971i = {0, 0}, m3972l = {1029}, m3973m = "delayinit", m3974n = {"this", "state"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$delayinit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SDKInterfaceStub this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SDKInterfaceStub$delayinit$1(SDKInterfaceStub sDKInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = sDKInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.delayinit(null, this);
    }
}
