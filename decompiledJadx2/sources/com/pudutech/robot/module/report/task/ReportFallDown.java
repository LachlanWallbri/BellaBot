package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.FallDownReport;
import kotlin.Metadata;

/* compiled from: ReportFallDown.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportFallDown;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "fallDownReport", "Lcom/pudutech/robot/module/report/protocol/FallDownReport;", "getFallDownReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportFallDown extends BaseReportTask {
    private final FallDownReport fallDownReport = new FallDownReport();

    public final FallDownReport getFallDownReport() {
        return this.fallDownReport;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        return this.fallDownReport;
    }
}
