package com.pudutech.peanut.robot_ui.p063ui.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

/* loaded from: classes5.dex */
public class VerticalCenterSpan extends ReplacementSpan {
    private float fontSizePx;

    public VerticalCenterSpan(float f) {
        this.fontSizePx = f;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return (int) getCustomTextPaint(paint).measureText(charSequence.subSequence(i, i2).toString());
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        CharSequence subSequence = charSequence.subSequence(i, i2);
        TextPaint customTextPaint = getCustomTextPaint(paint);
        customTextPaint.setTypeface(Typeface.DEFAULT);
        Paint.FontMetricsInt fontMetricsInt = customTextPaint.getFontMetricsInt();
        canvas.drawText(subSequence.toString(), f, i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2)), customTextPaint);
    }

    private TextPaint getCustomTextPaint(Paint paint) {
        TextPaint textPaint = new TextPaint(paint);
        textPaint.setTextSize(this.fontSizePx);
        return textPaint;
    }
}
