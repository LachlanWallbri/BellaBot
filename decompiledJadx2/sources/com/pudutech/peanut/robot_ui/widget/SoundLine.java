package com.pudutech.peanut.robot_ui.widget;

import android.animation.ValueAnimator;
import java.util.Random;

/* loaded from: classes5.dex */
public class SoundLine implements ValueAnimator.AnimatorUpdateListener {
    public static final int SPEED_HEI = 0;
    public static final int SPEED_LOW = 500;
    public static final int SPEED_MID = 200;
    public static final int SPEED_RAN = 0;
    public int bottom;
    public int left;
    private ValueAnimator mAnim;
    private Random mRandom = new Random();
    private int max;
    private int min;
    public int right;
    public int top;

    public SoundLine(int i, int i2, int i3, int i4) {
        this.left = i;
        this.right = i2;
        this.top = i3;
        this.bottom = i4;
        initAnim();
    }

    private void initAnim() {
        this.mAnim = ValueAnimator.ofFloat(0.0f, 1.0f);
        setMode(200);
        this.mAnim.setRepeatCount(-1);
        this.mAnim.setRepeatMode(2);
        this.mAnim.addUpdateListener(this);
    }

    public void setMode(int i) {
        if (i == 0) {
            i = this.mRandom.nextInt(400);
        }
        this.mAnim.setDuration(i + 300);
    }

    public void start() {
        if (this.mAnim.isRunning()) {
            this.mAnim.end();
        }
        this.mAnim.start();
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        int i = this.max;
        this.top = (int) ((floatValue * (i - this.min)) / 2.0f);
        this.bottom = i - this.top;
    }

    public void setBorder(int i, int i2) {
        this.min = i;
        this.max = i2;
    }

    public void stop() {
        this.mAnim.end();
        this.mAnim.cancel();
    }
}
