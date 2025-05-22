package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.Vertices;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class DottedLineShape extends BaseShape {
    private FloatBuffer vertices;

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.base.OpenGlDrawable
    public /* bridge */ /* synthetic */ void draw(VisualizationView visualizationView, GL10 gl10) {
        super.draw(visualizationView, gl10);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ Color getColor() {
        return super.getColor();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ FloatBuffer getFloatBuffer() {
        return super.getFloatBuffer();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ Transform getTransform() {
        return super.getTransform();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ float[] getVertices() {
        return super.getVertices();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ float getWidth() {
        return super.getWidth();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ void setColor(Color color) {
        super.setColor(color);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ void setFloatBuffer(FloatBuffer floatBuffer) {
        super.setFloatBuffer(floatBuffer);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ void setTransform(Transform transform) {
        super.setTransform(transform);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ void setVertices(float[] fArr) {
        super.setVertices(fArr);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape, com.pudutech.opengl_draw.shape.Shape
    public /* bridge */ /* synthetic */ void setWidth(float f) {
        super.setWidth(f);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape
    protected void drawShape(VisualizationView visualizationView, GL10 gl10) {
        Vertices.drawLineDotted(gl10, getFloatBuffer(), getColor(), 4.0f);
    }
}
