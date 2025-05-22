package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportErrorProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportErrorProcess;", "", "()V", "TAG", "", "toCloud", "", "error", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Error;", "process", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportErrorProcess {
    public static final ReportErrorProcess INSTANCE = new ReportErrorProcess();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ReportErrorProcess() {
    }

    public final void toCloud(Error error, String process) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        Intrinsics.checkParameterIsNotNull(process, "process");
        Pdlog.m3275i(TAG, "to cloud. process=" + process);
        com.pudutech.robot.module.report.task.ReportErrorProcess reportErrorProcess = new com.pudutech.robot.module.report.task.ReportErrorProcess();
        String str = error.error_type;
        Intrinsics.checkExpressionValueIsNotNull(str, "error.error_type");
        String str2 = error.detail;
        Intrinsics.checkExpressionValueIsNotNull(str2, "error.detail");
        reportErrorProcess.set(str, str2, process, String.valueOf(error.hashCode()));
        reportErrorProcess.report();
    }
}
