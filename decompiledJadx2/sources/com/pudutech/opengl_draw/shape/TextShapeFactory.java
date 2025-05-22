package com.pudutech.opengl_draw.shape;

import android.graphics.Typeface;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.gltext.GLText;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class TextShapeFactory {
    private final GLText glText;

    public TextShapeFactory(VisualizationView visualizationView, GL10 gl10, String str) {
        this.glText = new GLText(gl10, visualizationView.getContext().getAssets(), str);
    }

    public void loadFont(Typeface typeface, int i, int i2, int i3) {
        this.glText.load(typeface, i, i2, i3);
    }

    public void loadFont(String str, int i, int i2, int i3) {
        this.glText.load(str, i, i2, i3);
    }

    public TextShape newTextShape(String str, float f) {
        return new TextShape(this.glText, str, f);
    }
}
