package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.FactoryTestHelper;
import com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.MoveByGroup;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.extend.DeliveryInfoExtKt;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.GoHomeVm;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import com.pudutech.robot.module.report.task.ReportDelivery;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GoHomeVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0016J\b\u0010\u001b\u001a\u00020\u0016H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "backHomeStatusState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm$BackHomeStatus;", "getBackHomeStatusState", "()Landroidx/lifecycle/MutableLiveData;", "moveByGroup", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByGroup;", "moveByGroupStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveByGroupStateListener;", "onStopBack", "", "getOnStopBack", "reportDelivery", "Lcom/pudutech/robot/module/report/task/ReportDelivery;", "active", "", "cancel", "destory", "initMoveByGroup", "pause", "report", "BackHomeStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GoHomeVm extends BaseMoveViewModel {
    private MoveByGroup moveByGroup;
    private ReportDelivery reportDelivery;
    private final String TAG = "GoHomeVm";
    private final MutableLiveData<BackHomeStatus> backHomeStatusState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> onStopBack = new MutableLiveData<>();
    private final MoveByGroupStateListener moveByGroupStateListener = new SingleMoveByGroupStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.GoHomeVm$moveByGroupStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onArrive(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            GoHomeVm.this.onArrive();
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onArrive : groupName = " + groupName + "; ");
            GoHomeVm.this.report();
            GoHomeVm.this.getBackHomeStatusState().postValue(GoHomeVm.BackHomeStatus.Finish);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onAvoid ");
            GoHomeVm.this.onAvoid();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onCancel() {
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onCancel ");
            GoHomeVm.this.onCancel();
            GoHomeVm.this.report();
            GoHomeVm.this.getBackHomeStatusState().postValue(GoHomeVm.BackHomeStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                GoHomeVm.this.getBackHomeStatusState().postValue(GoHomeVm.BackHomeStatus.Pause);
                FactoryTestHelper.INSTANCE.recordError();
            }
            GoHomeVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onFillIn() {
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onFillIn ");
            GoHomeVm.this.onFillIn();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onMoving : event = " + event + "; ");
            GoHomeVm.this.onMoving(event);
            if (event == RobotMoveEvent.STUCK) {
                Pdlog.m3273d(GoHomeVm.this.getTAG(), "onMoving : event = STUCK");
            } else {
                VoicePlayTasks.INSTANCE.playDeliverMoving();
            }
            GoHomeVm.this.getBackHomeStatusState().postValue(GoHomeVm.BackHomeStatus.Moving);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByGroupStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByGroupStateListener
        public void onPause(String groupName) {
            Intrinsics.checkParameterIsNotNull(groupName, "groupName");
            Pdlog.m3273d(GoHomeVm.this.getTAG(), "onPause : groupName = " + groupName + "; ");
            GoHomeVm.this.onPause();
            GoHomeVm.this.getBackHomeStatusState().postValue(GoHomeVm.BackHomeStatus.Pause);
        }
    };

    /* compiled from: GoHomeVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/GoHomeVm$BackHomeStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum BackHomeStatus {
        Moving,
        Pause,
        Cancel,
        Finish
    }

    public GoHomeVm() {
        initMoveByGroup();
        this.reportDelivery = new ReportDelivery();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<BackHomeStatus> getBackHomeStatusState() {
        return this.backHomeStatusState;
    }

    public final MutableLiveData<Boolean> getOnStopBack() {
        return this.onStopBack;
    }

    private final void initMoveByGroup() {
        Pdlog.m3273d(getTAG(), "initMoveByGroup ");
        String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
        if (currentMapDiningOutLetChosen == null || currentMapDiningOutLetChosen.length() == 0) {
            this.onStopBack.postValue(true);
        }
        Pdlog.m3273d(getTAG(), "go " + RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen() + ' ');
        String currentMapDiningOutLetChosen2 = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
        this.moveByGroup = currentMapDiningOutLetChosen2 != null ? RobotMoveManager.INSTANCE.moveByGroup(new MoveByGroupTask(currentMapDiningOutLetChosen2, null, 2, null)) : null;
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

    public final void destory() {
        Pdlog.m3273d(getTAG(), "destory ");
        VoicePlayTasks.INSTANCE.stop();
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
        DeliveryInfoExtKt.setInfo$default(deliveryInfo, moveByGroupReport.getMoveReportData(), MovingTaskType.GO_HOME, null, 4, null);
        arrayList.add(deliveryInfo);
        ReportDelivery reportDelivery2 = this.reportDelivery;
        if (reportDelivery2 != null && (reportDelivery = reportDelivery2.set(arrayList, moveByGroupReport.getMoveReportData().getAllTime())) != null) {
            reportDelivery.report();
        }
        this.reportDelivery = (ReportDelivery) null;
    }
}
