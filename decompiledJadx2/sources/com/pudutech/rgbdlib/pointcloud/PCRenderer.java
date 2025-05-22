package com.pudutech.rgbdlib.pointcloud;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.pudutech.rgbdlib.pointcloud.tangoutils.Renderer;
import com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.CameraFrustumAndAxis;
import com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.Grid;
import com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.PointCloud;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes3.dex */
public class PCRenderer extends Renderer implements GLSurfaceView.Renderer {
    private CameraFrustumAndAxis mCameraFrustumAndAxis;
    private Grid mGrid;
    private int mMaxDepthPoints;
    private PointCloud mPointCloud;

    public PCRenderer(int i) {
        this.mMaxDepthPoints = i;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glEnable(2929);
        this.mPointCloud = new PointCloud(this.mMaxDepthPoints);
        this.mGrid = new Grid();
        this.mCameraFrustumAndAxis = new CameraFrustumAndAxis();
        Matrix.setIdentityM(this.mViewMatrix, 0);
        Matrix.setLookAtM(this.mViewMatrix, 0, 5.0f, 5.0f, 5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        this.mViewMatrix[0] = -1.0f;
        this.mViewMatrix[1] = 0.00234f;
        this.mViewMatrix[2] = -0.0355f;
        this.mViewMatrix[3] = 0.0f;
        this.mViewMatrix[4] = 0.0f;
        this.mViewMatrix[5] = 0.9978f;
        this.mViewMatrix[6] = 0.0657f;
        this.mViewMatrix[7] = 0.0f;
        this.mViewMatrix[8] = 0.0356f;
        this.mViewMatrix[9] = 0.06566f;
        this.mViewMatrix[10] = -0.997f;
        this.mViewMatrix[11] = 0.0f;
        this.mViewMatrix[12] = 0.0f;
        this.mViewMatrix[13] = 0.0f;
        this.mViewMatrix[14] = -1.0f;
        this.mViewMatrix[15] = 1.0f;
        this.mCameraFrustumAndAxis.setModelMatrix(getModelMatCalculator().getModelMatrix());
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        this.mCameraAspect = i / i2;
        Matrix.perspectiveM(this.mProjectionMatrix, 0, 37.8f, this.mCameraAspect, 0.01f, 200.0f);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        this.mGrid.draw(this.mViewMatrix, this.mProjectionMatrix);
        this.mPointCloud.draw(this.mViewMatrix, this.mProjectionMatrix);
        this.mCameraFrustumAndAxis.draw(this.mViewMatrix, this.mProjectionMatrix);
    }

    public PointCloud getPointCloud() {
        return this.mPointCloud;
    }
}
