package com.pudutech.bumblebee.presenter.robot_voices;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Property.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices/TtsVoiceProperty;", "Lcom/pudutech/bumblebee/presenter/robot_voices/Property;", "timeLag", "", "ttsPath", "", "(JLjava/lang/String;)V", "getTimeLag", "()J", "getTtsPath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class TtsVoiceProperty extends Property {
    private final long timeLag;
    private final String ttsPath;

    public static /* synthetic */ TtsVoiceProperty copy$default(TtsVoiceProperty ttsVoiceProperty, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = ttsVoiceProperty.timeLag;
        }
        if ((i & 2) != 0) {
            str = ttsVoiceProperty.ttsPath;
        }
        return ttsVoiceProperty.copy(j, str);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimeLag() {
        return this.timeLag;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTtsPath() {
        return this.ttsPath;
    }

    public final TtsVoiceProperty copy(long timeLag, String ttsPath) {
        return new TtsVoiceProperty(timeLag, ttsPath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsVoiceProperty)) {
            return false;
        }
        TtsVoiceProperty ttsVoiceProperty = (TtsVoiceProperty) other;
        return this.timeLag == ttsVoiceProperty.timeLag && Intrinsics.areEqual(this.ttsPath, ttsVoiceProperty.ttsPath);
    }

    public int hashCode() {
        long j = this.timeLag;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.ttsPath;
        return i + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "TtsVoiceProperty(timeLag=" + this.timeLag + ", ttsPath=" + this.ttsPath + ")";
    }

    public TtsVoiceProperty(long j, String str) {
        super(j, null);
        this.timeLag = j;
        this.ttsPath = str;
    }

    public final long getTimeLag() {
        return this.timeLag;
    }

    public final String getTtsPath() {
        return this.ttsPath;
    }
}
