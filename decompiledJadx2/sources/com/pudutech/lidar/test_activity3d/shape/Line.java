package com.pudutech.lidar.test_activity3d.shape;

import com.pudutech.lidar.util.BufferUtil;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Line.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006!"}, m3961d2 = {"Lcom/pudutech/lidar/test_activity3d/shape/Line;", "", "()V", "arrowX1", "", "arrowX2", "arrowY1", "arrowY2", "arrowZ1", "arrowZ2", "lineX", "lineY", "lineZ", "unitArrowSize", "", "unitLineSize", "vertexBuffer", "Ljava/nio/FloatBuffer;", "vertices", "", "yAngle", "getYAngle", "()F", "setYAngle", "(F)V", "zAngle", "getZAngle", "setZAngle", "draw", "", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "drawlines", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Line {
    private final byte[] arrowX1;
    private final byte[] arrowX2;
    private final byte[] arrowY1;
    private final byte[] arrowY2;
    private final byte[] arrowZ1;
    private final byte[] arrowZ2;
    private final byte[] lineX;
    private final byte[] lineY;
    private final byte[] lineZ;
    private FloatBuffer vertexBuffer;
    private final float[] vertices;
    private float yAngle;
    private float zAngle;
    private final float unitLineSize = 15.0f;
    private final float unitArrowSize = 0.2f;

    public Line() {
        float f = this.unitLineSize;
        float f2 = this.unitArrowSize;
        this.vertices = new float[]{f, 0.0f, 0.0f, -f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, -f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, -f, f, 0.0f, 0.0f, f - f2, f2, 0.0f, f - f2, -f2, 0.0f, 0.0f, f, 0.0f, -f2, f - f2, 0.0f, f2, f - f2, 0.0f, 0.0f, 0.0f, f, -f2, 0.0f, f - f2, f2, 0.0f, f - f2};
        this.lineX = new byte[]{0, 1};
        this.lineY = new byte[]{2, 3};
        this.lineZ = new byte[]{4, 5};
        this.arrowX1 = new byte[]{6, 7};
        this.arrowX2 = new byte[]{6, 8};
        this.arrowY1 = new byte[]{9, 10};
        this.arrowY2 = new byte[]{9, 11};
        this.arrowZ1 = new byte[]{12, 13};
        this.arrowZ2 = new byte[]{12, 14};
        this.vertexBuffer = BufferUtil.INSTANCE.getFloatBuffer(this.vertices);
    }

    public final float getYAngle() {
        return this.yAngle;
    }

    public final void setYAngle(float f) {
        this.yAngle = f;
    }

    public final float getZAngle() {
        return this.zAngle;
    }

    public final void setZAngle(float f) {
        this.zAngle = f;
    }

    public final void draw(GL10 gl) {
        Intrinsics.checkParameterIsNotNull(gl, "gl");
        gl.glEnableClientState(32884);
        gl.glRotatef(this.yAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(this.zAngle, 1.0f, 0.0f, 0.0f);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        gl.glVertexPointer(3, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.vertexBuffer);
        drawlines(gl);
        gl.glDisableClientState(32884);
    }

    private final void drawlines(GL10 gl) {
        gl.glLineWidth(2.0f);
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.lineX));
        gl.glLineWidth(2.0f);
        gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.lineY));
        gl.glLineWidth(2.0f);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 0.0f);
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.lineZ));
        gl.glLineWidth(2.0f);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowX1));
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowX2));
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowY1));
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowY2));
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowZ1));
        gl.glDrawElements(2, 2, HealthInfo.BaseError.BaseComponentErrorTypeSensorControllerDown, ByteBuffer.wrap(this.arrowZ2));
    }
}
