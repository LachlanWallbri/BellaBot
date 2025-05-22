package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes3.dex */
public class StaggeredDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Context context;
    private float interval;
    private int spanCount;

    public StaggeredDividerItemDecoration(Context context, float f, int i) {
        this.context = context;
        this.interval = f;
        this.spanCount = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        int i = (int) this.interval;
        if (spanIndex % this.spanCount == 0) {
            rect.right = i;
        }
        rect.bottom = i;
    }
}
