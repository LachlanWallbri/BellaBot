package com.pudutech.rgbdlib.pointcloud.tangoutils;

import android.opengl.Matrix;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes4.dex */
public class ModelMatCalculator {
    private static float[] mConversionMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float[] mModelMatrix = new float[16];
    private float[] mPointCloudModelMatrix = new float[16];
    private float[] mDevice2IMUMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float[] mColorCamera2IMUMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private float[] mOpengl2ColorCameraMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    public ModelMatCalculator() {
        Matrix.setIdentityM(this.mModelMatrix, 0);
        Matrix.setIdentityM(this.mPointCloudModelMatrix, 0);
    }

    public void updatePointCloudModelMatrix(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        Matrix.setIdentityM(fArr3, 0);
        Matrix.multiplyMM(fArr3, 0, this.mColorCamera2IMUMatrix, 0, this.mOpengl2ColorCameraMatrix, 0);
        float[] fArr4 = new float[16];
        Matrix.setIdentityM(fArr4, 0);
        Matrix.invertM(fArr4, 0, this.mDevice2IMUMatrix, 0);
        float[] fArr5 = new float[16];
        Matrix.setIdentityM(fArr5, 0);
        Matrix.multiplyMM(fArr5, 0, fArr4, 0, fArr3, 0);
        Matrix.setIdentityM(new float[16], 0);
        float[] quaternionMatrixOpenGL = quaternionMatrixOpenGL(fArr2);
        float[] fArr6 = new float[16];
        Matrix.setIdentityM(fArr6, 0);
        Matrix.setIdentityM(this.mPointCloudModelMatrix, 0);
        Matrix.multiplyMM(fArr6, 0, quaternionMatrixOpenGL, 0, fArr5, 0);
        Matrix.multiplyMM(this.mPointCloudModelMatrix, 0, mConversionMatrix, 0, fArr6, 0);
        float[] fArr7 = this.mPointCloudModelMatrix;
        fArr7[12] = fArr7[12] + fArr[0];
        fArr7[13] = fArr7[13] + fArr[2];
        fArr7[14] = fArr7[14] + (fArr[1] * (-1.0f));
    }

    public void updateModelMatrix(float[] fArr, float[] fArr2) {
        float[] fArr3 = new float[16];
        Matrix.setIdentityM(fArr3, 0);
        Matrix.multiplyMM(fArr3, 0, this.mColorCamera2IMUMatrix, 0, this.mOpengl2ColorCameraMatrix, 0);
        float[] fArr4 = new float[16];
        Matrix.setIdentityM(fArr4, 0);
        Matrix.invertM(fArr4, 0, this.mDevice2IMUMatrix, 0);
        float[] fArr5 = new float[16];
        Matrix.setIdentityM(fArr5, 0);
        Matrix.multiplyMM(fArr5, 0, fArr4, 0, fArr3, 0);
        Matrix.setIdentityM(new float[16], 0);
        float[] quaternionMatrixOpenGL = quaternionMatrixOpenGL(fArr2);
        float[] fArr6 = new float[16];
        Matrix.setIdentityM(fArr6, 0);
        Matrix.setIdentityM(this.mModelMatrix, 0);
        Matrix.multiplyMM(fArr6, 0, quaternionMatrixOpenGL, 0, fArr5, 0);
        Matrix.multiplyMM(this.mModelMatrix, 0, mConversionMatrix, 0, fArr6, 0);
        float[] fArr7 = this.mModelMatrix;
        fArr7[12] = fArr7[12] + fArr[0];
        fArr7[13] = fArr7[13] + fArr[2];
        fArr7[14] = fArr7[14] + (fArr[1] * (-1.0f));
    }

    public void SetDevice2IMUMatrix(float[] fArr, float[] fArr2) {
        this.mDevice2IMUMatrix = quaternionMatrixOpenGL(fArr2);
        float[] fArr3 = this.mDevice2IMUMatrix;
        fArr3[12] = fArr[0];
        fArr3[13] = fArr[1];
        fArr3[14] = fArr[2];
    }

    public void SetColorCamera2IMUMatrix(float[] fArr, float[] fArr2) {
        this.mOpengl2ColorCameraMatrix = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        this.mColorCamera2IMUMatrix = quaternionMatrixOpenGL(fArr2);
        float[] fArr3 = this.mColorCamera2IMUMatrix;
        fArr3[12] = fArr[0];
        fArr3[13] = fArr[1];
        fArr3[14] = fArr[2];
    }

    public float[] getModelMatrix() {
        return this.mModelMatrix;
    }

    public float[] getModelMatrixCopy() {
        float[] fArr = new float[16];
        System.arraycopy(this.mModelMatrix, 0, fArr, 0, 16);
        return fArr;
    }

    public float[] getPointCloudModelMatrixCopy() {
        float[] fArr = new float[16];
        float[] fArr2 = new float[16];
        Matrix.setIdentityM(fArr2, 0);
        Matrix.multiplyMM(fArr2, 0, this.mPointCloudModelMatrix, 0, new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f}, 0);
        System.arraycopy(fArr2, 0, fArr, 0, 16);
        return fArr;
    }

    public float[] getTranslation() {
        float[] fArr = this.mModelMatrix;
        return new float[]{fArr[12], fArr[13], fArr[14]};
    }

    public static float[] quaternionMatrixOpenGL(float[] fArr) {
        normalizeVector(fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = f * f;
        float f6 = f2 * f2;
        float f7 = f3 * f3;
        float f8 = f * f2;
        float f9 = f * f3;
        float f10 = f2 * f3;
        float f11 = f * f4;
        float f12 = f2 * f4;
        float f13 = f4 * f3;
        return new float[]{1.0f - ((f6 + f7) * 2.0f), (f8 + f13) * 2.0f, (f9 - f12) * 2.0f, 0.0f, (f8 - f13) * 2.0f, 1.0f - ((f7 + f5) * 2.0f), (f10 + f11) * 2.0f, 0.0f, (f9 + f12) * 2.0f, (f10 - f11) * 2.0f, 1.0f - ((f5 + f6) * 2.0f), 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static void normalizeVector(float[] fArr) {
        float f = (fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2]) + (fArr[3] * fArr[3]);
        if (Math.abs(f) <= 1.0E-5f || Math.abs(f - 1.0f) <= 1.0E-5f) {
            return;
        }
        float sqrt = (float) Math.sqrt(f);
        fArr[0] = fArr[0] / sqrt;
        fArr[1] = fArr[1] / sqrt;
        fArr[2] = fArr[2] / sqrt;
        fArr[3] = fArr[3] / sqrt;
    }
}
