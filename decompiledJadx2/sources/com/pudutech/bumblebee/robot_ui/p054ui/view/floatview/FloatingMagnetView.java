package com.pudutech.bumblebee.robot_ui.p054ui.view.floatview;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* loaded from: classes4.dex */
public class FloatingMagnetView extends FrameLayout {
    public static final int MARGIN_EDGE = 13;
    private static final int TOUCH_TIME_THRESHOLD = 150;
    private boolean isNearestLeft;
    private long mLastTouchDownTime;
    private MagnetViewListener mMagnetViewListener;
    protected MoveAnimator mMoveAnimator;
    private float mOriginalRawX;
    private float mOriginalRawY;
    private float mOriginalX;
    private float mOriginalY;
    private int mScreenHeight;
    protected int mScreenWidth;
    private int mStatusBarHeight;

    public void setMagnetViewListener(MagnetViewListener magnetViewListener) {
        this.mMagnetViewListener = magnetViewListener;
    }

    public FloatingMagnetView(Context context) {
        this(context, null);
    }

    public FloatingMagnetView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingMagnetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isNearestLeft = true;
        init();
    }

    private void init() {
        this.mMoveAnimator = new MoveAnimator();
        this.mStatusBarHeight = SystemUtils.getStatusBarHeight(getContext());
        setClickable(true);
        updateSize();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            changeOriginalTouchParams(motionEvent);
            updateSize();
            this.mMoveAnimator.stop();
        } else if (action == 1) {
            moveToEdge();
            if (isOnClickEvent()) {
                dealClickEvent();
            }
        } else if (action == 2) {
            updateViewPosition(motionEvent);
        }
        return true;
    }

    protected void dealClickEvent() {
        MagnetViewListener magnetViewListener = this.mMagnetViewListener;
        if (magnetViewListener != null) {
            magnetViewListener.onClick(this);
        }
    }

    protected boolean isOnClickEvent() {
        return System.currentTimeMillis() - this.mLastTouchDownTime < 150;
    }

    private void updateViewPosition(MotionEvent motionEvent) {
        setX((this.mOriginalX + motionEvent.getRawX()) - this.mOriginalRawX);
        float rawY = (this.mOriginalY + motionEvent.getRawY()) - this.mOriginalRawY;
        int i = this.mStatusBarHeight;
        if (rawY < i) {
            rawY = i;
        }
        if (rawY > this.mScreenHeight - getHeight()) {
            rawY = this.mScreenHeight - getHeight();
        }
        setY(rawY);
    }

    private void changeOriginalTouchParams(MotionEvent motionEvent) {
        this.mOriginalX = getX();
        this.mOriginalY = getY();
        this.mOriginalRawX = motionEvent.getRawX();
        this.mOriginalRawY = motionEvent.getRawY();
        this.mLastTouchDownTime = System.currentTimeMillis();
    }

    protected void updateSize() {
        this.mScreenWidth = SystemUtils.getScreenWidth(getContext()) - getWidth();
        this.mScreenHeight = SystemUtils.getScreenHeight(getContext());
    }

    public void moveToEdge() {
        moveToEdge(isNearestLeft());
    }

    public void moveToEdge(boolean z) {
        this.mMoveAnimator.start(z ? 13.0f : this.mScreenWidth - 13, getY());
    }

    protected boolean isNearestLeft() {
        this.isNearestLeft = getX() < ((float) (this.mScreenWidth / 2));
        return this.isNearestLeft;
    }

    public void onRemove() {
        MagnetViewListener magnetViewListener = this.mMagnetViewListener;
        if (magnetViewListener != null) {
            magnetViewListener.onRemove(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class MoveAnimator implements Runnable {
        private float destinationX;
        private float destinationY;
        private Handler handler = new Handler(Looper.getMainLooper());
        private long startingTime;

        protected MoveAnimator() {
        }

        void start(float f, float f2) {
            this.destinationX = f;
            this.destinationY = f2;
            this.startingTime = System.currentTimeMillis();
            this.handler.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FloatingMagnetView.this.getRootView() == null || FloatingMagnetView.this.getRootView().getParent() == null) {
                return;
            }
            float min = Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startingTime)) / 400.0f);
            FloatingMagnetView.this.move((this.destinationX - FloatingMagnetView.this.getX()) * min, (this.destinationY - FloatingMagnetView.this.getY()) * min);
            if (min < 1.0f) {
                this.handler.post(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            this.handler.removeCallbacks(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void move(float f, float f2) {
        setX(getX() + f);
        setY(getY() + f2);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateSize();
        moveToEdge(this.isNearestLeft);
    }
}
