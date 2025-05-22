package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveByGroup;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.SolicitViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SolicitViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0006\u0010\u0019\u001a\u00020\u0017J\b\u0010\u001a\u001a\u00020\u0017H\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "moveByGroup", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup;", "moveByGroupStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByGroupStateListener;", "moveStatusState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel$MoveStatus;", "getMoveStatusState", "()Landroidx/lifecycle/MutableLiveData;", "state", "", "getState", "()I", "setState", "(I)V", "active", "", "cancel", "initMoveByGroup", "onCleared", "pause", "MoveStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SolicitViewModel extends BaseMoveViewModel {
    private MoveByGroup moveByGroup;
    private int state;
    private final String TAG = "SolicitViewModel";
    private final MutableLiveData<MoveStatus> moveStatusState = new MutableLiveData<>();
    private final MoveByGroupStateListener moveByGroupStateListener = new SingleMoveByGroupStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.SolicitViewModel$moveByGroupStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onArrive(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            SolicitViewModel.this.onArrive();
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onArrive : groupName = " + groupName + "; ");
            SolicitViewModel.this.getMoveStatusState().postValue(SolicitViewModel.MoveStatus.Finish);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            SolicitViewModel.this.onAvoid();
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onAvoid ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onCancel() {
            SolicitViewModel.this.onCancel();
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onCancel ");
            SolicitViewModel.this.getMoveStatusState().postValue(SolicitViewModel.MoveStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                SolicitViewModel.this.getMoveStatusState().postValue(SolicitViewModel.MoveStatus.Pause);
            }
            SolicitViewModel.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onFillIn() {
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onFillIn ");
            SolicitViewModel.this.onFillIn();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onMoving : event = " + event + "; ");
            SolicitViewModel.this.onMoving(event);
            if (event != RobotMoveEvent.STUCK) {
                VoicePlayTasks.INSTANCE.playDeliverMoving();
            }
            SolicitViewModel.this.getMoveStatusState().postValue(SolicitViewModel.MoveStatus.Moving);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onPause(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            SolicitViewModel.this.onPause();
            Pdlog.m3273d(SolicitViewModel.this.getTAG(), "onPause : groupName = " + groupName + "; ");
            SolicitViewModel.this.getMoveStatusState().postValue(SolicitViewModel.MoveStatus.Pause);
        }
    };

    /* compiled from: SolicitViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/SolicitViewModel$MoveStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum MoveStatus {
        Moving,
        Pause,
        Cancel,
        Finish
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<MoveStatus> getMoveStatusState() {
        return this.moveStatusState;
    }

    public final int getState() {
        return this.state;
    }

    public final void setState(int i) {
        this.state = i;
    }

    public final void initMoveByGroup() {
        Pdlog.m3273d(getTAG(), "initMoveByGroup ");
        String usherOrTake = RobotMapManager.INSTANCE.getUsherOrTake(this.state);
        MoveByGroup moveByGroup = null;
        if (usherOrTake != null) {
            MoveByGroupTask moveByGroupTask = new MoveByGroupTask(usherOrTake, null, 2, null);
            Pdlog.m3273d(getTAG(), "initMoveByGroup: " + moveByGroupTask + ' ');
            moveByGroup = RobotMoveManager.INSTANCE.moveByGroup(moveByGroupTask);
        }
        this.moveByGroup = moveByGroup;
        MoveByGroup moveByGroup2 = this.moveByGroup;
        if (moveByGroup2 != null) {
            moveByGroup2.setOnMoveStateListener(this.moveByGroupStateListener);
        }
        MoveByGroup moveByGroup3 = this.moveByGroup;
        if (moveByGroup3 != null) {
            moveByGroup3.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
        active();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        Pdlog.m3273d(getTAG(), "active ");
        super.active();
        getMoveErrorHelperLiveData().postValue(null);
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.active();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        Pdlog.m3273d(getTAG(), "pause ");
        super.pause();
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        Pdlog.m3273d(getTAG(), "cancel ");
        super.cancel();
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel, com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }
}
