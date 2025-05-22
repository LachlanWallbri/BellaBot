package com.pudutech.rgbdlib.pointcloud.tangoutils.renderables;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes5.dex */
public class CameraFrustumAndAxis extends Renderable {
    private static final int COORDS_PER_VERTEX = 3;
    private static final String sFragmentShaderCode = "precision mediump float;varying vec4 vColor;void main() {gl_FragColor = vColor;}";
    private static final String sVertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;attribute vec4 aColor;varying vec4 vColor;void main() {  vColor=aColor;gl_Position = uMVPMatrix * vPosition;}";
    private FloatBuffer mColorBuffer;
    private int mColorHandle;
    private int mMVPMatrixHandle;
    private int mPosHandle;
    private final int mProgram;
    private FloatBuffer mVertexBuffer;
    private float[] mVertices = {0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -0.4f, 0.3f, -0.5f, 0.0f, 0.0f, 0.0f, 0.4f, 0.3f, -0.5f, 0.0f, 0.0f, 0.0f, -0.4f, -0.3f, -0.5f, 0.0f, 0.0f, 0.0f, 0.4f, -0.3f, -0.5f, -0.4f, 0.3f, -0.5f, 0.4f, 0.3f, -0.5f, 0.4f, 0.3f, -0.5f, 0.4f, -0.3f, -0.5f, 0.4f, -0.3f, -0.5f, -0.4f, -0.3f, -0.5f, -0.4f, -0.3f, -0.5f, -0.4f, 0.3f, -0.5f};
    private float[] mColors = {1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    public CameraFrustumAndAxis() {
        Matrix.setIdentityM(getModelMatrix(), 0);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.mVertices.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
        this.mVertexBuffer.put(this.mVertices);
        this.mVertexBuffer.position(0);
        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(this.mColors.length * 4);
        allocateDirect2.order(ByteOrder.nativeOrder());
        this.mColorBuffer = allocateDirect2.asFloatBuffer();
        this.mColorBuffer.put(this.mColors);
        this.mColorBuffer.position(0);
        int loadShader = RenderUtils.loadShader(35633, sVertexShaderCode);
        int loadShader2 = RenderUtils.loadShader(35632, sFragmentShaderCode);
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
    }

    @Override // com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.Renderable
    public synchronized void draw(float[] fArr, float[] fArr2) {
        GLES20.glUseProgram(this.mProgram);
        updateMvpMatrix(fArr, fArr2);
        this.mPosHandle = GLES20.glGetAttribLocation(this.mProgram, "vPosition");
        GLES20.glVertexAttribPointer(this.mPosHandle, 3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, false, 0, (Buffer) this.mVertexBuffer);
        GLES20.glEnableVertexAttribArray(this.mPosHandle);
        this.mColorHandle = GLES20.glGetAttribLocation(this.mProgram, "aColor");
        GLES20.glVertexAttribPointer(this.mColorHandle, 4, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, false, 0, (Buffer) this.mColorBuffer);
        GLES20.glEnableVertexAttribArray(this.mColorHandle);
        this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, getMvpMatrix(), 0);
        GLES20.glLineWidth(3.0f);
        GLES20.glDrawArrays(1, 0, this.mVertices.length / 3);
    }
}
