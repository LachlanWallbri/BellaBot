package com.pudutech.robot.module.report.track2;

import androidx.collection.LruCache;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.TrackingReportManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ad.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\"\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"adLruMap", "Landroidx/collection/LruCache;", "", "adTypeList", "", "", "onAdPlay", "", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "type", "id", TypedValues.Transition.S_DURATION, "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class AdKt {
    private static final List<Integer> adTypeList = CollectionsKt.listOf((Object[]) new Integer[]{-1, 1, 2, 3});
    private static final LruCache<Long, Long> adLruMap = new LruCache<>(10);

    public static final void onAdPlay(TrackingReportManager onAdPlay, int i, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(onAdPlay, "$this$onAdPlay");
        if (!adTypeList.contains(Integer.valueOf(i))) {
            onAdPlay.throwOrLog("onAdPlay error type " + i);
        }
        if (adLruMap.get(Long.valueOf(j)) == null || r4.longValue() * 0.5d <= j2) {
            adLruMap.put(Long.valueOf(j), Long.valueOf(j2));
            PuduEventTrackingManager.INSTANCE.customEvent(new CustomArgs(TrackType.AD.toString(), MapsKt.mapOf(TuplesKt.m3968to("type", Integer.valueOf(i)), TuplesKt.m3968to("id", Long.valueOf(j)), TuplesKt.m3968to(TypedValues.Transition.S_DURATION, Long.valueOf(j2))), 0));
        }
    }
}
