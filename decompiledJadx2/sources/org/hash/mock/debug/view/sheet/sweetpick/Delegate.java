package org.hash.mock.debug.view.sheet.sweetpick;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import org.hash.mock.debug.view.sheet.SimpleAnimationListener;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;
import org.hash.mock.debug.view.sheet.sweetpick.SweetSheet;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public abstract class Delegate implements View.OnClickListener {
    private ImageView mBg;
    private Effect mEffect;
    SweetSheet.OnMenuItemClickListener mOnMenuItemClickListener;
    protected ViewGroup mParentVG;
    protected View mRootView;
    protected SweetSheet.Status mStatus = SweetSheet.Status.DISMISS;
    private boolean mIsBgClickEnable = true;

    protected abstract View createView();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void setMenuList(SparseArray<ArrayList<SimpleEntity>> sparseArray);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(ViewGroup viewGroup) {
        this.mParentVG = viewGroup;
        this.mBg = new ImageView(viewGroup.getContext());
        this.mRootView = createView();
        this.mBg.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* renamed from: org.hash.mock.debug.view.sheet.sweetpick.Delegate$4 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C86424 {

        /* renamed from: $SwitchMap$org$hash$mock$debug$view$sheet$sweetpick$SweetSheet$Status */
        static final /* synthetic */ int[] f10010x357e09e5 = new int[SweetSheet.Status.values().length];

        static {
            try {
                f10010x357e09e5[SweetSheet.Status.SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10010x357e09e5[SweetSheet.Status.SHOWING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10010x357e09e5[SweetSheet.Status.DISMISS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10010x357e09e5[SweetSheet.Status.DISMISSING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toggle() {
        int i = C86424.f10010x357e09e5[this.mStatus.ordinal()];
        if (i == 1 || i == 2) {
            dismiss();
        } else if (i == 3 || i == 4) {
            show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void show() {
        if (getStatus() != SweetSheet.Status.DISMISS) {
            return;
        }
        this.mBg.setClickable(this.mIsBgClickEnable);
        showShowdown();
    }

    private void showShowdown() {
        ViewHelper.setTranslationY(this.mRootView, 0.0f);
        this.mEffect.effect(this.mParentVG, this.mBg);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        if (this.mBg.getParent() != null) {
            this.mParentVG.removeView(this.mBg);
        }
        this.mParentVG.addView(this.mBg, layoutParams);
        ViewHelper.setAlpha(this.mBg, 0.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mBg, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(400L);
        ofFloat.start();
    }

    private void dismissShowdown() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mBg, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(400L);
        ofFloat.start();
        ofFloat.addListener(new SimpleAnimationListener() { // from class: org.hash.mock.debug.view.sheet.sweetpick.Delegate.1
            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Delegate.this.mParentVG.removeView(Delegate.this.mBg);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dismiss() {
        if (getStatus() == SweetSheet.Status.DISMISS) {
            return;
        }
        this.mBg.setClickable(false);
        dismissShowdown();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mRootView, "translationY", 0.0f, r0.getHeight());
        ofFloat.setDuration(600L);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.addListener(new SimpleAnimationListener() { // from class: org.hash.mock.debug.view.sheet.sweetpick.Delegate.2
            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Delegate.this.mStatus = SweetSheet.Status.DISMISSING;
            }

            @Override // org.hash.mock.debug.view.sheet.SimpleAnimationListener, com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Delegate.this.mStatus = SweetSheet.Status.DISMISS;
                Delegate.this.mParentVG.removeView(Delegate.this.mRootView);
            }
        });
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setBackgroundEffect(Effect effect) {
        this.mEffect = effect;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setOnMenuItemClickListener(SweetSheet.OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void delayedDismiss() {
        this.mParentVG.postDelayed(new Runnable() { // from class: org.hash.mock.debug.view.sheet.sweetpick.Delegate.3
            @Override // java.lang.Runnable
            public void run() {
                Delegate.this.dismiss();
            }
        }, 300L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SweetSheet.Status getStatus() {
        return this.mStatus;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        dismiss();
    }

    public void setBackgroundClickEnable(boolean z) {
        this.mIsBgClickEnable = z;
    }

    public View getRootView() {
        return this.mRootView;
    }
}
