package com.pudutech.mirsdk.movetask;

import android.bluetooth.BluetoothAdapter;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GatePassTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GatePassTask$StopBeforeGate$start$1", m3970f = "GatePassTask.kt", m3971i = {0, 1, 2, 3}, m3972l = {TypedValues.Motion.TYPE_EASING, TypedValues.Motion.TYPE_ANIMATE_RELATIVE_TO, TypedValues.Motion.TYPE_QUANTIZE_INTERPOLATOR_ID, 614}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class GatePassTask$StopBeforeGate$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6388p$;
    final /* synthetic */ GatePassTask.StopBeforeGate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GatePassTask$StopBeforeGate$start$1(GatePassTask.StopBeforeGate stopBeforeGate, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stopBeforeGate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GatePassTask$StopBeforeGate$start$1 gatePassTask$StopBeforeGate$start$1 = new GatePassTask$StopBeforeGate$start$1(this.this$0, completion);
        gatePassTask$StopBeforeGate$start$1.f6388p$ = (CoroutineScope) obj;
        return gatePassTask$StopBeforeGate$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GatePassTask$StopBeforeGate$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01fb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0211  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x01f9 -> B:8:0x01fc). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        GatePassTask$StopBeforeGate$start$1 gatePassTask$StopBeforeGate$start$1;
        ScheduleInterface scheduler;
        DestinationWithAccRange findNextGateOnPath;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        Object obj3 = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6388p$;
            obj2 = coroutine_suspended;
            gatePassTask$StopBeforeGate$start$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            gatePassTask$StopBeforeGate$start$1 = this;
            BluetoothAdapter.getDefaultAdapter().enable();
            gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
            gatePassTask$StopBeforeGate$start$1.label = 2;
            if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, gatePassTask$StopBeforeGate$start$1) == obj2) {
            }
            if (GatePassTask.this.getManualMode()) {
            }
            gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
            gatePassTask$StopBeforeGate$start$1.label = 4;
            if (DelayKt.delay(1000L, gatePassTask$StopBeforeGate$start$1) == obj2) {
            }
            MirCoreInterface mirCoreInterface = GatePassTask.this.getCoreService().getInterface();
            if (mirCoreInterface != null) {
            }
            if (scheduler == null) {
            }
            findNextGateOnPath = scheduler.findNextGateOnPath();
            if (!(!Intrinsics.areEqual(GatePassTask.this.getGateMAC(), findNextGateOnPath.getId()))) {
            }
        } else if (i == 2) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            gatePassTask$StopBeforeGate$start$1 = this;
            if (GatePassTask.this.getManualMode()) {
            }
            gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
            gatePassTask$StopBeforeGate$start$1.label = 4;
            if (DelayKt.delay(1000L, gatePassTask$StopBeforeGate$start$1) == obj2) {
            }
            MirCoreInterface mirCoreInterface2 = GatePassTask.this.getCoreService().getInterface();
            if (mirCoreInterface2 != null) {
            }
            if (scheduler == null) {
            }
            findNextGateOnPath = scheduler.findNextGateOnPath();
            if (!(!Intrinsics.areEqual(GatePassTask.this.getGateMAC(), findNextGateOnPath.getId()))) {
            }
        } else if (i == 3) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            gatePassTask$StopBeforeGate$start$1 = this;
            gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
            gatePassTask$StopBeforeGate$start$1.label = 4;
            if (DelayKt.delay(1000L, gatePassTask$StopBeforeGate$start$1) == obj2) {
            }
            MirCoreInterface mirCoreInterface22 = GatePassTask.this.getCoreService().getInterface();
            if (mirCoreInterface22 != null) {
            }
            if (scheduler == null) {
            }
            findNextGateOnPath = scheduler.findNextGateOnPath();
            if (!(!Intrinsics.areEqual(GatePassTask.this.getGateMAC(), findNextGateOnPath.getId()))) {
            }
        } else if (i == 4) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            gatePassTask$StopBeforeGate$start$1 = this;
            MirCoreInterface mirCoreInterface222 = GatePassTask.this.getCoreService().getInterface();
            scheduler = mirCoreInterface222 != null ? mirCoreInterface222.getScheduler() : null;
            if (scheduler == null) {
                Intrinsics.throwNpe();
            }
            findNextGateOnPath = scheduler.findNextGateOnPath();
            if (!(!Intrinsics.areEqual(GatePassTask.this.getGateMAC(), findNextGateOnPath.getId()))) {
                obj3 = null;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    if (!(GatePassTask.access$getMoveState$p(GatePassTask.this) instanceof GatePassTask.StopBeforeGate) || GatePassTask.this.getIsPause()) {
                        return Unit.INSTANCE;
                    }
                    GatePassTask.this.notifyListener(AccessDoorControlState.WaitingOpenAccessDoor);
                    if (!GatePassTask.this.getManualMode()) {
                        int connectionState = GatePassTask.access$getGateController$p(GatePassTask.this).getConnectionState(GatePassTask.this.getGateMAC());
                        if (connectionState == 0) {
                            Pdlog.m3273d(GatePassTask.this.TAG, "StopBeforeGate Try to connect gate " + GatePassTask.this.getGateMAC());
                            GatePassTask.access$getGateController$p(GatePassTask.this).connect(GatePassTask.this.getGateMAC());
                        } else if (connectionState == 1) {
                            Pdlog.m3273d(GatePassTask.this.TAG, "StopBeforeGate Connecting gate " + GatePassTask.this.getGateMAC() + "state :" + GatePassTask.access$getGateController$p(GatePassTask.this).getConnectionState(GatePassTask.this.getGateMAC()));
                            GatePassTask gatePassTask = GatePassTask.this;
                            gatePassTask.setConnectTimeout(gatePassTask.getConnectTimeout() + 1);
                            if (GatePassTask.this.getConnectTimeout() >= 7) {
                                GatePassTask.this.notifyListener(AccessDoorControlState.CallingDoorFail);
                                Pdlog.m3274e(GatePassTask.this.TAG, "connect timeout " + GatePassTask.this.getConnectTimeout());
                                GatePassTask.this.setConnectTimeout(0);
                                GatePassTask.access$getGateController$p(GatePassTask.this).disconnect(GatePassTask.this.getGateMAC());
                                BluetoothAdapter.getDefaultAdapter().disable();
                                gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
                                gatePassTask$StopBeforeGate$start$1.label = 1;
                                if (DelayKt.delay(500L, gatePassTask$StopBeforeGate$start$1) == obj2) {
                                    return obj2;
                                }
                                BluetoothAdapter.getDefaultAdapter().enable();
                                gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
                                gatePassTask$StopBeforeGate$start$1.label = 2;
                                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, gatePassTask$StopBeforeGate$start$1) == obj2) {
                                    return obj2;
                                }
                            }
                        } else if (connectionState == 2) {
                            Pdlog.m3273d(GatePassTask.this.TAG, "StopBeforeGate Try to open gate " + GatePassTask.this.getGateMAC());
                            GatePassTask.openGateWithDir$default(GatePassTask.this, 0, 1, obj3);
                        }
                    }
                    if (GatePassTask.this.getManualMode()) {
                        gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
                        gatePassTask$StopBeforeGate$start$1.label = 3;
                        if (DelayKt.delay(1000L, gatePassTask$StopBeforeGate$start$1) == obj2) {
                            return obj2;
                        }
                    }
                    gatePassTask$StopBeforeGate$start$1.L$0 = coroutineScope;
                    gatePassTask$StopBeforeGate$start$1.label = 4;
                    if (DelayKt.delay(1000L, gatePassTask$StopBeforeGate$start$1) == obj2) {
                        return obj2;
                    }
                    MirCoreInterface mirCoreInterface2222 = GatePassTask.this.getCoreService().getInterface();
                    if (mirCoreInterface2222 != null) {
                    }
                    if (scheduler == null) {
                    }
                    findNextGateOnPath = scheduler.findNextGateOnPath();
                    if (!(!Intrinsics.areEqual(GatePassTask.this.getGateMAC(), findNextGateOnPath.getId()))) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                GatePassTask.this.setError(true);
                GatePassTask.this.lockWheel(false);
                Pdlog.m3274e(GatePassTask.this.TAG, "new gate " + findNextGateOnPath.getId() + " different form " + GatePassTask.this.getGateMAC());
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
