package org.hash.mock.debug.view.sheet.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.ArrayList;
import java.util.List;
import org.hash.mock.debug.view.sheet.widget.CircleRevealHelper;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class IndicatorView extends LinearLayout implements ViewPager.OnPageChangeListener, CircleRevealHelper.CircleRevealEnable {
    private CircleRevealHelper mCircleRevealHelper;
    private List<View> mIndicators;
    private int mPreSelectPosition;
    private ViewPager mViewPager;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public IndicatorView(Context context) {
        super(context);
        this.mPreSelectPosition = -1;
        this.mIndicators = new ArrayList();
        init();
    }

    public IndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPreSelectPosition = -1;
        this.mIndicators = new ArrayList();
        init();
    }

    public IndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPreSelectPosition = -1;
        this.mIndicators = new ArrayList();
        init();
    }

    public IndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPreSelectPosition = -1;
        this.mIndicators = new ArrayList();
        init();
    }

    private void init() {
        setOrientation(0);
        this.mCircleRevealHelper = new CircleRevealHelper(this);
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        this.mViewPager = viewPager;
        this.mViewPager.addOnPageChangeListener(this);
        initIndicatorView();
    }

    private void initIndicatorView() {
        removeAllViews();
        this.mIndicators.clear();
        int count = this.mViewPager.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            addIndicationView();
        }
        setSelect(this.mViewPager.getCurrentItem());
    }

    private void setSelect(int i) {
        this.mIndicators.get(i).setSelected(true);
        int i2 = this.mPreSelectPosition;
        if (i2 != -1) {
            this.mIndicators.get(i2).setSelected(false);
        }
        this.mPreSelectPosition = i;
    }

    private void addIndicationView() {
        View view = new View(getContext());
        view.setBackgroundResource(C4429R.drawable.debug_indicator);
        int dimensionPixelSize = getResources().getDimensionPixelSize(C4429R.dimen.indicator_width);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C4429R.dimen.indicator_height);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(C4429R.dimen.indicator_padding);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2);
        layoutParams.leftMargin = dimensionPixelSize3;
        layoutParams.rightMargin = dimensionPixelSize3;
        addView(view, layoutParams);
        this.mIndicators.add(view);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setSelect(i);
    }

    public void alphaShow(boolean z) {
        if (z) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
            ofFloat.setDuration(300L);
            ofFloat.start();
            return;
        }
        ViewHelper.setAlpha(this, 1.0f);
    }

    public void alphaDismiss(boolean z) {
        if (z) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
            ofFloat.setDuration(300L);
            ofFloat.start();
            return;
        }
        ViewHelper.setAlpha(this, 0.0f);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        this.mCircleRevealHelper.onDraw(canvas);
    }

    @Override // org.hash.mock.debug.view.sheet.widget.CircleRevealHelper.CircleRevealEnable
    public void superOnDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // org.hash.mock.debug.view.sheet.widget.CircleRevealHelper.CircleRevealEnable
    public void circularReveal(int i, int i2, float f, float f2, long j, Interpolator interpolator) {
        this.mCircleRevealHelper.circularReveal(i, i2, f, f2, j, interpolator);
    }

    @Override // org.hash.mock.debug.view.sheet.widget.CircleRevealHelper.CircleRevealEnable
    public void circularReveal(int i, int i2, float f, float f2) {
        this.mCircleRevealHelper.circularReveal(i, i2, f, f2);
    }
}
