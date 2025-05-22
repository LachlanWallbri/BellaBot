package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveEvent;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener;
import com.pudutech.bumblebee.presenter.robot_open_task.provider.PositionDataManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.RunReport;
import com.pudutech.robot.module.report.task.BaseReportTask;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: PrivateCloudReportRun.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\r\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u00020\r8\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u000f\u0012\u0004\b\u000e\u0010\u0003¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/PrivateCloudReportRun;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveListener;", "()V", "TAG", "", "sleepStatus", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveEvent;", "taskSet", "", "timer", "Ljava/util/Timer;", "timerTask", "com/pudutech/bumblebee/presenter/report_cloud/task/PrivateCloudReportRun$timerTask$1", "timerTask$annotations", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/PrivateCloudReportRun$timerTask$1;", "createNewTask", "", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "getStatus", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/PrivateCloudReportRun$Status;", "onEvent", "event", "Status", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PrivateCloudReportRun extends BaseReportTask implements PowerSaveListener {
    private static boolean taskSet;
    public static final PrivateCloudReportRun INSTANCE = new PrivateCloudReportRun();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final Timer timer = new Timer();
    private static volatile PowerSaveEvent sleepStatus = PowerSaveEvent.DEVICES_READY;
    private static final PrivateCloudReportRun$timerTask$1 timerTask = new TimerTask() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.PrivateCloudReportRun$timerTask$1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            PrivateCloudReportRun.INSTANCE.report();
        }
    };

    private static /* synthetic */ void timerTask$annotations() {
    }

    private PrivateCloudReportRun() {
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        RunReport runReport = new RunReport();
        runReport.setBattery(BatteryInfoManager.INSTANCE.getPower() != null ? r1.intValue() : 0.0d);
        runReport.setStatus(getStatus().getValue());
        runReport.setPosition(PositionDataManager.INSTANCE.getPositionInfo());
        Pdlog.m3273d(TAG, "getReportData run:" + runReport + ' ');
        return runReport;
    }

    /* compiled from: PrivateCloudReportRun.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/PrivateCloudReportRun$Status;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF_LINE", "WORKING", "IDLE", "CHARGING", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Status {
        OFF_LINE(0),
        WORKING(1),
        IDLE(2),
        CHARGING(3);

        private final int value;

        Status(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    private final Status getStatus() {
        if (BatteryInfoManager.INSTANCE.isCharging()) {
            return Status.CHARGING;
        }
        if (sleepStatus == PowerSaveEvent.STAND_BY || sleepStatus == PowerSaveEvent.SLEEP) {
            return Status.IDLE;
        }
        return Status.WORKING;
    }

    public final void createNewTask() {
        if (taskSet) {
            return;
        }
        taskSet = true;
        timer.schedule(timerTask, 0L, 30000L);
        CoreDevices.INSTANCE.getPowerSaveTask().addListener(this);
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener
    public void onEvent(PowerSaveEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3275i(TAG, "PowerSaveEvenT=" + event);
        sleepStatus = event;
    }
}
