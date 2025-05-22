package com.pudutech.base.geometry2d;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class RelationshipPointToLineSegment {
    private double closestDistance;
    private Point closestPoint;

    public RelationshipPointToLineSegment(Point point, LineSegment lineSegment) {
        this.closestDistance = 0.0d;
        Point point2 = lineSegment.begin;
        Point point3 = lineSegment.end;
        this.closestPoint = point2;
        this.closestDistance = point.distanceToPoint(point2);
        double d = point3.f4497y - point2.f4497y;
        double d2 = point2.f4496x - point3.f4496x;
        double d3 = (point3.f4496x * point2.f4497y) - (point2.f4496x * point3.f4497y);
        if (d == 0.0d && d2 == 0.0d) {
            return;
        }
        double d4 = d * d;
        double d5 = d2 * d2;
        double d6 = d4 + d5;
        double d7 = d * d2;
        this.closestPoint.f4496x = (((d5 * point.f4496x) - (point.f4497y * d7)) - (d * d3)) / d6;
        this.closestPoint.f4497y = (((point.f4497y * d4) - (d7 * point.f4496x)) - (d2 * d3)) / d6;
        this.closestDistance = point.distanceToPoint(this.closestPoint);
        if (this.closestPoint.distanceToPoint(point2) > point3.distanceToPoint(point2) || this.closestPoint.distanceToPoint(point3) > point3.distanceToPoint(point2)) {
            this.closestPoint = point2;
            this.closestDistance = point.distanceToPoint(point2);
            if (point.distanceToPoint(point3) < this.closestDistance) {
                this.closestPoint = point3;
                this.closestDistance = point.distanceToPoint(point3);
            }
        }
    }

    public Point getClosestPoint() {
        return this.closestPoint;
    }

    public double getClosestDistance() {
        return this.closestDistance;
    }
}
