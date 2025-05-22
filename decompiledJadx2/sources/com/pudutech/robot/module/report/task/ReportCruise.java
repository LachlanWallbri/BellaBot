package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.CruiseReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportCruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000fJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportCruise;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", "cruiseReport", "Lcom/pudutech/robot/module/report/protocol/CruiseReport;", "getCruiseReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "setAverage", "a", "", "setDuration", "l", "", "setMileage", "m", "setPauseCount", "c", "", "setPauseDuration", "setStatus", "b", "", "setTaskId", "taskId", "setTotalTime", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportCruise extends BaseReportTask {
    private final String TAG = "ReportCruise";
    private final CruiseReport cruiseReport = new CruiseReport();

    public final CruiseReport getCruiseReport() {
        return this.cruiseReport;
    }

    public final ReportCruise setStatus(boolean b) {
        this.cruiseReport.setStatus(b);
        return this;
    }

    public final ReportCruise setDuration(long l) {
        this.cruiseReport.setDuration(l);
        return this;
    }

    public final ReportCruise setPauseDuration(long l) {
        this.cruiseReport.setDuration_pause(l);
        return this;
    }

    public final ReportCruise setPauseCount(int c) {
        this.cruiseReport.setDuration_count(c);
        return this;
    }

    public final ReportCruise setMileage(double m) {
        this.cruiseReport.setMileage(m);
        return this;
    }

    public final ReportCruise setAverage(double a) {
        this.cruiseReport.setAverage(a);
        return this;
    }

    public final ReportCruise setTotalTime(long l) {
        this.cruiseReport.setTotal_time(l);
        return this;
    }

    public final ReportCruise setTaskId(long taskId) {
        this.cruiseReport.setTask_id(taskId);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        if (this.cruiseReport.getTask_id() <= 0) {
            this.cruiseReport.setTask_id(System.currentTimeMillis() / 1000);
        }
        CruiseReport cruiseReport = this.cruiseReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        cruiseReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        return this.cruiseReport;
    }
}
