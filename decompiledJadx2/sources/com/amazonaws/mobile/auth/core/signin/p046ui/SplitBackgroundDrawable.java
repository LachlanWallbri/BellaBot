package com.amazonaws.mobile.auth.core.signin.p046ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
public class SplitBackgroundDrawable extends Drawable {
    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private int distanceFromTopToSplitPoint;
    private Paint paint;
    private int topBackgroundColor;

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

    public SplitBackgroundDrawable(int i) {
        this.distanceFromTopToSplitPoint = -1;
        this.paint = new Paint();
        this.topBackgroundColor = -1;
        setSplitPointDistanceFromTop(i);
    }

    public SplitBackgroundDrawable(int i, int i2) {
        this.distanceFromTopToSplitPoint = -1;
        this.paint = new Paint();
        this.topBackgroundColor = i2;
        setSplitPointDistanceFromTop(i);
    }

    public void setSplitPointDistanceFromTop(int i) {
        this.distanceFromTopToSplitPoint = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.paint.setColor(this.topBackgroundColor);
        float height = this.distanceFromTopToSplitPoint < bounds.height() ? this.distanceFromTopToSplitPoint : bounds.height();
        canvas.drawRect(0.0f, 0.0f, bounds.width(), height, this.paint);
        this.paint.setColor(-1);
        canvas.drawRect(0.0f, height, bounds.width(), bounds.height(), this.paint);
    }
}
