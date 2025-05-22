package com.pudutech.mirsdk.movetask;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.MoveAction;
import com.pudutech.mirsdk.base.SDKRobotState;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.bluetooth.BluetoothBleListener;
import com.pudutech.mirsdk.charge.ChargeFinder;
import com.pudutech.mirsdk.charge.ChargeMessageParser;
import com.pudutech.mirsdk.charge.ChargeUpdateTaskManager;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.movetask.GeneralTask;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: GeneralTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J8\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¨\u0006\u0018"}, m3961d2 = {"com/pudutech/mirsdk/movetask/GeneralTask$messageParserListener$1", "Lcom/pudutech/mirsdk/charge/ChargeMessageParser$OnChargeMessageParserListener;", "onChargeArrived", "", "onChargeLeave", "onChargeStartReply", "isSuccess", "", "onChargeState", AUserTrack.UTKEY_ERROR_CODE, "", "busyState", "inVol", "outVol", "current", "temp", "onChargeStopReply", "onChargeTimeout", "onCheckPileSuccess", "onCmdFail", "onRebootReply", "onVersion", "version", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class GeneralTask$messageParserListener$1 implements ChargeMessageParser.OnChargeMessageParserListener {
    final /* synthetic */ MoveAction $action;
    final /* synthetic */ AIDLConnection $coreService;
    final /* synthetic */ GeneralTask this$0;

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onCheckPileSuccess() {
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onCmdFail() {
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onRebootReply() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneralTask$messageParserListener$1(GeneralTask generalTask, MoveAction moveAction, AIDLConnection aIDLConnection) {
        this.this$0 = generalTask;
        this.$action = moveAction;
        this.$coreService = aIDLConnection;
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onVersion(String version) {
        GeneralTask.MoveState moveState;
        String str;
        Intrinsics.checkParameterIsNotNull(version, "version");
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$messageParserListener$1$onVersion$1(this, null), 1, null);
        moveState = this.this$0.moveState;
        if (moveState != null && (GeneralTask.access$getMoveState$p(this.this$0) instanceof GeneralTask.GeneralState) && this.this$0.getBusinessType() == BusinessType.GoCharging) {
            if (ChargeUpdateTaskManager.INSTANCE.isAutoUpdate() && !Intrinsics.areEqual(version, ChargeUpdateTaskManager.INSTANCE.getTargetVersion())) {
                BluetoothBleListener bluetoothBleListener = this.this$0.callback;
                if (bluetoothBleListener != null) {
                    BluetoothBleHelper.INSTANCE.removeBluetoothBleListeners(bluetoothBleListener);
                }
                GeneralTask generalTask = this.this$0;
                str = generalTask.targetMac;
                generalTask.startUpdateTask(str);
                return;
            }
            GeneralTask.MoveState access$getMoveState$p = GeneralTask.access$getMoveState$p(this.this$0);
            if (access$getMoveState$p == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.movetask.GeneralTask.GeneralState");
            }
            ((GeneralTask.GeneralState) access$getMoveState$p).doGoBluetoothChargeTask();
        }
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeState(int errorCode, int busyState, int inVol, int outVol, int current, int temp) {
        GeneralTask.MoveState moveState;
        this.this$0.isRecStatusReply = true;
        moveState = this.this$0.moveState;
        if (moveState != null && (GeneralTask.access$getMoveState$p(this.this$0) instanceof GeneralTask.GeneralState) && this.this$0.getBusinessType() == BusinessType.GoCharging) {
            if (busyState == 0) {
                BluetoothBleListener bluetoothBleListener = this.this$0.callback;
                if (bluetoothBleListener != null) {
                    this.this$0.doBluetoothVersionCheck(bluetoothBleListener.getMac());
                    return;
                }
                return;
            }
            this.this$0.lockWheel(false);
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new GeneralTask$messageParserListener$1$onChargeState$3(this, null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getSDKStateWorker(), null, new GeneralTask$messageParserListener$1$onChargeState$4(this, null), 2, null);
        }
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeStartReply(boolean isSuccess) {
        this.this$0.isRecChargeStartMsg = true;
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$messageParserListener$1$onChargeStartReply$1(this, null), 1, null);
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeStopReply(boolean isSuccess) {
        this.$action.notifyBTChargePileArrivedState(false);
        this.this$0.disconnectChargePile();
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeArrived() {
        GeneralTask.MoveState moveState;
        Pdlog.m3273d(this.this$0.TAG, "onChargeArrived");
        moveState = this.this$0.moveState;
        if (moveState == null || !(GeneralTask.access$getMoveState$p(this.this$0) instanceof GeneralTask.GoBluetoothChargingState)) {
            return;
        }
        ChargeFinder.INSTANCE.setToChargingState();
        this.$action.notifyBTChargePileArrivedState(true);
        MirCoreInterface mirCoreInterface = (MirCoreInterface) this.$coreService.getInterface();
        if (mirCoreInterface != null) {
            mirCoreInterface.removeDockerEstimateTransformListener("bluetooth_charge");
        }
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeLeave() {
        Pdlog.m3273d(this.this$0.TAG, "onChargeLeave");
        this.$action.notifyBTChargePileArrivedState(false);
        this.this$0.disconnectChargePile();
    }

    @Override // com.pudutech.mirsdk.charge.ChargeMessageParser.OnChargeMessageParserListener
    public void onChargeTimeout() {
        this.this$0.disconnectChargePile();
        BuildersKt__BuildersKt.runBlocking$default(null, new GeneralTask$messageParserListener$1$onChargeTimeout$1(this, null), 1, null);
        this.this$0.onStateChange(SDKRobotState.Error, "{\"error_type\":\"CanNotReach\",\"level\":\"Error\",\"detail\":\"charge overtime!\"}");
    }
}
