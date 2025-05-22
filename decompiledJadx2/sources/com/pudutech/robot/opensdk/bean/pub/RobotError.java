package com.pudutech.robot.opensdk.bean.pub;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: PubRobotMoveStateData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/RobotError;", "", "errorType", "", "level", "detail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDetail", "()Ljava/lang/String;", "getErrorType", "getLevel", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class RobotError {
    private final String detail;
    private final String errorType;
    private final String level;

    public static /* synthetic */ RobotError copy$default(RobotError robotError, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = robotError.errorType;
        }
        if ((i & 2) != 0) {
            str2 = robotError.level;
        }
        if ((i & 4) != 0) {
            str3 = robotError.detail;
        }
        return robotError.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getErrorType() {
        return this.errorType;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    public final RobotError copy(String errorType, String level, String detail) {
        Intrinsics.checkParameterIsNotNull(errorType, "errorType");
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        return new RobotError(errorType, level, detail);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotError)) {
            return false;
        }
        RobotError robotError = (RobotError) other;
        return Intrinsics.areEqual(this.errorType, robotError.errorType) && Intrinsics.areEqual(this.level, robotError.level) && Intrinsics.areEqual(this.detail, robotError.detail);
    }

    public int hashCode() {
        String str = this.errorType;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.level;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.detail;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "RobotError(errorType=" + this.errorType + ", level=" + this.level + ", detail=" + this.detail + ")";
    }

    public RobotError(String errorType, String level, String detail) {
        Intrinsics.checkParameterIsNotNull(errorType, "errorType");
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        this.errorType = errorType;
        this.level = level;
        this.detail = detail;
    }

    public final String getDetail() {
        return this.detail;
    }

    public final String getErrorType() {
        return this.errorType;
    }

    public final String getLevel() {
        return this.level;
    }
}
