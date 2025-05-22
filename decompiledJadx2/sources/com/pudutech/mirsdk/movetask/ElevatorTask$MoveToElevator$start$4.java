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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$start$4", m3970f = "ElevatorTask.kt", m3971i = {0, 0, 0}, m3972l = {670}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "currentTime", "dura"}, m3975s = {"L$0", "J$0", "J$1"})
/* loaded from: classes6.dex */
public final class ElevatorTask$MoveToElevator$start$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    long J$1;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6336p$;
    final /* synthetic */ ElevatorTask.MoveToElevator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevatorTask$MoveToElevator$start$4(ElevatorTask.MoveToElevator moveToElevator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveToElevator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ElevatorTask$MoveToElevator$start$4 elevatorTask$MoveToElevator$start$4 = new ElevatorTask$MoveToElevator$start$4(this.this$0, completion);
        elevatorTask$MoveToElevator$start$4.f6336p$ = (CoroutineScope) obj;
        return elevatorTask$MoveToElevator$start$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ElevatorTask$MoveToElevator$start$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00be -> B:5:0x00c1). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long elapsedRealtime;
        long j;
        Object obj2;
        ElevatorTask$MoveToElevator$start$4 elevatorTask$MoveToElevator$start$4;
        int i;
        int i2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6336p$;
            elapsedRealtime = SystemClock.elapsedRealtime();
            j = 0;
            ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$start$4.1
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
                    String informationForElevatorListeners;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    ElevatorUtilizeState elevatorUtilizeState = ElevatorUtilizeState.CallingElevator;
                    informationForElevatorListeners = ElevatorTask.this.informationForElevatorListeners("INCAB");
                    it.informElevatorUtilizeState(elevatorUtilizeState, informationForElevatorListeners);
                }
            });
            this.this$0.overtimeCallElv = false;
            obj2 = coroutine_suspended;
            elevatorTask$MoveToElevator$start$4 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i3 == 1) {
            long j2 = this.J$1;
            elapsedRealtime = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            elevatorTask$MoveToElevator$start$4 = this;
            j = SystemClock.elapsedRealtime() - elapsedRealtime;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                i2 = ElevatorTask.this.CALL_ELV_OVERTIME;
                if (j < i2) {
                    ElevatorCommunicateInterface access$getElvRemoteCommunicate$p = ElevatorTask.access$getElvRemoteCommunicate$p(ElevatorTask.this);
                    PathSegments pathSegments = ElevatorTask.this.getPathSegments();
                    if (pathSegments == null) {
                        Intrinsics.throwNpe();
                    }
                    String dstFloor = pathSegments.getDstFloor();
                    long j3 = ElevatorTask.this.seq;
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
                    access$getElvRemoteCommunicate$p.callElv("INCAB", dstFloor, j3, str, curGoal);
                    elevatorTask$MoveToElevator$start$4.L$0 = coroutineScope;
                    elevatorTask$MoveToElevator$start$4.J$0 = elapsedRealtime;
                    elevatorTask$MoveToElevator$start$4.J$1 = j;
                    elevatorTask$MoveToElevator$start$4.label = 1;
                    if (DelayKt.delay(2000L, elevatorTask$MoveToElevator$start$4) == obj2) {
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
                    elevatorTask$MoveToElevator$start$4.this$0.overtimeCallElv = true;
                    Pdlog.m3273d(ElevatorTask.this.TAG, "call elevator failed. not receive ack or enter cmd when robot send INCAB");
                    ElevatorTask.this.getElevatorListener().notify(new Function2<ElevatorRequestListener, String, Unit>() { // from class: com.pudutech.mirsdk.movetask.ElevatorTask$MoveToElevator$start$4.2
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
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
