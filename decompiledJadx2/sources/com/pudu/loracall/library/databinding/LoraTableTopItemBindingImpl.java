package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel;
import com.pudu.loracall.library.BR;
import com.pudu.loracall.library.generated.callback.OnClickListener;

/* loaded from: classes4.dex */
public class LoraTableTopItemBindingImpl extends LoraTableTopItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback1;
    private long mDirtyFlags;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public LoraTableTopItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private LoraTableTopItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[0]);
        this.mDirtyFlags = -1L;
        this.tabView.setTag(null);
        setRootTag(view);
        this.mCallback1 = new OnClickListener(this, 1);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
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
    public boolean setVariable(int i, Object obj) {
        if (BR.item == i) {
            setItem((String) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((LoRaTableMatchViewModel) obj);
        }
        return true;
    }

    @Override // com.pudu.loracall.library.databinding.LoraTableTopItemBinding
    public void setItem(String str) {
        this.mItem = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override // com.pudu.loracall.library.databinding.LoraTableTopItemBinding
    public void setViewModel(LoRaTableMatchViewModel loRaTableMatchViewModel) {
        this.mViewModel = loRaTableMatchViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mItem;
        LoRaTableMatchViewModel loRaTableMatchViewModel = this.mViewModel;
        long j2 = 5 & j;
        if ((j & 4) != 0) {
            this.tabView.setOnClickListener(this.mCallback1);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.tabView, str);
        }
    }

    @Override // com.pudu.loracall.library.generated.callback.OnClickListener.Listener
    public final void _internalCallbackOnClick(int i, View view) {
        String str = this.mItem;
        LoRaTableMatchViewModel loRaTableMatchViewModel = this.mViewModel;
        if (loRaTableMatchViewModel != null) {
            loRaTableMatchViewModel.tabClickView(view, str);
        }
    }
}
