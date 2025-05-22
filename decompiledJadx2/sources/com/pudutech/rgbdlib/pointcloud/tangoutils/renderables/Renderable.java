package com.pudutech.rgbdlib.pointcloud.tangoutils.renderables;

import android.opengl.Matrix;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes5.dex */
public abstract class Renderable {
    private float[] mModelMatrix = new float[16];
    private float[] mMvMatrix = new float[16];
    private float[] mMvpMatrix = new float[16];

    public abstract void draw(float[] fArr, float[] fArr2);

    public synchronized void updateMvpMatrix(float[] fArr, float[] fArr2) {
        Matrix.setIdentityM(this.mMvMatrix, 0);
        Matrix.setIdentityM(this.mMvpMatrix, 0);
        Matrix.multiplyMM(this.mMvMatrix, 0, fArr, 0, this.mModelMatrix, 0);
        Matrix.multiplyMM(this.mMvpMatrix, 0, fArr2, 0, this.mMvMatrix, 0);
    }

    public float[] getModelMatrix() {
        return this.mModelMatrix;
    }

    public void setModelMatrix(float[] fArr) {
        this.mModelMatrix = fArr;
    }

    public float[] getMvMatrix() {
        return this.mMvMatrix;
    }

    public float[] getMvpMatrix() {
        return this.mMvpMatrix;
    }
}
