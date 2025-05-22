package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.BootReport;
import com.pudutech.robot.module.report.protocol.bean.CheckResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportSelfCheck.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0010\u001a\u00020\u0006J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportSelfCheck;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", "bootReport", "Lcom/pudutech/robot/module/report/protocol/BootReport;", "machineInfo", "addStep", "", "step", "boolean", "", "description", "state", "", "getBootReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "setMachineInfo", "s", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportSelfCheck extends BaseReportTask {
    private final String TAG = "ReportSelfCheck";
    private BootReport bootReport = new BootReport();
    private String machineInfo = "";

    public ReportSelfCheck() {
        this.bootReport.setSystemid(PuduReportManager.INSTANCE.getSysBuildId$module_robot_report_release());
    }

    public final BootReport getBootReport() {
        return this.bootReport;
    }

    public final void addStep(String step, boolean r3, String description) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        CheckResult checkResult = new CheckResult();
        checkResult.type = step;
        checkResult.status = r3 ? 1 : 2;
        if (description != null) {
            checkResult.description = description;
        }
        this.bootReport.getCheckResult().add(checkResult);
    }

    public final void addStep(String step, int state, String description) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        CheckResult checkResult = new CheckResult();
        checkResult.type = step;
        checkResult.status = state;
        if (description != null) {
            checkResult.description = description;
        }
        this.bootReport.getCheckResult().add(checkResult);
    }

    public final ReportSelfCheck setMachineInfo(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        this.machineInfo = s;
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        BootReport bootReport = this.bootReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        bootReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        CheckResult checkResult = new CheckResult();
        checkResult.type = "machineinfo";
        checkResult.status = 0;
        checkResult.description = this.machineInfo;
        this.bootReport.getCheckResult().add(checkResult);
        return this.bootReport;
    }
}
