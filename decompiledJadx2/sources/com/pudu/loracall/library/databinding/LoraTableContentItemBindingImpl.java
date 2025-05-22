package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.pudu.library.loracall.dao.TableMatchBean;
import com.pudu.library.loracall.utils.DetailBindingAdaptersKt;
import com.pudu.loracall.library.BR;

/* loaded from: classes4.dex */
public class LoraTableContentItemBindingImpl extends LoraTableContentItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public LoraTableContentItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private LoraTableContentItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (TextView) objArr[2], (LinearLayout) objArr[0], (TextView) objArr[1]);
        this.mDirtyFlags = -1L;
        this.idView.setTag(null);
        this.itemLayoutView.setTag(null);
        this.tableView.setTag(null);
        setRootTag(view);
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
        if (BR.item != i) {
            return false;
        }
        setItem((TableMatchBean) obj);
        return true;
    }

    @Override // com.pudu.loracall.library.databinding.LoraTableContentItemBinding
    public void setItem(TableMatchBean tableMatchBean) {
        updateRegistration(0, tableMatchBean);
        this.mItem = tableMatchBean;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeItem((TableMatchBean) obj, i2);
    }

    private boolean onChangeItem(TableMatchBean tableMatchBean, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (i != BR.devAdder) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TableMatchBean tableMatchBean = this.mItem;
        long j2 = 7 & j;
        String str2 = null;
        if (j2 != 0) {
            str = ((j & 5) == 0 || tableMatchBean == null) ? null : tableMatchBean.getTableName();
            if (tableMatchBean != null) {
                str2 = tableMatchBean.getDevAdder();
            }
        } else {
            str = null;
        }
        if (j2 != 0) {
            DetailBindingAdaptersKt.setDevAdder(this.idView, str2);
            DetailBindingAdaptersKt.visibilityEmpty(this.idView, str2);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.tableView, str);
        }
    }
}
