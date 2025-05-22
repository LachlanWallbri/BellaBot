package com.pudutech.bumblebee.robot_ui.track.task;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.robot.module.report.TrackingReportManager;
import com.pudutech.robot.module.report.track2.BaseVoiceConfig;
import com.pudutech.robot.module.report.track2.VoiceInteractionTask;
import com.pudutech.robot.module.report.track2.VoiceSkillType;
import com.pudutech.robot.module.report.track2.Voice_interactionKt;
import com.pudutech.robot.module.report.track2.WakeUpType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceInteractionTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004J\u0016\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/track/task/VoiceInteractionTrack;", "", "()V", "<set-?>", "", "pageKey", "getPageKey", "()Ljava/lang/String;", "sessionId", "getSessionId", "task", "Lcom/pudutech/robot/module/report/track2/VoiceInteractionTask;", "onAnswer", "", "answer", "onCreateTask", "onError", AUserTrack.UTKEY_ERROR_CODE, "", NotificationCompat.CATEGORY_MESSAGE, "onQuestion", "question", "onSkill", "skillType", "Lcom/pudutech/robot/module/report/track2/VoiceSkillType;", "onWakeup", "type", "Lcom/pudutech/robot/module/report/track2/WakeUpType;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceInteractionTrack {
    private static VoiceInteractionTask task;
    public static final VoiceInteractionTrack INSTANCE = new VoiceInteractionTrack();
    private static String sessionId = "";
    private static String pageKey = "";

    private VoiceInteractionTrack() {
    }

    public final String getSessionId() {
        return sessionId;
    }

    public final String getPageKey() {
        return pageKey;
    }

    public static /* synthetic */ void onCreateTask$default(VoiceInteractionTrack voiceInteractionTrack, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        voiceInteractionTrack.onCreateTask(str, str2);
    }

    public final void onCreateTask(String pageKey2, String sessionId2) {
        Intrinsics.checkParameterIsNotNull(pageKey2, "pageKey");
        Intrinsics.checkParameterIsNotNull(sessionId2, "sessionId");
        sessionId = sessionId2;
        pageKey = pageKey2;
        task = Voice_interactionKt.newVoiceInteractionTask(TrackingReportManager.INSTANCE, new BaseVoiceConfig.BellaVoiceConfig(pageKey2), sessionId2);
    }

    public final void onWakeup(WakeUpType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        VoiceInteractionTask voiceInteractionTask = task;
        if (voiceInteractionTask != null) {
            voiceInteractionTask.onWakeup(type);
        }
    }

    public final void onQuestion(String question) {
        Intrinsics.checkParameterIsNotNull(question, "question");
        VoiceInteractionTask voiceInteractionTask = task;
        if (voiceInteractionTask != null) {
            voiceInteractionTask.onQuestion(question);
        }
    }

    public final void onAnswer(String answer) {
        Intrinsics.checkParameterIsNotNull(answer, "answer");
        VoiceInteractionTask voiceInteractionTask = task;
        if (voiceInteractionTask != null) {
            voiceInteractionTask.onAnswer(answer);
        }
    }

    public final void onSkill(VoiceSkillType skillType) {
        Intrinsics.checkParameterIsNotNull(skillType, "skillType");
        VoiceInteractionTask voiceInteractionTask = task;
        if (voiceInteractionTask != null) {
            voiceInteractionTask.onSkill(skillType);
        }
    }

    public final void onError(int errorCode, String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        VoiceInteractionTask voiceInteractionTask = task;
        if (voiceInteractionTask != null) {
            voiceInteractionTask.onError(errorCode, msg);
        }
    }
}
