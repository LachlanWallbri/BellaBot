package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;

/* compiled from: peaktime_event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\u001f\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00130\u0012H\u0002¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u000eJ\u0006\u0010\u0016\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/PeakTimeTask;", "", "eventType", "Lcom/pudutech/robot/module/report/track2/EventType;", "sessionId", "", "(Lcom/pudutech/robot/module/report/track2/EventType;Ljava/lang/String;)V", "getEventType", "()Lcom/pudutech/robot/module/report/track2/EventType;", "getSessionId", "()Ljava/lang/String;", "uniqueId", "getUniqueId", "customEvent", "", "status", "Lcom/pudutech/robot/module/report/track2/EventStatus;", "getPose", "", "Lkotlin/Pair;", "()[Lkotlin/Pair;", "onStart", "onStop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeakTimeTask {
    private final EventType eventType;
    private final String sessionId;
    private final String uniqueId;

    public PeakTimeTask(EventType eventType, String sessionId) {
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.eventType = eventType;
        this.sessionId = sessionId;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.uniqueId = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    public final EventType getEventType() {
        return this.eventType;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final String getUniqueId() {
        return this.uniqueId;
    }

    public final void onStart() {
        customEvent(this.eventType, EventStatus.START);
    }

    public final void onStop() {
        customEvent(this.eventType, EventStatus.STOP);
        Peaktime_eventKt.crtPeakTimeTask = (PeakTimeTask) null;
    }

    public final void customEvent(EventType eventType, EventStatus status) {
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(status, "status");
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String name = TrackType.PEAKTIME_EVENT.name();
        SpreadBuilder spreadBuilder = new SpreadBuilder(5);
        spreadBuilder.add(TuplesKt.m3968to("sessionId", this.sessionId));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.SCENE_ID, this.uniqueId));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.EVENT_TYPE, eventType.name()));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.EVENT_STATUS, status.name()));
        spreadBuilder.addSpread(getPose());
        puduEventTrackingManager.customEvent(new CustomArgs(name, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
    }

    private final Pair<String, Object>[] getPose() {
        PoseData pose = MileageHelper.INSTANCE.getPose();
        return pose != null ? new Pair[]{TuplesKt.m3968to(TrackKey.POSITION_X, Double.valueOf(pose.getX())), TuplesKt.m3968to(TrackKey.POSITION_Y, Double.valueOf(pose.getY()))} : new Pair[0];
    }
}
