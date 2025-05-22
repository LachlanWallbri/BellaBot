package com.pudutech.rgbdlib.pointcloud.tangoutils.renderables;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes5.dex */
public class PointCloud extends Renderable {
    private static final int BYTES_PER_FLOAT = 4;
    private static final int COORDS_PER_VERTEX = 4;
    private static final int POINT_TO_XYZ = 3;
    private static final String sFragmentShaderCode = "precision mediump float;varying vec4 vColor;void main() {  gl_FragColor = vec4(vColor);}";
    private static final String sVertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;varying vec4 vColor;void main() {gl_PointSize = 5.0; float v = vPosition[3];vec4 vPosition1 = vPosition; vPosition1[3] = 1.0; vPosition1[0] = vPosition1[0]/1000.0;vPosition1[1] = vPosition1[1]/1000.0;vPosition1[2] = vPosition1[2]/1000.0;gl_Position = uMVPMatrix * vPosition1;if(v == 0.0) vColor = vec4(0,255,0,255);if(v == 1.0) vColor = vec4(255,0,0,255);if(v == 2.0) vColor = vec4(0,0,255,255);}";
    private int mMVPMatrixHandle;
    private int mPointCount;
    private int mPosHandle;
    private final int mProgram;
    private FloatBuffer mVertexBuffer;
    private FloatBuffer[] mVertexBufferArray;
    float[] xyz;
    private int xyzlength = 60000;
    private float mAverageZ = 0.0f;

    public PointCloud(int i) {
        int loadShader = RenderUtils.loadShader(35633, sVertexShaderCode);
        int loadShader2 = RenderUtils.loadShader(35632, sFragmentShaderCode);
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
        Matrix.setIdentityM(getModelMatrix(), 0);
        this.mVertexBuffer = ByteBuffer.allocateDirect(i * 4 * 3).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.xyz = new float[this.xyzlength];
        this.mVertexBuffer.limit(this.mVertexBuffer.capacity());
    }

    public synchronized void UpdatePoints(byte[] bArr, int i) {
        FloatBuffer asFloatBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mPointCount = i;
        this.mVertexBuffer.clear();
        this.mVertexBuffer.position(0);
        this.mVertexBuffer.put(asFloatBuffer);
        float f = 0.0f;
        for (int i2 = 0; i2 < asFloatBuffer.capacity() - 3; i2 += 3) {
            f += asFloatBuffer.get(i2 + 2);
        }
        this.mAverageZ = f / this.mPointCount;
    }

    public synchronized void UpdatePoints(float[] fArr) {
        int i = ((int) fArr[0]) - 8;
        this.mPointCount = i;
        int i2 = i * 4;
        Pdlog.m3273d("CloudPoint", "Size:", Integer.valueOf(i2));
        if (i2 > this.xyzlength) {
            i2 = this.xyzlength;
        }
        Log.d("CloudPoint.size:", String.valueOf(this.mPointCount));
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4 + 1;
            int i6 = 33 + i3;
            this.xyz[i4] = fArr[i6 + 0];
            int i7 = i5 + 1;
            this.xyz[i5] = fArr[i6 + 1];
            int i8 = i7 + 1;
            this.xyz[i7] = fArr[i6 + 2];
            this.xyz[i8] = fArr[i6 + 3];
            i3 += 4;
            i4 = i8 + 1;
        }
        Log.d("CloudPoint.size:", String.valueOf(this.mPointCount));
        Log.d("CloudPoint.nd:", String.valueOf(i2));
        FloatBuffer.wrap(this.xyz, 0, i2);
        this.mVertexBuffer.position(0);
        Log.d("CloudPoint.remaining:", String.valueOf(this.mVertexBuffer.remaining()));
        this.mVertexBuffer.put(this.xyz, 0, i2);
        float f = 0.0f;
        for (int i9 = 0; i9 < this.xyz.length - 4; i9 += 4) {
            f += this.xyz[i9 + 2];
        }
        this.mAverageZ = f / this.mPointCount;
    }

    @Override // com.pudutech.rgbdlib.pointcloud.tangoutils.renderables.Renderable
    public synchronized void draw(float[] fArr, float[] fArr2) {
        if (this.mPointCount > 0) {
            this.mVertexBuffer.position(0);
            GLES20.glUseProgram(this.mProgram);
            updateMvpMatrix(fArr, fArr2);
            this.mPosHandle = GLES20.glGetAttribLocation(this.mProgram, "vPosition");
            GLES20.glVertexAttribPointer(this.mPosHandle, 4, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, false, 0, (Buffer) this.mVertexBuffer);
            GLES20.glEnableVertexAttribArray(this.mPosHandle);
            this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, "uMVPMatrix");
            GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, getMvpMatrix(), 0);
            GLES20.glDrawArrays(0, 0, this.mPointCount);
        }
    }

    public float getAverageZ() {
        return this.mAverageZ;
    }

    public int getPointCount() {
        return this.mPointCount;
    }
}
