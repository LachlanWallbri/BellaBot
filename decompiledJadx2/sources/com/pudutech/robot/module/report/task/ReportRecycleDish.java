package com.pudutech.robot.module.report.task;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.RecycleDishReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportRecycleDish.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\fJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportRecycleDish;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "recycleDishReport", "Lcom/pudutech/robot/module/report/protocol/RecycleDishReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "setArriveWaitTime", "l", "", "setAverage", "a", "", "setCruiseRecycleTime", "preset", "actual", "setDes", LinkFormat.DOMAIN, "", "setDesType", "i", "", "setDuration", "setInterrupt", "setMileage", "m", "setMode", "setPause", "time", "count", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportRecycleDish extends BaseReportTask {
    private final RecycleDishReport recycleDishReport = new RecycleDishReport();

    public final ReportRecycleDish setMode(int i) {
        this.recycleDishReport.setTask_mode(i);
        return this;
    }

    public final ReportRecycleDish setDesType(int i) {
        this.recycleDishReport.setDestination(i);
        return this;
    }

    public final ReportRecycleDish setDes(String d) {
        Intrinsics.checkParameterIsNotNull(d, "d");
        this.recycleDishReport.setGoal_id(d);
        return this;
    }

    public final ReportRecycleDish setInterrupt(int i) {
        this.recycleDishReport.setInterrupt(i);
        return this;
    }

    public final ReportRecycleDish setArriveWaitTime(long l) {
        this.recycleDishReport.setDuration_wait(l);
        return this;
    }

    public final ReportRecycleDish setPause(long time, int count) {
        this.recycleDishReport.setDuration_pause(time);
        this.recycleDishReport.setPause_count(count);
        return this;
    }

    public final ReportRecycleDish setCruiseRecycleTime(long preset, long actual) {
        this.recycleDishReport.setDuration_recycle_preset(preset);
        this.recycleDishReport.setDuration_recycle_actual(actual);
        return this;
    }

    public final ReportRecycleDish setMileage(double m) {
        this.recycleDishReport.setMileage((long) m);
        return this;
    }

    public final ReportRecycleDish setAverage(double a) {
        this.recycleDishReport.setAverage(a);
        return this;
    }

    public final ReportRecycleDish setDuration(long l) {
        this.recycleDishReport.setDuration(l);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        this.recycleDishReport.setTask_id(System.currentTimeMillis() / 1000);
        RecycleDishReport recycleDishReport = this.recycleDishReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        recycleDishReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        return this.recycleDishReport;
    }
}
