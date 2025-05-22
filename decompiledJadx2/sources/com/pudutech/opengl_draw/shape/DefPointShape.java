package com.pudutech.opengl_draw.shape;

import com.pudutech.opengl_draw.base.Color;

/* loaded from: classes5.dex */
public class DefPointShape extends PointShape {
    private static final Color COLOR = Color.fromHexAndAlpha("377dfa", 1.0f);
    private static final float[] VERTICES = {0.0f, 0.0f, 0.0f};

    public DefPointShape() {
        super(VERTICES, COLOR);
    }
}
