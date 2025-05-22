package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;
import java.io.File;

/* loaded from: classes4.dex */
public abstract class LoraUpdatePopItemBinding extends ViewDataBinding {
    public final TextView installBut;

    @Bindable
    protected File mItem;
    public final TextView nameView;

    public abstract void setItem(File file);

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraUpdatePopItemBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.installBut = textView;
        this.nameView = textView2;
    }

    public File getItem() {
        return this.mItem;
    }

    public static LoraUpdatePopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraUpdatePopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_pop_item, viewGroup, z, obj);
    }

    public static LoraUpdatePopItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraUpdatePopItemBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_pop_item, null, false, obj);
    }

    public static LoraUpdatePopItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopItemBinding bind(View view, Object obj) {
        return (LoraUpdatePopItemBinding) bind(obj, view, C3965R.layout.lora_update_pop_item);
    }
}
