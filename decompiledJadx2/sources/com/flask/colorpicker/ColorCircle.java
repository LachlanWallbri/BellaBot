package com.flask.colorpicker;

import android.graphics.Color;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class ColorCircle {
    private int color;
    private float[] hsv = new float[3];
    private float[] hsvClone;

    /* renamed from: x */
    private float f1276x;

    /* renamed from: y */
    private float f1277y;

    public ColorCircle(float f, float f2, float[] fArr) {
        set(f, f2, fArr);
    }

    public double sqDist(float f, float f2) {
        double d = this.f1276x - f;
        double d2 = this.f1277y - f2;
        return (d * d) + (d2 * d2);
    }

    public float getX() {
        return this.f1276x;
    }

    public float getY() {
        return this.f1277y;
    }

    public float[] getHsv() {
        return this.hsv;
    }

    public float[] getHsvWithLightness(float f) {
        if (this.hsvClone == null) {
            this.hsvClone = (float[]) this.hsv.clone();
        }
        float[] fArr = this.hsvClone;
        float[] fArr2 = this.hsv;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        fArr[2] = f;
        return fArr;
    }

    public void set(float f, float f2, float[] fArr) {
        this.f1276x = f;
        this.f1277y = f2;
        float[] fArr2 = this.hsv;
        fArr2[0] = fArr[0];
        fArr2[1] = fArr[1];
        fArr2[2] = fArr[2];
        this.color = Color.HSVToColor(fArr2);
    }

    public int getColor() {
        return this.color;
    }
}
