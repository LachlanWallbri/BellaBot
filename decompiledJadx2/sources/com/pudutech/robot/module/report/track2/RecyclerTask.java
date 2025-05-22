package com.pudutech.robot.module.report.track2;

import com.iflytek.cloud.SpeechEvent;
import com.pudutech.robot.module.report.ExtKt;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: recycler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0016¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0003J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\tJ\u0006\u0010\u0019\u001a\u00020\u0012J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/RecyclerTask;", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask;", "sessionId", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;", "(Ljava/lang/String;Lcom/pudutech/robot/module/report/track2/BaseRecyclerTaskConfig;)V", "finishOneCauseList", "", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "stopCauseList", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "commonArgs", "", "Lkotlin/Pair;", "", "()[Lkotlin/Pair;", "onAdSwitch", "", "general", "", "onArrive", "destination", "onFinishOne", "cause", "onStart", "onStop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class RecyclerTask extends BaseMoveTrackTask {
    private final BaseRecyclerTaskConfig config;
    private final List<BaseMoveTrackTask.FinishOneCause> finishOneCauseList;
    private final String sessionId;
    private final List<BaseMoveTrackTask.StopCause> stopCauseList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerTask(String sessionId, BaseRecyclerTaskConfig config) {
        super(TrackType.RECYCLER);
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.sessionId = sessionId;
        this.config = config;
        this.stopCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.StopCause[]{BaseMoveTrackTask.StopCause.Normal, BaseMoveTrackTask.StopCause.Charge, BaseMoveTrackTask.StopCause.Cancel, BaseMoveTrackTask.StopCause.LostLocation, BaseMoveTrackTask.StopCause.Abnormal, BaseMoveTrackTask.StopCause.GoToInventory, BaseMoveTrackTask.StopCause.Exit});
        this.finishOneCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.FinishOneCause[]{BaseMoveTrackTask.FinishOneCause.Normal, BaseMoveTrackTask.FinishOneCause.Click, BaseMoveTrackTask.FinishOneCause.CountDown});
    }

    public final void onStart() {
        BusinessStatus businessStatus = BusinessStatus.ON_START;
        Pair<String, Object>[] pairArray = ExtKt.toPairArray(this.config);
        customEvent$module_robot_report_release(businessStatus, (Pair[]) Arrays.copyOf(pairArray, pairArray.length));
    }

    @Override // com.pudutech.robot.module.report.track2.BaseMoveTrackTask
    public Pair<String, Object>[] commonArgs() {
        return new Pair[]{TuplesKt.m3968to(SpeechEvent.KEY_EVENT_SESSION_ID, this.sessionId)};
    }

    public final void onStop(BaseMoveTrackTask.StopCause cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        if (!this.stopCauseList.contains(cause)) {
            TrackingReportManager.INSTANCE.throwOrLog(getEvent() + " stopError.causeError cause=" + cause);
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_STOP, TuplesKt.m3968to("cause", Integer.valueOf(cause.getType())));
    }

    public final void onArrive(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        customEvent$module_robot_report_release(BusinessStatus.ON_ARRIVE, TuplesKt.m3968to("destination", destination));
    }

    public final void onFinishOne(String destination, BaseMoveTrackTask.FinishOneCause cause) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        if (!this.finishOneCauseList.contains(cause)) {
            TrackingReportManager.INSTANCE.throwOrLog(getEvent() + " onFinishOne.causeError cause=" + cause);
        }
        customEvent$module_robot_report_release(BusinessStatus.ON_FINISH_ONE, TuplesKt.m3968to("destination", destination), TuplesKt.m3968to("cause", Integer.valueOf(cause.getType())));
    }

    public final void onAdSwitch(boolean general) {
        customEvent$module_robot_report_release(BusinessStatus.ON_AD_SWITCH, TuplesKt.m3968to("general", Boolean.valueOf(general)));
    }
}
