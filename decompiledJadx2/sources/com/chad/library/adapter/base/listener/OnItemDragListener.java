package com.chad.library.adapter.base.listener;

import androidx.recyclerview.widget.RecyclerView;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface OnItemDragListener {
    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i);

    void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2);

    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i);
}
