package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecyclerViewClickListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0016B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\u0018\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/RecyclerViewClickListener;", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "context", "Landroid/content/Context;", "listener", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/RecyclerViewClickListener$OnItemClickListener;", "(Landroid/content/Context;Lcom/pudutech/bumblebee/robot_ui/ui_utils/RecyclerViewClickListener$OnItemClickListener;)V", "mLastDownX", "", "mLastDownY", "mListener", "touchSlop", "onInterceptTouchEvent", "", "rv", "Landroidx/recyclerview/widget/RecyclerView;", C3898x.f4338g, "Landroid/view/MotionEvent;", "onRequestDisallowInterceptTouchEvent", "", "disallowIntercept", "onTouchEvent", "OnItemClickListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
    private int mLastDownX;
    private int mLastDownY;
    private final OnItemClickListener mListener;
    private int touchSlop;

    /* compiled from: RecyclerViewClickListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/RecyclerViewClickListener$OnItemClickListener;", "", "onItemDownClick", "", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "onItemMoveClick", "onItemUpClick", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnItemClickListener {
        void onItemDownClick(View view, int position);

        void onItemMoveClick(View view, int position);

        void onItemUpClick(View view, int position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        Intrinsics.checkParameterIsNotNull(rv, "rv");
        Intrinsics.checkParameterIsNotNull(e, "e");
    }

    public RecyclerViewClickListener(Context context, OnItemClickListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        Intrinsics.checkExpressionValueIsNotNull(viewConfiguration, "ViewConfiguration.get(context)");
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.mListener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Intrinsics.checkParameterIsNotNull(rv, "rv");
        Intrinsics.checkParameterIsNotNull(e, "e");
        View findChildViewUnder = rv.findChildViewUnder(e.getX(), e.getY());
        if (findChildViewUnder != null) {
            Intrinsics.checkExpressionValueIsNotNull(findChildViewUnder, "rv.findChildViewUnder(e.x, e.y) ?: return false");
            int x = (int) e.getX();
            int y = (int) e.getY();
            int action = e.getAction();
            if (action == 0) {
                this.mLastDownX = x;
                this.mLastDownY = y;
                this.mListener.onItemDownClick(findChildViewUnder, rv.getChildLayoutPosition(findChildViewUnder));
            } else if (action != 1) {
                if (action == 2 && (Math.abs(x - this.mLastDownX) > this.touchSlop || Math.abs(y - this.mLastDownY) > this.touchSlop)) {
                    this.mListener.onItemMoveClick(findChildViewUnder, rv.getChildLayoutPosition(findChildViewUnder));
                }
            } else {
                this.mListener.onItemUpClick(findChildViewUnder, rv.getChildLayoutPosition(findChildViewUnder));
            }
        }
        return false;
    }
}
