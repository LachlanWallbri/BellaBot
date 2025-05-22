package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LinearSpacesItemDecoration.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/LinearSpacesItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "space", "", "(I)V", "getSpace", "()I", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LinearSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public LinearSpacesItemDecoration(int i) {
        this.space = i;
    }

    public final int getSpace() {
        return this.space;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkParameterIsNotNull(outRect, "outRect");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            if (orientation == 0) {
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                RecyclerView.Adapter adapter = parent.getAdapter();
                if (adapter == null || childAdapterPosition != adapter.getItemCount()) {
                    outRect.right = this.space;
                    return;
                }
                return;
            }
            if (orientation != 1) {
                return;
            }
            int childAdapterPosition2 = parent.getChildAdapterPosition(view);
            RecyclerView.Adapter adapter2 = parent.getAdapter();
            if (adapter2 == null || childAdapterPosition2 != adapter2.getItemCount()) {
                outRect.bottom = this.space;
                return;
            }
            return;
        }
        throw new RuntimeException("LinearSpacesItemDecoration only support LinearLayoutManager");
    }
}
