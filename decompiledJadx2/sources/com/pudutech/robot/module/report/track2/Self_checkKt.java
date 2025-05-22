package com.pudutech.robot.module.report.track2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.TrackingReportManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: self_check.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0007\u001a\u00020\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004¨\u0006\r"}, m3961d2 = {"checkStepList", "", "", "getCheckStepList", "()Ljava/util/List;", "statusList", "getStatusList", "selfCheck", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "step", "status", TmpConstant.SERVICE_DESC, "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Self_checkKt {
    private static final List<String> checkStepList = CollectionsKt.listOf((Object[]) new String[]{"OTACheck", "HDVersionCheck", "ConnectRobotHardwareService", "CheckCAN", "CheckMachineInfo", "CheckESP", "CheckEncoder", "CheckIMU", "CheckBattery", "CheckCamera", "CheckLidar", "CheckMagicSensor", "CheckRGBD", "CheckMap", "EmptyMap", "NoToPoMap", "LoadLocateMap", "LoadTopoMap", "ConnectCoreService", "Finish"});
    private static final List<String> statusList = CollectionsKt.listOf((Object[]) new String[]{"Idle", "Running", "Success", "Fail"});

    public static final List<String> getCheckStepList() {
        return checkStepList;
    }

    public static final List<String> getStatusList() {
        return statusList;
    }

    public static final void selfCheck(TrackingReportManager selfCheck, String step, String status, String str) {
        Intrinsics.checkParameterIsNotNull(selfCheck, "$this$selfCheck");
        Intrinsics.checkParameterIsNotNull(step, "step");
        Intrinsics.checkParameterIsNotNull(status, "status");
        if (!checkStepList.contains(step)) {
            selfCheck.throwOrLog("step > " + step + " 未知的自检step");
        }
        if (!statusList.contains(status)) {
            selfCheck.throwOrLog("status > " + status + " 未知的自检status");
        }
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String trackType = TrackType.SELF_CHECK.toString();
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.m3968to(TrackKey.CHECK_STEP, step);
        pairArr[1] = TuplesKt.m3968to(TrackKey.CHECK_STATE, status);
        if (str == null) {
            str = "";
        }
        pairArr[2] = TuplesKt.m3968to(TrackKey.CHECK_DESCRIPTION, str);
        puduEventTrackingManager.customEvent(new CustomArgs(trackType, MapsKt.mapOf(pairArr), 0));
    }
}
