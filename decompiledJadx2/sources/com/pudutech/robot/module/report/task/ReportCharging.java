package com.pudutech.robot.module.report.task;

import android.os.SystemClock;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.ChargeReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportCharging.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportCharging;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "chargeReport", "Lcom/pudutech/robot/module/report/protocol/ChargeReport;", "getChargeReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "recodeStartCharging", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportCharging extends BaseReportTask {
    private ChargeReport chargeReport = new ChargeReport();

    public final ChargeReport getChargeReport() {
        return this.chargeReport;
    }

    public final void recodeStartCharging() {
        ChargeReport chargeReport = this.chargeReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        chargeReport.setStart_battery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        this.chargeReport.setStart_timestamp(SystemClock.elapsedRealtime() / 1000);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        ChargeReport chargeReport = this.chargeReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        chargeReport.setEnd_battery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        this.chargeReport.setEnd_timestamp(SystemClock.elapsedRealtime() / 1000);
        return this.chargeReport;
    }
}
