package com.flask.colorpicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import com.flask.colorpicker.builder.PaintBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class CircleColorDrawable extends ColorDrawable {
    private Paint fillBackPaint;
    private Paint fillPaint;
    private Paint strokePaint;
    private float strokeWidth;

    public CircleColorDrawable() {
        this.strokePaint = PaintBuilder.newPaint().style(Paint.Style.STROKE).stroke(this.strokeWidth).color(-1).build();
        this.fillPaint = PaintBuilder.newPaint().style(Paint.Style.FILL).color(0).build();
        this.fillBackPaint = PaintBuilder.newPaint().shader(PaintBuilder.createAlphaPatternShader(16)).build();
    }

    public CircleColorDrawable(int i) {
        super(i);
        this.strokePaint = PaintBuilder.newPaint().style(Paint.Style.STROKE).stroke(this.strokeWidth).color(-1).build();
        this.fillPaint = PaintBuilder.newPaint().style(Paint.Style.FILL).color(0).build();
        this.fillBackPaint = PaintBuilder.newPaint().shader(PaintBuilder.createAlphaPatternShader(16)).build();
    }

    @Override // android.graphics.drawable.ColorDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(0);
        float width = canvas.getWidth() / 2.0f;
        float f = width / 12.0f;
        this.strokeWidth = f;
        this.strokePaint.setStrokeWidth(f);
        this.fillPaint.setColor(getColor());
        canvas.drawCircle(width, width, width - (this.strokeWidth * 1.5f), this.fillBackPaint);
        canvas.drawCircle(width, width, width - (this.strokeWidth * 1.5f), this.fillPaint);
        canvas.drawCircle(width, width, width - this.strokeWidth, this.strokePaint);
    }

    @Override // android.graphics.drawable.ColorDrawable
    public void setColor(int i) {
        super.setColor(i);
        invalidateSelf();
    }
}
