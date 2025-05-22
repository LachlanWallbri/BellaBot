package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: charge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, m3961d2 = {"newBatteryChargeTask", "Lcom/pudutech/robot/module/report/track2/BatteryChargeTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "config", "Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig;", "onChargeChange", "", "power", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ChargeKt {
    public static final void onChargeChange(TrackingReportManager onChargeChange, int i) {
        Intrinsics.checkParameterIsNotNull(onChargeChange, "$this$onChargeChange");
        if (i < 0 || i > 100) {
            onChargeChange.throwOrLog("onChargeChange error power " + i);
        }
        PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.CHARGE.toString(), MapsKt.mapOf(TuplesKt.m3968to(TrackKey.POWER_PERCENT, Integer.valueOf(i))), 0));
    }

    public static final BatteryChargeTask newBatteryChargeTask(TrackingReportManager newBatteryChargeTask, BaseBatteryChargeConfig config) {
        Intrinsics.checkParameterIsNotNull(newBatteryChargeTask, "$this$newBatteryChargeTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        return new BatteryChargeTask(config);
    }
}
