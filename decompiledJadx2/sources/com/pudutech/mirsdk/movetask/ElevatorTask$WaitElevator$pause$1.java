package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.movetask.ElevatorTask;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$pause$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1}, m3972l = {460, 461}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$WaitElevator$pause$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6361p$;
    final /* synthetic */ ElevatorTask.WaitElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitElevator$pause$1(ElevatorTask.WaitElevator waitElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitElevator$pause$1 elevatorTask$WaitElevator$pause$1 = new ElevatorTask$WaitElevator$pause$1(this.this$0, completion);
        elevatorTask$WaitElevator$pause$1.f6361p$ = (CoroutineScope) obj;
        return elevatorTask$WaitElevator$pause$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitElevator$pause$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6361p$;
            job = this.this$0.job;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job2 = this.this$0.controlWheelJob;
        if (job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
