package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes4.dex */
public class MarqueeTextView extends AppCompatTextView {
    public static final int INVALIDATE_STEP = 1;
    public static final int INVALIDATE_TIME = 15;
    public static final int WAIT_TIME = 1500;
    private String drawingText;
    public boolean exitFlag;
    private Handler mHandler;
    private String mText;
    private Runnable moveRun;
    private TextPaint paint;
    private float posX;
    private float posY;

    /* renamed from: rf */
    private Rect f4955rf;
    private String space;
    private float spaceTextWidth;
    private float textWidth;
    private int width;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.space = "              ";
        this.drawingText = "";
        this.exitFlag = true;
        this.posX = 0.0f;
        this.posY = 50.0f;
        this.f4955rf = new Rect();
        this.mHandler = new Handler();
        this.moveRun = new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MarqueeTextView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MarqueeTextView.this.width >= MarqueeTextView.this.textWidth) {
                    return;
                }
                MarqueeTextView.this.drawingText = MarqueeTextView.this.mText + MarqueeTextView.this.space + MarqueeTextView.this.mText;
                MarqueeTextView marqueeTextView = MarqueeTextView.this;
                marqueeTextView.posX = marqueeTextView.posX - 1.0f;
                if (MarqueeTextView.this.posX >= -0.5f && MarqueeTextView.this.posX <= 0.5f) {
                    MarqueeTextView.this.mHandler.postDelayed(this, 1500L);
                    MarqueeTextView.this.invalidate();
                    return;
                }
                if (MarqueeTextView.this.posX < (MarqueeTextView.this.textWidth * (-1.0f)) - MarqueeTextView.this.spaceTextWidth) {
                    MarqueeTextView.this.posX = 1.0f;
                }
                MarqueeTextView.this.invalidate();
                if (!MarqueeTextView.this.exitFlag) {
                    MarqueeTextView.this.mHandler.postDelayed(this, 15L);
                } else {
                    MarqueeTextView.this.posX = 0.0f;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(i, i2);
        if (!this.exitFlag) {
            stopMove();
        }
        this.mText = getText().toString();
        this.drawingText = this.mText;
        this.paint = getPaint();
        layoutView();
        int i3 = this.width;
        if (i3 <= 0 || i3 >= this.textWidth) {
            return;
        }
        startMove();
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        this.mText = getText().toString();
        this.drawingText = this.mText;
    }

    private void layoutView() {
        this.width = getMeasuredWidth();
        this.textWidth = this.paint.measureText(this.mText);
        TextPaint textPaint = this.paint;
        String str = this.mText;
        textPaint.getTextBounds(str, 0, str.length(), this.f4955rf);
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        this.posY = ((getMeasuredHeight() - fontMetrics.bottom) - fontMetrics.top) / 2.0f;
        int colorForState = getTextColors().getColorForState(getDrawableState(), 0);
        this.spaceTextWidth = this.paint.measureText(this.space);
        TextPaint textPaint2 = this.paint;
        if (textPaint2 != null) {
            textPaint2.setColor(colorForState);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.save();
        String str = this.drawingText;
        canvas.drawText(str, 0, str.length(), this.posX, this.posY, (Paint) this.paint);
        canvas.restore();
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        int colorForState = getTextColors().getColorForState(getDrawableState(), 0);
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setColor(colorForState);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopMove();
        this.posX = 0.0f;
        this.width = 0;
    }

    private void stopMove() {
        this.exitFlag = true;
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void startMove() {
        if (this.exitFlag) {
            this.exitFlag = false;
            this.mHandler.post(this.moveRun);
        }
    }
}
