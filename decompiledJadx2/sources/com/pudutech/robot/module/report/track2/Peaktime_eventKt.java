package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: peaktime_event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\u001a\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"crtPeakTimeTask", "Lcom/pudutech/robot/module/report/track2/PeakTimeTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "newPeakTimeTask", "eventType", "Lcom/pudutech/robot/module/report/track2/EventType;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Peaktime_eventKt {
    private static PeakTimeTask crtPeakTimeTask;

    public static final PeakTimeTask newPeakTimeTask(TrackingReportManager newPeakTimeTask, EventType eventType, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newPeakTimeTask, "$this$newPeakTimeTask");
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        crtPeakTimeTask = new PeakTimeTask(eventType, sessionId);
        PeakTimeTask peakTimeTask = crtPeakTimeTask;
        if (peakTimeTask == null) {
            Intrinsics.throwNpe();
        }
        return peakTimeTask;
    }

    public static final PeakTimeTask crtPeakTimeTask(TrackingReportManager crtPeakTimeTask2) {
        Intrinsics.checkParameterIsNotNull(crtPeakTimeTask2, "$this$crtPeakTimeTask");
        return crtPeakTimeTask;
    }
}
