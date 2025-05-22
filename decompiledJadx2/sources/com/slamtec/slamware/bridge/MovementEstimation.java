package com.slamtec.slamware.bridge;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class MovementEstimation {
    private float angularDifference;
    private float positionDifferenceX;
    private float positionDifferenceY;

    public MovementEstimation(float f, float f2, float f3) {
        this.positionDifferenceX = f;
        this.positionDifferenceY = f2;
        this.angularDifference = f3;
    }

    public float getPositionDifferenceX() {
        return this.positionDifferenceX;
    }

    public void setPositionDifferenceX(float f) {
        this.positionDifferenceX = f;
    }

    public float getPositionDifferenceY() {
        return this.positionDifferenceY;
    }

    public void setPositionDifferenceY(float f) {
        this.positionDifferenceY = f;
    }

    public float getAngularDifference() {
        return this.angularDifference;
    }

    public void setAngularDifference(float f) {
        this.angularDifference = f;
    }
}
