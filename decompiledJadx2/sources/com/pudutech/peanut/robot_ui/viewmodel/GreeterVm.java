package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.FactoryTestHelper;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.move.MoveByDestination;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveMode;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.robot_ui.extend.DeliveryInfoExtKt;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.GreeterVm;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import com.pudutech.robot.module.report.task.ReportDelivery;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GreeterVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0004J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\b\u0010 \u001a\u00020\u001bH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "currentDes", "Landroidx/lifecycle/MutableLiveData;", "getCurrentDes", "()Landroidx/lifecycle/MutableLiveData;", "greeterStatusState", "Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm$GreeterStatus;", "getGreeterStatusState", "hasLeft", "", "getHasLeft", "()Z", "setHasLeft", "(Z)V", "moveByGroupStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/SingleMoveByDestinationStateListener;", "moveBySingleDestination", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByDestination;", "reportDelivery", "Lcom/pudutech/robot/module/report/task/ReportDelivery;", "active", "", "cancel", "initGreeterName", "mGreeterName", "pause", "report", "GreeterStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GreeterVm extends BaseMoveViewModel {
    private MoveByDestination moveBySingleDestination;
    private final String TAG = "GreeterVm";
    private final MutableLiveData<GreeterStatus> greeterStatusState = new MutableLiveData<>();
    private final MutableLiveData<String> currentDes = new MutableLiveData<>();
    private boolean hasLeft = true;
    private final SingleMoveByDestinationStateListener moveByGroupStateListener = new SingleMoveByDestinationStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.GreeterVm$moveByGroupStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onArrive(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            GreeterVm.this.onArrive();
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onArrive : groupName = " + d.getName() + "; ");
            GreeterVm.this.getCurrentDes().setValue(d.getName());
            GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Arrive);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onDone(Destination d) {
            GreeterVm.this.onDone();
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onDone : d = " + d + "; ");
            if (d == null) {
                if (GreeterVm.this.getHasLeft()) {
                    GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Finish);
                } else {
                    GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Cancel);
                }
                GreeterVm.this.report();
            }
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onPause(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            GreeterVm.this.onPause();
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onPause : groupName = " + d.getName() + "; ");
            GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Pause);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onCancel(ArrayList<Destination> d, boolean left) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onCancel : d = " + d + "; hasLeft = " + left + "; ");
            GreeterVm.this.onCancel();
            GreeterVm.this.setHasLeft(left);
            GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onStart(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            GreeterVm.this.onStart();
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onStart : d = " + d + "; ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            GreeterVm.this.onMoving(event);
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onMoving : event = " + event + "; ");
            if (event == RobotMoveEvent.STUCK) {
                Pdlog.m3273d(GreeterVm.this.getTAG(), "onMoving : event = STUCK");
            } else {
                VoicePlayTasks.INSTANCE.playDeliverMoving();
            }
            GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Moving);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                GreeterVm.this.getGreeterStatusState().postValue(GreeterVm.GreeterStatus.Pause);
                FactoryTestHelper.INSTANCE.recordError();
            }
            GreeterVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            Pdlog.m3273d(GreeterVm.this.getTAG(), "onAvoid ");
            GreeterVm.this.onAvoid();
        }
    };
    private ReportDelivery reportDelivery = new ReportDelivery();

    /* compiled from: GreeterVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/GreeterVm$GreeterStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "Arrive", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum GreeterStatus {
        Moving,
        Pause,
        Cancel,
        Finish,
        Arrive
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<GreeterStatus> getGreeterStatusState() {
        return this.greeterStatusState;
    }

    public final MutableLiveData<String> getCurrentDes() {
        return this.currentDes;
    }

    public final boolean getHasLeft() {
        return this.hasLeft;
    }

    public final void setHasLeft(boolean z) {
        this.hasLeft = z;
    }

    public final void initGreeterName(String mGreeterName) {
        Intrinsics.checkParameterIsNotNull(mGreeterName, "mGreeterName");
        Pdlog.m3273d(getTAG(), "initMoveByGroup ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(mGreeterName);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.distinct(arrayList));
        Pdlog.m3273d(getTAG(), "initTask : mGreeterName = " + mGreeterName + ";  ");
        RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            arrayList4.add(new MoveDestinationTask(new Destination((String) it.next(), "", "", null, 0.0d, 0.0d), MoveMode.Normal));
        }
        this.moveBySingleDestination = robotMoveManager.moveByDestination(arrayList4, MoveSortType.AUTO);
        MoveByDestination moveByDestination = this.moveBySingleDestination;
        if (moveByDestination != null) {
            moveByDestination.setOnMoveStateListener(this.moveByGroupStateListener);
        }
        MoveByDestination moveByDestination2 = this.moveBySingleDestination;
        if (moveByDestination2 != null) {
            moveByDestination2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
        active();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        super.active();
        Pdlog.m3273d(getTAG(), "active ");
        MoveByDestination moveByDestination = this.moveBySingleDestination;
        if (moveByDestination != null) {
            moveByDestination.active();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
        MoveByDestination moveByDestination = this.moveBySingleDestination;
        if (moveByDestination != null) {
            moveByDestination.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
        MoveByDestination moveByDestination = this.moveBySingleDestination;
        if (moveByDestination != null) {
            moveByDestination.cancelAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void report() {
        ReportDelivery reportDelivery;
        if (this.reportDelivery == null) {
            return;
        }
        MoveByDestination moveByDestination = this.moveBySingleDestination;
        ArrayList<MoveReportData> moveReport = moveByDestination != null ? moveByDestination.getMoveReport() : null;
        if (moveReport == null || moveReport.size() == 0) {
            Pdlog.m3274e(getTAG(), "report : moveReport is null");
            return;
        }
        ArrayList<DeliveryInfo> arrayList = new ArrayList<>();
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        MoveReportData moveReportData = moveReport.get(0);
        Intrinsics.checkExpressionValueIsNotNull(moveReportData, "moveReport[0]");
        DeliveryInfoExtKt.setInfo$default(deliveryInfo, moveReportData, MovingTaskType.SEAT_GUEST, null, 4, null);
        arrayList.add(deliveryInfo);
        ReportDelivery reportDelivery2 = this.reportDelivery;
        if (reportDelivery2 != null && (reportDelivery = reportDelivery2.set(arrayList, moveReport.get(0).getAllTime())) != null) {
            reportDelivery.report();
        }
        this.reportDelivery = (ReportDelivery) null;
    }
}
