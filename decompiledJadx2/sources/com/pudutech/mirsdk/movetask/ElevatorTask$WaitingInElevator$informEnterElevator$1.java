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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElevatorTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$informEnterElevator$1", m3970f = "ElevatorTask.kt", m3971i = {0, 0, 0, 1, 1, 1}, m3972l = {796, 806}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "currentTime", "dura", "$this$launch", "currentTime", "dura"}, m3975s = {"L$0", "J$0", "J$1", "L$0", "J$0", "J$1"})
/* loaded from: classes6.dex */
public final class ElevatorTask$WaitingInElevator$informEnterElevator$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $floor;
    long J$0;
    long J$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6370p$;
    final /* synthetic */ ElevatorTask.WaitingInElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$WaitingInElevator$informEnterElevator$1(ElevatorTask.WaitingInElevator waitingInElevator, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = waitingInElevator;
        this.$floor = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$WaitingInElevator$informEnterElevator$1 elevatorTask$WaitingInElevator$informEnterElevator$1 = new ElevatorTask$WaitingInElevator$informEnterElevator$1(this.this$0, this.$floor, completion);
        elevatorTask$WaitingInElevator$informEnterElevator$1.f6370p$ = (CoroutineScope) obj;
        return elevatorTask$WaitingInElevator$informEnterElevator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$WaitingInElevator$informEnterElevator$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00fc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00ad -> B:26:0x00b0). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long elapsedRealtime;
        long j;
        Object obj2;
        ElevatorTask$WaitingInElevator$informEnterElevator$1 elevatorTask$WaitingInElevator$informEnterElevator$1;
        CoroutineScope coroutineScope2;
        long j2;
        int i;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6370p$;
            elapsedRealtime = SystemClock.elapsedRealtime();
            j = 0;
            this.this$0.overtimeEnterdElv = false;
            obj2 = coroutine_suspended;
            elevatorTask$WaitingInElevator$informEnterElevator$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            long j3 = j;
            coroutineScope2 = coroutineScope;
            j2 = j3;
            while (CoroutineScopeKt.isActive(coroutineScope2)) {
            }
            return Unit.INSTANCE;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                j2 = this.J$1;
                elapsedRealtime = this.J$0;
                coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                elevatorTask$WaitingInElevator$informEnterElevator$1 = this;
                while (CoroutineScopeKt.isActive(coroutineScope2)) {
                    ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                    String str = elevatorTask$WaitingInElevator$informEnterElevator$1.$floor;
                    long j4 = ElevatorTask.this.seq;
                    String str2 = ElevatorTask.this.robotID;
                    PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    List<PathSegment> segments = pathSegments.getSegments();
                    if (segments == null) {
                        Intrinsics.throwNpe();
                    }
                    String curGoal = segments.get(1).getCurGoal();
                    if (curGoal == null) {
                        Intrinsics.throwNpe();
                    }
                    access$getElvRemoteCommunicate$p.elvEntered(str, j4, str2, curGoal);
                    elevatorTask$WaitingInElevator$informEnterElevator$1.L$0 = coroutineScope2;
                    elevatorTask$WaitingInElevator$informEnterElevator$1.J$0 = elapsedRealtime;
                    elevatorTask$WaitingInElevator$informEnterElevator$1.J$1 = j2;
                    elevatorTask$WaitingInElevator$informEnterElevator$1.label = 2;
                    if (DelayKt.delay(200L, elevatorTask$WaitingInElevator$informEnterElevator$1) == obj2) {
                        return obj2;
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        long j5 = this.J$1;
        elapsedRealtime = this.J$0;
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        obj2 = coroutine_suspended;
        elevatorTask$WaitingInElevator$informEnterElevator$1 = this;
        j = SystemClock.elapsedRealtime() - elapsedRealtime;
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            i2 = ElevatorTask.this.CALL_ELV_OVERTIME;
            if (j < i2) {
                ElevatorCommunicateInterface access$getElvRemoteCommunicate$p2 = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                String str3 = elevatorTask$WaitingInElevator$informEnterElevator$1.$floor;
                long j6 = ElevatorTask.this.seq;
                String str4 = ElevatorTask.this.robotID;
                PathSegments pathSegments2 = ElevatorTask.this.getPathSegments();
                if (pathSegments2 == null) {
                    Intrinsics.throwNpe();
                }
                List<PathSegment> segments2 = pathSegments2.getSegments();
                if (segments2 == null) {
                    Intrinsics.throwNpe();
                }
                String curGoal2 = segments2.get(1).getCurGoal();
                if (curGoal2 == null) {
                    Intrinsics.throwNpe();
                }
                access$getElvRemoteCommunicate$p2.elvEntered(str3, j6, str4, curGoal2);
                elevatorTask$WaitingInElevator$informEnterElevator$1.L$0 = coroutineScope;
                elevatorTask$WaitingInElevator$informEnterElevator$1.J$0 = elapsedRealtime;
                elevatorTask$WaitingInElevator$informEnterElevator$1.J$1 = j;
                elevatorTask$WaitingInElevator$informEnterElevator$1.label = 1;
                if (DelayKt.delay(2000L, elevatorTask$WaitingInElevator$informEnterElevator$1) == obj2) {
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
                elevatorTask$WaitingInElevator$informEnterElevator$1.this$0.overtimeEnterdElv = true;
                Pdlog.m3273d(ElevatorTask.this.TAG, "notice entered elevator failed. not receive entered ack");
                ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$WaitingInElevator$informEnterElevator$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ElevatorRequestListener elevatorRequestListener, String str5) {
                        invoke2(elevatorRequestListener, str5);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ElevatorRequestListener it, String str5) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                        it.informElevatorUtilizeState(ElevatorUtilizeState.OvertimeEnteredAck, ElevatorTask.informationForElevatorListeners$default(ElevatorTask.this, null, 1, null));
                    }
                });
            }
        }
        long j32 = j;
        coroutineScope2 = coroutineScope;
        j2 = j32;
        while (CoroutineScopeKt.isActive(coroutineScope2)) {
        }
        return Unit.INSTANCE;
    }
}
