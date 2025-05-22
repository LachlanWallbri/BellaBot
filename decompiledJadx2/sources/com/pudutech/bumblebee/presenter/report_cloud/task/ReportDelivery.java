package com.pudutech.bumblebee.presenter.report_cloud.task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.Delivery;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.Info;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.TaskType;
import com.pudutech.robot.module.report.protocol.DeliveryReport;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportDelivery.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000g\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u0014J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ.\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` J\u0006\u0010!\u001a\u00020\u0014J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020$J\u0006\u0010'\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@BX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportDelivery;", "", "()V", "TAG", "", "currentDestination", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/Info;", "<set-?>", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/Delivery;", "delivery", "mReportDelivery", "Lcom/pudutech/robot/module/report/task/ReportDelivery;", "moveTaskModel", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/MoveTaskModel;", "spdListener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportDelivery$spdListener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportDelivery$spdListener$1;", "startTimestamp", "", "addMeters", "", "speed", "", "createNewDelivery", "goHome", "des", "type", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/TaskType;", "goNext", "trays", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "markTaking", "setAutoMove", "isActive", "", "setCurrentTaskDoneOrCancel", "isDone", "toCloud", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportDelivery {
    private static Info currentDestination;
    private static Delivery delivery;
    private static com.pudutech.robot.module.report.task.ReportDelivery mReportDelivery;
    private static MoveTaskModel moveTaskModel;
    private static long startTimestamp;
    public static final ReportDelivery INSTANCE = new ReportDelivery();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ReportDelivery$spdListener$1 spdListener = new SpeedListener() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery$spdListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            ReportDelivery.INSTANCE.addMeters(p0);
        }
    };

    private ReportDelivery() {
    }

    public final void toCloud() {
        Pdlog.m3275i(TAG, "to cloud. delivery=" + delivery);
        if (currentDestination != null) {
            Pdlog.m3275i(TAG, "report without setting task done or cancel. so set task false");
            INSTANCE.setCurrentTaskDoneOrCancel(false);
        }
        Delivery delivery2 = delivery;
        if (delivery2 != null) {
            delivery2.battery = CoreDevices.INSTANCE.getBattery().getPowerPercent();
            ArrayList<DeliveryInfo> arrayList = new ArrayList<>();
            for (Info info : delivery2.info) {
                DeliveryInfo deliveryInfo = new DeliveryInfo();
                deliveryInfo.goal_id = info.goal_id;
                deliveryInfo.tray_list = info.tray_list;
                deliveryInfo.task_type = info.task_type;
                deliveryInfo.order_id = info.order_id;
                deliveryInfo.status = info.status;
                deliveryInfo.theme = info.theme;
                deliveryInfo.mileage = info.mileage;
                deliveryInfo.duration = info.duration;
                deliveryInfo.average = info.average;
                deliveryInfo.duration_wait = info.duration_wait;
                deliveryInfo.duration_pause = info.duration_pause;
                arrayList.add(deliveryInfo);
            }
            delivery2.total_time = (SystemClock.elapsedRealtime() - startTimestamp) / 1000;
            com.pudutech.robot.module.report.task.ReportDelivery reportDelivery = mReportDelivery;
            if (reportDelivery != null) {
                reportDelivery.set(arrayList, delivery2.total_time);
            }
            com.pudutech.robot.module.report.task.ReportDelivery reportDelivery2 = mReportDelivery;
            if (reportDelivery2 != null) {
                reportDelivery2.report();
            }
            Pdlog.m3273d(TAG, "toCloud report " + delivery);
        }
        delivery = (Delivery) null;
        mReportDelivery = (com.pudutech.robot.module.report.task.ReportDelivery) null;
        SDK.INSTANCE.getSpeedListeners().removeListener(spdListener);
    }

    public final void createNewDelivery() {
        DeliveryReport deliveryReport;
        Pdlog.m3275i(TAG, "create new delivery");
        delivery = new Delivery();
        mReportDelivery = new com.pudutech.robot.module.report.task.ReportDelivery();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Delivery delivery2 = delivery;
        if (delivery2 != null) {
            delivery2.task_id = currentTimeMillis;
        }
        com.pudutech.robot.module.report.task.ReportDelivery reportDelivery = mReportDelivery;
        if (reportDelivery != null && (deliveryReport = reportDelivery.getDeliveryReport()) != null) {
            deliveryReport.setTask_id(currentTimeMillis);
        }
        startTimestamp = SystemClock.elapsedRealtime();
        SDK.INSTANCE.getSpeedListeners().addListener(spdListener);
    }

    public final void goNext(String des, TaskType type, ArrayList<Integer> trays) {
        Intrinsics.checkParameterIsNotNull(des, "des");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        Pdlog.m3273d(TAG, "go next " + des + ' ' + type + ' ' + trays);
        Info info = new Info();
        info.goal_id = des;
        info.task_type = type.getValue();
        info.tray_list = trays;
        info.order_id = SystemClock.elapsedRealtime();
        currentDestination = info;
        moveTaskModel = new MoveTaskModel();
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setStartTimestamp_ms(SystemClock.elapsedRealtime());
        }
    }

    public static /* synthetic */ void goHome$default(ReportDelivery reportDelivery, String str, TaskType taskType, int i, Object obj) {
        if ((i & 2) != 0) {
            taskType = TaskType.GO_HOME;
        }
        reportDelivery.goHome(str, taskType);
    }

    public final void goHome(String des, TaskType type) {
        Intrinsics.checkParameterIsNotNull(des, "des");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3275i(TAG, "go home " + des + ". delivery=" + delivery);
        if (delivery == null) {
            Pdlog.m3275i(TAG, "go home only. create new reportDelivery task");
            INSTANCE.createNewDelivery();
            Unit unit = Unit.INSTANCE;
        }
        Info info = new Info();
        info.goal_id = des;
        info.task_type = type.getValue();
        info.order_id = SystemClock.elapsedRealtime();
        currentDestination = info;
        moveTaskModel = new MoveTaskModel();
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setStartTimestamp_ms(SystemClock.elapsedRealtime());
        }
    }

    public final void setCurrentTaskDoneOrCancel(boolean isDone) {
        Delivery delivery2;
        List<Info> list;
        Info info;
        Info info2;
        Pdlog.m3275i(TAG, "setCurrentTaskDoneOrCancel. done=" + isDone);
        Info info3 = currentDestination;
        if (info3 != null) {
            info3.status = isDone;
        }
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setEndTimestamp_ms(SystemClock.elapsedRealtime());
            Info info4 = currentDestination;
            if (info4 != null) {
                info4.mileage = moveTaskModel2.getMileages_m();
            }
            Info info5 = currentDestination;
            if (info5 != null) {
                info5.duration = (moveTaskModel2.getEndTimestamp_ms() - moveTaskModel2.getStartTimestamp_ms()) / 1000;
            }
            if (Intrinsics.areEqual((Object) moveTaskModel2.getIsActive(), (Object) false)) {
                moveTaskModel2.setDurationPause_ms(moveTaskModel2.getDurationPause_ms() + (SystemClock.elapsedRealtime() - moveTaskModel2.getPauseTimestamp_ms()));
            }
            Info info6 = currentDestination;
            if (info6 != null) {
                info6.duration_pause = moveTaskModel2.getDurationPause_ms() / 1000;
            }
            Pdlog.m3273d(TAG, "calculate average " + moveTaskModel2.getMileages_m() + "  " + moveTaskModel2.getValidSpeedTime_ms());
            if (moveTaskModel2.getValidSpeedTime_ms() > 0 && (info2 = currentDestination) != null) {
                info2.average = (moveTaskModel2.getMileages_m() / moveTaskModel2.getValidSpeedTime_ms()) * 1000.0d;
            }
            moveTaskModel2.setTakingEndTimestamp_ms(SystemClock.elapsedRealtime());
            if (moveTaskModel2.getTakingStartTimestamp_ms() != 0 && (info = currentDestination) != null) {
                info.duration_wait = (moveTaskModel2.getTakingEndTimestamp_ms() - moveTaskModel2.getTakingStartTimestamp_ms()) / 1000;
            }
        }
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("duration_wait=");
        Info info7 = currentDestination;
        sb.append(info7 != null ? Long.valueOf(info7.duration) : null);
        sb.append(" endTimestamp= ");
        MoveTaskModel moveTaskModel3 = moveTaskModel;
        sb.append(moveTaskModel3 != null ? Long.valueOf(moveTaskModel3.getTakingEndTimestamp_ms()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        Info info8 = currentDestination;
        if (info8 != null && (delivery2 = delivery) != null && (list = delivery2.info) != null) {
            list.add(info8);
        }
        currentDestination = (Info) null;
        moveTaskModel = (MoveTaskModel) null;
    }

    public final void setAutoMove(boolean isActive) {
        MoveTaskModel moveTaskModel2;
        Pdlog.m3275i(TAG, "set auto move=" + isActive + ' ');
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("moveTask isActive=");
        MoveTaskModel moveTaskModel3 = moveTaskModel;
        sb.append(moveTaskModel3 != null ? moveTaskModel3.getIsActive() : null);
        sb.append(" activeTimestamp_ms=");
        MoveTaskModel moveTaskModel4 = moveTaskModel;
        sb.append(moveTaskModel4 != null ? Long.valueOf(moveTaskModel4.getActiveTimestamp_ms()) : null);
        sb.append(" pauseTimestamp_ms=");
        MoveTaskModel moveTaskModel5 = moveTaskModel;
        sb.append(moveTaskModel5 != null ? Long.valueOf(moveTaskModel5.getPauseTimestamp_ms()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        MoveTaskModel moveTaskModel6 = moveTaskModel;
        if (Intrinsics.areEqual(moveTaskModel6 != null ? moveTaskModel6.getIsActive() : null, Boolean.valueOf(isActive)) || (moveTaskModel2 = moveTaskModel) == null) {
            return;
        }
        if (isActive) {
            moveTaskModel2.setActiveTimestamp_ms(SystemClock.elapsedRealtime());
            if (moveTaskModel2.getPauseTimestamp_ms() != 0) {
                moveTaskModel2.setDurationPause_ms(moveTaskModel2.getDurationPause_ms() + (moveTaskModel2.getActiveTimestamp_ms() - moveTaskModel2.getPauseTimestamp_ms()));
            }
        } else {
            moveTaskModel2.setPauseTimestamp_ms(SystemClock.elapsedRealtime());
        }
        moveTaskModel2.setActive(Boolean.valueOf(isActive));
    }

    public final void markTaking() {
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            moveTaskModel2.setTakingStartTimestamp_ms(SystemClock.elapsedRealtime());
        }
        Pdlog.m3273d(TAG, "mark taking " + moveTaskModel + "?.takingStartTimestamp_ms");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addMeters(double speed) {
        MoveTaskModel moveTaskModel2 = moveTaskModel;
        if (moveTaskModel2 != null) {
            double elapsedRealtime = SystemClock.elapsedRealtime() - moveTaskModel2.getLastSpeedTimestap();
            moveTaskModel2.setMileages_m(moveTaskModel2.getMileages_m() + ((elapsedRealtime / 1000.0d) * moveTaskModel2.getSpeed()));
            if (moveTaskModel2.getSpeed() >= 0.2d) {
                moveTaskModel2.setValidSpeedTime_ms(moveTaskModel2.getValidSpeedTime_ms() + elapsedRealtime);
            }
            moveTaskModel2.setLastSpeedTimestap(SystemClock.elapsedRealtime());
            if (speed < 0.2d) {
                speed = 0.0d;
            }
            moveTaskModel2.setSpeed(speed);
        }
    }
}
