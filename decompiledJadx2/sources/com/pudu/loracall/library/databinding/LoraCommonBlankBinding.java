package com.pudu.loracall.library.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pudu.loracall.library.C3965R;

/* loaded from: classes4.dex */
public abstract class LoraCommonBlankBinding extends ViewDataBinding {
    /* JADX INFO: Access modifiers changed from: protected */
    public LoraCommonBlankBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static LoraCommonBlankBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonBlankBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LoraCommonBlankBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_common_blank, viewGroup, z, obj);
    }

    public static LoraCommonBlankBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonBlankBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LoraCommonBlankBinding) ViewDataBinding.inflateInternal(layoutInflater, C3965R.layout.lora_common_blank, null, false, obj);
    }

    public static LoraCommonBlankBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LoraCommonBlankBinding bind(View view, Object obj) {
        return (LoraCommonBlankBinding) bind(obj, view, C3965R.layout.lora_common_blank);
    }
}
