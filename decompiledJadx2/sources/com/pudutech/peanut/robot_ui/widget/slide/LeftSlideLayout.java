package com.pudutech.peanut.robot_ui.widget.slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LeftSlideLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/widget/slide/LeftSlideLayout;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "isSlideHorizontal", "", "mDownX", "", "mDownY", "mSlideContainerLayout", "Lcom/pudutech/peanut/robot_ui/widget/slide/SlideContainerLayout;", "dispatchTouchEvent", "event", "Landroid/view/MotionEvent;", "setSlideContainerView", "", "slideContainerLayout", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LeftSlideLayout extends RelativeLayout {
    private HashMap _$_findViewCache;
    private boolean isSlideHorizontal;
    private int mDownX;
    private int mDownY;
    private SlideContainerLayout mSlideContainerLayout;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LeftSlideLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public /* synthetic */ LeftSlideLayout(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? (AttributeSet) null : attributeSet);
    }

    public final void setSlideContainerView(SlideContainerLayout slideContainerLayout) {
        Intrinsics.checkParameterIsNotNull(slideContainerLayout, "slideContainerLayout");
        this.mSlideContainerLayout = slideContainerLayout;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r3 != 3) goto L66;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        int i = rawX - this.mDownX;
        SlideContainerLayout slideContainerLayout = this.mSlideContainerLayout;
        if (slideContainerLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
        }
        if (!slideContainerLayout.getIsSlideShow()) {
            return false;
        }
        int action = event.getAction();
        if (action == 0) {
            this.mDownX = rawX;
            this.mDownY = rawY;
            SlideContainerLayout slideContainerLayout2 = this.mSlideContainerLayout;
            if (slideContainerLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
            }
            slideContainerLayout2.setDownXY(this.mDownX, this.mDownY);
        } else {
            if (action != 1) {
                if (action == 2) {
                    if (Math.abs(rawX - this.mDownX) < Math.abs(rawY - this.mDownY) && getPaddingLeft() < rawX) {
                        if (this.isSlideHorizontal) {
                            SlideContainerLayout slideContainerLayout3 = this.mSlideContainerLayout;
                            if (slideContainerLayout3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
                            }
                            return slideContainerLayout3.dispatchTouchEvent(event);
                        }
                    } else {
                        if (i > 0) {
                            SlideContainerLayout slideContainerLayout4 = this.mSlideContainerLayout;
                            if (slideContainerLayout4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
                            }
                            if (slideContainerLayout4.isAlignLeftSide()) {
                                return super.dispatchTouchEvent(event);
                            }
                        }
                        if (Math.abs(rawX - this.mDownX) > Math.abs(rawY - this.mDownY)) {
                            this.isSlideHorizontal = true;
                            SlideContainerLayout slideContainerLayout5 = this.mSlideContainerLayout;
                            if (slideContainerLayout5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
                            }
                            return slideContainerLayout5.dispatchTouchEvent(event);
                        }
                    }
                }
            }
            if (i > 0) {
                SlideContainerLayout slideContainerLayout6 = this.mSlideContainerLayout;
                if (slideContainerLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
                }
                if (slideContainerLayout6.isAlignLeftSide()) {
                    return super.dispatchTouchEvent(event);
                }
            }
            if (Math.abs(rawX - this.mDownX) > Math.abs(rawY - this.mDownY) || this.isSlideHorizontal) {
                this.isSlideHorizontal = false;
                SlideContainerLayout slideContainerLayout7 = this.mSlideContainerLayout;
                if (slideContainerLayout7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mSlideContainerLayout");
                }
                return slideContainerLayout7.dispatchTouchEvent(event);
            }
            this.isSlideHorizontal = false;
        }
        return super.dispatchTouchEvent(event);
    }
}
