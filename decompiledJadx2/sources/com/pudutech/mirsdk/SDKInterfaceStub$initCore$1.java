package com.pudutech.mirsdk;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, m3961d2 = {"initCore", "", "pdmap", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub", m3970f = "SDKService.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {654}, m3973m = "initCore", m3974n = {"this", "pdmap", "state", "step", SpeechUtility.TAG_RESOURCE_RESULT, "topoMapList", "checkOverTime"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0"})
/* loaded from: classes5.dex */
public final class SDKInterfaceStub$initCore$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SDKInterfaceStub this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKInterfaceStub$initCore$1(SDKInterfaceStub sDKInterfaceStub, Continuation continuation) {
        super(continuation);
        this.this$0 = sDKInterfaceStub;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initCore(null, this);
    }
}
