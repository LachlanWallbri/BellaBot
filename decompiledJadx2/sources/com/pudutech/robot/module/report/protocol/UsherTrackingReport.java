package com.pudutech.robot.module.report.protocol;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: UsherTrackingReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/UsherTrackingReport;", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "()V", "usherFlowNumber", "", "getUsherFlowNumber", "()J", "setUsherFlowNumber", "(J)V", "usherMiles", "", "getUsherMiles", "()D", "setUsherMiles", "(D)V", "usherNumber", "getUsherNumber", "setUsherNumber", "usherUseTime", "getUsherUseTime", "setUsherUseTime", "isEmpty", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class UsherTrackingReport implements BaseTrackingReport {

    @SerializedName("passenger_flow")
    private long usherFlowNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName(Constants.POINT_TYPE_DOOR)
    private long usherNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("mileage")
    private double usherMiles = getDEFAULT_TRACKING_FLOAT_VALUE();

    @SerializedName(TypedValues.Transition.S_DURATION)
    private long usherUseTime = getDEFAULT_TRACKING_VALUE();

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public double getDEFAULT_TRACKING_FLOAT_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_FLOAT_VALUE(this);
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public long getDEFAULT_TRACKING_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_VALUE(this);
    }

    public final long getUsherFlowNumber() {
        return this.usherFlowNumber;
    }

    public final void setUsherFlowNumber(long j) {
        this.usherFlowNumber = j;
    }

    public final long getUsherNumber() {
        return this.usherNumber;
    }

    public final void setUsherNumber(long j) {
        this.usherNumber = j;
    }

    public final double getUsherMiles() {
        return this.usherMiles;
    }

    public final void setUsherMiles(double d) {
        this.usherMiles = d;
    }

    public final long getUsherUseTime() {
        return this.usherUseTime;
    }

    public final void setUsherUseTime(long j) {
        this.usherUseTime = j;
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public boolean isEmpty() {
        return this.usherFlowNumber == getDEFAULT_TRACKING_VALUE() && this.usherNumber == getDEFAULT_TRACKING_VALUE() && this.usherMiles == getDEFAULT_TRACKING_FLOAT_VALUE() && this.usherUseTime == getDEFAULT_TRACKING_VALUE();
    }

    public String toString() {
        return "usherFlowNumber:" + this.usherFlowNumber + ";usherNumber：" + this.usherNumber + "; usherMiles:" + this.usherMiles + ";userUseTime:" + this.usherUseTime;
    }
}
