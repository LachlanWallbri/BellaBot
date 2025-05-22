package defpackage;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.TrackKey;
import com.pudutech.robot.module.report.track2.TrackType;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: sleep.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"preType", "", "sleepStatusMap", "", "", "uniqueId", "onSleep", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "type", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* renamed from: SleepKt, reason: from Kotlin metadata */
/* loaded from: classes4.dex */
public final class preType {
    private static int preType;
    private static final Map<Integer, String> sleepStatusMap = MapsKt.mapOf(TuplesKt.m3968to(1, "SLEEP"), TuplesKt.m3968to(2, "WAKE_UP"));
    private static String uniqueId = "";

    public static final void onSleep(TrackingReportManager onSleep, int i) {
        Intrinsics.checkParameterIsNotNull(onSleep, "$this$onSleep");
        String str = sleepStatusMap.get(Integer.valueOf(i));
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            onSleep.throwOrLog("UnKnow sleepType type = " + i);
        }
        if (i == 1) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            uniqueId = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        }
        if (preType == i) {
            NetWorkLog.INSTANCE.mo3280i("SLEEP_STATUS", "preType == type " + i);
            return;
        }
        if (uniqueId.length() == 0) {
            NetWorkLog.INSTANCE.mo3280i("SLEEP_STATUS", "uniqueId.isEmpty() " + i);
            return;
        }
        preType = i;
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String trackType = TrackType.SLEEP_STATUS.toString();
        Pair[] pairArr = new Pair[2];
        if (str == null) {
            str = "";
        }
        pairArr[0] = TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, str);
        pairArr[1] = TuplesKt.m3968to("event_unique_id", uniqueId);
        puduEventTrackingManager.customEvent(new CustomArgs(trackType, MapsKt.mapOf(pairArr), 0));
    }
}
