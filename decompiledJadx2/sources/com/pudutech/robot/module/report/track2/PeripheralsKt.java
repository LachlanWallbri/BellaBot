package com.pudutech.robot.module.report.track2;

import com.iflytek.cloud.SpeechEvent;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.ExtKt;
import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: peripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"onPeripheralsStatus", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "status", "Lcom/pudutech/robot/module/report/track2/BasePeripheralsStatus;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeripheralsKt {
    public static /* synthetic */ void onPeripheralsStatus$default(TrackingReportManager trackingReportManager, BasePeripheralsStatus basePeripheralsStatus, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        onPeripheralsStatus(trackingReportManager, basePeripheralsStatus, str);
    }

    public static final void onPeripheralsStatus(TrackingReportManager onPeripheralsStatus, BasePeripheralsStatus status, String sessionId) {
        Intrinsics.checkParameterIsNotNull(onPeripheralsStatus, "$this$onPeripheralsStatus");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String trackType = TrackType.PERIPHERALS.toString();
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.addSpread(ExtKt.toPairArray(status));
        spreadBuilder.add(TuplesKt.m3968to(SpeechEvent.KEY_EVENT_SESSION_ID, sessionId));
        puduEventTrackingManager.customEvent(new CustomArgs(trackType, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
    }
}
