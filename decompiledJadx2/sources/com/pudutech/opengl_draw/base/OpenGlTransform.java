package com.pudutech.opengl_draw.base;

import com.pudutech.opengl_draw.geometry.Transform;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class OpenGlTransform {
    private static final ThreadLocal<FloatBuffer> buffer = new ThreadLocal<FloatBuffer>() { // from class: com.pudutech.opengl_draw.base.OpenGlTransform.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public FloatBuffer initialValue() {
            return FloatBuffer.allocate(16);
        }

        @Override // java.lang.ThreadLocal
        public FloatBuffer get() {
            FloatBuffer floatBuffer = (FloatBuffer) super.get();
            floatBuffer.clear();
            return floatBuffer;
        }
    };

    private OpenGlTransform() {
    }

    public static void apply(GL10 gl10, Transform transform) {
        FloatBuffer floatBuffer = buffer.get();
        for (double d : transform.toMatrix()) {
            floatBuffer.put((float) d);
        }
        floatBuffer.position(0);
        gl10.glMultMatrixf(floatBuffer);
    }
}
