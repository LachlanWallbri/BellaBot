package com.pudutech.bumblebee.presenter.robot_voices;

import com.pudutech.resources.voice.VoiceItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Property.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceProperty;", "Lcom/pudutech/bumblebee/presenter/robot_voices/Property;", "timeLag", "", "item", "Lcom/pudutech/resources/voice/VoiceItem;", "appoint", "", "(JLcom/pudutech/resources/voice/VoiceItem;I)V", "getAppoint", "()I", "getItem", "()Lcom/pudutech/resources/voice/VoiceItem;", "getTimeLag", "()J", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class VoiceProperty extends Property {
    private final int appoint;
    private final VoiceItem item;
    private final long timeLag;

    public static /* synthetic */ VoiceProperty copy$default(VoiceProperty voiceProperty, long j, VoiceItem voiceItem, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = voiceProperty.timeLag;
        }
        if ((i2 & 2) != 0) {
            voiceItem = voiceProperty.item;
        }
        if ((i2 & 4) != 0) {
            i = voiceProperty.appoint;
        }
        return voiceProperty.copy(j, voiceItem, i);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimeLag() {
        return this.timeLag;
    }

    /* renamed from: component2, reason: from getter */
    public final VoiceItem getItem() {
        return this.item;
    }

    /* renamed from: component3, reason: from getter */
    public final int getAppoint() {
        return this.appoint;
    }

    public final VoiceProperty copy(long timeLag, VoiceItem item, int appoint) {
        return new VoiceProperty(timeLag, item, appoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoiceProperty)) {
            return false;
        }
        VoiceProperty voiceProperty = (VoiceProperty) other;
        return this.timeLag == voiceProperty.timeLag && Intrinsics.areEqual(this.item, voiceProperty.item) && this.appoint == voiceProperty.appoint;
    }

    public int hashCode() {
        long j = this.timeLag;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        VoiceItem voiceItem = this.item;
        return ((i + (voiceItem != null ? voiceItem.hashCode() : 0)) * 31) + this.appoint;
    }

    public String toString() {
        return "VoiceProperty(timeLag=" + this.timeLag + ", item=" + this.item + ", appoint=" + this.appoint + ")";
    }

    public VoiceProperty(long j, VoiceItem voiceItem, int i) {
        super(j, null);
        this.timeLag = j;
        this.item = voiceItem;
        this.appoint = i;
    }

    public final int getAppoint() {
        return this.appoint;
    }

    public final VoiceItem getItem() {
        return this.item;
    }

    public final long getTimeLag() {
        return this.timeLag;
    }
}
