package com.pudutech.lidar.preview3d.shape;

import com.pudutech.lidar.util.BufferUtil;
import com.slamtec.slamware.robot.HealthInfo;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Circle.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/lidar/preview3d/shape/Circle;", "", "()V", "TAG", "", "verBuffer100", "Ljava/nio/FloatBuffer;", "verBuffer300", "verBuffer50", ES6Iterator.VALUE_PROPERTY, "", "yAngle", "getYAngle", "()F", "setYAngle", "(F)V", "zAngle", "getZAngle", "setZAngle", "DegToRad", "deg", "draw", "", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "initCircleBuffer", "size", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Circle {
    private float yAngle;
    private float zAngle;
    private final String TAG = "Circle";
    private FloatBuffer verBuffer50 = initCircleBuffer(1);
    private FloatBuffer verBuffer100 = initCircleBuffer(2);
    private FloatBuffer verBuffer300 = initCircleBuffer(6);

    public final float DegToRad(float deg) {
        return (float) ((deg * 3.141592653589793d) / 180.0d);
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

    private final FloatBuffer initCircleBuffer(int size) {
        float[] fArr = new float[720];
        for (int i = 0; i < 720; i += 2) {
            float f = i;
            double d = size;
            fArr[i] = (float) (Math.cos(DegToRad(f)) * d);
            fArr[i + 1] = (float) (Math.sin(DegToRad(f)) * d);
        }
        return BufferUtil.INSTANCE.getFloatBuffer(fArr);
    }

    public final void draw(GL10 gl) {
        Intrinsics.checkParameterIsNotNull(gl, "gl");
        gl.glLoadIdentity();
        gl.glEnableClientState(32884);
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glRotatef(this.yAngle, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(this.zAngle, 1.0f, 0.0f, 0.0f);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glVertexPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.verBuffer50);
        gl.glDrawArrays(1, 0, 360);
        gl.glVertexPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.verBuffer100);
        gl.glDrawArrays(1, 0, 360);
        gl.glVertexPointer(2, HealthInfo.BaseError.BaseComponentErrorTypeSensorDustbinGone, 0, this.verBuffer300);
        gl.glDrawArrays(1, 0, 360);
        gl.glDisableClientState(32884);
    }
}
