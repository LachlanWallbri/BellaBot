package com.pudutech.mirsdk.movetask;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.map.elements.FireFoxGateSource;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.movetask.GatePassTask;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: GatePassTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GatePassTask$PassGate$start$1", m3970f = "GatePassTask.kt", m3971i = {0, 0, 0}, m3972l = {717}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "distance", "gate"}, m3975s = {"L$0", "D$0", "L$1"})
/* loaded from: classes6.dex */
final class GatePassTask$PassGate$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    double D$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6386p$;
    final /* synthetic */ GatePassTask.PassGate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GatePassTask$PassGate$start$1(GatePassTask.PassGate passGate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = passGate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GatePassTask$PassGate$start$1 gatePassTask$PassGate$start$1 = new GatePassTask$PassGate$start$1(this.this$0, completion);
        gatePassTask$PassGate$start$1.f6386p$ = (CoroutineScope) obj;
        return gatePassTask$PassGate$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GatePassTask$PassGate$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6386p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            double d = this.D$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            if (!(GatePassTask.access$getMoveState$p(GatePassTask.this) instanceof GatePassTask.PassGate) || GatePassTask.this.getIsPause()) {
                return Unit.INSTANCE;
            }
            MirCoreInterface mirCoreInterface = GatePassTask.this.getCoreService().getInterface();
            ScheduleInterface scheduler = mirCoreInterface != null ? mirCoreInterface.getScheduler() : null;
            if (scheduler == null) {
                Intrinsics.throwNpe();
            }
            double range = scheduler.findNextGateOnPath().getRange();
            MirCoreInterface mirCoreInterface2 = GatePassTask.this.getCoreService().getInterface();
            ScheduleInterface scheduler2 = mirCoreInterface2 != null ? mirCoreInterface2.getScheduler() : null;
            if (scheduler2 == null) {
                Intrinsics.throwNpe();
            }
            String id = scheduler2.findNextGateOnPath().getId();
            Pdlog.m3273d(GatePassTask.this.TAG, "PassGate : distance = " + range + " , get next gate id = " + id);
            if (range == -1.0d || !StringsKt.equals(id, GatePassTask.this.getGateMAC(), true)) {
                double distanceFromGate = GatePassTask.this.distanceFromGate();
                Pdlog.m3273d(GatePassTask.this.TAG, "gate away gate is " + distanceFromGate);
                FireFoxGateSource fireFoxGateSource = GatePassTask.this.getAction().getAtlas().getMapDecode().getFireFoxGates().get(GatePassTask.this.getGateMAC());
                if (fireFoxGateSource == null) {
                    Intrinsics.throwNpe();
                }
                if (distanceFromGate > fireFoxGateSource.getDown_stop_distance()) {
                    GatePassTask.this.notifyListener(AccessDoorControlState.LeavingAccessDoor);
                    GatePassTask.access$getGateController$p(GatePassTask.this).disconnect(GatePassTask.this.getGateMAC());
                    if (Intrinsics.areEqual(id, "")) {
                        Pdlog.m3273d(GatePassTask.this.TAG, "gate pass success, task finish");
                        this.this$0.setLeaveGate(true);
                    } else {
                        this.this$0.setLeaveGate(false);
                        GatePassTask.this.setGateMAC(id);
                        Pdlog.m3274e(GatePassTask.this.TAG, "Start GatePass Action , Gate " + GatePassTask.this.getGateMAC() + " will be pass");
                        GatePassTask.this.startMoveAction();
                    }
                    return Unit.INSTANCE;
                }
            }
            if (!GatePassTask.this.getManualMode()) {
                GatePassTask.openGateWithDir$default(GatePassTask.this, 0, 1, null);
            }
            this.L$0 = coroutineScope;
            this.D$0 = range;
            this.L$1 = id;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
