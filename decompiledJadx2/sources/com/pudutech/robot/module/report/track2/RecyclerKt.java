package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: recycler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"newRecyclerTask", "Lcom/pudutech/robot/module/report/track2/RecyclerTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "sessionId", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class RecyclerKt {
    public static final RecyclerTask newRecyclerTask(TrackingReportManager newRecyclerTask, String sessionId, BaseRecyclerTaskConfig config) {
        Intrinsics.checkParameterIsNotNull(newRecyclerTask, "$this$newRecyclerTask");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(config, "config");
        return new RecyclerTask(sessionId, config);
    }
}
