package com.pudutech.mirsdkwrap.lib.move.bean;

import com.pudutech.mirsdkwrap.lib.enums.ErrorType;
import com.pudutech.robot.module.report.track2.TrackKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020!J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveError;", "", TrackKey.ERROR_TYPE, "", "level", "detail", "id", "time", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getDetail", "()Ljava/lang/String;", "getError_type", "getId", "setId", "(Ljava/lang/String;)V", "getLevel", "getTime", "()J", "setTime", "(J)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "genId", "", "getErrorType", "Lcom/pudutech/mirsdkwrap/lib/enums/ErrorType;", "hashCode", "", "toString", "Companion", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveError {
    public static final String LEVEL_ERROR = "Error";
    public static final String LEVEL_EVENT = "Event";
    public static final String LEVEL_FATAL = "Fatal";
    public static final String LEVEL_WARNING = "WARNING";
    private final String detail;
    private final String error_type;
    private String id;
    private final String level;
    private long time;

    public MoveError() {
        this(null, null, null, null, 0L, 31, null);
    }

    public static /* synthetic */ MoveError copy$default(MoveError moveError, String str, String str2, String str3, String str4, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = moveError.error_type;
        }
        if ((i & 2) != 0) {
            str2 = moveError.level;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = moveError.detail;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = moveError.id;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            j = moveError.time;
        }
        return moveError.copy(str, str5, str6, str7, j);
    }

    /* renamed from: component1, reason: from getter */
    public final String getError_type() {
        return this.error_type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    /* renamed from: component4, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    public final MoveError copy(String error_type, String level, String detail, String id, long time) {
        Intrinsics.checkParameterIsNotNull(error_type, "error_type");
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new MoveError(error_type, level, detail, id, time);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoveError)) {
            return false;
        }
        MoveError moveError = (MoveError) other;
        return Intrinsics.areEqual(this.error_type, moveError.error_type) && Intrinsics.areEqual(this.level, moveError.level) && Intrinsics.areEqual(this.detail, moveError.detail) && Intrinsics.areEqual(this.id, moveError.id) && this.time == moveError.time;
    }

    public int hashCode() {
        String str = this.error_type;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.level;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.detail;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.id;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        long j = this.time;
        return hashCode4 + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "MoveError(error_type=" + this.error_type + ", level=" + this.level + ", detail=" + this.detail + ", id=" + this.id + ", time=" + this.time + ")";
    }

    public MoveError(String error_type, String level, String detail, String id, long j) {
        Intrinsics.checkParameterIsNotNull(error_type, "error_type");
        Intrinsics.checkParameterIsNotNull(level, "level");
        Intrinsics.checkParameterIsNotNull(detail, "detail");
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.error_type = error_type;
        this.level = level;
        this.detail = detail;
        this.id = id;
        this.time = j;
        this.time = System.currentTimeMillis();
    }

    public final String getError_type() {
        return this.error_type;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getDetail() {
        return this.detail;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public /* synthetic */ MoveError(String str, String str2, String str3, String str4, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) == 0 ? str4 : "", (i & 16) != 0 ? 0L : j);
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }

    public final void genId() {
        if (this.id.length() == 0) {
            this.id = String.valueOf(hashCode());
        }
    }

    public final ErrorType getErrorType() {
        try {
            return ErrorType.valueOf(this.error_type);
        } catch (Exception unused) {
            return ErrorType.NoDefine;
        }
    }
}
