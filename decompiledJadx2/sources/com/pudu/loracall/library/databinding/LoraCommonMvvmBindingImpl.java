package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.BR;

/* loaded from: classes4.dex */
public class LoraCommonMvvmBindingImpl extends LoraCommonMvvmBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public LoraCommonMvvmBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 1, sIncludes, sViewsWithIds));
    }

    private LoraCommonMvvmBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (ConstraintLayout) objArr[0];
        this.mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256L;
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
        if (BR.isfood == i) {
            setIsfood((String) obj);
        } else if (BR.item == i) {
            setItem((String) obj);
        } else if (BR.position == i) {
            setPosition((String) obj);
        } else if (BR.oldItem == i) {
            setOldItem((String) obj);
        } else if (BR.mContext == i) {
            setMContext((String) obj);
        } else if (BR.isSelect == i) {
            setIsSelect((String) obj);
        } else if (BR.binding == i) {
            setBinding((String) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((String) obj);
        }
        return true;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setIsfood(String str) {
        this.mIsfood = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setItem(String str) {
        this.mItem = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setPosition(String str) {
        this.mPosition = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setOldItem(String str) {
        this.mOldItem = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setMContext(String str) {
        this.mMContext = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setIsSelect(String str) {
        this.mIsSelect = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setBinding(String str) {
        this.mBinding = str;
    }

    @Override // com.pudu.loracall.library.databinding.LoraCommonMvvmBinding
    public void setViewModel(String str) {
        this.mViewModel = str;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        synchronized (this) {
            long j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
    }
}
