package com.pudutech.mirsdk.movetask;

import android.os.SystemClock;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$MovingOutElevator$resume$1", m3970f = "ElevatorTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class ElevatorTask$MovingOutElevator$resume$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6352p$;
    final /* synthetic */ ElevatorTask.MovingOutElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$MovingOutElevator$resume$1(ElevatorTask.MovingOutElevator movingOutElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = movingOutElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$MovingOutElevator$resume$1 elevatorTask$MovingOutElevator$resume$1 = new ElevatorTask$MovingOutElevator$resume$1(this.this$0, completion);
        elevatorTask$MovingOutElevator$resume$1.f6352p$ = (CoroutineScope) obj;
        return elevatorTask$MovingOutElevator$resume$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$MovingOutElevator$resume$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long j;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6352p$;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        j = this.this$0.startOutElevator;
        if (elapsedRealtime - j < ElevatorTask.this.MOVE_OVERTIME) {
            ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MovingOutElevator$resume$1.1
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
                    it.informElevatorUtilizeState(ElevatorUtilizeState.LeavingElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                }
            });
        }
        return Unit.INSTANCE;
    }
}
