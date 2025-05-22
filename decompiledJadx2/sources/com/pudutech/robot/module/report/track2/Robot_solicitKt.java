package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: robot_solicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"MIN_DURATION", "", "PEOPLE_INVALID_TIME", "scope", "Lkotlinx/coroutines/CoroutineScope;", "newSolicitTask", "Lcom/pudutech/robot/module/report/track2/SolicitTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "config", "Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Robot_solicitKt {
    private static final long MIN_DURATION = 3000;
    private static final long PEOPLE_INVALID_TIME = 3000;
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    public static final SolicitTask newSolicitTask(TrackingReportManager newSolicitTask, BaseSolicitConfig config) {
        Intrinsics.checkParameterIsNotNull(newSolicitTask, "$this$newSolicitTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        return new SolicitTask(config);
    }
}
