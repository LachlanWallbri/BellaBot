package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.library.loracall.viewModel.LoRaTableMatchViewModel;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraTableTopItemBinding extends ViewDataBinding {

    @Bindable
    protected String mItem;

    @Bindable
    protected LoRaTableMatchViewModel mViewModel;
    public final TextView tabView;

    public abstract void setItem(String str);

    public abstract void setViewModel(LoRaTableMatchViewModel loRaTableMatchViewModel);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraTableTopItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.tabView = textView;
    }

    public String getItem() {
        return this.mItem;
    }

    public LoRaTableMatchViewModel getViewModel() {
        return this.mViewModel;
    }

    public static LoraTableTopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableTopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraTableTopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_table_top_item, viewGroup, z, obj);
    }

    public static LoraTableTopItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableTopItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraTableTopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_table_top_item, null, false, obj);
    }

    public static LoraTableTopItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraTableTopItemBinding bind(View view, Object obj) {
        return (LoraTableTopItemBinding) bind(obj, view, C3965R.layout.lora_table_top_item);
    }
}
