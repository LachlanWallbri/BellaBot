package com.scwang.smartrefresh.layout.footer;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.core.graphics.ColorUtils;
import com.scwang.smartrefresh.layout.C5778R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.SmartUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class BallPulseFooter extends InternalAbstract implements RefreshFooter {
    protected int mAnimatingColor;
    protected float mCircleSpacing;
    protected TimeInterpolator mInterpolator;
    protected boolean mIsStarted;
    protected boolean mManualAnimationColor;
    protected boolean mManualNormalColor;
    protected int mNormalColor;
    protected Paint mPaint;
    protected long mStartTime;

    public BallPulseFooter(Context context) {
        this(context, null);
    }

    public BallPulseFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BallPulseFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNormalColor = -1118482;
        this.mAnimatingColor = -1615546;
        this.mStartTime = 0L;
        this.mIsStarted = false;
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        setMinimumHeight(SmartUtil.dp2px(60.0f));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5778R.styleable.BallPulseFooter);
        this.mPaint = new Paint();
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mSpinnerStyle = SpinnerStyle.Translate;
        this.mSpinnerStyle = SpinnerStyle.values[obtainStyledAttributes.getInt(C5778R.styleable.BallPulseFooter_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal)];
        if (obtainStyledAttributes.hasValue(C5778R.styleable.BallPulseFooter_srlNormalColor)) {
            setNormalColor(obtainStyledAttributes.getColor(C5778R.styleable.BallPulseFooter_srlNormalColor, 0));
        }
        if (obtainStyledAttributes.hasValue(C5778R.styleable.BallPulseFooter_srlAnimatingColor)) {
            setAnimatingColor(obtainStyledAttributes.getColor(C5778R.styleable.BallPulseFooter_srlAnimatingColor, 0));
        }
        obtainStyledAttributes.recycle();
        this.mCircleSpacing = SmartUtil.dp2px(4.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float min = Math.min(width, height);
        float f = this.mCircleSpacing;
        float f2 = (min - (f * 2.0f)) / 6.0f;
        float f3 = f2 * 2.0f;
        float f4 = (width / 2.0f) - (f + f3);
        float f5 = height / 2.0f;
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < 3) {
            int i2 = i + 1;
            long j = (currentTimeMillis - this.mStartTime) - (i2 * 120);
            float interpolation = this.mInterpolator.getInterpolation(j > 0 ? ((float) (j % 750)) / 750.0f : 0.0f);
            canvas.save();
            float f6 = i;
            canvas.translate((f3 * f6) + f4 + (this.mCircleSpacing * f6), f5);
            if (interpolation < 0.5d) {
                float f7 = 1.0f - ((interpolation * 2.0f) * 0.7f);
                canvas.scale(f7, f7);
            } else {
                float f8 = ((interpolation * 2.0f) * 0.7f) - 0.4f;
                canvas.scale(f8, f8);
            }
            canvas.drawCircle(0.0f, 0.0f, f2, this.mPaint);
            canvas.restore();
            i = i2;
        }
        super.dispatchDraw(canvas);
        if (this.mIsStarted) {
            invalidate();
        }
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
        if (this.mIsStarted) {
            return;
        }
        invalidate();
        this.mIsStarted = true;
        this.mStartTime = System.currentTimeMillis();
        this.mPaint.setColor(this.mAnimatingColor);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        this.mIsStarted = false;
        this.mStartTime = 0L;
        this.mPaint.setColor(this.mNormalColor);
        return 0;
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    @Deprecated
    public void setPrimaryColors(int... iArr) {
        if (!this.mManualAnimationColor && iArr.length > 1) {
            setAnimatingColor(iArr[0]);
            this.mManualAnimationColor = false;
        }
        if (this.mManualNormalColor) {
            return;
        }
        if (iArr.length > 1) {
            setNormalColor(iArr[1]);
        } else if (iArr.length > 0) {
            setNormalColor(ColorUtils.compositeColors(-1711276033, iArr[0]));
        }
        this.mManualNormalColor = false;
    }

    public BallPulseFooter setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.mSpinnerStyle = spinnerStyle;
        return this;
    }

    public BallPulseFooter setNormalColor(int i) {
        this.mNormalColor = i;
        this.mManualNormalColor = true;
        if (!this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }

    public BallPulseFooter setAnimatingColor(int i) {
        this.mAnimatingColor = i;
        this.mManualAnimationColor = true;
        if (this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }
}
