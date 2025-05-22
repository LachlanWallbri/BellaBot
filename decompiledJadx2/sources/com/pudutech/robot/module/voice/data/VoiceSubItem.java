package com.pudutech.robot.module.voice.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePlayTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/data/VoiceSubItem;", "", "voiceName", "", "voicePath", "delayPlay", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getDelayPlay", "()J", "setDelayPlay", "(J)V", "getVoiceName", "()Ljava/lang/String;", "setVoiceName", "(Ljava/lang/String;)V", "getVoicePath", "setVoicePath", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class VoiceSubItem {
    private long delayPlay;
    private String voiceName;
    private String voicePath;

    public static /* synthetic */ VoiceSubItem copy$default(VoiceSubItem voiceSubItem, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = voiceSubItem.voiceName;
        }
        if ((i & 2) != 0) {
            str2 = voiceSubItem.voicePath;
        }
        if ((i & 4) != 0) {
            j = voiceSubItem.delayPlay;
        }
        return voiceSubItem.copy(str, str2, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVoiceName() {
        return this.voiceName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVoicePath() {
        return this.voicePath;
    }

    /* renamed from: component3, reason: from getter */
    public final long getDelayPlay() {
        return this.delayPlay;
    }

    public final VoiceSubItem copy(String voiceName, String voicePath, long delayPlay) {
        Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
        Intrinsics.checkParameterIsNotNull(voicePath, "voicePath");
        return new VoiceSubItem(voiceName, voicePath, delayPlay);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoiceSubItem)) {
            return false;
        }
        VoiceSubItem voiceSubItem = (VoiceSubItem) other;
        return Intrinsics.areEqual(this.voiceName, voiceSubItem.voiceName) && Intrinsics.areEqual(this.voicePath, voiceSubItem.voicePath) && this.delayPlay == voiceSubItem.delayPlay;
    }

    public int hashCode() {
        String str = this.voiceName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.voicePath;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        long j = this.delayPlay;
        return hashCode2 + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "VoiceSubItem(voiceName=" + this.voiceName + ", voicePath=" + this.voicePath + ", delayPlay=" + this.delayPlay + ")";
    }

    public VoiceSubItem(String voiceName, String voicePath, long j) {
        Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
        Intrinsics.checkParameterIsNotNull(voicePath, "voicePath");
        this.voiceName = voiceName;
        this.voicePath = voicePath;
        this.delayPlay = j;
    }

    public final String getVoiceName() {
        return this.voiceName;
    }

    public final void setVoiceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.voiceName = str;
    }

    public final String getVoicePath() {
        return this.voicePath;
    }

    public final void setVoicePath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.voicePath = str;
    }

    public /* synthetic */ VoiceSubItem(String str, String str2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? 0L : j);
    }

    public final long getDelayPlay() {
        return this.delayPlay;
    }

    public final void setDelayPlay(long j) {
        this.delayPlay = j;
    }
}
