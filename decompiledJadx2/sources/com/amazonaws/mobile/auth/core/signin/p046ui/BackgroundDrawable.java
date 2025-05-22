package com.amazonaws.mobile.auth.core.signin.p046ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class BackgroundDrawable extends Drawable {
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private int backgroundColor;
    private Paint paint;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public BackgroundDrawable() {
        this.paint = new Paint();
        this.backgroundColor = -1;
    }

    public BackgroundDrawable(int i) {
        this.paint = new Paint();
        this.backgroundColor = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.paint.setColor(this.backgroundColor);
        canvas.drawRect(0.0f, 0.0f, bounds.width(), bounds.height(), this.paint);
    }
}
