package com.pudutech.robot.module.report.task;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.NfcReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportNfc.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportNfc;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "nfcReport", "Lcom/pudutech/robot/module/report/protocol/NfcReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", TmpConstant.PROPERTY_IDENTIFIER_SET, "nfcId", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportNfc extends BaseReportTask {
    private final NfcReport nfcReport = new NfcReport();

    public final ReportNfc set(String nfcId) {
        Intrinsics.checkParameterIsNotNull(nfcId, "nfcId");
        this.nfcReport.setNfc_id(nfcId);
        this.nfcReport.setClock_timestamp(System.currentTimeMillis() / 1000);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        return this.nfcReport;
    }
}
