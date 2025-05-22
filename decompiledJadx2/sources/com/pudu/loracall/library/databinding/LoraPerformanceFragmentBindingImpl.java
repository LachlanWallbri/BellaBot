package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public class LoraPerformanceFragmentBindingImpl extends LoraPerformanceFragmentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(C3965R.id.switchView, 1);
        sViewsWithIds.put(C3965R.id.rfModuleView, 2);
        sViewsWithIds.put(C3965R.id.rfModuleEdit, 3);
        sViewsWithIds.put(C3965R.id.sendButton, 4);
        sViewsWithIds.put(C3965R.id.signalLayout, 5);
        sViewsWithIds.put(C3965R.id.signalStateView, 6);
        sViewsWithIds.put(C3965R.id.signalRefreshView, 7);
        sViewsWithIds.put(C3965R.id.receiveCycleHint, 8);
        sViewsWithIds.put(C3965R.id.receiveCycleView, 9);
        sViewsWithIds.put(C3965R.id.receiveCountView, 10);
    }

    public LoraPerformanceFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private LoraPerformanceFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[10], (TextView) objArr[8], (TextView) objArr[9], (EditText) objArr[3], (TextView) objArr[2], (Button) objArr[4], (RelativeLayout) objArr[5], (ImageView) objArr[7], (TextView) objArr[6], (Switch) objArr[1]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
    }
}
