package com.pudutech.robot.peripherals.config;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightBeltAnimationFrame.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/config/LightBeltAnimationFrame;", "", "head", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;", "end", TypedValues.Transition.S_DURATION, "", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;I)V", "getDuration", "()I", "setDuration", "(I)V", "getEnd", "()Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;", "setEnd", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;)V", "getHead", "setHead", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class LightBeltAnimationFrame {
    private int duration;
    private LightBeltAnimationColor end;
    private LightBeltAnimationColor head;

    public static /* synthetic */ LightBeltAnimationFrame copy$default(LightBeltAnimationFrame lightBeltAnimationFrame, LightBeltAnimationColor lightBeltAnimationColor, LightBeltAnimationColor lightBeltAnimationColor2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            lightBeltAnimationColor = lightBeltAnimationFrame.head;
        }
        if ((i2 & 2) != 0) {
            lightBeltAnimationColor2 = lightBeltAnimationFrame.end;
        }
        if ((i2 & 4) != 0) {
            i = lightBeltAnimationFrame.duration;
        }
        return lightBeltAnimationFrame.copy(lightBeltAnimationColor, lightBeltAnimationColor2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final LightBeltAnimationColor getHead() {
        return this.head;
    }

    /* renamed from: component2, reason: from getter */
    public final LightBeltAnimationColor getEnd() {
        return this.end;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    public final LightBeltAnimationFrame copy(LightBeltAnimationColor head, LightBeltAnimationColor end, int duration) {
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(end, "end");
        return new LightBeltAnimationFrame(head, end, duration);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightBeltAnimationFrame)) {
            return false;
        }
        LightBeltAnimationFrame lightBeltAnimationFrame = (LightBeltAnimationFrame) other;
        return Intrinsics.areEqual(this.head, lightBeltAnimationFrame.head) && Intrinsics.areEqual(this.end, lightBeltAnimationFrame.end) && this.duration == lightBeltAnimationFrame.duration;
    }

    public int hashCode() {
        LightBeltAnimationColor lightBeltAnimationColor = this.head;
        int hashCode = (lightBeltAnimationColor != null ? lightBeltAnimationColor.hashCode() : 0) * 31;
        LightBeltAnimationColor lightBeltAnimationColor2 = this.end;
        return ((hashCode + (lightBeltAnimationColor2 != null ? lightBeltAnimationColor2.hashCode() : 0)) * 31) + Integer.hashCode(this.duration);
    }

    public LightBeltAnimationFrame(LightBeltAnimationColor head, LightBeltAnimationColor end, int i) {
        Intrinsics.checkParameterIsNotNull(head, "head");
        Intrinsics.checkParameterIsNotNull(end, "end");
        this.head = head;
        this.end = end;
        this.duration = i;
    }

    public final LightBeltAnimationColor getHead() {
        return this.head;
    }

    public final void setHead(LightBeltAnimationColor lightBeltAnimationColor) {
        Intrinsics.checkParameterIsNotNull(lightBeltAnimationColor, "<set-?>");
        this.head = lightBeltAnimationColor;
    }

    public final LightBeltAnimationColor getEnd() {
        return this.end;
    }

    public final void setEnd(LightBeltAnimationColor lightBeltAnimationColor) {
        Intrinsics.checkParameterIsNotNull(lightBeltAnimationColor, "<set-?>");
        this.end = lightBeltAnimationColor;
    }

    public /* synthetic */ LightBeltAnimationFrame(LightBeltAnimationColor lightBeltAnimationColor, LightBeltAnimationColor lightBeltAnimationColor2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(lightBeltAnimationColor, lightBeltAnimationColor2, (i2 & 4) != 0 ? 0 : i);
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public String toString() {
        return "LightBeltAnimationFrame(head=" + this.head + ", end=" + this.end + ", duration=" + this.duration + ')';
    }
}
