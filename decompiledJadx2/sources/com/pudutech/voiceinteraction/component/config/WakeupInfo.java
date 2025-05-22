package com.pudutech.voiceinteraction.component.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WakeupInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J:\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "", "angle", "", "beam", "wakeUpWord", "", "score", "(IILjava/lang/String;Ljava/lang/Integer;)V", "getAngle", "()I", "setAngle", "(I)V", "getBeam", "setBeam", "getScore", "()Ljava/lang/Integer;", "setScore", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getWakeUpWord", "()Ljava/lang/String;", "setWakeUpWord", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "copy", "(IILjava/lang/String;Ljava/lang/Integer;)Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "equals", "", "other", "hashCode", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class WakeupInfo {
    private int angle;
    private int beam;
    private Integer score;
    private String wakeUpWord;

    public static /* synthetic */ WakeupInfo copy$default(WakeupInfo wakeupInfo, int i, int i2, String str, Integer num, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = wakeupInfo.angle;
        }
        if ((i3 & 2) != 0) {
            i2 = wakeupInfo.beam;
        }
        if ((i3 & 4) != 0) {
            str = wakeupInfo.wakeUpWord;
        }
        if ((i3 & 8) != 0) {
            num = wakeupInfo.score;
        }
        return wakeupInfo.copy(i, i2, str, num);
    }

    /* renamed from: component1, reason: from getter */
    public final int getAngle() {
        return this.angle;
    }

    /* renamed from: component2, reason: from getter */
    public final int getBeam() {
        return this.beam;
    }

    /* renamed from: component3, reason: from getter */
    public final String getWakeUpWord() {
        return this.wakeUpWord;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getScore() {
        return this.score;
    }

    public final WakeupInfo copy(int angle, int beam, String wakeUpWord, Integer score) {
        return new WakeupInfo(angle, beam, wakeUpWord, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WakeupInfo)) {
            return false;
        }
        WakeupInfo wakeupInfo = (WakeupInfo) other;
        return this.angle == wakeupInfo.angle && this.beam == wakeupInfo.beam && Intrinsics.areEqual(this.wakeUpWord, wakeupInfo.wakeUpWord) && Intrinsics.areEqual(this.score, wakeupInfo.score);
    }

    public int hashCode() {
        int i = ((this.angle * 31) + this.beam) * 31;
        String str = this.wakeUpWord;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        Integer num = this.score;
        return hashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "WakeupInfo(angle=" + this.angle + ", beam=" + this.beam + ", wakeUpWord=" + this.wakeUpWord + ", score=" + this.score + ")";
    }

    public WakeupInfo(int i, int i2, String str, Integer num) {
        this.angle = i;
        this.beam = i2;
        this.wakeUpWord = str;
        this.score = num;
    }

    public final int getAngle() {
        return this.angle;
    }

    public final void setAngle(int i) {
        this.angle = i;
    }

    public final int getBeam() {
        return this.beam;
    }

    public final void setBeam(int i) {
        this.beam = i;
    }

    public final String getWakeUpWord() {
        return this.wakeUpWord;
    }

    public final void setWakeUpWord(String str) {
        this.wakeUpWord = str;
    }

    public /* synthetic */ WakeupInfo(int i, int i2, String str, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? 0 : num);
    }

    public final Integer getScore() {
        return this.score;
    }

    public final void setScore(Integer num) {
        this.score = num;
    }
}
