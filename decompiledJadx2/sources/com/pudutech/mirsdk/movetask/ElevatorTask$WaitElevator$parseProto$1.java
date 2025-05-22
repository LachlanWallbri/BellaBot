package com.pudutech.mirsdk.movetask;

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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$parseProto$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1}, m3972l = {590, 592}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$WaitElevator$parseProto$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6359p$;
    final /* synthetic */ ElevatorTask.WaitElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitElevator$parseProto$1(ElevatorTask.WaitElevator waitElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitElevator$parseProto$1 elevatorTask$WaitElevator$parseProto$1 = new ElevatorTask$WaitElevator$parseProto$1(this.this$0, completion);
        elevatorTask$WaitElevator$parseProto$1.f6359p$ = (CoroutineScope) obj;
        return elevatorTask$WaitElevator$parseProto$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitElevator$parseProto$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
    
        r11 = r10.this$0.job;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0073  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        PathSegments pathSegments;
        PathSegments pathSegments2;
        List<PathSegment> segments;
        String curGoal;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6359p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(20L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    this.this$0.job = (Job) null;
                    ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                    pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    String curFloor = pathSegments.getCurFloor();
                    ElevatorTask elevatorTask = ElevatorTask.this;
                    elevatorTask.seq++;
                    long j = elevatorTask.seq;
                    String str = ElevatorTask.this.robotID;
                    pathSegments2 = ElevatorTask.this.getPathSegments();
                    if (pathSegments2 == null) {
                        Intrinsics.throwNpe();
                    }
                    segments = pathSegments2.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    curGoal = segments.get(1).getCurGoal();
                    if (curGoal == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getElvRemoteCommunicate$p.prepareRide(curFloor, j, str, curGoal);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job = this.this$0.job;
        if (job != null && job.isActive() && job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.this$0.job = (Job) null;
        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p2 = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
        pathSegments = ElevatorTask.this.getPathSegments();
        if (pathSegments == null) {
        }
        String curFloor2 = pathSegments.getCurFloor();
        ElevatorTask elevatorTask2 = ElevatorTask.this;
        elevatorTask2.seq++;
        long j2 = elevatorTask2.seq;
        String str2 = ElevatorTask.this.robotID;
        pathSegments2 = ElevatorTask.this.getPathSegments();
        if (pathSegments2 == null) {
        }
        segments = pathSegments2.getSegments();
        if (segments == null) {
        }
        curGoal = segments.get(1).getCurGoal();
        if (curGoal == null) {
        }
        access$getElvRemoteCommunicate$p2.prepareRide(curFloor2, j2, str2, curGoal);
        return Unit.INSTANCE;
    }
}
