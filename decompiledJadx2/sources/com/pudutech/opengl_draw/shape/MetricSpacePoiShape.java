package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;

/* loaded from: classes5.dex */
public class MetricSpacePoiShape extends TriangleFanShape {
    private static final Color COLOR = Color.fromHexAndAlpha("377dfa", 1.0f);
    private static final float[] VERTICES = {0.2f, 0.0f, 0.0f, -0.2f, -0.15f, 0.0f, -0.05f, 0.0f, 0.0f, -0.2f, 0.15f, 0.0f, 0.2f, 0.0f, 0.0f};

    public MetricSpacePoiShape() {
        super(VERTICES, COLOR);
    }
}
