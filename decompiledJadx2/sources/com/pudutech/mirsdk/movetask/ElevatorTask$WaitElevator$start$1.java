package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$start$1", m3970f = "ElevatorTask.kt", m3971i = {0}, m3972l = {443}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class ElevatorTask$WaitElevator$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6364p$;
    final /* synthetic */ ElevatorTask.WaitElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitElevator$start$1(ElevatorTask.WaitElevator waitElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitElevator$start$1 elevatorTask$WaitElevator$start$1 = new ElevatorTask$WaitElevator$start$1(this.this$0, completion);
        elevatorTask$WaitElevator$start$1.f6364p$ = (CoroutineScope) obj;
        return elevatorTask$WaitElevator$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitElevator$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6364p$;
            Pdlog.m3273d(ElevatorTask.this.TAG, "robot start call elevator, and continue to elevator waiter");
            MoveAction action = ElevatorTask.this.getAction();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ElevatorTask.this.moveToPoint(0);
        job = this.this$0.job;
        if (job != null && job.isActive()) {
            return Unit.INSTANCE;
        }
        ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$start$1.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str) {
                invoke2(elevatorRequestListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ElevatorRequestListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.informElevatorUtilizeState(ElevatorUtilizeState.CallingElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
            }
        });
        ElevatorTask.WaitElevator waitElevator = this.this$0;
        waitElevator.runCallElvJob(ElevatorTask.this.intoElevatorCount > 0 ? 10000L : 0L);
        return Unit.INSTANCE;
    }
}
