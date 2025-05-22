package com.pudutech.opengl_draw.bean;

/* loaded from: classes5.dex */
public class LaserScan {
    private float angle;
    private float angleIncrement;
    private float rangeMax;
    private float rangeMin;
    private float[] ranges;

    public float[] getRanges() {
        return this.ranges;
    }

    public void setRanges(float[] fArr) {
        this.ranges = fArr;
    }

    public float getRangeMin() {
        return this.rangeMin;
    }

    public void setRangeMin(float f) {
        this.rangeMin = f;
    }

    public float getRangeMax() {
        return this.rangeMax;
    }

    public void setRangeMax(float f) {
        this.rangeMax = f;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public float getAngleIncrement() {
        return this.angleIncrement;
    }

    public void setAngleIncrement(float f) {
        this.angleIncrement = f;
    }
}
