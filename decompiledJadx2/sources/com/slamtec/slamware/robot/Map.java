package com.slamtec.slamware.robot;

import android.graphics.RectF;
import com.slamtec.slamware.geometry.PointF;
import com.slamtec.slamware.geometry.Size;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Map {
    private byte[] data;
    private Size dimension;
    private PointF origin;
    private PointF resolution;
    private long timestamp;

    public Map(PointF pointF, Size size, PointF pointF2, long j, byte[] bArr) {
        this.origin = new PointF(pointF);
        this.dimension = new Size(size.getWidth(), size.getHeight());
        this.resolution = new PointF(pointF2);
        this.timestamp = j;
        this.data = Arrays.copyOf(bArr, bArr.length);
    }

    public PointF getOrigin() {
        return this.origin;
    }

    public void setOrigin(PointF pointF) {
        this.origin = new PointF(pointF);
    }

    public Size getDimension() {
        return this.dimension;
    }

    public void setDimension(Size size) {
        this.dimension = new Size(size);
    }

    public PointF getResolution() {
        return this.resolution;
    }

    public void setResolution(PointF pointF) {
        this.resolution = new PointF(pointF);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public RectF getMapArea() {
        return new RectF(this.origin.getX(), this.origin.getY(), (this.dimension.getWidth() * this.resolution.getX()) + this.origin.getX(), (this.dimension.getHeight() * this.resolution.getY()) + this.origin.getY());
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = Arrays.copyOf(bArr, bArr.length);
    }
}
