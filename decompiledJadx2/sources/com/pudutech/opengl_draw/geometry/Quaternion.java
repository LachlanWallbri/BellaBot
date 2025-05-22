package com.pudutech.opengl_draw.geometry;

import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class Quaternion {

    /* renamed from: w */
    private final double f6817w;

    /* renamed from: x */
    private final double f6818x;

    /* renamed from: y */
    private final double f6819y;

    /* renamed from: z */
    private final double f6820z;

    public static Quaternion fromAxisAngle(Vector3 vector3, double d) {
        Vector3 normalize = vector3.normalize();
        double d2 = d / 2.0d;
        double sin = Math.sin(d2);
        return new Quaternion(normalize.getX() * sin, normalize.getY() * sin, normalize.getZ() * sin, Math.cos(d2));
    }

    public static Quaternion fromQuaternionMessage(Quaternion quaternion) {
        return new Quaternion(quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW());
    }

    public static Quaternion rotationBetweenVectors(Vector3 vector3, Vector3 vector32) {
        Preconditions.checkArgument(vector3.getMagnitude() > 0.0d, "Cannot calculate rotation between zero-length vectors.");
        Preconditions.checkArgument(vector32.getMagnitude() > 0.0d, "Cannot calculate rotation between zero-length vectors.");
        if (vector3.normalize().equals(vector32.normalize())) {
            return identity();
        }
        return fromAxisAngle(new Vector3((vector3.getY() * vector32.getZ()) - (vector3.getZ() * vector32.getY()), (vector3.getZ() * vector32.getX()) - (vector3.getX() * vector32.getZ()), (vector3.getX() * vector32.getY()) - (vector3.getY() * vector32.getX())), Math.acos(vector3.dotProduct(vector32) / (vector3.getMagnitude() * vector32.getMagnitude())));
    }

    public Quaternion(double[] dArr) {
        if (dArr.length != 3) {
            this.f6818x = 0.0d;
            this.f6819y = 0.0d;
            this.f6820z = 0.0d;
            this.f6817w = 0.0d;
            return;
        }
        double d = dArr[0] / 2.0d;
        double d2 = dArr[1] / 2.0d;
        double d3 = dArr[2] / 2.0d;
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        double cos2 = Math.cos(d2);
        double sin2 = Math.sin(d2);
        double cos3 = Math.cos(d3);
        double sin3 = Math.sin(d3);
        double d4 = cos * cos2;
        double d5 = sin * sin2;
        this.f6817w = (d4 * cos3) + (d5 * sin3);
        double d6 = sin * cos2;
        double d7 = cos * sin2;
        this.f6818x = (d6 * cos3) - (d7 * sin3);
        this.f6819y = (d7 * cos3) + (d6 * sin3);
        this.f6820z = (d4 * sin3) - (d5 * cos3);
    }

    public static Quaternion identity() {
        return new Quaternion(0.0d, 0.0d, 0.0d, 1.0d);
    }

    public Quaternion(double d, double d2, double d3, double d4) {
        this.f6818x = d;
        this.f6819y = d2;
        this.f6820z = d3;
        this.f6817w = d4;
    }

    public Quaternion scale(double d) {
        return new Quaternion(this.f6818x * d, this.f6819y * d, this.f6820z * d, this.f6817w * d);
    }

    public Quaternion conjugate() {
        return new Quaternion(-this.f6818x, -this.f6819y, -this.f6820z, this.f6817w);
    }

    public Quaternion invert() {
        double magnitudeSquared = getMagnitudeSquared();
        Preconditions.checkState(magnitudeSquared != 0.0d);
        return conjugate().scale(1.0d / magnitudeSquared);
    }

    public Quaternion normalize() {
        return scale(1.0d / getMagnitude());
    }

    public Quaternion multiply(Quaternion quaternion) {
        double d = this.f6817w;
        double d2 = quaternion.f6818x;
        double d3 = this.f6818x;
        double d4 = quaternion.f6817w;
        double d5 = (d * d2) + (d3 * d4);
        double d6 = this.f6819y;
        double d7 = quaternion.f6820z;
        double d8 = d5 + (d6 * d7);
        double d9 = this.f6820z;
        double d10 = quaternion.f6819y;
        return new Quaternion(d8 - (d9 * d10), (((d * d10) + (d6 * d4)) + (d9 * d2)) - (d3 * d7), (((d * d7) + (d9 * d4)) + (d3 * d10)) - (d6 * d2), (((d * d4) - (d2 * d3)) - (d6 * d10)) - (d9 * d7));
    }

    public Vector3 rotateAndScaleVector(Vector3 vector3) {
        Quaternion multiply = multiply(new Quaternion(vector3.getX(), vector3.getY(), vector3.getZ(), 0.0d).multiply(conjugate()));
        return new Vector3(multiply.getX(), multiply.getY(), multiply.getZ());
    }

    public double getX() {
        return this.f6818x;
    }

    public double getY() {
        return this.f6819y;
    }

    public double getZ() {
        return this.f6820z;
    }

    public double getW() {
        return this.f6817w;
    }

    public double getMagnitudeSquared() {
        double d = this.f6818x;
        double d2 = this.f6819y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.f6820z;
        double d5 = d3 + (d4 * d4);
        double d6 = this.f6817w;
        return d5 + (d6 * d6);
    }

    public double getMagnitude() {
        return Math.sqrt(getMagnitudeSquared());
    }

    public boolean isAlmostNeutral(double d) {
        double d2 = this.f6818x;
        double d3 = 1.0d - (d2 * d2);
        double d4 = this.f6819y;
        double d5 = d3 - (d4 * d4);
        double d6 = this.f6820z;
        double d7 = d5 - (d6 * d6);
        double d8 = this.f6817w;
        return Math.abs(d7 - (d8 * d8)) < d;
    }

    public boolean almostEquals(Quaternion quaternion, double d) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(this.f6818x - quaternion.f6818x));
        arrayList.add(Double.valueOf(this.f6819y - quaternion.f6819y));
        arrayList.add(Double.valueOf(this.f6820z - quaternion.f6820z));
        arrayList.add(Double.valueOf(this.f6817w - quaternion.f6817w));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (Math.abs(((Double) it.next()).doubleValue()) > d) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return String.format("Quaternion<x: %.4f, y: %.4f, z: %.4f, w: %.4f>", Double.valueOf(this.f6818x), Double.valueOf(this.f6819y), Double.valueOf(this.f6820z), Double.valueOf(this.f6817w));
    }

    public int hashCode() {
        double d = this.f6817w;
        if (d == 0.0d) {
            d = 0.0d;
        }
        double d2 = this.f6818x;
        if (d2 == 0.0d) {
            d2 = 0.0d;
        }
        double d3 = this.f6819y;
        if (d3 == 0.0d) {
            d3 = 0.0d;
        }
        double d4 = this.f6820z;
        double d5 = d4 != 0.0d ? d4 : 0.0d;
        long doubleToLongBits = Double.doubleToLongBits(d);
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(d3);
        long doubleToLongBits4 = Double.doubleToLongBits(d5);
        return (((i * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quaternion quaternion = (Quaternion) obj;
        double d = this.f6817w;
        if (d == 0.0d) {
            d = 0.0d;
        }
        double d2 = this.f6818x;
        if (d2 == 0.0d) {
            d2 = 0.0d;
        }
        double d3 = this.f6819y;
        if (d3 == 0.0d) {
            d3 = 0.0d;
        }
        double d4 = this.f6820z;
        if (d4 == 0.0d) {
            d4 = 0.0d;
        }
        double d5 = quaternion.f6817w;
        if (d5 == 0.0d) {
            d5 = 0.0d;
        }
        double d6 = quaternion.f6818x;
        if (d6 == 0.0d) {
            d6 = 0.0d;
        }
        double d7 = d4;
        double d8 = quaternion.f6819y;
        if (d8 == 0.0d) {
            d8 = 0.0d;
        }
        double d9 = quaternion.f6820z;
        if (d9 == 0.0d) {
            d9 = 0.0d;
        }
        return Double.doubleToLongBits(d) == Double.doubleToLongBits(d5) && Double.doubleToLongBits(d2) == Double.doubleToLongBits(d6) && Double.doubleToLongBits(d3) == Double.doubleToLongBits(d8) && Double.doubleToLongBits(d7) == Double.doubleToLongBits(d9);
    }
}
