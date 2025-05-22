package com.pudutech.base.transform2d;

import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class Transform2d {
    private static final int THREE_DIMENSIONAL = 3;
    private double[][] matrix = (double[][]) Array.newInstance((Class<?>) double.class, 3, 3);

    public Transform2d() {
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                this.matrix[i][i2] = 0.0d;
            }
        }
    }

    public void setIdentity() {
        int i = 0;
        while (i < 3) {
            int i2 = 0;
            while (i2 < 3) {
                this.matrix[i][i2] = i == i2 ? 1.0d : 0.0d;
                i2++;
            }
            i++;
        }
    }

    public void rotate(double d) {
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        for (int i = 0; i < 3; i++) {
            double[][] dArr = this.matrix;
            double d2 = dArr[i][0];
            double d3 = dArr[i][1];
            dArr[i][0] = (d2 * cos) + (d3 * sin);
            dArr[i][1] = (d3 * cos) - (d2 * sin);
        }
    }

    public void translate(double d, double d2) {
        for (int i = 0; i < 3; i++) {
            double[][] dArr = this.matrix;
            double[] dArr2 = dArr[i];
            dArr2[2] = dArr2[2] + (dArr[i][0] * d) + (dArr[i][1] * d2);
        }
    }

    public void translate(Vector2d vector2d) {
        translate(vector2d.f4498x, vector2d.f4499y);
    }

    public Vector2d times(Vector2d vector2d) {
        Vector2d vector2d2 = new Vector2d();
        vector2d2.f4498x = (this.matrix[0][0] * vector2d.f4498x) + (this.matrix[0][1] * vector2d.f4499y) + this.matrix[0][2];
        vector2d2.f4499y = (this.matrix[1][0] * vector2d.f4498x) + (this.matrix[1][1] * vector2d.f4499y) + this.matrix[1][2];
        return vector2d2;
    }

    public Transform2d times(Transform2d transform2d) {
        Transform2d transform2d2 = new Transform2d();
        for (int i = 0; i < 3; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                transform2d2.matrix[i][i2] = 0.0d;
                for (int i3 = 0; i3 < 3; i3++) {
                    double[] dArr = transform2d2.matrix[i];
                    dArr[i2] = dArr[i2] + (this.matrix[i][i3] * transform2d.matrix[i3][i2]);
                }
            }
        }
        return transform2d2;
    }
}
