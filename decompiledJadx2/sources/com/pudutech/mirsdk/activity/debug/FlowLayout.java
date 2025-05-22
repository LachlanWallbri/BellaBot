package com.pudutech.mirsdk.activity.debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlowLayout.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0014J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\tH\u0014R\u001c\u0010\u000b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/debug/FlowLayout;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "mAllChildViews", "", "Landroid/view/View;", "mLineHeights", "generateLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "onLayout", "", "changed", "", "l", "t", "r", "b", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FlowLayout extends ViewGroup {
    private HashMap _$_findViewCache;
    private List<List<View>> mAllChildViews;
    private List<Integer> mLineHeights;

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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlowLayout(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mAllChildViews = new ArrayList();
        this.mLineHeights = new ArrayList();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        View.MeasureSpec.getMode(widthMeasureSpec);
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childCount = getChildCount();
        List<List<View>> list = this.mAllChildViews;
        if (list != null) {
            list.clear();
        }
        List<Integer> list2 = this.mLineHeights;
        if (list2 != null) {
            list2.clear();
        }
        int i = 0;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        while (i < childCount) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            Intrinsics.checkExpressionValueIsNotNull(childView, "childView");
            ViewGroup.LayoutParams layoutParams = childView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int measuredWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int i4 = size2;
            int measuredHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            i2 += measuredWidth;
            if (i2 > (size - getPaddingRight()) - getPaddingLeft()) {
                paddingTop += i3;
                List<List<View>> list3 = this.mAllChildViews;
                if (list3 != null) {
                    list3.add(arrayList);
                }
                List<Integer> list4 = this.mLineHeights;
                if (list4 != null) {
                    list4.add(Integer.valueOf(i3));
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(childView);
                arrayList = arrayList2;
                i2 = measuredWidth;
            } else {
                int max = Math.max(measuredHeight, i3);
                arrayList.add(childView);
                i3 = max;
            }
            if (i == childCount - 1) {
                paddingTop += i3;
                List<Integer> list5 = this.mLineHeights;
                if (list5 != null) {
                    list5.add(Integer.valueOf(i3));
                }
                List<List<View>> list6 = this.mAllChildViews;
                if (list6 != null) {
                    list6.add(arrayList);
                }
            }
            i++;
            size2 = i4;
        }
        int i5 = size2;
        if (mode != Integer.MIN_VALUE) {
            paddingTop = i5;
        }
        setMeasuredDimension(size, paddingTop);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        List<List<View>> list = this.mAllChildViews;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        int size = list.size();
        int i = paddingTop;
        int i2 = paddingLeft;
        for (int i3 = 0; i3 < size; i3++) {
            List<List<View>> list2 = this.mAllChildViews;
            if (list2 == null) {
                Intrinsics.throwNpe();
            }
            List<View> list3 = list2.get(i3);
            List<Integer> list4 = this.mLineHeights;
            if (list4 == null) {
                Intrinsics.throwNpe();
            }
            int intValue = list4.get(i3).intValue();
            int size2 = list3.size();
            int i4 = i2;
            for (int i5 = 0; i5 < size2; i5++) {
                View view = list3.get(i5);
                if (view.getVisibility() != 8) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    if (layoutParams == null) {
                        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                    }
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int i6 = marginLayoutParams.leftMargin + i4;
                    int i7 = marginLayoutParams.topMargin + i;
                    view.layout(i6, i7, view.getMeasuredWidth() + i6, view.getMeasuredHeight() + i7);
                    i4 += view.getMeasuredWidth() + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                }
            }
            i2 = getPaddingLeft();
            i += intValue;
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }
}
