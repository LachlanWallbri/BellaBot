package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Location {

    /* renamed from: x */
    private float f7601x;

    /* renamed from: y */
    private float f7602y;

    /* renamed from: z */
    private float f7603z;

    public Location() {
        this.f7603z = 0.0f;
        this.f7602y = 0.0f;
        this.f7601x = 0.0f;
    }

    public Location(float f, float f2, float f3) {
        this.f7601x = f;
        this.f7602y = f2;
        this.f7603z = f3;
    }

    public Location(Location location) {
        this.f7601x = location.f7601x;
        this.f7602y = location.f7602y;
        this.f7603z = location.f7603z;
    }

    public float distanceTo(Location location) {
        float f = location.f7601x - this.f7601x;
        float f2 = location.f7602y - this.f7602y;
        float f3 = location.f7603z - this.f7603z;
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
    }

    public float getX() {
        return this.f7601x;
    }

    public void setX(float f) {
        this.f7601x = f;
    }

    public float getY() {
        return this.f7602y;
    }

    public void setY(float f) {
        this.f7602y = f;
    }

    public float getZ() {
        return this.f7603z;
    }

    public void setZ(float f) {
        this.f7603z = f;
    }
}
