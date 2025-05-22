package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.selfcheck.CheckResult;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportSelfCheck.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportSelfCheck;", "", "()V", "TAG", "", "mReportSelfCheck", "Lcom/pudutech/robot/module/report/task/ReportSelfCheck;", "addStep", "", "step", "boolean", "", "description", "createNewTask", "toCloud", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportSelfCheck {
    private static com.pudutech.robot.module.report.task.ReportSelfCheck mReportSelfCheck;
    public static final ReportSelfCheck INSTANCE = new ReportSelfCheck();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ReportSelfCheck() {
    }

    public final void toCloud() {
        MachineInfoHelper.MachineInfos machineInfo = MachineInfoHelper.INSTANCE.getMachineInfo();
        if (machineInfo != null) {
            String json = GsonSingleton.INSTANCE.getINSTANCE().toJson(machineInfo);
            com.pudutech.robot.module.report.task.ReportSelfCheck reportSelfCheck = mReportSelfCheck;
            if (reportSelfCheck != null) {
                reportSelfCheck.setMachineInfo(json);
            }
            Pdlog.m3275i(TAG, "setMachineInfo  machineInfo = " + json);
        }
        com.pudutech.robot.module.report.task.ReportSelfCheck reportSelfCheck2 = mReportSelfCheck;
        if (reportSelfCheck2 != null) {
            reportSelfCheck2.report();
        }
        Pdlog.m3275i(TAG, "to cloud  mReportSelfCheck.report");
    }

    public final void addStep(String step, boolean r4, String description) {
        Intrinsics.checkParameterIsNotNull(step, "step");
        CheckResult checkResult = new CheckResult();
        checkResult.type = step;
        checkResult.status = r4 ? 1 : 2;
        if (description != null) {
            checkResult.description = description;
        }
        com.pudutech.robot.module.report.task.ReportSelfCheck reportSelfCheck = mReportSelfCheck;
        if (reportSelfCheck != null) {
            reportSelfCheck.addStep(step, r4, description);
        }
    }

    public final void createNewTask() {
        Pdlog.m3275i(TAG, "createNewTask");
        mReportSelfCheck = new com.pudutech.robot.module.report.task.ReportSelfCheck();
    }
}
