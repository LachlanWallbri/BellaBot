package com.pudutech.opengl_draw.shape;

import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.OpenGlTransform;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public abstract class BaseShape implements Shape {
    private Color color;
    private FloatBuffer floatBuffer;
    private Transform transform;
    private float[] vertices;
    private float width;

    protected abstract void drawShape(VisualizationView visualizationView, GL10 gl10);

    protected void scale(VisualizationView visualizationView, GL10 gl10) {
    }

    public BaseShape() {
        setTransform(Transform.identity());
    }

    @Override // com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        gl10.glPushMatrix();
        OpenGlTransform.apply(gl10, getTransform());
        scale(visualizationView, gl10);
        drawShape(visualizationView, gl10);
        gl10.glPopMatrix();
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public Color getColor() {
        Preconditions.checkNotNull(this.color);
        return this.color;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public void setColor(Color color) {
        this.color = color;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public Transform getTransform() {
        Preconditions.checkNotNull(this.transform);
        return this.transform;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public void setVertices(float[] fArr) {
        this.vertices = fArr;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public float[] getVertices() {
        return this.vertices;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public void setFloatBuffer(FloatBuffer floatBuffer) {
        this.floatBuffer = floatBuffer;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public FloatBuffer getFloatBuffer() {
        return this.floatBuffer;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public void setWidth(float f) {
        this.width = f;
    }

    @Override // com.pudutech.opengl_draw.shape.Shape
    public float getWidth() {
        return this.width;
    }
}
