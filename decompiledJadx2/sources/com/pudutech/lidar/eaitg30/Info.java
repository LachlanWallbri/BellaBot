package com.pudutech.lidar.eaitg30;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* compiled from: Tg30Filter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0006\"\u0004\b\u0010\u0010\bR\u001a\u0010\u0011\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lidar/eaitg30/Info;", "", "()V", "diff", "", "getDiff", "()D", "setDiff", "(D)V", "isValid", "", "()Z", "setValid", "(Z)V", "maxOffsetDistance", "getMaxOffsetDistance", "setMaxOffsetDistance", TypedValues.Cycle.S_WAVE_OFFSET, "getOffset", "setOffset", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Info {
    private double diff;
    private boolean isValid;
    private double maxOffsetDistance;
    private double offset;

    /* renamed from: isValid, reason: from getter */
    public final boolean getIsValid() {
        return this.isValid;
    }

    public final void setValid(boolean z) {
        this.isValid = z;
    }

    public final double getOffset() {
        return this.offset;
    }

    public final void setOffset(double d) {
        this.offset = d;
    }

    public final double getDiff() {
        return this.diff;
    }

    public final void setDiff(double d) {
        this.diff = d;
    }

    public final double getMaxOffsetDistance() {
        return this.maxOffsetDistance;
    }

    public final void setMaxOffsetDistance(double d) {
        this.maxOffsetDistance = d;
    }
}
