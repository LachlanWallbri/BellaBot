package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: cruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0004\u001a\f\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u001c\u0010\b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"crtCruiseTask", "Lcom/pudutech/robot/module/report/track2/CruiseTask;", "crtCruiseTask2", "Lcom/pudutech/robot/module/report/track2/CruiseTask2;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "newCruiseTask", "config", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "newCruiseTask2", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CruiseKt {
    private static CruiseTask crtCruiseTask;
    private static CruiseTask2 crtCruiseTask2;

    public static final CruiseTask newCruiseTask(TrackingReportManager newCruiseTask, BaseCruiseConfig config) {
        Intrinsics.checkParameterIsNotNull(newCruiseTask, "$this$newCruiseTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        crtCruiseTask = new CruiseTask(config);
        CruiseTask cruiseTask = crtCruiseTask;
        if (cruiseTask == null) {
            Intrinsics.throwNpe();
        }
        return cruiseTask;
    }

    public static final CruiseTask crtCruiseTask(TrackingReportManager crtCruiseTask3) {
        Intrinsics.checkParameterIsNotNull(crtCruiseTask3, "$this$crtCruiseTask");
        return crtCruiseTask;
    }

    public static /* synthetic */ CruiseTask2 newCruiseTask2$default(TrackingReportManager trackingReportManager, BaseCruiseConfig baseCruiseConfig, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return newCruiseTask2(trackingReportManager, baseCruiseConfig, str);
    }

    public static final CruiseTask2 newCruiseTask2(TrackingReportManager newCruiseTask2, BaseCruiseConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newCruiseTask2, "$this$newCruiseTask2");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        crtCruiseTask2 = new CruiseTask2(config, sessionId);
        CruiseTask2 cruiseTask2 = crtCruiseTask2;
        if (cruiseTask2 == null) {
            Intrinsics.throwNpe();
        }
        return cruiseTask2;
    }

    public static final CruiseTask2 crtCruiseTask2(TrackingReportManager crtCruiseTask22) {
        Intrinsics.checkParameterIsNotNull(crtCruiseTask22, "$this$crtCruiseTask2");
        return crtCruiseTask2;
    }
}
