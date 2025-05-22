package com.pudutech.peanut.robot_ui.ttsvoices.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: TtsDownInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "", "left", "", "all", "code", "(III)V", "getAll", "()I", "setAll", "(I)V", "getCode", "setCode", "getLeft", "setLeft", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TtsDownInfo {
    private int all;
    private int code;
    private int left;

    public static /* synthetic */ TtsDownInfo copy$default(TtsDownInfo ttsDownInfo, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = ttsDownInfo.left;
        }
        if ((i4 & 2) != 0) {
            i2 = ttsDownInfo.all;
        }
        if ((i4 & 4) != 0) {
            i3 = ttsDownInfo.code;
        }
        return ttsDownInfo.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final int getAll() {
        return this.all;
    }

    /* renamed from: component3, reason: from getter */
    public final int getCode() {
        return this.code;
    }

    public final TtsDownInfo copy(int left, int all, int code) {
        return new TtsDownInfo(left, all, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TtsDownInfo)) {
            return false;
        }
        TtsDownInfo ttsDownInfo = (TtsDownInfo) other;
        return this.left == ttsDownInfo.left && this.all == ttsDownInfo.all && this.code == ttsDownInfo.code;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.left) * 31) + Integer.hashCode(this.all)) * 31) + Integer.hashCode(this.code);
    }

    public String toString() {
        return "TtsDownInfo(left=" + this.left + ", all=" + this.all + ", code=" + this.code + ")";
    }

    public TtsDownInfo(int i, int i2, int i3) {
        this.left = i;
        this.all = i2;
        this.code = i3;
    }

    public final int getLeft() {
        return this.left;
    }

    public final void setLeft(int i) {
        this.left = i;
    }

    public final int getAll() {
        return this.all;
    }

    public final void setAll(int i) {
        this.all = i;
    }

    public /* synthetic */ TtsDownInfo(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i) {
        this.code = i;
    }
}
