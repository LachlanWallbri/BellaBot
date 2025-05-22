package com.pudutech.opengl_draw.geometry;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class Vector3 {

    /* renamed from: x */
    private final double f6821x;

    /* renamed from: y */
    private final double f6822y;

    /* renamed from: z */
    private final double f6823z;
    private static final Vector3 ZERO = new Vector3(0.0d, 0.0d, 0.0d);
    private static final Vector3 X_AXIS = new Vector3(1.0d, 0.0d, 0.0d);
    private static final Vector3 Y_AXIS = new Vector3(0.0d, 1.0d, 0.0d);
    private static final Vector3 Z_AXIS = new Vector3(0.0d, 0.0d, 1.0d);

    public static Vector3 fromPointMessage(double d, double d2) {
        return new Vector3(d, d2, 0.0d);
    }

    public static Vector3 zero() {
        return ZERO;
    }

    public static Vector3 xAxis() {
        return X_AXIS;
    }

    public static Vector3 yAxis() {
        return Y_AXIS;
    }

    public static Vector3 zAxis() {
        return Z_AXIS;
    }

    public Vector3(double d, double d2, double d3) {
        this.f6821x = d;
        this.f6822y = d2;
        this.f6823z = d3;
    }

    public Vector3 add(Vector3 vector3) {
        return new Vector3(this.f6821x + vector3.f6821x, this.f6822y + vector3.f6822y, this.f6823z + vector3.f6823z);
    }

    public Vector3 subtract(Vector3 vector3) {
        return new Vector3(this.f6821x - vector3.f6821x, this.f6822y - vector3.f6822y, this.f6823z - vector3.f6823z);
    }

    public Vector3 invert() {
        return new Vector3(-this.f6821x, -this.f6822y, -this.f6823z);
    }

    public double dotProduct(Vector3 vector3) {
        return (this.f6821x * vector3.f6821x) + (this.f6822y * vector3.f6822y) + (this.f6823z * vector3.f6823z);
    }

    public Vector3 normalize() {
        return new Vector3(this.f6821x / getMagnitude(), this.f6822y / getMagnitude(), this.f6823z / getMagnitude());
    }

    public Vector3 scale(double d) {
        return new Vector3(this.f6821x * d, this.f6822y * d, this.f6823z * d);
    }

    public double getX() {
        return this.f6821x;
    }

    public double getY() {
        return this.f6822y;
    }

    public double getZ() {
        return this.f6823z;
    }

    public double getMagnitudeSquared() {
        double d = this.f6821x;
        double d2 = this.f6822y;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.f6823z;
        return d3 + (d4 * d4);
    }

    public double getMagnitude() {
        return Math.sqrt(getMagnitudeSquared());
    }

    public boolean almostEquals(Vector3 vector3, double d) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Double.valueOf(this.f6821x - vector3.f6821x));
        arrayList.add(Double.valueOf(this.f6822y - vector3.f6822y));
        arrayList.add(Double.valueOf(this.f6823z - vector3.f6823z));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (Math.abs(((Double) it.next()).doubleValue()) > d) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return String.format("Vector3<x: %.4f, y: %.4f, z: %.4f>", Double.valueOf(this.f6821x), Double.valueOf(this.f6822y), Double.valueOf(this.f6823z));
    }

    public int hashCode() {
        double d = this.f6821x;
        if (d == 0.0d) {
            d = 0.0d;
        }
        double d2 = this.f6822y;
        if (d2 == 0.0d) {
            d2 = 0.0d;
        }
        double d3 = this.f6823z;
        double d4 = d3 != 0.0d ? d3 : 0.0d;
        long doubleToLongBits = Double.doubleToLongBits(d);
        long doubleToLongBits2 = Double.doubleToLongBits(d2);
        long doubleToLongBits3 = Double.doubleToLongBits(d4);
        return ((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector3 vector3 = (Vector3) obj;
        double d = this.f6821x;
        if (d == 0.0d) {
            d = 0.0d;
        }
        double d2 = this.f6822y;
        if (d2 == 0.0d) {
            d2 = 0.0d;
        }
        double d3 = this.f6823z;
        if (d3 == 0.0d) {
            d3 = 0.0d;
        }
        double d4 = vector3.f6821x;
        if (d4 == 0.0d) {
            d4 = 0.0d;
        }
        double d5 = vector3.f6822y;
        if (d5 == 0.0d) {
            d5 = 0.0d;
        }
        double d6 = vector3.f6823z;
        if (d6 == 0.0d) {
            d6 = 0.0d;
        }
        return Double.doubleToLongBits(d) == Double.doubleToLongBits(d4) && Double.doubleToLongBits(d2) == Double.doubleToLongBits(d5) && Double.doubleToLongBits(d3) == Double.doubleToLongBits(d6);
    }
}
