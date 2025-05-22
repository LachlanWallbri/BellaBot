package com.pudutech.freeinstall_ui.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: GridDividerItemDecoration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J(\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J(\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0002J(\u0010%\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0002J(\u0010&\u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020\u0003H\u0002J \u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006)"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/view/GridDividerItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "width", "", "height", TypedValues.Custom.S_COLOR, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "mDividerHeight", "mDividerWidth", "<set-?>", "Landroid/graphics/Paint;", "mPaint", "getMPaint", "()Landroid/graphics/Paint;", "setMPaint", "(Landroid/graphics/Paint;)V", "mPaint$delegate", "Lkotlin/properties/ReadWriteProperty;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "getItemOffsets", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "getSpanCount", "isFirstRow", "", "pos", "spanCount", "childCount", "isLastColumn", "isLastRow", "onDraw", "c", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(GridDividerItemDecoration.class), "mPaint", "getMPaint()Landroid/graphics/Paint;"))};
    private int mDividerHeight;
    private int mDividerWidth;

    /* renamed from: mPaint$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty mPaint;

    public GridDividerItemDecoration() {
        this(null, null, null, 7, null);
    }

    private final Paint getMPaint() {
        return (Paint) this.mPaint.getValue(this, $$delegatedProperties[0]);
    }

    private final void setMPaint(Paint paint) {
        this.mPaint.setValue(this, $$delegatedProperties[0], paint);
    }

    public GridDividerItemDecoration(Integer num, Integer num2, Integer num3) {
        this.mPaint = Delegates.INSTANCE.notNull();
        Integer num4 = num != null ? num : num2;
        this.mDividerWidth = num4 != null ? num4.intValue() : 0;
        num = num2 != null ? num2 : num;
        this.mDividerHeight = num != null ? num.intValue() : 0;
        setMPaint(new Paint(1));
        getMPaint().setColor(num3 != null ? num3.intValue() : 0);
        getMPaint().setStyle(Paint.Style.FILL);
    }

    public /* synthetic */ GridDividerItemDecoration(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? (Integer) null : num3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkParameterIsNotNull(outRect, "outRect");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Intrinsics.checkParameterIsNotNull(state, "state");
        super.getItemOffsets(outRect, view, parent, state);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(adapter, "parent.adapter!!");
        boolean isLastRow = isLastRow(parent, viewLayoutPosition, spanCount, adapter.getItemCount());
        int i = this.mDividerWidth;
        int i2 = ((spanCount - 1) * i) / spanCount;
        int i3 = (viewLayoutPosition % spanCount) * (i - i2);
        int i4 = i2 - i3;
        int i5 = this.mDividerHeight;
        if (isLastRow) {
            i5 = 0;
        }
        outRect.set(i3, 0, i4, i5);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Intrinsics.checkParameterIsNotNull(state, "state");
        super.onDraw(c, parent, state);
        draw(c, parent);
    }

    private final void draw(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "parent.getChildAt(i)");
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            }
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            boolean isLastRow = isLastRow(parent, i, getSpanCount(parent), childCount);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int bottom = childAt.getBottom() + layoutParams2.bottomMargin;
            int i2 = this.mDividerHeight + bottom;
            if (isLastRow) {
                i2 = bottom;
            }
            canvas.drawRect(left, bottom, right, i2, getMPaint());
            int top = childAt.getTop();
            int bottom2 = childAt.getBottom() + this.mDividerHeight;
            int right2 = childAt.getRight() + layoutParams2.rightMargin;
            int i3 = this.mDividerWidth + right2;
            if (isLastRow) {
                bottom2 = childAt.getBottom();
            }
            canvas.drawRect(right2, top, i3, bottom2, getMPaint());
        }
    }

    private final boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return (pos + 1) % spanCount == 0;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1 ? (pos + 1) % spanCount == 0 : pos >= childCount - (childCount % spanCount);
        }
        return false;
    }

    private final boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int i = childCount % spanCount;
            int i2 = childCount / spanCount;
            if (i != 0) {
                i2++;
            }
            return i2 == (pos / spanCount) + 1;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if (pos >= childCount - (childCount % spanCount)) {
                    return true;
                }
            } else if ((pos + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean isFirstRow(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return (pos / spanCount) + 1 == 1;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1) {
                if (pos >= childCount - (childCount % spanCount)) {
                    return true;
                }
            } else if ((pos + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    private final int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }
}
