package com.pudutech.voiceinteraction.component.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: VoiceMediaType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/VoiceMediaType;", "", "voiceMediaName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getVoiceMediaName", "()Ljava/lang/String;", "setVoiceMediaName", "(Ljava/lang/String;)V", "WAKE_UP_1", "WAKE_UP_2", "WAKE_UP_3", "NO_NETWORK_CONNECTION", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum VoiceMediaType {
    WAKE_UP_1("voice_wakeup_01.mp3"),
    WAKE_UP_2("voice_wakeup_02.mp3"),
    WAKE_UP_3("voice_wakeup_03.mp3"),
    NO_NETWORK_CONNECTION("no_network_connection.mp3");

    private String voiceMediaName;

    VoiceMediaType(String str) {
        this.voiceMediaName = str;
    }

    public final String getVoiceMediaName() {
        return this.voiceMediaName;
    }

    public final void setVoiceMediaName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.voiceMediaName = str;
    }
}
