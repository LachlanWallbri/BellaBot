package com.pudutech.lidar.eait05;

import kotlin.Metadata;

/* compiled from: Point.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001f\u0010\f\u001a\u00020\rX\u0086\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/lidar/eait05/Point;", "", "()V", "angleDegree", "", "getAngleDegree", "()D", "setAngleDegree", "(D)V", "distanceMm", "getDistanceMm", "setDistanceMm", "intensity", "Lkotlin/UByte;", "getIntensity", "()B", "setIntensity-7apg3OU", "(B)V", "B", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Point {
    private double angleDegree;
    private double distanceMm;
    private byte intensity;

    public final byte getIntensity() {
        return this.intensity;
    }

    /* renamed from: setIntensity-7apg3OU, reason: not valid java name */
    public final void m4364setIntensity7apg3OU(byte b) {
        this.intensity = b;
    }

    public final double getAngleDegree() {
        return this.angleDegree;
    }

    public final void setAngleDegree(double d) {
        this.angleDegree = d;
    }

    public final double getDistanceMm() {
        return this.distanceMm;
    }

    public final void setDistanceMm(double d) {
        this.distanceMm = d;
    }
}
