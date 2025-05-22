package com.slamtec.slamware.robot;

import com.slamtec.slamware.geometry.PointF;
import com.slamtec.slamware.geometry.Size;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class GridMap extends MapLayer {
    private Size dimension_;
    private byte[] mapData_;
    private Location origin_;
    private PointF resolution_;

    public Location getOrigin() {
        return this.origin_;
    }

    public void setOrigin(Location location) {
        this.origin_ = location;
    }

    public Size getDimension() {
        return this.dimension_;
    }

    public void setDimension(Size size) {
        this.dimension_ = size;
    }

    public PointF getResolution() {
        return this.resolution_;
    }

    public void setResolution(PointF pointF) {
        this.resolution_ = pointF;
    }

    public byte[] getMapData() {
        return this.mapData_;
    }

    public void setMapData(byte[] bArr) {
        this.mapData_ = bArr;
    }

    @Override // com.slamtec.slamware.robot.MapLayer
    public void clear() {
        super.clear();
        this.origin_ = null;
        this.dimension_ = null;
        this.resolution_ = null;
        this.mapData_ = null;
    }
}
