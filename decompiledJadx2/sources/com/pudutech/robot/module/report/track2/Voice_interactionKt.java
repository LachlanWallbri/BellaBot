package com.pudutech.robot.module.report.track2;

import com.pudutech.robot.module.report.TrackingReportManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: voice_interaction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"newVoiceInteractionTask", "Lcom/pudutech/robot/module/report/track2/VoiceInteractionTask;", "Lcom/pudutech/robot/module/report/TrackingReportManager;", "config", "Lcom/pudutech/robot/module/report/track2/BaseVoiceConfig;", "sessionId", "", "module_robot_report_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class Voice_interactionKt {
    public static final VoiceInteractionTask newVoiceInteractionTask(TrackingReportManager newVoiceInteractionTask, BaseVoiceConfig config, String sessionId) {
        Intrinsics.checkParameterIsNotNull(newVoiceInteractionTask, "$this$newVoiceInteractionTask");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        return new VoiceInteractionTask(config, sessionId);
    }

    public static /* synthetic */ VoiceInteractionTask newVoiceInteractionTask$default(TrackingReportManager trackingReportManager, BaseVoiceConfig baseVoiceConfig, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return newVoiceInteractionTask(trackingReportManager, baseVoiceConfig, str);
    }
}
