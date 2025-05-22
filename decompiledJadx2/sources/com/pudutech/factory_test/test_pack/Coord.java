package com.pudutech.factory_test.test_pack;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Coord.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0003J\u001e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/Coord;", "", "x", "", "y", "(FF)V", "getX", "()F", "getY", "checkInCircle", "", "point", "raduis", "checkInLineRange", "begin", "end", "width", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* data */ class Coord {
    private final float x;
    private final float y;

    public static /* synthetic */ Coord copy$default(Coord coord, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = coord.x;
        }
        if ((i & 2) != 0) {
            f2 = coord.y;
        }
        return coord.copy(f, f2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final float getY() {
        return this.y;
    }

    public final Coord copy(float x, float y) {
        return new Coord(x, y);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coord)) {
            return false;
        }
        Coord coord = (Coord) other;
        return Float.compare(this.x, coord.x) == 0 && Float.compare(this.y, coord.y) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public String toString() {
        return "Coord(x=" + this.x + ", y=" + this.y + ")";
    }

    public Coord(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final boolean checkInCircle(Coord point, float raduis) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        float f = point.x;
        float f2 = this.x;
        float f3 = (f - f2) * (f - f2);
        float f4 = point.y;
        float f5 = this.y;
        return ((float) Math.sqrt((double) (f3 + ((f4 - f5) * (f4 - f5))))) < raduis;
    }

    public final boolean checkInLineRange(Coord begin, Coord end, float width) {
        Intrinsics.checkParameterIsNotNull(begin, "begin");
        Intrinsics.checkParameterIsNotNull(end, "end");
        float f = end.y;
        float f2 = begin.y;
        float f3 = f - f2;
        float f4 = begin.x;
        float f5 = end.x;
        float f6 = f4 - f5;
        float f7 = (f5 * f2) - (f4 * f);
        if (f3 == 0.0f && f6 == 0.0f) {
            return false;
        }
        float f8 = f3 * f3;
        float f9 = f6 * f6;
        float f10 = f8 + f9;
        float f11 = this.x;
        float f12 = f3 * f6;
        float f13 = this.y;
        float f14 = (((f9 * f11) - (f12 * f13)) - (f3 * f7)) / f10;
        float f15 = (((f8 * f13) - (f12 * f11)) - (f6 * f7)) / f10;
        return ((float) Math.sqrt((double) (((f11 - f14) * (f11 - f14)) + ((f13 - f15) * (f13 - f15))))) < width;
    }
}
