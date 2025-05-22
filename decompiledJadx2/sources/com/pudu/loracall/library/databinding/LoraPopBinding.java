package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraPopBinding extends ViewDataBinding {
    public final RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraPopBinding(Object obj, View view, int i, RecyclerView recyclerView) {
        super(obj, view, i);
        this.recyclerView = recyclerView;
    }

    public static LoraPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop, viewGroup, z, obj);
    }

    public static LoraPopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraPopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_pop, null, false, obj);
    }

    public static LoraPopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraPopBinding bind(View view, Object obj) {
        return (LoraPopBinding) bind(obj, view, C3965R.layout.lora_pop);
    }
}
