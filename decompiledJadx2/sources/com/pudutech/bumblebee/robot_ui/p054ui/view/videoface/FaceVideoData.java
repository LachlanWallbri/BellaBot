package com.pudutech.bumblebee.robot_ui.p054ui.view.videoface;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceVideoData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoData;", "", "path", "", "isLooper", "", "replayTimes", "", "(Ljava/lang/String;ZI)V", "()Z", "getPath", "()Ljava/lang/String;", "getReplayTimes", "()I", "setReplayTimes", "(I)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class FaceVideoData {
    private final boolean isLooper;
    private final String path;
    private int replayTimes;

    public static /* synthetic */ FaceVideoData copy$default(FaceVideoData faceVideoData, String str, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = faceVideoData.path;
        }
        if ((i2 & 2) != 0) {
            z = faceVideoData.isLooper;
        }
        if ((i2 & 4) != 0) {
            i = faceVideoData.replayTimes;
        }
        return faceVideoData.copy(str, z, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsLooper() {
        return this.isLooper;
    }

    /* renamed from: component3, reason: from getter */
    public final int getReplayTimes() {
        return this.replayTimes;
    }

    public final FaceVideoData copy(String path, boolean isLooper, int replayTimes) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new FaceVideoData(path, isLooper, replayTimes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceVideoData)) {
            return false;
        }
        FaceVideoData faceVideoData = (FaceVideoData) other;
        return Intrinsics.areEqual(this.path, faceVideoData.path) && this.isLooper == faceVideoData.isLooper && this.replayTimes == faceVideoData.replayTimes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.path;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isLooper;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((hashCode + i) * 31) + Integer.hashCode(this.replayTimes);
    }

    public String toString() {
        return "FaceVideoData(path=" + this.path + ", isLooper=" + this.isLooper + ", replayTimes=" + this.replayTimes + ")";
    }

    public FaceVideoData(String path, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.path = path;
        this.isLooper = z;
        this.replayTimes = i;
    }

    public /* synthetic */ FaceVideoData(String str, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i2 & 4) != 0 ? 0 : i);
    }

    public final String getPath() {
        return this.path;
    }

    public final int getReplayTimes() {
        return this.replayTimes;
    }

    public final boolean isLooper() {
        return this.isLooper;
    }

    public final void setReplayTimes(int i) {
        this.replayTimes = i;
    }
}
