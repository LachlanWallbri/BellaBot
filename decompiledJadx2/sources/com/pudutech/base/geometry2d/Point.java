package com.pudutech.base.geometry2d;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class Point {

    /* renamed from: x */
    public double f4496x;

    /* renamed from: y */
    public double f4497y;

    public Point(double d, double d2) {
        this.f4496x = d;
        this.f4497y = d2;
    }

    public double distanceToPoint(Point point) {
        double d = this.f4496x - point.f4496x;
        double d2 = this.f4497y - point.f4497y;
        return Math.sqrt((d * d) + (d2 * d2));
    }
}
