package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;
import com.pudutech.opengl_draw.base.VisualizationView;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class SquareShape extends LineShape {
    private static final float PIXELS_PER_METER = 100.0f;
    private static final Color COLOR = Color.fromHexAndAlpha("377dfa", 1.0f);
    private static final float[] VERTICES = {0.1f, 0.1f, 0.0f, -0.1f, 0.1f, 0.0f, -0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f};

    public SquareShape() {
        super(VERTICES, COLOR);
    }

    @Override // com.pudutech.opengl_draw.shape.BaseShape
    protected void scale(VisualizationView visualizationView, GL10 gl10) {
        gl10.glScalef(PIXELS_PER_METER, PIXELS_PER_METER, 1.0f);
        gl10.glScalef(1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f / ((float) visualizationView.getCamera().getZoom()), 1.0f);
    }
}
