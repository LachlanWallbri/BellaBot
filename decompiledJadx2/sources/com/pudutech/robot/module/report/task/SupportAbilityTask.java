package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.SupportAbilityReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportAbilityTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/SupportAbilityTask;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "mSupportAbilityReport", "Lcom/pudutech/robot/module/report/protocol/SupportAbilityReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "getSupportAbilityReport", "setAbilities", "", "abilities", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SupportAbilityTask extends BaseReportTask {
    private SupportAbilityReport mSupportAbilityReport = new SupportAbilityReport();

    /* renamed from: getSupportAbilityReport, reason: from getter */
    public final SupportAbilityReport getMSupportAbilityReport() {
        return this.mSupportAbilityReport;
    }

    public final void setAbilities(int[] abilities) {
        Intrinsics.checkParameterIsNotNull(abilities, "abilities");
        this.mSupportAbilityReport.setAbilities(abilities);
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        return this.mSupportAbilityReport;
    }
}
