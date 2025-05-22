package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.ElevatorTask;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$start$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 2}, m3972l = {832, 836, 842}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class ElevatorTask$WaitingInElevator$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6376p$;
    final /* synthetic */ ElevatorTask.WaitingInElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitingInElevator$start$1(ElevatorTask.WaitingInElevator waitingInElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitingInElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitingInElevator$start$1 elevatorTask$WaitingInElevator$start$1 = new ElevatorTask$WaitingInElevator$start$1(this.this$0, completion);
        elevatorTask$WaitingInElevator$start$1.f6376p$ = (CoroutineScope) obj;
        return elevatorTask$WaitingInElevator$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitingInElevator$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
    
        r8 = r7.this$0.controlWheelJob;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0045, code lost:
    
        r8 = r7.this$0.job;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00e2  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        Job job3;
        Job job4;
        PathSegments pathSegments;
        PathSegments pathSegments2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6376p$;
            job = this.this$0.job;
            if (job != null && job.isActive() && job2 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        MoveAction action = ElevatorTask.this.getAction();
                        pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                            Intrinsics.throwNpe();
                        }
                        action.reloadMap(pathSegments.getDstFloor(), new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$start$1.1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                ElevatorTask$WaitingInElevator$start$1.this.this$0.elvPoses = ElevatorTask.this.getAction().getElevatorPose();
                            }
                        });
                        ElevatorTask.WaitingInElevator waitingInElevator = this.this$0;
                        pathSegments2 = ElevatorTask.this.getPathSegments();
                        if (pathSegments2 == null) {
                            Intrinsics.throwNpe();
                        }
                        waitingInElevator.informEnterElevator(pathSegments2.getCurFloor());
                        this.this$0.controlWheel();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                if (ElevatorTask.WaitingInElevator.WhenMappings.$EnumSwitchMapping$0[ElevatorTask.elvConnection.ordinal()] == 1) {
                    Pdlog.m3273d(ElevatorTask.this.TAG, "switch wifi to " + ElevatorTask.this.getGoalSSID());
                    ElevatorTask elevatorTask = ElevatorTask.this;
                    String goalSSID = ElevatorTask.this.getGoalSSID();
                    String goalSSIDName = ElevatorTask.this.getGoalSSIDName();
                    this.L$0 = coroutineScope;
                    this.label = 3;
                    if (elevatorTask.switchWifi(goalSSID, goalSSIDName, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                MoveAction action2 = ElevatorTask.this.getAction();
                pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                }
                action2.reloadMap(pathSegments.getDstFloor(), new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$start$1.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ElevatorTask$WaitingInElevator$start$1.this.this$0.elvPoses = ElevatorTask.this.getAction().getElevatorPose();
                    }
                });
                ElevatorTask.WaitingInElevator waitingInElevator2 = this.this$0;
                pathSegments2 = ElevatorTask.this.getPathSegments();
                if (pathSegments2 == null) {
                }
                waitingInElevator2.informEnterElevator(pathSegments2.getCurFloor());
                this.this$0.controlWheel();
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job3 = this.this$0.controlWheelJob;
        if (job3 != null && job3.isActive() && job4 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job4, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (ElevatorTask.WaitingInElevator.WhenMappings.$EnumSwitchMapping$0[ElevatorTask.elvConnection.ordinal()] == 1) {
        }
        MoveAction action22 = ElevatorTask.this.getAction();
        pathSegments = ElevatorTask.this.getPathSegments();
        if (pathSegments == null) {
        }
        action22.reloadMap(pathSegments.getDstFloor(), new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$start$1.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ElevatorTask$WaitingInElevator$start$1.this.this$0.elvPoses = ElevatorTask.this.getAction().getElevatorPose();
            }
        });
        ElevatorTask.WaitingInElevator waitingInElevator22 = this.this$0;
        pathSegments2 = ElevatorTask.this.getPathSegments();
        if (pathSegments2 == null) {
        }
        waitingInElevator22.informEnterElevator(pathSegments2.getCurFloor());
        this.this$0.controlWheel();
        return Unit.INSTANCE;
    }
}
