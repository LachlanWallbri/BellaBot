package com.pudutech.rgbdlib.pointcloud.tangoutils.renderables;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes5.dex */
public class Trajectory extends Renderable {
    private static final int BYTES_PER_FLOAT = 4;
    private static final int COORDS_PER_VERTEX = 3;
    private static final int MAX_VERTICES = 60000;
    private static final float MIN_DISTANCE_CHECK = 0.025f;
    private static final String TAG = Trajectory.class.getSimpleName();
    private static int mTrajectoryCount;
    private float[] mColor;
    private int mColorHandle;
    private String mFragmentShaderCode;
    private int mLineWidth;
    private int mMVPMatrixHandle;
    private int mPosHandle;
    private final int mProgram;
    private FloatBuffer mVertexBuffer;
    private String mVertexShaderCode;

    public Trajectory(int i) {
        this.mVertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;uniform vec4 aColor;varying vec4 vColor;void main() {gl_PointSize = 5.0;vColor=aColor;gl_Position = uMVPMatrix * vPosition;}";
        this.mFragmentShaderCode = "precision mediump float;varying vec4 vColor;void main() {gl_FragColor = vColor;}";
        this.mColor = new float[]{0.22f, 0.28f, 0.67f, 1.0f};
        this.mLineWidth = i;
        Matrix.setIdentityM(getModelMatrix(), 0);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(240000);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
        int loadShader = RenderUtils.loadShader(35633, this.mVertexShaderCode);
        int loadShader2 = RenderUtils.loadShader(35632, this.mFragmentShaderCode);
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
    }

    public Trajectory(int i, float[] fArr) {
        this.mVertexShaderCode = "uniform mat4 uMVPMatrix;attribute vec4 vPosition;uniform vec4 aColor;varying vec4 vColor;void main() {gl_PointSize = 5.0;vColor=aColor;gl_Position = uMVPMatrix * vPosition;}";
        this.mFragmentShaderCode = "precision mediump float;varying vec4 vColor;void main() {gl_FragColor = vColor;}";
        this.mColor = new float[]{0.22f, 0.28f, 0.67f, 1.0f};
        this.mLineWidth = i;
        this.mColor = fArr;
        Matrix.setIdentityM(getModelMatrix(), 0);
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(240000);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
        int loadShader = RenderUtils.loadShader(35633, this.mVertexShaderCode);
        int loadShader2 = RenderUtils.loadShader(35632, this.mFragmentShaderCode);
        this.mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.mProgram, loadShader);
        GLES20.glAttachShader(this.mProgram, loadShader2);
        GLES20.glLinkProgram(this.mProgram);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void updateTrajectory(float[] fArr) {
        float f;
        float f2;
        this.mVertexBuffer.position(mTrajectoryCount * 3);
        if ((mTrajectoryCount + 1) * 3 >= 60000) {
            Log.w(TAG, "Clearing float buffer");
            resetPath();
        }
        float f3 = 0.0f;
        try {
            f = this.mVertexBuffer.get(this.mVertexBuffer.position() - 3) - fArr[0];
            try {
                f2 = this.mVertexBuffer.get(this.mVertexBuffer.position() - 2) - fArr[2];
                try {
                    f3 = this.mVertexBuffer.get(this.mVertexBuffer.position() - 1) - (-fArr[1]);
                } catch (IndexOutOfBoundsException unused) {
                    this.mVertexBuffer.put(new float[]{fArr[0], fArr[2], -fArr[1]});
                    mTrajectoryCount++;
                    if (((float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3))) <= 0.025f) {
                    }
                }
            } catch (IndexOutOfBoundsException unused2) {
                f2 = 0.0f;
            }
        } catch (IndexOutOfBoundsException unused3) {
            f = 0.0f;
            f2 = 0.0f;
        }
        if (((float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3))) <= 0.025f) {
            this.mVertexBuffer.put(new float[]{fArr[0], fArr[2], -fArr[1]});
            mTrajectoryCount++;
        }
    }

    public void resetPath() {
        this.mVertexBuffer.position(this.mVertexBuffer.position() - 20000);
        float[] fArr = new float[20000];
        this.mVertexBuffer.get(fArr, 0, 20000);
        this.mVertexBuffer.clear();
        this.mVertexBuffer.put(fArr);
        mTrajectoryCount = 6666;
    }

    public void clearPath() {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(240000);
        allocateDirect.order(ByteOrder.nativeOrder());
        this.mVertexBuffer = allocateDirect.asFloatBuffer();
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
        this.mColorHandle = GLES20.glGetUniformLocation(this.mProgram, "aColor");
        int i = this.mColorHandle;
        float[] fArr3 = this.mColor;
        GLES20.glUniform4f(i, fArr3[0], fArr3[1], fArr3[2], fArr3[3]);
        GLES20.glLineWidth(this.mLineWidth);
        GLES20.glDrawArrays(3, 0, mTrajectoryCount);
    }

    public void setColor(float[] fArr) {
        this.mColor = fArr;
    }
}
