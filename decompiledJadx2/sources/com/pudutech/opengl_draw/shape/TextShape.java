package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.Vertices;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.gltext.GLText;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class TextShape extends BaseShape {
    private final GLText glText;
    private float len;
    private FloatBuffer lines = Vertices.allocateBuffer(12);
    private final String text;

    /* renamed from: x */
    private float f6831x;

    /* renamed from: y */
    private float f6832y;

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

    public TextShape(GLText gLText, String str, float f) {
        this.glText = gLText;
        this.text = str;
        this.len = f;
    }

    public void setOffset(float f, float f2) {
        this.f6831x = f;
        this.f6832y = f2;
        this.lines.put(0.0f);
        this.lines.put(0.0f);
        this.lines.put(0.0f);
        this.lines.put(f);
        this.lines.put(f2);
        this.lines.put(0.0f);
        this.lines.put(f);
        this.lines.put(f2);
        this.lines.put(0.0f);
        this.lines.put(f + this.glText.getLength(this.text));
        this.lines.put(f2);
        this.lines.put(0.0f);
        this.lines.flip();
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape
    protected void scale(VisualizationView visualizationView, GL10 gl10) {
        gl10.glScalef(1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape
    protected void drawShape(VisualizationView visualizationView, GL10 gl10) {
        Vertices.drawLines(gl10, this.lines, getColor(), 3.0f);
        gl10.glEnable(3553);
        this.glText.begin(getColor().getRed(), getColor().getGreen(), getColor().getBlue(), getColor().getAlpha());
        this.glText.draw(this.text, this.f6831x, this.f6832y, this.len);
        this.glText.end();
        gl10.glDisable(3553);
    }
}
