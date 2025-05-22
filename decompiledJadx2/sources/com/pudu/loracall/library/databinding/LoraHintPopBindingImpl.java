package com.pudu.loracall.library.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.pudu.library.loracall.utils.DetailBindingAdaptersKt;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public class LoraHintPopBindingImpl extends LoraHintPopBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ConstraintLayout mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        sViewsWithIds.put(C3965R.id.lineView, 4);
        sViewsWithIds.put(C3965R.id.closeBut, 5);
        sViewsWithIds.put(C3965R.id.contentView, 6);
    }

    public LoraHintPopBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private LoraHintPopBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[2], (ImageView) objArr[5], (TextView) objArr[6], (View) objArr[4], (TextView) objArr[3]);
        this.mDirtyFlags = -1L;
        this.cancelView.setTag(null);
        this.mboundView0 = (FrameLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (ConstraintLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.okView.setTag(null);
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
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        if ((j & 1) != 0) {
            String str = (String) null;
            DetailBindingAdaptersKt.viewShape(this.cancelView, Float.valueOf(8.0f), 0.0f, 0.0f, 0.0f, 0.0f, str, Float.valueOf(2.0f), "#ff0072ff");
            DetailBindingAdaptersKt.viewRadius(this.mboundView1, 8);
            DetailBindingAdaptersKt.viewShape(this.okView, Float.valueOf(8.0f), 0.0f, 0.0f, 0.0f, 0.0f, "#0072FF", (Float) null, str);
        }
    }
}
