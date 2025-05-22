package com.pudutech.mirsdk.movetask;

import com.pudutech.mirsdk.elv.proto.Elevator;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$parseProto$2", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 2}, m3972l = {971, 972, 974}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
final class ElevatorTask$WaitingInElevator$parseProto$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Elevator.Elv $msg;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6374p$;
    final /* synthetic */ ElevatorTask.WaitingInElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitingInElevator$parseProto$2(ElevatorTask.WaitingInElevator waitingInElevator, Elevator.Elv elv, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitingInElevator;
        this.$msg = elv;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitingInElevator$parseProto$2 elevatorTask$WaitingInElevator$parseProto$2 = new ElevatorTask$WaitingInElevator$parseProto$2(this.this$0, this.$msg, completion);
        elevatorTask$WaitingInElevator$parseProto$2.f6374p$ = (CoroutineScope) obj;
        return elevatorTask$WaitingInElevator$parseProto$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitingInElevator$parseProto$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0092  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        PathSegments pathSegments;
        List<PathSegment> segments;
        String curGoal;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6374p$;
            this.L$0 = coroutineScope2;
            this.label = 1;
            if (DelayKt.delay(100L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        ResultKt.throwOnFailure(obj);
                        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                        long seq = this.$msg.getSeq();
                        String str = ElevatorTask.this.robotID;
                        pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                            Intrinsics.throwNpe();
                        }
                        segments = pathSegments.getSegments();
                        if (segments == null) {
                            Intrinsics.throwNpe();
                        }
                        curGoal = segments.get(1).getCurGoal();
                        if (curGoal == null) {
                            Intrinsics.throwNpe();
                        }
                        access$getElvRemoteCommunicate$p.leaveElvAck(seq, str, curGoal);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.this$0.job = (Job) null;
                job2 = this.this$0.controlWheelJob;
                if (job2 != null) {
                    this.L$0 = coroutineScope;
                    this.label = 3;
                    if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                ElevatorCommunicateInterface access$getElvRemoteCommunicate$p2 = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                long seq2 = this.$msg.getSeq();
                String str2 = ElevatorTask.this.robotID;
                pathSegments = ElevatorTask.this.getPathSegments();
                if (pathSegments == null) {
                }
                segments = pathSegments.getSegments();
                if (segments == null) {
                }
                curGoal = segments.get(1).getCurGoal();
                if (curGoal == null) {
                }
                access$getElvRemoteCommunicate$p2.leaveElvAck(seq2, str2, curGoal);
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
        this.this$0.job = (Job) null;
        job2 = this.this$0.controlWheelJob;
        if (job2 != null) {
        }
        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p22 = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
        long seq22 = this.$msg.getSeq();
        String str22 = ElevatorTask.this.robotID;
        pathSegments = ElevatorTask.this.getPathSegments();
        if (pathSegments == null) {
        }
        segments = pathSegments.getSegments();
        if (segments == null) {
        }
        curGoal = segments.get(1).getCurGoal();
        if (curGoal == null) {
        }
        access$getElvRemoteCommunicate$p22.leaveElvAck(seq22, str22, curGoal);
        return Unit.INSTANCE;
    }
}
