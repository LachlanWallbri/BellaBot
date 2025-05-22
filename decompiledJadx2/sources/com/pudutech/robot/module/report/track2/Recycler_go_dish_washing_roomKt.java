package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseGoDishTaskConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: recycler_go_dish_washing_room.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"newGoDishwashingRoomTask", "Lcom/pudutech/robot/module/report/track2/GoDishwashingRoomTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "sessionId", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseGoDishTaskConfig;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Recycler_go_dish_washing_roomKt {
    public static /* synthetic */ GoDishwashingRoomTask newGoDishwashingRoomTask$default(TrackingReportManager trackingReportManager, String str, BaseGoDishTaskConfig baseGoDishTaskConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            baseGoDishTaskConfig = BaseGoDishTaskConfig.EmptyGoDishTaskConfig.INSTANCE;
        }
        return newGoDishwashingRoomTask(trackingReportManager, str, baseGoDishTaskConfig);
    }

    public static final GoDishwashingRoomTask newGoDishwashingRoomTask(TrackingReportManager newGoDishwashingRoomTask, String sessionId, BaseGoDishTaskConfig config) {
        Intrinsics.checkParameterIsNotNull(newGoDishwashingRoomTask, "$this$newGoDishwashingRoomTask");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(config, "config");
        return new GoDishwashingRoomTask(sessionId, config);
    }
}
