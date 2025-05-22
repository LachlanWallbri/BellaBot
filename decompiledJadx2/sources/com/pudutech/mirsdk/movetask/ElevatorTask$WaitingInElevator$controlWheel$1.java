package com.pudutech.mirsdk.movetask;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$controlWheel$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 2}, m3972l = {815, 818, BaseQuickAdapter.FOOTER_VIEW}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class ElevatorTask$WaitingInElevator$controlWheel$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6369p$;
    final /* synthetic */ ElevatorTask.WaitingInElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitingInElevator$controlWheel$1(ElevatorTask.WaitingInElevator waitingInElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitingInElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitingInElevator$controlWheel$1 elevatorTask$WaitingInElevator$controlWheel$1 = new ElevatorTask$WaitingInElevator$controlWheel$1(this.this$0, completion);
        elevatorTask$WaitingInElevator$controlWheel$1.f6369p$ = (CoroutineScope) obj;
        return elevatorTask$WaitingInElevator$controlWheel$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitingInElevator$controlWheel$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x007a -> B:8:0x0057). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ElevatorTask$WaitingInElevator$controlWheel$1 elevatorTask$WaitingInElevator$controlWheel$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6369p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            elevatorTask$WaitingInElevator$controlWheel$1 = this;
            elevatorTask$WaitingInElevator$controlWheel$1.L$0 = coroutineScope;
            elevatorTask$WaitingInElevator$controlWheel$1.label = 3;
            if (DelayKt.delay(100L, elevatorTask$WaitingInElevator$controlWheel$1) == coroutine_suspended) {
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 3) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            elevatorTask$WaitingInElevator$controlWheel$1 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                MoveAction action = ElevatorTask.this.getAction();
                elevatorTask$WaitingInElevator$controlWheel$1.L$0 = coroutineScope;
                elevatorTask$WaitingInElevator$controlWheel$1.label = 2;
                if (action.controlWheelCmd(true, elevatorTask$WaitingInElevator$controlWheel$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                elevatorTask$WaitingInElevator$controlWheel$1.L$0 = coroutineScope;
                elevatorTask$WaitingInElevator$controlWheel$1.label = 3;
                if (DelayKt.delay(100L, elevatorTask$WaitingInElevator$controlWheel$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$controlWheel$1.1
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
                it.informElevatorUtilizeState(ElevatorUtilizeState.FinishEnterElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
            }
        });
        elevatorTask$WaitingInElevator$controlWheel$1 = this;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
