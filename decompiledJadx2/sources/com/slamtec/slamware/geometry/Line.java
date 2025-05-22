package com.slamtec.slamware.geometry;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Line {
    private PointF endPoint;
    private int segmentId;
    private PointF startPoint;

    public Line(int i, PointF pointF, PointF pointF2) {
        this.segmentId = i;
        this.startPoint = new PointF(pointF);
        this.endPoint = new PointF(pointF2);
    }

    public Line(int i, float f, float f2, float f3, float f4) {
        this.segmentId = i;
        this.startPoint = new PointF(f, f2);
        this.endPoint = new PointF(f3, f4);
    }

    public Line(Line line) {
        this.segmentId = line.segmentId;
        this.startPoint = new PointF(line.startPoint);
        this.endPoint = new PointF(line.endPoint);
    }

    public Line(PointF pointF, PointF pointF2) {
        this.startPoint = pointF;
        this.endPoint = pointF2;
    }

    public PointF getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(PointF pointF) {
        this.startPoint = new PointF(pointF);
    }

    public PointF getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(PointF pointF) {
        this.endPoint = new PointF(pointF);
    }

    public float getStartX() {
        return getStartPoint().getX();
    }

    public float getStartY() {
        return getStartPoint().getY();
    }

    public float getEndX() {
        return getEndPoint().getX();
    }

    public float getEndY() {
        return getEndPoint().getY();
    }

    public int getSegmentId() {
        return this.segmentId;
    }

    public void setSegmentId(int i) {
        this.segmentId = i;
    }
}
