package com.pudutech.mirsdk.movetask;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$runCallElvJob$1", m3970f = "ElevatorTask.kt", m3971i = {0, 1, 1, 1, 2, 2, 2}, m3972l = {420, 426, 434}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "currentTime", "dura", "$this$launch", "currentTime", "dura"}, m3975s = {"L$0", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
/* loaded from: classes6.dex */
public final class ElevatorTask$WaitElevator$runCallElvJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayTime;
    long J$0;
    long J$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6363p$;
    final /* synthetic */ ElevatorTask.WaitElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitElevator$runCallElvJob$1(ElevatorTask.WaitElevator waitElevator, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitElevator;
        this.$delayTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitElevator$runCallElvJob$1 elevatorTask$WaitElevator$runCallElvJob$1 = new ElevatorTask$WaitElevator$runCallElvJob$1(this.this$0, this.$delayTime, completion);
        elevatorTask$WaitElevator$runCallElvJob$1.f6363p$ = (CoroutineScope) obj;
        return elevatorTask$WaitElevator$runCallElvJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitElevator$runCallElvJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f8  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00e5 -> B:13:0x00e8). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long elapsedRealtime;
        long j;
        Object obj2;
        ElevatorTask$WaitElevator$runCallElvJob$1 elevatorTask$WaitElevator$runCallElvJob$1;
        int i;
        Job job;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6363p$;
            long j2 = this.$delayTime;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(j2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 == 3) {
                        long j3 = this.J$1;
                        long j4 = this.J$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j5 = this.J$1;
                elapsedRealtime = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                elevatorTask$WaitElevator$runCallElvJob$1 = this;
                j = SystemClock.elapsedRealtime() - elapsedRealtime;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    i2 = ElevatorTask.this.CALL_ELV_OVERTIME;
                    if (j < i2) {
                        ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                        PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                        if (pathSegments == null) {
                            Intrinsics.throwNpe();
                        }
                        String curFloor = pathSegments.getCurFloor();
                        PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                        if (pathSegments2 == null) {
                            Intrinsics.throwNpe();
                        }
                        String dstFloor = pathSegments2.getDstFloor();
                        long j6 = ElevatorTask.this.seq;
                        String str = ElevatorTask.this.robotID;
                        PathSegments pathSegments3 = ElevatorTask.this.getPathSegments();
                        if (pathSegments3 == null) {
                            Intrinsics.throwNpe();
                        }
                        List<PathSegment> segments = pathSegments3.getSegments();
                        if (segments == null) {
                            Intrinsics.throwNpe();
                        }
                        String curGoal = segments.get(1).getCurGoal();
                        if (curGoal == null) {
                            Intrinsics.throwNpe();
                        }
                        access$getElvRemoteCommunicate$p.callElv(curFloor, dstFloor, j6, str, curGoal);
                        elevatorTask$WaitElevator$runCallElvJob$1.L$0 = coroutineScope;
                        elevatorTask$WaitElevator$runCallElvJob$1.J$0 = elapsedRealtime;
                        elevatorTask$WaitElevator$runCallElvJob$1.J$1 = j;
                        elevatorTask$WaitElevator$runCallElvJob$1.label = 2;
                        if (DelayKt.delay(2000L, elevatorTask$WaitElevator$runCallElvJob$1) == obj2) {
                            return obj2;
                        }
                        j = SystemClock.elapsedRealtime() - elapsedRealtime;
                        if (CoroutineScopeKt.isActive(coroutineScope)) {
                        }
                    }
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    i = ElevatorTask.this.CALL_ELV_OVERTIME;
                    if (j >= i) {
                        elevatorTask$WaitElevator$runCallElvJob$1.this$0.overtimeCallElv = true;
                        Pdlog.m3273d(ElevatorTask.this.TAG, "call elevator failed. not receive ack or enter cmd");
                        ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitElevator$runCallElvJob$1.1
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
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                                it.informElevatorUtilizeState(ElevatorUtilizeState.OvertimeCallElevator, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                            }
                        });
                        job = elevatorTask$WaitElevator$runCallElvJob$1.this$0.controlWheelJob;
                        if (job != null) {
                            elevatorTask$WaitElevator$runCallElvJob$1.L$0 = coroutineScope;
                            elevatorTask$WaitElevator$runCallElvJob$1.J$0 = elapsedRealtime;
                            elevatorTask$WaitElevator$runCallElvJob$1.J$1 = j;
                            elevatorTask$WaitElevator$runCallElvJob$1.label = 3;
                            if (JobKt.cancelAndJoin(job, elevatorTask$WaitElevator$runCallElvJob$1) == obj2) {
                                return obj2;
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        elapsedRealtime = SystemClock.elapsedRealtime();
        j = 0;
        this.this$0.overtimeCallElv = false;
        obj2 = coroutine_suspended;
        elevatorTask$WaitElevator$runCallElvJob$1 = this;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
        }
        return Unit.INSTANCE;
    }
}
