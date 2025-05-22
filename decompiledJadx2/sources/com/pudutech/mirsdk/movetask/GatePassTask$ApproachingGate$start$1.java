package com.pudutech.mirsdk.movetask;

import android.bluetooth.BluetoothAdapter;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.AccessDoorControlState;
import com.pudutech.mirsdk.map.elements.FireFoxGateSource;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.movetask.GatePassTask$ApproachingGate$start$1", m3970f = "GatePassTask.kt", m3971i = {0, 1, 2, 3, 4, 4, 4, 5, 5}, m3972l = {454, 456, 479, 481, 515, 523}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch", "$this$launch", "gate", "runTime", "$this$launch", "gate"}, m3975s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$1", "J$0", "L$0", "L$1"})
/* loaded from: classes6.dex */
public final class GatePassTask$ApproachingGate$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $startMoveTime;
    long J$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6383p$;
    final /* synthetic */ GatePassTask.ApproachingGate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GatePassTask$ApproachingGate$start$1(GatePassTask.ApproachingGate approachingGate, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = approachingGate;
        this.$startMoveTime = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GatePassTask$ApproachingGate$start$1 gatePassTask$ApproachingGate$start$1 = new GatePassTask$ApproachingGate$start$1(this.this$0, this.$startMoveTime, completion);
        gatePassTask$ApproachingGate$start$1.f6383p$ = (CoroutineScope) obj;
        return gatePassTask$ApproachingGate$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GatePassTask$ApproachingGate$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01bb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0087 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x02ab -> B:9:0x0089). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        GatePassTask$ApproachingGate$start$1 gatePassTask$ApproachingGate$start$1;
        ScheduleInterface scheduler;
        DestinationWithAccRange findNextGateOnPath;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6383p$;
                GatePassTask.this.moveToPoint(false);
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                Intrinsics.checkExpressionValueIsNotNull(defaultAdapter, "BluetoothAdapter.getDefaultAdapter()");
                if (!defaultAdapter.isEnabled()) {
                    BluetoothAdapter.getDefaultAdapter().enable();
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (DelayKt.delay(2700L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                this.L$0 = coroutineScope;
                this.label = 2;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                gatePassTask$ApproachingGate$start$1 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    if (!(GatePassTask.access$getMoveState$p(GatePassTask.this) instanceof GatePassTask.ApproachingGate) || GatePassTask.this.getIsPause()) {
                        return Unit.INSTANCE;
                    }
                    if (!GatePassTask.this.getManualMode()) {
                        if (GatePassTask.access$getGateController$p(GatePassTask.this).getConnectionState(GatePassTask.this.getGateMAC()) != 1 && GatePassTask.access$getGateController$p(GatePassTask.this).getConnectionState(GatePassTask.this.getGateMAC()) != 2) {
                            Pdlog.m3273d(GatePassTask.this.TAG, "ApproachingGate Try to connect gate " + GatePassTask.this.getGateMAC());
                            GatePassTask.access$getGateController$p(GatePassTask.this).connect(GatePassTask.this.getGateMAC());
                        } else if (GatePassTask.access$getGateController$p(GatePassTask.this).getConnectionState(GatePassTask.this.getGateMAC()) == 1) {
                            GatePassTask gatePassTask = GatePassTask.this;
                            gatePassTask.setConnectTimeout(gatePassTask.getConnectTimeout() + 1);
                            if (GatePassTask.this.getConnectTimeout() >= 7) {
                                GatePassTask.this.notifyListener(AccessDoorControlState.CallingDoorFail);
                                Pdlog.m3274e(GatePassTask.this.TAG, "connect timeout " + GatePassTask.this.getConnectTimeout());
                                GatePassTask.this.setConnectTimeout(0);
                                GatePassTask.access$getGateController$p(GatePassTask.this).disconnect(GatePassTask.this.getGateMAC());
                                BluetoothAdapter.getDefaultAdapter().disable();
                                gatePassTask$ApproachingGate$start$1.L$0 = coroutineScope;
                                gatePassTask$ApproachingGate$start$1.label = 3;
                                if (DelayKt.delay(500L, gatePassTask$ApproachingGate$start$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                BluetoothAdapter.getDefaultAdapter().enable();
                                gatePassTask$ApproachingGate$start$1.L$0 = coroutineScope;
                                gatePassTask$ApproachingGate$start$1.label = 4;
                                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, gatePassTask$ApproachingGate$start$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    }
                    MirCoreInterface mirCoreInterface = GatePassTask.this.getCoreService().getInterface();
                    scheduler = mirCoreInterface != null ? mirCoreInterface.getScheduler() : null;
                    if (scheduler == null) {
                        Intrinsics.throwNpe();
                    }
                    findNextGateOnPath = scheduler.findNextGateOnPath();
                    Pdlog.m3275i(GatePassTask.this.TAG, "ApproachingGate distance between gate : " + findNextGateOnPath.getRange());
                    if (findNextGateOnPath.getRange() == -1.0d) {
                        String id = findNextGateOnPath.getId();
                        Pdlog.m3273d(GatePassTask.this.TAG, "ApproachingGate recheck gateId:" + id + " target:" + GatePassTask.this.getGateMAC());
                        Pdlog.m3274e(GatePassTask.this.TAG, "gate lost");
                        GatePassTask.this.notifyListener(AccessDoorControlState.CallingDoorFail);
                        GatePassTask.this.setError(true);
                        return Unit.INSTANCE;
                    }
                    double range = findNextGateOnPath.getRange();
                    FireFoxGateSource fireFoxGateSource = GatePassTask.this.getAction().getAtlas().getMapDecode().getFireFoxGates().get(GatePassTask.this.getGateMAC());
                    if (fireFoxGateSource == null) {
                        Intrinsics.throwNpe();
                    }
                    if (range < fireFoxGateSource.getUp_stop_distance() || findNextGateOnPath.getRange() < 1) {
                        long elapsedRealtime = SystemClock.elapsedRealtime() - gatePassTask$ApproachingGate$start$1.$startMoveTime;
                        long j = 300;
                        if (elapsedRealtime < j) {
                            Pdlog.m3273d(GatePassTask.this.TAG, "runTime is " + elapsedRealtime + " ms need delay");
                            gatePassTask$ApproachingGate$start$1.L$0 = coroutineScope;
                            gatePassTask$ApproachingGate$start$1.L$1 = findNextGateOnPath;
                            gatePassTask$ApproachingGate$start$1.J$0 = elapsedRealtime;
                            gatePassTask$ApproachingGate$start$1.label = 5;
                            if (DelayKt.delay(j - elapsedRealtime, gatePassTask$ApproachingGate$start$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        Pdlog.m3273d(GatePassTask.this.TAG, "change mode to stop");
                        GatePassTask.this.moveState = new GatePassTask.StopBeforeGate();
                        GatePassTask.access$getMoveState$p(GatePassTask.this).start();
                        return Unit.INSTANCE;
                    }
                    gatePassTask$ApproachingGate$start$1.L$0 = coroutineScope;
                    gatePassTask$ApproachingGate$start$1.L$1 = findNextGateOnPath;
                    gatePassTask$ApproachingGate$start$1.label = 6;
                    if (DelayKt.delay(1000L, gatePassTask$ApproachingGate$start$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
                break;
            case 1:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                this.L$0 = coroutineScope;
                this.label = 2;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                }
                gatePassTask$ApproachingGate$start$1 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                break;
            case 2:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                gatePassTask$ApproachingGate$start$1 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                break;
            case 3:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                gatePassTask$ApproachingGate$start$1 = this;
                BluetoothAdapter.getDefaultAdapter().enable();
                gatePassTask$ApproachingGate$start$1.L$0 = coroutineScope;
                gatePassTask$ApproachingGate$start$1.label = 4;
                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, gatePassTask$ApproachingGate$start$1) == coroutine_suspended) {
                }
                MirCoreInterface mirCoreInterface2 = GatePassTask.this.getCoreService().getInterface();
                if (mirCoreInterface2 != null) {
                }
                if (scheduler == null) {
                }
                findNextGateOnPath = scheduler.findNextGateOnPath();
                Pdlog.m3275i(GatePassTask.this.TAG, "ApproachingGate distance between gate : " + findNextGateOnPath.getRange());
                if (findNextGateOnPath.getRange() == -1.0d) {
                }
                break;
            case 4:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                gatePassTask$ApproachingGate$start$1 = this;
                MirCoreInterface mirCoreInterface22 = GatePassTask.this.getCoreService().getInterface();
                if (mirCoreInterface22 != null) {
                }
                if (scheduler == null) {
                }
                findNextGateOnPath = scheduler.findNextGateOnPath();
                Pdlog.m3275i(GatePassTask.this.TAG, "ApproachingGate distance between gate : " + findNextGateOnPath.getRange());
                if (findNextGateOnPath.getRange() == -1.0d) {
                }
                break;
            case 5:
                long j2 = this.J$0;
                ResultKt.throwOnFailure(obj);
                gatePassTask$ApproachingGate$start$1 = this;
                Pdlog.m3273d(GatePassTask.this.TAG, "change mode to stop");
                GatePassTask.this.moveState = new GatePassTask.StopBeforeGate();
                GatePassTask.access$getMoveState$p(GatePassTask.this).start();
                return Unit.INSTANCE;
            case 6:
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                gatePassTask$ApproachingGate$start$1 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
