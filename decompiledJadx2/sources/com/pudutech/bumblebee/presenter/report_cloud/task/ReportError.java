package com.pudutech.bumblebee.presenter.report_cloud.task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.robot.module.report.protocol.bean.ErrorString;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportError;", "", "()V", "TAG", "", "batterListener", "com/pudutech/bumblebee/presenter/report_cloud/task/ReportError$batterListener$1", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportError$batterListener$1;", "mReportError", "Lcom/pudutech/robot/module/report/task/ReportError;", "checkChargeWorkNormal", "", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "createNewTask", "", "toCloud", "errors", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/protocol/Errors;", "state", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportError {
    private static com.pudutech.robot.module.report.task.ReportError mReportError;
    public static final ReportError INSTANCE = new ReportError();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final ReportError$batterListener$1 batterListener = new SystemBatteryListener() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportError$batterListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onPowerChange(int powerPercent) {
        }

        @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
        public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
            boolean checkChargeWorkNormal;
            checkChargeWorkNormal = ReportError.INSTANCE.checkChargeWorkNormal(chargeState);
            if (checkChargeWorkNormal) {
                return;
            }
            ReportError reportError = ReportError.INSTANCE;
            if (chargeState == null) {
                Intrinsics.throwNpe();
            }
            reportError.toCloud(chargeState);
        }
    };

    private ReportError() {
    }

    public final void toCloud(Errors errors) {
        Intrinsics.checkParameterIsNotNull(errors, "errors");
        if (errors.list.size() == 0) {
            Pdlog.m3273d(TAG, "size is 0. no need report. return");
            return;
        }
        ArrayList<ErrorString> arrayList = new ArrayList<>();
        ArrayList<Error> arrayList2 = errors.list;
        Intrinsics.checkExpressionValueIsNotNull(arrayList2, "errors.list");
        for (Error error : arrayList2) {
            ErrorString errorString = new ErrorString();
            errorString.f7202id = String.valueOf(error.hashCode());
            errorString.error_type = error.error_type;
            errorString.detail = error.detail;
            errorString.setGrade(error.level);
            arrayList.add(errorString);
        }
        com.pudutech.robot.module.report.task.ReportError reportError = mReportError;
        if (reportError != null) {
            reportError.setError(arrayList);
        }
        com.pudutech.robot.module.report.task.ReportError reportError2 = mReportError;
        if (reportError2 != null) {
            reportError2.report();
        }
        Pdlog.m3275i(TAG, "to cloud");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toCloud(ChargeState state) {
        com.pudutech.robot.module.report.task.ReportError reportError = mReportError;
        if (reportError != null) {
            reportError.setChargeError(String.valueOf(state));
        }
        com.pudutech.robot.module.report.task.ReportError reportError2 = mReportError;
        if (reportError2 != null) {
            reportError2.report();
        }
        Pdlog.m3275i(TAG, "to cloud. " + state);
    }

    public final void createNewTask() {
        Pdlog.m3275i(TAG, "createNewTask");
        CoreDevices.INSTANCE.getBattery().addListener(batterListener);
        mReportError = new com.pudutech.robot.module.report.task.ReportError();
        ChargeState chargerState = CoreDevices.INSTANCE.getBattery().getChargerState();
        if (checkChargeWorkNormal(chargerState)) {
            return;
        }
        if (chargerState == null) {
            Intrinsics.throwNpe();
        }
        toCloud(chargerState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkChargeWorkNormal(ChargeState chargeState) {
        return chargeState == null || chargeState == ChargeState.Idle || chargeState == ChargeState.Charging || chargeState == ChargeState.ChargeFull;
    }
}
