package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraLogItemBinding extends ViewDataBinding {

    @Bindable
    protected String mItem;
    public final TextView nameView;

    public abstract void setItem(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraLogItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.nameView = textView;
    }

    public String getItem() {
        return this.mItem;
    }

    public static LoraLogItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraLogItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraLogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_log_item, viewGroup, z, obj);
    }

    public static LoraLogItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraLogItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraLogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_log_item, null, false, obj);
    }

    public static LoraLogItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraLogItemBinding bind(View view, Object obj) {
        return (LoraLogItemBinding) bind(obj, view, C3965R.layout.lora_log_item);
    }
}
