package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.run.Run;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportRun;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ReportRun.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0002\u0006\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0014\u0010\u0015¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun;", "", "()V", "TAG", "", "batteryListener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$batteryListener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$batteryListener$1;", "mReportRun", "Lcom/pudutech/robot/module/report/task/ReportRun;", "getMReportRun", "()Lcom/pudutech/robot/module/report/task/ReportRun;", "mReportRun$delegate", "Lkotlin/Lazy;", "stateListener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$stateListener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$stateListener$1;", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$Status;", "status", "setStatus", "(Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$Status;)V", "createNewTask", "", "toCloud", "Status", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportRun {
    public static final ReportRun INSTANCE = new ReportRun();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* renamed from: mReportRun$delegate, reason: from kotlin metadata */
    private static final Lazy mReportRun = LazyKt.lazy(new Function0<com.pudutech.robot.module.report.task.ReportRun>() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportRun$mReportRun$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final com.pudutech.robot.module.report.task.ReportRun invoke() {
            return new com.pudutech.robot.module.report.task.ReportRun();
        }
    });
    private static Status status = Status.IDLE;
    private static final ReportRun$stateListener$1 stateListener = new Function2<RobotState, String, Unit>() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportRun$stateListener$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
            invoke2(robotState, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(RobotState state, String p1) {
            ReportRun.Status status2;
            ReportRun reportRun = ReportRun.INSTANCE;
            status2 = ReportRun.status;
            if (status2 == ReportRun.Status.CHARGING) {
                return;
            }
            if (state == RobotState.Idle) {
                ReportRun.INSTANCE.setStatus(ReportRun.Status.IDLE);
                return;
            }
            if (state == RobotState.Pause && (Behavior.INSTANCE.getMovementTask() instanceof IdleTask)) {
                ReportRun.INSTANCE.setStatus(ReportRun.Status.IDLE);
            } else if (state == RobotState.Moving) {
                ReportRun.INSTANCE.setStatus(ReportRun.Status.DELIVER);
            }
        }
    };
    private static final ReportRun$batteryListener$1 batteryListener = new SystemBatteryListener() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportRun$batteryListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onPowerChange(int powerPercent) {
        }

        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
            if (chargeState == ChargeState.ChargeFull || chargeState == ChargeState.Charging) {
                ReportRun.INSTANCE.setStatus(ReportRun.Status.CHARGING);
            } else if (chargeState == ChargeState.Idle) {
                ReportRun.INSTANCE.setStatus(ReportRun.Status.IDLE);
            }
        }
    };

    private final com.pudutech.robot.module.report.task.ReportRun getMReportRun() {
        return (com.pudutech.robot.module.report.task.ReportRun) mReportRun.getValue();
    }

    private ReportRun() {
    }

    public final void createNewTask() {
        com.pudutech.robot.module.report.task.ReportRun mReportRun2 = getMReportRun();
        if (mReportRun2 != null) {
            mReportRun2.startTask();
        }
    }

    private final void toCloud() {
        Pdlog.m3275i(TAG, "to cloud. " + status.getValue());
        Run run = new Run();
        run.battery = (double) CoreDevices.INSTANCE.getBattery().getPowerPercent();
        run.status = status.getValue();
    }

    /* compiled from: ReportRun.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportRun$Status;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF_LINE", "DELIVER", "IDLE", "CHARGING", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum Status {
        OFF_LINE(0),
        DELIVER(1),
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void setStatus(Status status2) {
        status = status2;
        Pdlog.m3275i(TAG, "set status=" + status2);
    }
}
