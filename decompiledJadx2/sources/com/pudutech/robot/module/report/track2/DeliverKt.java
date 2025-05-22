package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.DeliverTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: deliver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u001c\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"crtDeliverTask", "Lcom/pudutech/robot/module/report/track2/DeliverTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "newDeliverTask", "config", "Lcom/pudutech/robot/module/report/track2/DeliverTask$BaseDeliverConfig;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DeliverKt {
    private static DeliverTask crtDeliverTask;

    public static /* synthetic */ DeliverTask newDeliverTask$default(TrackingReportManager trackingReportManager, DeliverTask.BaseDeliverConfig baseDeliverConfig, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return newDeliverTask(trackingReportManager, baseDeliverConfig, str);
    }

    public static final DeliverTask newDeliverTask(TrackingReportManager newDeliverTask, DeliverTask.BaseDeliverConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newDeliverTask, "$this$newDeliverTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        crtDeliverTask = new DeliverTask(config, sessionId);
        DeliverTask deliverTask = crtDeliverTask;
        if (deliverTask == null) {
            Intrinsics.throwNpe();
        }
        return deliverTask;
    }

    public static final DeliverTask crtDeliverTask(TrackingReportManager crtDeliverTask2) {
        Intrinsics.checkParameterIsNotNull(crtDeliverTask2, "$this$crtDeliverTask");
        return crtDeliverTask;
    }
}
