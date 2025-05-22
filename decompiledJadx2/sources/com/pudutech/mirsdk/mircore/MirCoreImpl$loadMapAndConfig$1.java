package com.pudutech.mirsdk.mircore;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirCoreImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.MirCoreImpl$loadMapAndConfig$1", m3970f = "MirCoreImpl.kt", m3971i = {0}, m3972l = {834}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class MirCoreImpl$loadMapAndConfig$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $locateInitJob;
    final /* synthetic */ Ref.ObjectRef $perceptionInitJob;
    final /* synthetic */ Ref.ObjectRef $scheduleInitJob;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6168p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirCoreImpl$loadMapAndConfig$1(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3, Continuation continuation) {
        super(2, continuation);
        this.$scheduleInitJob = objectRef;
        this.$locateInitJob = objectRef2;
        this.$perceptionInitJob = objectRef3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirCoreImpl$loadMapAndConfig$1 mirCoreImpl$loadMapAndConfig$1 = new MirCoreImpl$loadMapAndConfig$1(this.$scheduleInitJob, this.$locateInitJob, this.$perceptionInitJob, completion);
        mirCoreImpl$loadMapAndConfig$1.f6168p$ = (CoroutineScope) obj;
        return mirCoreImpl$loadMapAndConfig$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirCoreImpl$loadMapAndConfig$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6168p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            if (((Job) this.$scheduleInitJob.element).isActive() || ((Job) this.$locateInitJob.element).isActive() || ((Job) this.$perceptionInitJob.element).isActive()) {
                this.L$0 = coroutineScope;
                this.label = 1;
            } else {
                return Unit.INSTANCE;
            }
        } while (DelayKt.delay(50L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
