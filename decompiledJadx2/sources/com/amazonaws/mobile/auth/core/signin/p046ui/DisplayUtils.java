package com.amazonaws.mobile.auth.core.signin.p046ui;

import android.content.res.Resources;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.DisplayMetrics;

/* loaded from: classes.dex */
public class DisplayUtils {
    private static final DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    private static int dpMultiplier = metrics.densityDpi / 160;

    /* renamed from: dp */
    public static int m502dp(int i) {
        return i * dpMultiplier;
    }

    public static Shape getRoundedRectangleShape(int i) {
        float f = i;
        return new RoundRectShape(new float[]{f, f, f, f, f, f, f, f}, null, null);
    }

    public static ShapeDrawable getRoundedRectangleBackground(int i, int i2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(getRoundedRectangleShape(i));
        shapeDrawable.getPaint().setColor(i2);
        return shapeDrawable;
    }
}
