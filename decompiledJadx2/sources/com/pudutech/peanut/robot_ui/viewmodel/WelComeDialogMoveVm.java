package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.FactoryTestHelper;
import com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.move.MoveBySolicit;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.viewmodel.CruiseVm;
import com.pudutech.robot.module.report.task.ReportCruiseTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WelComeDialogMoveVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0006\u0010\u0015\u001a\u00020\u0013J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/WelComeDialogMoveVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "cruiseStatusState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm$CruiseStatus;", "getCruiseStatusState", "()Landroidx/lifecycle/MutableLiveData;", "handlerConnectionLD", "getHandlerConnectionLD", "moveBySolicit", "Lcom/pudutech/mirsdkwrap/lib/move/MoveBySolicit;", "moveCruiseStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveCruiseStateListener;", "active", "", "cancel", "initMoveSolicit", "onCleared", "pause", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WelComeDialogMoveVm extends BaseMoveViewModel {
    private MoveBySolicit moveBySolicit;
    private final String TAG = "WelComeDialogMoveVm";
    private final MutableLiveData<String> handlerConnectionLD = new MutableLiveData<>();
    private final MutableLiveData<CruiseVm.CruiseStatus> cruiseStatusState = new MutableLiveData<>();
    private final MoveCruiseStateListener moveCruiseStateListener = new SingleMoveCruiseStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.WelComeDialogMoveVm$moveCruiseStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onPause() {
            Pdlog.m3273d(WelComeDialogMoveVm.this.getTAG(), "onPause ");
            WelComeDialogMoveVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Pause);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onCancel() {
            Pdlog.m3273d(WelComeDialogMoveVm.this.getTAG(), "onCancel ");
            WelComeDialogMoveVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onStayPointArrive(String s) {
            Intrinsics.checkParameterIsNotNull(s, "s");
            Pdlog.m3273d(WelComeDialogMoveVm.this.getTAG(), "onStayPointArrive : s = " + s + "; ");
            WelComeDialogMoveVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Finish);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onGoalCruise() {
            Pdlog.m3273d(WelComeDialogMoveVm.this.getTAG(), "onGoalCruise ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            WelComeDialogMoveVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Moving);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(WelComeDialogMoveVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                FactoryTestHelper.INSTANCE.recordError();
            }
            ReportCruiseTask.INSTANCE.trackingCruiseEndEvent();
            WelComeDialogMoveVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            WelComeDialogMoveVm.this.onAvoid();
        }
    };

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<String> getHandlerConnectionLD() {
        return this.handlerConnectionLD;
    }

    public final MutableLiveData<CruiseVm.CruiseStatus> getCruiseStatusState() {
        return this.cruiseStatusState;
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        super.active();
        Pdlog.m3273d(getTAG(), "active ");
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel, com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        FactoryTestHelper.INSTANCE.recordCruiseEnd();
    }

    public final void initMoveSolicit() {
        Pdlog.m3273d(getTAG(), "initMoveSolicit ");
        this.moveBySolicit = RobotMoveManager.INSTANCE.solicit();
        MoveBySolicit moveBySolicit = this.moveBySolicit;
        if (moveBySolicit != null) {
            moveBySolicit.setOnMoveStateListener(this.moveCruiseStateListener);
        }
        MoveBySolicit moveBySolicit2 = this.moveBySolicit;
        if (moveBySolicit2 != null) {
            moveBySolicit2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
    }
}
