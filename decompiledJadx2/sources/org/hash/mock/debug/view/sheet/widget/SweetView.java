package org.hash.mock.debug.view.sheet.widget;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.view.sheet.SimpleAnimationListener;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SweetView extends View {
    private AnimationListener mAnimationListener;
    private int mArcHeight;
    private int mMaxArcHeight;
    private Paint mPaint;
    private Path mPath;
    private Status mStatus;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface AnimationListener {
        void onContentShow();

        void onEnd();

        void onStart();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum Status {
        NONE,
        STATUS_SMOOTH_UP,
        STATUS_UP,
        STATUS_DOWN
    }

    public SweetView(Context context) {
        super(context);
        this.mStatus = Status.NONE;
        this.mPath = new Path();
        init();
    }

    public SweetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStatus = Status.NONE;
        this.mPath = new Path();
        init();
    }

    public SweetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStatus = Status.NONE;
        this.mPath = new Path();
        init();
    }

    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(getResources().getColor(R.color.white));
        this.mMaxArcHeight = getResources().getDimensionPixelSize(C4429R.dimen.arc_max_height);
    }

    public SweetView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mStatus = Status.NONE;
        this.mPath = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBG(canvas);
    }

    public void show() {
        this.mStatus = Status.STATUS_SMOOTH_UP;
        AnimationListener animationListener = this.mAnimationListener;
        if (animationListener != null) {
            animationListener.onStart();
            postDelayed(new Runnable() { // from class: org.hash.mock.debug.view.sheet.widget.SweetView.1
                @Override // java.lang.Runnable
                public void run() {
                    SweetView.this.mAnimationListener.onContentShow();
                }
            }, 600L);
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(0, this.mMaxArcHeight);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.hash.mock.debug.view.sheet.widget.SweetView.2
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SweetView.this.mArcHeight = intValue;
                if (intValue == SweetView.this.mMaxArcHeight) {
                    SweetView.this.duang();
                }
                SweetView.this.invalidate();
            }
        });
        ofInt.setDuration(800L);
        ofInt.setInterpolator(new AccelerateInterpolator());
        ofInt.start();
    }

    public void duang() {
        this.mStatus = Status.STATUS_DOWN;
        ValueAnimator ofInt = ValueAnimator.ofInt(this.mMaxArcHeight, 0);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.hash.mock.debug.view.sheet.widget.SweetView.3
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SweetView.this.mArcHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SweetView.this.invalidate();
            }
        });
        ofInt.addListener(new SimpleAnimationListener() { // from class: org.hash.mock.debug.view.sheet.widget.SweetView.4
            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (SweetView.this.mAnimationListener != null) {
                    SweetView.this.mAnimationListener.onEnd();
                }
            }
        });
        ofInt.setDuration(500L);
        ofInt.setInterpolator(new OvershootInterpolator(4.0f));
        ofInt.start();
    }

    private void drawBG(Canvas canvas) {
        int i;
        this.mPath.reset();
        int i2 = C86515.f10011x777dfbb2[this.mStatus.ordinal()];
        if (i2 == 1) {
            i = this.mMaxArcHeight;
        } else if (i2 == 2 || i2 == 3) {
            int height = getHeight();
            int height2 = getHeight();
            i = height - ((int) ((height2 - r3) * Math.min(1.0d, (((this.mArcHeight - (r3 / 4)) * 2.0d) / this.mMaxArcHeight) * 1.3d)));
        } else {
            i = i2 != 4 ? 0 : this.mMaxArcHeight;
        }
        float f = i;
        this.mPath.moveTo(0.0f, f);
        this.mPath.quadTo(getWidth() / 2, i - this.mArcHeight, getWidth(), f);
        this.mPath.lineTo(getWidth(), getHeight());
        this.mPath.lineTo(0.0f, getHeight());
        this.mPath.lineTo(0.0f, f);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: org.hash.mock.debug.view.sheet.widget.SweetView$5 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C86515 {

        /* renamed from: $SwitchMap$org$hash$mock$debug$view$sheet$widget$SweetView$Status */
        static final /* synthetic */ int[] f10011x777dfbb2 = new int[Status.values().length];

        static {
            try {
                f10011x777dfbb2[Status.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10011x777dfbb2[Status.STATUS_SMOOTH_UP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10011x777dfbb2[Status.STATUS_UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10011x777dfbb2[Status.STATUS_DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AnimationListener getAnimationListener() {
        return this.mAnimationListener;
    }

    public void setAnimationListener(AnimationListener animationListener) {
        this.mAnimationListener = animationListener;
    }

    public void setSweetSheetColor(int i) {
        this.mPaint.setColor(i);
    }
}
