package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086@"}, m3961d2 = {"initializationCore", "", "defFloorIndex", "", "pdmap", "", "floors_map", "", "initListener", "Lcom/pudutech/mirsdk/mircore/InitServiceListener;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl", m3970f = "MirCoreImpl.kt", m3971i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, m3972l = {102, 103}, m3973m = "initializationCore", m3974n = {"this", "defFloorIndex", "pdmap", "floors_map", "initListener", "finishInit", "this", "defFloorIndex", "pdmap", "floors_map", "initListener", "finishInit"}, m3975s = {"L$0", "I$0", "L$1", "L$2", "L$3", "I$1", "L$0", "I$0", "L$1", "L$2", "L$3", "I$1"})
/* loaded from: classes6.dex */
public final class MirCoreImpl$initializationCore$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MirCoreImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirCoreImpl$initializationCore$1(MirCoreImpl mirCoreImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = mirCoreImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initializationCore(0, null, null, null, this);
    }
}
