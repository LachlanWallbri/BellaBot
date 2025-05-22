package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class LaserPoint {
    private float angle;
    private float distance;
    private boolean valid;

    public LaserPoint() {
        this.angle = 0.0f;
        this.distance = 0.0f;
        this.valid = false;
    }

    public LaserPoint(float f, float f2) {
        this.distance = f;
        this.angle = f2;
        this.valid = true;
    }

    public LaserPoint(float f, float f2, boolean z) {
        this.distance = f;
        this.angle = f2;
        this.valid = z;
    }

    public LaserPoint(LaserPoint laserPoint) {
        this.distance = laserPoint.distance;
        this.angle = laserPoint.angle;
        this.valid = laserPoint.valid;
    }

    public float getDistance() {
        return this.distance;
    }

    public void setDistance(float f) {
        this.distance = f;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setValid(boolean z) {
        this.valid = z;
    }
}
