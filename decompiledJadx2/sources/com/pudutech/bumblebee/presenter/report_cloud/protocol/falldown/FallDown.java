package com.pudutech.bumblebee.presenter.report_cloud.protocol.falldown;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FallDown.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/falldown/FallDown;", "", "level", "", "fall_down_type", "", "(Ljava/lang/String;I)V", "getFall_down_type", "()I", "getLevel", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class FallDown {
    private final int fall_down_type;
    private final String level;

    public static /* synthetic */ FallDown copy$default(FallDown fallDown, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = fallDown.level;
        }
        if ((i2 & 2) != 0) {
            i = fallDown.fall_down_type;
        }
        return fallDown.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    /* renamed from: component2, reason: from getter */
    public final int getFall_down_type() {
        return this.fall_down_type;
    }

    public final FallDown copy(String level, int fall_down_type) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        return new FallDown(level, fall_down_type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FallDown)) {
            return false;
        }
        FallDown fallDown = (FallDown) other;
        return Intrinsics.areEqual(this.level, fallDown.level) && this.fall_down_type == fallDown.fall_down_type;
    }

    public int hashCode() {
        String str = this.level;
        return ((str != null ? str.hashCode() : 0) * 31) + this.fall_down_type;
    }

    public String toString() {
        return "FallDown(level=" + this.level + ", fall_down_type=" + this.fall_down_type + ")";
    }

    public FallDown(String level, int i) {
        Intrinsics.checkParameterIsNotNull(level, "level");
        this.level = level;
        this.fall_down_type = i;
    }

    public final int getFall_down_type() {
        return this.fall_down_type;
    }

    public final String getLevel() {
        return this.level;
    }
}
