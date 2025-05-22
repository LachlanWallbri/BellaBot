package com.pudutech.robot.module.report.task;

import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.ErrorReport;
import com.pudutech.robot.module.report.protocol.bean.ErrorString;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004J\u001e\u0010\f\u001a\u00020\u00002\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportError;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "TAG", "", "errReport", "Lcom/pudutech/robot/module/report/protocol/ErrorReport;", "getErrorReport", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "setChargeError", "state", "setError", "erres", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/ErrorString;", "Lkotlin/collections/ArrayList;", "setFreeInstallError", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportError extends BaseReportTask {
    private final String TAG = "ReportError";
    private final ErrorReport errReport = new ErrorReport();

    /* renamed from: getErrorReport, reason: from getter */
    public final ErrorReport getErrReport() {
        return this.errReport;
    }

    public final ReportError setError(ArrayList<ErrorString> erres) {
        Intrinsics.checkParameterIsNotNull(erres, "erres");
        if (erres.isEmpty()) {
            return this;
        }
        ErrorReport errorReport = this.errReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        errorReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        this.errReport.getErrors().clear();
        this.errReport.getErrors().addAll(erres);
        return this;
    }

    public final ReportError setChargeError(String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        ErrorString errorString = new ErrorString();
        errorString.error_type = "charge";
        errorString.detail = state;
        errorString.grade = 1;
        errorString.f7202id = String.valueOf(errorString.hashCode());
        ErrorReport errorReport = this.errReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        errorReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        this.errReport.getErrors().clear();
        this.errReport.getErrors().add(errorString);
        return this;
    }

    public final ReportError setFreeInstallError(String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        ErrorString errorString = new ErrorString();
        errorString.error_type = "freeInstall";
        errorString.detail = state;
        errorString.grade = 1;
        errorString.f7202id = String.valueOf(errorString.hashCode());
        ErrorReport errorReport = this.errReport;
        PuduReportManager.IReportDataProvide iReportDataProvide$module_robot_report_release = PuduReportManager.INSTANCE.getIReportDataProvide$module_robot_report_release();
        errorReport.setBattery(iReportDataProvide$module_robot_report_release != null ? iReportDataProvide$module_robot_report_release.getPowerPercent() : 0.0d);
        this.errReport.getErrors().clear();
        this.errReport.getErrors().add(errorString);
        return this;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        if (this.errReport.getErrors().isEmpty()) {
            return null;
        }
        return this.errReport;
    }
}
