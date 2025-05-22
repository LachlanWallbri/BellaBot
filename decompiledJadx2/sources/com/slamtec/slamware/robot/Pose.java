package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class Pose {
    private Location location;
    private Rotation rotation;

    public Pose() {
        this.location = new Location();
        this.rotation = new Rotation();
    }

    public Pose(Location location, Rotation rotation) {
        this.location = location;
        this.rotation = rotation;
    }

    public Pose(float f, float f2, float f3, float f4, float f5, float f6) {
        this.location = new Location(f, f2, f3);
        this.rotation = new Rotation(f4, f6, f5);
    }

    public Pose(Pose pose) {
        this.location = new Location(pose.location);
        this.rotation = new Rotation(pose.rotation);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = new Location(location);
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = new Rotation(rotation);
    }

    public float getX() {
        return this.location.getX();
    }

    public void setX(float f) {
        this.location.setX(f);
    }

    public float getY() {
        return this.location.getY();
    }

    public void setY(float f) {
        this.location.setY(f);
    }

    public float getZ() {
        return this.location.getZ();
    }

    public void setZ(float f) {
        this.location.setZ(f);
    }

    public float getYaw() {
        return this.rotation.getYaw();
    }

    public void setYaw(float f) {
        this.rotation.setYaw(f);
    }

    public float getRoll() {
        return this.rotation.getRoll();
    }

    public void setRoll(float f) {
        this.rotation.setRoll(f);
    }

    public float getPitch() {
        return this.rotation.getPitch();
    }

    public void setPitch(float f) {
        this.rotation.setPitch(f);
    }
}
