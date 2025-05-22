package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: alarm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"onAlarm", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "type", "", "level", "Lcom/pudutech/robot/module/report/track2/AlarmType;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class AlarmKt {
    public static final void onAlarm(TrackingReportManager onAlarm, String type, AlarmType level) {
        Intrinsics.checkParameterIsNotNull(onAlarm, "$this$onAlarm");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(level, "level");
        PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.ALARM_EVENT.toString(), MapsKt.mapOf(TuplesKt.m3968to(TrackKey.ALARM_TYPE, type), TuplesKt.m3968to(TrackKey.ALARM_LEVEL, level.name())), 0));
    }
}
