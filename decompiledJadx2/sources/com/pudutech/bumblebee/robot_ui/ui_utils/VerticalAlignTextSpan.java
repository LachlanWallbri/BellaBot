package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VerticalAlignTextSpan.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005JR\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0002J4\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VerticalAlignTextSpan;", "Landroid/text/style/ReplacementSpan;", "fontSizeSp", "", TypedValues.Custom.S_COLOR, "(II)V", "getColor", "()I", "setColor", "(I)V", "getFontSizeSp", "setFontSizeSp", "draw", "", "canvas", "Landroid/graphics/Canvas;", "text", "", "start", "end", "x", "", "top", "y", "bottom", "paint", "Landroid/graphics/Paint;", "getCustomTextPaint", "Landroid/text/TextPaint;", "srcPaint", "getSize", "fm", "Landroid/graphics/Paint$FontMetricsInt;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VerticalAlignTextSpan extends ReplacementSpan {
    private int color;
    private int fontSizeSp;

    public VerticalAlignTextSpan(int i, int i2) {
        this.fontSizeSp = -1;
        this.color = -7829368;
        this.color = i2;
        this.fontSizeSp = i;
    }

    public final int getFontSizeSp() {
        return this.fontSizeSp;
    }

    public final void setFontSizeSp(int i) {
        this.fontSizeSp = i;
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Intrinsics.checkParameterIsNotNull(paint, "paint");
        return (int) getCustomTextPaint(paint).measureText(text, start, end);
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(paint, "paint");
        TextPaint customTextPaint = getCustomTextPaint(paint);
        Paint.FontMetricsInt fontMetricsInt = customTextPaint.getFontMetricsInt();
        int i = ((((y + fontMetricsInt.ascent) + y) + fontMetricsInt.descent) / 2) - ((top + bottom) / 2);
        Log.d("VerticalAlignTextSpan", "offsetY-> " + i);
        canvas.drawText(text, start, end, x, (float) (y - i), customTextPaint);
    }

    private final TextPaint getCustomTextPaint(Paint srcPaint) {
        TextPaint textPaint = new TextPaint(srcPaint);
        textPaint.setColor(this.color);
        int i = this.fontSizeSp;
        if (i == -1) {
            textPaint.setTextSize(i * textPaint.density);
        }
        return textPaint;
    }
}
