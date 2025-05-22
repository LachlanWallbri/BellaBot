package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: shop_id_ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"SHOP_ID", "", "onShopIdChange", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "shopId", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Shop_id_extKt {
    private static final String SHOP_ID = "shop_id";

    public static final void onShopIdChange(TrackingReportManager onShopIdChange, String str) {
        Intrinsics.checkParameterIsNotNull(onShopIdChange, "$this$onShopIdChange");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            PuduEventTrackingManager.INSTANCE.removeHeaderCustom(SHOP_ID);
        } else {
            PuduEventTrackingManager.INSTANCE.addHeaderCustom(TuplesKt.m3968to(SHOP_ID, str));
        }
    }
}
