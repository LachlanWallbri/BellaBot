package com.pudutech.lidar.preview3d.shape;

import com.pudutech.lidar.util.BufferUtil;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.FloatBuffer;
import java.util.concurrent.LinkedBlockingQueue;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Point.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0019R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/lidar/preview3d/shape/Point;", "", "()V", "cloudPointQueue", "Ljava/util/concurrent/LinkedBlockingQueue;", "", "getCloudPointQueue", "()Ljava/util/concurrent/LinkedBlockingQueue;", "setCloudPointQueue", "(Ljava/util/concurrent/LinkedBlockingQueue;)V", "mPointSize", "", ES6Iterator.VALUE_PROPERTY, "points", "getPoints", "()[F", "setPoints", "([F)V", "ponitsBuffer", "Ljava/nio/FloatBuffer;", "getPonitsBuffer", "()Ljava/nio/FloatBuffer;", "setPonitsBuffer", "(Ljava/nio/FloatBuffer;)V", "draw", "", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "updatePoint", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Point {
    private int mPointSize;
    private float[] points = new float[0];
    private LinkedBlockingQueue<float[]> cloudPointQueue = new LinkedBlockingQueue<>();
    private FloatBuffer ponitsBuffer = BufferUtil.INSTANCE.getFloatBuffer(this.points);

    public final float[] getPoints() {
        return this.points;
    }

    public final void setPoints(float[] value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.points = value;
    }

    public final FloatBuffer getPonitsBuffer() {
        return this.ponitsBuffer;
    }

    public final void setPonitsBuffer(FloatBuffer floatBuffer) {
        this.ponitsBuffer = floatBuffer;
    }

    public final LinkedBlockingQueue<float[]> getCloudPointQueue() {
        return this.cloudPointQueue;
    }

    public final void setCloudPointQueue(LinkedBlockingQueue<float[]> linkedBlockingQueue) {
        Intrinsics.checkParameterIsNotNull(linkedBlockingQueue, "<set-?>");
        this.cloudPointQueue = linkedBlockingQueue;
    }

    public final void updatePoint() {
        if (this.cloudPointQueue.size() > 0) {
            float[] floatArray = this.cloudPointQueue.poll();
            this.mPointSize = floatArray.length;
            BufferUtil.Companion companion = BufferUtil.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(floatArray, "floatArray");
            this.ponitsBuffer = companion.getFloatBuffer(floatArray);
        }
    }

    public final void draw(GL10 gl) {
        Intrinsics.checkParameterIsNotNull(gl, "gl");
        updatePoint();
        gl.glEnableClientState(32884);
        gl.glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
        gl.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.ponitsBuffer);
        gl.glPointSize(2.0f);
        gl.glDrawArrays(0, 0, this.mPointSize / 3);
        gl.glDisableClientState(32884);
    }
}
