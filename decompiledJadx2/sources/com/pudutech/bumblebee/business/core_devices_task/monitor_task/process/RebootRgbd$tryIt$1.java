package com.pudutech.bumblebee.business.core_devices_task.monitor_task.process;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RebootRgbd.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, m3961d2 = {"tryIt", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.business.core_devices_task.monitor_task.process.RebootRgbd", m3970f = "RebootRgbd.kt", m3971i = {0, 0, 1, 1}, m3972l = {15, 19}, m3973m = "tryIt", m3974n = {"this", "it", "this", "it"}, m3975s = {"L$0", "I$2", "L$0", "I$2"})
/* loaded from: classes4.dex */
public final class RebootRgbd$tryIt$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RebootRgbd this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RebootRgbd$tryIt$1(RebootRgbd rebootRgbd, Continuation continuation) {
        super(continuation);
        this.this$0 = rebootRgbd;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.tryIt(this);
    }
}
