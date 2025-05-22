package com.pudutech.robot.module.report.task;

import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.DisinfectReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportDisinfectTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportDisinfectTask;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", "disinfectReport", "Lcom/pudutech/robot/module/report/protocol/DisinfectReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "reportDisinfectTask", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportDisinfectTask extends BaseReportTask {
    private final String TAG = "ReportDisinfectTask";
    private DisinfectReport disinfectReport = new DisinfectReport();

    public final void reportDisinfectTask(DisinfectReport disinfectReport) {
        Intrinsics.checkParameterIsNotNull(disinfectReport, "disinfectReport");
        this.disinfectReport = disinfectReport;
        report();
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "reportDisinfectTask disinfectReport = " + disinfectReport);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        return this.disinfectReport;
    }
}
