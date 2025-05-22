package com.pudutech.mirsdk.mircore.p057ui.speedlevel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public class SectionSeekBar extends View {
    private static final int BAR_HEIGHT = 4;
    private static final int CIRCLE_INNER_COLOR = -16777216;
    private static final int CIRCLE_INNER_HIGHLIGHT_RADIUS = 10;
    private static final int CIRCLE_INNER_RADIUS = 6;
    private static final int CIRCLE_OUTER_COLOR = -1;
    private static final int CIRCLE_OUTER_RADIUS = 8;
    private static final int SECTION_SIZE = 5;
    private int highlightIdx;
    private final Paint paint;
    private int sectionSize;
    private static final int BAR_COLOR = Color.parseColor("#009688");
    private static final int CIRCLE_INNER_HIGHLIGHT_COLOR = Color.parseColor("#00A0E6");

    public SectionSeekBar(Context context) {
        this(context, null);
    }

    public SectionSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.highlightIdx = -1;
        this.sectionSize = 5;
        this.paint = new Paint(1);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        Rect clipBounds = canvas.getClipBounds();
        clipBounds.inset(-10, -10);
        canvas.clipRect(clipBounds, Region.Op.REPLACE);
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        this.paint.setColor(BAR_COLOR);
        float f = paddingTop;
        float f2 = paddingStart;
        float dp2px = (int) ((dp2px(8) + f) - (dp2px(4) / 2.0f));
        canvas.drawRect(f2, dp2px, (getWidth() - paddingStart) - getPaddingEnd(), dp2px + dp2px(4), this.paint);
        float dp2px2 = dp2px(8);
        float width = ((((getWidth() - paddingStart) - getPaddingEnd()) - (2.0f * dp2px2)) * 1.0f) / (this.sectionSize - 1);
        float dp2px3 = dp2px(6);
        int i = 0;
        while (i < this.sectionSize) {
            this.paint.setColor(-1);
            float f3 = f2 + dp2px2;
            float f4 = f + dp2px2;
            canvas.drawCircle(f3, f4, dp2px2, this.paint);
            this.paint.setColor(this.highlightIdx == i ? CIRCLE_INNER_HIGHLIGHT_COLOR : -16777216);
            canvas.drawCircle(f3, f4, i == this.highlightIdx ? dp2px(10) : dp2px3, this.paint);
            f2 += width;
            i++;
        }
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize((int) ((dp2px(8) * 2.0f * this.sectionSize) + getPaddingStart() + getPaddingEnd()), i), resolveSize((int) ((dp2px(8) * 2.0f) + getPaddingTop() + getPaddingBottom()), i2));
    }

    private float dp2px(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    public int getHighlightIdx() {
        return this.highlightIdx;
    }

    public void setHighlightIdx(int i) {
        if (this.highlightIdx != i) {
            this.highlightIdx = i;
            invalidate();
        }
    }

    public int getSectionSize() {
        return this.sectionSize;
    }

    public void setSectionSize(int i) {
        this.sectionSize = i;
        invalidate();
    }
}
