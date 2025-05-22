package com.flask.colorpicker;

import android.graphics.Color;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class Utils {
    public static float getAlphaPercent(int i) {
        return Color.alpha(i) / 255.0f;
    }

    public static int alphaValueAsInt(float f) {
        return Math.round(f * 255.0f);
    }

    public static int adjustAlpha(float f, int i) {
        return (alphaValueAsInt(f) << 24) | (i & 16777215);
    }

    public static int colorAtLightness(int i, float f) {
        Color.colorToHSV(i, r0);
        float[] fArr = {0.0f, 0.0f, f};
        return Color.HSVToColor(fArr);
    }

    public static float lightnessOfColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return fArr[2];
    }

    public static String getHexString(int i, boolean z) {
        return String.format(z ? "#%08X" : "#%06X", Integer.valueOf(i & (z ? -1 : 16777215))).toUpperCase();
    }
}
