package com.pudutech.peanut.robot_ui.p063ui.view.videoface;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FaceVideoData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoData;", "", "path", "", "isLooper", "", "(Ljava/lang/String;Z)V", "()Z", "getPath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FaceVideoData {
    private final boolean isLooper;
    private final String path;

    public static /* synthetic */ FaceVideoData copy$default(FaceVideoData faceVideoData, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = faceVideoData.path;
        }
        if ((i & 2) != 0) {
            z = faceVideoData.isLooper;
        }
        return faceVideoData.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsLooper() {
        return this.isLooper;
    }

    public final FaceVideoData copy(String path, boolean isLooper) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new FaceVideoData(path, isLooper);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceVideoData)) {
            return false;
        }
        FaceVideoData faceVideoData = (FaceVideoData) other;
        return Intrinsics.areEqual(this.path, faceVideoData.path) && this.isLooper == faceVideoData.isLooper;
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
        return hashCode + i;
    }

    public String toString() {
        return "FaceVideoData(path=" + this.path + ", isLooper=" + this.isLooper + ")";
    }

    public FaceVideoData(String path, boolean z) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.path = path;
        this.isLooper = z;
    }

    public final String getPath() {
        return this.path;
    }

    public final boolean isLooper() {
        return this.isLooper;
    }
}
