package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.aidl.serialize.Destination;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.base.SDKRobotStateKt;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.charge.ChargeMessageParser;
import com.pudutech.mirsdk.charge.ChargeUpdateTaskManager;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.mircore.DockerEstimateTransformListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.ScheduleInterface;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.PathSegments;
import com.pudutech.mirsdk.movetask.GeneralTask;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0002\u001f8\u0018\u0000 ]2\u00020\u0001:\u0006\\]^_`aB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010=\u001a\u00020/J\u0014\u0010>\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u001a0?H\u0016J\b\u0010A\u001a\u00020/H\u0002J\u0010\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\u0010\u0010D\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\u0010\u0010E\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\u0010\u0010F\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\u0010\u0010G\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\u0006\u0010H\u001a\u00020\u001aJ\u0006\u0010I\u001a\u00020\u001aJ\u0010\u0010J\u001a\u00020/2\u0006\u0010K\u001a\u00020\u001aH\u0002J\u0016\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020\tJ\u0018\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u000bH\u0016J\b\u0010T\u001a\u00020/H\u0016J\b\u0010U\u001a\u00020/H\u0016J\b\u0010V\u001a\u00020/H\u0016J\u0006\u0010W\u001a\u00020/J\u0010\u0010X\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\b\u0010Y\u001a\u00020/H\u0016J\u0010\u0010Z\u001a\u00020/2\u0006\u0010C\u001a\u00020\u000bH\u0002J\b\u0010[\u001a\u00020/H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u0012\u0010!\u001a\u00060\"R\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010'\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0-X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00100\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010)\"\u0004\b2\u0010+R\u001c\u00103\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010)\"\u0004\b5\u0010+R\u000e\u00106\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010)\"\u0004\b<\u0010+¨\u0006b"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask;", "Lcom/pudutech/mirsdk/movetask/MoveTask;", "action", "Lcom/pudutech/mirsdk/MoveAction;", "coreService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/mircore/MirCoreInterface;", "(Lcom/pudutech/mirsdk/MoveAction;Lcom/pudutech/base/architecture/AIDLConnection;)V", "ROBOT_IN_CHARING_POINT", "", "TAG", "", "callback", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "connectJob", "Lkotlinx/coroutines/Job;", "dockListener", "Lcom/pudutech/mirsdk/mircore/DockerEstimateTransformListener$Stub;", "getDockListener", "()Lcom/pudutech/mirsdk/mircore/DockerEstimateTransformListener$Stub;", "gateID", "getGateID", "()Ljava/lang/String;", "setGateID", "(Ljava/lang/String;)V", "isNeedCallStart", "", "isRecChargeStartMsg", "isRecStatusReply", "isRequestBluetooth", "messageParserListener", "com/pudutech/mirsdk/movetask/GeneralTask$messageParserListener$1", "Lcom/pudutech/mirsdk/movetask/GeneralTask$messageParserListener$1;", "moveState", "Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "parser", "Lcom/pudutech/mirsdk/charge/ChargeMessageParser;", "reConnectCount", "", "startChargeJob", "getStartChargeJob", "()Lkotlinx/coroutines/Job;", "setStartChargeJob", "(Lkotlinx/coroutines/Job;)V", "stateNoticeQueue", "Ljava/util/concurrent/ArrayBlockingQueue;", "Lkotlin/Function0;", "", "stateReqJob", "getStateReqJob", "setStateReqJob", "stopChargeJob", "getStopChargeJob", "setStopChargeJob", "targetMac", "updateTaskListener", "com/pudutech/mirsdk/movetask/GeneralTask$updateTaskListener$1", "Lcom/pudutech/mirsdk/movetask/GeneralTask$updateTaskListener$1;", "versionJob", "getVersionJob", "setVersionJob", "assignChargingPile", "checkNavigationMode", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "disconnectChargePile", "doBluetoothChargeState", "mac", "doBluetoothStartCharge", "doBluetoothStopCharge", "doBluetoothVersionCheck", "initCallBack", "isGoChargingTask", "isLeaveChargingTask", "lockWheel", "isLock", "onSpeed", "line", "angular", "onStateChange", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "state", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "pause", "reset", "resume", "setCruiseMode", "startConnectChargePile", "startMoveAction", "startUpdateTask", "stopAllJob", "AssignChargingPile", "Companion", "GeneralState", "GoBluetoothChargingState", "GoChargingState", "MoveState", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GeneralTask extends MoveTask {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int goChargingTimes;
    private final double ROBOT_IN_CHARING_POINT;
    private final String TAG;
    private BluetoothBleListener callback;
    private Job connectJob;
    private final DockerEstimateTransformListener.Stub dockListener;
    private String gateID;
    private boolean isNeedCallStart;
    private boolean isRecChargeStartMsg;
    private volatile boolean isRecStatusReply;
    private volatile boolean isRequestBluetooth;
    private final GeneralTask$messageParserListener$1 messageParserListener;
    private volatile MoveState moveState;
    private ChargeMessageParser parser;
    private int reConnectCount;
    private Job startChargeJob;
    private final ArrayBlockingQueue<Function0<Unit>> stateNoticeQueue;
    private Job stateReqJob;
    private Job stopChargeJob;
    private volatile String targetMac;
    private final GeneralTask$updateTaskListener$1 updateTaskListener;
    private Job versionJob;

    public static final /* synthetic */ MoveState access$getMoveState$p(GeneralTask generalTask) {
        MoveState moveState = generalTask.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneralTask(MoveAction action, AIDLConnection<MirCoreInterface> coreService) {
        super(action, coreService, null, null, null, false, 60, null);
        Intrinsics.checkParameterIsNotNull(action, "action");
        Intrinsics.checkParameterIsNotNull(coreService, "coreService");
        this.TAG = "GeneralTask";
        this.stateNoticeQueue = new ArrayBlockingQueue<>(10);
        this.ROBOT_IN_CHARING_POINT = 0.5d;
        this.targetMac = "";
        this.gateID = "";
        this.updateTaskListener = new GeneralTask$updateTaskListener$1(this);
        this.messageParserListener = new GeneralTask$messageParserListener$1(this, action, coreService);
        this.dockListener = new GeneralTask$dockListener$1(this);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void reset() {
        goChargingTimes = 0;
        this.reConnectCount = 0;
        Pdlog.m3273d(this.TAG, "generalTask reset");
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void startMoveAction() {
        MirCoreInterface mirCoreInterface = getCoreService().getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.removeDockerEstimateTransformListener("bluetooth_charge");
        }
        ChargeFinder.INSTANCE.reset();
        disconnectChargePile();
        this.moveState = new GeneralState();
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.start();
    }

    public final void setCruiseMode() {
        this.moveState = new GeneralState();
    }

    public final void assignChargingPile() {
        this.isRequestBluetooth = false;
        ChargeFinder.INSTANCE.reset();
        BluetoothBleListener bluetoothBleListener = this.callback;
        if (bluetoothBleListener != null) {
            doBluetoothStopCharge(bluetoothBleListener.getMac());
        }
        MirCoreInterface mirCoreInterface = getCoreService().getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.removeDockerEstimateTransformListener("bluetooth_charge");
        }
        this.moveState = new AssignChargingPile();
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d7  */
    @Override // com.pudutech.mirsdk.movetask.MoveTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public RobotState onStateChange(SDKRobotState state, final String desc) {
        final RobotState second;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (this.moveState == null) {
            Pdlog.m3273d(this.TAG, "onStateChange moveState not initialized");
            return SDKRobotStateKt.peerConversion(state);
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        Pair<MoveState, RobotState> mo4468switch = moveState.mo4468switch(state, desc);
        if (state == SDKRobotState.Error) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$onStateChange$2(this, null), 3, null);
        }
        if (mo4468switch.getSecond() == RobotState.Arrive) {
            if (!(this.gateID.length() == 0)) {
                second = RobotState.Moving;
                Pdlog.m3275i(this.TAG, "state report " + second + " real " + mo4468switch.getSecond() + " gate id " + this.gateID);
                this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$onStateChange$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        GeneralTask.this.getAction().informStateChange(second, desc);
                    }
                });
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new GeneralTask$onStateChange$4(this, null), 2, null);
                if (this.moveState == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("moveState");
                }
                if (!Intrinsics.areEqual(r13, mo4468switch.getFirst())) {
                    this.moveState = mo4468switch.getFirst();
                    String str = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("switch move state from ");
                    MoveState moveState2 = this.moveState;
                    if (moveState2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("moveState");
                    }
                    sb.append(moveState2.getClass().getName());
                    sb.append(" to ");
                    sb.append(mo4468switch.getFirst().getClass().getName());
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str, objArr);
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$onStateChange$5(this, null), 3, null);
                }
                return mo4468switch.getSecond();
            }
        }
        second = mo4468switch.getSecond();
        Pdlog.m3275i(this.TAG, "state report " + second + " real " + mo4468switch.getSecond() + " gate id " + this.gateID);
        this.stateNoticeQueue.put(new Function0<Unit>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$onStateChange$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                GeneralTask.this.getAction().informStateChange(second, desc);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new GeneralTask$onStateChange$4(this, null), 2, null);
        if (this.moveState == null) {
        }
        if (!Intrinsics.areEqual(r13, mo4468switch.getFirst())) {
        }
        return mo4468switch.getSecond();
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public Pair<NavigationMode, Boolean> checkNavigationMode() {
        return new Pair<>(NavigationMode.Normal, true);
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void pause() {
        if (this.moveState != null) {
            MoveState moveState = this.moveState;
            if (moveState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            moveState.pause();
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$pause$2(this, null), 3, null);
        }
    }

    @Override // com.pudutech.mirsdk.movetask.MoveTask
    public void resume() {
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        moveState.resume();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H\u0016J(\u0010\u0007\u001a\u0012\u0012\b\u0012\u00060\u0000R\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public abstract class MoveState {
        public abstract void pause();

        public abstract void resume();

        /* renamed from: switch */
        public abstract Pair<MoveState, RobotState> mo4468switch(SDKRobotState robotState, String desc);

        public MoveState() {
        }

        public void start() {
            BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$MoveState$start$1(this, null), 1, null);
        }
    }

    public final boolean isGoChargingTask() {
        if (this.moveState != null && getBusinessType() == BusinessType.GoCharging) {
            MoveState moveState = this.moveState;
            if (moveState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if (moveState instanceof GoChargingState) {
                return true;
            }
            MoveState moveState2 = this.moveState;
            if (moveState2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if (moveState2 instanceof GeneralState) {
                return true;
            }
            MoveState moveState3 = this.moveState;
            if (moveState3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if (moveState3 instanceof GoBluetoothChargingState) {
                return true;
            }
        }
        return false;
    }

    public final boolean isLeaveChargingTask() {
        if (this.moveState == null) {
            return false;
        }
        MoveState moveState = this.moveState;
        if (moveState == null) {
            Intrinsics.throwUninitializedPropertyAccessException("moveState");
        }
        return moveState instanceof AssignChargingPile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void lockWheel(boolean isLock) {
        getAction().lockWheel(isLock);
    }

    public final String getGateID() {
        return this.gateID;
    }

    public final void setGateID(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.gateID = str;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u0006\u0010\u0016\u001a\u00020\u0012J(\u0010\u0017\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$GeneralState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "currentJob", "Lkotlinx/coroutines/Job;", "getCurrentJob", "()Lkotlinx/coroutines/Job;", "setCurrentJob", "(Lkotlinx/coroutines/Job;)V", "findPile", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "<set-?>", "", "isArrived", "()Z", "isCanGoBluetoothCharge", "doGoBluetoothChargeTask", "", "pause", "resume", "start", "startFindGateJob", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GeneralState extends MoveState {
        private Job currentJob;
        private ChargingPile findPile;
        private boolean isArrived;
        private boolean isCanGoBluetoothCharge;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;
            public static final /* synthetic */ int[] $EnumSwitchMapping$2;
            public static final /* synthetic */ int[] $EnumSwitchMapping$3;
            public static final /* synthetic */ int[] $EnumSwitchMapping$4;
            public static final /* synthetic */ int[] $EnumSwitchMapping$5;

            static {
                $EnumSwitchMapping$0[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$1 = new int[BusinessType.values().length];
                $EnumSwitchMapping$1[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$1[BusinessType.GoGroup.ordinal()] = 2;
                $EnumSwitchMapping$2 = new int[BusinessType.values().length];
                $EnumSwitchMapping$2[BusinessType.GoGroup.ordinal()] = 1;
                $EnumSwitchMapping$3 = new int[BusinessType.values().length];
                $EnumSwitchMapping$3[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$4 = new int[BusinessType.values().length];
                $EnumSwitchMapping$4[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$5 = new int[SDKRobotState.values().length];
                $EnumSwitchMapping$5[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$5[SDKRobotState.Approaching.ordinal()] = 2;
                $EnumSwitchMapping$5[SDKRobotState.Error.ordinal()] = 3;
                $EnumSwitchMapping$5[SDKRobotState.Pause.ordinal()] = 4;
                $EnumSwitchMapping$5[SDKRobotState.Moving.ordinal()] = 5;
            }
        }

        public GeneralState() {
            super();
        }

        public final Job getCurrentJob() {
            return this.currentJob;
        }

        public final void setCurrentJob(Job job) {
            this.currentJob = job;
        }

        /* renamed from: isArrived, reason: from getter */
        public final boolean getIsArrived() {
            return this.isArrived;
        }

        public final void doGoBluetoothChargeTask() {
            Pdlog.m3273d(GeneralTask.this.TAG, "doGoBluetoothChargeTask isArrived:" + this.isArrived);
            this.isCanGoBluetoothCharge = true;
            if (this.isArrived) {
                GeneralTask.this.lockWheel(false);
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$GeneralState$doGoBluetoothChargeTask$1(this, null), 3, null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:39:0x01cb  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x01f6  */
        /* JADX WARN: Type inference failed for: r5v28 */
        /* JADX WARN: Type inference failed for: r5v29 */
        /* JADX WARN: Type inference failed for: r5v30, types: [java.lang.Object] */
        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void start() {
            Object obj;
            final Destination destination;
            Object obj2;
            Destination destination2;
            Pdlog.m3273d(GeneralTask.this.TAG, "---start---");
            super.start();
            GeneralTask.this.targetMac = "";
            if (GeneralTask.this.getPathSegments() == null) {
                Pdlog.m3273d(GeneralTask.this.TAG, "pathSegments is null so return");
                return;
            }
            if (WhenMappings.$EnumSwitchMapping$0[GeneralTask.this.getBusinessType().ordinal()] == 1) {
                PathSegments pathSegments = GeneralTask.this.getPathSegments();
                String finalGoal = pathSegments != null ? pathSegments.getFinalGoal() : null;
                Iterator it = GeneralTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    } else {
                        obj = it.next();
                        if (Intrinsics.areEqual(((ChargingPile) obj).getId(), finalGoal)) {
                            break;
                        }
                    }
                }
                ChargingPile chargingPile = (ChargingPile) obj;
                if (chargingPile != null) {
                    this.findPile = chargingPile;
                    GeneralTask.this.targetMac = chargingPile.getMac();
                    Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
                    vector3d.setX(chargingPile.getPose().getX() + Math.cos(chargingPile.getPose().getZ()));
                    vector3d.setY(chargingPile.getPose().getY() + Math.sin(chargingPile.getPose().getZ()));
                    vector3d.setZ(chargingPile.getPose().getZ() >= ((double) 0) ? chargingPile.getPose().getZ() - 3.141592653589793d : chargingPile.getPose().getZ() + 3.141592653589793d);
                    destination = new Destination(chargingPile.getId(), chargingPile.getFloor(), vector3d.getX(), vector3d.getY(), vector3d.getZ(), false, "", "", false);
                    if (destination == null) {
                    }
                }
                destination = null;
                if (destination == null) {
                }
            } else {
                List<Destination> destinations = GeneralTask.this.getAction().getDestinations();
                if (destinations != null) {
                    Iterator it2 = destinations.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            destination2 = 0;
                            break;
                        }
                        destination2 = it2.next();
                        String name = ((Destination) destination2).getName();
                        PathSegments pathSegments2 = GeneralTask.this.getPathSegments();
                        if (Intrinsics.areEqual(name, pathSegments2 != null ? pathSegments2.getFinalGoal() : null)) {
                            break;
                        }
                    }
                    destination = destination2;
                } else {
                    destination = null;
                }
                if (destination == null) {
                    Iterator it3 = GeneralTask.this.getAction().getElevWaiters().iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            obj2 = null;
                            break;
                        }
                        obj2 = it3.next();
                        String id = ((ElevatorWaiter) obj2).getId();
                        PathSegments pathSegments3 = GeneralTask.this.getPathSegments();
                        if (Intrinsics.areEqual(id, pathSegments3 != null ? pathSegments3.getFinalGoal() : null)) {
                            break;
                        }
                    }
                    ElevatorWaiter elevatorWaiter = (ElevatorWaiter) obj2;
                    if (elevatorWaiter != null) {
                        destination = new Destination(elevatorWaiter.getFloor(), elevatorWaiter.getId(), elevatorWaiter.getPose().getX(), elevatorWaiter.getPose().getY(), elevatorWaiter.getPose().getZ(), false, "", "", false);
                    }
                    destination = null;
                }
                if (destination == null) {
                    String str = GeneralTask.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("cannot find destination ");
                    PathSegments pathSegments4 = GeneralTask.this.getPathSegments();
                    sb.append(pathSegments4 != null ? pathSegments4.getFinalGoal() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3277w(str, objArr);
                    return;
                }
                Pdlog.m3273d(GeneralTask.this.TAG, "---dest floor:" + destination.getFloor() + " name:" + destination.getName() + " id:" + destination.getGroup() + "---");
                GeneralTask.this.getAction().moveToPosition(GeneralTask.this.getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$GeneralState$start$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Boolean invoke() {
                        return Boolean.valueOf(invoke2());
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final boolean invoke2() {
                        Boolean valueOf;
                        ScheduleInterface scheduler;
                        ScheduleInterface scheduler2;
                        String str2 = GeneralTask.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append('[');
                        Thread currentThread = Thread.currentThread();
                        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                        sb2.append(currentThread.getName());
                        sb2.append("]move to (");
                        sb2.append(CommonKt.format(destination.getX(), 2));
                        sb2.append(',');
                        sb2.append(CommonKt.format(destination.getY(), 2));
                        sb2.append(") dir:");
                        sb2.append(destination.getDirection());
                        sb2.append(" goal_dir_property:");
                        sb2.append(destination.getDoubleDirection());
                        Pdlog.m3273d(str2, sb2.toString());
                        MirCoreInterface mirCoreInterface = GeneralTask.this.getCoreService().getInterface();
                        Boolean bool = null;
                        NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                        int i = GeneralTask.GeneralState.WhenMappings.$EnumSwitchMapping$1[GeneralTask.this.getBusinessType().ordinal()];
                        if (i == 1) {
                            if (navigator != null) {
                                valueOf = Boolean.valueOf(navigator.prepareMoveToChargeTask());
                            }
                            valueOf = null;
                        } else if (i != 2) {
                            if (navigator != null) {
                                valueOf = Boolean.valueOf(navigator.prepareDeliverTask(destination.getDoubleDirection(), destination.getPrecise()));
                            }
                            valueOf = null;
                        } else {
                            if (navigator != null) {
                                valueOf = Boolean.valueOf(navigator.prepareGoHomeTask());
                            }
                            valueOf = null;
                        }
                        if (GeneralTask.GeneralState.WhenMappings.$EnumSwitchMapping$2[GeneralTask.this.getBusinessType().ordinal()] == 1) {
                            com.pudutech.mirsdk.mircore.coreparcel.BusinessType businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.GoHome;
                            if (GeneralTask.this.getIsFillIn()) {
                                businessType = com.pudutech.mirsdk.mircore.coreparcel.BusinessType.WaitTake;
                            }
                            Pdlog.m3273d(GeneralTask.this.TAG, " go home task status isFillIn: " + GeneralTask.this.getIsFillIn() + "  , business_type : " + businessType.name());
                            MirCoreInterface mirCoreInterface2 = GeneralTask.this.getCoreService().getInterface();
                            if (mirCoreInterface2 != null && (scheduler = mirCoreInterface2.getScheduler()) != null) {
                                bool = Boolean.valueOf(scheduler.prepareGoHomeTask(destination.getGroup(), businessType));
                            }
                        } else {
                            MirCoreInterface mirCoreInterface3 = GeneralTask.this.getCoreService().getInterface();
                            if (mirCoreInterface3 != null && (scheduler2 = mirCoreInterface3.getScheduler()) != null) {
                                bool = Boolean.valueOf(scheduler2.prepareDeliverTask(new Vector3d(destination.getX(), destination.getY(), destination.getDirection())));
                            }
                        }
                        return Intrinsics.areEqual((Object) valueOf, (Object) true) && Intrinsics.areEqual((Object) bool, (Object) true);
                    }
                });
                startFindGateJob();
            }
        }

        public final void startFindGateJob() {
            Job launch$default;
            Job job = this.currentJob;
            if (job == null || !job.isActive()) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$GeneralState$startFindGateJob$1(this, null), 3, null);
                this.currentJob = launch$default;
            }
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void pause() {
            GeneralTask.this.lockWheel(false);
            GeneralTask.this.getAction().actionPause();
            BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$GeneralState$pause$1(this, null), 1, null);
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void resume() {
            GeneralTask.this.getAction().actionResume();
            startFindGateJob();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4468switch(SDKRobotState robotState, String desc) {
            Pair<MoveState, RobotState> pair;
            ChargingPile chargingPile;
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            Pdlog.m3273d(GeneralTask.this.TAG, "General state change " + robotState);
            int i = WhenMappings.$EnumSwitchMapping$5[robotState.ordinal()];
            if (i == 1) {
                BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$GeneralState$switch$1(this, null), 1, null);
                this.isArrived = true;
                if (WhenMappings.$EnumSwitchMapping$3[GeneralTask.this.getBusinessType().ordinal()] == 1) {
                    Pdlog.m3273d(GeneralTask.this.TAG, "Switch to go charging");
                    ChargingPile chargingPile2 = this.findPile;
                    if (chargingPile2 != null) {
                        Pdlog.m3273d(GeneralTask.this.TAG, "mac is " + chargingPile2.getMac());
                        if (chargingPile2.getMac().length() == 0) {
                            pair = new Pair<>(new GoChargingState(), RobotState.Moving);
                        } else if (!this.isCanGoBluetoothCharge) {
                            GeneralTask.this.lockWheel(true);
                            if (!ChargeUpdateTaskManager.INSTANCE.isUpdating(chargingPile2.getMac())) {
                                if (!GeneralTask.this.isRequestBluetooth) {
                                    GeneralTask.this.startConnectChargePile(chargingPile2.getMac());
                                }
                            } else {
                                Pdlog.m3273d(GeneralTask.this.TAG, "current pile is In updating");
                                GeneralTask.this.startUpdateTask(chargingPile2.getMac());
                            }
                            pair = new Pair<>(this, RobotState.Moving);
                        } else {
                            pair = new Pair<>(new GoBluetoothChargingState(), RobotState.Moving);
                        }
                        return pair;
                    }
                    return new Pair<>(new GoChargingState(), RobotState.Moving);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i == 2) {
                if (WhenMappings.$EnumSwitchMapping$4[GeneralTask.this.getBusinessType().ordinal()] == 1) {
                    ChargingPile chargingPile3 = this.findPile;
                    if (chargingPile3 != null) {
                        if ((chargingPile3.getMac().length() > 0) && !GeneralTask.this.isRecStatusReply) {
                            if (!ChargeUpdateTaskManager.INSTANCE.isUpdating(chargingPile3.getMac())) {
                                GeneralTask.this.startConnectChargePile(chargingPile3.getMac());
                            } else {
                                Pdlog.m3273d(GeneralTask.this.TAG, "current pile is In updating waiting in approaching");
                            }
                        }
                    }
                    return new Pair<>(this, RobotState.Moving);
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i != 3 && i != 4) {
                if (i == 5) {
                    if (!(GeneralTask.this.getGateID().length() == 0)) {
                        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$GeneralState$switch$6(this, null), 1, null);
                        return new Pair<>(this, SDKRobotStateKt.peerConversion(SDKRobotState.Arrive));
                    }
                    return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$GeneralState$switch$4(this, null), 1, null);
            this.isArrived = false;
            GeneralTask.this.lockWheel(false);
            if (GeneralTask.this.getBusinessType() == BusinessType.GoCharging && (chargingPile = this.findPile) != null) {
                if ((chargingPile.getMac().length() > 0) && !GeneralTask.this.isRecStatusReply) {
                    if (!ChargeUpdateTaskManager.INSTANCE.isUpdating(chargingPile.getMac())) {
                        GeneralTask.this.disconnectChargePile();
                    } else {
                        Pdlog.m3273d(GeneralTask.this.TAG, "current pile is In updating waiting in pause or error");
                    }
                }
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startUpdateTask(String mac) {
        Job job = this.stateReqJob;
        if (job != null && job.isActive()) {
            BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$startUpdateTask$1(this, null), 1, null);
        }
        ChargeUpdateTaskManager.INSTANCE.addBluetoothChargeUpdateListener(this.TAG, this.updateTaskListener);
        ChargeUpdateTaskManager.INSTANCE.startUpdateTask(mac);
    }

    public final Job getStateReqJob() {
        return this.stateReqJob;
    }

    public final void setStateReqJob(Job job) {
        this.stateReqJob = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doBluetoothChargeState(String mac) {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "doBluetoothChargeState " + mac);
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$doBluetoothChargeState$1(this, null), 1, null);
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$doBluetoothChargeState$2(this, mac, null), 3, null);
        this.stateReqJob = launch$default;
    }

    public final Job getStopChargeJob() {
        return this.stopChargeJob;
    }

    public final void setStopChargeJob(Job job) {
        this.stopChargeJob = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doBluetoothStopCharge(String mac) {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "doBluetoothStopCharge " + mac);
        stopAllJob();
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$doBluetoothStopCharge$1(this, mac, null), 3, null);
        this.stopChargeJob = launch$default;
    }

    public final Job getStartChargeJob() {
        return this.startChargeJob;
    }

    public final void setStartChargeJob(Job job) {
        this.startChargeJob = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doBluetoothStartCharge(String mac) {
        Job launch$default;
        Pdlog.m3273d(this.TAG, "doBluetoothStartCharge " + mac);
        stopAllJob();
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$doBluetoothStartCharge$1(this, mac, null), 3, null);
        this.startChargeJob = launch$default;
    }

    public final Job getVersionJob() {
        return this.versionJob;
    }

    public final void setVersionJob(Job job) {
        this.versionJob = job;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doBluetoothVersionCheck(String mac) {
        Job launch$default;
        stopAllJob();
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$doBluetoothVersionCheck$1(this, mac, null), 3, null);
        this.versionJob = launch$default;
    }

    private final void stopAllJob() {
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$stopAllJob$1(this, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initCallBack(String mac) {
        if (!Intrinsics.areEqual(mac, this.callback != null ? r0.getMac() : null)) {
            ChargeMessageParser chargeMessageParser = this.parser;
            if (chargeMessageParser != null) {
                chargeMessageParser.setMessageParserListener((ChargeMessageParser.OnChargeMessageParserListener) null);
            }
            ChargeMessageParser chargeMessageParser2 = new ChargeMessageParser(mac);
            chargeMessageParser2.setMessageParserListener(this.messageParserListener);
            this.parser = chargeMessageParser2;
            this.callback = new GeneralTask$initCallBack$2(this, mac, mac);
        }
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothBleListener bluetoothBleListener = this.callback;
        if (bluetoothBleListener == null) {
            Intrinsics.throwNpe();
        }
        bluetoothBleHelper.addBluetoothBleListeners(bluetoothBleListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startConnectChargePile(String mac) {
        Job launch$default;
        if (this.isRequestBluetooth) {
            Pdlog.m3273d(this.TAG, "startConnectChargePile is in request");
            return;
        }
        this.isRequestBluetooth = true;
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$startConnectChargePile$1(this, mac, null), 3, null);
        this.connectJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disconnectChargePile() {
        this.isRecStatusReply = false;
        this.isRequestBluetooth = false;
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$disconnectChargePile$1(this, null), 1, null);
        BluetoothBleListener bluetoothBleListener = this.callback;
        if (bluetoothBleListener != null) {
            BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
            if (!ChargeUpdateTaskManager.INSTANCE.isUpdating(bluetoothBleListener.getMac())) {
                BluetoothBleHelper.INSTANCE.disconnectDevice(bluetoothBleListener.getMac());
            }
        }
        this.callback = (BluetoothBleListener) null;
        ChargeFinder.INSTANCE.reset();
    }

    public final void onSpeed(double line, double angular) {
        if (getAction().isLockWheel() && this.moveState != null) {
            MoveState moveState = this.moveState;
            if (moveState == null) {
                Intrinsics.throwUninitializedPropertyAccessException("moveState");
            }
            if ((moveState instanceof GoBluetoothChargingState) && (Math.abs(line) > 0.15d || Math.abs(angular) > 0.25d)) {
                Pdlog.m3273d(this.TAG, "onSpeed lock wheel to false");
                lockWheel(false);
            }
        }
        if (ChargeFinder.INSTANCE.getFoundStep() != ChargeFinder.FoundChargeStep.Idle) {
            if (Math.abs(line) > 0.15d || Math.abs(angular) > 0.25d) {
                Pdlog.m3273d(this.TAG, "onSpeed reset Finder");
                ChargeFinder.INSTANCE.reset();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J(\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$GoChargingState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "recentPile", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GoChargingState extends MoveState {
        private ChargingPile recentPile;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Pause.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Resume.ordinal()] = 3;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 4;
            }
        }

        public GoChargingState() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void start() {
            Object obj;
            super.start();
            Iterator<T> it = GeneralTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                String id = ((ChargingPile) obj).getId();
                PathSegments pathSegments = GeneralTask.this.getPathSegments();
                if (Intrinsics.areEqual(id, pathSegments != null ? pathSegments.getFinalGoal() : null)) {
                    break;
                }
            }
            final ChargingPile chargingPile = (ChargingPile) obj;
            if (chargingPile == null) {
                String str = GeneralTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("cannot find destination ");
                PathSegments pathSegments2 = GeneralTask.this.getPathSegments();
                sb.append(pathSegments2 != null ? pathSegments2.getFinalGoal() : null);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                this.recentPile = (ChargingPile) null;
                return;
            }
            this.recentPile = chargingPile;
            GeneralTask.this.getAction().moveToPosition(GeneralTask.this.getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$GoChargingState$start$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(invoke2());
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2() {
                    String str2 = GeneralTask.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append('[');
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb2.append(currentThread.getName());
                    sb2.append("]move to charge pile (");
                    sb2.append(CommonKt.format(chargingPile.getPose().getX(), 2));
                    sb2.append(',');
                    sb2.append(CommonKt.format(chargingPile.getPose().getY(), 2));
                    sb2.append(") dir:");
                    sb2.append(chargingPile.getPose().getZ());
                    Pdlog.m3273d(str2, sb2.toString());
                    MirCoreInterface mirCoreInterface = GeneralTask.this.getCoreService().getInterface();
                    NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                    if (navigator != null) {
                        return navigator.prepareAutoChargeTask(new Vector3d(chargingPile.getPose().getX(), chargingPile.getPose().getY(), chargingPile.getPose().getZ()));
                    }
                    return false;
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void pause() {
            GeneralTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void resume() {
            GeneralTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4468switch(SDKRobotState robotState, String desc) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
                }
                if (i == 3) {
                    return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
                }
                if (i == 4) {
                    return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (GeneralTask.INSTANCE.getGoChargingTimes() < 1) {
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$GoChargingState$switch$1(this, null), 3, null);
                Companion companion = GeneralTask.INSTANCE;
                companion.setGoChargingTimes(companion.getGoChargingTimes() + 1);
                return new Pair<>(this, RobotState.Moving);
            }
            Pdlog.m3273d(GeneralTask.this.TAG, "GoChargingState has try twice");
            Companion companion2 = GeneralTask.INSTANCE;
            companion2.setGoChargingTimes(companion2.getGoChargingTimes() + 1);
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    public final DockerEstimateTransformListener.Stub getDockListener() {
        return this.dockListener;
    }

    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J(\u0010\n\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$GoBluetoothChargingState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "recentPile", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class GoBluetoothChargingState extends MoveState {
        private ChargingPile recentPile;

        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SDKRobotState.values().length];

            static {
                $EnumSwitchMapping$0[SDKRobotState.Arrive.ordinal()] = 1;
                $EnumSwitchMapping$0[SDKRobotState.Pause.ordinal()] = 2;
                $EnumSwitchMapping$0[SDKRobotState.Resume.ordinal()] = 3;
                $EnumSwitchMapping$0[SDKRobotState.Error.ordinal()] = 4;
            }
        }

        public GoBluetoothChargingState() {
            super();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void start() {
            Object obj;
            super.start();
            ChargeFinder.INSTANCE.reset();
            Pdlog.m3273d(GeneralTask.this.TAG, "GoBluetoothChargingState START");
            Iterator<T> it = GeneralTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                String id = ((ChargingPile) obj).getId();
                PathSegments pathSegments = GeneralTask.this.getPathSegments();
                if (Intrinsics.areEqual(id, pathSegments != null ? pathSegments.getFinalGoal() : null)) {
                    break;
                }
            }
            final ChargingPile chargingPile = (ChargingPile) obj;
            if (chargingPile == null) {
                String str = GeneralTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("cannot find destination ");
                PathSegments pathSegments2 = GeneralTask.this.getPathSegments();
                sb.append(pathSegments2 != null ? pathSegments2.getFinalGoal() : null);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                this.recentPile = (ChargingPile) null;
                return;
            }
            GeneralTask.this.isNeedCallStart = true;
            MirCoreInterface mirCoreInterface = GeneralTask.this.getCoreService().getInterface();
            if (mirCoreInterface != null) {
                mirCoreInterface.addDockerEstimateTransformListener("bluetooth_charge", GeneralTask.this.getDockListener());
            }
            this.recentPile = chargingPile;
            GeneralTask.this.getAction().moveToPosition(GeneralTask.this.getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$GoBluetoothChargingState$start$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(invoke2());
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2() {
                    String str2 = GeneralTask.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append('[');
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb2.append(currentThread.getName());
                    sb2.append("]move to charge pile (");
                    sb2.append(CommonKt.format(chargingPile.getPose().getX(), 2));
                    sb2.append(',');
                    sb2.append(CommonKt.format(chargingPile.getPose().getY(), 2));
                    sb2.append(") dir:");
                    sb2.append(chargingPile.getPose().getZ());
                    Pdlog.m3273d(str2, sb2.toString());
                    MirCoreInterface mirCoreInterface2 = GeneralTask.this.getCoreService().getInterface();
                    NavigationInterface navigator = mirCoreInterface2 != null ? mirCoreInterface2.getNavigator() : null;
                    if (navigator != null) {
                        return navigator.prepareAutoChargeTask(new Vector3d(chargingPile.getPose().getX(), chargingPile.getPose().getY(), chargingPile.getPose().getZ()));
                    }
                    return false;
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void pause() {
            GeneralTask.this.getAction().actionPause();
            GeneralTask.this.lockWheel(false);
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void resume() {
            GeneralTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        /* renamed from: switch */
        public Pair<MoveState, RobotState> mo4468switch(SDKRobotState robotState, String desc) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            int i = WhenMappings.$EnumSwitchMapping$0[robotState.ordinal()];
            if (i == 1) {
                if (GeneralTask.INSTANCE.getGoChargingTimes() < 1) {
                    BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$GoBluetoothChargingState$switch$1(this, null), 3, null);
                    Companion companion = GeneralTask.INSTANCE;
                    companion.setGoChargingTimes(companion.getGoChargingTimes() + 1);
                    return new Pair<>(this, RobotState.Moving);
                }
                Pdlog.m3273d(GeneralTask.this.TAG, "GoBluetoothChargingState has try twice");
                Companion companion2 = GeneralTask.INSTANCE;
                companion2.setGoChargingTimes(companion2.getGoChargingTimes() + 1);
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i == 2) {
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i == 3) {
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            if (i == 4) {
                BluetoothBleListener bluetoothBleListener = GeneralTask.this.callback;
                if (bluetoothBleListener != null) {
                    GeneralTask.this.doBluetoothStopCharge(bluetoothBleListener.getMac());
                }
                return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J(\u0010\u0010\u001a\u0012\u0012\b\u0012\u00060\u0001R\u00020\u0002\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$AssignChargingPile;", "Lcom/pudutech/mirsdk/movetask/GeneralTask$MoveState;", "Lcom/pudutech/mirsdk/movetask/GeneralTask;", "(Lcom/pudutech/mirsdk/movetask/GeneralTask;)V", "isArrived", "", "()Z", "setArrived", "(Z)V", "getChargingPileWithPath", "Lcom/pudutech/mirsdk/aidl/serialize/Destination;", "getNearChargingPiles", "pause", "", "resume", "start", "switch", "Lkotlin/Pair;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "robotState", "Lcom/pudutech/mirsdk/base/SDKRobotState;", TmpConstant.SERVICE_DESC, "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class AssignChargingPile extends MoveState {
        private volatile boolean isArrived;

        /* JADX WARN: Classes with same name are omitted:
          classes4.dex
         */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BusinessType.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                $EnumSwitchMapping$0[BusinessType.GoCharging.ordinal()] = 1;
                $EnumSwitchMapping$1 = new int[BusinessType.values().length];
                $EnumSwitchMapping$1[BusinessType.GoCharging.ordinal()] = 1;
            }
        }

        public AssignChargingPile() {
            super();
        }

        /* renamed from: isArrived, reason: from getter */
        public final boolean getIsArrived() {
            return this.isArrived;
        }

        public final void setArrived(boolean z) {
            this.isArrived = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x012c A[EDGE_INSN: B:11:0x012c->B:12:0x012c BREAK  A[LOOP:0: B:2:0x0012->B:25:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:25:? A[LOOP:0: B:2:0x0012->B:25:?, LOOP_END, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final Destination getNearChargingPiles() {
            Object obj;
            boolean z;
            Iterator<T> it = GeneralTask.this.getAction().getChargingPilesList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                ChargingPile chargingPile = (ChargingPile) obj;
                Pdlog.m3273d(GeneralTask.this.TAG, "getNearChargingPiles floor:" + chargingPile.getFloor() + " defaultFloor:" + GeneralTask.this.getAction().getAtlas().getDefaultFloor());
                if (Intrinsics.areEqual(chargingPile.getFloor(), GeneralTask.this.getAction().getAtlas().getDefaultFloor())) {
                    double x = GeneralTask.this.getAction().getCurRobotPose().getX() - chargingPile.getPose().getX();
                    double y = GeneralTask.this.getAction().getCurRobotPose().getY() - chargingPile.getPose().getY();
                    String str = GeneralTask.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("getNearChargingPiles cur[");
                    sb.append(GeneralTask.this.getAction().getCurRobotPose().getX());
                    sb.append(", ");
                    sb.append(GeneralTask.this.getAction().getCurRobotPose().getY());
                    sb.append("] pose[");
                    sb.append(chargingPile.getPose().getX());
                    sb.append(',');
                    sb.append(chargingPile.getPose().getY());
                    sb.append("] ");
                    sb.append(SpeechUtility.TAG_RESOURCE_RESULT);
                    double d = (x * x) + (y * y);
                    sb.append(Math.sqrt(d));
                    Pdlog.m3273d(str, sb.toString());
                    if (Math.sqrt(d) < GeneralTask.this.ROBOT_IN_CHARING_POINT) {
                        z = true;
                        if (!z) {
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
            ChargingPile chargingPile2 = (ChargingPile) obj;
            if (chargingPile2 == null) {
                Pdlog.m3273d(GeneralTask.this.TAG, "getNearChargingPiles fail");
                return null;
            }
            Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
            vector3d.setX(chargingPile2.getPose().getX() + Math.cos(chargingPile2.getPose().getZ()));
            vector3d.setY(chargingPile2.getPose().getY() + Math.sin(chargingPile2.getPose().getZ()));
            vector3d.setZ(chargingPile2.getPose().getZ());
            GeneralTask.this.setBusinessType(BusinessType.GoCharging);
            Pdlog.m3273d(GeneralTask.this.TAG, "getNearChargingPiles success " + chargingPile2.getId() + ',' + chargingPile2.getFloor() + ',' + vector3d.getX() + ',' + vector3d.getY() + ',' + vector3d.getZ());
            if (chargingPile2.getMac().length() > 0) {
                GeneralTask.this.doBluetoothStopCharge(chargingPile2.getMac());
            }
            return new Destination(chargingPile2.getId(), chargingPile2.getFloor(), vector3d.getX(), vector3d.getY(), vector3d.getZ(), false, "", "", false);
        }

        private final Destination getChargingPileWithPath() {
            Object obj;
            PathSegments pathSegments = GeneralTask.this.getPathSegments();
            String finalGoal = pathSegments != null ? pathSegments.getFinalGoal() : null;
            Iterator<T> it = GeneralTask.this.getAction().getAtlas().getMapDecode().getChargingPiles().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((ChargingPile) obj).getId(), finalGoal)) {
                    break;
                }
            }
            ChargingPile chargingPile = (ChargingPile) obj;
            Pdlog.m3273d(GeneralTask.this.TAG, "AssignChargingPile getChargingPileWithPath pileId " + finalGoal);
            if (chargingPile == null) {
                return null;
            }
            Vector3d vector3d = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
            vector3d.setX(chargingPile.getPose().getX() + Math.cos(chargingPile.getPose().getZ()));
            vector3d.setY(chargingPile.getPose().getY() + Math.sin(chargingPile.getPose().getZ()));
            vector3d.setZ(chargingPile.getPose().getZ());
            if (chargingPile.getMac().length() > 0) {
                GeneralTask.this.doBluetoothStopCharge(chargingPile.getMac());
            }
            return new Destination(chargingPile.getId(), chargingPile.getFloor(), vector3d.getX(), vector3d.getY(), vector3d.getZ(), false, "", "", false);
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void start() {
            if (!this.isArrived) {
                Pdlog.m3273d(GeneralTask.this.TAG, "is already arrived");
                return;
            }
            super.start();
            Pdlog.m3273d(GeneralTask.this.TAG, "AssignChargingPile start");
            final Destination nearChargingPiles = getNearChargingPiles();
            if (nearChargingPiles == null) {
                nearChargingPiles = getChargingPileWithPath();
            }
            if (nearChargingPiles == null) {
                String str = GeneralTask.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("cannot find destination ");
                PathSegments pathSegments = GeneralTask.this.getPathSegments();
                sb.append(pathSegments != null ? pathSegments.getFinalGoal() : null);
                objArr[0] = sb.toString();
                Pdlog.m3277w(str, objArr);
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$AssignChargingPile$start$1(this, null), 3, null);
                return;
            }
            GeneralTask.this.getAction().moveToPosition(GeneralTask.this.getSteadyMode(), new Function0<Boolean>() { // from class: com.pudutech.mirsdk.movetask.GeneralTask$AssignChargingPile$start$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Boolean invoke() {
                    return Boolean.valueOf(invoke2());
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final boolean invoke2() {
                    Boolean valueOf;
                    ScheduleInterface scheduler;
                    String str2 = GeneralTask.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append('[');
                    Thread currentThread = Thread.currentThread();
                    Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                    sb2.append(currentThread.getName());
                    sb2.append("]move to (");
                    sb2.append(CommonKt.format(nearChargingPiles.getX(), 2));
                    sb2.append(',');
                    sb2.append(CommonKt.format(nearChargingPiles.getY(), 2));
                    sb2.append(") dir:");
                    sb2.append(nearChargingPiles.getDirection());
                    sb2.append(" goal_dir_property:");
                    sb2.append(nearChargingPiles.getDoubleDirection());
                    Boolean bool = false;
                    Pdlog.m3273d(str2, sb2.toString());
                    MirCoreInterface mirCoreInterface = GeneralTask.this.getCoreService().getInterface();
                    NavigationInterface navigator = mirCoreInterface != null ? mirCoreInterface.getNavigator() : null;
                    if (GeneralTask.AssignChargingPile.WhenMappings.$EnumSwitchMapping$0[GeneralTask.this.getBusinessType().ordinal()] != 1) {
                        valueOf = bool;
                    } else {
                        valueOf = navigator != null ? Boolean.valueOf(navigator.prepareLeaveChargeTask()) : null;
                    }
                    if (GeneralTask.AssignChargingPile.WhenMappings.$EnumSwitchMapping$1[GeneralTask.this.getBusinessType().ordinal()] == 1) {
                        MirCoreInterface mirCoreInterface2 = GeneralTask.this.getCoreService().getInterface();
                        bool = (mirCoreInterface2 == null || (scheduler = mirCoreInterface2.getScheduler()) == null) ? null : Boolean.valueOf(scheduler.prepareDeliverTask(new Vector3d(nearChargingPiles.getX(), nearChargingPiles.getY(), nearChargingPiles.getDirection())));
                    }
                    return Intrinsics.areEqual((Object) valueOf, (Object) true) && Intrinsics.areEqual((Object) bool, (Object) true);
                }
            });
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void pause() {
            GeneralTask.this.getAction().actionPause();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        public void resume() {
            GeneralTask.this.getAction().actionResume();
        }

        @Override // com.pudutech.mirsdk.movetask.GeneralTask.MoveState
        /* renamed from: switch, reason: not valid java name */
        public Pair<MoveState, RobotState> mo4468switch(SDKRobotState robotState, String desc) {
            Intrinsics.checkParameterIsNotNull(robotState, "robotState");
            Intrinsics.checkParameterIsNotNull(desc, "desc");
            if (robotState == SDKRobotState.Error) {
                GeneralTask.this.lockWheel(false);
                GeneralTask.this.disconnectChargePile();
            }
            if (robotState == SDKRobotState.Arrive) {
                this.isArrived = true;
            }
            return new Pair<>(this, SDKRobotStateKt.peerConversion(robotState));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: GeneralTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/movetask/GeneralTask$Companion;", "", "()V", "goChargingTimes", "", "getGoChargingTimes", "()I", "setGoChargingTimes", "(I)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getGoChargingTimes() {
            return GeneralTask.goChargingTimes;
        }

        public final void setGoChargingTimes(int i) {
            GeneralTask.goChargingTimes = i;
        }
    }
}
