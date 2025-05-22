package com.pudutech.opengl_draw.geometry;

import com.pudutech.opengl_draw.bean.OccupancyGrid;

/* loaded from: classes5.dex */
public class Transform {
    private Quaternion rotationAndScale;
    private Vector3 translation;

    public static Transform fromPoseMessage(OccupancyGrid occupancyGrid) {
        return new Transform(occupancyGrid.getVector3(), occupancyGrid.getQuaternion());
    }

    public static Transform identity() {
        return new Transform(Vector3.zero(), Quaternion.identity());
    }

    public static Transform xRotation(double d) {
        return new Transform(Vector3.zero(), Quaternion.fromAxisAngle(Vector3.xAxis(), d));
    }

    public static Transform yRotation(double d) {
        return new Transform(Vector3.zero(), Quaternion.fromAxisAngle(Vector3.yAxis(), d));
    }

    public static Transform zRotation(double d) {
        return new Transform(Vector3.zero(), Quaternion.fromAxisAngle(Vector3.zAxis(), d));
    }

    public static Transform translation(double d, double d2, double d3) {
        return new Transform(new Vector3(d, d2, d3), Quaternion.identity());
    }

    public static Transform translation(Vector3 vector3) {
        return new Transform(vector3, Quaternion.identity());
    }

    public Transform(Vector3 vector3, Quaternion quaternion) {
        this.translation = vector3;
        this.rotationAndScale = quaternion;
    }

    public Transform multiply(Transform transform) {
        return new Transform(apply(transform.translation), apply(transform.rotationAndScale));
    }

    public Transform invert() {
        Quaternion invert = this.rotationAndScale.invert();
        return new Transform(invert.rotateAndScaleVector(this.translation.invert()), invert);
    }

    public Vector3 apply(Vector3 vector3) {
        return this.rotationAndScale.rotateAndScaleVector(vector3).add(this.translation);
    }

    public Vector3 applyCenter(Vector3 vector3) {
        return this.rotationAndScale.rotateAndScaleVector(vector3);
    }

    public Quaternion apply(Quaternion quaternion) {
        return this.rotationAndScale.multiply(quaternion);
    }

    public Transform scale(double d) {
        return new Transform(this.translation, this.rotationAndScale.scale(Math.sqrt(d)));
    }

    public double getScale() {
        Quaternion quaternion = this.rotationAndScale;
        if (quaternion == null) {
            return Quaternion.identity().getMagnitudeSquared();
        }
        return quaternion.getMagnitudeSquared();
    }

    public double[] toMatrix() {
        double x = this.rotationAndScale.getX();
        double y = this.rotationAndScale.getY();
        double z = this.rotationAndScale.getZ();
        double w = this.rotationAndScale.getW();
        double magnitudeSquared = this.rotationAndScale.getMagnitudeSquared();
        double d = y * 2.0d;
        double d2 = d * y;
        double d3 = z * 2.0d;
        double d4 = d3 * z;
        double d5 = 2.0d * x;
        double d6 = y * d5;
        double d7 = d3 * w;
        double d8 = d5 * z;
        double d9 = d * w;
        double d10 = magnitudeSquared - (x * d5);
        double d11 = d * z;
        double d12 = d5 * w;
        return new double[]{(magnitudeSquared - d2) - d4, d6 + d7, d8 - d9, 0.0d, d6 - d7, d10 - d4, d11 + d12, 0.0d, d8 + d9, d11 - d12, d10 - d2, 0.0d, this.translation.getX(), this.translation.getY(), this.translation.getZ(), 1.0d};
    }

    public boolean almostEquals(Transform transform, double d) {
        return this.translation.almostEquals(transform.translation, d) && this.rotationAndScale.almostEquals(transform.rotationAndScale, d);
    }

    public Vector3 getTranslation() {
        return this.translation;
    }

    public Quaternion getRotationAndScale() {
        return this.rotationAndScale;
    }

    public String toString() {
        return String.format("Transform<%s, %s>", this.translation, this.rotationAndScale);
    }

    public int hashCode() {
        Quaternion quaternion = this.rotationAndScale;
        int hashCode = ((quaternion == null ? 0 : quaternion.hashCode()) + 31) * 31;
        Vector3 vector3 = this.translation;
        return hashCode + (vector3 != null ? vector3.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Transform transform = (Transform) obj;
        Quaternion quaternion = this.rotationAndScale;
        if (quaternion == null) {
            if (transform.rotationAndScale != null) {
                return false;
            }
        } else if (!quaternion.equals(transform.rotationAndScale)) {
            return false;
        }
        Vector3 vector3 = this.translation;
        if (vector3 == null) {
            if (transform.translation != null) {
                return false;
            }
        } else if (!vector3.equals(transform.translation)) {
            return false;
        }
        return true;
    }
}
