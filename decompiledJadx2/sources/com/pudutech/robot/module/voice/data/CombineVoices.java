package com.pudutech.robot.module.voice.data;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePlayTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B3\b\u0016\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\nJ\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005J\u0006\u0010\u0015\u001a\u00020\u000eJ\b\u0010\u0016\u001a\u00020\u000eH\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/data/CombineVoices;", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "()V", "voices", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/data/VoiceSubItem;", "Lkotlin/collections/ArrayList;", "delayPlay", "", "playCount", "", "(Ljava/util/ArrayList;JI)V", "index", "equals", "", "other", "", "finishOne", "", "getIndex", "getNextPlayVoiceItem", "hasNext", "isFirstVoice", "toString", "", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CombineVoices extends BaseVoice {
    private int index;
    private ArrayList<VoiceSubItem> voices;

    public CombineVoices() {
        this.index = -1;
    }

    public /* synthetic */ CombineVoices(ArrayList arrayList, long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 1 : i);
    }

    public CombineVoices(ArrayList<VoiceSubItem> voices, long j, int i) {
        Intrinsics.checkParameterIsNotNull(voices, "voices");
        this.index = -1;
        this.voices = voices;
        setDelayPlay(j);
        setPlayCount(i);
    }

    public final VoiceSubItem getNextPlayVoiceItem() {
        ArrayList<VoiceSubItem> arrayList = this.voices;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        this.index++;
        int i = this.index;
        ArrayList<VoiceSubItem> arrayList2 = this.voices;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        if (i >= arrayList2.size()) {
            return null;
        }
        ArrayList<VoiceSubItem> arrayList3 = this.voices;
        if (arrayList3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        return arrayList3.get(this.index);
    }

    public final boolean hasNext() {
        int i = this.index;
        ArrayList<VoiceSubItem> arrayList = this.voices;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        return i < arrayList.size() - 1;
    }

    public final void finishOne() {
        this.index = -1;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override // com.pudutech.robot.module.voice.data.BaseVoice
    public boolean isFirstVoice() {
        return this.index == -1;
    }

    @Override // com.pudutech.robot.module.voice.data.BaseVoice
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CombineVoices(index=");
        sb.append(this.index);
        sb.append(", voices=");
        ArrayList<VoiceSubItem> arrayList = this.voices;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        sb.append(arrayList);
        sb.append(", ");
        sb.append(super.toString());
        sb.append(')');
        return sb.toString();
    }

    public boolean equals(Object other) {
        if ((other != null ? other.hashCode() : 0) == hashCode()) {
            return true;
        }
        if (!(other instanceof CombineVoices)) {
            return false;
        }
        CombineVoices combineVoices = (CombineVoices) other;
        if (combineVoices.getDelayPlay() != getDelayPlay() || combineVoices.getPlayCount() != getPlayCount()) {
            return false;
        }
        ArrayList<VoiceSubItem> arrayList = combineVoices.voices;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        int size = arrayList.size();
        ArrayList<VoiceSubItem> arrayList2 = this.voices;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        if (size != arrayList2.size()) {
            return false;
        }
        ArrayList<VoiceSubItem> arrayList3 = combineVoices.voices;
        if (arrayList3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("voices");
        }
        int i = 0;
        for (Object obj : arrayList3) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            VoiceSubItem voiceSubItem = (VoiceSubItem) obj;
            if (this.voices == null) {
                Intrinsics.throwUninitializedPropertyAccessException("voices");
            }
            if (!Intrinsics.areEqual(voiceSubItem, r6.get(i))) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
