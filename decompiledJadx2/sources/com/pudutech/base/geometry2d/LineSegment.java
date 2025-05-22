package com.pudutech.base.geometry2d;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class LineSegment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    Point begin;
    Point end;

    public LineSegment(Point point, Point point2) {
        this.begin = new Point(0.0d, 0.0d);
        this.end = new Point(0.0d, 0.0d);
        this.begin = point;
        this.end = point2;
    }

    public void switchBeginAndEnd() {
        Point point = this.begin;
        this.begin = this.end;
        this.end = point;
    }
}
