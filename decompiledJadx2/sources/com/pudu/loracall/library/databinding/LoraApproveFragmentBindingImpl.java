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
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public class LoraApproveFragmentBindingImpl extends LoraApproveFragmentBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(C3965R.id.rzLayout, 1);
        sViewsWithIds.put(C3965R.id.switchView, 2);
        sViewsWithIds.put(C3965R.id.rfModuleView, 3);
        sViewsWithIds.put(C3965R.id.rfModuleEdit, 4);
        sViewsWithIds.put(C3965R.id.waveformView, 5);
        sViewsWithIds.put(C3965R.id.sendType, 6);
        sViewsWithIds.put(C3965R.id.fixedLayout, 7);
        sViewsWithIds.put(C3965R.id.powerView, 8);
        sViewsWithIds.put(C3965R.id.powerEdit, 9);
        sViewsWithIds.put(C3965R.id.sendPowerView, 10);
        sViewsWithIds.put(C3965R.id.sendPowerEdit, 11);
        sViewsWithIds.put(C3965R.id.silentValueView, 12);
        sViewsWithIds.put(C3965R.id.silentValueEdit, 13);
        sViewsWithIds.put(C3965R.id.sendModelView, 14);
        sViewsWithIds.put(C3965R.id.sendCycleView, 15);
        sViewsWithIds.put(C3965R.id.sendCycleEdit, 16);
        sViewsWithIds.put(C3965R.id.receiveFrequencyView, 17);
        sViewsWithIds.put(C3965R.id.receiveFrequencyEdit, 18);
        sViewsWithIds.put(C3965R.id.sendButton, 19);
        sViewsWithIds.put(C3965R.id.signalLayout, 20);
        sViewsWithIds.put(C3965R.id.signalStateView, 21);
        sViewsWithIds.put(C3965R.id.signalRefreshView, 22);
        sViewsWithIds.put(C3965R.id.receiveCycleHint, 23);
        sViewsWithIds.put(C3965R.id.receiveCycleView, 24);
        sViewsWithIds.put(C3965R.id.receiveCountView, 25);
    }

    public LoraApproveFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 26, sIncludes, sViewsWithIds));
    }

    private LoraApproveFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RelativeLayout) objArr[7], (EditText) objArr[9], (TextView) objArr[8], (TextView) objArr[25], (TextView) objArr[23], (TextView) objArr[24], (EditText) objArr[18], (TextView) objArr[17], (EditText) objArr[4], (TextView) objArr[3], (LinearLayout) objArr[1], (Button) objArr[19], (EditText) objArr[16], (TextView) objArr[15], (TextView) objArr[14], (EditText) objArr[11], (TextView) objArr[10], (TextView) objArr[6], (RelativeLayout) objArr[20], (ImageView) objArr[22], (TextView) objArr[21], (EditText) objArr[13], (TextView) objArr[12], (Switch) objArr[2], (TextView) objArr[5]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (NestedScrollView) objArr[0];
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
