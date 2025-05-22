package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.MoveAction;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$switch$rState$2", m3970f = "ElevatorTask.kt", m3971i = {0, 1}, m3972l = {498, 499}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$WaitElevator$switch$rState$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6366p$;
    final /* synthetic */ ElevatorTask.WaitElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitElevator$switch$rState$2(ElevatorTask.WaitElevator waitElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitElevator$switch$rState$2 elevatorTask$WaitElevator$switch$rState$2 = new ElevatorTask$WaitElevator$switch$rState$2(this.this$0, completion);
        elevatorTask$WaitElevator$switch$rState$2.f6366p$ = (CoroutineScope) obj;
        return elevatorTask$WaitElevator$switch$rState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitElevator$switch$rState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0059 -> B:7:0x0030). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ElevatorTask$WaitElevator$switch$rState$2 elevatorTask$WaitElevator$switch$rState$2;
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6366p$;
        } else if (i == 1) {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            Object obj3 = coroutine_suspended;
            elevatorTask$WaitElevator$switch$rState$2 = this;
            elevatorTask$WaitElevator$switch$rState$2.L$0 = coroutineScope2;
            elevatorTask$WaitElevator$switch$rState$2.label = 2;
            if (DelayKt.delay(100L, elevatorTask$WaitElevator$switch$rState$2) != obj3) {
                return obj3;
            }
            CoroutineScope coroutineScope3 = coroutineScope2;
            obj2 = obj3;
            coroutineScope = coroutineScope3;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                MoveAction action = ElevatorTask.this.getAction();
                elevatorTask$WaitElevator$switch$rState$2.L$0 = coroutineScope;
                elevatorTask$WaitElevator$switch$rState$2.label = 1;
                if (action.controlWheelCmd(true, elevatorTask$WaitElevator$switch$rState$2) == obj2) {
                    return obj2;
                }
                Object obj4 = obj2;
                coroutineScope2 = coroutineScope;
                obj3 = obj4;
                elevatorTask$WaitElevator$switch$rState$2.L$0 = coroutineScope2;
                elevatorTask$WaitElevator$switch$rState$2.label = 2;
                if (DelayKt.delay(100L, elevatorTask$WaitElevator$switch$rState$2) != obj3) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope4;
        }
        obj2 = coroutine_suspended;
        elevatorTask$WaitElevator$switch$rState$2 = this;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
