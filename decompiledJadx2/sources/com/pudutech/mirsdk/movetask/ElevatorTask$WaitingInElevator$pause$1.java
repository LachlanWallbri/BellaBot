package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.base.SDKRobotState;
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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$pause$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 2, 3}, m3972l = {865, 866, 867, 868}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$WaitingInElevator$pause$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6375p$;
    final /* synthetic */ ElevatorTask.WaitingInElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitingInElevator$pause$1(ElevatorTask.WaitingInElevator waitingInElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitingInElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitingInElevator$pause$1 elevatorTask$WaitingInElevator$pause$1 = new ElevatorTask$WaitingInElevator$pause$1(this.this$0, completion);
        elevatorTask$WaitingInElevator$pause$1.f6375p$ = (CoroutineScope) obj;
        return elevatorTask$WaitingInElevator$pause$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitingInElevator$pause$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        MoveAction action;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6375p$;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job2 = this.this$0.controlWheelJob;
                    if (job2 != null) {
                        this.L$0 = coroutineScope;
                        this.label = 3;
                        if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    action = ElevatorTask.this.getAction();
                    this.L$0 = coroutineScope;
                    this.label = 4;
                    if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
                    }
                    ElevatorTask.this.onStateChange(SDKRobotState.Pause, "");
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    if (i == 4) {
                        ResultKt.throwOnFailure(obj);
                        ElevatorTask.this.onStateChange(SDKRobotState.Pause, "");
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                action = ElevatorTask.this.getAction();
                this.L$0 = coroutineScope;
                this.label = 4;
                if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ElevatorTask.this.onStateChange(SDKRobotState.Pause, "");
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job = this.this$0.job;
        if (job != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        job2 = this.this$0.controlWheelJob;
        if (job2 != null) {
        }
        action = ElevatorTask.this.getAction();
        this.L$0 = coroutineScope;
        this.label = 4;
        if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
        }
        ElevatorTask.this.onStateChange(SDKRobotState.Pause, "");
        return Unit.INSTANCE;
    }
}
