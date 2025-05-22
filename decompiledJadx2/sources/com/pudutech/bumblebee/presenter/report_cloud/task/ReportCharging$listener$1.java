package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import kotlin.Metadata;

/* compiled from: ReportCharging.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/report_cloud/task/ReportCharging$listener$1", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "onPowerChange", "", "powerPercent", "", "onStateChange", "powerState", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportCharging$listener$1 implements SystemBatteryListener {
    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onPowerChange(int powerPercent) {
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
        String str;
        com.pudutech.robot.module.report.task.ReportCharging reportCharging;
        com.pudutech.robot.module.report.task.ReportCharging reportCharging2;
        String str2;
        com.pudutech.robot.module.report.task.ReportCharging reportCharging3;
        String str3;
        com.pudutech.robot.module.report.task.ReportCharging reportCharging4;
        String str4;
        ReportCharging reportCharging5 = ReportCharging.INSTANCE;
        str = ReportCharging.TAG;
        Pdlog.m3275i(str, "onStateChange " + powerPercent + ' ' + powerState + ' ' + chargeState);
        if (chargeState == ChargeState.Charging || chargeState == ChargeState.ChargeFull) {
            ReportCharging reportCharging6 = ReportCharging.INSTANCE;
            reportCharging = ReportCharging.mReportCharging;
            if (reportCharging == null) {
                ReportCharging reportCharging7 = ReportCharging.INSTANCE;
                ReportCharging.mReportCharging = new com.pudutech.robot.module.report.task.ReportCharging();
                ReportCharging reportCharging8 = ReportCharging.INSTANCE;
                reportCharging3 = ReportCharging.mReportCharging;
                if (reportCharging3 != null) {
                    reportCharging3.recodeStartCharging();
                }
                ReportCharging reportCharging9 = ReportCharging.INSTANCE;
                str3 = ReportCharging.TAG;
                Pdlog.m3273d(str3, "onStateChange() 开始统计电量");
                return;
            }
            if (chargeState == ChargeState.ChargeFull) {
                ReportCharging reportCharging10 = ReportCharging.INSTANCE;
                reportCharging2 = ReportCharging.mReportCharging;
                if (reportCharging2 != null) {
                    reportCharging2.report();
                }
                ReportCharging reportCharging11 = ReportCharging.INSTANCE;
                ReportCharging.mReportCharging = (com.pudutech.robot.module.report.task.ReportCharging) null;
                ReportCharging reportCharging12 = ReportCharging.INSTANCE;
                str2 = ReportCharging.TAG;
                Pdlog.m3273d(str2, "onStateChange() ChargeFull 上传电量");
                return;
            }
            return;
        }
        if (chargeState == ChargeState.Idle) {
            ReportCharging reportCharging13 = ReportCharging.INSTANCE;
            reportCharging4 = ReportCharging.mReportCharging;
            if (reportCharging4 != null) {
                reportCharging4.report();
            }
            ReportCharging reportCharging14 = ReportCharging.INSTANCE;
            ReportCharging.mReportCharging = (com.pudutech.robot.module.report.task.ReportCharging) null;
            ReportCharging reportCharging15 = ReportCharging.INSTANCE;
            str4 = ReportCharging.TAG;
            Pdlog.m3273d(str4, "onStateChange() Idle 上传电量");
        }
    }
}
