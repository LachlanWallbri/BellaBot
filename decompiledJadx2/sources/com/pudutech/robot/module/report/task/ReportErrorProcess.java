package com.pudutech.robot.module.report.task;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.ErrorProcessReport;
import com.pudutech.robot.module.report.protocol.bean.ErrorProcess;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportErrorProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportErrorProcess;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", "errorProcess", "Lcom/pudutech/robot/module/report/protocol/ErrorProcessReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", TmpConstant.PROPERTY_IDENTIFIER_SET, "errorType", "detail", "process", "id", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportErrorProcess extends BaseReportTask {
    private final String TAG = "ReportErrorProcess";
    private final ErrorProcessReport errorProcess = new ErrorProcessReport();

    public final ReportErrorProcess set(String errorType, String detail, String process, String id) {
        Intrinsics.checkParameterIsNotNull(errorType, "errorType");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        Intrinsics.checkParameterIsNotNull(process, "process");
        Intrinsics.checkParameterIsNotNull(id, "id");
        ArrayList<ErrorProcess> data = this.errorProcess.getData();
        ErrorProcess errorProcess = new ErrorProcess();
        errorProcess.f7201id = id;
        errorProcess.process = process;
        errorProcess.error_type = errorType;
        errorProcess.detail = detail;
        data.add(errorProcess);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        if (this.errorProcess.getData().isEmpty()) {
            NetWorkLog.INSTANCE.mo3278d(this.TAG, "getReportData process is Empty");
            return null;
        }
        return this.errorProcess;
    }
}
