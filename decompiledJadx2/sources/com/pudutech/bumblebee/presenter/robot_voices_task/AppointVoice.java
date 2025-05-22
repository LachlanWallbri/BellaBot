package com.pudutech.bumblebee.presenter.robot_voices_task;

import com.pudutech.resources.voice.VoiceItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppointVoice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;", "", "()V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "info", "", "getInfo", "()Ljava/lang/String;", "setInfo", "(Ljava/lang/String;)V", "voiceItem", "Lcom/pudutech/resources/voice/VoiceItem;", "getVoiceItem", "()Lcom/pudutech/resources/voice/VoiceItem;", "setVoiceItem", "(Lcom/pudutech/resources/voice/VoiceItem;)V", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AppointVoice {
    private int index = -1;
    private String info = "";
    public VoiceItem voiceItem;

    public final VoiceItem getVoiceItem() {
        VoiceItem voiceItem = this.voiceItem;
        if (voiceItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceItem");
        }
        return voiceItem;
    }

    public final void setVoiceItem(VoiceItem voiceItem) {
        Intrinsics.checkParameterIsNotNull(voiceItem, "<set-?>");
        this.voiceItem = voiceItem;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final String getInfo() {
        return this.info;
    }

    public final void setInfo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.info = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        VoiceItem voiceItem = this.voiceItem;
        if (voiceItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voiceItem");
        }
        sb.append(voiceItem);
        sb.append(' ');
        sb.append(this.index);
        sb.append(' ');
        sb.append(this.info);
        return sb.toString();
    }
}
