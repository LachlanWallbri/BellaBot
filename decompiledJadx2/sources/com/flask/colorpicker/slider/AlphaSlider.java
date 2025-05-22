package com.flask.colorpicker.slider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.Utils;
import com.flask.colorpicker.builder.PaintBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class AlphaSlider extends AbsCustomSlider {
    private Paint alphaPatternPaint;
    private Paint barPaint;
    private Paint clearingStroke;
    public int color;
    private ColorPickerView colorPicker;
    private Paint solid;

    public AlphaSlider(Context context) {
        super(context);
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.barPaint = PaintBuilder.newPaint().build();
        this.solid = PaintBuilder.newPaint().build();
        this.clearingStroke = PaintBuilder.newPaint().color(-1).xPerMode(PorterDuff.Mode.CLEAR).build();
    }

    public AlphaSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.barPaint = PaintBuilder.newPaint().build();
        this.solid = PaintBuilder.newPaint().build();
        this.clearingStroke = PaintBuilder.newPaint().color(-1).xPerMode(PorterDuff.Mode.CLEAR).build();
    }

    public AlphaSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.alphaPatternPaint = PaintBuilder.newPaint().build();
        this.barPaint = PaintBuilder.newPaint().build();
        this.solid = PaintBuilder.newPaint().build();
        this.clearingStroke = PaintBuilder.newPaint().color(-1).xPerMode(PorterDuff.Mode.CLEAR).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.flask.colorpicker.slider.AbsCustomSlider
    public void createBitmaps() {
        super.createBitmaps();
        this.alphaPatternPaint.setShader(PaintBuilder.createAlphaPatternShader(this.barHeight / 2));
    }

    @Override // com.flask.colorpicker.slider.AbsCustomSlider
    protected void drawBar(Canvas canvas) {
        int width = canvas.getWidth();
        float height = canvas.getHeight();
        canvas.drawRect(0.0f, 0.0f, width, height, this.alphaPatternPaint);
        int max = Math.max(2, width / 256);
        int i = 0;
        while (i <= width) {
            float f = i;
            this.barPaint.setColor(this.color);
            this.barPaint.setAlpha(Math.round((f / (width - 1)) * 255.0f));
            i += max;
            canvas.drawRect(f, 0.0f, i, height, this.barPaint);
        }
    }

    @Override // com.flask.colorpicker.slider.AbsCustomSlider
    protected void onValueChanged(float f) {
        ColorPickerView colorPickerView = this.colorPicker;
        if (colorPickerView != null) {
            colorPickerView.setAlphaValue(f);
        }
    }

    @Override // com.flask.colorpicker.slider.AbsCustomSlider
    protected void drawHandle(Canvas canvas, float f, float f2) {
        this.solid.setColor(this.color);
        this.solid.setAlpha(Math.round(this.value * 255.0f));
        canvas.drawCircle(f, f2, this.handleRadius, this.clearingStroke);
        if (this.value < 1.0f) {
            canvas.drawCircle(f, f2, this.handleRadius * 0.75f, this.alphaPatternPaint);
        }
        canvas.drawCircle(f, f2, this.handleRadius * 0.75f, this.solid);
    }

    public void setColorPicker(ColorPickerView colorPickerView) {
        this.colorPicker = colorPickerView;
    }

    public void setColor(int i) {
        this.color = i;
        this.value = Utils.getAlphaPercent(i);
        if (this.bar != null) {
            updateBar();
            invalidate();
        }
    }
}
