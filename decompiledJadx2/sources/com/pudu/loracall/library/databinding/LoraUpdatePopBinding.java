package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraUpdatePopBinding extends ViewDataBinding {
    public final ImageView closeBut;
    public final RecyclerView recyclerView;

    /* JADX INFO: Access modifiers changed from: protected */
    public LoraUpdatePopBinding(Object obj, View view, int i, ImageView imageView, RecyclerView recyclerView) {
        super(obj, view, i);
        this.closeBut = imageView;
        this.recyclerView = recyclerView;
    }

    public static LoraUpdatePopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraUpdatePopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_pop, viewGroup, z, obj);
    }

    public static LoraUpdatePopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraUpdatePopBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_update_pop, null, false, obj);
    }

    public static LoraUpdatePopBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraUpdatePopBinding bind(View view, Object obj) {
        return (LoraUpdatePopBinding) bind(obj, view, C3965R.layout.lora_update_pop);
    }
}
