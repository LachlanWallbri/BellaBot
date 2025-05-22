package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.robot.module.report.TrackingReportManager;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: device_ext_info.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"TAG", "", "onDeviceExtInfo", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "info", "Lcom/pudutech/robot/module/report/track2/DeviceExtInfo;", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Device_ext_infoKt {
    private static final String TAG = TrackType.DEVICE_EXT_INFO.toString();

    public static final void onDeviceExtInfo(TrackingReportManager onDeviceExtInfo, DeviceExtInfo info) {
        Intrinsics.checkParameterIsNotNull(onDeviceExtInfo, "$this$onDeviceExtInfo");
        Intrinsics.checkParameterIsNotNull(info, "info");
        NetWorkLog.INSTANCE.mo3280i(TAG, "onDeviceExtInfo > " + info);
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String str = TAG;
        Map<String, Object> gsonToMaps = GsonUtils.gsonToMaps(GsonUtils.toJson(info));
        Intrinsics.checkExpressionValueIsNotNull(gsonToMaps, "GsonUtils.gsonToMaps(GsonUtils.toJson(info))");
        puduEventTrackingManager.customEvent(new CustomArgs(str, gsonToMaps, 0));
    }
}
