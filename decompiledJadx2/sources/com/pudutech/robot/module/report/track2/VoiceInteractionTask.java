package com.pudutech.robot.module.report.track2;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.ExtKt;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;

/* compiled from: voice_interaction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JA\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052*\u0010\u000b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\r0\f\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\rH\u0002¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/VoiceInteractionTask;", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseVoiceConfig;", "sessionId", "", "(Lcom/pudutech/robot/module/report/track2/BaseVoiceConfig;Ljava/lang/String;)V", TrackKey.SCENE_ID, "customEvent", "", "status", "pairs", "", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)V", "onAnswer", "answer", "onError", AUserTrack.UTKEY_ERROR_CODE, "", NotificationCompat.CATEGORY_MESSAGE, "onQuestion", "question", "onSkill", "skillType", "Lcom/pudutech/robot/module/report/track2/VoiceSkillType;", "onWakeup", "type", "Lcom/pudutech/robot/module/report/track2/WakeUpType;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractionTask {
    private final BaseVoiceConfig config;
    private final String scene_id;
    private final String sessionId;

    public VoiceInteractionTask(BaseVoiceConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.config = config;
        this.sessionId = sessionId;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.scene_id = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    public final void onWakeup(WakeUpType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        customEvent("ON_WAKEUP", TuplesKt.m3968to("wakeup_type", Integer.valueOf(type.getType())));
    }

    public final void onQuestion(String question) {
        Intrinsics.checkParameterIsNotNull(question, "question");
        customEvent("ON_QUESTION", TuplesKt.m3968to("question", question));
    }

    public final void onAnswer(String answer) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        customEvent("ON_ANSWER", TuplesKt.m3968to("answer", answer));
    }

    public final void onSkill(VoiceSkillType skillType) {
        Intrinsics.checkParameterIsNotNull(skillType, "skillType");
        customEvent("ON_SKILL", TuplesKt.m3968to("skill_type", Integer.valueOf(skillType.getType())));
    }

    public final void onError(int errorCode, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        customEvent("ON_ERROR", TuplesKt.m3968to("error_code", Integer.valueOf(errorCode)), TuplesKt.m3968to("error_msg", msg));
    }

    private final void customEvent(String status, Pair<String, ? extends Object>... pairs) {
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String name = TrackType.VOICE_INTERACTION.name();
        SpreadBuilder spreadBuilder = new SpreadBuilder(5);
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, status));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.SCENE_ID, this.scene_id));
        spreadBuilder.add(TuplesKt.m3968to(SpeechEvent.KEY_EVENT_SESSION_ID, this.sessionId));
        spreadBuilder.addSpread(ExtKt.toPairArray(this.config));
        spreadBuilder.addSpread(pairs);
        puduEventTrackingManager.customEvent(new CustomArgs(name, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
    }
}
