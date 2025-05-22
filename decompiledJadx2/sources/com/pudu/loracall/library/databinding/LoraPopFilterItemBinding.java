package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;
import kotlin.Pair;

/* loaded from: classes4.dex */
public abstract class LoraPopFilterItemBinding extends ViewDataBinding {

    @Bindable
    protected Pair<String, Integer> mItem;
    public final TextView nameView;

    public abstract void setItem(Pair<String, Integer> pair);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraPopFilterItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.nameView = textView;
    }

    public Pair<String, Integer> getItem() {
        return this.mItem;
    }

    public static LoraPopFilterItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopFilterItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraPopFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop_filter_item, viewGroup, z, obj);
    }

    public static LoraPopFilterItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopFilterItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraPopFilterItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop_filter_item, null, false, obj);
    }

    public static LoraPopFilterItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopFilterItemBinding bind(View view, Object obj) {
        return (LoraPopFilterItemBinding) bind(obj, view, C3965R.layout.lora_pop_filter_item);
    }
}
