package com.slamtec.slamware.geometry;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class PointF {

    /* renamed from: x */
    private float f7597x;

    /* renamed from: y */
    private float f7598y;

    public PointF() {
        this.f7597x = 0.0f;
        this.f7598y = 0.0f;
    }

    public PointF(float f, float f2) {
        this.f7597x = f;
        this.f7598y = f2;
    }

    public PointF(PointF pointF) {
        this.f7597x = pointF.f7597x;
        this.f7598y = pointF.f7598y;
    }

    public float getX() {
        return this.f7597x;
    }

    public void setX(float f) {
        this.f7597x = f;
    }

    public float getY() {
        return this.f7598y;
    }

    public void setY(float f) {
        this.f7598y = f;
    }
}
