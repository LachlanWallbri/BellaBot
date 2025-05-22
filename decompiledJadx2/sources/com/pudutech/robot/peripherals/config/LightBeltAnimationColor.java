package com.pudutech.robot.peripherals.config;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightBeltAnimationColor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u0003H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/config/LightBeltAnimationColor;", "", "R", "", "G", "B", "sameAsLast", "", "(IIIZ)V", "getB", "()I", "setB", "(I)V", "getG", "setG", "getR", "setR", "getSameAsLast", "()Z", "setSameAsLast", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class LightBeltAnimationColor {
    private int B;
    private int G;
    private int R;
    private boolean sameAsLast;

    public LightBeltAnimationColor() {
        this(0, 0, 0, false, 15, null);
    }

    public static /* synthetic */ LightBeltAnimationColor copy$default(LightBeltAnimationColor lightBeltAnimationColor, int i, int i2, int i3, boolean z, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = lightBeltAnimationColor.R;
        }
        if ((i4 & 2) != 0) {
            i2 = lightBeltAnimationColor.G;
        }
        if ((i4 & 4) != 0) {
            i3 = lightBeltAnimationColor.B;
        }
        if ((i4 & 8) != 0) {
            z = lightBeltAnimationColor.sameAsLast;
        }
        return lightBeltAnimationColor.copy(i, i2, i3, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getR() {
        return this.R;
    }

    /* renamed from: component2, reason: from getter */
    public final int getG() {
        return this.G;
    }

    /* renamed from: component3, reason: from getter */
    public final int getB() {
        return this.B;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getSameAsLast() {
        return this.sameAsLast;
    }

    public final LightBeltAnimationColor copy(int R, int G, int B, boolean sameAsLast) {
        return new LightBeltAnimationColor(R, G, B, sameAsLast);
    }

    public LightBeltAnimationColor(int i, int i2, int i3, boolean z) {
        this.R = i;
        this.G = i2;
        this.B = i3;
        this.sameAsLast = z;
    }

    public /* synthetic */ LightBeltAnimationColor(int i, int i2, int i3, boolean z, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? false : z);
    }

    public final int getB() {
        return this.B;
    }

    public final int getG() {
        return this.G;
    }

    public final int getR() {
        return this.R;
    }

    public final boolean getSameAsLast() {
        return this.sameAsLast;
    }

    public final void setB(int i) {
        this.B = i;
    }

    public final void setG(int i) {
        this.G = i;
    }

    public final void setR(int i) {
        this.R = i;
    }

    public final void setSameAsLast(boolean z) {
        this.sameAsLast = z;
    }

    public String toString() {
        return "LightBeltAnimationColor(R=" + this.R + ", G=" + this.G + ", B=" + this.B + ", sameAsLast=" + this.sameAsLast + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            LightBeltAnimationColor lightBeltAnimationColor = (LightBeltAnimationColor) other;
            return this.R == lightBeltAnimationColor.R && this.G == lightBeltAnimationColor.G && this.B == lightBeltAnimationColor.B;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.peripherals.config.LightBeltAnimationColor");
    }

    public int hashCode() {
        return (((this.R * 31) + this.G) * 31) + this.B;
    }
}
