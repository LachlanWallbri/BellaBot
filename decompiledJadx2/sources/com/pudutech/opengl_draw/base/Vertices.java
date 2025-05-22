package com.pudutech.opengl_draw.base;

import androidx.core.util.Preconditions;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class Vertices {
    private static final int FLOAT_BYTE_SIZE = 4;

    private Vertices() {
    }

    public static FloatBuffer allocateBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asFloatBuffer();
    }

    public static FloatBuffer toFloatBuffer(float[] fArr) {
        FloatBuffer allocateBuffer = allocateBuffer(fArr.length);
        allocateBuffer.put(fArr);
        allocateBuffer.position(0);
        return allocateBuffer;
    }

    public static void drawPoints(GL10 gl10, FloatBuffer floatBuffer, Color color, float f) {
        floatBuffer.mark();
        color.apply(gl10);
        gl10.glPointSize(f);
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, floatBuffer);
        gl10.glDrawArrays(0, 0, countVertices(floatBuffer, 3));
        gl10.glDisableClientState(32884);
        floatBuffer.reset();
    }

    public static void drawTriangleFan(GL10 gl10, FloatBuffer floatBuffer, Color color) {
        floatBuffer.mark();
        color.apply(gl10);
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, floatBuffer);
        gl10.glDrawArrays(6, 0, countVertices(floatBuffer, 3));
        gl10.glDisableClientState(32884);
        floatBuffer.reset();
    }

    public static void drawLineLoop(GL10 gl10, FloatBuffer floatBuffer, Color color, float f) {
        floatBuffer.mark();
        color.apply(gl10);
        gl10.glLineWidth(f);
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, floatBuffer);
        gl10.glDrawArrays(2, 0, countVertices(floatBuffer, 3));
        gl10.glDisableClientState(32884);
        floatBuffer.reset();
    }

    public static void drawLineDotted(GL10 gl10, FloatBuffer floatBuffer, Color color, float f) {
        floatBuffer.mark();
        color.apply(gl10);
        gl10.glLineWidth(f);
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, floatBuffer);
        gl10.glDrawArrays(1, 0, countVertices(floatBuffer, 3));
        gl10.glDisableClientState(32884);
        floatBuffer.reset();
    }

    public static void drawLines(GL10 gl10, FloatBuffer floatBuffer, Color color, float f) {
        floatBuffer.mark();
        color.apply(gl10);
        gl10.glLineWidth(f);
        gl10.glEnableClientState(32884);
        gl10.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, floatBuffer);
        gl10.glDrawArrays(3, 0, countVertices(floatBuffer, 3));
        gl10.glDisableClientState(32884);
        floatBuffer.reset();
    }

    private static int countVertices(FloatBuffer floatBuffer, int i) {
        Preconditions.checkArgument(floatBuffer.remaining() % i == 0, "Number of vertices: " + floatBuffer.remaining());
        return floatBuffer.remaining() / i;
    }
}
