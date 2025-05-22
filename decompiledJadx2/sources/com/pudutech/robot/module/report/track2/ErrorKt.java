package com.pudutech.robot.module.report.track2;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.robot.module.report.TrackingReportManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: error.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\u001a4\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"levelList", "", "", "onError", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "errorType", "detail", "level", "time", "", TmpConstant.SERVICE_DESC, "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ErrorKt {
    private static final List<String> levelList = CollectionsKt.listOf((Object[]) new String[]{MoveError.LEVEL_ERROR, MoveError.LEVEL_FATAL, MoveError.LEVEL_EVENT, MoveError.LEVEL_WARNING, "Warning", "Warn", "WARN"});

    public static /* synthetic */ void onError$default(TrackingReportManager trackingReportManager, String str, String str2, String str3, long j, String str4, int i, Object obj) {
        if ((i & 16) != 0) {
            str4 = "";
        }
        onError(trackingReportManager, str, str2, str3, j, str4);
    }

    public static final void onError(TrackingReportManager onError, String errorType, String detail, String level, long j, String desc) {
        Intrinsics.checkParameterIsNotNull(onError, "$this$onError");
        Intrinsics.checkParameterIsNotNull(errorType, "errorType");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (!levelList.contains(level)) {
            onError.throwOrLog("level > " + level + " 未知的异常level");
        }
        PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.ERROR_FAULT.toString(), MapsKt.mapOf(TuplesKt.m3968to(TrackKey.ERROR_TYPE, errorType), TuplesKt.m3968to(TrackKey.ERROR_LEVEL, level), TuplesKt.m3968to(TrackKey.ERROR_DETAIL, detail), TuplesKt.m3968to(TrackKey.ERROR_DESCRIPTION, desc), TuplesKt.m3968to("time", Long.valueOf(j))), 0));
    }
}
