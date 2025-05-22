package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: upgrade_ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, m3961d2 = {"onUpgrade", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "status", "Lcom/pudutech/robot/module/report/track2/BusinessStatus;", "type", "Lcom/pudutech/robot/module/report/track2/UpgradeType;", "targetVersion", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Upgrade_extKt {
    public static /* synthetic */ void onUpgrade$default(TrackingReportManager trackingReportManager, BusinessStatus businessStatus, UpgradeType upgradeType, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "";
        }
        onUpgrade(trackingReportManager, businessStatus, upgradeType, str);
    }

    public static final void onUpgrade(TrackingReportManager onUpgrade, BusinessStatus status, UpgradeType type, String targetVersion) {
        Intrinsics.checkParameterIsNotNull(onUpgrade, "$this$onUpgrade");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(targetVersion, "targetVersion");
        PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.UPGRADE.toString(), MapsKt.mapOf(TuplesKt.m3968to(TrackKey.UPGRADE_TYPE, type.name()), TuplesKt.m3968to(TrackKey.TARGET_VERSION, targetVersion), TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, status.name())), 0));
    }
}
