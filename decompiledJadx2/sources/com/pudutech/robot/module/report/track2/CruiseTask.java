package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.ExtKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: cruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\u0006\u0010\u000b\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/CruiseTask;", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask;", "config", "Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;", "(Lcom/pudutech/robot/module/report/track2/BaseCruiseConfig;)V", "cruiseWelcome", "", "from", "", "run", "start", "stop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CruiseTask extends BaseMoveTrackTask {
    private final BaseCruiseConfig config;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CruiseTask(BaseCruiseConfig config) {
        super(TrackType.CRUISE);
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.config = config;
    }

    public final void start() {
        BusinessStatus businessStatus = BusinessStatus.ON_START;
        Pair<String, Object>[] pairArray = ExtKt.toPairArray(this.config);
        customEvent$module_robot_report_release(businessStatus, (Pair[]) Arrays.copyOf(pairArray, pairArray.length));
    }

    public final void stop() {
        BusinessStatus businessStatus = BusinessStatus.ON_STOP;
        if (getCrtStatus() == BusinessStatus.CRUISE_WELCOME) {
            customEvent$module_robot_report_release(BusinessStatus.RUN, new Pair[0]);
        }
        customEvent$module_robot_report_release(businessStatus, new Pair[0]);
        CruiseKt.crtCruiseTask = (CruiseTask) null;
    }

    public final void run() {
        customEvent$module_robot_report_release(BusinessStatus.RUN, new Pair[0]);
    }

    public final void cruiseWelcome(int from) {
        customEvent$module_robot_report_release(BusinessStatus.CRUISE_WELCOME, TuplesKt.m3968to("from", Integer.valueOf(from)));
    }
}
