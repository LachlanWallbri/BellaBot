package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.ElevatorRequestListener;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegment;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.ElevatorTask;
import com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface;
import java.util.List;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$MovingOutElevator$switch$2", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 2}, m3972l = {1102, 1109, 1118}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class ElevatorTask$MovingOutElevator$switch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6356p$;
    final /* synthetic */ ElevatorTask.MovingOutElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$MovingOutElevator$switch$2(ElevatorTask.MovingOutElevator movingOutElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = movingOutElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$MovingOutElevator$switch$2 elevatorTask$MovingOutElevator$switch$2 = new ElevatorTask$MovingOutElevator$switch$2(this.this$0, completion);
        elevatorTask$MovingOutElevator$switch$2.f6356p$ = (CoroutineScope) obj;
        return elevatorTask$MovingOutElevator$switch$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$MovingOutElevator$switch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x006a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6356p$;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i == 2 || i == 3) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    while (CoroutineScopeKt.isActive(coroutineScope)) {
                        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                        PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                            Intrinsics.throwNpe();
                        }
                        String dstFloor = pathSegments.getDstFloor();
                        long j = ElevatorTask.this.seq;
                        String str = ElevatorTask.this.robotID;
                        PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                        if (pathSegments2 == null) {
                            Intrinsics.throwNpe();
                        }
                        List<PathSegment> segments = pathSegments2.getSegments();
                        if (segments == null) {
                            Intrinsics.throwNpe();
                        }
                        String curGoal = segments.get(1).getCurGoal();
                        if (curGoal == null) {
                            Intrinsics.throwNpe();
                        }
                        access$getElvRemoteCommunicate$p.callElv("INCAB", dstFloor, j, str, curGoal);
                        this.L$0 = coroutineScope;
                        this.label = 3;
                        if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MovingOutElevator$switch$2.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str2) {
                invoke2(elevatorRequestListener, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ElevatorRequestListener it, String str2) {
                String informationForElevatorListeners;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                ElevatorUtilizeState elevatorUtilizeState = ElevatorUtilizeState.CallingElevator;
                informationForElevatorListeners = ElevatorTask.this.informationForElevatorListeners("INCAB");
                it.informElevatorUtilizeState(elevatorUtilizeState, informationForElevatorListeners);
            }
        });
        MoveAction action = ElevatorTask.this.getAction();
        this.L$0 = coroutineScope;
        this.label = 2;
        if (MoveAction.actionStop$default(action, false, this, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        return Unit.INSTANCE;
    }
}
