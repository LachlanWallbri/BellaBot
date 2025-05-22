package com.pudutech.opengl_draw.base;

import androidx.core.util.Preconditions;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes5.dex */
public class Color {
    private float alpha;
    private float blue;
    private float green;
    private float red;

    public static Color copyOf(Color color) {
        return new Color(color.red, color.green, color.blue, color.alpha);
    }

    public static Color fromHexAndAlpha(String str, float f) {
        Preconditions.checkArgument(str.length() == 6);
        return new Color(Integer.parseInt(str.substring(0, 2), 16) / 255.0f, Integer.parseInt(str.substring(2, 4), 16) / 255.0f, Integer.parseInt(str.substring(4), 16) / 255.0f, f);
    }

    public Color(float f, float f2, float f3, float f4) {
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f);
        Preconditions.checkArgument(0.0f <= f2 && f2 <= 1.0f);
        Preconditions.checkArgument(0.0f <= f3 && f3 <= 1.0f);
        Preconditions.checkArgument(0.0f <= f4 && f4 <= 1.0f);
        this.red = f;
        this.green = f2;
        this.blue = f3;
        this.alpha = f4;
    }

    public void apply(GL10 gl10) {
        gl10.glColor4f(this.red, this.green, this.blue, this.alpha);
    }

    public float getRed() {
        return this.red;
    }

    public void setRed(float f) {
        this.red = f;
    }

    public float getGreen() {
        return this.green;
    }

    public void setGreen(float f) {
        this.green = f;
    }

    public float getBlue() {
        return this.blue;
    }

    public void setBlue(float f) {
        this.blue = f;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setAlpha(float f) {
        this.alpha = f;
    }
}
