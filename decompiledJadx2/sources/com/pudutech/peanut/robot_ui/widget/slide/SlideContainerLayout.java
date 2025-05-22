package com.pudutech.peanut.robot_ui.widget.slide;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlideContainerLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J#\u0010(\u001a\u00020)2\u0016\u0010*\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00180+\"\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020#J\u0010\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020)H\u0002J\u0006\u00103\u001a\u00020\u0010J\b\u00104\u001a\u00020)H\u0002J\b\u00105\u001a\u00020)H\u0002J\u0012\u00106\u001a\u00020)2\b\u00107\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u00108\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0016J\u0010\u00109\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0016J\u0006\u0010:\u001a\u00020)J\u0006\u0010;\u001a\u00020)J\u0016\u0010<\u001a\u00020)2\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\tJ\b\u0010?\u001a\u00020)H\u0002J\b\u0010@\u001a\u00020)H\u0002J\b\u0010A\u001a\u00020)H\u0002J\u0010\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\tH\u0002J\u0010\u0010D\u001a\u00020)2\u0006\u0010C\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006E"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/slide/SlideContainerLayout;", "Landroid/widget/RelativeLayout;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "MASK_DARK_COLOR", "", "MASK_TRANSPANT_COLOR", "TAG", "", "kotlin.jvm.PlatformType", "endX", "isCleared", "", "isSlideShow", "()Z", "setSlideShow", "(Z)V", "isSlideVertical", "isSliderGoning", "mBgColorView", "Landroid/view/View;", "mClearAnimator", "Landroid/animation/ValueAnimator;", "mClearViews", "Ljava/util/ArrayList;", "mDownX", "mDownY", "mLastOffsetList", "Ljava/util/LinkedList;", "mSlideInAnimator", "mSlideView", "Lcom/pudutech/peanut/robot_ui/widget/slide/LeftSlideLayout;", "mVelocityTracker", "Landroid/view/VelocityTracker;", "startX", "translateX", "addClearViews", "", "views", "", "([Landroid/view/View;)V", "addSlideView", "view", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "initView", "isAlignLeftSide", "layerGoneWithAnim", "layerShowWithAnim", "onClick", "p0", "onInterceptTouchEvent", "onTouchEvent", "release", "reset", "setDownXY", "downX", "downY", "sliderGone", "sliderGoneWithAnim", "sliderShowWithAnim", "translateClearChild", "translate", "translateSlideView", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SlideContainerLayout extends RelativeLayout implements View.OnClickListener {
    private final int MASK_DARK_COLOR;
    private final int MASK_TRANSPANT_COLOR;
    private final String TAG;
    private HashMap _$_findViewCache;
    private int endX;
    private boolean isCleared;
    private boolean isSlideShow;
    private boolean isSlideVertical;
    private boolean isSliderGoning;
    private View mBgColorView;
    private ValueAnimator mClearAnimator;
    private final ArrayList<View> mClearViews;
    private int mDownX;
    private int mDownY;
    private LinkedList<Integer> mLastOffsetList;
    private ValueAnimator mSlideInAnimator;
    private LeftSlideLayout mSlideView;
    private VelocityTracker mVelocityTracker;
    private int startX;
    private int translateX;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View p0) {
    }

    public /* synthetic */ SlideContainerLayout(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    public static final /* synthetic */ View access$getMBgColorView$p(SlideContainerLayout slideContainerLayout) {
        View view = slideContainerLayout.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        return view;
    }

    public static final /* synthetic */ ValueAnimator access$getMSlideInAnimator$p(SlideContainerLayout slideContainerLayout) {
        ValueAnimator valueAnimator = slideContainerLayout.mSlideInAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        return valueAnimator;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlideContainerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = getClass().getSimpleName();
        this.mLastOffsetList = new LinkedList<>();
        this.MASK_DARK_COLOR = 170;
        this.mClearViews = new ArrayList<>();
        initView();
    }

    /* renamed from: isSlideShow, reason: from getter */
    public final boolean getIsSlideShow() {
        return this.isSlideShow;
    }

    public final void setSlideShow(boolean z) {
        this.isSlideShow = z;
    }

    private final void initView() {
        this.mBgColorView = new View(getContext());
        View view = this.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        View view2 = this.mBgColorView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view2.setClickable(true);
        View view3 = this.mBgColorView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        addView(view3, getChildCount() - 4);
        View view4 = this.mBgColorView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view4.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$initView$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view5) {
                boolean z;
                if (!SlideContainerLayout.this.getIsSlideShow() || SlideContainerLayout.access$getMSlideInAnimator$p(SlideContainerLayout.this).isRunning()) {
                    return;
                }
                z = SlideContainerLayout.this.isSlideVertical;
                if (z) {
                    return;
                }
                SlideContainerLayout.this.sliderGoneWithAnim();
            }
        });
        this.mVelocityTracker = VelocityTracker.obtain();
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(300L);
        Intrinsics.checkExpressionValueIsNotNull(duration, "ValueAnimator.ofFloat(0f, 1.0f).setDuration(300)");
        this.mClearAnimator = duration;
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$initView$2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int i;
                int i2;
                int i3;
                Intrinsics.checkExpressionValueIsNotNull(valueAnimator2, "valueAnimator");
                Object animatedValue = valueAnimator2.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                }
                float floatValue = ((Float) animatedValue).floatValue();
                SlideContainerLayout slideContainerLayout = SlideContainerLayout.this;
                i = slideContainerLayout.startX;
                i2 = SlideContainerLayout.this.endX;
                i3 = SlideContainerLayout.this.startX;
                slideContainerLayout.translateClearChild((int) (i + (floatValue * (i2 - i3))));
            }
        });
        ValueAnimator valueAnimator2 = this.mClearAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        valueAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$initView$3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                boolean z;
                Intrinsics.checkParameterIsNotNull(animation, "animation");
                SlideContainerLayout slideContainerLayout = SlideContainerLayout.this;
                z = slideContainerLayout.isCleared;
                slideContainerLayout.isCleared = !z;
            }
        });
        ValueAnimator duration2 = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(500L);
        Intrinsics.checkExpressionValueIsNotNull(duration2, "ValueAnimator.ofFloat(0f, 1.0f).setDuration(500)");
        this.mSlideInAnimator = duration2;
        ValueAnimator valueAnimator3 = this.mSlideInAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator3.setInterpolator(new DecelerateInterpolator(3.0f));
        ValueAnimator valueAnimator4 = this.mSlideInAnimator;
        if (valueAnimator4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$initView$4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator5) {
                int i;
                int i2;
                int i3;
                Intrinsics.checkExpressionValueIsNotNull(valueAnimator5, "valueAnimator");
                Object animatedValue = valueAnimator5.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                }
                float floatValue = ((Float) animatedValue).floatValue();
                SlideContainerLayout slideContainerLayout = SlideContainerLayout.this;
                i = slideContainerLayout.startX;
                i2 = SlideContainerLayout.this.endX;
                i3 = SlideContainerLayout.this.startX;
                slideContainerLayout.translateSlideView((int) (i + (floatValue * (i2 - i3))));
            }
        });
        ValueAnimator valueAnimator5 = this.mSlideInAnimator;
        if (valueAnimator5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator5.addListener(new AnimatorListenerAdapter() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$initView$5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                LeftSlideLayout leftSlideLayout;
                Intrinsics.checkParameterIsNotNull(animation, "animation");
                leftSlideLayout = SlideContainerLayout.this.mSlideView;
                if (leftSlideLayout == null) {
                    Intrinsics.throwNpe();
                }
                leftSlideLayout.setVisibility(0);
                SlideContainerLayout.access$getMBgColorView$p(SlideContainerLayout.this).setClickable(true);
            }

            /* JADX WARN: Removed duplicated region for block: B:8:0x005f  */
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onAnimationEnd(Animator animation) {
                int i;
                LeftSlideLayout leftSlideLayout;
                LeftSlideLayout leftSlideLayout2;
                int i2;
                Intrinsics.checkParameterIsNotNull(animation, "animation");
                if (!SlideContainerLayout.this.getIsSlideShow()) {
                    i2 = SlideContainerLayout.this.translateX;
                    if (i2 == 0) {
                        SlideContainerLayout.this.setSlideShow(!r4.getIsSlideShow());
                        if (!SlideContainerLayout.this.getIsSlideShow()) {
                            SlideContainerLayout.this.getParent().requestDisallowInterceptTouchEvent(false);
                            leftSlideLayout2 = SlideContainerLayout.this.mSlideView;
                            if (leftSlideLayout2 == null) {
                                Intrinsics.throwNpe();
                            }
                            leftSlideLayout2.setVisibility(8);
                            SlideContainerLayout slideContainerLayout = SlideContainerLayout.this;
                            slideContainerLayout.removeView(SlideContainerLayout.access$getMBgColorView$p(slideContainerLayout));
                            SlideContainerLayout slideContainerLayout2 = SlideContainerLayout.this;
                            slideContainerLayout2.addView(SlideContainerLayout.access$getMBgColorView$p(slideContainerLayout2), SlideContainerLayout.this.getChildCount() - 4);
                        }
                        SlideContainerLayout.this.isSliderGoning = false;
                    }
                }
                if (SlideContainerLayout.this.getIsSlideShow()) {
                    i = SlideContainerLayout.this.translateX;
                    int abs = Math.abs(i);
                    int width = SlideContainerLayout.this.getWidth();
                    leftSlideLayout = SlideContainerLayout.this.mSlideView;
                    if (leftSlideLayout == null) {
                        Intrinsics.throwNpe();
                    }
                    if (abs == width - leftSlideLayout.getPaddingRight()) {
                        SlideContainerLayout.this.setSlideShow(!r4.getIsSlideShow());
                    }
                }
                if (!SlideContainerLayout.this.getIsSlideShow()) {
                }
                SlideContainerLayout.this.isSliderGoning = false;
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        if (r5.isSlideShow == false) goto L15;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        if (!valueAnimator.isRunning()) {
            ValueAnimator valueAnimator2 = this.mSlideInAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
            }
            if (!valueAnimator2.isRunning()) {
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        int action = event.getAction();
        if (action == 0) {
            this.mDownX = rawX;
            this.mDownY = rawY;
        } else if (action == 1) {
            this.isSlideVertical = Math.abs(rawY - this.mDownY) > 5;
        }
        return super.dispatchTouchEvent(event);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        if (r6.isSlideShow == false) goto L15;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        if (!valueAnimator.isRunning()) {
            ValueAnimator valueAnimator2 = this.mSlideInAnimator;
            if (valueAnimator2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
            }
            if (!valueAnimator2.isRunning()) {
            }
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        int action = event.getAction();
        if (action == 0) {
            this.mDownX = rawX;
            this.mDownY = rawY;
        } else if (action == 1) {
            this.isSlideVertical = Math.abs(rawY - this.mDownY) > 5;
        } else if (action == 2) {
            ValueAnimator valueAnimator3 = this.mClearAnimator;
            if (valueAnimator3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
            }
            if (!valueAnimator3.isRunning() && this.mDownY > 200 && Math.abs(rawX - this.mDownX) > Math.abs(rawY - this.mDownY) && Math.abs(rawX - this.mDownX) > 10) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int i;
        int i2;
        Intrinsics.checkParameterIsNotNull(event, "event");
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            Intrinsics.throwNpe();
        }
        velocityTracker.addMovement(event);
        int rawX = ((int) event.getRawX()) - this.mDownX;
        if (this.mLastOffsetList.size() > 2) {
            this.mLastOffsetList.removeFirst();
        }
        this.mLastOffsetList.add(Integer.valueOf(rawX));
        Integer first = this.mLastOffsetList.getFirst();
        Intrinsics.checkExpressionValueIsNotNull(first, "mLastOffsetList.first");
        boolean z = rawX - first.intValue() > 0;
        int action = event.getAction();
        if (action != 1) {
            if (action == 2) {
                if (this.isSlideShow && rawX < 0) {
                    ValueAnimator valueAnimator = this.mSlideInAnimator;
                    if (valueAnimator == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                    }
                    if (valueAnimator.isRunning() && !this.isSliderGoning) {
                        ValueAnimator valueAnimator2 = this.mSlideInAnimator;
                        if (valueAnimator2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                        }
                        valueAnimator2.cancel();
                        translateSlideView(-rawX);
                    }
                }
                if (this.isSlideShow && rawX < 0) {
                    ValueAnimator valueAnimator3 = this.mSlideInAnimator;
                    if (valueAnimator3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                    }
                    if (!valueAnimator3.isRunning()) {
                        translateSlideView(-rawX);
                    }
                }
                return true;
            }
            if (action == 3 && this.isSlideShow) {
                this.startX = this.translateX;
                ValueAnimator valueAnimator4 = this.mSlideInAnimator;
                if (valueAnimator4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                }
                valueAnimator4.start();
            }
            return super.onTouchEvent(event);
        }
        VelocityTracker velocityTracker2 = this.mVelocityTracker;
        if (velocityTracker2 == null) {
            Intrinsics.throwNpe();
        }
        velocityTracker2.computeCurrentVelocity(10);
        if (this.isSlideShow && rawX > 0 && Math.abs(rawX) > getWidth() / 3 && !this.isSliderGoning) {
            VelocityTracker velocityTracker3 = this.mVelocityTracker;
            if (velocityTracker3 == null) {
                Intrinsics.throwNpe();
            }
            if (velocityTracker3.getXVelocity() >= 0) {
                this.startX = rawX;
                int width = getWidth();
                LeftSlideLayout leftSlideLayout = this.mSlideView;
                if (leftSlideLayout == null) {
                    Intrinsics.throwNpe();
                }
                this.endX = width - leftSlideLayout.getPaddingRight();
                this.isSliderGoning = true;
                ValueAnimator valueAnimator5 = this.mSlideInAnimator;
                if (valueAnimator5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                }
                valueAnimator5.start();
                return true;
            }
        }
        VelocityTracker velocityTracker4 = this.mVelocityTracker;
        if (velocityTracker4 == null) {
            Intrinsics.throwNpe();
        }
        if (Math.abs(velocityTracker4.getXVelocity()) > 1) {
            if (this.isCleared && rawX > 0) {
                layerShowWithAnim();
            } else {
                if (!this.isCleared && rawX < 0 && !this.isSlideShow) {
                    ValueAnimator valueAnimator6 = this.mSlideInAnimator;
                    if (valueAnimator6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                    }
                    if (!valueAnimator6.isRunning()) {
                        layerGoneWithAnim();
                    }
                }
                if (this.isSlideShow && rawX < 0 && z) {
                    ValueAnimator valueAnimator7 = this.mSlideInAnimator;
                    if (valueAnimator7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                    }
                    valueAnimator7.cancel();
                    this.isSliderGoning = true;
                    this.startX = this.translateX;
                    int width2 = getWidth();
                    LeftSlideLayout leftSlideLayout2 = this.mSlideView;
                    if (leftSlideLayout2 == null) {
                        Intrinsics.throwNpe();
                    }
                    this.endX = width2 - leftSlideLayout2.getPaddingRight();
                    ValueAnimator valueAnimator8 = this.mSlideInAnimator;
                    if (valueAnimator8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                    }
                    valueAnimator8.start();
                } else if (this.isSlideShow && rawX < 0 && this.translateX != 0) {
                    sliderGone();
                } else {
                    if (!this.isSlideShow && rawX > 0) {
                        ValueAnimator valueAnimator9 = this.mSlideInAnimator;
                        if (valueAnimator9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                        }
                        if (!valueAnimator9.isRunning()) {
                            sliderShowWithAnim();
                        }
                    }
                    if (this.isSlideShow && (i2 = this.translateX) != 0) {
                        this.startX = i2;
                        ValueAnimator valueAnimator10 = this.mSlideInAnimator;
                        if (valueAnimator10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
                        }
                        valueAnimator10.start();
                    }
                }
            }
        } else if (this.isSlideShow && (i = this.translateX) != 0) {
            this.startX = i;
            ValueAnimator valueAnimator11 = this.mSlideInAnimator;
            if (valueAnimator11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
            }
            valueAnimator11.start();
        }
        return super.onTouchEvent(event);
    }

    public final void addClearViews(View... views) {
        Intrinsics.checkParameterIsNotNull(views, "views");
        for (View view : views) {
            if (!CollectionsKt.contains(this.mClearViews, view)) {
                ArrayList<View> arrayList = this.mClearViews;
                if (view == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(view);
            }
        }
    }

    public final void addSlideView(LeftSlideLayout view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.mSlideView = view;
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout != null) {
            leftSlideLayout.setSlideContainerView(this);
        }
        LeftSlideLayout leftSlideLayout2 = this.mSlideView;
        if (leftSlideLayout2 != null) {
            ViewExtKt.onSingleClick(leftSlideLayout2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.widget.slide.SlideContainerLayout$addSlideView$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                    invoke2(view2);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    SlideContainerLayout.this.sliderGone();
                }
            });
        }
    }

    private final void layerShowWithAnim() {
        this.startX = getWidth();
        this.endX = 0;
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        valueAnimator.start();
    }

    private final void layerGoneWithAnim() {
        this.startX = 0;
        this.endX = getWidth();
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sliderGone() {
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout != null) {
            leftSlideLayout.setVisibility(8);
        }
        this.isSlideShow = false;
        View view = this.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view.setBackgroundColor(this.MASK_TRANSPANT_COLOR);
        View view2 = this.mBgColorView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        removeView(view2);
        View view3 = this.mBgColorView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        addView(view3, getChildCount() - 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sliderGoneWithAnim() {
        View view = this.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view.setClickable(true);
        this.isSliderGoning = true;
        this.startX = this.translateX;
        int width = getWidth();
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout == null) {
            Intrinsics.throwNpe();
        }
        this.endX = width - leftSlideLayout.getPaddingRight();
        ValueAnimator valueAnimator = this.mSlideInAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator.start();
    }

    private final void sliderShowWithAnim() {
        ValueAnimator valueAnimator = this.mSlideInAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        if (valueAnimator.isRunning()) {
            return;
        }
        ValueAnimator valueAnimator2 = this.mClearAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        if (valueAnimator2.isRunning()) {
            return;
        }
        View view = this.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        removeView(view);
        View view2 = this.mBgColorView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        addView(view2, getChildCount() - 1);
        int measuredWidth = getMeasuredWidth();
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout == null) {
            Intrinsics.throwNpe();
        }
        this.startX = measuredWidth - leftSlideLayout.getPaddingRight();
        int measuredWidth2 = getMeasuredWidth();
        LeftSlideLayout leftSlideLayout2 = this.mSlideView;
        if (leftSlideLayout2 == null) {
            Intrinsics.throwNpe();
        }
        this.translateX = measuredWidth2 - leftSlideLayout2.getPaddingRight();
        this.endX = 0;
        ValueAnimator valueAnimator3 = this.mSlideInAnimator;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator3.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translateClearChild(int translate) {
        int size = this.mClearViews.size();
        for (int i = 0; i < size; i++) {
            View view = this.mClearViews.get(i);
            Intrinsics.checkExpressionValueIsNotNull(view, "mClearViews[i]");
            view.setTranslationX(translate);
        }
    }

    public final boolean isAlignLeftSide() {
        return this.isSlideShow && this.translateX == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void translateSlideView(int translate) {
        if (this.mSlideView == null) {
            Intrinsics.throwNpe();
        }
        float f = translate;
        float width = r0.getWidth() - f;
        if (this.mSlideView == null) {
            Intrinsics.throwNpe();
        }
        int width2 = ((int) (this.MASK_DARK_COLOR * (width / r2.getWidth()))) << 24;
        View view = this.mBgColorView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBgColorView");
        }
        view.setBackgroundColor(width2);
        this.translateX = translate;
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout == null) {
            Intrinsics.throwNpe();
        }
        leftSlideLayout.setTranslationX(-f);
        Pdlog.m3273d(this.TAG, String.valueOf(f));
    }

    public final void reset() {
        ValueAnimator valueAnimator = this.mClearAnimator;
        if (valueAnimator == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mClearAnimator");
        }
        valueAnimator.cancel();
        ValueAnimator valueAnimator2 = this.mSlideInAnimator;
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideInAnimator");
        }
        valueAnimator2.cancel();
        if (this.isCleared) {
            translateClearChild(0);
        }
        LeftSlideLayout leftSlideLayout = this.mSlideView;
        if (leftSlideLayout == null) {
            Intrinsics.throwNpe();
        }
        leftSlideLayout.setVisibility(8);
        this.translateX = 0;
        this.isSliderGoning = false;
        this.isSlideShow = false;
        this.isCleared = false;
    }

    public final void release() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
    }

    public final void setDownXY(int downX, int downY) {
        this.mDownX = downX;
        this.mDownY = downY;
    }
}
