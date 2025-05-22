package org.hash.mock.debug.view.sheet.widget;

import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import com.nineoldandroids.animation.ValueAnimator;
import org.hash.mock.debug.view.sheet.SimpleAnimationListener;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class CircleRevealHelper {
    private int mAnchorX;
    private int mAnchorY;
    private CircleRevealEnable mCircleRevealEnable;
    private float mRadius;
    private View mView;
    private Path mPath = new Path();
    private boolean isCircularReveal = false;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface CircleRevealEnable {
        void circularReveal(int i, int i2, float f, float f2);

        void circularReveal(int i, int i2, float f, float f2, long j, Interpolator interpolator);

        void superOnDraw(Canvas canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CircleRevealHelper(View view) {
        this.mView = view;
        if (view instanceof CircleRevealEnable) {
            this.mCircleRevealEnable = (CircleRevealEnable) view;
        }
    }

    public void circularReveal(int i, int i2, float f, float f2) {
        circularReveal(i, i2, f, f2, 700L, new AccelerateDecelerateInterpolator());
    }

    public void circularReveal(int i, int i2, float f, float f2, long j, Interpolator interpolator) {
        this.mAnchorX = i;
        this.mAnchorY = i2;
        if (this.mView.getParent() == null || !ViewCompat.isAttachedToWindow(this.mView)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.mView, this.mAnchorX, this.mAnchorY, f, f2);
            createCircularReveal.setInterpolator(interpolator);
            createCircularReveal.setDuration(j);
            createCircularReveal.start();
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.hash.mock.debug.view.sheet.widget.CircleRevealHelper.1
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleRevealHelper.this.mRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircleRevealHelper.this.mView.invalidate();
            }
        });
        ofFloat.setInterpolator(interpolator);
        ofFloat.addListener(new SimpleAnimationListener() { // from class: org.hash.mock.debug.view.sheet.widget.CircleRevealHelper.2
            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationStart(com.nineoldandroids.animation.Animator animator) {
                CircleRevealHelper.this.isCircularReveal = true;
            }

            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(com.nineoldandroids.animation.Animator animator) {
                CircleRevealHelper.this.isCircularReveal = false;
            }

            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationCancel(com.nineoldandroids.animation.Animator animator) {
                CircleRevealHelper.this.isCircularReveal = false;
            }
        });
        ofFloat.setDuration(j);
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.isCircularReveal) {
            canvas.save();
            canvas.translate(0.0f, 0.0f);
            this.mPath.reset();
            this.mPath.addCircle(this.mAnchorX, this.mAnchorY, this.mRadius, Path.Direction.CCW);
            canvas.clipPath(this.mPath, Region.Op.REPLACE);
            this.mCircleRevealEnable.superOnDraw(canvas);
            canvas.restore();
            return;
        }
        this.mCircleRevealEnable.superOnDraw(canvas);
    }
}
