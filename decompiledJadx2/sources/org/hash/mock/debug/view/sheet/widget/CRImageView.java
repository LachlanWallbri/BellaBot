package org.hash.mock.debug.view.sheet.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import org.hash.mock.debug.view.sheet.widget.CircleRevealHelper;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class CRImageView extends ImageView implements CircleRevealHelper.CircleRevealEnable {
    private CircleRevealHelper mCircleRevealHelper;

    public CRImageView(Context context) {
        super(context);
        init();
    }

    public CRImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CRImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public CRImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getParent() instanceof GrowUpParent) {
            return ((GrowUpParent) getParent()).onParentHandMotionEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private void init() {
        this.mCircleRevealHelper = new CircleRevealHelper(this);
    }

    @Override // android.widget.ImageView, android.view.View
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
