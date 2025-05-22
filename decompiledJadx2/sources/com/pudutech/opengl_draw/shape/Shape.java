package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.OpenGlDrawable;
import com.pudutech.opengl_draw.geometry.Transform;
import java.nio.FloatBuffer;

/* loaded from: classes5.dex */
public interface Shape extends OpenGlDrawable {
    Color getColor();

    FloatBuffer getFloatBuffer();

    Transform getTransform();

    float[] getVertices();

    float getWidth();

    void setColor(Color color);

    void setFloatBuffer(FloatBuffer floatBuffer);

    void setTransform(Transform transform);

    void setVertices(float[] fArr);

    void setWidth(float f);
}
