package com.pudutech.robot.module.report.task;

import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.RunReport;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.DimensionsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportRunCheck.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0018\u0018\u00002\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0017\u001a\u00020\u00188\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u001b\u0012\u0004\b\u0019\u0010\u001a¨\u0006#"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportRunCheck;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "checkTimeSeconds", "", "(J)V", "TAG", "", "lastReportBattery", "", "Ljava/lang/Double;", "lastReportRunStatus", "", "Ljava/lang/Integer;", "lastReportRunTime", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/robot/module/report/task/ReportRunCheck$Status;", "status", "setStatus", "(Lcom/pudutech/robot/module/report/task/ReportRunCheck$Status;)V", "taskSet", "", "timer", "Ljava/util/Timer;", "timerTask", "com/pudutech/robot/module/report/task/ReportRunCheck$timerTask$1", "timerTask$annotations", "()V", "Lcom/pudutech/robot/module/report/task/ReportRunCheck$timerTask$1;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "getStatus", "isUpdate", "startTask", "", "Status", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportRunCheck extends BaseReportTask {
    private final long checkTimeSeconds;
    private Double lastReportBattery;
    private Integer lastReportRunStatus;
    private long lastReportRunTime;
    private boolean taskSet;
    private final String TAG = "ReportRun";
    private final Timer timer = new Timer();
    private final ReportRunCheck$timerTask$1 timerTask = new TimerTask() { // from class: com.pudutech.robot.module.report.task.ReportRunCheck$timerTask$1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            boolean isUpdate;
            long j;
            long j2;
            long j3;
            long j4;
            long j5;
            long j6;
            isUpdate = ReportRunCheck.this.isUpdate();
            if (!isUpdate) {
                j5 = ReportRunCheck.this.lastReportRunTime;
                long j7 = DimensionsKt.XXHDPI;
                j6 = ReportRunCheck.this.checkTimeSeconds;
                if (j5 != j7 / j6) {
                    return;
                }
            }
            ReportRunCheck.this.report();
            j = ReportRunCheck.this.lastReportRunTime;
            long j8 = DimensionsKt.XXHDPI;
            j2 = ReportRunCheck.this.checkTimeSeconds;
            if (j == j8 / j2) {
                ReportRunCheck.this.lastReportRunTime = 0L;
                return;
            }
            ReportRunCheck reportRunCheck = ReportRunCheck.this;
            j3 = reportRunCheck.lastReportRunTime;
            j4 = ReportRunCheck.this.checkTimeSeconds;
            reportRunCheck.lastReportRunTime = j3 + j4;
        }
    };
    private Status status = Status.IDLE;

    private static /* synthetic */ void timerTask$annotations() {
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.pudutech.robot.module.report.task.ReportRunCheck$timerTask$1] */
    public ReportRunCheck(long j) {
        this.checkTimeSeconds = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isUpdate() {
        if (this.lastReportBattery == null || this.lastReportRunStatus == null) {
            PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
            this.lastReportBattery = Double.valueOf(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
            this.lastReportRunStatus = Integer.valueOf(getStatus().getValue());
            return true;
        }
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release2 = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        double powerPercent = iReportDataProvide$module_robot_report_release2 != null ? iReportDataProvide$module_robot_report_release2.getPowerPercent() : 0.0d;
        int value = getStatus().getValue();
        boolean z = false;
        if (!Intrinsics.areEqual(this.lastReportBattery, powerPercent)) {
            this.lastReportBattery = Double.valueOf(powerPercent);
            z = true;
        }
        Integer num = this.lastReportRunStatus;
        if (num != null && num.intValue() == value) {
            return z;
        }
        this.lastReportRunStatus = Integer.valueOf(value);
        return true;
    }

    public final void startTask() {
        if (this.taskSet) {
            return;
        }
        this.taskSet = true;
        this.timer.schedule(this.timerTask, 0L, this.checkTimeSeconds * 1000);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: ReportRunCheck.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportRunCheck$Status;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF_LINE", "WORKING", "IDLE", "CHARGING", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
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

    private final void setStatus(Status status) {
        this.status = status;
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "set status=" + status);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        RunReport runReport = new RunReport();
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        runReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        runReport.setStatus(getStatus().getValue());
        return runReport;
    }

    private final Status getStatus() {
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        if (iReportDataProvide$module_robot_report_release != null && iReportDataProvide$module_robot_report_release.isCharging()) {
            return Status.CHARGING;
        }
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release2 = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        if (iReportDataProvide$module_robot_report_release2 != null && iReportDataProvide$module_robot_report_release2.isWorking()) {
            return Status.WORKING;
        }
        return Status.IDLE;
    }
}
