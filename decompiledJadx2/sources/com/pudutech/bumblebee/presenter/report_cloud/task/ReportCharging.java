package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.Battery;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.charge.Charge;
import kotlin.Metadata;

/* compiled from: ReportCharging.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000+\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportCharging;", "", "()V", "TAG", "", "charge", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/charge/Charge;", "listener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportCharging$listener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportCharging$listener$1;", "mReportCharging", "Lcom/pudutech/robot/module/report/task/ReportCharging;", "createNewTask", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportCharging {
    private static Charge charge;
    private static com.pudutech.robot.module.report.task.ReportCharging mReportCharging;
    public static final ReportCharging INSTANCE = new ReportCharging();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ReportCharging$listener$1 listener = new ReportCharging$listener$1();

    private ReportCharging() {
    }

    public final void createNewTask() {
        Pdlog.m3275i(TAG, "createNewTask");
        Battery battery = CoreDevices.INSTANCE.getBattery();
        battery.removeListener(listener);
        battery.addListener(listener);
        listener.onStateChange(battery.getPowerPercent(), battery.getPowerState(), battery.getChargerState());
    }
}
