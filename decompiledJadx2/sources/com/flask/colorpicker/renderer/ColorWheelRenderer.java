package com.flask.colorpicker.renderer;

import com.flask.colorpicker.ColorCircle;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public interface ColorWheelRenderer {
    public static final float GAP_PERCENTAGE = 0.025f;

    void draw();

    List<ColorCircle> getColorCircleList();

    ColorWheelRenderOption getRenderOption();

    void initWith(ColorWheelRenderOption colorWheelRenderOption);
}
