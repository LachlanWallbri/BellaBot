package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.interf.MoveByChargeListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.move.MoveByCharge;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.ChargeArriveState;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByChargeTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.OnKeyChargingVm;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnKeyChargingVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0014J\b\u0010\u001a\u001a\u00020\u0016H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/OnKeyChargingVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "<set-?>", "Lcom/pudutech/mirsdkwrap/lib/move/bean/ChargeArriveState;", "arriveState", "getArriveState", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/ChargeArriveState;", "moveByCharge", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByCharge;", "moveByGroupStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByChargeListener;", "moveStatusState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/viewmodel/OnKeyChargingVm$MoveStatus;", "getMoveStatusState", "()Landroidx/lifecycle/MutableLiveData;", "active", "", "cancel", "initMoveByGroup", "onCleared", "pause", "MoveStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OnKeyChargingVm extends BaseMoveViewModel {
    private ChargeArriveState arriveState;
    private MoveByCharge moveByCharge;
    private final String TAG = "OnKeyChargingVm";
    private final MutableLiveData<MoveStatus> moveStatusState = new MutableLiveData<>();
    private final MoveByChargeListener moveByGroupStateListener = new MoveByChargeListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.OnKeyChargingVm$moveByGroupStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByChargeListener
        public void onArrive(ChargeArriveState chargeArriveState) {
            Intrinsics.checkParameterIsNotNull(chargeArriveState, "chargeArriveState");
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onArrive : chargeArriveState = " + chargeArriveState + "; ");
            OnKeyChargingVm.this.onArrive();
            OnKeyChargingVm.this.arriveState = chargeArriveState;
            OnKeyChargingVm.this.getMoveStatusState().postValue(OnKeyChargingVm.MoveStatus.Finish);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByChargeListener
        public void onPause() {
            OnKeyChargingVm.this.onPause();
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onPause ");
            OnKeyChargingVm.this.getMoveStatusState().postValue(OnKeyChargingVm.MoveStatus.Pause);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            OnKeyChargingVm.this.onAvoid();
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onAvoid ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onElevatorUtilizeState(ElevatorUtilizeState state, ElevatorEventParam p1) {
            Intrinsics.checkParameterIsNotNull(state, "state");
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onElevatorUtilizeState : state = " + state + "; p1 = " + p1 + "; ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByChargeListener
        public void onCancel() {
            OnKeyChargingVm.this.onCancel();
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onCancel ");
            OnKeyChargingVm.this.getMoveStatusState().postValue(OnKeyChargingVm.MoveStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                OnKeyChargingVm.this.getMoveStatusState().postValue(OnKeyChargingVm.MoveStatus.Pause);
            }
            OnKeyChargingVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3273d(OnKeyChargingVm.this.getTAG(), "onMoving : event = " + event + "; ");
            OnKeyChargingVm.this.onMoving(event);
            if (event != RobotMoveEvent.STUCK) {
                VoicePlayTasks.INSTANCE.playDeliverMoving();
            }
            OnKeyChargingVm.this.getMoveStatusState().postValue(OnKeyChargingVm.MoveStatus.Moving);
        }
    };

    /* compiled from: OnKeyChargingVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/OnKeyChargingVm$MoveStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum MoveStatus {
        Moving,
        Pause,
        Cancel,
        Finish
    }

    public OnKeyChargingVm() {
        initMoveByGroup();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<MoveStatus> getMoveStatusState() {
        return this.moveStatusState;
    }

    public final ChargeArriveState getArriveState() {
        return this.arriveState;
    }

    private final void initMoveByGroup() {
        Pdlog.m3273d(getTAG(), "initMoveByGroup ");
        this.moveByCharge = RobotMoveManager.INSTANCE.moveByCharge(new MoveByChargeTask(Constans.INSTANCE.getChargingPosition()));
        MoveByCharge moveByCharge = this.moveByCharge;
        if (moveByCharge != null) {
            moveByCharge.setOnMoveStateListener(this.moveByGroupStateListener);
        }
        MoveByCharge moveByCharge2 = this.moveByCharge;
        if (moveByCharge2 != null) {
            moveByCharge2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
        active();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        super.active();
        Pdlog.m3273d(getTAG(), "active ");
        MoveByCharge moveByCharge = this.moveByCharge;
        if (moveByCharge != null) {
            moveByCharge.active();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
        MoveByCharge moveByCharge = this.moveByCharge;
        if (moveByCharge != null) {
            moveByCharge.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
        MoveByCharge moveByCharge = this.moveByCharge;
        if (moveByCharge != null) {
            moveByCharge.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel, com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }
}
