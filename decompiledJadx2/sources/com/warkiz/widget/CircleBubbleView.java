package com.warkiz.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CircleBubbleView extends View {
    private Context mContext;
    private int mIndicatorColor;
    private float mIndicatorHeight;
    private int mIndicatorTextColor;
    private float mIndicatorTextSize;
    private float mIndicatorWidth;
    private Paint mPaint;
    private Path mPath;
    private String mProgress;
    private float mTextHeight;

    CircleBubbleView(Context context) {
        this(context, null);
    }

    CircleBubbleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    CircleBubbleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init("100");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircleBubbleView(Context context, float f, int i, int i2, String str) {
        super(context, null, 0);
        this.mContext = context;
        this.mIndicatorTextSize = f;
        this.mIndicatorTextColor = i;
        this.mIndicatorColor = i2;
        init(str);
    }

    private void init(String str) {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(this.mIndicatorTextSize);
        this.mPaint.getTextBounds(str, 0, str.length(), new Rect());
        this.mIndicatorWidth = r0.width() + SizeUtils.dp2px(this.mContext, 4.0f);
        float dp2px = SizeUtils.dp2px(this.mContext, 36.0f);
        if (this.mIndicatorWidth < dp2px) {
            this.mIndicatorWidth = dp2px;
        }
        this.mTextHeight = r0.height();
        this.mIndicatorHeight = this.mIndicatorWidth * 1.2f;
        initPath();
    }

    private void initPath() {
        this.mPath = new Path();
        float f = this.mIndicatorWidth;
        this.mPath.arcTo(new RectF(0.0f, 0.0f, f, f), 135.0f, 270.0f);
        this.mPath.lineTo(this.mIndicatorWidth / 2.0f, this.mIndicatorHeight);
        this.mPath.close();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension((int) this.mIndicatorWidth, (int) this.mIndicatorHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mPaint.setColor(this.mIndicatorColor);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPaint.setColor(this.mIndicatorTextColor);
        canvas.drawText(this.mProgress, this.mIndicatorWidth / 2.0f, (this.mIndicatorHeight / 2.0f) + (this.mTextHeight / 4.0f), this.mPaint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProgress(String str) {
        this.mProgress = str;
        invalidate();
    }
}
