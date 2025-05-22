package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.robot_voices_task.AppointVoice;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SpecialModeVoiceAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\fHÆ\u0003JK\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010)\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000e\"\u0004\b\u0011\u0010\u0010R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006/"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectVoiceItem;", "", "voiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceType;", "voice", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;", "ttsVoice", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "isSelect", "", "isPlaying", "voiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "(Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceType;Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;ZZLcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;)V", "()Z", "setPlaying", "(Z)V", "setSelect", "getTtsVoice", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "setTtsVoice", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;)V", "getVoice", "()Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;", "setVoice", "(Lcom/pudutech/bumblebee/presenter/robot_voices_task/AppointVoice;)V", "getVoiceTask", "()Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "setVoiceTask", "(Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;)V", "getVoiceType", "()Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceType;", "setVoiceType", "(Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceType;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class SelectVoiceItem {
    private boolean isPlaying;
    private boolean isSelect;
    private TtsVoiceHelper.TtsConfigData ttsVoice;
    private AppointVoice voice;
    private VoiceTask voiceTask;
    private VoiceType voiceType;

    public static /* synthetic */ SelectVoiceItem copy$default(SelectVoiceItem selectVoiceItem, VoiceType voiceType, AppointVoice appointVoice, TtsVoiceHelper.TtsConfigData ttsConfigData, boolean z, boolean z2, VoiceTask voiceTask, int i, Object obj) {
        if ((i & 1) != 0) {
            voiceType = selectVoiceItem.voiceType;
        }
        if ((i & 2) != 0) {
            appointVoice = selectVoiceItem.voice;
        }
        AppointVoice appointVoice2 = appointVoice;
        if ((i & 4) != 0) {
            ttsConfigData = selectVoiceItem.ttsVoice;
        }
        TtsVoiceHelper.TtsConfigData ttsConfigData2 = ttsConfigData;
        if ((i & 8) != 0) {
            z = selectVoiceItem.isSelect;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = selectVoiceItem.isPlaying;
        }
        boolean z4 = z2;
        if ((i & 32) != 0) {
            voiceTask = selectVoiceItem.voiceTask;
        }
        return selectVoiceItem.copy(voiceType, appointVoice2, ttsConfigData2, z3, z4, voiceTask);
    }

    /* renamed from: component1, reason: from getter */
    public final VoiceType getVoiceType() {
        return this.voiceType;
    }

    /* renamed from: component2, reason: from getter */
    public final AppointVoice getVoice() {
        return this.voice;
    }

    /* renamed from: component3, reason: from getter */
    public final TtsVoiceHelper.TtsConfigData getTtsVoice() {
        return this.ttsVoice;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsPlaying() {
        return this.isPlaying;
    }

    /* renamed from: component6, reason: from getter */
    public final VoiceTask getVoiceTask() {
        return this.voiceTask;
    }

    public final SelectVoiceItem copy(VoiceType voiceType, AppointVoice voice, TtsVoiceHelper.TtsConfigData ttsVoice, boolean isSelect, boolean isPlaying, VoiceTask voiceTask) {
        Intrinsics.checkParameterIsNotNull(voiceType, "voiceType");
        return new SelectVoiceItem(voiceType, voice, ttsVoice, isSelect, isPlaying, voiceTask);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectVoiceItem)) {
            return false;
        }
        SelectVoiceItem selectVoiceItem = (SelectVoiceItem) other;
        return Intrinsics.areEqual(this.voiceType, selectVoiceItem.voiceType) && Intrinsics.areEqual(this.voice, selectVoiceItem.voice) && Intrinsics.areEqual(this.ttsVoice, selectVoiceItem.ttsVoice) && this.isSelect == selectVoiceItem.isSelect && this.isPlaying == selectVoiceItem.isPlaying && Intrinsics.areEqual(this.voiceTask, selectVoiceItem.voiceTask);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        VoiceType voiceType = this.voiceType;
        int hashCode = (voiceType != null ? voiceType.hashCode() : 0) * 31;
        AppointVoice appointVoice = this.voice;
        int hashCode2 = (hashCode + (appointVoice != null ? appointVoice.hashCode() : 0)) * 31;
        TtsVoiceHelper.TtsConfigData ttsConfigData = this.ttsVoice;
        int hashCode3 = (hashCode2 + (ttsConfigData != null ? ttsConfigData.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode3 + i) * 31;
        boolean z2 = this.isPlaying;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        VoiceTask voiceTask = this.voiceTask;
        return i4 + (voiceTask != null ? voiceTask.hashCode() : 0);
    }

    public String toString() {
        return "SelectVoiceItem(voiceType=" + this.voiceType + ", voice=" + this.voice + ", ttsVoice=" + this.ttsVoice + ", isSelect=" + this.isSelect + ", isPlaying=" + this.isPlaying + ", voiceTask=" + this.voiceTask + ")";
    }

    public SelectVoiceItem(VoiceType voiceType, AppointVoice appointVoice, TtsVoiceHelper.TtsConfigData ttsConfigData, boolean z, boolean z2, VoiceTask voiceTask) {
        Intrinsics.checkParameterIsNotNull(voiceType, "voiceType");
        this.voiceType = voiceType;
        this.voice = appointVoice;
        this.ttsVoice = ttsConfigData;
        this.isSelect = z;
        this.isPlaying = z2;
        this.voiceTask = voiceTask;
    }

    public final VoiceType getVoiceType() {
        return this.voiceType;
    }

    public final void setVoiceType(VoiceType voiceType) {
        Intrinsics.checkParameterIsNotNull(voiceType, "<set-?>");
        this.voiceType = voiceType;
    }

    public /* synthetic */ SelectVoiceItem(VoiceType voiceType, AppointVoice appointVoice, TtsVoiceHelper.TtsConfigData ttsConfigData, boolean z, boolean z2, VoiceTask voiceTask, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(voiceType, (i & 2) != 0 ? (AppointVoice) null : appointVoice, (i & 4) != 0 ? (TtsVoiceHelper.TtsConfigData) null : ttsConfigData, z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? (VoiceTask) null : voiceTask);
    }

    public final AppointVoice getVoice() {
        return this.voice;
    }

    public final void setVoice(AppointVoice appointVoice) {
        this.voice = appointVoice;
    }

    public final TtsVoiceHelper.TtsConfigData getTtsVoice() {
        return this.ttsVoice;
    }

    public final void setTtsVoice(TtsVoiceHelper.TtsConfigData ttsConfigData) {
        this.ttsVoice = ttsConfigData;
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

    public final VoiceTask getVoiceTask() {
        return this.voiceTask;
    }

    public final void setVoiceTask(VoiceTask voiceTask) {
        this.voiceTask = voiceTask;
    }
}
