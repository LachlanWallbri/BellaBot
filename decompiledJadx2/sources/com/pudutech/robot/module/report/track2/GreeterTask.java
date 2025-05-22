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

/* compiled from: greeter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\u0016¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005J\u0016\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u000bJ\u0006\u0010\u001a\u001a\u00020\u0016J\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/GreeterTask;", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask;", "config", "Lcom/pudutech/robot/module/report/track2/BaseGreeterConfig;", "sessionId", "", "(Lcom/pudutech/robot/module/report/track2/BaseGreeterConfig;Ljava/lang/String;)V", "getConfig", "()Lcom/pudutech/robot/module/report/track2/BaseGreeterConfig;", "finishOneCauseList", "", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$FinishOneCause;", "getSessionId", "()Ljava/lang/String;", "stopCauseList", "Lcom/pudutech/robot/module/report/track2/BaseMoveTrackTask$StopCause;", "commonArgs", "", "Lkotlin/Pair;", "", "()[Lkotlin/Pair;", "onArrive", "", "destination", "onFinishOne", "cause", "onStart", "onStop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class GreeterTask extends BaseMoveTrackTask {
    private final BaseGreeterConfig config;
    private final List<BaseMoveTrackTask.FinishOneCause> finishOneCauseList;
    private final String sessionId;
    private final List<BaseMoveTrackTask.StopCause> stopCauseList;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GreeterTask(BaseGreeterConfig config, String sessionId) {
        super(TrackType.GREETER);
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.config = config;
        this.sessionId = sessionId;
        this.stopCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.StopCause[]{BaseMoveTrackTask.StopCause.Normal, BaseMoveTrackTask.StopCause.Charge, BaseMoveTrackTask.StopCause.CountDown, BaseMoveTrackTask.StopCause.Cancel, BaseMoveTrackTask.StopCause.LocalModification, BaseMoveTrackTask.StopCause.LostLocation, BaseMoveTrackTask.StopCause.Exit, BaseMoveTrackTask.StopCause.Abnormal});
        this.finishOneCauseList = CollectionsKt.listOf((Object[]) new BaseMoveTrackTask.FinishOneCause[]{BaseMoveTrackTask.FinishOneCause.Normal, BaseMoveTrackTask.FinishOneCause.Click, BaseMoveTrackTask.FinishOneCause.CountDown, BaseMoveTrackTask.FinishOneCause.PowerBtn, BaseMoveTrackTask.FinishOneCause.InAdvance});
    }

    public final BaseGreeterConfig getConfig() {
        return this.config;
    }

    public final String getSessionId() {
        return this.sessionId;
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
}
