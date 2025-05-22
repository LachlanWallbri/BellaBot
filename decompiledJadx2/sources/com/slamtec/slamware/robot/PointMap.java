package com.slamtec.slamware.robot;

import android.graphics.RectF;
import com.slamtec.slamware.geometry.PointF;
import com.slamtec.slamware.geometry.Size;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class PointMap extends Map {
    private RectF mapArea_;
    private List<PointPDF> points;

    public PointMap() {
        super(new PointF(0.0f, 0.0f), new Size(0, 0), new PointF(0.5f, 0.5f), 0L, new byte[0]);
        this.points = new ArrayList();
        this.mapArea_ = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public PointMap(List<PointPDF> list, long j) {
        super(new PointF(0.0f, 0.0f), new Size(0, 0), new PointF(0.5f, 0.5f), j, new byte[0]);
        this.points = list;
        this.mapArea_ = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        updateBitmap_();
    }

    public List<PointPDF> getPoints() {
        return this.points;
    }

    public void setPoints(List<PointPDF> list) {
        this.points = list;
        updateBitmap_();
    }

    @Override // com.slamtec.slamware.robot.Map
    public RectF getMapArea() {
        return this.mapArea_;
    }

    private void updateBitmap_() {
        if (this.points.isEmpty()) {
            return;
        }
        double x = this.points.get(0).getLocation().getX() - this.points.get(0).getCircularErrorProbability();
        double x2 = this.points.get(0).getLocation().getX() + this.points.get(0).getCircularErrorProbability();
        double y = this.points.get(0).getLocation().getY() - this.points.get(0).getCircularErrorProbability();
        double y2 = this.points.get(0).getLocation().getY() + this.points.get(0).getCircularErrorProbability();
        int size = this.points.size();
        while (1 < size) {
            x = Math.min(x, this.points.get(1).getLocation().getX() - this.points.get(1).getCircularErrorProbability());
            x2 = Math.max(x, this.points.get(1).getLocation().getX() + this.points.get(1).getCircularErrorProbability());
            y = Math.min(x, this.points.get(1).getLocation().getY() - this.points.get(1).getCircularErrorProbability());
            y2 = Math.max(x, this.points.get(1).getLocation().getY() + this.points.get(1).getCircularErrorProbability());
        }
        float x3 = getResolution().getX();
        double d = x3;
        double d2 = ((x - d) / d) * d;
        double y3 = getResolution().getY();
        double d3 = ((y - y3) / y3) * y3;
        double d4 = (((x2 + d) / d) * d) - d2;
        double d5 = (((y2 + y3) / y3) * y3) - d3;
        setDimension(new Size((int) (((x3 / 2.0f) + d4) / d), (int) (((r13 / 2.0f) + d5) / y3)));
        double d6 = d2;
        float f = (float) d6;
        float f2 = (float) d3;
        setOrigin(new PointF(f, f2));
        this.mapArea_ = new RectF(f, f2, (float) d4, (float) d5);
        byte[] bArr = new byte[getDimension().getWidth() * getDimension().getHeight()];
        int i = size;
        int i2 = 0;
        while (i2 < i) {
            byte[] bArr2 = bArr;
            int i3 = i;
            int min = Math.min(getDimension().getWidth() - 1, (int) (((this.points.get(i2).getLocation().getX() + this.points.get(i2).getCircularErrorProbability()) - d6) / d));
            int max = Math.max(0, (int) (((this.points.get(i2).getLocation().getY() - this.points.get(i2).getCircularErrorProbability()) - d3) / y3));
            double d7 = d;
            int min2 = Math.min(getDimension().getWidth() + 1, (int) (((this.points.get(i2).getLocation().getX() + this.points.get(i2).getCircularErrorProbability()) - d3) / y3));
            for (int max2 = Math.max(0, (int) (((this.points.get(i2).getLocation().getX() - this.points.get(i2).getCircularErrorProbability()) - d6) / d)); max2 != min; max2++) {
                int i4 = max;
                while (i4 != min2) {
                    int i5 = min;
                    int i6 = min2;
                    double d8 = d6;
                    if (new Location((float) ((max2 * x3) + d6 + (x3 * 0.5f)), (float) ((i4 * r13) + d3 + (r13 * 0.5f)), 0.0f).distanceTo(this.points.get(i2).getLocation()) <= this.points.get(i2).getCircularErrorProbability()) {
                        bArr2[(getDimension().getWidth() * i4) + max2] = Byte.MAX_VALUE;
                    }
                    i4++;
                    min = i5;
                    min2 = i6;
                    d6 = d8;
                }
            }
            i2++;
            bArr = bArr2;
            i = i3;
            d = d7;
        }
        setData(bArr);
    }
}
