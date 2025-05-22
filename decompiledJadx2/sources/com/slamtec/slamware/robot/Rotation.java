package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Rotation {
    private float pitch;
    private float roll;
    private float yaw;

    public Rotation() {
        this.pitch = 0.0f;
        this.roll = 0.0f;
        this.yaw = 0.0f;
    }

    public Rotation(float f) {
        this.yaw = f;
        this.pitch = 0.0f;
        this.roll = 0.0f;
    }

    public Rotation(float f, float f2, float f3) {
        this.yaw = f;
        this.roll = f3;
        this.pitch = f2;
    }

    public Rotation(Rotation rotation) {
        this.yaw = rotation.yaw;
        this.roll = rotation.roll;
        this.pitch = rotation.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float f) {
        this.roll = f;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }
}
