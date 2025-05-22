package com.pudutech.peanut.robot_ui.viewmodel;

import android.os.SystemClock;
import androidx.lifecycle.MutableLiveData;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
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
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.robot_ui.extend.DeliveryInfoExtKt;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.p063ui.DeliverActivity;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.DeliverVm;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import com.pudutech.robot.module.report.task.ReportDelivery;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0017\u0018\u00002\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020*H\u0016J\u0006\u0010,\u001a\u00020*J \u0010-\u001a\u0012\u0012\u0004\u0012\u00020.0$j\b\u0012\u0004\u0012\u00020.`&2\u0006\u0010/\u001a\u00020\u0004H\u0002J&\u00100\u001a\u00020*2\u0016\u00101\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&2\u0006\u00102\u001a\u000203J\b\u00104\u001a\u00020*H\u0014J\b\u00105\u001a\u00020*H\u0016J\b\u00106\u001a\u00020*H\u0002J\u0010\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\tH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010#\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006:"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "currentDes", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "getCurrentDes", "()Landroidx/lifecycle/MutableLiveData;", "deliverStatusState", "Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm$DeliverStatus;", "getDeliverStatusState", "<set-?>", "", "hasLeft", "getHasLeft", "()Z", "moveByDestination", "Lcom/pudutech/mirsdkwrap/lib/move/MoveByDestination;", "moveByDestinationStateListener", "com/pudutech/peanut/robot_ui/viewmodel/DeliverVm$moveByDestinationStateListener$1", "Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm$moveByDestinationStateListener$1;", "reportDelivery", "Lcom/pudutech/robot/module/report/task/ReportDelivery;", "reportType", "Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;", "getReportType", "()Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;", "setReportType", "(Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;)V", AUserTrack.UTKEY_START_TIME, "", "trays", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "getTrays", "()Ljava/util/ArrayList;", "active", "", "cancel", "finishOne", "getTrayList", "", "des", "initTask", "tableTaskList", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "onCleared", "pause", "report", "setTrayDeliveryModel", LinkFormat.DOMAIN, "DeliverStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverVm extends BaseMoveViewModel {
    private MoveByDestination moveByDestination;
    private long startTime;
    private final String TAG = "DeliverVm";
    private final ArrayList<TrayModel> trays = new ArrayList<>();
    private final MutableLiveData<DeliverStatus> deliverStatusState = new MutableLiveData<>();
    private final MutableLiveData<Destination> currentDes = new MutableLiveData<>();
    private boolean hasLeft = true;
    private final DeliverVm$moveByDestinationStateListener$1 moveByDestinationStateListener = new SingleMoveByDestinationStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.DeliverVm$moveByDestinationStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onArrive(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            DeliverVm.this.onArrive();
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onArrive : d = " + d + "; ");
            FactoryTestHelper.INSTANCE.recordDelivery();
            DeliverVm.this.getCurrentDes().setValue(d);
            Iterator<T> it = DeliverVm.this.getTrays().iterator();
            while (it.hasNext()) {
                for (DeliveryModel deliveryModel : ((TrayModel) it.next()).getAllDestinations()) {
                    if (Intrinsics.areEqual(deliveryModel.getDestination(), d.getName())) {
                        deliveryModel.setStatus(DeliveryModel.TaskStatus.ARRIVE);
                    }
                }
            }
            DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Arrive);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onDone(Destination d) {
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onDone : d = " + d + "; ");
            DeliverVm.this.onDone();
            if (d == null) {
                DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.AllTaskFinish);
                DeliverVm.this.report();
                return;
            }
            Iterator<T> it = DeliverVm.this.getTrays().iterator();
            while (it.hasNext()) {
                for (DeliveryModel deliveryModel : ((TrayModel) it.next()).getAllDestinations()) {
                    if (Intrinsics.areEqual(deliveryModel.getDestination(), d.getName())) {
                        deliveryModel.setStatus(DeliveryModel.TaskStatus.DONE);
                    }
                }
            }
            DeliverVm.this.getCurrentDes().setValue(d);
            TableTaskManager.INSTANCE.finishTask(d.getName());
            DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.FinishOne);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onPause(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onPause : d = " + d + "; ");
            DeliverVm.this.onPause();
            DeliverVm.this.getCurrentDes().setValue(d);
            DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Pause);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onCancel(ArrayList<Destination> d, boolean left) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onCancel : d = " + d + "; hasLeft = " + DeliverVm.this.getHasLeft() + "; ");
            DeliverVm.this.onCancel();
            if (DeliverActivity.INSTANCE.getModifyState() != 0) {
                DeliverActivity.INSTANCE.setModifyState(0);
            } else {
                for (Destination destination : d) {
                    TableTaskManager.INSTANCE.finishTask(destination.getName());
                    Iterator<T> it = DeliverVm.this.getTrays().iterator();
                    while (it.hasNext()) {
                        for (DeliveryModel deliveryModel : ((TrayModel) it.next()).getAllDestinations()) {
                            if (Intrinsics.areEqual(deliveryModel.getDestination(), destination.getName()) && deliveryModel.getStatus() == DeliveryModel.TaskStatus.IDLE) {
                                deliveryModel.setStatus(DeliveryModel.TaskStatus.CANCEL);
                            }
                        }
                    }
                }
            }
            DeliverVm.this.hasLeft = left;
            DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Cancel);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener
        public void onStart(Destination d) {
            Intrinsics.checkParameterIsNotNull(d, "d");
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onStart : d = " + d + "; ");
            DeliverVm.this.onStart();
            DeliverVm.this.setTrayDeliveryModel(d);
            DeliverVm.this.getCurrentDes().setValue(d);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onMoving : event = " + event + "; ");
            DeliverVm.this.onMoving(event);
            if (event == RobotMoveEvent.APPROACHING) {
                DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Approaching);
            } else {
                DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Moving);
                if (event != RobotMoveEvent.STUCK) {
                    VoicePlayTasks.INSTANCE.playDeliverMoving();
                }
            }
            DeliverVm.this.getOnSchedulingLiveData().postValue(false);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(DeliverVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                DeliverVm.this.getDeliverStatusState().postValue(DeliverVm.DeliverStatus.Pause);
                FactoryTestHelper.INSTANCE.recordError();
            }
            DeliverVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveByDestinationStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            DeliverVm.this.onAvoid();
        }
    };
    private ReportDelivery reportDelivery = new ReportDelivery();
    private MovingTaskType reportType = MovingTaskType.Delivery;

    /* compiled from: DeliverVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/DeliverVm$DeliverStatus;", "", "(Ljava/lang/String;I)V", "Pause", "Cancel", "Moving", "AllTaskFinish", "AllLeftCancel", "Arrive", "FinishOne", "Approaching", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum DeliverStatus {
        Pause,
        Cancel,
        Moving,
        AllTaskFinish,
        AllLeftCancel,
        Arrive,
        FinishOne,
        Approaching
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final ArrayList<TrayModel> getTrays() {
        return this.trays;
    }

    public final MutableLiveData<DeliverStatus> getDeliverStatusState() {
        return this.deliverStatusState;
    }

    public final MutableLiveData<Destination> getCurrentDes() {
        return this.currentDes;
    }

    public final boolean getHasLeft() {
        return this.hasLeft;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTrayDeliveryModel(Destination d) {
        for (TrayModel trayModel : this.trays) {
            trayModel.setCurrent(trayModel.getDeliveryModel(d.getName()));
        }
    }

    public final void initTask(ArrayList<TrayModel> tableTaskList, MoveSortType sortType) {
        Intrinsics.checkParameterIsNotNull(tableTaskList, "tableTaskList");
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        this.trays.addAll(tableTaskList);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.trays.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((TrayModel) it.next()).getAllDestinationStr());
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.distinct(arrayList));
        Pdlog.m3273d(getTAG(), "initTask : tableTaskList = " + arrayList2 + "; sortType = " + sortType + "; ");
        RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(new MoveDestinationTask(new Destination((String) it2.next(), "", "", null, 0.0d, 0.0d), MoveMode.Normal));
        }
        this.moveByDestination = robotMoveManager.moveByDestination(arrayList4, sortType);
        MoveByDestination moveByDestination = this.moveByDestination;
        if (moveByDestination == null) {
            Intrinsics.throwNpe();
        }
        moveByDestination.setOnMoveStateListener(this.moveByDestinationStateListener);
        MoveByDestination moveByDestination2 = this.moveByDestination;
        if (moveByDestination2 == null) {
            Intrinsics.throwNpe();
        }
        moveByDestination2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        MoveByDestination moveByDestination3 = this.moveByDestination;
        if (moveByDestination3 == null) {
            Intrinsics.throwNpe();
        }
        moveByDestination3.active();
        this.startTime = SystemClock.elapsedRealtime();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        Pdlog.m3273d(getTAG(), "active ");
        super.active();
        MoveByDestination moveByDestination = this.moveByDestination;
        if (moveByDestination != null) {
            moveByDestination.active();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
        MoveByDestination moveByDestination = this.moveByDestination;
        if (moveByDestination != null) {
            moveByDestination.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
        MoveByDestination moveByDestination = this.moveByDestination;
        if (moveByDestination != null) {
            moveByDestination.cancelAll();
        }
    }

    public final void finishOne() {
        Pdlog.m3273d(getTAG(), "finishOne ");
        Iterator<T> it = this.trays.iterator();
        while (it.hasNext()) {
            for (DeliveryModel deliveryModel : ((TrayModel) it.next()).getAllDestinations()) {
                String destination = deliveryModel.getDestination();
                Destination value = this.currentDes.getValue();
                if (Intrinsics.areEqual(destination, value != null ? value.getName() : null) && deliveryModel.getStatus() == DeliveryModel.TaskStatus.IDLE) {
                    deliveryModel.setStatus(DeliveryModel.TaskStatus.ADVANCE_DONE);
                }
            }
        }
        MoveByDestination moveByDestination = this.moveByDestination;
        if (moveByDestination != null) {
            moveByDestination.cancelOne();
        }
    }

    public final MovingTaskType getReportType() {
        return this.reportType;
    }

    public final void setReportType(MovingTaskType movingTaskType) {
        Intrinsics.checkParameterIsNotNull(movingTaskType, "<set-?>");
        this.reportType = movingTaskType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void report() {
        if (this.reportDelivery == null) {
            return;
        }
        MoveByDestination moveByDestination = this.moveByDestination;
        ArrayList<MoveReportData> moveReport = moveByDestination != null ? moveByDestination.getMoveReport() : null;
        ArrayList<DeliveryInfo> arrayList = new ArrayList<>();
        if (moveReport != null) {
            for (MoveReportData moveReportData : moveReport) {
                DeliveryInfo deliveryInfo = new DeliveryInfo();
                DeliveryInfoExtKt.setInfo(deliveryInfo, moveReportData, this.reportType, getTrayList(moveReportData.getGoalId()));
                arrayList.add(deliveryInfo);
            }
        }
        ReportDelivery reportDelivery = this.reportDelivery;
        if (reportDelivery != null) {
            ReportDelivery reportDelivery2 = reportDelivery.set(arrayList, this.startTime != 0 ? SystemClock.elapsedRealtime() - this.startTime : 0L);
            if (reportDelivery2 != null) {
                reportDelivery2.report();
            }
        }
        this.reportDelivery = (ReportDelivery) null;
    }

    private final ArrayList<Integer> getTrayList(String des) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0;
        for (Object obj : this.trays) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) obj).getDeliveryModel(des) != null) {
                arrayList.add(Integer.valueOf(i));
            }
            i = i2;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel, com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        Pdlog.m3273d(getTAG(), "onCleared ");
    }
}
