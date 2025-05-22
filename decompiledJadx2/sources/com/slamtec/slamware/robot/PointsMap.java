package com.slamtec.slamware.robot;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class PointsMap extends MapLayer {
    private ArrayList<PointPDF> points_;

    public ArrayList<PointPDF> getPoints() {
        return this.points_;
    }

    public void setPoints(ArrayList<PointPDF> arrayList) {
        this.points_ = arrayList;
    }

    @Override // com.slamtec.slamware.robot.MapLayer
    public void clear() {
        super.clear();
        this.points_.clear();
    }
}
