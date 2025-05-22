package com.pudutech.robot.opensdk.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomCallContentBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/TtsTask;", "", "text", "", "playType", "", "intervalTime", "", "(Ljava/lang/String;IJ)V", "getIntervalTime", "()J", "setIntervalTime", "(J)V", "getPlayType", "()I", "setPlayType", "(I)V", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class TtsTask {
    private long intervalTime;
    private int playType;
    private String text;

    public static /* synthetic */ TtsTask copy$default(TtsTask ttsTask, String str, int i, long j, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = ttsTask.text;
        }
        if ((i2 & 2) != 0) {
            i = ttsTask.playType;
        }
        if ((i2 & 4) != 0) {
            j = ttsTask.intervalTime;
        }
        return ttsTask.copy(str, i, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component2, reason: from getter */
    public final int getPlayType() {
        return this.playType;
    }

    /* renamed from: component3, reason: from getter */
    public final long getIntervalTime() {
        return this.intervalTime;
    }

    public final TtsTask copy(String text, int playType, long intervalTime) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        return new TtsTask(text, playType, intervalTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsTask)) {
            return false;
        }
        TtsTask ttsTask = (TtsTask) other;
        return Intrinsics.areEqual(this.text, ttsTask.text) && this.playType == ttsTask.playType && this.intervalTime == ttsTask.intervalTime;
    }

    public int hashCode() {
        String str = this.text;
        return ((((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.playType)) * 31) + Long.hashCode(this.intervalTime);
    }

    public String toString() {
        return "TtsTask(text=" + this.text + ", playType=" + this.playType + ", intervalTime=" + this.intervalTime + ")";
    }

    public TtsTask(String text, int i, long j) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.text = text;
        this.playType = i;
        this.intervalTime = j;
    }

    public /* synthetic */ TtsTask(String str, int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? 0L : j);
    }

    public final long getIntervalTime() {
        return this.intervalTime;
    }

    public final int getPlayType() {
        return this.playType;
    }

    public final String getText() {
        return this.text;
    }

    public final void setIntervalTime(long j) {
        this.intervalTime = j;
    }

    public final void setPlayType(int i) {
        this.playType = i;
    }

    public final void setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }
}
