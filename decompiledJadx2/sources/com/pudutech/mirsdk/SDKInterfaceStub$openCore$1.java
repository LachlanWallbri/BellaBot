package com.pudutech.mirsdk;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, m3961d2 = {"openCore", "", "locateCase", "Lcom/pudutech/mirsdk/aidl/serialize/LocateCase;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub", m3970f = "SDKService.kt", m3971i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m3972l = {557, 567}, m3973m = "openCore", m3974n = {"this", "locateCase", "success", "productMachineType", "cameraExposure", "mapInitResult", "this", "locateCase", "success", "productMachineType", "cameraExposure", "mapInitResult"}, m3975s = {"L$0", "L$1", "I$0", "L$2", "L$3", "Z$0", "L$0", "L$1", "I$0", "L$2", "L$3", "Z$0"})
/* loaded from: classes5.dex */
public final class SDKInterfaceStub$openCore$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SDKInterfaceStub this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKInterfaceStub$openCore$1(SDKInterfaceStub sDKInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = sDKInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.openCore(null, this);
    }
}
