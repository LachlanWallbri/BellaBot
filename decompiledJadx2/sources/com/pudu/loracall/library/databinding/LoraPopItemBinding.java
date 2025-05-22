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
public abstract class LoraPopItemBinding extends ViewDataBinding {

    @Bindable
    protected Pair<String, Byte> mItem;
    public final TextView nameView;

    public abstract void setItem(Pair<String, Byte> pair);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraPopItemBinding(Object obj, View view, int i, TextView textView) {
        super(obj, view, i);
        this.nameView = textView;
    }

    public Pair<String, Byte> getItem() {
        return this.mItem;
    }

    public static LoraPopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraPopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop_item, viewGroup, z, obj);
    }

    public static LoraPopItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraPopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop_item, null, false, obj);
    }

    public static LoraPopItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopItemBinding bind(View view, Object obj) {
        return (LoraPopItemBinding) bind(obj, view, C3965R.layout.lora_pop_item);
    }
}
