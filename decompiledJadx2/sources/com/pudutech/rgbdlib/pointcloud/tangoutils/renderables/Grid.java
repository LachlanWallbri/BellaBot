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
public class Grid extends Renderable {
    private static final int BYTES_PER_FLOAT = 4;
    private static final int COORDS_PER_VERTEX = 3;
    private static final int GRID_RANGE_M = 3;
    private static final String sFragmentShaderCode = "precision mediump float;uniform vec4 vColor;void main() { gl_FragColor = vec4(0.8,0.8,0.8,1.0);}";
    private static final String sVertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;void main() {gl_Position = uMVPMatrix * vPosition;}";
    private int mMVPMatrixHandle;
    private int mPosHandle;
    private final int mProgram;
    private FloatBuffer mVertexBuffer;

    public Grid() {
        Matrix.setIdentityM(getModelMatrix(), 0);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(336);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
        for (int i = -3; i <= 3; i++) {
            float f = i;
            this.mVertexBuffer.put(new float[]{f, -1.3f, -3.0f});
            this.mVertexBuffer.put(new float[]{f, -1.3f, 3.0f});
        }
        for (int i2 = -3; i2 <= 3; i2++) {
            float f2 = i2;
            this.mVertexBuffer.put(new float[]{-3.0f, -1.3f, f2});
            this.mVertexBuffer.put(new float[]{3.0f, -1.3f, f2});
        }
        int loadShader = RenderUtils.loadShader(35633, sVertexShaderCode);
        int loadShader2 = RenderUtils.loadShader(35632, sFragmentShaderCode);
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
    }

    @Override // com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.Renderable
    public void draw(float[] fArr, float[] fArr2) {
        GLES20.glUseProgram(this.mProgram);
        this.mVertexBuffer.position(0);
        updateMvpMatrix(fArr, fArr2);
        this.mPosHandle = GLES20.glGetAttribLocation(this.mProgram, "vPosition");
        GLES20.glVertexAttribPointer(this.mPosHandle, 3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, false, 0, (Buffer) this.mVertexBuffer);
        GLES20.glEnableVertexAttribArray(this.mPosHandle);
        this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, getMvpMatrix(), 0);
        GLES20.glLineWidth(1.0f);
        GLES20.glDrawArrays(1, 0, 28);
    }
}
