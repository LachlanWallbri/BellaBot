package com.pudutech.bumblebee.robot_ui.widget.random_layout;

import android.view.View;
import com.nineoldandroids.view.ViewPropertyAnimator;

/* loaded from: classes4.dex */
public class AnimatorUtil {
    private ViewPropertyAnimator animate;
    private int duration;
    private View view;

    public void setSelfAnimator() {
    }

    public AnimatorUtil(View view, int i) {
        this.view = view;
        this.duration = i;
        this.animate = ViewPropertyAnimator.animate(view);
    }

    public ViewPropertyAnimator getAnimate() {
        return this.animate;
    }

    public AnimatorUtil addScaleAnimationBy(float f) {
        this.animate.scaleXBy(f).scaleYBy(f).setDuration(this.duration);
        return this;
    }

    public AnimatorUtil addTranslationAnimationBy(float f, float f2) {
        this.animate.translationXBy(f).translationYBy(f2).setDuration(this.duration);
        return this;
    }

    public AnimatorUtil addRotationAnimationBy(float f) {
        this.animate.rotationBy(f).setDuration(this.duration);
        return this;
    }

    public AnimatorUtil addAlphaAnimationBy(float f) {
        this.animate.alphaBy(f).setDuration(this.duration);
        return this;
    }

    public void cancelAnimation() {
        this.animate.cancel();
        this.view.clearAnimation();
    }

    public void resetAnimation() {
        this.animate.cancel();
        this.view.clearAnimation();
        this.animate = ViewPropertyAnimator.animate(this.view);
    }

    public void startAnimator() {
        this.animate.start();
    }
}
