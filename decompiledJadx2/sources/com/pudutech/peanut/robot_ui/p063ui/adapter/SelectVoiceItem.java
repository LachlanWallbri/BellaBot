package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.robot.module.voice.data.VoicePlayTask;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\n\"\u0004\b\r\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006!"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectVoiceItem;", "", "voice", "Lcom/pudutech/peanut/robot_ui/ui/adapter/AppointVoice;", "isSelect", "", "isPlaying", "voiceTask", "Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "(Lcom/pudutech/peanut/robot_ui/ui/adapter/AppointVoice;ZZLcom/pudutech/robot/module/voice/data/VoicePlayTask;)V", "()Z", "setPlaying", "(Z)V", "setSelect", "getVoice", "()Lcom/pudutech/peanut/robot_ui/ui/adapter/AppointVoice;", "setVoice", "(Lcom/pudutech/peanut/robot_ui/ui/adapter/AppointVoice;)V", "getVoiceTask", "()Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "setVoiceTask", "(Lcom/pudutech/robot/module/voice/data/VoicePlayTask;)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SelectVoiceItem {
    private boolean isPlaying;
    private boolean isSelect;
    private AppointVoice voice;
    private VoicePlayTask voiceTask;

    public static /* synthetic */ SelectVoiceItem copy$default(SelectVoiceItem selectVoiceItem, AppointVoice appointVoice, boolean z, boolean z2, VoicePlayTask voicePlayTask, int i, Object obj) {
        if ((i & 1) != 0) {
            appointVoice = selectVoiceItem.voice;
        }
        if ((i & 2) != 0) {
            z = selectVoiceItem.isSelect;
        }
        if ((i & 4) != 0) {
            z2 = selectVoiceItem.isPlaying;
        }
        if ((i & 8) != 0) {
            voicePlayTask = selectVoiceItem.voiceTask;
        }
        return selectVoiceItem.copy(appointVoice, z, z2, voicePlayTask);
    }

    /* renamed from: component1, reason: from getter */
    public final AppointVoice getVoice() {
        return this.voice;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    /* renamed from: component4, reason: from getter */
    public final VoicePlayTask getVoiceTask() {
        return this.voiceTask;
    }

    public final SelectVoiceItem copy(AppointVoice voice, boolean isSelect, boolean isPlaying, VoicePlayTask voiceTask) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        return new SelectVoiceItem(voice, isSelect, isPlaying, voiceTask);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectVoiceItem)) {
            return false;
        }
        SelectVoiceItem selectVoiceItem = (SelectVoiceItem) other;
        return Intrinsics.areEqual(this.voice, selectVoiceItem.voice) && this.isSelect == selectVoiceItem.isSelect && this.isPlaying == selectVoiceItem.isPlaying && Intrinsics.areEqual(this.voiceTask, selectVoiceItem.voiceTask);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        AppointVoice appointVoice = this.voice;
        int hashCode = (appointVoice != null ? appointVoice.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isPlaying;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        VoicePlayTask voicePlayTask = this.voiceTask;
        return i4 + (voicePlayTask != null ? voicePlayTask.hashCode() : 0);
    }

    public String toString() {
        return "SelectVoiceItem(voice=" + this.voice + ", isSelect=" + this.isSelect + ", isPlaying=" + this.isPlaying + ", voiceTask=" + this.voiceTask + ")";
    }

    public SelectVoiceItem(AppointVoice voice, boolean z, boolean z2, VoicePlayTask voicePlayTask) {
        Intrinsics.checkParameterIsNotNull(voice, "voice");
        this.voice = voice;
        this.isSelect = z;
        this.isPlaying = z2;
        this.voiceTask = voicePlayTask;
    }

    public final AppointVoice getVoice() {
        return this.voice;
    }

    public final void setVoice(AppointVoice appointVoice) {
        Intrinsics.checkParameterIsNotNull(appointVoice, "<set-?>");
        this.voice = appointVoice;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public final void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    public /* synthetic */ SelectVoiceItem(AppointVoice appointVoice, boolean z, boolean z2, VoicePlayTask voicePlayTask, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(appointVoice, z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? (VoicePlayTask) null : voicePlayTask);
    }

    public final VoicePlayTask getVoiceTask() {
        return this.voiceTask;
    }

    public final void setVoiceTask(VoicePlayTask voicePlayTask) {
        this.voiceTask = voicePlayTask;
    }
}
