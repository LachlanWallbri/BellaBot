package com.pudutech.mpmodule.p060ui.widget;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CItemTouchHelper<T> extends ItemTouchHelper.Callback {
    private RecyclerView.Adapter mAdapter;
    private List<T> mData;
    private OnSelectedChangedCallback mOnSelectedChangedCallback;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnSelectedChangedCallback {
        void onSelectedChanged();
    }

    public CItemTouchHelper(RecyclerView.Adapter adapter, List<T> list) {
        this.mAdapter = adapter;
        this.mData = list;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(recyclerView.getLayoutManager() instanceof GridLayoutManager ? 15 : 3, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = viewHolder2.getAdapterPosition();
        if (adapterPosition2 >= this.mData.size()) {
            return false;
        }
        if (adapterPosition < adapterPosition2) {
            int i = adapterPosition;
            while (i < adapterPosition2) {
                int i2 = i + 1;
                Collections.swap(this.mData, i, i2);
                i = i2;
            }
        } else {
            for (int i3 = adapterPosition; i3 > adapterPosition2; i3--) {
                Collections.swap(this.mData, i3, i3 - 1);
            }
        }
        this.mAdapter.notifyItemMoved(adapterPosition, adapterPosition2);
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Pdlog.m3273d("CItemTouchHelper", "onSwiped()");
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        OnSelectedChangedCallback onSelectedChangedCallback;
        Pdlog.m3273d("CItemTouchHelper", "onSelectedChanged() actionState=" + i);
        if (i != 0 || (onSelectedChangedCallback = this.mOnSelectedChangedCallback) == null) {
            return;
        }
        onSelectedChangedCallback.onSelectedChanged();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
        Pdlog.m3273d("CItemTouchHelper", "onMoved()");
    }

    public void setOnSelectedChangedCallback(OnSelectedChangedCallback onSelectedChangedCallback) {
        this.mOnSelectedChangedCallback = onSelectedChangedCallback;
    }
}
