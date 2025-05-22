package com.pudutech.lidar.base;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LidarError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lidar/base/LidarError;", "", "type", "Lcom/pudutech/lidar/base/LidarErrorType;", "description", "", "(Lcom/pudutech/lidar/base/LidarErrorType;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getType", "()Lcom/pudutech/lidar/base/LidarErrorType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* data */ class LidarError {
    private final String description;
    private final LidarErrorType type;

    public static /* synthetic */ LidarError copy$default(LidarError lidarError, LidarErrorType lidarErrorType, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            lidarErrorType = lidarError.type;
        }
        if ((i & 2) != 0) {
            str = lidarError.description;
        }
        return lidarError.copy(lidarErrorType, str);
    }

    /* renamed from: component1, reason: from getter */
    public final LidarErrorType getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final LidarError copy(LidarErrorType type, String description) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new LidarError(type, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LidarError)) {
            return false;
        }
        LidarError lidarError = (LidarError) other;
        return Intrinsics.areEqual(this.type, lidarError.type) && Intrinsics.areEqual(this.description, lidarError.description);
    }

    public int hashCode() {
        LidarErrorType lidarErrorType = this.type;
        int hashCode = (lidarErrorType != null ? lidarErrorType.hashCode() : 0) * 31;
        String str = this.description;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LidarError(type=" + this.type + ", description=" + this.description + ")";
    }

    public LidarError(LidarErrorType type, String description) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(description, "description");
        this.type = type;
        this.description = description;
    }

    public final String getDescription() {
        return this.description;
    }

    public final LidarErrorType getType() {
        return this.type;
    }
}
