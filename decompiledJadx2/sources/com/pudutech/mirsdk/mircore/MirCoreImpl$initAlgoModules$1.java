package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.math.Primes;

/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"initAlgoModules", "", "initListener", "Lcom/pudutech/mirsdk/mircore/InitServiceListener;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl", m3970f = "MirCoreImpl.kt", m3971i = {0, 0, 0, 1, 1, 1}, m3972l = {210, Primes.SMALL_FACTOR_LIMIT}, m3973m = "initAlgoModules", m3974n = {"this", "initListener", "finishInit", "this", "initListener", "finishInit"}, m3975s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0"})
/* loaded from: classes4.dex */
final class MirCoreImpl$initAlgoModules$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MirCoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirCoreImpl$initAlgoModules$1(MirCoreImpl mirCoreImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = mirCoreImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initAlgoModules(null, this);
    }
}
