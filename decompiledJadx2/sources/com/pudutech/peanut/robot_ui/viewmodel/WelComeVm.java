package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.ElevatorUtilizeState;
import com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveByGroup;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.ElevatorEventParam;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.extend.DeliveryInfoExtKt;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.WelComeVm;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import com.pudutech.robot.module.report.task.ReportDelivery;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WelComeVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0006\u0010\u0017\u001a\u00020\u0015J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "currentDes", "Landroidx/lifecycle/MutableLiveData;", "getCurrentDes", "()Landroidx/lifecycle/MutableLiveData;", "moveByGroup", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup;", "moveByGroupStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByGroupStateListener;", "reportDelivery", "Lcom/pudutech/robot/module/report/task/ReportDelivery;", "welComeStatusState", "Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm$WelComeStatus;", "getWelComeStatusState", "active", "", "cancel", "initWelComeName", "pause", "report", "WelComeStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeVm extends BaseMoveViewModel {
    private MoveByGroup moveByGroup;
    private final String TAG = "WelComeVm";
    private final MutableLiveData<WelComeStatus> welComeStatusState = new MutableLiveData<>();
    private final MutableLiveData<String> currentDes = new MutableLiveData<>();
    private final MoveByGroupStateListener moveByGroupStateListener = new MoveByGroupStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.WelComeVm$moveByGroupStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onElevatorUtilizeState(ElevatorUtilizeState state, ElevatorEventParam p1) {
            Intrinsics.checkParameterIsNotNull(state, "state");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onArrive(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            WelComeVm.this.onArrive();
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onArrive : groupName = " + groupName + "; ");
            WelComeVm.this.getCurrentDes().setValue(groupName);
            WelComeVm.this.report();
            WelComeVm.this.getWelComeStatusState().postValue(WelComeVm.WelComeStatus.Arrive);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            WelComeVm.this.onAvoid();
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onAvoid ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onCancel() {
            WelComeVm.this.onCancel();
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onCancel ");
            WelComeVm.this.report();
            WelComeVm.this.getWelComeStatusState().postValue(WelComeVm.WelComeStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                WelComeVm.this.getWelComeStatusState().postValue(WelComeVm.WelComeStatus.Pause);
            }
            WelComeVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onFillIn() {
            WelComeVm.this.onFillIn();
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onFillIn ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            WelComeVm.this.onMoving(event);
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onMoving : event = " + event + "; ");
            WelComeVm.this.getWelComeStatusState().postValue(WelComeVm.WelComeStatus.Moving);
            if (event != RobotMoveEvent.STUCK) {
                VoicePlayTasks.INSTANCE.playDeliverMoving();
            }
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onPause(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            WelComeVm.this.onPause();
            Pdlog.m3273d(WelComeVm.this.getTAG(), "onPause : groupName = " + groupName + "; ");
            WelComeVm.this.getWelComeStatusState().postValue(WelComeVm.WelComeStatus.Pause);
        }
    };
    private ReportDelivery reportDelivery = new ReportDelivery();

    /* compiled from: WelComeVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeVm$WelComeStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "Arrive", "NoPoint", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum WelComeStatus {
        Moving,
        Pause,
        Cancel,
        Finish,
        Arrive,
        NoPoint
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<WelComeStatus> getWelComeStatusState() {
        return this.welComeStatusState;
    }

    public final MutableLiveData<String> getCurrentDes() {
        return this.currentDes;
    }

    public final void initWelComeName() {
        String currentMapUsherChosen = RobotMapManager.INSTANCE.getCurrentMapUsherChosen();
        String str = currentMapUsherChosen;
        if (str == null || str.length() == 0) {
            this.welComeStatusState.postValue(WelComeStatus.NoPoint);
            getOnSchedulingLiveData().postValue(false);
        } else {
            this.moveByGroup = currentMapUsherChosen != null ? RobotMoveManager.INSTANCE.moveByGroup(new MoveByGroupTask(currentMapUsherChosen, null, 2, null)) : null;
        }
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.setOnMoveStateListener(this.moveByGroupStateListener);
        }
        MoveByGroup moveByGroup2 = this.moveByGroup;
        if (moveByGroup2 != null) {
            moveByGroup2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
        active();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        super.active();
        Pdlog.m3273d(getTAG(), "active ");
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.active();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
        MoveByGroup moveByGroup = this.moveByGroup;
        if (moveByGroup != null) {
            moveByGroup.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void report() {
        ReportDelivery reportDelivery;
        if (this.reportDelivery == null) {
            return;
        }
        MoveByGroup moveByGroup = this.moveByGroup;
        MoveByGroupReport moveByGroupReport = moveByGroup != null ? moveByGroup.getMoveByGroupReport() : null;
        if (moveByGroupReport == null) {
            Pdlog.m3274e(getTAG(), "report : moveReport is null");
            return;
        }
        ArrayList<DeliveryInfo> arrayList = new ArrayList<>();
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        DeliveryInfoExtKt.setInfo$default(deliveryInfo, moveByGroupReport.getMoveReportData(), MovingTaskType.GO_WELCOME_AREA, null, 4, null);
        arrayList.add(deliveryInfo);
        ReportDelivery reportDelivery2 = this.reportDelivery;
        if (reportDelivery2 != null && (reportDelivery = reportDelivery2.set(arrayList, moveByGroupReport.getMoveReportData().getAllTime())) != null) {
            reportDelivery.report();
        }
        this.reportDelivery = (ReportDelivery) null;
    }
}
