package com.pudutech.mirsdk.movetask;

import android.os.SystemClock;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1", m3970f = "ElevatorTask.kt", m3971i = {0, 0}, m3972l = {1293}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "startJobTimer"}, m3975s = {"L$0", "J$0"})
/* loaded from: classes6.dex */
public final class ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6343p$;
    final /* synthetic */ ElevatorTask.MoveToFinalGoal this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1(ElevatorTask.MoveToFinalGoal moveToFinalGoal, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveToFinalGoal;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1 elevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1 = new ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1(this.this$0, completion);
        elevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1.f6343p$ = (CoroutineScope) obj;
        return elevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$MoveToFinalGoal$startLeftElvNoticeJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long elapsedRealtime;
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6343p$;
            elapsedRealtime = SystemClock.elapsedRealtime();
            coroutineScope = coroutineScope2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            elapsedRealtime = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope) && SystemClock.elapsedRealtime() - elapsedRealtime < ElevatorTask.this.MOVE_OVERTIME) {
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
            access$getElvRemoteCommunicate$p.elvLeft(dstFloor, j, str, curGoal);
            this.L$0 = coroutineScope;
            this.J$0 = elapsedRealtime;
            this.label = 1;
            if (DelayKt.delay(200L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.this$0.job = (Job) null;
        return Unit.INSTANCE;
    }
}
