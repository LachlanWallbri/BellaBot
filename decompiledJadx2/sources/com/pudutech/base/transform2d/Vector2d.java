package com.pudutech.base.transform2d;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class Vector2d {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* renamed from: x */
    public double f4498x;

    /* renamed from: y */
    public double f4499y;

    public Vector2d() {
        this.f4498x = 0.0d;
        this.f4499y = 0.0d;
    }

    public Vector2d(double d, double d2) {
        this.f4498x = d;
        this.f4499y = d2;
    }

    public void setUnit() {
        this.f4498x = 1.0d;
        this.f4499y = 0.0d;
    }

    public double norm() {
        double d = this.f4498x;
        double d2 = this.f4499y;
        return Math.sqrt((d * d) + (d2 * d2));
    }

    public void normalize() {
        double norm = norm();
        this.f4498x /= norm;
        this.f4499y /= norm;
    }

    public Vector2d add(Vector2d vector2d) {
        Vector2d vector2d2 = new Vector2d();
        vector2d2.f4498x = this.f4498x + vector2d.f4498x;
        vector2d2.f4499y = this.f4499y + vector2d.f4499y;
        return vector2d2;
    }

    public Vector2d minus(Vector2d vector2d) {
        Vector2d vector2d2 = new Vector2d();
        vector2d2.f4498x = this.f4498x - vector2d.f4498x;
        vector2d2.f4499y = this.f4499y - vector2d.f4499y;
        return vector2d2;
    }

    public double angleToVector(Vector2d vector2d) {
        normalize();
        vector2d.normalize();
        double d = this.f4498x;
        double d2 = vector2d.f4499y;
        double d3 = vector2d.f4498x;
        double d4 = this.f4499y;
        double d5 = (d * d2) - (d3 * d4);
        double d6 = (d * d3) + (d4 * d2);
        if (d6 > 1.0d) {
            d6 = 1.0d;
        }
        if (d6 < -1.0d) {
            d6 = -1.0d;
        }
        double acos = Math.acos(d6);
        if (d5 < 0.0d) {
            acos = -acos;
        }
        while (acos > 3.141592653589793d) {
            acos -= 6.283185307179586d;
        }
        while (acos < -3.141592653589793d) {
            acos += 6.283185307179586d;
        }
        return acos;
    }

    public String toString() {
        return "[" + this.f4498x + " " + this.f4499y + "]";
    }
}
