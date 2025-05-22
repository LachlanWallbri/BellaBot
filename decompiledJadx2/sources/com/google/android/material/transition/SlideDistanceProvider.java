package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.C1653R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes2.dex
 */
/* loaded from: classes.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {
    private static final int DEFAULT_DISTANCE = -1;
    private int slideDistance = -1;
    private int slideEdge;

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes2.dex
     */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface GravityFlag {
    }

    public SlideDistanceProvider(int i) {
        this.slideEdge = i;
    }

    public int getSlideEdge() {
        return this.slideEdge;
    }

    public void setSlideEdge(int i) {
        this.slideEdge = i;
    }

    public int getSlideDistance() {
        return this.slideDistance;
    }

    public void setSlideDistance(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
        }
        this.slideDistance = i;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup, View view) {
        return createTranslationAppearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup, View view) {
        return createTranslationDisappearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    private int getSlideDistanceOrDefault(Context context) {
        int i = this.slideDistance;
        return i != -1 ? i : context.getResources().getDimensionPixelSize(C1653R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    private static Animator createTranslationAppearAnimator(View view, View view2, int i, int i2) {
        if (i == 3) {
            return createTranslationXAnimator(view2, i2, 0.0f);
        }
        if (i == 5) {
            return createTranslationXAnimator(view2, -i2, 0.0f);
        }
        if (i == 48) {
            return createTranslationYAnimator(view2, -i2, 0.0f);
        }
        if (i == 80) {
            return createTranslationYAnimator(view2, i2, 0.0f);
        }
        if (i == 8388611) {
            return createTranslationXAnimator(view2, isRtl(view) ? i2 : -i2, 0.0f);
        }
        if (i == 8388613) {
            return createTranslationXAnimator(view2, isRtl(view) ? -i2 : i2, 0.0f);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i);
    }

    private static Animator createTranslationDisappearAnimator(View view, View view2, int i, int i2) {
        if (i == 3) {
            return createTranslationXAnimator(view2, 0.0f, -i2);
        }
        if (i == 5) {
            return createTranslationXAnimator(view2, 0.0f, i2);
        }
        if (i == 48) {
            return createTranslationYAnimator(view2, 0.0f, i2);
        }
        if (i == 80) {
            return createTranslationYAnimator(view2, 0.0f, -i2);
        }
        if (i == 8388611) {
            return createTranslationXAnimator(view2, 0.0f, isRtl(view) ? -i2 : i2);
        }
        if (i == 8388613) {
            return createTranslationXAnimator(view2, 0.0f, isRtl(view) ? i2 : -i2);
        }
        throw new IllegalArgumentException("Invalid slide direction: " + i);
    }

    private static Animator createTranslationXAnimator(View view, float f, float f2) {
        return ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f, f2));
    }

    private static Animator createTranslationYAnimator(View view, float f, float f2) {
        return ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f, f2));
    }

    private static boolean isRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    /* renamed from: com.google.android.material.transition.SlideDistanceProvider$1 */
    /* loaded from: classes2.dex */
    static class C19271 extends AnimatorListenerAdapter {
        final /* synthetic */ float val$originalTranslation;
        final /* synthetic */ View val$view;

        C19271(View view, float f) {
            this.val$view = view;
            this.val$originalTranslation = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.val$view.setTranslationX(this.val$originalTranslation);
        }
    }

    /* renamed from: com.google.android.material.transition.SlideDistanceProvider$2 */
    /* loaded from: classes2.dex */
    static class C19282 extends AnimatorListenerAdapter {
        final /* synthetic */ float val$originalTranslation;
        final /* synthetic */ View val$view;

        C19282(View view, float f) {
            this.val$view = view;
            this.val$originalTranslation = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.val$view.setTranslationY(this.val$originalTranslation);
        }
    }
}
