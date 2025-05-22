package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraCommonMvvmBinding extends ViewDataBinding {

    @Bindable
    protected String mBinding;

    @Bindable
    protected String mIsSelect;

    @Bindable
    protected String mIsfood;

    @Bindable
    protected String mItem;

    @Bindable
    protected String mMContext;

    @Bindable
    protected String mOldItem;

    @Bindable
    protected String mPosition;

    @Bindable
    protected String mViewModel;

    public abstract void setBinding(String str);

    public abstract void setIsSelect(String str);

    public abstract void setIsfood(String str);

    public abstract void setItem(String str);

    public abstract void setMContext(String str);

    public abstract void setOldItem(String str);

    public abstract void setPosition(String str);

    public abstract void setViewModel(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraCommonMvvmBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public String getViewModel() {
        return this.mViewModel;
    }

    public String getItem() {
        return this.mItem;
    }

    public String getBinding() {
        return this.mBinding;
    }

    public String getPosition() {
        return this.mPosition;
    }

    public String getIsfood() {
        return this.mIsfood;
    }

    public String getIsSelect() {
        return this.mIsSelect;
    }

    public String getOldItem() {
        return this.mOldItem;
    }

    public String getMContext() {
        return this.mMContext;
    }

    public static LoraCommonMvvmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonMvvmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraCommonMvvmBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_common_mvvm, viewGroup, z, obj);
    }

    public static LoraCommonMvvmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonMvvmBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraCommonMvvmBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_common_mvvm, null, false, obj);
    }

    public static LoraCommonMvvmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonMvvmBinding bind(View view, Object obj) {
        return (LoraCommonMvvmBinding) bind(obj, view, C3965R.layout.lora_common_mvvm);
    }
}
