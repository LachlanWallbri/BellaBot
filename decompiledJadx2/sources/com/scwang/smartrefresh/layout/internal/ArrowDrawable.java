package com.scwang.smartrefresh.layout.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ArrowDrawable extends PaintDrawable {
    private int mWidth = 0;
    private int mHeight = 0;
    private Path mPath = new Path();

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        if (this.mWidth != width || this.mHeight != height) {
            this.mPath.reset();
            float f = (width * 30) / 225;
            float f2 = f * 0.70710677f;
            float f3 = f / 0.70710677f;
            float f4 = width;
            float f5 = f4 / 2.0f;
            float f6 = height;
            this.mPath.moveTo(f5, f6);
            float f7 = f6 / 2.0f;
            this.mPath.lineTo(0.0f, f7);
            float f8 = f7 - f2;
            this.mPath.lineTo(f2, f8);
            float f9 = f / 2.0f;
            float f10 = f5 - f9;
            float f11 = (f6 - f3) - f9;
            this.mPath.lineTo(f10, f11);
            this.mPath.lineTo(f10, 0.0f);
            float f12 = f5 + f9;
            this.mPath.lineTo(f12, 0.0f);
            this.mPath.lineTo(f12, f11);
            this.mPath.lineTo(f4 - f2, f8);
            this.mPath.lineTo(f4, f7);
            this.mPath.close();
            this.mWidth = width;
            this.mHeight = height;
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
