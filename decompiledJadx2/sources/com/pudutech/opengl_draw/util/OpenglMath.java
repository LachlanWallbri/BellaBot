package com.pudutech.opengl_draw.util;

/* loaded from: classes5.dex */
public class OpenglMath {
    public static double clamp(double d, double d2, double d3) {
        return d < d2 ? d2 : d > d3 ? d3 : d;
    }

    public static double getAngleByPoint(double d, double d2) {
        return d2 > 0.0d ? Math.acos(d / Math.sqrt((d * d) + (d2 * d2))) : 6.283185307179586d - Math.acos(d / Math.sqrt((d * d) + (d2 * d2)));
    }

    public static double getLength(double d, double d2) {
        return Math.sqrt((d * d) + (d2 * d2));
    }
}
