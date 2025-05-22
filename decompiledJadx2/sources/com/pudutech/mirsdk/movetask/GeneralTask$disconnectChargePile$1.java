package com.pudutech.mirsdk.movetask;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GeneralTask$disconnectChargePile$1", m3970f = "GeneralTask.kt", m3971i = {0, 1, 2, 3}, m3972l = {737, 738, 739, 740}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class GeneralTask$disconnectChargePile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6402p$;
    final /* synthetic */ GeneralTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask$disconnectChargePile$1(GeneralTask generalTask, Continuation continuation) {
        super(2, continuation);
        this.this$0 = generalTask;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GeneralTask$disconnectChargePile$1 generalTask$disconnectChargePile$1 = new GeneralTask$disconnectChargePile$1(this.this$0, completion);
        generalTask$disconnectChargePile$1.f6402p$ = (CoroutineScope) obj;
        return generalTask$disconnectChargePile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GeneralTask$disconnectChargePile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0091 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0070  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job stopChargeJob;
        Job stateReqJob;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6402p$;
            job = this.this$0.connectJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                stateReqJob = this.this$0.getStateReqJob();
                if (stateReqJob == null) {
                    return null;
                }
                this.L$0 = coroutineScope;
                this.label = 4;
                if (JobKt.cancelAndJoin(stateReqJob, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            stopChargeJob = this.this$0.getStopChargeJob();
            if (stopChargeJob != null) {
                this.L$0 = coroutineScope;
                this.label = 3;
                if (JobKt.cancelAndJoin(stopChargeJob, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            stateReqJob = this.this$0.getStateReqJob();
            if (stateReqJob == null) {
            }
        }
        Job startChargeJob = this.this$0.getStartChargeJob();
        if (startChargeJob != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(startChargeJob, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        stopChargeJob = this.this$0.getStopChargeJob();
        if (stopChargeJob != null) {
        }
        stateReqJob = this.this$0.getStateReqJob();
        if (stateReqJob == null) {
        }
    }
}
