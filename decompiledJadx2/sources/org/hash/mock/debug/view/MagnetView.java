package org.hash.mock.debug.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class MagnetView extends FrameLayout {
    private static final int TOUCH_TIME_THRESHOLD = 150;
    private MoveAnimator animator;
    private float initialTouchX;
    private float initialTouchY;
    private float initialX;
    private float initialY;
    private long lastTouchDown;
    private DebugToolsCoordinator layoutCoordinator;
    private int mScreenHeight;
    private int mStatusBarHeight;
    private OnClickListener onClickListener;
    private OnRemoveListener onRemoveListener;
    private boolean shouldStickToWall;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface OnClickListener {
        void onClick(MagnetView magnetView);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface OnRemoveListener {
        void onRemoved(MagnetView magnetView);
    }

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MagnetView(Context context) {
        this(context, null);
        inflate(getContext(), C4429R.layout.debug_tools_layout, this);
        initializeView();
    }

    public MagnetView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        initializeView();
    }

    public MagnetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.shouldStickToWall = true;
        initializeView();
    }

    public void setShouldStickToWall(boolean z) {
        this.shouldStickToWall = z;
    }

    void notifyRemoved() {
        OnRemoveListener onRemoveListener = this.onRemoveListener;
        if (onRemoveListener != null) {
            onRemoveListener.onRemoved(this);
        }
    }

    private void initializeView() {
        this.mStatusBarHeight = Utils.getStatusBarHeight();
        this.mScreenHeight = Utils.getScreenHeight();
        this.animator = new MoveAnimator();
        setClickable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        playAnimation();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        OnClickListener onClickListener;
        if (motionEvent != null) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.initialX = getX();
                this.initialY = getY();
                this.initialTouchX = motionEvent.getRawX();
                this.initialTouchY = motionEvent.getRawY();
                playAnimationClickDown();
                this.lastTouchDown = System.currentTimeMillis();
                updateSize();
                this.animator.stop();
            } else if (action == 1) {
                goToWall();
                if (getLayoutCoordinator() != null) {
                    getLayoutCoordinator().notifyBubbleRelease(this);
                    playAnimationClickUp();
                }
                if (System.currentTimeMillis() - this.lastTouchDown < 150 && (onClickListener = this.onClickListener) != null) {
                    onClickListener.onClick(this);
                }
            } else if (action == 2) {
                updateViewPosition(motionEvent);
                if (getLayoutCoordinator() != null) {
                    getLayoutCoordinator().notifyPositionChanged(this);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void updateViewPosition(MotionEvent motionEvent) {
        setX((this.initialX + motionEvent.getRawX()) - this.initialTouchX);
        float rawY = (this.initialY + motionEvent.getRawY()) - this.initialTouchY;
        int i = this.mStatusBarHeight;
        if (rawY < i) {
            rawY = i;
        }
        if (rawY > this.mScreenHeight - getHeight()) {
            rawY = this.mScreenHeight - getHeight();
        }
        setY(rawY);
    }

    private void playAnimation() {
        if (isInEditMode()) {
            return;
        }
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), C4429R.animator.debug_tools_shown_animator);
        animatorSet.setTarget(this);
        animatorSet.start();
    }

    private void playAnimationClickDown() {
        if (isInEditMode()) {
            return;
        }
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), C4429R.animator.debug_tools_down_click_animator);
        animatorSet.setTarget(this);
        animatorSet.start();
    }

    private void playAnimationClickUp() {
        if (isInEditMode()) {
            return;
        }
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), C4429R.animator.debug_tools_up_click_animator);
        animatorSet.setTarget(this);
        animatorSet.start();
    }

    private void updateSize() {
        this.width = Utils.getScreenWidth() - getWidth();
    }

    public void setLayoutCoordinator(DebugToolsCoordinator debugToolsCoordinator) {
        this.layoutCoordinator = debugToolsCoordinator;
    }

    DebugToolsCoordinator getLayoutCoordinator() {
        return this.layoutCoordinator;
    }

    public void goToWall() {
        if (this.shouldStickToWall) {
            this.animator.start(getX() >= ((float) (this.width / 2)) ? this.width + Utils.dp2px(20.0f) : -Utils.dp2px(20.0f), getY());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void move(float f, float f2) {
        setX(getX() + f);
        setY(getY() + f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public class MoveAnimator implements Runnable {
        private float destinationX;
        private float destinationY;
        private Handler handler;
        private long startingTime;

        private MoveAnimator() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start(float f, float f2) {
            this.destinationX = f;
            this.destinationY = f2;
            this.startingTime = System.currentTimeMillis();
            this.handler.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MagnetView.this.getRootView() == null || MagnetView.this.getRootView().getParent() == null) {
                return;
            }
            float min = Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startingTime)) / 400.0f);
            MagnetView.this.move((this.destinationX - MagnetView.this.getX()) * min, (this.destinationY - MagnetView.this.getY()) * min);
            if (min < 1.0f) {
                this.handler.post(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void stop() {
            this.handler.removeCallbacks(this);
        }
    }
}
