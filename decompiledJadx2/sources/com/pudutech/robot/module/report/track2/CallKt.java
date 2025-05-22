package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u001c\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"crtCallDirectTask", "Lcom/pudutech/robot/module/report/track2/CallDirectTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "newCallDirectTask", "config", "Lcom/pudutech/robot/module/report/track2/BaseCallConfig;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CallKt {
    private static CallDirectTask crtCallDirectTask;

    public static /* synthetic */ CallDirectTask newCallDirectTask$default(TrackingReportManager trackingReportManager, BaseCallConfig baseCallConfig, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return newCallDirectTask(trackingReportManager, baseCallConfig, str);
    }

    public static final CallDirectTask newCallDirectTask(TrackingReportManager newCallDirectTask, BaseCallConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newCallDirectTask, "$this$newCallDirectTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        crtCallDirectTask = new CallDirectTask(config, sessionId);
        CallDirectTask callDirectTask = crtCallDirectTask;
        if (callDirectTask == null) {
            Intrinsics.throwNpe();
        }
        return callDirectTask;
    }

    public static final CallDirectTask crtCallDirectTask(TrackingReportManager crtCallDirectTask2) {
        Intrinsics.checkParameterIsNotNull(crtCallDirectTask2, "$this$crtCallDirectTask");
        return crtCallDirectTask;
    }
}
