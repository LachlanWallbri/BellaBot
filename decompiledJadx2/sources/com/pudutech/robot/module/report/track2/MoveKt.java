package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: move.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\"\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, m3961d2 = {"onMove", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "left", "", "right", "onPose", "x", "y", "yaw", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class MoveKt {
    public static final void onMove(TrackingReportManager onMove, double d, double d2) {
        Intrinsics.checkParameterIsNotNull(onMove, "$this$onMove");
        MileageHelper.INSTANCE.onMove(d, d2);
    }

    public static final void onPose(TrackingReportManager onPose, double d, double d2, double d3) {
        Intrinsics.checkParameterIsNotNull(onPose, "$this$onPose");
        MileageHelper.INSTANCE.onPose(d, d2, d3);
    }
}
