package com.pudutech.robot.module.voice.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePlayTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB/\b\u0016\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\u0006\u0010\u0012\u001a\u00020\u0004J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/data/SingleVoice;", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "()V", "voice", "Lcom/pudutech/robot/module/voice/data/VoiceSubItem;", "delayPlay", "", "playCount", "", "(Lcom/pudutech/robot/module/voice/data/VoiceSubItem;JI)V", "voiceName", "", "voicePath", "(Ljava/lang/String;Ljava/lang/String;JI)V", "equals", "", "other", "", "getPlayVoiceItem", "isFirstVoice", "toString", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SingleVoice extends BaseVoice {
    private VoiceSubItem voice;

    @Override // com.pudutech.robot.module.voice.data.BaseVoice
    public boolean isFirstVoice() {
        return true;
    }

    public SingleVoice() {
    }

    public /* synthetic */ SingleVoice(VoiceSubItem voiceSubItem, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(voiceSubItem, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 1 : i);
    }

    public SingleVoice(VoiceSubItem voice, long j, int i) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        this.voice = voice;
        setDelayPlay(j);
        setPlayCount(i);
    }

    public /* synthetic */ SingleVoice(String str, String str2, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) == 0 ? str2 : "", (i2 & 4) != 0 ? 0L : j, (i2 & 8) != 0 ? 1 : i);
    }

    public SingleVoice(String voiceName, String voicePath, long j, int i) {
        Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
        Intrinsics.checkParameterIsNotNull(voicePath, "voicePath");
        this.voice = new VoiceSubItem(voiceName, voicePath, 0L, 4, null);
        setDelayPlay(j);
        setPlayCount(i);
    }

    public final VoiceSubItem getPlayVoiceItem() {
        VoiceSubItem voiceSubItem = this.voice;
        if (voiceSubItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voice");
        }
        return voiceSubItem;
    }

    @Override // com.pudutech.robot.module.voice.data.BaseVoice
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SingleVoice(voice=");
        VoiceSubItem voiceSubItem = this.voice;
        if (voiceSubItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voice");
        }
        sb.append(voiceSubItem);
        sb.append(", ");
        sb.append(super.toString());
        sb.append(')');
        return sb.toString();
    }

    public boolean equals(Object other) {
        if ((other != null ? other.hashCode() : 0) == hashCode()) {
            return true;
        }
        if (!(other instanceof SingleVoice)) {
            return false;
        }
        SingleVoice singleVoice = (SingleVoice) other;
        if (singleVoice.getDelayPlay() == getDelayPlay() && singleVoice.getPlayCount() == getPlayCount()) {
            if (singleVoice.voice == null) {
                Intrinsics.throwUninitializedPropertyAccessException("voice");
            }
            if (this.voice == null) {
                Intrinsics.throwUninitializedPropertyAccessException("voice");
            }
            if (!(!Intrinsics.areEqual(r7, r2))) {
                return true;
            }
        }
        return false;
    }
}
