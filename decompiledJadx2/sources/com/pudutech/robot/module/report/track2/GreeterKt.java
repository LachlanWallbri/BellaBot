package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: greeter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u001c\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"crtGreeterTask", "Lcom/pudutech/robot/module/report/track2/GreeterTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "newGreeterTask", "config", "Lcom/pudutech/robot/module/report/track2/BaseGreeterConfig;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class GreeterKt {
    private static GreeterTask crtGreeterTask;

    public static /* synthetic */ GreeterTask newGreeterTask$default(TrackingReportManager trackingReportManager, BaseGreeterConfig baseGreeterConfig, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return newGreeterTask(trackingReportManager, baseGreeterConfig, str);
    }

    public static final GreeterTask newGreeterTask(TrackingReportManager newGreeterTask, BaseGreeterConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newGreeterTask, "$this$newGreeterTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        crtGreeterTask = new GreeterTask(config, sessionId);
        GreeterTask greeterTask = crtGreeterTask;
        if (greeterTask == null) {
            Intrinsics.throwNpe();
        }
        return greeterTask;
    }

    public static final GreeterTask crtGreeterTask(TrackingReportManager crtGreeterTask2) {
        Intrinsics.checkParameterIsNotNull(crtGreeterTask2, "$this$crtGreeterTask");
        return crtGreeterTask;
    }
}
