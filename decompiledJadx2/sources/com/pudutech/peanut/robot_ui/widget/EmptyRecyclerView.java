package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class EmptyRecyclerView extends RecyclerView {
    private static final String TAG = "hiwhitley";
    private View emptyView;
    private final RecyclerView.AdapterDataObserver observer;

    public EmptyRecyclerView(Context context) {
        super(context);
        this.observer = new RecyclerView.AdapterDataObserver() { // from class: com.pudutech.peanut.robot_ui.widget.EmptyRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                Log.i(EmptyRecyclerView.TAG, "onItemRangeInserted" + i2);
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                EmptyRecyclerView.this.checkIfEmpty();
            }
        };
    }

    public EmptyRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.observer = new RecyclerView.AdapterDataObserver() { // from class: com.pudutech.peanut.robot_ui.widget.EmptyRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i, int i2) {
                Log.i(EmptyRecyclerView.TAG, "onItemRangeInserted" + i2);
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i, int i2) {
                EmptyRecyclerView.this.checkIfEmpty();
            }
        };
    }

    public EmptyRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.observer = new RecyclerView.AdapterDataObserver() { // from class: com.pudutech.peanut.robot_ui.widget.EmptyRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i2, int i22) {
                Log.i(EmptyRecyclerView.TAG, "onItemRangeInserted" + i22);
                EmptyRecyclerView.this.checkIfEmpty();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i2, int i22) {
                EmptyRecyclerView.this.checkIfEmpty();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIfEmpty() {
        if (this.emptyView == null || getAdapter() == null) {
            return;
        }
        boolean z = getAdapter().getItemCount() == 0;
        this.emptyView.setVisibility(z ? 0 : 8);
        setVisibility(z ? 8 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        RecyclerView.Adapter adapter2 = getAdapter();
        if (adapter2 != null) {
            adapter2.unregisterAdapterDataObserver(this.observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(this.observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View view) {
        this.emptyView = view;
        checkIfEmpty();
    }
}
