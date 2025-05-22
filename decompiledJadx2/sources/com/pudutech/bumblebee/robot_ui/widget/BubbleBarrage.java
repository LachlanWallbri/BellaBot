package com.pudutech.bumblebee.robot_ui.widget;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class BubbleBarrage<T> {
    private static final String TAG = "BubbleBarrage";
    private LinearLayout barrageContainer;
    private Context context;
    private int layout;
    private DisplayMetrics metrics;
    private OnBarrageLoadListener<T> onBarrageLoadListener;
    private LayoutTransition transition = new LayoutTransition();
    private int index = 0;
    private long intervalTime = 4000;
    private int visibleCount = 3;
    private long visibleTime = this.intervalTime * this.visibleCount;
    private Handler handler = new Handler();
    private int margin = 5;
    private List<T> barrages = new ArrayList();

    /* loaded from: classes4.dex */
    public interface OnBarrageLoadListener<T> {
        void loadBarrage(View view, List<T> list, int i);
    }

    static /* synthetic */ int access$008(BubbleBarrage bubbleBarrage) {
        int i = bubbleBarrage.index;
        bubbleBarrage.index = i + 1;
        return i;
    }

    public BubbleBarrage<T> init(Context context, LinearLayout linearLayout, int i) {
        this.context = context;
        this.layout = i;
        this.barrageContainer = linearLayout;
        linearLayout.setLayoutTransition(this.transition);
        setAppearTransition();
        setDisappearTransition();
        return this;
    }

    public BubbleBarrage<T> setIntervalTime(int i) {
        this.intervalTime = i;
        this.visibleTime = i * this.visibleCount;
        return this;
    }

    public BubbleBarrage<T> setVisibleCount(int i) {
        this.visibleCount = i;
        this.visibleTime = this.intervalTime * this.visibleCount;
        return this;
    }

    public BubbleBarrage<T> setItemMargin(int i) {
        this.margin = (int) parseToDp(i);
        return this;
    }

    public BubbleBarrage<T> setOnBarrageLoadListener(OnBarrageLoadListener<T> onBarrageLoadListener) {
        this.onBarrageLoadListener = onBarrageLoadListener;
        return this;
    }

    public BubbleBarrage<T> start(List<T> list, int i) {
        if (list == null) {
            try {
                throw new Exception("barrage data should not be null");
            } catch (Exception e) {
                e.printStackTrace();
                return this;
            }
        }
        this.barrages.clear();
        this.barrages.addAll(list);
        this.handler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage.1
            @Override // java.lang.Runnable
            public void run() {
                Log.e(BubbleBarrage.TAG, "current in index -->" + BubbleBarrage.this.index + "barrage size -->" + BubbleBarrage.this.barrages.size());
                if (BubbleBarrage.this.index > BubbleBarrage.this.barrages.size() - 1) {
                    BubbleBarrage.this.index = 0;
                } else {
                    BubbleBarrage bubbleBarrage = BubbleBarrage.this;
                    bubbleBarrage.addBarrageToContainer(bubbleBarrage.index);
                    BubbleBarrage.access$008(BubbleBarrage.this);
                }
                if (BubbleBarrage.this.handler != null) {
                    BubbleBarrage.this.handler.postDelayed(this, BubbleBarrage.this.intervalTime);
                }
                Log.i(BubbleBarrage.TAG, "current index -->" + BubbleBarrage.this.index);
            }
        }, i);
        return this;
    }

    public BubbleBarrage<T> insertToNext(T t) {
        this.barrages.add(this.index, t);
        Log.i(TAG, "barrages size -->" + this.barrages.size());
        return this;
    }

    public BubbleBarrage<T> appendBarrages(List<T> list) {
        this.barrages.addAll(list);
        return this;
    }

    public BubbleBarrage<T> appendBarrage(T t) {
        this.barrages.add(t);
        return this;
    }

    private void setAppearTransition() {
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f)).setDuration(this.transition.getDuration(2));
        duration.addListener(new Animator.AnimatorListener() { // from class: com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                View view = (View) ((ObjectAnimator) animator).getTarget();
                if (view != null) {
                    view.setPivotX(0.0f);
                    view.setPivotY(view.getHeight());
                }
            }
        });
        this.transition.setAnimator(2, duration);
    }

    private void setDisappearTransition() {
        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f), PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f), PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f)).setDuration(this.transition.getDuration(3));
        duration.addListener(new Animator.AnimatorListener() { // from class: com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                View view = (View) ((ObjectAnimator) animator).getTarget();
                if (view != null) {
                    view.setPivotX(0.0f);
                    view.setPivotY(0.0f);
                }
            }
        });
        this.transition.setAnimator(3, duration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBarrageToContainer(int i) {
        View createBarrage = createBarrage(i);
        this.barrageContainer.addView(createBarrage, setItemMargin());
        createBarrage.postDelayed(removeView(createBarrage), this.visibleTime);
    }

    private View createBarrage(int i) {
        View inflate = LayoutInflater.from(this.context).inflate(this.layout, (ViewGroup) null);
        OnBarrageLoadListener<T> onBarrageLoadListener = this.onBarrageLoadListener;
        if (onBarrageLoadListener != null) {
            onBarrageLoadListener.loadBarrage(inflate, this.barrages, i);
        }
        return inflate;
    }

    private LinearLayout.LayoutParams setItemMargin() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int i = this.margin;
        layoutParams.setMargins(i, 0, i, i);
        return layoutParams;
    }

    private Runnable removeView(final View view) {
        return new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.widget.BubbleBarrage.4
            @Override // java.lang.Runnable
            public void run() {
                BubbleBarrage.this.barrageContainer.removeView(view);
            }
        };
    }

    private float parseToDp(int i) {
        if (this.metrics == null) {
            this.metrics = new DisplayMetrics();
            try {
                ((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return TypedValue.applyDimension(1, i, this.metrics);
    }

    public void destroyView() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }
}
