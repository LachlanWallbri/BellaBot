package com.pudutech.peanut.robot_ui.manager;

import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.robot.module.report.protocol.bean.ErrorString;
import com.pudutech.robot.module.report.task.ReportError;
import com.pudutech.robot.module.report.task.ReportErrorProcess;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/ReportHelper;", "", "()V", "ROBOT_RESUME", "", "getROBOT_RESUME", "()Ljava/lang/String;", "init", "", "reportCharge", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "reportErrorProcess", "eh", "Lcom/pudutech/mirsdkwrap/lib/move/MoveErrorHelper;", "process", "reportMoveError", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ReportHelper {
    public static final ReportHelper INSTANCE = new ReportHelper();
    private static final String ROBOT_RESUME = ROBOT_RESUME;
    private static final String ROBOT_RESUME = ROBOT_RESUME;

    public final void init() {
    }

    private ReportHelper() {
    }

    public final void reportMoveError(MoveErrorHelper eh) {
        Intrinsics.checkParameterIsNotNull(eh, "eh");
        ArrayList<ErrorString> arrayList = new ArrayList<>();
        ArrayList<MoveError> currentErrors = eh.getCurrentErrors();
        if (currentErrors != null) {
            for (MoveError moveError : currentErrors) {
                ErrorString errorString = new ErrorString();
                errorString.f7202id = moveError.getId();
                errorString.detail = moveError.getDetail();
                errorString.error_type = moveError.getError_type();
                errorString.setGrade(moveError.getLevel());
                arrayList.add(errorString);
            }
        }
        new ReportError().setError(arrayList).report();
    }

    public final String getROBOT_RESUME() {
        return ROBOT_RESUME;
    }

    public final void reportErrorProcess(MoveErrorHelper eh, String process) {
        Intrinsics.checkParameterIsNotNull(eh, "eh");
        Intrinsics.checkParameterIsNotNull(process, "process");
        ArrayList<MoveError> currentErrors = eh.getCurrentErrors();
        if (currentErrors != null) {
            for (MoveError moveError : currentErrors) {
                new ReportErrorProcess().set(moveError.getError_type(), moveError.getDetail(), process, moveError.getId()).report();
            }
        }
    }

    public final void reportCharge(ChargeState chargeState) {
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
        if (chargeState == ChargeState.ChargeFull || chargeState == ChargeState.Charging || chargeState == ChargeState.Idle || chargeState == ChargeState.ChargeFullUsePile || chargeState == ChargeState.StopChargeUsePile || chargeState == ChargeState.CharingUsePile) {
            return;
        }
        new ReportError().setChargeError(chargeState.name()).report();
    }
}
