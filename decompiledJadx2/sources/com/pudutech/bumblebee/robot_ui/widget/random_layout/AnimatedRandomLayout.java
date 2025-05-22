package com.pudutech.bumblebee.robot_ui.widget.random_layout;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.pudutech.base.Pdlog;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class AnimatedRandomLayout extends FrameLayout {
    private List<AnimatorUtil> animatorUtilList;
    private Handler handler;
    private boolean isRunning;
    private List<View> justInitChilds;
    private int[][] mAreaDensity;
    private int mAreaNum;
    private Point mCenter;
    private int mDefaultDruation;
    private float mDiagonalLength;
    private Set<View> mFixedViews;
    private boolean mIsLayout;
    private int mItemShowCount;
    private int mLooperDuration;
    private int mOverlapAdd;
    private Random mRandom;
    private List<View> mRecycledViews;
    private int mTotalViewNum;
    private int mXRegularity;
    private int mYRegularity;
    private OnCreateItemViewListener onCreateItemViewListener;
    private WaveLoadingView waveLoadingView;

    /* loaded from: classes4.dex */
    public interface OnCreateItemViewListener {
        View createItemView(int i, View view);

        int getCount();
    }

    public AnimatedRandomLayout(Context context) {
        this(context, null);
    }

    public AnimatedRandomLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public AnimatedRandomLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDefaultDruation = 2000;
        this.mLooperDuration = 1000;
        this.mItemShowCount = 1;
        this.mOverlapAdd = 2;
        this.mIsLayout = false;
        this.animatorUtilList = new CopyOnWriteArrayList();
        this.handler = new Handler() { // from class: com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                AnimatedRandomLayout.this.loopChild();
            }
        };
        init();
    }

    private void init() {
        this.mRandom = new Random();
        setRegularity(1, 1);
        this.mFixedViews = new HashSet();
        this.mRecycledViews = new ArrayList();
        this.mCenter = new Point();
    }

    public void start() {
        this.isRunning = true;
        removeAllViews();
        this.mTotalViewNum = this.onCreateItemViewListener.getCount();
        this.justInitChilds = new ArrayList();
        loopChild();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loopChild() {
        Pdlog.m3275i("who", "loopChild begin");
        resetPanelForChild();
        generateChild();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AnimatedRandomLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                AnimatedRandomLayout.this.startZoomAnimation();
                Pdlog.m3275i("who", "loopChild animation");
            }
        });
        this.handler.sendEmptyMessageDelayed(0, this.mLooperDuration);
        Pdlog.m3275i("who", "loopChild end");
        Pdlog.m3275i("randomLayout", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>loopChild end" + getChildCount() + "  justInitChilds:" + this.justInitChilds.size());
    }

    public void stop() {
        this.isRunning = false;
        removeAllViews();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.isRunning = false;
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    private void generateChild() {
        if (this.onCreateItemViewListener == null) {
            return;
        }
        int size = this.mFixedViews.size();
        int nextInt = this.mRandom.nextInt(this.mItemShowCount) + size;
        Pdlog.m3275i("randomLayout", "count:" + nextInt);
        for (int i = nextInt - 1; i >= size; i--) {
            View popRecycler = popRecycler();
            View createItemView = this.onCreateItemViewListener.createItemView(i % this.mTotalViewNum, popRecycler);
            if (createItemView != popRecycler) {
                Pdlog.m3275i("randomLayout", "add view");
                pushRecycler(createItemView);
            }
            createItemView.setLayoutParams(new ChildViewBound(-2, -2));
            removeView(createItemView);
            addView(createItemView);
            this.justInitChilds.add(createItemView);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        boolean z2 = true;
        int i8 = 0;
        Pdlog.m3275i("randomLayout", "onLayout");
        int childCount = getChildCount();
        int paddingStart = ((i3 - i) - getPaddingStart()) - getPaddingEnd();
        int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        Point point = this.mCenter;
        point.x = paddingStart / 2;
        point.y = paddingTop / 2;
        this.mDiagonalLength = GeometryUtil.getDistanceBetween2Points(i - getPaddingStart(), i4 - getPaddingBottom(), this.mCenter);
        float f = paddingStart / this.mXRegularity;
        float f2 = paddingTop / this.mYRegularity;
        int i9 = this.mAreaNum;
        int i10 = ((childCount + 1) / i9) + 1;
        int i11 = i9;
        int i12 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            childAt.measure(i8, i8);
            if (childAt.getVisibility() != 8 && !this.mFixedViews.contains(childAt)) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int paddingEnd = (i3 - getPaddingEnd()) - measuredWidth;
                int paddingBottom = (i4 - getPaddingBottom()) - measuredHeight;
                while (i11 > 0) {
                    int nextInt = this.mRandom.nextInt(i11);
                    int i13 = this.mXRegularity;
                    int i14 = nextInt / i13;
                    int i15 = nextInt % i13;
                    if (this.mAreaDensity[i15][i14] < i10) {
                        int childInBlockOffestX = childInBlockOffestX((int) f, measuredWidth);
                        i5 = childCount;
                        int childInBlockOffsetY = childInBlockOffsetY((int) f2, measuredHeight);
                        i6 = i10;
                        ChildViewBound childViewBound = (ChildViewBound) childAt.getLayoutParams();
                        i7 = i12;
                        int min = Math.min(((int) (i15 * f)) + getPaddingStart() + childInBlockOffestX, paddingEnd);
                        int min2 = Math.min(((int) (i14 * f2)) + getPaddingTop() + childInBlockOffsetY, paddingBottom);
                        int i16 = measuredWidth + min;
                        int i17 = measuredHeight + min2;
                        childViewBound.setChildViewBound(min, min2, i16, i17);
                        childAt.layout(min, min2, i16, i17);
                        i8 = 0;
                        Pdlog.m3275i("randomLayout", "layout!!!!!!!!!!!!!" + min + "       availAreaNum:" + i11);
                        this.mFixedViews.add(childAt);
                        int[] iArr = this.mAreaDensity[i15];
                        iArr[i14] = iArr[i14] + 1;
                        break;
                    }
                    i8 = 0;
                    i11--;
                }
            }
            i5 = childCount;
            i6 = i10;
            i7 = i12;
            i12 = i7 + 1;
            i10 = i6;
            childCount = i5;
            z2 = true;
        }
        this.mIsLayout = z2;
    }

    private boolean isOverLap(ChildViewBound childViewBound) {
        Iterator<View> it = this.mFixedViews.iterator();
        while (it.hasNext()) {
            ChildViewBound childViewBound2 = (ChildViewBound) it.next().getLayoutParams();
            int max = Math.max(childViewBound.getChildLeft() - this.mOverlapAdd, childViewBound2.getChildLeft() - this.mOverlapAdd);
            int max2 = Math.max(childViewBound.getChildTop() - this.mOverlapAdd, childViewBound2.getChildTop() - this.mOverlapAdd);
            int min = Math.min(childViewBound.getChildRight() + this.mOverlapAdd, childViewBound2.getChildRight() + this.mOverlapAdd);
            int min2 = Math.min(childViewBound.getChildBottom() + this.mOverlapAdd, childViewBound2.getChildBottom() + this.mOverlapAdd);
            if (min - max > 0 || min2 - max2 > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.handler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();
    }

    public void setRegularity(int i, int i2) {
        if (i <= 1) {
            i = 1;
        }
        this.mXRegularity = i;
        if (i2 <= 1) {
            i2 = 1;
        }
        this.mYRegularity = i2;
        this.mAreaNum = this.mXRegularity * this.mYRegularity;
        initAreaDensity();
    }

    private void initAreaDensity() {
        this.mAreaDensity = (int[][]) Array.newInstance((Class<?>) int.class, this.mXRegularity, this.mYRegularity);
        resetAreasDensity();
    }

    public void setItemShowCount(int i) {
        if (i <= 1) {
            i = 1;
        }
        this.mItemShowCount = i;
    }

    public void setLooperDuration(int i) {
        this.mLooperDuration = i;
    }

    public void setDefaultDruation(int i) {
        this.mDefaultDruation = i;
    }

    public void setWaveLoadingView(WaveLoadingView waveLoadingView) {
        this.waveLoadingView = waveLoadingView;
    }

    private int childInBlockOffestX(int i, int i2) {
        int i3 = i - i2;
        if (i3 <= 0) {
            i3 = 1;
        }
        return this.mRandom.nextInt(i3);
    }

    private int childInBlockOffsetY(int i, int i2) {
        int i3 = i - i2;
        if (i3 <= 0) {
            i3 = 1;
        }
        return this.mRandom.nextInt(i3);
    }

    private void resetAreasDensity() {
        if (this.mAreaDensity != null) {
            for (int i = 0; i < this.mXRegularity; i++) {
                for (int i2 = 0; i2 < this.mYRegularity; i2++) {
                    this.mAreaDensity[i][i2] = 0;
                }
            }
        }
    }

    private void resetRecycler() {
        List<View> list = this.mRecycledViews;
        if (list != null) {
            list.clear();
        }
    }

    private void resetPanelForChild() {
        resetAreasDensity();
        resetRecycler();
    }

    private void resetAnimation() {
        List<AnimatorUtil> list = this.animatorUtilList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<AnimatorUtil> it = this.animatorUtilList.iterator();
        while (it.hasNext()) {
            it.next().cancelAnimation();
        }
        this.animatorUtilList.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushRecycler(View view) {
        if (view != null) {
            this.mRecycledViews.add(0, view);
        }
    }

    private View popRecycler() {
        if (this.mRecycledViews.size() > 0) {
            return this.mRecycledViews.remove(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startZoomAnimation() {
        for (final View view : this.justInitChilds) {
            ChildViewBound childViewBound = (ChildViewBound) view.getLayoutParams();
            int distanceBetween2Points = (int) ((this.mDefaultDruation * (GeometryUtil.getDistanceBetween2Points(childViewBound, this.mCenter) / this.mDiagonalLength)) + 0.5f);
            int right = (this.waveLoadingView.getRight() - this.waveLoadingView.getLeft()) / 2;
            if (((int) Math.sqrt(Math.pow(Math.abs(this.mCenter.x - childViewBound.getChildLeft()), 2.0d) + Math.pow(Math.abs(this.mCenter.y - childViewBound.getChildTop()), 2.0d))) <= right) {
                this.mFixedViews.remove(view);
                removeView(view);
            } else {
                Point point = GeometryUtil.getPoint(this.mCenter.x, this.mCenter.y, right, childViewBound.getChildLeft(), childViewBound.getChildTop(), this.mCenter.x, this.mCenter.y);
                float caculateDx = GeometryUtil.caculateDx(childViewBound, point);
                float caculateDy = GeometryUtil.caculateDy(childViewBound, point);
                final AnimatorUtil animatorUtil = new AnimatorUtil(view, distanceBetween2Points);
                animatorUtil.addAlphaAnimationBy(-1.0f).addTranslationAnimationBy(caculateDx, caculateDy).addScaleAnimationBy(-0.8f).startAnimator();
                animatorUtil.getAnimate().setListener(new AnimatorListenerAdapter() { // from class: com.pudutech.bumblebee.robot_ui.widget.random_layout.AnimatedRandomLayout.3
                    @Override // com.nineoldandroids.animation.AnimatorListenerAdapter, com.nineoldandroids.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        animatorUtil.cancelAnimation();
                        AnimatedRandomLayout.this.pushRecycler(view);
                        AnimatedRandomLayout.this.removeView(view);
                        AnimatedRandomLayout.this.mFixedViews.remove(view);
                        if (AnimatedRandomLayout.this.isRunning()) {
                            AnimatedRandomLayout.this.animatorUtilList.remove(animatorUtil);
                        }
                    }
                });
                this.animatorUtilList.add(animatorUtil);
            }
        }
        this.justInitChilds.clear();
        Pdlog.m3275i("randomLayout", "fix size:" + this.mFixedViews.size());
    }

    public boolean isLayout() {
        return this.mIsLayout;
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        resetAreasDensity();
        resetRecycler();
        resetAnimation();
    }

    public void refreshView() {
        resetAreasDensity();
        requestLayout();
    }

    public int getLooperDuration() {
        return this.mLooperDuration;
    }

    public int getDefaultDruation() {
        return this.mDefaultDruation;
    }

    public void setOnCreateItemViewListener(OnCreateItemViewListener onCreateItemViewListener) {
        this.onCreateItemViewListener = onCreateItemViewListener;
    }
}
